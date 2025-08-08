/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import java.sql.Timestamp;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NonComplianceEventResponseEvent extends ResponseEvent 
{
	private Timestamp dateTime;
    private String details;
    private String nonComplianceEventId;
    private String newEventType;
    private String eventTypes;
    private String eventTypesId;    
	private String ncResponseId; // this is for violation report only
    private boolean manualAdded;
    private Date occurenceDate;
    
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the newEventType.
	 */
	public String getNewEventType() {
		return newEventType;
	}
	/**
	 * @param newEventType The newEventType to set.
	 */
	public void setNewEventType(String newEventType) {
		this.newEventType = newEventType;
	}
        
	/**
	 * @return Returns the dateTime.
	 */
	public Timestamp getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime The dateTime to set.
	 */
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * @return Returns the nonComplianceEventId.
	 */
	public String getNonComplianceEventId() {
		return nonComplianceEventId;
	}
	
	/**
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(String nonComplianceEventId) {
		this.nonComplianceEventId = nonComplianceEventId;
	}	
		
	/**
	 * @return Returns the eventTypes.
	 */
	public String getEventTypes() {
		return eventTypes;
	}
	/**
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(String eventTypes) {
		this.eventTypes = eventTypes;
	}
	public Date getOccurenceDate() {
		long date = this.getDateTime().getTime();
		return new Date(date);
	}
	
	public String getEventTypesId() {
		return eventTypesId;
	}
	
	public void setEventTypesId(String eventTypesId) {
		this.eventTypesId = eventTypesId;
	}
}
