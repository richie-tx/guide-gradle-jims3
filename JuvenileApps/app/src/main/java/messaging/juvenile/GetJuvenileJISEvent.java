//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenileGEDProgramEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileJISEvent extends RequestEvent
{
	public String juvenileNum;

	/**
	 * @roseuid 42B196870000
	 */
	public GetJuvenileJISEvent()
	{

	}

	/**
	 * @param juvenileNum
	 * @roseuid 42B18B3E034E
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 42B18B3E035B
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}
}
