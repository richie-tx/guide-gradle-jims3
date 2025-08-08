package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportAssociatedJuvenileEventReferralsEvent extends RequestEvent 
{
    
	private String juvenileProgramReferralNum;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportAssociatedJuvenileEventReferralsEvent() 
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
