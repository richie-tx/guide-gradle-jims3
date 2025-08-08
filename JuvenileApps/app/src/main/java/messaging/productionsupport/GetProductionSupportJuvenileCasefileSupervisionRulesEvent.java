package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileCasefileSupervisionRulesEvent extends RequestEvent 
{
   
	private String casefileId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportJuvenileCasefileSupervisionRulesEvent() 
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
   
}
