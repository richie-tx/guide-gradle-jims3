/*
 *
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.cscdcalendar.CSFVItineraryBean;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author This action will handle operations performed to schedule next field
 *         visit.
 */
public class HandleScheduleNextFVAction extends JIMSBaseAction {

	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm) getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		String superviseeId = null;
		String forward = "";
		// check case is not closed
		if (myHeaderForm.getOfficerPositionId() != null) {
			if (form.getCurrentFieldVisit() != null && form.getCurrentFieldVisit().getSuperviseeId() != null) {
				superviseeId = form.getCurrentFieldVisit().getSuperviseeId();
				myHeaderForm.setSuperviseeId(form.getCurrentFieldVisit().getSuperviseeId());
				UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			}
			CSCalendarDisplayForm csCalendarDisplayForm = 
				(CSCalendarDisplayForm) getSessionForm(aMapping, aRequest, "csCalendarDisplayForm", true);
			csCalendarDisplayForm.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE);
			csCalendarDisplayForm.setSelectedEventTypeCd(PDCodeTableConstants.CS_FIELD_VISIT_CATEGORY);
			if (superviseeId != null) {
				csCalendarDisplayForm.setSuperviseeId(superviseeId);
				form.setSuperviseeId(superviseeId);
			}
			csCalendarDisplayForm.setPositionId(myHeaderForm.getOfficerPositionId());
			
			form.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE);
			form.setActivityFlow(UIConstants.CREATE);
			form.getCurrentFieldVisit().setComments("");
			forward = getCreateForwardPath(form, myHeaderForm);
		}
		return aMapping.findForward(forward);
	}

	private String getCreateForwardPath(CSCalendarFVForm form,
			SuperviseeHeaderForm myHeaderForm) {

		// Clearing any previously queried eventsList so that it does
		// not show up to look that they belong to the new itinerary

		// form.clear();
		String returnString = ""; 
		form.getCurrentItinerary().clear();
		form.getCurrentFieldVisit().reset();
		form.setEventsList(null);
		form.setEventsForSupervisee(null);

		// Check if Itinerary exists, if it doesnt, then forward to
		// create itinerary page.
		GetCSFVItineraryDetailsEvent getItineraryEvent = (GetCSFVItineraryDetailsEvent) EventFactory.getInstance(CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);

		getItineraryEvent.setItineraryDate(form.getEventDate());
		getItineraryEvent.setPositionId(myHeaderForm.getOfficerPositionId());
				
		CompositeResponse response = postRequestEvent(getItineraryEvent);

		CSFVItineraryResponseEvent itinerary = (CSFVItineraryResponseEvent) MessageUtil
				.filterComposite(response, CSFVItineraryResponseEvent.class);

		if (itinerary == null) {

			form.setSecondaryActivityFlow("createItinerary");
			form.setCurrentItinerary(new CSFVItineraryBean(form.getAgencyId()));
			form.getCurrentItinerary().setEventDate(form.getEventDate());
			form.setItineraryCreate(UIConstants.YES);
			
			if (myHeaderForm.getOfficerPositionId() != null) {
				form.getCurrentItinerary().setPositionId(myHeaderForm.getOfficerPositionId());
			}
			
			returnString = "createItinerary";

		}

		else {
			form.setSecondaryActivityFlow("");
			
			form.getCurrentItinerary().load(itinerary);

			// get the list of field visit events for that day too...
			GetCSFVEventsEvent getCSFVEvents = (GetCSFVEventsEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.GETCSFVEVENTS);
			getCSFVEvents.setFvIteneraryId(itinerary.getFvItineraryId());

			// At this point, we want to search for all the events attached to
			// the itineraryId, therefore belonging to this specific officer
			getCSFVEvents.setCurrentContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);

			
			if (myHeaderForm.getOfficerPositionId() != null) {
				getCSFVEvents.setPositionId(myHeaderForm.getOfficerPositionId());

			}
			
			response = postRequestEvent(getCSFVEvents);
			List events = (List) MessageUtil.compositeToCollection(response,
					CSFieldVisitResponseEvent.class);

			form.setEventsList(events);

			returnString = UIConstants.SUCCESS;

		}
		return returnString;
	}

	protected void addButtonMapping(Map keyMap) {

		keyMap.put("button.next", "next");
	}

}
