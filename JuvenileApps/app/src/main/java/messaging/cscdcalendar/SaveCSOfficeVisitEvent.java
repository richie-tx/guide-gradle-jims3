//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\SaveCSOfficeVisitEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class SaveCSOfficeVisitEvent extends RequestEvent {
	
	private Date eventDate;
	private String assignStaffPos_Id;
	private String eventName;
	private String startTime;
	private String endTime;	
	private String outcome;	
	private String purpose;
	private String narrative;	
	private String eventType;	
	private String createdBy;
	private String superviseeId;	
	private String positionId;
	private String eventId;
	private String status;	
	private String phonenum;
	private String rescheduleReason;
	private String resultUserId;
	private String resultPositionId;
	private boolean create;
	private boolean update;
	private boolean reschedule;
	private boolean results;
	private boolean delete;
	private String rescheduleOVId;
	private String deleteOVId;
   
	/**
	 * @roseuid 479A0E2101B2
	 */
	public SaveCSOfficeVisitEvent() {
    
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
	 * @return Returns the deleteOVId.
	 */
	public String getDeleteOVId() {
		return deleteOVId;
	}
	/**
	 * @param deleteOVId The deleteOVId to set.
	 */
	public void setDeleteOVId(String deleteOVId) {
		this.deleteOVId = deleteOVId;
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
	 * @return Returns the rescheduleOVId.
	 */
	public String getRescheduleOVId() {
		return rescheduleOVId;
	}
	/**
	 * @param rescheduleOVId The rescheduleOVId to set.
	 */
	public void setRescheduleOVId(String rescheduleOVId) {
		this.rescheduleOVId = rescheduleOVId;
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
	 * @return the assignStaffPos_Id
	 */
	public String getAssignStaffPos_Id() {
		return assignStaffPos_Id;
	}
	/**
	 * @param assignStaffPos_Id the assignStaffPos_Id to set
	 */
	public void setAssignStaffPos_Id(String assignStaffPos_Id) {
		this.assignStaffPos_Id = assignStaffPos_Id;
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
}
