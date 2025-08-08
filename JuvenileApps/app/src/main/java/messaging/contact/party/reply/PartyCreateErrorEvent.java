/*
 * Created on Feb 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.party.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PartyCreateErrorEvent extends ResponseEvent
{
	public PartyCreateErrorEvent()
	{
	}
	private String message;

	/**
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string)
	{
		message = string;
	}

}
