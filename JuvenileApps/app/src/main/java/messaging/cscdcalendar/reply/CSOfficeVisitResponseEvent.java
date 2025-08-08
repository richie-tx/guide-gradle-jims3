//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\ResponseEvents\\CSOfficeVisitResponseEvent.java

package messaging.cscdcalendar.reply;

import java.util.Date;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.ResponseEvent;


public class CSOfficeVisitResponseEvent  extends ResponseEvent implements Comparable{
   
	private Date eventDate;
	private String eventName;
	private String startTime;
	private String endTime;	
	private String outcome;	
	private String purpose;
	private String narrative;	
	private String eventType;
	private String eventTypeDesc;
	private String superviseeId;	
	private String outcomeDesc;	
	private String positionId;
	private String eventId;
	private String status;	
	private String phonenum;
	private String rescheduleReason;
	private String resultUserId;
	private String resultPositionId;
	private PartyResponseEvent partyEvent;
	private CSCDSupervisionStaffResponseEvent positionEvent;
   
	/**
	 * @roseuid 47A236700164
	 */
	public CSOfficeVisitResponseEvent() {
    
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
	 * 
	 * @return
	 */
	public String getEventTypeDesc() {
		return eventTypeDesc;
	}
	/**
	 * 
	 * @param eventTypeDesc
	 */
	public void setEventTypeDesc(String eventTypeDesc) {
		this.eventTypeDesc = eventTypeDesc;
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
	 * @return the rescheduleReason
	 */
	public String getRescheduleReason() {
		return rescheduleReason;
	}
	/**
	 * @param rescheduleReason the rescheduleReason to set
	 */
	public void setRescheduleReason(String rescheduleReason) {
		this.rescheduleReason = rescheduleReason;
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
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	
	public int compareTo(Object o) {

        int result = 0;

        if(o != null && o instanceof CSOfficeVisitResponseEvent){

              CSOfficeVisitResponseEvent cso = (CSOfficeVisitResponseEvent)o;

              if(   cso.getPartyEvent() != null &&

                    this.partyEvent != null &&

                    cso.getPartyEvent().getLastName() != null &&

                    this.partyEvent.getLastName()!= null &&

                    cso.getPartyEvent().getFirstName() != null &&

                    this.partyEvent.getFirstName() != null){

                    

                    result = partyEvent.getLastName().compareTo(cso.partyEvent.getLastName());

                    if(result == 0){

                          result = partyEvent.getFirstName().compareTo(cso.partyEvent.getFirstName());

                    }

              }

        }

        return result;

  }





}



