// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\notification\\SendCasefileClosingNotificationEvent.java

package messaging.notification;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.PersistentEvent;

public class SendCasefileClosingNotificationEvent extends PersistentEvent implements IAddressable {
	public String juvenileNum;

	public String supervisionNumber;

	public String subject;

	public String identity;

	public String notificationMessage;

	public String referralId;

	public String supervisionType;

	public String noticeTopic;
	
	public String identityType;
	
	public Date supervisionExpectedEndDate;

	/**
	 * @roseuid 46C1B35600C5
	 */
	public SendCasefileClosingNotificationEvent() {

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
	 * @return Returns the referralId.
	 */
	public String getReferralId() {
		return referralId;
	}

	/**
	 * @param referralId
	 *            The referralId to set.
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
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
	 * @return Returns the supervisionType.
	 */
	public String getSupervisionType() {
		return supervisionType;
	}

	/**
	 * @param supervisionType
	 *            The supervisionType to set.
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
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

	public Date getSupervisionExpectedEndDate() {
		return supervisionExpectedEndDate;
	}

	public void setSupervisionExpectedEndDate(Date supervisionExpectedEndDate) {
		this.supervisionExpectedEndDate = supervisionExpectedEndDate;
	}
}
