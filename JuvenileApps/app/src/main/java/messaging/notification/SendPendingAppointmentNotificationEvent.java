// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\notification\\SendPendingAppointmentNotificationEvent.java

package messaging.notification;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.PersistentEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendPendingAppointmentNotificationEvent extends PersistentEvent implements IAddressable {
	public String juvenileNum;

	public String supervisionNumber;

	public String subject;

	public String identity;

	public String notificationMessage;

	public String referralId;

	public String supervisionType;

	/**
	 * @roseuid 46C1B3570161
	 */
	public SendPendingAppointmentNotificationEvent() {

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
}
