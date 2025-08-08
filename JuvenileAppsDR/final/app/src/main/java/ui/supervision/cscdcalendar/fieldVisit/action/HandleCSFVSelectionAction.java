/*
 * Created on Feb 8, 2008
 *
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.cscdcalendar.CSFVBean;
import ui.supervision.cscdcalendar.cscdCalendarUIUtil;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.manageassociate.UIManageAssociateHelper;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja This action will handle operations performed to a selected
 *         field visit.
 */
public class HandleCSFVSelectionAction extends JIMSBaseAction {

	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		String selectedFVEventId = form.getSelectedFVEventId();

		if (selectedFVEventId != null && selectedFVEventId.length() > 0) {
			CSFVBean fv = null;
			if (PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) {
				fv = loadSelectedEvent(form.getEventsList(), selectedFVEventId, form.getAgencyId());
				form.setCurrentFieldVisit(fv);
			} else {
				fv = loadSelectedEvent(form.getEventsForSupervisee(), selectedFVEventId, form.getAgencyId());
				form.setCurrentFieldVisit(fv);
			}
			
			String itineraryId = null;
			if ( form.getEventsForSupervisee() != null ) {
				for (Iterator eventsIter = form.getEventsForSupervisee().iterator(); eventsIter.hasNext();) {
					CSFieldVisitResponseEvent fvRE = (CSFieldVisitResponseEvent) eventsIter.next();
					if (fvRE.getFvEventId().equals(selectedFVEventId)) {
						itineraryId = fvRE.getFvIteneraryId();
					}
				}
			}else if ( StringUtils.isNotEmpty(form.getCurrentItinerary().getItineraryId()) ) {
				itineraryId = form.getCurrentItinerary().getItineraryId();
			}else if ( StringUtils.isNotEmpty(form.getCurrentItineraryId()) ) {
				itineraryId = form.getCurrentItineraryId();
			}
			
			// load up itinerary and fv events
			GetCSFVItineraryDetailsEvent getCSFVItinerary = (GetCSFVItineraryDetailsEvent) EventFactory.getInstance(CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);
			getCSFVItinerary.setItineraryId(itineraryId);

			CompositeResponse response = MessageUtil.postRequest(getCSFVItinerary);
			CSFVItineraryResponseEvent itineraryRE = (CSFVItineraryResponseEvent) MessageUtil.filterComposite(response, CSFVItineraryResponseEvent.class);

			if (itineraryRE != null) {
				form.getCurrentItinerary().load(itineraryRE);
				form.getCurrentItinerary().setAgencyId(form.getAgencyId());

				List events = cscdCalendarUIUtil.getEventsForItinerary(itineraryRE.getFvItineraryId(), itineraryRE.getPositionId(), form.getEventDate());
				if (events.size() > 0) {
					form.setEventsList(events);
				} else {
					// load up itinerary and fv events
					form.setEventDate(form.getCurrentEventDate());
					form.setEventsList(form.getCurrentEventsList());
					fv = loadSelectedEvent(form.getEventsList(), selectedFVEventId, form.getAgencyId());
					form.setCurrentFieldVisit(fv);
				}
			}

			// load header form information to be displayed on the top header
			// tile
			SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
			myHeaderForm.setSuperviseeId(fv.getSuperviseeId());
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		} else {
			// TODO: Error here
		}

		form.setActivityFlow(UIConstants.VIEW);
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);

	}

	private CSFVBean loadSelectedEvent(List events, String selectedFVEventId,
			String agencyId) {
		if ( events != null ) {
			for (Iterator iter = events.iterator(); iter.hasNext();) {
				CSFieldVisitResponseEvent event = (CSFieldVisitResponseEvent) iter.next();
				if (selectedFVEventId.equals(event.getFvEventId())) {
					CSFVBean fv = new CSFVBean(agencyId);
					fv.load(event);
					return fv;

				}
			}
		}
		return null;
	}

	private void loadHeaderInfo(ActionMapping aMapping,
			HttpServletRequest aRequest, String superviseeId)
			throws GeneralFeedbackMessageException {

		// load header form information to be displayed on the top header tile
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
	}

	public ActionForward update(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		String selectedFVEventId = form.getSelectedFVEventId();

		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {

			String itineraryId = null;
			for (Iterator eventsIter = form.getEventsForSupervisee().iterator(); eventsIter.hasNext();) {
				CSFieldVisitResponseEvent fvRE = (CSFieldVisitResponseEvent) eventsIter.next();
				if (fvRE.getFvEventId().equals(selectedFVEventId)) {
					itineraryId = fvRE.getFvIteneraryId();
				}
			}

			// load up itinerary and fv events
			GetCSFVItineraryDetailsEvent getCSFVItinerary = (GetCSFVItineraryDetailsEvent) EventFactory.getInstance(CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);
			getCSFVItinerary.setItineraryId(itineraryId);

			CompositeResponse response = MessageUtil.postRequest(getCSFVItinerary);
			CSFVItineraryResponseEvent itineraryRE = (CSFVItineraryResponseEvent) MessageUtil.filterComposite(response, CSFVItineraryResponseEvent.class);

			if (itineraryRE != null) {
				form.getCurrentItinerary().load(itineraryRE);
				form.getCurrentItinerary().setAgencyId(form.getAgencyId());

				List events = cscdCalendarUIUtil.getEventsForItinerary(itineraryRE.getFvItineraryId(), itineraryRE.getPositionId(), form.getEventDate());

				form.setEventsList(events);

			}
		}

		if (selectedFVEventId != null && selectedFVEventId.length() > 0) {
			List events = form.getEventsList();
			if (events != null && events.size() > 0) {
				form.setEventsList(events);
			} else {
				// load up itinerary and fv events
				form.setEventDate(form.getCurrentEventDate());
				form.setEventsList(form.getCurrentEventsList());
			}
			CSFVBean fv = loadSelectedEvent(form.getEventsList(), selectedFVEventId, form.getAgencyId());
			form.setCurrentFieldVisit(fv);
			loadHeaderInfo(aMapping, aRequest, fv.getSuperviseeId());

			if (fv.getStatusCd().equals("C") && !fv.getOutcomeCd().equals("RE")) {
				String superviseeId = form.getCurrentFieldVisit().getSuperviseeId();
				if (superviseeId != null && superviseeId.length() > 0) {
					form.getCurrentFieldVisit().setSuperviseeAssociates((List) UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(superviseeId));

				}
			} else {
				// TODO: Error here
			}
		}

		form.setActivityFlow(UIConstants.UPDATE);

		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}

	public ActionForward enterResults(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		String selectedFVEventId = form.getSelectedFVEventId();

		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {

			String itineraryId = null;
			for (Iterator eventsIter = form.getEventsForSupervisee().iterator(); eventsIter.hasNext();) {
				CSFieldVisitResponseEvent fvRE = (CSFieldVisitResponseEvent) eventsIter.next();
				if (fvRE.getFvEventId().equals(selectedFVEventId)) {
					itineraryId = fvRE.getFvIteneraryId();
				}
			}

			// load up itinerary and fv events
			GetCSFVItineraryDetailsEvent getCSFVItinerary = (GetCSFVItineraryDetailsEvent) EventFactory.getInstance(CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);
			getCSFVItinerary.setItineraryId(itineraryId);

			CompositeResponse response = MessageUtil.postRequest(getCSFVItinerary);
			CSFVItineraryResponseEvent itineraryRE = (CSFVItineraryResponseEvent) MessageUtil.filterComposite(response, CSFVItineraryResponseEvent.class);

			if (itineraryRE != null) {
				form.getCurrentItinerary().load(itineraryRE);
				form.getCurrentItinerary().setAgencyId(form.getAgencyId());

				List events = cscdCalendarUIUtil.getEventsForItinerary(itineraryRE.getFvItineraryId(), itineraryRE.getPositionId(), form.getEventDate());

				form.setEventsList(events);

			}
		}
		
		if (selectedFVEventId != null && selectedFVEventId.length() > 0) {
			List events = form.getEventsList();
			if (events != null && events.size() > 0) {
				form.setEventsList(events);
			} else {
				// load up itinerary and fv events
				form.setEventDate(form.getCurrentEventDate());
				form.setEventsList(form.getCurrentEventsList());
			}
			CSFVBean fv = null;
			if (PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) {
				fv = loadSelectedEvent(form.getEventsList(), selectedFVEventId, form.getAgencyId());
			} else {
				fv = loadSelectedEvent(form.getEventsForSupervisee(), selectedFVEventId, form.getAgencyId());
			}
			form.setCurrentFieldVisit(fv);
			loadHeaderInfo(aMapping, aRequest, fv.getSuperviseeId());
		} else {
			// TODO: Error here
		}

		form.setActivityFlow("enterResults");
		return aMapping.findForward("enterResultsSuccess");
	}

	public ActionForward reschedule(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		String selectedFVEventId = form.getSelectedFVEventId();

		if (selectedFVEventId != null && selectedFVEventId.length() > 0) {
			CSFVBean fv = null;
			if (PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) {
				if (form.getEventsList() != null) {
					fv = loadSelectedEvent(form.getEventsList(), selectedFVEventId, form.getAgencyId());
					form.setCurrentEventsList(form.getEventsList());
					form.setCurrentEventDate(form.getEventDate());
					form.setCurrentItineraryId(form.getCurrentItinerary().getItineraryId());
				} else {
					// load up itinerary and fv events
					form.setEventDate(form.getCurrentEventDate());
					fv = loadSelectedEvent(form.getCurrentEventsList(), selectedFVEventId, form.getAgencyId());
				}
			} else {
				fv = loadSelectedEvent(form.getEventsForSupervisee(), selectedFVEventId, form.getAgencyId());
			}
			fv.setRescheduleFVEventId(fv.getFvEventId());
			form.setCurrentFieldVisit(fv);
			loadHeaderInfo(aMapping, aRequest, fv.getSuperviseeId());

		} else {
			// TODO: Error here
		}

		form.setActivityFlow("reschedule");
		return aMapping.findForward("rescheduleSuccess");
	}

	public ActionForward delete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarFVForm form = (CSCalendarFVForm) aForm;

		form.setActivityFlow("delete");
		aRequest.setAttribute("status", "summary");
		return aMapping.findForward("deleteSuccess");
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view", "view");
		keyMap.put("button.update", "update");
		keyMap.put("button.enterResults", "enterResults");
		keyMap.put("button.reschedule", "reschedule");
		keyMap.put("button.delete", "delete");
	}

}
