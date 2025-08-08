package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportCasefileEvent extends RequestEvent 
{
   
	private String supervisionId;
	private String jpoOfficerId;	 
	
   
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportCasefileEvent() 
   {
    
   }

/**
 * @return the supervisionId
 */
public String getSupervisionId() {
	return supervisionId;
}

/**
 * @param supervisionId the supervisionId to set
 */
public void setSupervisionId(String supervisionId) {
	this.supervisionId = supervisionId;
}

/**
 * @return the jpoOfficerId
 */
public String getJpoOfficerId() {
	return jpoOfficerId;
}

/**
 * @param jpoOfficerId the jpoOfficerId to set
 */
public void setJpoOfficerId(String jpoOfficerId) {
	this.jpoOfficerId = jpoOfficerId;
}

   
}
