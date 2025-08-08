package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportAssignmentEvent extends RequestEvent 
{
   
	private String assignmentId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportAssignmentEvent() 
   {
    
   }

	/**
	 * @return the assignmentId
	 */
	public String getAssignmentId() {
		return assignmentId;
	}
	
	/**
	 * @param assignmentId the assignmentId to set
	 */
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

}
