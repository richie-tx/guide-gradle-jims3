//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCurrentJuvenileDSTTraitsEvent extends RequestEvent
{
	public String juvenileNum;
	
	/**
	 * @roseuid 42A7336D0279
	 */
	public GetCurrentJuvenileDSTTraitsEvent()
	{

	}

	/**
	 * @param juvenileNum
	 * @roseuid 42A732AF03E1
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 42A732AF03E3
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}

}
