//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSFVEventUpdateAction.java

package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.cscdcalendar.GetCSFVEventsEvent;
import messaging.cscdcalendar.GetCSFVItineraryDetailsEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.CSFVItineraryBean;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class DisplayCSFVEventUpdateAction extends JIMSBaseAction {
	/**
	 * @roseuid 479A0F0902AC
	 */
	public DisplayCSFVEventUpdateAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.addFieldVisit", "create");
		keyMap.put("button.next", "reschedule");
		keyMap.put("button.update", "update");
	}

	public ActionForward update(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);

	}

	/**
	 * @throws GeneralFeedbackMessageException
	 * @roseuid 47A22E1201F0
	 */
	public ActionForward create(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		form.setActivityFlow(UIConstants.CREATE);
	    String forward = getCreateForwardPath(form, myHeaderForm);
		if (forward != null && forward.equalsIgnoreCase(UIConstants.FAILURE)) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Position Id provided is null or not valid.");
		}
		return aMapping.findForward(forward);

	}

	/**
	 * @throws GeneralFeedbackMessageException
	 * @roseuid 47A22E4302DB
	 */
	public ActionForward reschedule(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		form.setActivityFlow(UIConstants.CREATE);
		form.setActivityFlow("reschedule");
		String forward = getCreateForwardPath(form, myHeaderForm);
		return aMapping.findForward(forward);
	}

	private String getCreateForwardPath(CSCalendarFVForm form,
			SuperviseeHeaderForm myHeaderForm) {

		// Clearing any previously querried eventsList so that it does
		// not show up to look that they belong to the new itinerary

		if (form.getActivityFlow() != null
				&& form.getActivityFlow().equals("reschedule")) {
			form.getCurrentItinerary().clear();
			form.setEventsList(null);
			form.setEventsForSupervisee(null);
		} else {
			form.clear();
		}

		// Check if Itinerary exists, if it doesnt, then forward to
		// create itinerary page.
		GetCSFVItineraryDetailsEvent getItineraryEvent = (GetCSFVItineraryDetailsEvent) EventFactory
				.getInstance(CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);

		getItineraryEvent.setItineraryDate(form.getEventDate());
		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
				.getContext())) {
			if(!StringUtil.isEmpty(myHeaderForm.getOfficerPositionId())){
				getItineraryEvent.setPositionId(myHeaderForm.getOfficerPositionId());	
			}
			else {	
				return UIConstants.FAILURE;
			}
		} else {
			getItineraryEvent.setPositionId(form.getPositionId());
		}
		

		CompositeResponse response = postRequestEvent(getItineraryEvent);	

		CSFVItineraryResponseEvent itinerary = (CSFVItineraryResponseEvent) MessageUtil
				.filterComposite(response, CSFVItineraryResponseEvent.class);

		if (itinerary == null) {

			form.setSecondaryActivityFlow("createItinerary");
			form.setCurrentItinerary(new CSFVItineraryBean(form.getAgencyId()));
			form.getCurrentItinerary().setEventDate(form.getEventDate());

			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
					.getContext())) {
				if (myHeaderForm.getOfficerPositionId() != null) {
					form.getCurrentItinerary().setPositionId(myHeaderForm.getOfficerPositionId());
				}
			} else {
				form.getCurrentItinerary().setPositionId(form.getPositionId());
			}
			
			String staffPositionId = form.getCurrentItinerary().getPositionId();
			if ( StringUtils.isNotEmpty( staffPositionId ) ) {
				GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
				reqEvt.setStaffPositionId(staffPositionId);
				reqEvt.setOfficerNameNeeded(true);
				LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
				//add logic to find userprofile to get positions phone and email
				if(staffPosRespEvt!=null)
				{
					if ( StringUtils.isNotEmpty( staffPosRespEvt.getOfficerName() ) ) {
						form.getCurrentItinerary().setFvOfficer(staffPosRespEvt.getOfficerName());
					} else {
						form.getCurrentItinerary().setFvOfficer("NO OFFICER ASSIGNED");						
					}
				}
			}
			return "createItinerary";
		} else {
			form.setSecondaryActivityFlow("");
			// forward to caseload search page since there is an existing
			// itinerary for the day
			form.getCurrentItinerary().load(itinerary);

			// get the list of field visit events for that day too...
			GetCSFVEventsEvent getCSFVEvents = (GetCSFVEventsEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.GETCSFVEVENTS);
			getCSFVEvents.setFvIteneraryId(itinerary.getFvItineraryId());

			// At this point, we want to search for all the events attached to
			// the itineraryId, therefore belonging to this specific officer
			getCSFVEvents.setCurrentContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);

			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
					.getContext())) {
				if (myHeaderForm.getOfficerPositionId() != null) {
					getCSFVEvents.setPositionId(myHeaderForm.getOfficerPositionId());

				}
			} else {
				getCSFVEvents.setPositionId(form.getPositionId());
			}		

			response = postRequestEvent(getCSFVEvents);
			List events = (List) MessageUtil.compositeToCollection(response,
					CSFieldVisitResponseEvent.class);

			form.setEventsList(events);

			if (UIConstants.CREATE.equals(form.getActivityFlow())) {
				if (PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form
						.getContext())) {
					return "caseloadSearch";
				} else {
					return "superviseeSuccess";
				}

			} else {
				return UIConstants.UPDATE_SUCCESS;
			}
		}
	}

}
