//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitsByTypeEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileTraitsByJuvenileIdEvent extends RequestEvent
{
	private String juvenileNum;

	/**
	 * @roseuid 42A733740102
	 */
	public GetJuvenileTraitsByJuvenileIdEvent()
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
