/*
 * Main.java
 *
 * Created on March 22, 2006, 9:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package messaging.task.to;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import messaging.task.domintf.ITaskState;

/**
 *
 * @author Jim Fisher
 */
public class TaskStateBean implements ITaskState
{
	private Map items;

	/** Creates a new instance of Main */
	public TaskStateBean()
	{
		this.items = new HashMap();
	}	

	public void addItem(String key, String string)
	{
		this.items.put(key, string);
	}

	public void addItem(String key, Integer integer)
	{
		this.items.put(key, integer);
	}

	public void addItem(String key, Date date)
	{
		this.items.put(key, date);
	}

	public Object get(Object key)
	{
		return this.items.get(key);
	}

	public Set getKeys()
	{
		return this.items.keySet();
	}	

	public Collection getItems()
	{
		return this.items.values();
	}

	/* (non-Javadoc)
	 * @see messaging.task.domintf.ITaskState#getMap()
	 */
	public HashMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
