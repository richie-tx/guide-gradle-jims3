package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportJuvenileReferralCommentEvent extends RequestEvent 
{
	private String referralcommentId;	 
   
   

/**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportJuvenileReferralCommentEvent() 
   {
    
   }

	/**
	 * @return the referralNum
	 */
   public String getReferralcommentId()
	{
	    return referralcommentId;
	}

	public void setReferralcommentId(String referralcommentId)
	{
	    this.referralcommentId = referralcommentId;
	}
  
}
