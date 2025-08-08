//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\SaveCSGroupOfficeVisitEvent.java

package messaging.cscdcalendar;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class SaveCSGroupOfficeVisitEvent extends RequestEvent {   
	
	private List eventIds;
	private List superviseeIds;	
	private Date eventDate;
	private String eventName;
	private String startTime;
	private String endTime;	
	private String outcome;	
	private String purpose;
	private String narrative;	
	private String eventType;	
	private String positionId;	
	private String status;	
	private String phonenum;
	private String createdBy;
	private String rescheduleReason;
	private String resultUserId;
	private String resultPositionId;
	private boolean create;
	private boolean update;
	private boolean addAttendees;
	private boolean reschedule;
	private boolean results;
	private boolean delete;
   
	/**
	 * @roseuid 479A0E210154
	 */
	public SaveCSGroupOfficeVisitEvent() {
    
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
	 * @return Returns the addAttendees.
	 */
	public boolean isAddAttendees() {
		return addAttendees;
	}
	/**
	 * @param addAttendees The addAttendees to set.
	 */
	public void setAddAttendees(boolean addAttendees) {
		this.addAttendees = addAttendees;
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
	 * @return Returns the eventIds.
	 */
	public List getEventIds() {
		return eventIds;
	}
	/**
	 * @param eventIds The eventIds to set.
	 */
	public void setEventIds(List eventIds) {
		this.eventIds = eventIds;
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
	 * @return Returns the superviseeIds.
	 */
	public List getSuperviseeIds() {
		return superviseeIds;
	}
	/**
	 * @param superviseeIds The superviseeIds to set.
	 */
	public void setSuperviseeIds(List superviseeIds) {
		this.superviseeIds = superviseeIds;
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
