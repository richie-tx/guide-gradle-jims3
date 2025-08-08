package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportRiskResponseEvent extends RequestEvent 
{
 
	private String riskAnalysisId;
   
   /**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportRiskResponseEvent() 
   {
    
   }

	/**
	 * @return the riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
	
	/**
	 * @param riskAnalysisId the riskAnalysisId to set
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}
   
}
