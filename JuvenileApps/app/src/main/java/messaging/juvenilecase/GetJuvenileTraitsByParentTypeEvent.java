//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitsByTypeEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileTraitsByParentTypeEvent extends RequestEvent
{
	private String traitType;
	private String juvenileNum;

	/**
	 * @roseuid 42A733740102
	 */
	public GetJuvenileTraitsByParentTypeEvent()
	{

	}
	/**
	 * @return
	 */
	public String getTraitType()
	{
		return traitType;
	}

	/**
	 * @param string
	 */
	public void setTraitType(String string)
	{
		traitType = string;
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
