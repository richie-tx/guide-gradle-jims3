//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefilesEvent extends RequestEvent
{
	public String juvenileNum;

	/**
	 * @roseuid 42B855410388
	 */
	public GetJuvenileCasefilesEvent()
	{

	}

	/**
	 * @param juvenileNum
	 * @roseuid 42A70E2E006C
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 42A70E2E006E
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}
}
