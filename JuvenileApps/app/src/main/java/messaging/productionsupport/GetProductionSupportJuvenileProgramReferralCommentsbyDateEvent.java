package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileProgramReferralCommentsbyDateEvent extends RequestEvent 
{
    
	private String juvenileProgramReferralNum;
	private String commentDate;
   
  

/**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportJuvenileProgramReferralCommentsbyDateEvent() 
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
public String getCommentDate()
	{
	    return commentDate;
	}

	public void setCommentDate(String commentDate)
	{
	    this.commentDate = commentDate;
	}
   
  
}
