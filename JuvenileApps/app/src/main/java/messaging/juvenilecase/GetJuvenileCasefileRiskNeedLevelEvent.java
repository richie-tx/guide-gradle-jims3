//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileRiskNeedLevelEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileRiskNeedLevelEvent extends RequestEvent 
{
   public String caseFileId;
   public String juvenileNum;
   public String referralNumber;
   
   /**
    * @roseuid 4278C82F0396
    */
   public GetJuvenileCasefileRiskNeedLevelEvent() 
   {
    
   }
   
   /**
    * @param caseFileId
    * @roseuid 4278C7B902C4
    */
   public void setCaseFileId(String aCaseFileId) 
   {
    this.caseFileId = aCaseFileId;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B902C6
    */
   public String getCaseFileId() 
   {
    return this.caseFileId;
   }
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the referralNumber
	 */
	public String getReferralNumber() {
		return referralNumber;
	}

	/**
	 * @param referralNumber the referralNumber to set
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
}
