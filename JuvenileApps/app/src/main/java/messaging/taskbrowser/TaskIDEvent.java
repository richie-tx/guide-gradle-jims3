//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\RemoveSelectedTaskEvent.java

package messaging.taskbrowser;

import mojo.km.messaging.RequestEvent;

public class TaskIDEvent extends RequestEvent 
{
   private String taskID;
   
   /**
    * @roseuid 43E12DC60284
    */
   public TaskIDEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getTaskID()
	{
		return taskID;
	}
	
	/**
	 * @param string
	 */
	public void setTaskID(String string)
	{
		taskID = string;
	}

}
