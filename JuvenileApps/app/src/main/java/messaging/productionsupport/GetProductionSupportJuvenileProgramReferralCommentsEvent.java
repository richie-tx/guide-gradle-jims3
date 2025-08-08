package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileProgramReferralCommentsEvent extends RequestEvent 
{
    
	private String juvenileProgramReferralNum;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportJuvenileProgramReferralCommentsEvent() 
   {
    
   }

/**
 * @return the juvenileProgramReferralNum
 */
public String getJuvenileProgramReferralNum() {
	return juvenileProgramReferralNum;
}

/**
 * @param juvenileProgramReferralNum the juvenileProgramReferralNum to set
 */
public void setJuvenileProgramReferralNum(String juvenileProgramReferralNum) {
	this.juvenileProgramReferralNum = juvenileProgramReferralNum;
}

   
  
}
