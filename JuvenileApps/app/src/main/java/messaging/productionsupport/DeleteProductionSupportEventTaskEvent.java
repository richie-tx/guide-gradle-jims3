package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportEventTaskEvent extends RequestEvent 
{
 
	private String casefileId;
	private String eventTaskId;
   
   /**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportEventTaskEvent() 
   {
    
   }

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	/**
	 * @return the eventTaskId
	 */
	public String getEventTaskId() {
		return eventTaskId;
	}

	/**
	 * @param eventTaskId the eventTaskId to set
	 */
	public void setEventTaskId(String eventTaskId) {
		this.eventTaskId = eventTaskId;
	}

}
