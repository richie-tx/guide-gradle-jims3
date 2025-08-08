package pd.juvenilecase.interviewinfo;

import java.io.IOException;
import java.util.Date;

import messaging.task.CreateTaskEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import naming.PDJuvenileCaseConstants;
import pd.task.Task;
import pd.task.TaskDef;

/**
 * InterviewTask wrapps a Task and exposes the interface that is relevent to interviews.
 * 
* @roseuid 448EC35C0252
*/
public class InterviewTask extends PersistentObject 
{
	/**
	 * 
	 */
	private String interviewId;
	
	/**
	 * 
	 */
	private String taskId;
	private Task task;
	
	
	/**
	 *
	 */
	public static InterviewTask createTask( TaskDef taskDef, String casefileId, String juvenileId )
		throws IOException
	{
		
		CreateTaskEvent event  = new CreateTaskEvent();
		event.addTaskStateItem( PDJuvenileCaseConstants.JUVENILENUM_PARAM, juvenileId );
		event.addTaskStateItem( PDJuvenileCaseConstants.CASEFILEID_PARAM, casefileId );
		
		Task newTask = taskDef.createTask(event);
		if ( newTask.getOID() == null )
		{
			new Home().bind( newTask );
		}
		
		InterviewTask itask = new InterviewTask();
		new Home().bind( itask );
		
		newTask.setStatusId( Task.ACCEPTED );
		newTask.setAcceptedDate( new Date() );
		
		itask.setTaskId( newTask.getOID().toString() );
	
		return itask;
	}
	
	/**
	* @roseuid 448EC35C0252
	*/
	public InterviewTask() 
	{
	}
	
	/**
	 * @return Returns the interviewId.
	 */
	public String getInterviewId() 
	{
		fetch();
		return interviewId;
	}
	
	/**
	 * @param interviewId The interviewId to set.
	 */
	public void setInterviewId( String anInterviewId ) 
	{
		if ( interviewId == null || ! interviewId.equals(anInterviewId) )
		{
			interviewId = anInterviewId;
			markModified();
		}
	}
	
	/**
	* Initialize class relationship implementation for pd.juvenilecase.interviewinfo.InterviewPerson
	*/
	private void initTask() 
	{
		if ( task == null && taskId != null ) 
		{
			task = (Task) new mojo.km.persistence.Reference(taskId, Task.class).getObject();
		}
	}
	
	/**
	 * @return Returns the task.
	 */
	public Task getTask() 
	{
		initTask();
		return task;
	}
	
	/**
	 * @param task The task to set.
	 */
	public void setTask(Task task) 
	{
		if ( task.getOID() == null ) 
		{
			new Home().bind(task);
		}
		setTaskId( task.getOID().toString() );
	}
	
	/**
	 * @return Returns the taskId.
	 */
	public String getTaskId() 
	{
		fetch();
		return taskId;
	}
	
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId( String aTaskId ) 
	{
		if ( taskId == null || ! taskId.equals(aTaskId) )
		{
			taskId = aTaskId;
			markModified();
			task = null;
		}
	}

	/**
	 * @return String topic
	 */
	public String getTopic()
	{
		return getTask().getTopic();
	}

	/**
	 * 
	 */
	public TaskDef getTaskDef()
	{
		Task task = getTask();
		TaskDef taskDef = TaskDef.find(task.getTopic());
		return taskDef;
	}
	
	/**
	 * 
	 */
	public String getSubject()
	{
		return getTaskDef().getSubject();
	}
	
	/**
	 * 
	 */
	public void complete()
	{
		if ( ! completed() )
		{
			Task task = getTask();
			task.setStatusId( Task.CLOSED );
			task.setClosedDate( new Date() );
		}
	}

	/**
	 * 
	 */
	public boolean completed()
	{
		return Task.CLOSED.equals(getTask().getStatusId());
	}

	/**
	 * Returns the executable Task ID required for use with the executeTask.do action.
	 */
	public String getExecTaskId()
	{
		return getTask().getOID().toString();
	}
	
}
