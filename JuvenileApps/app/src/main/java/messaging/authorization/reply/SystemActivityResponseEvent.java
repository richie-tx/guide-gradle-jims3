/*
 * Created on Aug 2, 2004
 *
 */
package messaging.authorization.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 *
 */
public class SystemActivityResponseEvent extends ResponseEvent
{
	public String systemActivityId;
	public String name;
	public String description;
	public boolean associated;

	public SystemActivityResponseEvent()
	{
		this.associated = true;
	}

	/**
	 * @return
	 */
	public String getSystemActivityId()
	{
		return systemActivityId;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param string
	 */
	public void setSystemActivityId(String string)
	{
		systemActivityId = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @return
	 */
	public boolean isAssociated()
	{
		return associated;
	}

	/**
	 * @param b
	 */
	public void setAssociated(boolean b)
	{
		associated = b;
	}

}
