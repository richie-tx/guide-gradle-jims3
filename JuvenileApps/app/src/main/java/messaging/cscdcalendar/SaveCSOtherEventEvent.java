//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\SaveCSOtherEventEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class SaveCSOtherEventEvent extends RequestEvent {
   
	private String eventType;
	private String eventName;
	private String startTime;
	private String endTime;
	private String superviseeId;
	private String outcome;
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
	private String createdBy;
	private Date markedForDeleteOn;
	private String context;
	private boolean create;
	private boolean update;
	private boolean reschedule;
	private boolean results;
	private boolean delete;
	private String rescheduleEventId;
	private String deleteEventId;
   
	/**
	 * @roseuid 479A0E21025E
	 */
	public SaveCSOtherEventEvent() {
    
	}
	
	
	/**
	 * @return Returns the create.
	 */
	public boolean isCreate() {
		return create;
	}
	/**
	 * @param create The create to set.
	 */
	public void setCreate(boolean create) {
		this.create = create;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the deleteEventId.
	 */
	public String getDeleteEventId() {
		return deleteEventId;
	}
	/**
	 * @param deleteEventId The deleteEventId to set.
	 */
	public void setDeleteEventId(String deleteEventId) {
		this.deleteEventId = deleteEventId;
	}	
	/**
	 * @return Returns the reschedule.
	 */
	public boolean isReschedule() {
		return reschedule;
	}
	/**
	 * @param reschedule The reschedule to set.
	 */
	public void setReschedule(boolean reschedule) {
		this.reschedule = reschedule;
	}
	/**
	 * @return Returns the rescheduleEventId.
	 */
	public String getRescheduleEventId() {
		return rescheduleEventId;
	}
	/**
	 * @param rescheduleEventId The rescheduleEventId to set.
	 */
	public void setRescheduleEventId(String rescheduleEventId) {
		this.rescheduleEventId = rescheduleEventId;
	}
	/**
	 * @return Returns the results.
	 */
	public boolean isResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(boolean results) {
		this.results = results;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	
	
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	 * @return Returns the markedForDeleteOn.
	 */
	public Date getMarkedForDeleteOn() {
		return markedForDeleteOn;
	}
	/**
	 * @param markedForDeleteOn The markedForDeleteOn to set.
	 */
	public void setMarkedForDeleteOn(Date markedForDeleteOn) {
		this.markedForDeleteOn = markedForDeleteOn;
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
	
	/**
	 * @return Returns the context.
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @param context The context to set.
	 */
	public void setContext(String context) {
		this.context = context;
	}
}
