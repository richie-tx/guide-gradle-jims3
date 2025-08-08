/*
 * Created on Mar 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.interviewinfo.reply;

import messaging.error.reply.ErrorResponseEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MissingConstellationResponseEvent extends ErrorResponseEvent{
	
	public MissingConstellationResponseEvent()
	{
		this.setMessage("error.missingConstellation");
	}
}
