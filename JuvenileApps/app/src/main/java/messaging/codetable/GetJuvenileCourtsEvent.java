/*
 * Created on Jul 15, 2005
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author jfisher
 *
 */
public class GetJuvenileCourtsEvent extends RequestEvent
{
	private String court;
	private String judgeName;

	/**
	 * @return
	 */
	public String getCourt()
	{
		return court;
	}

	/**
	 * @return
	 */
	public String getJudgeName()
	{
		return judgeName;
	}

	/**
	 * @param aCourt
	 */
	public void setCourt(String aCourt)
	{
		court = aCourt;
	}

	/**
	 * @param aJudgeName
	 */
	public void setJudgeName(String aJudgeName)
	{
		judgeName = aJudgeName;
	}
}
