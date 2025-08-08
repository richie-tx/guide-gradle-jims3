package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportRiskAnalysisEvent extends RequestEvent 
{
   
	private String riskAnalysisId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportRiskAnalysisEvent() 
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
