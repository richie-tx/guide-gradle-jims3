// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\notification\\SendCaseplanNotificationEventEvent.java

package messaging.notification;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.PersistentEvent;

public class SendNonComplianceSanctionDateExpiredNotificationEvent extends PersistentEvent implements IAddressable {
	public String juvenileNum;

	public String supervisionNumber;
	
	public String SanctionCompleteByDateStr;
	
	public String noticeId;
	
	public String subject;

	public String identity;

	public String notificationMessage;

	public String noticeTopic;
	
	public String identityType;

	//Testing to be deleted later

	private String statusCode;

	private String casefileId;

	/**
	 * @roseuid 46C1B35601B0
	 */
	public SendNonComplianceSanctionDateExpiredNotificationEvent() {
		
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
	 * @return Returns the statusCode.
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            The statusCode to set.
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see messaging.task.domintf.ICreateTask#addTaskStateItem(String,
	 *      Integer)
	 */
	public void addTaskStateItem(String key, Integer item) {
		// TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see messaging.task.domintf.ICreateTask#addTaskStateItem(String,
	 *      Date)
	 */
	public void addTaskStateItem(String key, Date item) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}

	/**
	 * @param casefileId
	 *            The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	/**
	 * @return Returns the noticeTopic.
	 */
	public String getNoticeTopic() {
		return noticeTopic;
	}
	/**
	 * @param noticeTopic The noticeTopic to set.
	 */
	public void setNoticeTopic(String noticeTopic) {
		this.noticeTopic = noticeTopic;
	}
	/**
	 * @return Returns the identityType.
	 */
	public String getIdentityType() {
		return identityType;
	}
	/**
	 * @param identityType The identityType to set.
	 */
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	/**
	 * @return the sanctionCompleteByDateStr
	 */
	public String getSanctionCompleteByDateStr() {
		return SanctionCompleteByDateStr;
	}
	/**
	 * @param sanctionCompleteByDateStr the sanctionCompleteByDateStr to set
	 */
	public void setSanctionCompleteByDateStr(String sanctionCompleteByDateStr) {
		SanctionCompleteByDateStr = sanctionCompleteByDateStr;
	}
	/**
	 * @return the noticeId
	 */
	public String getNoticeId() {
		return noticeId;
	}
	/**
	 * @param noticeId the noticeId to set
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
}