/*
 * Created on Dec 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.notification;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.PersistentEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotificationMessage extends PersistentEvent implements IAddressable {
	
	public String subject;

	public String identity;

	public String notificationMessage;
	
	public NotificationMessage() {
		
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
}
