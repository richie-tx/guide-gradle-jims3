/*
 * Created on Mar 24, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.task;

import java.util.HashMap;
import java.util.Map;

import messaging.task.domintf.IGetTasks;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetUserTasksEvent extends RequestEvent implements IGetTasks
{
	private String ownerId;
	private Map criterion;
	private String[] statusIds;

	public GetUserTasksEvent()
	{
		this.criterion = new HashMap();
	}

	public void addCriterion(String key, String value)
	{
		this.criterion.put(key, value);
	}

	public void addCriterion(String key, Integer value)
	{
		this.criterion.put(key, value);
	}

	public Map getCriterion()
	{
		return this.criterion;
	}

	public String getOwnerId()
	{
		return this.ownerId;
	}

	public void setOwnerId(String ownerId)
	{
		this.ownerId = ownerId;
	}

	public String[] getStatusIds()
	{
		return this.statusIds;
	}

	public void setStatusIds(String[] statusIds)
	{
		this.statusIds = statusIds;
	}

}
