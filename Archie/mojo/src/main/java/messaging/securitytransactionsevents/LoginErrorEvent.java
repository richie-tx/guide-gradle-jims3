package messaging.securitytransactionsevents;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * Responsible for housing data that will be sent to control command LoginCommand
 *@author Design detail addin
 *@version 1.0
 */
public class LoginErrorEvent extends RequestEvent {
    private String message;

	public LoginErrorEvent() { }

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		message = string;
	}

}
