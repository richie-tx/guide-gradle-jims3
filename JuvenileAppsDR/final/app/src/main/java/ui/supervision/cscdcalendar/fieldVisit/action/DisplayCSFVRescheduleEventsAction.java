/*
 * Created on Mar 5, 2008
 *
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
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
import ui.supervision.cscdcalendar.CSFVItineraryBean;
import ui.supervision.cscdcalendar.SuperviseeBean;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * 
 *
 */
public class DisplayCSFVRescheduleEventsAction extends JIMSBaseAction {

	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		form.setActivityFlow("rescheduleView");
	

		// Filter the list based on Scheduled Outcome
		List eventsList = form.getEventsList();
		List filteredList = new ArrayList();
		if (eventsList != null) {
			for (Iterator eventIter = eventsList.iterator(); eventIter
					.hasNext();) {
				CSFieldVisitResponseEvent event = (CSFieldVisitResponseEvent) eventIter.next();
				if (event.getOutcomeCd() != null && event.getOutcomeCd().equalsIgnoreCase("SC")) {
					filteredList.add(event);
				}
			}

			form.setFilteredList(filteredList);
		}

		return aMapping.findForward(UIConstants.SUCCESS);

	}

	public ActionForward reschedule(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		form.setActivityFlow("reschedule");
		

		String[] fvEventIds = aRequest.getParameterValues("selectedFVEventId");

		List filteredeventsList = new ArrayList();

		if (fvEventIds != null && fvEventIds.length > 0) {
			for (String fvEventId : fvEventIds) {

				for (int i = 0; i < form.getEventsList().size(); i++) {
					CSFieldVisitResponseEvent resp = (CSFieldVisitResponseEvent) form.getEventsList().get(i);
					if (resp.getFvEventId().equalsIgnoreCase(fvEventId)) {
						filteredeventsList.add(resp);
						break;
					}
				}
			}
		}
		if (filteredeventsList != null) {
			form.setFilteredList(filteredeventsList);
		}

		return aMapping.findForward(UIConstants.SUCCESS);

	}
	
	
	public ActionForward reschedulenext(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {

	
		
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		form.setActivityFlow("reschedulemultipleFV");
		String forward = getCreateForwardPath(form, myHeaderForm);
		return aMapping.findForward(forward);	
	}
	
	private String getCreateForwardPath(CSCalendarFVForm form,
			SuperviseeHeaderForm myHeaderForm) {

		
		// Clearing any previously querried eventsList so that it does
		// not show up to look that they belong to the new itinerary

		form.clear();

		// Check if Itinerary exists, if it doesnt, then forward to
		// create itinerary page.
		GetCSFVItineraryDetailsEvent getItineraryEvent = (GetCSFVItineraryDetailsEvent) EventFactory
				.getInstance(CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);

		getItineraryEvent.setItineraryDate(form.getEventDate());
		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
				.getContext())) {
			if (myHeaderForm.getOfficerPositionId() != null) {
				getItineraryEvent.setPositionId(myHeaderForm.getOfficerPositionId());

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

		
			return "createItinerary";		
			
		}
		
		
		 else {
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
			// getCSFVEvents.setPositionId(form.getPositionId());

			response = postRequestEvent(getCSFVEvents);
			List events = (List) MessageUtil.compositeToCollection(response,
					CSFieldVisitResponseEvent.class);

			form.setEventsList(events);

		
				return UIConstants.SUCCESS;
			
		}
	}
	
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {

	
		
		
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
		
		form.setActivityFlow("reschedulemultipleFV");	


	return aMapping.findForward(UIConstants.SUCCESS);

	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view", "view");
		keyMap.put("button.reschedule", "reschedule");
		keyMap.put("button.next", "reschedulenext");
		keyMap.put("button.link", "link");
	}

}
