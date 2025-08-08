/*
 * Created on Mar 30, 2006
 *
 */
package messaging.notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.notification.domintf.INotificationIdentityAddressDef;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class UpdateNotificationDefEvent extends RequestEvent
{
	private String notificationTopic;
	private String defaultServerity;
	private String defaultSubject;

	private Collection identityAddressDefs;

	private String template;

	public UpdateNotificationDefEvent()
	{
		super();
		this.identityAddressDefs = new ArrayList();
	}

	public void addIdentityAddressDefs(INotificationIdentityAddressDef identityDefs)
	{
		this.identityAddressDefs.add(identityDefs);
	}

	public Iterator getIdentityAddressDefs()
	{
		return this.identityAddressDefs.iterator();
	}

	/**
	 * @return
	 */
	public String getDefaultServerity()
	{
		return defaultServerity;
	}

	/**
	 * @return
	 */
	public String getNotificationTopic()
	{
		return notificationTopic;
	}

	/**
	 * @param string
	 */
	public void setDefaultServerity(String string)
	{
		defaultServerity = string;
	}

	/**
	 * @param string
	 */
	public void setNotificationTopic(String string)
	{
		notificationTopic = string;
	}

	/**
	 * @return
	 */
	public String getTemplate()
	{
		return template;
	}

	/**
	 * @param string
	 */
	public void setTemplate(String string)
	{
		template = string;
	}

	/**
	 * @return
	 */
	public String getDefaultSubject()
	{
		return defaultSubject;
	}

	/**
	 * @param string
	 */
	public void setDefaultSubject(String string)
	{
		defaultSubject = string;
	}

}
