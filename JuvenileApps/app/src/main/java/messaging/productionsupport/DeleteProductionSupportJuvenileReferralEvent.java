package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportJuvenileReferralEvent extends RequestEvent 
{
	private String referralNum;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportJuvenileReferralEvent() 
   {
    
   }

	/**
	 * @return the referralNum
	 */
	public String getReferralNum() {
		return referralNum;
	}

	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
  
}
