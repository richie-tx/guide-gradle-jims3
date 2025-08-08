/*
 * Created on May 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.mentalhealth.reply;

import naming.PDJuvenileCaseConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NoMAYSIRecordResponseEvent extends ResponseEvent
{
	private String message;

	/**
	 * 
	 */
	public NoMAYSIRecordResponseEvent()
	{
		super();
		this.setTopic(PDJuvenileCaseConstants.NO_MAYSI_RECORD_TOPIC);
	}

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
