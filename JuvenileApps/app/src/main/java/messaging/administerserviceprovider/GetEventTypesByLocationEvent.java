//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetEventTypesByLocationEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetEventTypesByLocationEvent extends RequestEvent
{
	public String locationId;

	/**
	 * @roseuid 44805C92017C
	 */
	public GetEventTypesByLocationEvent()
	{

	}

	/**
	 * Access method for the serviceLocationId property.
	 * 
	 * @return   the current value of the serviceLocationId property
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * Sets the value of the serviceLocationId property.
	 * 
	 * @param aServiceLocationId the new value of the serviceLocationId property
	 */
	public void setLocationId(String aLocationId)
	{
		locationId = aLocationId;
	}
}
