/*
 * Create on May 24, 2006
 */

package messaging.administerserviceprovider.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * author ugopinath
 */

public class ServiceProviderErrorResponseEvent extends ResponseEvent
{
	private String message;
	private String userId;
	private String serviceName;
	private String locationName;
	
	
	/**
	 * @return
	 */

	public String getMessage()
	{
		return message;
	}
	
	/**
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	

	/**
	 * @return
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @return
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * @return
	 */
	public String getServiceName()
	{
		return serviceName;
	}

	/**
	 * @param string
	 */
	public void setLocationName(String string)
	{
		locationName = string;
	}

	/**
	 * @param string
	 */
	public void setServiceName(String string)
	{
		serviceName = string;
	}

}