// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\SaveCSGroupOfficeVisitCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventHelper;
import messaging.administercaseload.GetActiveCasesEvent;
import messaging.cscdcalendar.CSEventIdAttribute;
import messaging.cscdcalendar.CSEventNameAttribute;
import messaging.cscdcalendar.CSEventTypeAttribute;
import messaging.cscdcalendar.CSPositionAttribute;
import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.cscdcalendar.SaveCSGroupOfficeVisitEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class SaveCSGroupOfficeVisitCommand implements ICommand {

	/**
	 * @roseuid 479A0EB80089
	 */
	public SaveCSGroupOfficeVisitCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA50240
	 */
	public void execute(IEvent event) {

		SaveCSGroupOfficeVisitEvent saveEvent = (SaveCSGroupOfficeVisitEvent) event;
		int trueCount = 0;
		if(saveEvent.isCreate()) {
			trueCount++;
		}
		if(saveEvent.isUpdate()) {
			trueCount++;
		}
		if(saveEvent.isDelete()) {
			trueCount++;
		}
		if(saveEvent.isReschedule()) {
			trueCount++;
		}
		if(saveEvent.isAddAttendees()) {
			trueCount++;
		}
		if(saveEvent.isResults()) {
			trueCount++;
		}
		if(trueCount==0) {
			CSEventHelper.postCSEventError("Please specify atleast one funtion like isCreate, isUpdate etc.");
			return;
		}
		if(trueCount>1) {
			CSEventHelper.postCSEventError("Please specify only one funtion like isCreate, isUpdate etc.");
			return;
		}
		if(saveEvent.isCreate()) {
			createGroupOfficeVisit(saveEvent);
		} else if(saveEvent.isAddAttendees()) {
			addAttendeesToGroupOfficeVisit(saveEvent);
		} else if(saveEvent.isUpdate()) {
			updateGroupOfficeVisit(saveEvent);
		} else if(saveEvent.isDelete()) {
			deleteGroupOfficeVisit(saveEvent);
		} else if(saveEvent.isReschedule()) {
			rescheduleGroupOfficeVisit(saveEvent);
		} else if(saveEvent.isResults()) {
			resultsGroupOfficeVisit(saveEvent);
		} 
	}
	
	public void createGroupOfficeVisit(SaveCSGroupOfficeVisitEvent saveEvent) {		
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(isGroupExistsError(saveEvent)) {
			return;
		}
		if(isSuperviseeIdsError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isPositionError(saveEvent.getPositionId())) {
			return;
		}		
		Iterator superviseeIte = saveEvent.getSuperviseeIds().iterator();
		while(superviseeIte.hasNext()) {	
			String superviseeId = (String) superviseeIte.next();
			CSEvent csEvent = new CSEvent();
			csEvent.setPartyId(superviseeId);
			setUpdateOrCreateValues(csEvent, saveEvent);
			csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
			csEvent.setOutCome(PDCodeTableConstants.DEFAULT_CS_EVENT_OUTCOME);
			csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
//			postCSEvent(csEvent);	
		}
	}
	
	public void addAttendeesToGroupOfficeVisit(SaveCSGroupOfficeVisitEvent saveEvent) {		
		if(isCSEventGenericError(saveEvent)) {
			return;
		}		
		if(isSuperviseeIdsError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isPositionError(saveEvent.getPositionId())) {
			return;
		}
		Iterator superviseeIte = saveEvent.getSuperviseeIds().iterator();
		while(superviseeIte.hasNext()) {	
			String superviseeId = (String) superviseeIte.next();
			CSEvent csEvent = new CSEvent();
			csEvent.setPartyId(superviseeId);
			setUpdateOrCreateValues(csEvent, saveEvent);
			csEvent.setOutCome(saveEvent.getOutcome());
			csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
			csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
//			postCSEvent(csEvent);	
		}		
	}
	
	public void updateGroupOfficeVisit(SaveCSGroupOfficeVisitEvent saveEvent) {
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(isEventIdsError(saveEvent)) {
			return;
		}			
		Iterator csEventIte = getGroupOfficeVisits(saveEvent.getEventIds()).iterator();
		SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_GROUP_OFFICE_VISIT);
		while(csEventIte.hasNext()) {	
			CSEvent csEvent = (CSEvent) csEventIte.next();		
			setUpdateOrCreateValues(csEvent, saveEvent);
			csEvent.setOutCome(saveEvent.getOutcome());
			csEvent.setNarrative(saveEvent.getNarrative());
			if(saveEvent.getResultPositionId()!=null && saveEvent.getResultPositionId().trim().length()>0) {
				csEvent.setResultPositionId(saveEvent.getResultPositionId());
			}
			String logonId = PDSecurityHelper.getLogonId();
			csEvent.setResultUserId(logonId);
			String defendantId = csEvent.getPartyId();
			if(defendantId!=null && defendantId.trim().length()>0) {		
				Collection subjects = new ArrayList();
				subjects.add((String) aCode.getOID());
				StringBuffer caseNote = new StringBuffer();
				if(StringUtils.isNotEmpty(CSEventHelper.getNotes(csEvent, true))){
					caseNote.append("Office Visit Result Updated to:");
					caseNote.append(CSEventHelper.getNotes(csEvent, true));
				}
				String notes =  caseNote.toString();
				// here for id
		        CSEventHelper.createCasenote(defendantId, notes, subjects, csEvent.getEventDate(), csEvent.getOID());
			}
//			postCSEvent(csEvent);
		}
	}
	
	public void deleteGroupOfficeVisit(SaveCSGroupOfficeVisitEvent saveEvent) {	
		if(isEventIdsError(saveEvent)) {
			return;
		}		
		Iterator csEventIte = getGroupOfficeVisits(saveEvent.getEventIds()).iterator();
		while(csEventIte.hasNext()) {	
			CSEvent csEvent = (CSEvent) csEventIte.next();
			String status = csEvent.getStatusId();
			if(status!=null) {
				if(status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_OPEN)) {				
					csEvent.setDeleted();	
				} else if(status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)){
//					csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
					csEvent.setMarkedForDeleteOn(new Date());
//					csEvent.setDeleted();	
				} 
//				else {
//					CSEventHelper.postCSEventError("The Office Visit status is corrupted.");
//					return;
//				}
			} 
//			else {
//				CSEventHelper.postCSEventError("The Office Visit status is corrupted.");
//				return;
//			}	
		}				
	}
	
	public void rescheduleGroupOfficeVisit(SaveCSGroupOfficeVisitEvent saveEvent) {	
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(isEventIdsError(saveEvent)) {
			return;
		}		
		if(isSuperviseeIdsError(saveEvent)) {
			return;
		}	
		if(CSEventHelper.isPositionError(saveEvent.getPositionId())) {
			return;
		}
		List groupVisits = getGroupOfficeVisits(saveEvent.getEventIds());
		Iterator csEventIte = groupVisits.iterator();
		// Handle this on UI side, if all the visits are open, then only show
		// the reschedule button
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		while(csEventIte.hasNext()) {	
			CSEvent resCSEvent = (CSEvent) csEventIte.next();		
//			update existing events			
			resCSEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
			resCSEvent.setOutCome(PDCodeTableConstants.OV_OUTCOME_RESCHEDULED);	
			resCSEvent.setRescheduleReason(saveEvent.getRescheduleReason());
			String defendantId = resCSEvent.getPartyId();
//			create new events			
			CSEvent csEvent = new CSEvent();
			csEvent.setOutCome(PDCodeTableConstants.OTHER_AND_FV_OUTCOME_SCHEDULED);
			csEvent.setPartyId(defendantId);
			setUpdateOrCreateValues(csEvent, saveEvent);
			csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
			csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
//			create casenote if there is a supervisee associated with the event
			if(defendantId!=null && defendantId.trim().length()>0) {		
				Collection subjects = new ArrayList();
				SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_OFFICE_VISIT);
				subjects.add((String) aCode.getOID());
				String notes = CSEventHelper.getRescheduleNotes(csEvent, resCSEvent, true);
		        CSEventHelper.createCasenote(defendantId, notes, subjects, today,resCSEvent.getOID());
			}
		}	
	}
	
	public void resultsGroupOfficeVisit(SaveCSGroupOfficeVisitEvent saveEvent) {
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(isEventIdsError(saveEvent)) {
			return;
		}	
		if(CSEventHelper.isResultPositionError(saveEvent.getResultPositionId())) {
			return;		
		}

		List csEventList = getGroupOfficeVisits(saveEvent.getEventIds());
		for (int i=0; i < csEventList.size(); i++) {                 
			CSEvent csEvent = (CSEvent) csEventList.get(i);		
			if(CSEventHelper.isStatusNotOpenError(csEvent.getStatusId())) {
				return;		
			}		
		}
		SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_GROUP_OFFICE_VISIT);
		for (int j=0; j < csEventList.size(); j++) {                 
			CSEvent csEvent = (CSEvent) csEventList.get(j);		
			setCSEventDates(csEvent, saveEvent);
			csEvent.setOutCome(saveEvent.getOutcome());
			csEvent.setNarrative(saveEvent.getNarrative());
			csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
			csEvent.setResultPositionId(saveEvent.getResultPositionId());
			String logonId = PDSecurityHelper.getLogonId();
			csEvent.setResultUserId(logonId);
//			create casenote if there is a supervisee associated with the event
			String defendantId = csEvent.getPartyId();
	  	  //  Retrieve current supervision period for supervisee
	      //  Create a casenote in current supervision period
			if(defendantId!=null && defendantId.trim().length()>0) {		
				Collection subjects = new ArrayList();
				subjects.add((String) aCode.getOID());
				String notes = CSEventHelper.getNotes(csEvent, true);
		        CSEventHelper.createCasenote(defendantId, notes, subjects, csEvent.getEventDate(),csEvent.getOID() );
			}
//			postCSEvent(csEvent);		
		}	
		
	}
	
	private void setUpdateOrCreateValues(CSEvent csEvent, SaveCSGroupOfficeVisitEvent saveEvent) {
		
		setCSEventDates(csEvent, saveEvent);
//		csEvent.setEventTypeId(saveEvent.getEventType());
		csEvent.setEventTypeId(PDCodeTableConstants.CS_GROUP_OFFICE_VISIT_TYPE);
		csEvent.setCreatedBy(saveEvent.getCreatedBy());
		csEvent.setTimeZone(CSEventHelper.getCSEventDefaultTimezone());
		csEvent.setEventName(saveEvent.getEventName());
		csEvent.setPurpose(saveEvent.getPurpose());	
		csEvent.setPhoneNumber(saveEvent.getPhonenum());
		csEvent.setPositionId(saveEvent.getPositionId());
		Supervisee supervisee = Supervisee.findByDefendantId( csEvent.getPartyId() ) ;
		if ( supervisee != null ){
			if ( StringUtils.isNotEmpty(supervisee.getCaseloadCreditStaffPositionId()) ) {
				csEvent.setAssignStaffPos_Id( supervisee.getCaseloadCreditStaffPositionId() );
			} else {
				GetActiveCasesEvent reqEvent = new GetActiveCasesEvent();
				reqEvent.setDefendantId( csEvent.getPartyId() );
				Iterator iter = CaseAssignmentOrder.findAllByEvent(reqEvent);
				while( iter.hasNext() ){
					CaseAssignment caseAssignment = (CaseAssignment)iter.next();
					if ( caseAssignment != null ){
						if ("OFFICER_ACKNOWLEDGED".equals( caseAssignment.getCaseState() ) || "OFFICER_ASSIGNED".equals( caseAssignment.getCaseState())){
							csEvent.setAssignStaffPos_Id( caseAssignment.getAssignedStaffPositionId() );
						}
					}
				}
			}
		}
	}	
	
	private void setCSEventDates(CSEvent csEvent, SaveCSGroupOfficeVisitEvent saveEvent) {
		csEvent.setEventDate(saveEvent.getEventDate());
		String startTime = null;
		if(saveEvent.getStartTime()==null || saveEvent.getStartTime().trim().equals("")) {
			startTime = null;
		} else startTime = saveEvent.getStartTime();
		if(startTime!=null) {
			Date startDateTime = CSEventHelper.getDateForCSEvent(saveEvent.getEventDate(), startTime);
			csEvent.setStartTime(startDateTime);
		}
		String endTime = null;
		if(saveEvent.getEndTime()==null || saveEvent.getEndTime().trim().equals("")) {
			endTime = null;
		} else endTime = saveEvent.getEndTime();
		if(endTime!=null) {
			Date endDateTime = CSEventHelper.getDateForCSEvent(saveEvent.getEventDate(), endTime);
			csEvent.setEndTime(endDateTime);
		}		
	}
	
	
	private boolean isCSEventGenericError(SaveCSGroupOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;
		if(CSEventHelper.isEventDateError(saveEvent.getEventDate())) {
			isErrorReported = true;			
		}
		if(CSEventHelper.isTimeError(saveEvent.getStartTime(), "start time")) {
			isErrorReported = true;			
		}
		if(CSEventHelper.isTimeError(saveEvent.getEndTime(), "end time")) {
			isErrorReported = true;			
		}	

		if(CSEventHelper.isEventTypeError(saveEvent.getEventType(), PDCodeTableConstants.CS_OFFICE_VISIT_CATEGORY)) {
			isErrorReported = true;			
		}

		return isErrorReported;		
	}
	
	public boolean isEventIdsError(SaveCSGroupOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;
		List groupIds = saveEvent.getEventIds();
		if(groupIds==null || groupIds.size()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Please provide Group Office visit Event Ids.");
			return isErrorReported;
		} else {
			Iterator groupIdsIte = groupIds.iterator();
			while(groupIdsIte.hasNext()) {
				String  eId = (String) groupIdsIte.next();
				return CSEventHelper.isEventIdError(eId);				
			}
		}
		return isErrorReported;
	}
	
	public boolean isSuperviseeIdsError(SaveCSGroupOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;
		List superviseeIds = saveEvent.getSuperviseeIds();
		if(superviseeIds==null || superviseeIds.size()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Please provide Group Office visit Supervisee Ids.");
			return isErrorReported;
		} else {
			Iterator superviseeIdsIte = superviseeIds.iterator();
			while(superviseeIdsIte.hasNext()) {
				String  eId = (String) superviseeIdsIte.next();
				return CSEventHelper.isSuperviseeError(eId);
			}
		}
		return isErrorReported;
	}
	
	public boolean isPositionError(SaveCSGroupOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;		
		if(saveEvent.getPositionId()==null || saveEvent.getPositionId().trim().length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Position Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	public boolean isGroupExistsError(SaveCSGroupOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;
		List groupList = getGroupOfficeVisits(saveEvent);
		if(groupList!=null && groupList.size()>0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("The group office visit already exists, please choose different group name.");
		}
		return isErrorReported;
	}
	
	private List getGroupOfficeVisits(SaveCSGroupOfficeVisitEvent saveEvent) {
		
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent();
		retrieverEvent.setStartDatetime(saveEvent.getEventDate());
		retrieverEvent.setEndDatetime(saveEvent.getEventDate());    	
		
		// Calendar Attributes    	
		ICalendarAttribute[] as = new ICalendarAttribute[3];		
		retrieverEvent.setCalendarAttributes(as);		
		
		CSEventTypeAttribute eventTypeAttr = new CSEventTypeAttribute();
		eventTypeAttr.setEventTypeId(PDCodeTableConstants.CS_GROUP_OFFICE_VISIT_TYPE);
		as[0] = eventTypeAttr;	
		
		CSPositionAttribute positionAttr = new CSPositionAttribute();
		positionAttr.setPositionId(new Integer(saveEvent.getPositionId()));
		as[1] = positionAttr;
		
		CSEventNameAttribute nameAttr = new CSEventNameAttribute();
		nameAttr.setEventName(saveEvent.getEventName());
		as[2] = nameAttr;
		
		List groupList = new ArrayList();
		IHome home = new Home();
        Iterator csEventsIte = home.findAll(retrieverEvent, CSEvent.class);       
        if(csEventsIte!=null) {
        	while(csEventsIte.hasNext()) {
        		CSEvent e = (CSEvent) csEventsIte.next();
        		groupList.add(e);
        	}
        }
        return groupList;
	}
	
	private List getGroupOfficeVisits(List eventIds) {
		
		Iterator eveIdsIte = eventIds.iterator();
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent(); 	
		
		// Calendar Attributes    	
		ICalendarAttribute[] as = new ICalendarAttribute[eventIds.size()];		
		retrieverEvent.setCalendarAttributes(as);		
		int i=0;
		while(eveIdsIte.hasNext()) {
			String eveId = (String) eveIdsIte.next();
			CSEventIdAttribute eventIdAttr = new CSEventIdAttribute();
			eventIdAttr.setEventId(eveId);
			as[i++] = eventIdAttr;
		}		
		
		List groupList = new ArrayList();
		IHome home = new Home();
        Iterator csEventsIte = home.findAll(retrieverEvent, CSEvent.class);       
        if(csEventsIte!=null) {
        	while(csEventsIte.hasNext()) {
        		CSEvent e = (CSEvent) csEventsIte.next();
        		groupList.add(e);
        	}
        }
        return groupList;
	}

	/**
	 * @param event
	 * @roseuid 4798EEA50242
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA50244
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EEA5024F
	 */
	public void update(Object anObject) {

	}
}
