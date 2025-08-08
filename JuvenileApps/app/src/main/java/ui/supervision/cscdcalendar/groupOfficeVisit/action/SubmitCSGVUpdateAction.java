/*
 * Created on Mar 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.groupOfficeVisit.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.NameBean;
import messaging.cscdcalendar.SaveCSGroupOfficeVisitEvent;
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
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
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.CSOVBean;
import ui.supervision.cscdcalendar.SuperviseeBean;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

/**
 * @author awidjaja
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCSGVUpdateAction extends JIMSBaseAction {

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarOVForm form = (CSCalendarOVForm) aForm;

		SaveCSGroupOfficeVisitEvent saveGroupOfficeVisit = (SaveCSGroupOfficeVisitEvent) EventFactory
				.getInstance(CSEventControllerServiceNames.SAVECSGROUPOFFICEVISIT);

		if (UIConstants.CREATE.equals(form.getActivityFlow())) {
			saveGroupOfficeVisit.setCreate(true);
		} else if (UIConstants.UPDATE.equals(form.getActivityFlow())
				|| "updateResults".equals(form.getActivityFlow())) {
			saveGroupOfficeVisit.setUpdate(true);
			saveGroupOfficeVisit.setEventIds(form.getSelectedEventIdList());
		} else if ("addAttendees".equals(form.getActivityFlow())) {
			saveGroupOfficeVisit.setAddAttendees(true);
			saveGroupOfficeVisit.setEventIds(form.getSelectedEventIdList());

		} else if ("enterResults".equals(form.getActivityFlow())) {
			saveGroupOfficeVisit.setResults(true);
			AssignSuperviseeService helper = AssignSuperviseeService
					.getInstance();
			CSCDSupervisionStaffResponseEvent staff = helper.getCSCDStaff();
			saveGroupOfficeVisit
					.setResultPositionId(staff.getStaffPositionId());
			saveGroupOfficeVisit.setResultUserId(staff.getAssignedLogonId());
			saveGroupOfficeVisit.setEventIds(form.getSelectedEventIdList());
		} else if (UIConstants.DELETE.equals(form.getActivityFlow())) {
			saveGroupOfficeVisit.setDelete(true);
			List selectedEventIdList = new ArrayList();
			selectedEventIdList.add(form.getCurrentOfficeVisit().getEventId());
			if(form.getCurrentOfficeVisit().getStatus()!= null && (form.getCurrentOfficeVisit().getStatus()).equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)){
				aRequest.setAttribute("state", UIConstants.SUCCESS);	
			}
			else{
				aRequest.setAttribute("state", UIConstants.CONFIRM);	
			}
			saveGroupOfficeVisit.setEventIds(selectedEventIdList);
		} else if ("reschedule".equals(form.getActivityFlow())) {
			saveGroupOfficeVisit.setReschedule(true);
			saveGroupOfficeVisit.setEventIds(form.getSelectedEventIdList());
			CSOVBean currentOfficeVisit = form.getCurrentOfficeVisit();
			saveGroupOfficeVisit.setRescheduleReason(currentOfficeVisit.getRescheduleReason());
		}

		if ("addAttendees".equals(form.getActivityFlow())) {
			// For add attendees, only pass the newly attended superviseeId
			List superviseeIds = new ArrayList();
			for (Iterator<SuperviseeBean> superviseeIter = form
					.getNewSuperviseeList().iterator(); superviseeIter
					.hasNext();) {
				superviseeIds.add(superviseeIter.next().getSpn());
			}
			saveGroupOfficeVisit.setSuperviseeIds(superviseeIds);
		} else {
			List superviseeIds = new ArrayList();
			for (Iterator<SuperviseeBean> superviseeIter = form
					.getSuperviseeList().iterator(); superviseeIter.hasNext();) {
				superviseeIds.add(superviseeIter.next().getSpn());
			}
			saveGroupOfficeVisit.setSuperviseeIds(superviseeIds);
		}

		CSOVBean officeVisit = form.getCurrentOfficeVisit();

		saveGroupOfficeVisit.setEventDate(officeVisit.getEventDate());
		saveGroupOfficeVisit.setEventName(officeVisit.getEventName());

		if (!StringUtil.isEmpty(officeVisit.getStartTime())) {
			saveGroupOfficeVisit.setStartTime(officeVisit.getStartTime() + " " + officeVisit.getAMPMId1());
		} else {
			saveGroupOfficeVisit.setStartTime(officeVisit.getStartTime());
		}

		if (!StringUtil.isEmpty(officeVisit.getEndTime())) {
			saveGroupOfficeVisit.setEndTime(officeVisit.getEndTime() + " " + officeVisit.getAMPMId2());
		} else {
			saveGroupOfficeVisit.setEndTime(officeVisit.getEndTime());
		}

		saveGroupOfficeVisit.setPurpose(officeVisit.getPurpose());

		// saveGroupOfficeVisit.setPositionId(officeVisit.getPositionId());
		saveGroupOfficeVisit.setPositionId(form.getPositionId());
		saveGroupOfficeVisit.setEventType(officeVisit.getEventTypeCd());

		saveGroupOfficeVisit.setOutcome(officeVisit.getOutcomeCd());
		saveGroupOfficeVisit.setNarrative(officeVisit.getNarrative());

		CompositeResponse response = postRequestEvent(saveGroupOfficeVisit);

		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
				.filterComposite(response, ErrorResponseEvent.class);
		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					error.getMessage());
			return aMapping.findForward(UIConstants.FAILURE);
		}
        if(!UIConstants.DELETE.equals(form.getActivityFlow())){
		aRequest.setAttribute("state", "confirm");
	    }  
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward enterResults(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		CSCalendarOVForm form = (CSCalendarOVForm) aForm;
		form.setActivityFlow("enterResults");

		String selectedEventId = form.getSelectedEventId();

		List superviseeIdList = new ArrayList(); // this list will be converted
													// to String[] later
		List selectedEventIdList = new ArrayList();

		if (selectedEventId != null) {
			selectedEventIdList.add(selectedEventId);
		}
		CSOfficeVisitResponseEvent ov = null;

		// Reason: In supervisee context, group office visit and office visits
		// are
		// clumped together as one list. However, in officer context, they 2
		// seperate list. Therefore, we need to look for the correct collection
		// to find the
		// event.
		if (PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) {
			ov = searchForGroupOfficeVisitEvent(form.getGroupOfficeVisits(),
					selectedEventId);
		} else {
			ov = searchForOfficeVisitEvent(form.getOfficeVisits(),
					selectedEventId);
		}

		if (ov != null) {
			form.getCurrentOfficeVisit().load(ov);
			SuperviseeBean supervisee = new SuperviseeBean();
			supervisee.setSpn(ov.getSuperviseeId());
			NameBean superviseeName = new NameBean();
			superviseeName.setFirstName(ov.getPartyEvent().getFirstName());
			superviseeName.setLastName(ov.getPartyEvent().getLastName());
			superviseeName.setMiddleName(ov.getPartyEvent().getMiddleName());
			supervisee.setName(superviseeName);
			List superviseeList = new ArrayList();
			superviseeList.add(supervisee);
			form.setSuperviseeList(superviseeList);
			superviseeIdList.add(ov.getSuperviseeId());
		}

		form.setSelectedEventIdList(selectedEventIdList);

		return aMapping.findForward("enterResultsSuccess");

	}

	private CSOfficeVisitResponseEvent searchForGroupOfficeVisitEvent(
			List groupOfficeVisits, String eventId) {
		// search for the event, and extract the supervisee from the selected
		// events into a list
		for (Iterator groupOVByTimeIter = groupOfficeVisits.iterator(); groupOVByTimeIter.hasNext();) {
			CSGroupOfficeVisitResponseEvent gov = (CSGroupOfficeVisitResponseEvent) groupOVByTimeIter.next();
			if (gov.getOfficeVisits() != null) {
				CSOfficeVisitResponseEvent ov = searchForOfficeVisitEvent(
						(List) gov.getOfficeVisits(), eventId);
				if (ov != null) {
					return ov;
				}
			}
		}
		return null;
	}

	private CSOfficeVisitResponseEvent searchForOfficeVisitEvent(
			List officeVisits, String eventId) {
		for (Iterator officeVisitIter = officeVisits.iterator(); officeVisitIter.hasNext();) {
			CSOfficeVisitResponseEvent ov = (CSOfficeVisitResponseEvent) officeVisitIter.next();
			if (ov.getEventId().equals(eventId)) {
				return ov;
			}
		}
		return null;
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.enterResults", "enterResults");

	}

}
