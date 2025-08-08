/*
 * Created on Mar 17, 2006
 *
 */
package messaging.task;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class UpdateTaskStatusEvent extends RequestEvent
{
	private String taskId;
	private String statusCode;
	/**
	 * @return
	 */
	public String getStatusCode()
	{
		return statusCode;
	}

	/**
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @param string
	 */
	public void setStatusCode(String string)
	{
		statusCode = string;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}

}
