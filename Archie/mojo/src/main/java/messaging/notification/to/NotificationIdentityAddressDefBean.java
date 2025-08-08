/*
 * Created on Mar 30, 2006
 *
 */
package messaging.notification.to;

import messaging.notification.domintf.INotificationIdentityAddressDef;

/**
 * @author Jim Fisher
 *
 */
public class NotificationIdentityAddressDefBean implements INotificationIdentityAddressDef 
{
	private String transportType;
	private String direction;
	private String beanName;
	private String property;
	private String value;
	private String context;
	
	/**
	 * @return
	 */
	public String getBeanName()
	{
		return beanName;
	}

	/**
	 * @return
	 */
	public String getDirection()
	{
		return direction;
	}

	/**
	 * @return
	 */
	public String getProperty()
	{
		return property;
	}

	/**
	 * @return
	 */
	public String getTransportType()
	{
		return transportType;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param string
	 */
	public void setBeanName(String string)
	{
		beanName = string;
	}

	/**
	 * @param string
	 */
	public void setDirection(String string)
	{
		direction = string;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string)
	{
		property = string;
	}

	/**
	 * @param string
	 */
	public void setTransportType(String string)
	{
		transportType = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		value = string;
	}

	/**
	 * @return
	 */
	public String getContext()
	{
		return context;
	}

	/**
	 * @param string
	 */
	public void setContext(String string)
	{
		context = string;
	}

}
