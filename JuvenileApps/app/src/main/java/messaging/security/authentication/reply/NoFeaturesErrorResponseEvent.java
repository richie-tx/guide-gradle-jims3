/*
 * Created on Feb 27, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.authentication.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NoFeaturesErrorResponseEvent extends ResponseEvent
{
	public String message;

	/**
	 * @return
	 */

	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
}
