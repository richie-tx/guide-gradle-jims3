/*
 * Created on Sep 29, 2005
 *
 */
package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class SuggestedOrderConditionRequestEvent extends RequestEvent
{
	private String conditionId;
	private String seqNum;
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getSeqNum()
	{
		return seqNum;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @param string
	 */
	public void setSeqNum(String string)
	{
		seqNum = string;
	}

}
