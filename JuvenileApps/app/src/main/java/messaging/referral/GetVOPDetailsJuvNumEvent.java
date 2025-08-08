//Source file: C:\\views\\dev\\app\\src\\messaging\\referral\\GetJuvenileTraitsByTypeEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetVOPDetailsJuvNumEvent extends RequestEvent
{
	private String juvenileNum;

	/**
	 * @roseuid 42A733740102
	 */
	public GetVOPDetailsJuvNumEvent()
	{

	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}
}