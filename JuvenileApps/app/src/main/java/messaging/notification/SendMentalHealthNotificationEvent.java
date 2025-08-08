// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\notification\\SendMentalHealthNotificationEvent.java

package messaging.notification;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.PersistentEvent;

public class SendMentalHealthNotificationEvent extends PersistentEvent implements IAddressable {
	public String juvenileNum;

	public String supervisionNumber;

	//used for notification message to MAYSI requestor
	public String subject;

	public String identity;

	public String notificationMessage;

	public String maysiId;

	//Added

	public String locationUnitId;

	public String lengthOfStayId;

	public String facilityTypeId;

	//Added
	public String noticeTopic;

	public String alertType;

	public String taskTopic;
	
	public String maysiAssessId;
	
	public String officerId;
	
	public String taskSubject;

	/**
	 * @roseuid 46C1B35602AA
	 */
	public SendMentalHealthNotificationEvent() {

	}

	/**
	 * Access method for the juvenileNum property.
	 * 
	 * @return the current value of the juvenileNum property
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * Sets the value of the juvenileNum property.
	 * 
	 * @param aJuvenileNum
	 *            the new value of the juvenileNum property
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		juvenileNum = aJuvenileNum;
	}

	/**
	 * Access method for the supervisionNumber property.
	 * 
	 * @return the current value of the supervisionNumber property
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}

	/**
	 * Sets the value of the supervisionNumber property.
	 * 
	 * @param aSupervisionNumber
	 *            the new value of the supervisionNumber property
	 */
	public void setSupervisionNumber(String aSupervisionNumber) {
		supervisionNumber = aSupervisionNumber;
	}

	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return Returns the maysiId.
	 */
	public String getMaysiId() {
		return maysiId;
	}

	/**
	 * @param maysiId
	 *            The maysiId to set.
	 */
	public void setMaysiId(String maysiId) {
		this.maysiId = maysiId;
	}

	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}

	/**
	 * @param notificationMessage
	 *            The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the facilityTypeId.
	 */
	public String getFacilityTypeId() {
		return facilityTypeId;
	}

	/**
	 * @param facilityTypeId
	 *            The facilityTypeId to set.
	 */
	public void setFacilityTypeId(String facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}

	/**
	 * @return Returns the lengthOfStayId.
	 */
	public String getLengthOfStayId() {
		return lengthOfStayId;
	}

	/**
	 * @param lengthOfStayId
	 *            The lengthOfStayId to set.
	 */
	public void setLengthOfStayId(String lengthOfStayId) {
		this.lengthOfStayId = lengthOfStayId;
	}

	/**
	 * @return Returns the locationUnitId.
	 */
	public String getLocationUnitId() {
		return locationUnitId;
	}

	/**
	 * @param locationUnitId
	 *            The locationUnitId to set.
	 */
	public void setLocationUnitId(String locationUnitId) {
		this.locationUnitId = locationUnitId;
	}

	/**
	 * @return Returns the alertType.
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType
	 *            The alertType to set.
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return Returns the noticeTopic.
	 */
	public String getNoticeTopic() {
		return noticeTopic;
	}

	/**
	 * @param noticeTopic
	 *            The noticeTopic to set.
	 */
	public void setNoticeTopic(String noticeTopic) {
		this.noticeTopic = noticeTopic;
	}

	/**
	 * @return Returns the taskTopic.
	 */
	public String getTaskTopic() {
		return taskTopic;
	}

	/**
	 * @param taskTopic
	 *            The taskTopic to set.
	 */
	public void setTaskTopic(String taskTopic) {
		this.taskTopic = taskTopic;
	}
	/**
	 * @return Returns the maysiAssessId.
	 */
	public String getMaysiAssessId() {
		return maysiAssessId;
	}
	/**
	 * @param maysiAssessId The maysiAssessId to set.
	 */
	public void setMaysiAssessId(String maysiAssessId) {
		this.maysiAssessId = maysiAssessId;
	}
	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId() {
		return officerId;
	}
	/**
	 * @param officerId The officerId to set.
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	/**
	 * @return Returns the taskSubject.
	 */
	public String getTaskSubject() {
		return taskSubject;
	}
	/**
	 * @param taskSubject The taskSubject to set.
	 */
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
}
