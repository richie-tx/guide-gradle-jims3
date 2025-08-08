/*
 * Created on Mar 23, 2006
 *
 */
package messaging.task;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetUnassignedTasksEvent extends RequestEvent
{
	private boolean withState;
	private String statusCode;
	
	/**
	 * @return
	 */
	public boolean isWithState()
	{
		return withState;
	}

	/**
	 * @param b
	 */
	public void setWithState(boolean b)
	{
		withState = b;
	}

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

}
