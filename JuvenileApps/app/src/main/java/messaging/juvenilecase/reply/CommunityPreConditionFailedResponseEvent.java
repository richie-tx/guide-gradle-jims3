/*
 * Created on Oct 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import naming.PDJuvenileCaseConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommunityPreConditionFailedResponseEvent extends ResponseEvent
{
	private String message;
	
	/**
	 * 
	 */
	public CommunityPreConditionFailedResponseEvent()
	{
		super();
		this.setTopic(PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);
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
	public void setMessage(final String string)
	{
		message = string;
	}

}
