// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\SaveCSOtherEventCommand.java

package pd.supervision.cscdcalendar.transactions;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import naming.PDCodeTableConstants;

import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;
import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;

import messaging.cscdcalendar.SaveCSOtherEventEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class SaveCSOtherEventCommand implements ICommand {

	/**
	 * @roseuid 479A0EB80200
	 */
	public SaveCSOtherEventCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EE8B005C
	 */
	public void execute(IEvent event) {
		SaveCSOtherEventEvent saveEvent = (SaveCSOtherEventEvent) event;
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
			createOtherEvent(saveEvent);
		} else if(saveEvent.isUpdate()) {
			updateOtherEvent(saveEvent);
		} else if(saveEvent.isDelete()) {
			deleteOtherEvent(saveEvent);
		} else if(saveEvent.isReschedule()) {
			rescheduleOtherEvent(saveEvent);
		} else if(saveEvent.isResults()) {
			resultsOtherEvent(saveEvent);
		} 
	}
	
	public void createOtherEvent(SaveCSOtherEventEvent saveEvent) {		
		if(isCSEventGenericError(saveEvent)) {
			return;
		}		
		CSEvent csEvent = new CSEvent();
		setUpdateOrCreateValues(csEvent, saveEvent);
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
		csEvent.setOutCome(PDCodeTableConstants.DEFAULT_CS_EVENT_OUTCOME);
		csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
		postCSEvent(csEvent);		
	}
	
	public void updateOtherEvent(SaveCSOtherEventEvent saveEvent) {
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isEventIdError(saveEvent.getEventId())) {
			return;		
		}		
		CSEvent csEvent = CSEvent.find(saveEvent.getEventId());
		if(csEvent==null) {
			CSEventHelper.postCSEventError("There is no other event with the eventid specified.");
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
			SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_OTHER_EVENT);
			subjects.add((String) aCode.getOID());
			String notes = "Event Updated to : " + CSEventHelper.getNotes(csEvent, false);
	        CSEventHelper.createCasenote(defendantId, notes, subjects, csEvent.getEventDate(), csEvent.getOID());
		}
//		postCSEvent(csEvent);
	}
	
	public void deleteOtherEvent(SaveCSOtherEventEvent saveEvent) {	
		if(CSEventHelper.isEventIdError(saveEvent.getDeleteEventId())) {
			return;		
		}		
		CSEvent csEvent = CSEvent.find(saveEvent.getDeleteEventId());
		if(csEvent==null) {
			CSEventHelper.postCSEventError("There is no other event with the eventid specified.");
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
				CSEventHelper.postCSEventError("The other event status is corrupted.");
				return;
			}
		} else {
			CSEventHelper.postCSEventError("The other event status is corrupted.");
			return;
		}	
	}
	
	public void rescheduleOtherEvent(SaveCSOtherEventEvent saveEvent) {	
		if(isCSEventGenericError(saveEvent)) {
			return;
		}
		if(CSEventHelper.isEventIdError(saveEvent.getRescheduleEventId())) {
			return;		
		}		
		CSEvent resCSEvent = CSEvent.find(saveEvent.getRescheduleEventId());
		if(resCSEvent==null) {
			CSEventHelper.postCSEventError("There is no other event with the eventid specified.");
			return;
		}
		if(CSEventHelper.isStatusNotOpenError(resCSEvent.getStatusId())) {
			return;		
		}
		resCSEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
		resCSEvent.setOutCome(PDCodeTableConstants.OTHER_AND_FV_OUTCOME_RESCHEDULED);	
		resCSEvent.setAssignStaffPos_Id("");
		CSEvent csEvent = new CSEvent();
		setUpdateOrCreateValues(csEvent, saveEvent);
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
		csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
		postCSEvent(csEvent);	
	}
	
	public void resultsOtherEvent(SaveCSOtherEventEvent saveEvent) {
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
			CSEventHelper.postCSEventError("There is no other event with the eventid specified.");
			return;
		}	
		if(CSEventHelper.isStatusNotOpenError(csEvent.getStatusId())) {
			return;		
		}		
		setCSEventDates(csEvent, saveEvent);
		csEvent.setAssignStaffPos_Id(saveEvent.getPositionId());
		csEvent.setOutCome(saveEvent.getOutcome());
		csEvent.setNarrative(saveEvent.getNarrative());
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
		csEvent.setResultPositionId(saveEvent.getResultPositionId());
		String logonId = PDSecurityHelper.getLogonId();
		csEvent.setResultUserId(logonId);
		//create casenote if there is a supervisee associated with the event
		String defendantId = csEvent.getPartyId();
  	  //  Retrieve current supervision period for supervisee
      //  Create a casenote in current supervision period
		if(defendantId!=null && defendantId.trim().length()>0) {		
			Collection subjects = new ArrayList();
			SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,PDCodeTableConstants.CASENOTE_SUBJECT_OTHER_EVENT);
			subjects.add((String) aCode.getOID());
			String notes = CSEventHelper.getNotes(csEvent, false);
	        CSEventHelper.createCasenote(defendantId, notes, subjects, csEvent.getEventDate(), csEvent.getOID());
		}
//		csEvent.setResultUserId(saveEvent.getResultUserId());
//		postCSEvent(csEvent);		
	}
	
	private void setUpdateOrCreateValues(CSEvent csEvent, SaveCSOtherEventEvent saveEvent) {
		setCSEventDates(csEvent, saveEvent);
		csEvent.setEventTypeId(saveEvent.getEventType());
		csEvent.setOtherEventType(saveEvent.getOtherType());
		csEvent.setOutCome(PDCodeTableConstants.DEFAULT_CS_EVENT_OUTCOME);
		csEvent.setAssignStaffPos_Id(saveEvent.getPositionId());
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
		csEvent.setCreatedBy(saveEvent.getCreatedBy());
		csEvent.setTimeZone(CSEventHelper.getCSEventDefaultTimezone());
		csEvent.setEventName(saveEvent.getEventName());
		csEvent.setPurpose(saveEvent.getPurpose());		
		setEventForContexts(csEvent, saveEvent);
	}
	
	private void setCSEventDates(CSEvent csEvent, SaveCSOtherEventEvent saveEvent) {
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
	
	private void setEventForContexts(CSEvent csEvent, SaveCSOtherEventEvent saveEvent) {
		if(saveEvent.getContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE)) {
			csEvent.setPositionId(saveEvent.getPositionId());
			csEvent.setPartyId(saveEvent.getSuperviseeId());
		} else if(saveEvent.getContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			CSEventTypeResponseEvent eveType = CSEventHelper.getInstance().getCSEventType(saveEvent.getEventType());
			if(eveType.getDisplayContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
				csEvent.setPositionId(saveEvent.getPositionId());				
			} else if(eveType.getDisplayContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION_AND_SUPERVISEE)) {
				csEvent.setPositionId(saveEvent.getPositionId());
				csEvent.setPartyId(saveEvent.getSuperviseeId());
			} else if(eveType.getDisplayContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE)) {
				csEvent.setPartyId(saveEvent.getSuperviseeId());
			}
		}
	}
	
	private void postCSEvent(CSEvent csEvent) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CSOtherResponseEvent resp = CSEventBuilder.buildOtherEvent(csEvent, true);
		dispatch.postEvent(resp);
	}
	
	private boolean isCSEventGenericError(SaveCSOtherEventEvent saveEvent) {
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
		if(CSEventHelper.isEventTypeError(saveEvent.getEventType(), PDCodeTableConstants.CS_OTHER_EVENT_CATEGORY)) {
			isErrorReported = true;			
		}
//		if(CSEventHelper.isEventContextObjectsError(saveEvent.getEventType(), saveEvent.getContext(), 
//				saveEvent.getPositionId(), saveEvent.getSuperviseeId() )) {
//			isErrorReported = true;			
//		}
		if(CSEventHelper.isEventContextError(saveEvent.getContext())) {
			isErrorReported = true;			
		}
		if(CSEventHelper.isPositionError(saveEvent.getPositionId())) {
			isErrorReported = true;			
		}
		
		return isErrorReported;		
	}
		

	/**
	 * @param event
	 * @roseuid 4798EE8B006A
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EE8B006C
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EE8B006E
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB8021F
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 * 
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 * 
	 * public void update(Object updateObject) { // TODO Auto-generated method
	 * stub
	 *  }
	 * 
	 * (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 * 
	 * public void update(Object updateObject) { // TODO Auto-generated method
	 * stub
	 *  }
	 * 
	 * (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 * 
	 * public void update(Object updateObject) { // TODO Auto-generated method
	 * stub
	 *  }
	 */
}
