//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\ProcessCaseFileAssignmentsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.PersistentEvent;

public class ProcessJuvenileCasefileAssignmentsEvent extends PersistentEvent
{
   private String processId;
   /**
    * @roseuid 4277C4BC01C5
    */
   public ProcessJuvenileCasefileAssignmentsEvent() 
   {
    
   }
	/**
	 * @return Returns the processId.
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId The processId to set.
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
}
