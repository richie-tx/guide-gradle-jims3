package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetBenefitsAssessmentsEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 4370F7360204
    */
   public GetBenefitsAssessmentsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 436F8B0C0049
    */
   public void setJuvenileNum(String aJuvenileNum) 
   {
		juvenileNum = aJuvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 436F8B0C0051
    */
   public String getJuvenileNum() 
   {
    	return juvenileNum;
   }
}
