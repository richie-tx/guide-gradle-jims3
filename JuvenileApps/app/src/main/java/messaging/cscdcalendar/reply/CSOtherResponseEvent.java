//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\ResponseEvents\\CSOtherResponseEvent.java

package messaging.cscdcalendar.reply;

import java.util.Date;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;


public class CSOtherResponseEvent  extends ResponseEvent {
   
	private String eventType;
	private String eventTypeDesc;
	private String eventName;
	private String startTime;
	private String endTime;
	private String superviseeId;
	private String outcome;
	private String outcomeDesc;
	private String purpose;
	private String narrative;
	private Date eventDate;
	private String positionId;
	private String eventId;
	private String status;
	private String otherType;
	private String phonenum;
	private String resultUserId;
	private String resultPositionId;
	private PartyResponseEvent partyEvent;
	private CSCDSupervisionStaffResponseEvent positionEvent;
	private String sortLastName;
	private String startTime1;
	private String endTime1;
	private String AMPMId1;
	private String AMPMId2;

   
	/**
	 * @roseuid 47A23670023F
	 */
	public CSOtherResponseEvent() {
    
	}
	
	public String getSortLastName() {
		if (this.getPartyEvent() != null && this.getPartyEvent().getLastName() != null) {
			sortLastName = this.getPartyEvent().getLastName();

		} else {
			sortLastName = "";
		}
		return sortLastName;
	}
	
	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return Returns the eventName.
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName The eventName to set.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		return narrative;
	}
	/**
	 * @param narrative The narrative to set.
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	/**
	 * @return Returns the outcome.
	 */
	public String getOutcome() {
		return outcome;
	}
	/**
	 * @param outcome The outcome to set.
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose The purpose to set.
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return Returns the supervisee.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param supervisee The supervisee to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	
	
	
	public String getStartTime1() {
		if (startTime1 == null) {							
				if ((startTime != null) && (!startTime.trim().equals(""))
						&& (startTime.length() > 5)) {
					startTime1 = startTime.substring(0, 5);
				
			}
		}
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		
		this.startTime1 = startTime1;
	}

	public String getEndTime1() {
		if (endTime1 == null) {					
				if ((endTime != null) && (!endTime.trim().equals(""))
						&& (endTime.length() > 5)) {
					endTime1 = endTime.substring(0, 5);
				}
			
		}
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getAMPMId1() {
		if (AMPMId1 == null) {					
				if ((startTime != null) && (!startTime.trim().equals(""))
						&& (startTime.length() > 5)) {
					AMPMId1 = startTime.substring(5).trim();
				}
			
		}
		return AMPMId1;
	}

	public void setAMPMId1(String id1) {
		AMPMId1 = id1;
	}

	public String getAMPMId2() {
		if (AMPMId2 == null) {					
				if ((endTime != null) && (!endTime.trim().equals(""))
						&& (endTime.length() > 5)) {
					AMPMId2 = endTime.substring(5).trim();
				}
		}
		
		return AMPMId2;
	}

	public void setAMPMId2(String id2) {
		AMPMId2 = id2;
	}

	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return Returns the otherType.
	 */
	public String getOtherType() {
		return otherType;
	}
	/**
	 * @param otherType The otherType to set.
	 */
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
	/**
	 * @return Returns the phonenum.
	 */
	public String getPhonenum() {
		return phonenum;
	}
	/**
	 * @param phonenum The phonenum to set.
	 */
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @param positionId The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @return Returns the resultPositionId.
	 */
	public String getResultPositionId() {
		return resultPositionId;
	}
	/**
	 * @param resultPositionId The resultPositionId to set.
	 */
	public void setResultPositionId(String resultPositionId) {
		this.resultPositionId = resultPositionId;
	}
	/**
	 * @return Returns the resultUserId.
	 */
	public String getResultUserId() {
		return resultUserId;
	}
	/**
	 * @param resultUserId The resultUserId to set.
	 */
	public void setResultUserId(String resultUserId) {
		this.resultUserId = resultUserId;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the partyEvent.
	 */
	public PartyResponseEvent getPartyEvent() {
		return partyEvent;
	}
	/**
	 * @param partyEvent The partyEvent to set.
	 */
	public void setPartyEvent(PartyResponseEvent partyEvent) {
		this.partyEvent = partyEvent;
	}
	
	/**
	 * @return Returns the positionEvent.
	 */
	public CSCDSupervisionStaffResponseEvent getPositionEvent() {
		return positionEvent;
	}
	/**
	 * @param positionEvent The positionEvent to set.
	 */
	public void setPositionEvent(CSCDSupervisionStaffResponseEvent positionEvent) {
		this.positionEvent = positionEvent;
	}
	
	/**
	 * @return Returns the outcomeDesc.
	 */
	public String getOutcomeDesc() {
		return outcomeDesc;
	}
	/**
	 * @param outcomeDesc The outcomeDesc to set.
	 */
	public void setOutcomeDesc(String outcomeDesc) {
		this.outcomeDesc = outcomeDesc;
	}
	/**
	 * @return Returns the eventTypeDesc.
	 */
	public String getEventTypeDesc() {
		return eventTypeDesc;
	}
	/**
	 * @param eventTypeDesc The eventTypeDesc to set.
	 */
	public void setEventTypeDesc(String eventTypeDesc) {
		this.eventTypeDesc = eventTypeDesc;
	}
}
