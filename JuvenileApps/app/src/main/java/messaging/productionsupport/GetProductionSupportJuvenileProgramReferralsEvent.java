package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileProgramReferralsEvent extends RequestEvent 
{
   
	private String casefileId;	 
	private String referralNum;
	private boolean isFromCSJUVPROGREF = false;
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportJuvenileProgramReferralsEvent() 
   {
    
   }

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
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

	public boolean getIsFromCSJUVPROGREF()
	{
	    return isFromCSJUVPROGREF;
	}

	public void setIsFromCSJUVPROGREF(boolean isFromCSJUVPROGREF)
	{
	    this.isFromCSJUVPROGREF = isFromCSJUVPROGREF;
	}
  
}
