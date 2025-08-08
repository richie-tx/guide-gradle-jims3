package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportCasefileNonComplianceDocumentEvent extends RequestEvent 
{
   
	private String noncomnoteId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportCasefileNonComplianceDocumentEvent() 
   {
    
   }

	/**
	 * @return the noncomnoteId
	 */
	public String getNoncomnoteId() {
		return noncomnoteId;
	}
	
	/**
	 * @param noncomnoteId the noncomnoteId to set
	 */
	public void setNoncomnoteId(String noncomnoteId) {
		this.noncomnoteId = noncomnoteId;
	}   
   
}
