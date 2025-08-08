// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\SaveCSOfficeVisitCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;
import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;
import messaging.cscdcalendar.SaveCSOfficeVisitEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class SaveCSOfficeVisitCommand implements ICommand {

	/**
	 * @roseuid 479A0EB80145
	 */
	public SaveCSOfficeVisitCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA503E5
	 */
	public void execute(IEvent event) {

		SaveCSOfficeVisitEvent saveEvent = (SaveCSOfficeVisitEvent) event;
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
			createOfficeVisit(saveEvent);
		} else if(saveEvent.isUpdate()) {
			updateOfficeVisit(saveEvent);
		} else if(saveEvent.isDelete()) {
			deleteOfficeVisit(saveEvent);
		} else if(saveEvent.isReschedule()) {
			rescheduleOfficeVisit(saveEvent);
		} else if(saveEvent.isResults()) {
			resultsOfficeVisit(saveEvent);
		} 
	}
	
	public void createOfficeVisit(SaveCSOfficeVisitEvent saveEvent) {		
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isPositionError(saveEvent.getPositionId())) {
			return;
		}
		if(CSEventHelper.isSuperviseeError(saveEvent.getSuperviseeId())) {
			return;
		}
		CSEvent csEvent = new CSEvent();		
		setUpdateOrCreateValues(csEvent, saveEvent);
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
		csEvent.setOutCome(PDCodeTableConstants.DEFAULT_CS_EVENT_OUTCOME);
		csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
		postCSEvent(csEvent);		
	}
	
	public void updateOfficeVisit(SaveCSOfficeVisitEvent saveEvent) {
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isEventIdError(saveEvent.getEventId())) {
			return;		
		}		 
		CSEvent csEvent = CSEvent.find(saveEvent.getEventId());
		if(csEvent==null) {
			CSEventHelper.postCSEventError("There is no Office Visit with the eventid specified.");
			return;
		}	
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
			SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_OFFICE_VISIT);
			subjects.add((String) aCode.getOID());
			String notes = "";
			StringBuffer notesBuffer = new StringBuffer();
			notesBuffer.append("Office Visit Result Updated to: ");
			notesBuffer.append(CSEventHelper.getNotes(csEvent, true));
			notes = notesBuffer.toString();
	        CSEventHelper.createCasenote(defendantId, notes, subjects, csEvent.getEventDate(), csEvent.getOID());
		}
//		postCSEvent(csEvent);
	}
	
	public void deleteOfficeVisit(SaveCSOfficeVisitEvent saveEvent) {	
		if(CSEventHelper.isEventIdError(saveEvent.getDeleteOVId())) {
			return;		
		}		
		CSEvent csEvent = CSEvent.find(saveEvent.getDeleteOVId());
		if(csEvent==null) {
			CSEventHelper.postCSEventError("There is no Office Visit with the eventid specified.");
			return;
		}	
		String status = csEvent.getStatusId();
		if(status!=null) {
			if(status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_OPEN)) {				
				csEvent.setDeleted();	
			} else if(status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)){
//				csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
				csEvent.setMarkedForDeleteOn(new Date());
//				csEvent.setDeleted();	
			} else {
				CSEventHelper.postCSEventError("The Office Visit status is corrupted.");
				return;
			}
		} else {
			CSEventHelper.postCSEventError("The Office Visit status is corrupted.");
			return;
		}	
	}
	
	public void rescheduleOfficeVisit(SaveCSOfficeVisitEvent saveEvent) {	
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isEventIdError(saveEvent.getRescheduleOVId())) {
			return;		
		}
		if(CSEventHelper.isPositionError(saveEvent.getPositionId())) {
			return;
		}
		if(CSEventHelper.isSuperviseeError(saveEvent.getSuperviseeId())) {
			return;
		}
		CSEvent resCSEvent = CSEvent.find(saveEvent.getRescheduleOVId());
		if(resCSEvent==null) {
			CSEventHelper.postCSEventError("There is no Office Visit with the eventid specified.");
			return;
		}	
		if(CSEventHelper.isStatusNotOpenError(resCSEvent.getStatusId())) {
			return;		
		}
		resCSEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
		resCSEvent.setOutCome(PDCodeTableConstants.OV_OUTCOME_RESCHEDULED);	
		resCSEvent.setRescheduleReason(saveEvent.getRescheduleReason());
		CSEvent csEvent = new CSEvent();
		csEvent.setOutCome(PDCodeTableConstants.OTHER_AND_FV_OUTCOME_SCHEDULED);
		setUpdateOrCreateValues(csEvent, saveEvent);
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
		csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
//		create casenote if there is a supervisee associated with the event
		String defendantId = resCSEvent.getPartyId();
//  	Retrieve current supervision period for supervisee
//  	Create a casenote in current supervision period
		if(defendantId!=null && defendantId.trim().length()>0) {		
			Collection subjects = new ArrayList();
			SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_OFFICE_VISIT);
			subjects.add((String) aCode.getOID());
			String notes = CSEventHelper.getRescheduleNotes(csEvent, resCSEvent, true);
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
	        CSEventHelper.createCasenote(defendantId, notes, subjects, today,resCSEvent.getOID());
		}
		postCSEvent(csEvent);		
	}
	
	public void resultsOfficeVisit(SaveCSOfficeVisitEvent saveEvent) {
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isEventIdError(saveEvent.getEventId())) {
			return;		
		}
		if(CSEventHelper.isResultPositionError(saveEvent.getResultPositionId())) {
			return;		
		}
		CSEvent csEvent = CSEvent.find(saveEvent.getEventId());
		if(csEvent==null) {
			CSEventHelper.postCSEventError("There is no Office Visit with the eventid specified.");
			return;
		}	
		if(CSEventHelper.isStatusNotOpenError(csEvent.getStatusId())) {
			return;		
		}	
		
		setCSEventDates(csEvent, saveEvent);
		csEvent.setOutCome(saveEvent.getOutcome());
		csEvent.setNarrative(saveEvent.getNarrative());
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
		csEvent.setResultPositionId(saveEvent.getResultPositionId());
		String logonId = PDSecurityHelper.getLogonId();
		csEvent.setResultUserId(logonId);
		csEvent.setAssignStaffPos_Id(saveEvent.getAssignStaffPos_Id());
//		create casenote if there is a supervisee associated with the event
		String defendantId = csEvent.getPartyId();
  	  //  Retrieve current supervision period for supervisee
      //  Create a casenote in current supervision period
		if(defendantId!=null && defendantId.trim().length()>0) {		
			Collection subjects = new ArrayList();
			SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_OFFICE_VISIT);
			subjects.add((String) aCode.getOID());
			String notes = CSEventHelper.getNotes(csEvent, true);
	        CSEventHelper.createCasenote(defendantId, notes, subjects, csEvent.getEventDate(),csEvent.getOID());
		}
//		postCSEvent(csEvent);		
	}
	
	private void setUpdateOrCreateValues(CSEvent csEvent, SaveCSOfficeVisitEvent saveEvent) {
		setCSEventDates(csEvent, saveEvent);
//		csEvent.setEventTypeId(saveEvent.getEventType());	
		csEvent.setEventTypeId(PDCodeTableConstants.CS_OFFICE_VISIT_TYPE);
//		csEvent.setStatusId(saveEvent.getStatus());
		csEvent.setCreatedBy(saveEvent.getCreatedBy());
		csEvent.setTimeZone(CSEventHelper.getCSEventDefaultTimezone());
		csEvent.setEventName(saveEvent.getEventName());
		csEvent.setPurpose(saveEvent.getPurpose());
		csEvent.setPhoneNumber(saveEvent.getPhonenum());
		csEvent.setAssignStaffPos_Id(saveEvent.getAssignStaffPos_Id());
		setEventForContexts(csEvent, saveEvent);
	}	
	
	private void setCSEventDates(CSEvent csEvent, SaveCSOfficeVisitEvent saveEvent) {
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
	
	private void setEventForContexts(CSEvent csEvent, SaveCSOfficeVisitEvent saveEvent) {		
		csEvent.setPositionId(saveEvent.getPositionId());
		csEvent.setPartyId(saveEvent.getSuperviseeId());		
	}
	
	private void postCSEvent(CSEvent csEvent) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CSOfficeVisitResponseEvent resp = CSEventBuilder.buildOfficeVisit(csEvent, true);
		dispatch.postEvent(resp);
	}
	
	public boolean isPositionError(SaveCSOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;		
		if(saveEvent.getPositionId()==null || saveEvent.getPositionId().trim().length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Position Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	public boolean isSuperviseeError(SaveCSOfficeVisitEvent saveEvent) {
		boolean isErrorReported = false;		
		if(saveEvent.getSuperviseeId()==null || saveEvent.getSuperviseeId().trim().length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Supervisee Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	private boolean isCSEventGenericError(SaveCSOfficeVisitEvent saveEvent) {
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
//		if(CSEventHelper.isStatusError(saveEvent.getStatus())) {
//			isErrorReported = true;			
//		}
		if(CSEventHelper.isEventTypeError(saveEvent.getEventType(), PDCodeTableConstants.CS_OFFICE_VISIT_CATEGORY)) {
			isErrorReported = true;			
		}
//		if(CSEventHelper.isEventContextObjectsError(saveEvent.getEventType(), saveEvent.getContext(), 
//				saveEvent.getPositionId(), saveEvent.getSuperviseeId() )) {
//			isErrorReported = true;			
//		}
//		if(CSEventHelper.isEventContextError(saveEvent.getContext())) {
//			isErrorReported = true;			
//		}
		return isErrorReported;		
	}

	/**
	 * @param event
	 * @roseuid 4798EEA503E7
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA503E9
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EEA6000D
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB80164
	 */
	/*
	 * public void update(Object updateObject) { }
	 */
}
