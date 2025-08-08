/*
 * Created on Apr 5, 2006
 *
 */
package mojo.km.notification.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.notification.UpdateNotificationDestinationsEvent;
import messaging.notification.domintf.INotificationIdentityAddressDef;
import messaging.notification.to.NotificationIdentityAddressDefBean;
import mojo.km.context.ICommand;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;
import mojo.km.notification.Notification;
import mojo.km.notification.NotificationDef;
import mojo.km.notification.NotificationIdentityAddress;
import mojo.km.notification.NotificationIdentityAddressDef;
import mojo.km.utilities.Reflection;
import mojo.naming.IdentityAddressConstants;

/**
 * @author Jim Fisher
 *
 * Facilitates the addition of recipients to a notification that is not
 * contained in the notification definition
 */
public class UpdateNotificationDestinationsCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		UpdateNotificationDestinationsEvent event = (UpdateNotificationDestinationsEvent) anEvent;

		Notification notification = Notification.find(event.getNotificationId());

		if (notification == null)
		{
			this.throwNotificationNotFound(event);
		}
		else
		{
			Map identityDefMap = this.buildIdentityDefMap(notification);
			Map contextMap = this.buildContextMap(notification);

			Iterator i = event.getDestinationKeys();
			while (i.hasNext())
			{
				String context = (String) i.next();

				INotificationIdentityAddressDef identityDef =
					(INotificationIdentityAddressDef) identityDefMap.get(context);

				NotificationIdentityAddress identityAddress = (NotificationIdentityAddress) contextMap.get(context);

				String value = this.resolveIdentityBean(event.getDestination(context), identityDef);

				IdentityAddress identity =
					IdentityAddress.identityFactory(value, IdentityAddressConstants.IDENTITY_INDIVIDUAL);

				if (identityAddress != null)
				{
					identityAddress.setChild(identity);
				}
				else
				{
					notification.insertIdentities(identity, identityDef);
				}
			}
		}
	}

	private String resolveIdentityBean(Object identityBean, INotificationIdentityAddressDef identityDef)
	{
		String value = null;
		if (identityBean != null)
		{
			Object beanValue = Reflection.invokeAccessorMethod(identityBean, identityDef.getProperty());
			if (beanValue != null)
			{
				value = beanValue.toString();
			}
		}
		return value;
	}

	/**
	 * @param notifDef
	 * @return
	 */
	private Map buildIdentityDefMap(Notification notif)
	{
		NotificationDef notifDef = notif.getNotificationDef();

		Map identityDefMap = new HashMap();

		// Create lookup of Notification Identity Address Definitions based on context
		Iterator i = notifDef.getIdentityDefs().iterator();
		while (i.hasNext())
		{
			NotificationIdentityAddressDef identityDef = (NotificationIdentityAddressDef) i.next();
			INotificationIdentityAddressDef identityDefBean = new NotificationIdentityAddressDefBean();
			identityDef.fill(identityDefBean);
			identityDefMap.put(identityDefBean.getContext(), identityDefBean);
		}
		return identityDefMap;
	}

	/**
	 * @param notification
	 * @return
	 */
	private Map buildContextMap(Notification notification)
	{
		Map contextMap = new HashMap();
		Iterator i = notification.getIdentities().iterator();

		while (i.hasNext())
		{
			NotificationIdentityAddress identityAddress = (NotificationIdentityAddress) i.next();
			if (identityAddress.getContext() != null)
			{
				contextMap.put(identityAddress.getContext(), identityAddress);
			}
		}

		return contextMap;
	}

	/**
	 * @param notification
	 */
	private void throwIdentityNotDefined(Notification notification)
	{
		String msg =
			"Notification (id=" + notification.getOID() + ") for recipient update has an invalid recipient value.";
		throw new IllegalArgumentException(msg);
	}

	/**
	 * @param event
	 */
	private void throwNotificationNotFound(UpdateNotificationDestinationsEvent event)
	{
		String msg = "Notification (id=" + event.getNotificationId() + ") for recipient update not found.";
		throw new IllegalArgumentException(msg);
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}
