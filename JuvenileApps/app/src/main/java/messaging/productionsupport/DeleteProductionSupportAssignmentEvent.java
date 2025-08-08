package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportAssignmentEvent extends RequestEvent 
{	
	private String assignmentId;
    
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
