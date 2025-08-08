/*
 * Created on Apr 12, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import naming.PDJuvenileCaseConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cshimek
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CheckRiskAnalysisOneHourResponseEvent extends ResponseEvent
{
	private boolean message;
	
	/**
	 * 
	 */
	public CheckRiskAnalysisOneHourResponseEvent()
	{
		super();
		this.setTopic(PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);
	}

	/**
	 * @return the message
	 */
	public boolean isMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(boolean message) {
		this.message = message;
	}
}