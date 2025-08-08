package messaging.juvenilecase.reply;

import naming.PDJuvenileCaseConstants;
import mojo.km.messaging.ResponseEvent;

public class GangRiskPreConditionFailedResponseEvent extends ResponseEvent{
	private String message;
	
	/**
	 * 
	 */
	public GangRiskPreConditionFailedResponseEvent()
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
