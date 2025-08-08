/*
 * Created on Mar 14, 2006
 *
 */
package messaging.task.domintf;

import java.util.Date;

/**
 * @author Jim Fisher
 *
 */
public interface ICreateTask
{
	public String getTaskId();
	public Date getDueDate();
	public String getTaskTopic();
	public ITaskState getTaskState();
	public String getOwnerId();
	public String getCreatorId();
	public String getStatusCode();
	public Integer getSeverityLevel();
	public String getTaskSubject();
	public void setTaskId(String taskId);
	public void setDueDate(Date date);
	public void setOwnerId(String userId);
	public void setCreatorId(String userId);
	public void setTaskTopic(String string);
	public void addTaskStateItem(String key, String item);
	public void addTaskStateItem(String key, Integer item);
	public void addTaskStateItem(String key, Date item);
	public void setSeverityLevel(Integer integer);
	public void setStatusCode(String statusCode);
	public void setTaskSubject(String taskSubject);
}
