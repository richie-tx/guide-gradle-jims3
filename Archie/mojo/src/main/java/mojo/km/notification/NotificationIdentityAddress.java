/*
 * Created on Mar 10, 2006
 *
 */
package mojo.km.notification;

import mojo.km.identityaddress.IdentityAddress;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *
 */
public class NotificationIdentityAddress extends PersistentObject
{
	private String identityId;

	/**
	* Properties for identityAddress
	* @referencedType mojo.km.identityaddress.IdentityAddress
	* @detailerDoNotGenerate true
	*/
	private IdentityAddress identityAddress;

	private String status;
	private String context;
	private String transportDirection;
	private String transportType;
	private String notificationId;
	private Notification notification;

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
	public String getContext()
	{
		fetch();
		return context;
	}

	/**
	 * @return
	 */
	public String getIdentityId()
	{
		fetch();
		return identityId;
	}

	/**
	* Gets referenced type pd.contact.user.UserProfile
	* @return UserProfile releaseDecisionUser
	*/
	public IdentityAddress getIdentityAddress()
	{
		fetch();
		this.initIdentityAddress();
		return this.identityAddress;
	}

	/**
	 * 
	 */
	private void initIdentityAddress()
	{
		if (this.identityAddress == null)
		{
			identityAddress =
				(IdentityAddress) new mojo.km.persistence.Reference(identityId, IdentityAddress.class).getObject();
		}
	}

	public IdentityAddress getChild()
	{
		return this.getIdentityAddress();
	}

	public String getChildId()
	{
		return this.getIdentityId();
	}

	public void setChild(IdentityAddress anIdentityAddress)
	{
		this.setIdentityAddress(anIdentityAddress);
	}

	public void setChildId(String identityId)
	{
		this.setIdentityId(identityId);
	}

	public void setParent(Notification aNotification)
	{
		if (this.notification == null || !this.notification.equals(aNotification))
		{
			markModified();
		}
		if (aNotification.getOID() == null)
		{
			new Home().bind(aNotification);
		}
		setParentId("" + aNotification.getOID());
		this.notification = (Notification) new mojo.km.persistence.Reference(aNotification).getObject();
	}

	public void setParentId(String aNotificationId)
	{
		if (this.notificationId == null || !this.notificationId.equals(aNotificationId))
		{
			markModified();
		}
		this.notification = null;
		this.notificationId = aNotificationId;
	}

	public Notification getParent()
	{
		fetch();
		return this.notification;
	}

	private void setIdentityAddress(IdentityAddress anIdentityAddress)
	{
		if (this.identityAddress == null || !this.identityAddress.equals(anIdentityAddress))
		{
			markModified();
		}
		if (anIdentityAddress.getOID() == null)
		{
			new Home().bind(anIdentityAddress);
		}
		this.setIdentityId("" + anIdentityAddress.getOID());
		this.identityAddress = (IdentityAddress) new mojo.km.persistence.Reference(anIdentityAddress).getObject();
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		fetch();
		return status;
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
	 * @param string
	 */
	public void setContext(String string)
	{
		if (this.context == null || !this.context.equals(string))
		{
			markModified();
		}
		context = string;
	}

	/**
	 * @param string
	 */
	public void setIdentityId(String string)
	{
		if (this.identityId == null || !this.identityId.equals(string))
		{
			markModified();
		}
		identityId = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		if (this.status == null || !this.status.equals(string))
		{
			markModified();
		}
		status = string;
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
		transportDirection = string;
	}

	public String getParentId()
	{
		fetch();
		return this.notificationId;
	}

	/**
	 * @return
	 */
	public String getNotificationId()
	{
		return this.getParentId();
	}

	/**
	 * @param string
	 */
	public void setNotificationId(String string)
	{
		this.setParentId(string);
	}

	/**
	 * @param oid
	 * @return
	 */
	public static NotificationIdentityAddress find(String oid)
	{
		IHome home = new Home();		
		return (NotificationIdentityAddress) home.find(oid, NotificationIdentityAddress.class);
	}

}
