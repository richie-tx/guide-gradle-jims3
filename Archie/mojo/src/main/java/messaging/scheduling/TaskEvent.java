/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.scheduling;

import mojo.km.context.multidatasource.PersistentObject;
import mojo.km.messaging.IEvent;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TaskEvent extends PersistentObject implements IEvent
{

	/* (non-Javadoc)
	 * @see mojo.km.messaging.IEvent#getServer()
	 */
	public String getServer()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.IEvent#setServer(java.lang.String)
	 */
	public void setServer(String name)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.ITopic#getTopic()
	 */
	public String getTopic()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.ITopic#setTopic(java.lang.String)
	 */
	public void setTopic(String aService)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.messaging.ITopic#hashKey()
	 */
	public String hashKey()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public String notificationAddress;
	// where the notification is going 
	public String notificationTarget;
	//who it's coming from
	public String notificationSource;
	public String subject;
	//message text
	public String message;
	
	
	

	/**
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @return
	 */
	public String getNotificationAddress()
	{
		return notificationAddress;
	}

	/**
	 * @return
	 */
	public String getNotificationSource()
	{
		return notificationSource;
	}

	/**
	 * @return
	 */
	public String getNotificationTarget()
	{
		return notificationTarget;
	}

	/**
	 * @return
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string)
	{
		message = string;
	}

	/**
	 * @param string
	 */
	public void setNotificationAddress(String string)
	{
		notificationAddress = string;
	}

	/**
	 * @param string
	 */
	public void setNotificationSource(String string)
	{
		notificationSource = string;
	}

	/**
	 * @param string
	 */
	public void setNotificationTarget(String string)
	{
		notificationTarget = string;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string)
	{
		subject = string;
	}

}
