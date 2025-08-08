package mojo.km.scheduling;


/**
* @author mchowdhury
*/
public class SchedulerErrorReortingBean
{
	private Task task;
	private String errorMessage;
	
	/**
	* @roseuid 4107BF8402AF
	*/
	public SchedulerErrorReortingBean()
	{
	}
	
	/**
	* @roseuid 4107BF8402AF
	*/
	public SchedulerErrorReortingBean(Task task, String errorMessage)
	{
		this.task = task;
		this.errorMessage = errorMessage;
	}
	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return Returns the task.
	 */
	public Task getTask() {
		return task;
	}
	/**
	 * @param task The task to set.
	 */
	public void setTask(Task task) {
		this.task = task;
	}
}