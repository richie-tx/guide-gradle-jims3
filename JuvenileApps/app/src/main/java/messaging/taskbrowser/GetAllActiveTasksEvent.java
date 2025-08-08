//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\RemoveSelectedTaskEvent.java

package messaging.taskbrowser;

import mojo.km.messaging.RequestEvent;

public class GetAllActiveTasksEvent extends RequestEvent 
{
   private String taskUserID;
   
   /**
    * @roseuid 43E12DC60284
    */
   public GetAllActiveTasksEvent() 
   {
    
   }
/**
 * @return
 */
public String getTaskUserID()
{
	return taskUserID;
}

/**
 * @param string
 */
public void setTaskUserID(String string)
{
	taskUserID = string;
}

}
