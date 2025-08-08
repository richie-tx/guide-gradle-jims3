package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileProgramReferralCommentEvent extends RequestEvent 
{  
		 
	private String referralId;  
   


/**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportJuvenileProgramReferralCommentEvent() 
   {
    
   }

	/**
	 * @return the casefileId
	 */
   public String getReferralId()
	{
	    return referralId;
	}


	public void setReferralId(String referralId)
	{
	    this.referralId = referralId;
	}

}
