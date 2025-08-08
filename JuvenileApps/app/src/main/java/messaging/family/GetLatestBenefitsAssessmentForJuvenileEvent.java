package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetLatestBenefitsAssessmentForJuvenileEvent extends RequestEvent 
{
   private String juvenileId;
   
   /**
    * @roseuid 4370F7360204
    */
   public GetLatestBenefitsAssessmentForJuvenileEvent() 
   {
    
   }
 
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
}
