//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managetask\\DisplayTasksSearchEvent.java

package messaging.managetask;

import mojo.km.messaging.RequestEvent;

public class GetCSTaskByOIDEvent extends RequestEvent 
{
    private String taskId;
    
    
    
    /**
    * @roseuid 463F301402AE
    */
   public GetCSTaskByOIDEvent() 
   {
       
    
   }
    /**
     * @return Returns the staffPositionId.
     */
    public String getTaskId()
    {
        return taskId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setTaskId(String staffPositionId)
    {
        this.taskId = staffPositionId;
    }
    
}
