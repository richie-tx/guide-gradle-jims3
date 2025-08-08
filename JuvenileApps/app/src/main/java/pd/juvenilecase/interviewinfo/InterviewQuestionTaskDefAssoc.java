package pd.juvenilecase.interviewinfo;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.task.TaskDef;

/**
* @roseuid 448EC35C0252
*/
public class InterviewQuestionTaskDefAssoc extends PersistentObject 
{
	/**
	 * 
	 */
	private String questionId;
	
	/**
	 * 
	 */
	private String taskDefId;
	private TaskDef taskDef;
	
	
	/**
	* @roseuid 448EC35C0252
	*/
	public InterviewQuestionTaskDefAssoc() 
	{
	}
	
	/**
	 * @return Returns the interviewId.
	 */
	public String getQuestionId() 
	{
		fetch();
		return questionId;
	}
	
	/**
	 * @param interviewId The interviewId to set.
	 */
	public void setQuestionId( String aQuestionId ) 
	{
		if ( questionId == null || ! questionId.equals(aQuestionId) )
		{
			questionId = aQuestionId;
			markModified();
		}
	}
	
	/**
	* Initialize class relationship implementation for pd.juvenilecase.interviewinfo.InterviewPerson
	*/
	private void initTaskDef() 
	{
		if ( taskDef == null && taskDefId != null ) 
		{
			taskDef = (TaskDef) new mojo.km.persistence.Reference(taskDefId, TaskDef.class).getObject();
		}
	}
	
	/**
	 * @return Returns the task.
	 */
	public TaskDef getTaskDef() 
	{
		initTaskDef();
		return taskDef;
	}
	
	/**
	 * @param task The task to set.
	 */
	public void setTaskDef(TaskDef taskDef) 
	{
		if ( taskDef.getOID() == null ) 
		{
			new Home().bind(taskDef);
		}
		setTaskDefId( taskDef.getOID().toString() );
	}
	
	/**
	 * @return Returns the taskId.
	 */
	public String getTaskDefId() 
	{
		fetch();
		return taskDefId;
	}
	
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskDefId( String aTaskDefId ) 
	{
		if ( taskDefId == null || ! taskDefId.equals(aTaskDefId) )
		{
			taskDefId = aTaskDefId;
			markModified();
			taskDef = null;
		}
	}
	
}
