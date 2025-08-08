package messaging.interviewinfo.reply;

import java.util.HashMap;
import java.util.Map;

import mojo.km.messaging.RequestEvent;

public class InterviewTaskResponseEvent extends RequestEvent implements Comparable
{
	private String taskId;
	private String taskName;
	private String execTaskId;
	private boolean completed;
	private Map taskParams = new HashMap();  
   
   /**
    * @roseuid 448ECBC30012
    */
	public InterviewTaskResponseEvent() 
	{
	}


   
	/**
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @return
	 */
	public String getTaskName()
	{
		return taskName;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}

	/**
	 * @param string
	 */
	public void setTaskName(String string)
	{
		taskName = string;
	}

	/**
	 * @return Returns the completed.
	 */
	public boolean isCompleted() 
	{
		return completed;
	}
	
	/**
	 * @param completed The completed to set.
	 */
	public void setCompleted(boolean completed) 
	{
		this.completed = completed;
	}
	
	/**
	 * @return Returns the taskURL.
	 */
	public String getExecTaskId() 
	{
		return execTaskId;
	}
	
	/**
	 * @param taskURL The taskURL to set.
	 */
	public void setExecTaskId( String anId ) 
	{
		execTaskId = anId;
	}
	
	/**
	 * 
	 */
	public Map getTaskParams()
	{
		return taskParams;
	}
	
	/**
	 * 
	 */
	public void setTaskParam( String key, Object value )
	{
		taskParams.put( key, value );
	}
	
	/**
	 * Return the value of the parameter that is identified by 'key'.
	 */
	public Object getTaskParam( String key ) 
	{
		return taskParams.get(key);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) throws ClassCastException
	{
		InterviewTaskResponseEvent evt = (InterviewTaskResponseEvent) obj;
		return this.getTaskName().compareTo(evt.getTaskName());
		
	}		
	
	
}
