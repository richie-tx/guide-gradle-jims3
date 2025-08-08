/*
 * Created on Mar 14, 2006
 *
 */
package messaging.task;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetUserTasksByStatusEvent extends RequestEvent
{
	private String ownerId;
	private String statusCode;

	/**
	 * @return
	 */
	public String getStatusCode()
	{
		return statusCode;
	}

	/**
	 * @param string
	 */
	public void setStatusCode(String string)
	{
		statusCode = string;
	}

	/**
	 * @return
	 */
	public String getOwnerId()
	{
		return ownerId;
	}

	/**
	 * @param string
	 */
	public void setOwnerId(String string)
	{
		ownerId = string;
	}

}
