/*
 * Created on Apr 2, 2008
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
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.cscdcalendar.SuperviseeBean;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleCSGVSelectionAction extends JIMSBaseAction {

	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		String selectedEventId = form.getSelectedEventId();
		
		List superviseeIdList = new ArrayList(); //this list will be converted to String[] later
		
		CSOfficeVisitResponseEvent ov = null;
		
		//Reason: In supervisee context, group office visit and office visits are 
		//clumped together as one list. However, in officer context, they 2 seperate list.
		//Therefore, we need to look for the correct collection to find the event.
		if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) {
			ov = searchForGroupOfficeVisitEvent(form.getGroupOfficeVisits(), selectedEventId);
		} else {
			ov = searchForOfficeVisitEvent(form.getOfficeVisits(), selectedEventId);			
		}
		
		if(ov != null) {
			form.getCurrentOfficeVisit().load(ov);
			SuperviseeBean supervisee = new SuperviseeBean();
			supervisee.setSpn(ov.getSuperviseeId());
			NameBean superviseeName = new NameBean();
			superviseeName.setFirstName(ov.getPartyEvent().getFirstName());
			superviseeName.setLastName(ov.getPartyEvent().getLastName());
			superviseeName.setMiddleName(ov.getPartyEvent().getMiddleName());
			supervisee.setName(superviseeName);
			StringBuffer defendantName = new StringBuffer();
			defendantName.append(ov.getPartyEvent().getLastName());
			defendantName.append(",  ");
			defendantName.append(ov.getPartyEvent().getFirstName());
			defendantName.append(" ");
			defendantName.append(ov.getPartyEvent().getMiddleName());
			supervisee.setDefendantName(defendantName.toString());
			List superviseeList = new ArrayList();
			superviseeList.add(supervisee);
			form.setSuperviseeList(superviseeList);
			superviseeIdList.add(ov.getSuperviseeId());
		}
		
		form.setActivityFlow(UIConstants.VIEW);
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
		
	}

	private CSOfficeVisitResponseEvent searchForGroupOfficeVisitEvent(List groupOfficeVisits, String eventId) {
		//search for the event, and extract the supervisee from the selected events into a list
		for(Iterator groupOVByTimeIter = groupOfficeVisits.iterator();
					groupOVByTimeIter.hasNext();) {
			CSGroupOfficeVisitResponseEvent gov = 
				(CSGroupOfficeVisitResponseEvent)groupOVByTimeIter.next();
			if(gov.getOfficeVisits() != null) {
				CSOfficeVisitResponseEvent ov =
					searchForOfficeVisitEvent((List)gov.getOfficeVisits(), eventId);
				if(ov != null) {
					return ov;
				}
			}
		}
		return null;
	}
	
	private CSOfficeVisitResponseEvent searchForOfficeVisitEvent(List officeVisits, String eventId) {
		for(Iterator officeVisitIter = officeVisits.iterator(); officeVisitIter.hasNext();){
			CSOfficeVisitResponseEvent ov =
				(CSOfficeVisitResponseEvent)officeVisitIter.next();
			if(ov.getEventId().equals(eventId)) {
				return ov;
			}
		}		
		return null;
	}
	
	public ActionForward enterResults(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setActivityFlow("enterResults");
		
		String[] selectedEventIds = form.getSelectedEventIds();
		if(selectedEventIds == null || selectedEventIds.length < 0) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Please select a Group Office Visit to proceed");
   			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		String selectedGroupOVName = form.getSelectedGroupOVName();
		
		List selectedEvents = new ArrayList();
		List selectedEventIdList = new ArrayList();
		List superviseeList = new ArrayList();
		List superviseeIdList = new ArrayList(); //this list will be converted to String[] later
		
		if(selectedEventIds != null) {
			for(int i = 0;i < selectedEventIds.length;i++) {
				for(Iterator govByTimeIter = form.getGroupOfficeVisits().iterator();
					govByTimeIter.hasNext();) {
					CSGroupOfficeVisitResponseEvent govByTime = 
						(CSGroupOfficeVisitResponseEvent)govByTimeIter.next();
					if(govByTime.getOfficeVisits()!=null){
						for(Iterator govIter = govByTime.getOfficeVisits().iterator();
							govIter.hasNext();) {
							CSOfficeVisitResponseEvent ov = 
								(CSOfficeVisitResponseEvent)govIter.next();
							if(ov.getEventId().equals(selectedEventIds[i]) &&
									ov.getEventName().equals(selectedGroupOVName)) {
								selectedEvents.add(ov);
								selectedEventIdList.add(ov.getEventId());
								SuperviseeBean supervisee = new SuperviseeBean();
								supervisee.setSpn(ov.getSuperviseeId());
								NameBean superviseeName = new NameBean();
								superviseeName.setFirstName(ov.getPartyEvent().getFirstName());
								superviseeName.setLastName(ov.getPartyEvent().getLastName());
								superviseeName.setMiddleName(ov.getPartyEvent().getMiddleName());
								supervisee.setName(superviseeName);
								superviseeList.add(supervisee);
								superviseeIdList.add(ov.getSuperviseeId());
								break;
							}
						}
					}
				}
			}
			
		} else {
			//TODO:Enter supervisee Id
		}
		
		if(selectedEventIdList.size() < 1) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Please select a Group Office Visit to proceed");
   			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		form.setSuperviseeList(superviseeList);
		form.setSelectedEventIdList(selectedEventIdList);
		
		//load one of the events as they are essentially the same 
		form.getCurrentOfficeVisit().load((CSOfficeVisitResponseEvent)selectedEvents.iterator().next());
		
		return aMapping.findForward("enterResultsSuccess");
	}
	
	public ActionForward updateSelectedResults(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		String[] selectedEventIds = {form.getSelectedEventId()};
		
		form.setSelectedEventIds(selectedEventIds);
		ActionForward forward  = enterResults(aMapping, aForm, aRequest, aResponse);
		form.setActivityFlow("updateResults");
		
		return forward;
	}
	public ActionForward addAttendees(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		String selectedGroupOVName = form.getSelectedGroupOVName();
		
		List selectedEvents = new ArrayList();
		List selectedEventIdList = new ArrayList();
		List superviseeList = new ArrayList();
		
		for(Iterator govByTimeIter = form.getGroupOfficeVisits().iterator();
				govByTimeIter.hasNext();) {
			CSGroupOfficeVisitResponseEvent govByTime = 
				(CSGroupOfficeVisitResponseEvent)govByTimeIter.next();
			if(govByTime.getEventName().equals(selectedGroupOVName)) {
				if(govByTime.getOfficeVisits()!=null){
					for(Iterator govIter = govByTime.getOfficeVisits().iterator();
						govIter.hasNext();) {
						CSOfficeVisitResponseEvent ov = 
							(CSOfficeVisitResponseEvent)govIter.next();
							
						selectedEvents.add(ov);
						selectedEventIdList.add(ov.getEventId());
						SuperviseeBean supervisee = new SuperviseeBean();
						supervisee.setSpn(ov.getSuperviseeId());
						NameBean superviseeName = new NameBean();
						superviseeName.setFirstName(ov.getPartyEvent().getFirstName());
						superviseeName.setLastName(ov.getPartyEvent().getLastName());
						superviseeName.setMiddleName(ov.getPartyEvent().getMiddleName());
						supervisee.setName(superviseeName);
						superviseeList.add(supervisee);
					}
				}
				break;
			}
		}
		
		form.setSuperviseeList(superviseeList);
		form.setSelectedEventIdList(selectedEventIdList);
		
		//load one of the events as they are essentially the same 
		form.getCurrentOfficeVisit().load((CSOfficeVisitResponseEvent)selectedEvents.iterator().next());
		
		form.setActivityFlow("addAttendees");
		
		//get CSCalendarDisplay from session to set activityFlow = addAttendees
		CSCalendarDisplayForm displayForm = 
			(CSCalendarDisplayForm)getSessionForm(
					aMapping, aRequest, 
					"csCalendarDisplayForm", true);
		displayForm.setActivityFlow("addAttendees");
		displayForm.setSelectedEventTypeCd("GV");
		displayForm.setQuadrantSearch("");
		
		return aMapping.findForward("addAttendeesSuccess");
	
	}
	
	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		String selectedGroupOVName = form.getSelectedGroupOVName();
		form.setActivityFlow(UIConstants.UPDATE);
		form.setAgencyId(SecurityUIHelper.getUserAgencyId());
		
		List selectedEvents = new ArrayList();
		List selectedEventIdList = new ArrayList();
		List superviseeList = new ArrayList();
		List superviseeIdList = new ArrayList(); //this list will be converted to String[] later
		

		for(Iterator govByTimeIter = form.getGroupOfficeVisits().iterator();
				govByTimeIter.hasNext();) {
			CSGroupOfficeVisitResponseEvent govByTime = 
				(CSGroupOfficeVisitResponseEvent)govByTimeIter.next();
			if(govByTime.getEventName().equals(selectedGroupOVName)) {
				if(govByTime.getOfficeVisits()!=null){
					for(Iterator govIter = govByTime.getOfficeVisits().iterator();
						govIter.hasNext();) {
						CSOfficeVisitResponseEvent ov = 
							(CSOfficeVisitResponseEvent)govIter.next();
							
						selectedEvents.add(ov);
						selectedEventIdList.add(ov.getEventId());
						SuperviseeBean supervisee = new SuperviseeBean();
						supervisee.setSpn(ov.getSuperviseeId());
						NameBean superviseeName = new NameBean();
						superviseeName.setFirstName(ov.getPartyEvent().getFirstName());
						superviseeName.setLastName(ov.getPartyEvent().getLastName());
						superviseeName.setMiddleName(ov.getPartyEvent().getMiddleName());
						supervisee.setName(superviseeName);
						superviseeList.add(supervisee);
						superviseeIdList.add(ov.getSuperviseeId());		
					}
				}
				break;
			}
		}
		
		form.setSuperviseeList(superviseeList); //for display purpose
		form.setSelectedEventIdList(selectedEventIdList);
				
		//load one of the events as they are essentially the same 
		form.getCurrentOfficeVisit().load((CSOfficeVisitResponseEvent)selectedEvents.iterator().next());
		
		
		return aMapping.findForward("updateSuccess");
	
	}
	
	public ActionForward delete(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setActivityFlow(UIConstants.DELETE);
		aRequest.setAttribute("state","summary");
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}
	
	public ActionForward reschedule(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
				
		ActionForward forward = update(aMapping, aForm, aRequest, aResponse);
		
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setActivityFlow("reschedule");
		form.setAgencyId(SecurityUIHelper.getUserAgencyId());
				
		String[] selectedEventIds = form.getSelectedEventIds();
		if(selectedEventIds == null || selectedEventIds.length < 0) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Please select a Group Office Visit to proceed");
   			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		String selectedGroupOVName = form.getSelectedGroupOVName();
		
		List selectedEvents = new ArrayList();
		List selectedEventIdList = new ArrayList();
		List superviseeList = new ArrayList();
		List superviseeIdList = new ArrayList(); //this list will be converted to String[] later
		
		if(selectedEventIds != null) {
			for(int i = 0;i < selectedEventIds.length;i++) {
				for(Iterator govByTimeIter = form.getGroupOfficeVisits().iterator();
					govByTimeIter.hasNext();) {
					CSGroupOfficeVisitResponseEvent govByTime = 
						(CSGroupOfficeVisitResponseEvent)govByTimeIter.next();
					if(govByTime.getOfficeVisits()!=null){
						for(Iterator govIter = govByTime.getOfficeVisits().iterator();
							govIter.hasNext();) {
							CSOfficeVisitResponseEvent ov = 
								(CSOfficeVisitResponseEvent)govIter.next();
							if(ov.getEventId().equals(selectedEventIds[i]) &&
									ov.getEventName().equals(selectedGroupOVName)) {
								selectedEvents.add(ov);
								selectedEventIdList.add(ov.getEventId());
								SuperviseeBean supervisee = new SuperviseeBean();
								supervisee.setSpn(ov.getSuperviseeId());
								NameBean superviseeName = new NameBean();
								superviseeName.setFirstName(ov.getPartyEvent().getFirstName());
								superviseeName.setLastName(ov.getPartyEvent().getLastName());
								superviseeName.setMiddleName(ov.getPartyEvent().getMiddleName());
								supervisee.setName(superviseeName);
								superviseeList.add(supervisee);
								superviseeIdList.add(ov.getSuperviseeId());
								break;
							}
						}
					}
				}
			}
			
		} else {
			//TODO:Enter supervisee Id
		}
		
		if(selectedEventIdList.size() < 1) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Please select a Group Office Visit to proceed");
   			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		form.setSuperviseeList(superviseeList);
		form.setSelectedEventIdList(selectedEventIdList);
		
		//load one of the events as they are essentially the same 
		form.getCurrentOfficeVisit().load((CSOfficeVisitResponseEvent)selectedEvents.iterator().next());
		
		return forward;
	}
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link","view");
		keyMap.put("button.enterResults","enterResults");
		keyMap.put("button.updateSelectedResults","updateSelectedResults");
		keyMap.put("button.addAttendees","addAttendees");
		keyMap.put("button.update","update");
		keyMap.put("button.reschedule","reschedule");
		keyMap.put("button.delete","delete");

	}

}
