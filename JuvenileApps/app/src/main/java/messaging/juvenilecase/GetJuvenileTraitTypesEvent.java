//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitTypesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileTraitTypesEvent extends RequestEvent
{
	public String traitType;
	public boolean isFacility;
	
	/**
	 * @roseuid 42B063C000F2
	 */
	public GetJuvenileTraitTypesEvent()
	{

	}

	/**
	 * @param juvenileTraitType
	 * @roseuid 42B03B360017
	 */
	public void setTraitType(String traitType)
	{
		this.traitType = traitType;
	}

	/**
	 * @return String
	 * @roseuid 42B03B360019
	 */
	public String getTraitType()
	{
		return this.traitType;
	}
	
	public boolean isFacility() {
		return isFacility;
	}

	public void setFacility(boolean isFacility) {
		this.isFacility = isFacility;
	}
}
