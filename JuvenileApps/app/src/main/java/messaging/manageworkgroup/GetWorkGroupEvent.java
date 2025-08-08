//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageworkgroup\\GetWorkGroupEvent.java

package messaging.manageworkgroup;

import mojo.km.messaging.RequestEvent;

public class GetWorkGroupEvent extends RequestEvent 
{
   private String workGroupId;
   
   /**
    * @roseuid 45DB615401C1
    */
   public GetWorkGroupEvent() 
   {
    
   }

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
}
