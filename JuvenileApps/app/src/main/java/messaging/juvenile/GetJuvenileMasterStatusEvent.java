//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenileAbuseListEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileMasterStatusEvent extends RequestEvent
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	public String juvenileNum;
	/**
	 * @roseuid 42B1967F0157
	 */
	public GetJuvenileMasterStatusEvent()
	{

	}

	/**
	 * @param juvenileNum
	 * @roseuid 42B18B40014B
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 42B18B400157
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}
}
