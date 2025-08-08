package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportInterviewReportDocumentEvent extends RequestEvent 
{
   
	private String documentId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportInterviewReportDocumentEvent() 
   {
    
   }

	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}
	
	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	} 
   
}
