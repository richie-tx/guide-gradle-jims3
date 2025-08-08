/*
 * Created on Nov 3, 2014
 */
package messaging.facility.reply;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 */
public class JuvenileFacilityDetailsResponseEvent extends ResponseEvent implements IAddressable {
	
	
	public String juvenileNum;

	public String supervisionNumber;
	
	//used for notification message to SP/JPO
	public String subject;

	public String identity;

	public String notificationMessage;
	

	/**
	 * @roseuid 46C1B357025B
	 */
	public JuvenileFacilityDetailsResponseEvent() {

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
	 * @param identity The identity to set.
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
	 * @param notificationMessage The notificationMessage to set.
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
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the referralId.
	 */
	
}
