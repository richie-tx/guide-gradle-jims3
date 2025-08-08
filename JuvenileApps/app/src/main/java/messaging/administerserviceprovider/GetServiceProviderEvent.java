//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServiceLocationsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderEvent extends RequestEvent
{
	private String serviceProviderId;
	private String statusId;

	/**
	 * @roseuid 44805C9400DF
	 */
	public GetServiceProviderEvent()
	{

	}

	/**
	 * @return
	 */
	public String getServiceProviderId()
	{
		return serviceProviderId;
	}

	/**
	 * @param string
	 */
	public void setServiceProviderId(String string)
	{
		serviceProviderId = string;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

}