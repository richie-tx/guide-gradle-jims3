/*
 * Created on Mar 13, 2006
 *
 */
package mojo.km.notification;

import messaging.notification.domintf.INotificationIdentityAddressDef;
import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *
 */
public class NotificationIdentityAddressDef extends PersistentObject
{
	private String notificationDefId;
	private String beanName;
	private String property;
	private String context;
	private String transportType;
	private String transportDirection;
	private String value;

	public void update(INotificationIdentityAddressDef identityDef)
	{
		this.setTransportDirection(identityDef.getDirection());
		this.setBeanName(identityDef.getBeanName());
		this.setProperty(identityDef.getProperty());
		this.setContext(identityDef.getContext());
		this.setValue(identityDef.getValue());
		this.setTransportType(identityDef.getTransportType());
	}

	/**
	 * @return
	 */
	public String getProperty()
	{
		fetch();
		return property;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string)
	{
		if (this.property == null || !this.property.equals(string))
		{
			markModified();
		}
		this.property = string;
	}

	/**
	 * @return
	 */
	public String getBeanName()
	{
		fetch();
		return beanName;
	}

	/**
	 * @param string
	 */
	public void setBeanName(String string)
	{
		if (this.beanName == null || !this.beanName.equals(string))
		{
			markModified();
		}
		this.beanName = string;
	}

	/**
	 * @return
	 */
	public String getContext()
	{
		fetch();
		return context;
	}

	/**
	 * @param string
	 */
	public void setContext(String string)
	{
		if (this.context == null || !this.context.equals(string))
		{
			markModified();
		}
		this.context = string;
	}

	/**
	 * @return
	 */
	public String getTransportType()
	{
		fetch();
		return transportType;
	}

	/**
	 * @param string
	 */
	public void setTransportType(String string)
	{
		if (this.transportType == null || !this.transportType.equals(string))
		{
			markModified();
		}
		this.transportType = string;
	}

	/**
	 * @return
	 */
	public String getNotificationDefId()
	{
		fetch();
		return notificationDefId;
	}

	/**
	 * @param string
	 */
	public void setNotificationId(String string)
	{
		if (this.notificationDefId == null || !this.notificationDefId.equals(string))
		{
			markModified();
		}
		this.notificationDefId = string;
	}

	/**
	 * @return
	 */
	public String getTransportDirection()
	{
		fetch();
		return transportDirection;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		fetch();
		return value;
	}

	/**
	 * @param string
	 */
	public void setTransportDirection(String string)
	{
		if (this.transportDirection == null || !this.transportDirection.equals(string))
		{
			markModified();
		}
		this.transportDirection = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		if (this.value == null || !this.value.equals(string))
		{
			markModified();
		}
		this.value = string;
	}

	/**
	 * @param string
	 */
	public void setNotificationDefId(String string)
	{
		if (this.notificationDefId == null || !this.notificationDefId.equals(string))
		{
			markModified();
		}
		this.notificationDefId = string;
	}

	/**
	 * @param identityDefBean
	 */
	public void fill(INotificationIdentityAddressDef identityDefBean)
	{
		if(identityDefBean != null)
		{
			identityDefBean.setBeanName(this.getBeanName());
			identityDefBean.setContext(this.getContext());
			identityDefBean.setDirection(this.getTransportDirection());
			identityDefBean.setProperty(this.getProperty());
			identityDefBean.setValue(this.getValue());
			identityDefBean.setTransportType(this.getTransportType());
		}
	}

}
