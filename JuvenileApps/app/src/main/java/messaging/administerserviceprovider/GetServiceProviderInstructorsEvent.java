//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderInstructorsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderInstructorsEvent extends RequestEvent
{
	private String serviceProviderId;
	private boolean isInHouse;

	/**
	 * @roseuid 44805C9301F8
	 */
	public GetServiceProviderInstructorsEvent()
	{

	}

	/**
	 * Access method for the serviceProviderId property.
	 * 
	 * @return   the current value of the serviceProviderId property
	 */
	public String getServiceProviderId()
	{
		return serviceProviderId;
	}

	/**
	 * Sets the value of the serviceProviderId property.
	 * 
	 * @param aServiceProviderId the new value of the serviceProviderId property
	 */
	public void setServiceProviderId(String aServiceProviderId)
	{
		serviceProviderId = aServiceProviderId;
	}

	/**
	 * @return
	 */
	public boolean isInHouse()
	{
		return isInHouse;
	}

	/**
	 * @param b
	 */
	public void setInHouse(boolean b)
	{
		isInHouse = b;
	}

}