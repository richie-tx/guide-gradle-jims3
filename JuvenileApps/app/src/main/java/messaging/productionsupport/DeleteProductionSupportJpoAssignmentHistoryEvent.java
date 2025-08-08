package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportJpoAssignmentHistoryEvent extends RequestEvent 
{
   
	private String assignmentHistoryId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportJpoAssignmentHistoryEvent() 
   {
    
   }

	/**
	 * @return the assignmentHistoryId
	 */
	public String getAssignmentHistoryId() {
		return assignmentHistoryId;
	}
	
	/**
	 * @param assignmentHistoryId the assignmentHistoryId to set
	 */
	public void setAssignmentHistoryId(String assignmentHistoryId) {
		this.assignmentHistoryId = assignmentHistoryId;
	}
   
}
