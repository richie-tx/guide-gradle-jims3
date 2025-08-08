/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProgressRiskRecommendationResponseEvent extends RiskRecommendationResponseEvent
{
	private int totalSupervisionRules;

	
	/**
	 * @return
	 */
	public int getTotalSupervisionRules()
	{
		return totalSupervisionRules;
	}

	/**
	 * @param i
	 */
	public void setTotalSupervisionRules(int i)
	{
		totalSupervisionRules = i;
	}

}
