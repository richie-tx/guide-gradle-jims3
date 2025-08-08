/*
 * Created on Mar 24, 2006
 *
 */
package messaging.task.domintf;

import java.util.Map;

/**
 * @author Jim Fisher
 *
 */
public interface IGetTasks
{	
	public void addCriterion(String key, String value);
	public void addCriterion(String key, Integer value);
	public Map getCriterion();
	public String getOwnerId();
	public void setOwnerId(String ownerId);
	public String[] getStatusIds();
	public void setStatusIds(String[] statusIds);
}
