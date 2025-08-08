/*
 * Created on Mar 14, 2006
 *
 */
package messaging.task;

import messaging.identity.domintf.IIdentity;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetTaskEvent extends RequestEvent implements IIdentity
{
	private String taskId;
	
	/**
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @param integer
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}

	/* (non-Javadoc)
	 * @see messaging.domintf.identity.IIdentity#getId()
	 */
	public String getId()
	{
		return this.getTaskId();
	}

	/* (non-Javadoc)
	 * @see messaging.domintf.identity.IIdentity#setId(java.lang.String)
	 */
	public void setId(String id)
	{
		this.setTaskId(id);		
	}

}
