//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageworkgroup\\DeleteWorkGroupEvent.java

package messaging.manageworkgroup;

import mojo.km.messaging.RequestEvent;

public class DeleteWorkGroupEvent extends RequestEvent 
{
   private String workGroupId;
   
	/**
	 * @return Returns the workGroupId.
	 */
	public String getWorkGroupId() {
	    return workGroupId;
	}
	/**
	 * @param workGroupId The workGroupId to set.
	 */
	public void setWorkGroupId(String workGroupId) {
	    this.workGroupId = workGroupId;
	}

	/**
    * @roseuid 45DB6153029C
    */
   public DeleteWorkGroupEvent() 
   {
    
   }
}
