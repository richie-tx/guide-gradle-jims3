//Source file: C:\\views\\dev\\app\\src\\messaging\\referral\\GetJuvenileTraitsByTypeEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetTransferredOffenseReferralsEvent extends RequestEvent
{
	private String juvenileNum;

	/**
	 * @roseuid 42A733740102
	 */
	public GetTransferredOffenseReferralsEvent()
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