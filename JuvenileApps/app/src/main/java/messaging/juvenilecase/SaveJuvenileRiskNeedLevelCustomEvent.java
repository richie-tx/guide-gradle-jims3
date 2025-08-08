//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SaveJuvenileRiskNeedLevelEvent.java

package messaging.juvenilecase;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class SaveJuvenileRiskNeedLevelCustomEvent extends RequestEvent 
{
   /**
     * 
     */
   private static final long serialVersionUID = 1L;
   public String caseFileId;
   public String juvenileNum;
   public String referralNumber;
   private String riskLvl;
   private String needsLvl;
   private Date lastPactDate;
   private String status;
   private String riskNeedLvlId;
   private String pactId;

   
   /**
    * @roseuid 4278C82F0396
    */
   public SaveJuvenileRiskNeedLevelCustomEvent() 
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

	/**
	 * @return the riskLvl
	 */
	public String getRiskLvl() {
		return riskLvl;
	}

	/**
	 * @param riskLvl the riskLvl to set
	 */
	public void setRiskLvl(String riskLvl) {
		this.riskLvl = riskLvl;
	}

	/**
	 * @return the needsLvl
	 */
	public String getNeedsLvl() {
		return needsLvl;
	}

	/**
	 * @param needsLvl the needsLvl to set
	 */
	public void setNeedsLvl(String needsLvl) {
		this.needsLvl = needsLvl;
	}

	/**
	 * @return the lastPactDate
	 */
	public Date getLastPactDate() {
		return lastPactDate;
	}

	/**
	 * @param lastPactDate the lastPactDate to set
	 */
	public void setLastPactDate(Date lastPactDate) {
		this.lastPactDate = lastPactDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRiskNeedLvlId()
	{
	    return riskNeedLvlId;
	}

	public void setRiskNeedLvlId(String riskNeedLvlId)
	{
	    this.riskNeedLvlId = riskNeedLvlId;
	}

	public String getPactId()
	{
	    return pactId;
	}

	public void setPactId(String pactId)
	{
	    this.pactId = pactId;
	}
	
}
