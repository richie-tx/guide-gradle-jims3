/*
 * Created on Aug 2, 2004
 *
 */
package messaging.authorization;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class SystemActivityRequestEvent extends RequestEvent
{
	public String systemActivityId;
	/**
	 * @return
	 */
	public String getSystemActivityId()
	{
		return systemActivityId;
	}

	/**
	 * @param string
	 */
	public void setSystemActivityId(String string)
	{
		systemActivityId = string;
	}

}
