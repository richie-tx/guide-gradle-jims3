package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetRequestedBenefitsAssessmentByJuvenileEvent extends RequestEvent 
{
   private String juvenileNum;
   
   /**
    * @roseuid 4370F7360204
    */
   public GetRequestedBenefitsAssessmentByJuvenileEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}
	
}
