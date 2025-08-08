package pd.notification;

import mojo.km.persistence.PersistentObject;
import pd.notification.Task;

/**
* @roseuid 43FB4DEB0305
*/
public class TaskParam extends PersistentObject {
	/**
	* Properties for task
	*/
	public Task task = null;
	private String taskId;
	private String paramName;
	private String paramValue;
	/**
	* @roseuid 43FB4DEB0305
	*/
	public TaskParam() {
	}
	/**
	* Access method for the task property.
	* @return the current value of the task property
	*/
	public Task getTask() {
		initTask();
		return task;
	}
	/**
	* Set the reference value to class :: pd.notification.Task
	*/
	public void setTaskId(String taskId) {
		if (this.taskId == null || !this.taskId.equals(taskId)) {
			markModified();
		}
		task = null;
		this.taskId = taskId;
	}
	/**
	* Get the reference value to class :: pd.notification.Task
	*/
	public String getTaskId() {
		fetch();
		return taskId;
	}
	/**
	* Initialize class relationship to class pd.notification.Task
	*/
	private void initTask() {
		if (task == null) {
			task = (Task) new mojo.km.persistence.Reference(taskId, Task.class).getObject();
		}
	}
	/**
	* set the type reference for class member task
	*/
	public void setTask(Task task) {
		if (this.task == null || !this.task.equals(task)) {
			markModified();
		}
		if (task.getOID() == null) {
			new mojo.km.persistence.Home().bind(task);
		}
		setTaskId("" + task.getOID());
		this.task = (Task) new mojo.km.persistence.Reference(task).getObject();
	}
	/**
	* @return 
	*/
	public String getParamName() {
		return paramName;
	}
	/**
	* @return 
	*/
	public String getParamValue() {
		return paramValue;
	}
	/**
	* @param i
	*/
	public void setParamName(String i) {
		paramName = i;
	}
	/**
	* @param i
	*/
	public void setParamValue(String i) {
		paramValue = i;
	}
}
