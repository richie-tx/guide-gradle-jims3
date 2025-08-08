/*
 * Created on Mar 30, 2006
 *
 */
package mojo.km.notification.transactions;

import java.util.Iterator;

import messaging.notification.UpdateNotificationDefEvent;
import messaging.notification.domintf.INotificationIdentityAddressDef;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.notification.NotificationDef;
import mojo.km.notification.NotificationIdentityAddressDef;
import mojo.km.reporting.text.TextReportTemplate;

/**
 * @author Jim Fisher
 *
 */
public class UpdateNotificationDefCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		UpdateNotificationDefEvent event = (UpdateNotificationDefEvent) anEvent;

		NotificationDef notifDef = this.createNotificationDef(event);

		this.clearIdentityAddresses(notifDef);

		Iterator i = event.getIdentityAddressDefs();

		if (i == null)
		{
			this.throwNoIdentitiesDefined(event);
		}
		else if (event.getTemplate() == null || event.getTemplate().trim().equals(""))
		{
			this.throwNoTemplateDefined(event);
		}

		while (i.hasNext())
		{
			INotificationIdentityAddressDef identityDefBean = (INotificationIdentityAddressDef) i.next();
			NotificationIdentityAddressDef identityDef = new NotificationIdentityAddressDef();
			identityDef.update(identityDefBean);
			notifDef.insertIdentityDefs(identityDef);
		}

		TextReportTemplate template = TextReportTemplate.findByName(event.getNotificationTopic());
		if (template == null)
		{
			template = new TextReportTemplate();
			template.setName(event.getNotificationTopic());
		}
		template.setTemplate(event.getTemplate().trim());

	}

	/**
	 * @param event
	 */
	private void throwNoTemplateDefined(UpdateNotificationDefEvent event)
	{
		throw new IllegalArgumentException(
			"Notification definition: " + event.getNotificationTopic() + " has no template defined.");
	}

	/**
	 * @param event
	 */
	private void throwNoIdentitiesDefined(UpdateNotificationDefEvent event)
	{
		throw new IllegalArgumentException(
			"Notification definition: " + event.getNotificationTopic() + " has no identity definitions.");
	}

	private NotificationDef createNotificationDef(UpdateNotificationDefEvent event)
	{
		NotificationDef notifDef = NotificationDef.findByTopic(event.getNotificationTopic());

		if (notifDef == null)
		{
			notifDef = new NotificationDef();
		}

		notifDef.setTopic(event.getNotificationTopic());
		notifDef.setDefaultSubject(event.getDefaultSubject());
		notifDef.setSeverityLevel(event.getDefaultServerity());

		return notifDef;
	}

	private void clearIdentityAddresses(NotificationDef notifDef)
	{
		Iterator i = notifDef.getIdentityDefs().iterator();
		while (i.hasNext())
		{
			NotificationIdentityAddressDef identityDef = (NotificationIdentityAddressDef) i.next();
			identityDef.delete();
		}
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
