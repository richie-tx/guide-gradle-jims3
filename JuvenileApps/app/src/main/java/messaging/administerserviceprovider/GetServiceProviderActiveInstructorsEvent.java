//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderInstructorsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderActiveInstructorsEvent extends RequestEvent
{
	private String serviceProviderId;
	
	/**
	 * @roseuid 44805C9301F8
	 */
	public GetServiceProviderActiveInstructorsEvent()
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

}