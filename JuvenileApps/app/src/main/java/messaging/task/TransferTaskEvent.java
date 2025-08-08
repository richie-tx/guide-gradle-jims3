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
public class TransferTaskEvent extends RequestEvent
{
	private String taskId;
	private String ownerId;
	private String statusId;
	/**
	 * @return
	 */
	public String getOwnerId()
	{
		return ownerId;
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
	public void setOwnerId(String string)
	{
		ownerId = string;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}

    /**
     * @return Returns the statusId.
     */
    public String getStatusId()
    {
        return statusId;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId)
    {
        this.statusId = statusId;
    }
}