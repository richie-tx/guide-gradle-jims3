//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\SaveJuvenileTransferredOffenseReferralEvent.java

package messaging.referral;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveTransferredOffenseReferralsEvent extends CompositeRequest 
{
   public String adjudicationDateStr;
   public String countyDesc;
   public String countyId;
   public String dpsCode;
   public String juvenileNum;
   public String offenseDateStr;
   public String offenseId;
   public String offenseDesc;
   public String offenseCategory;  //display on page under Severity Level
   public String recId;
   public String referralNum;  
   public Date offenseDate;
   public Date adjudicationDate;
   public String probationStartDateStr;
   public String probationEndDateStr;
   public Date probationStartDate;
   public Date probationEndDate;
   public String personId;
   private String transOffenseReferralId;
   
   
   /**
    * @roseuid 42B1968A001F
    */
   public SaveTransferredOffenseReferralsEvent() 
   {
    
   }
   
   
   public String getTransOffenseReferralId()
	{
	    return transOffenseReferralId;
	}
	public void setTransOffenseReferralId(String transOffenseReferralId)
	{
	    this.transOffenseReferralId = transOffenseReferralId;
	}

	/**
	 * @return the adjudicationDateStr
	 */
	public String getAdjudicationDateStr() {
		return adjudicationDateStr;
	}
	
	/**
	 * @param adjudicationDateStr the adjudicationDateStr to set
	 */
	public void setAdjudicationDateStr(String adjudicationDateStr) {
		this.adjudicationDateStr = adjudicationDateStr;
	}
	
	/**
	 * @return the countyDesc
	 */
	public String getCountyDesc() {
		return countyDesc;
	}
	
	/**
	 * @param countyDesc the countyDesc to set
	 */
	public void setCountyDesc(String countyDesc) {
		this.countyDesc = countyDesc;
	}
	
	/**
	 * @return the countyId
	 */
	public String getCountyId() {
		return countyId;
	}
	
	/**
	 * @param countyId the countyId to set
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	/**
	 * @return the dpsCode
	 */
	public String getDpsCode() {
		return dpsCode;
	}
	
	/**
	 * @param dpsCode the dpsCode to set
	 */
	public void setDpsCode(String dpsCode) {
		this.dpsCode = dpsCode;
	}
	
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	/**
	 * @return the offenseDateStr
	 */
	public String getOffenseDateStr() {
		return offenseDateStr;
	}
	
	/**
	 * @param offenseDateStr the offenseDateStr to set
	 */
	public void setOffenseDateStr(String offenseDateStr) {
		this.offenseDateStr = offenseDateStr;
	}
	
	/**
	 * @return the offenseId
	 */
	public String getOffenseId() {
		return offenseId;
	}
	
	/**
	 * @param offenseId the offenseId to set
	 */
	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}
	
	/**
	 * @return the offenseDesc
	 */
	public String getOffenseDesc() {
		return offenseDesc;
	}
	
	/**
	 * @param offenseDesc the offenseDesc to set
	 */
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}
	
	/**
	 * @return the offenseCategory
	 */
	public String getOffenseCategory() {
		return offenseCategory;
	}
	
	/**
	 * @param offenseCategory the offenseCategory to set
	 */
	public void setOffenseCategory(String offenseCategory) {
		this.offenseCategory = offenseCategory;
	}
	
	/**
	 * @return the recId
	 */
	public String getRecId() {
		return recId;
	}
	
	/**
	 * @param recId the recId to set
	 */
	public void setRecId(String recId) {
		this.recId = recId;
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

	/**
	 * @return the offenseDate
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}

	/**
	 * @param offenseDate the offenseDate to set
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}

	/**
	 * @return the adjudicationDate
	 */
	public Date getAdjudicationDate() {
		return adjudicationDate;
	}

	/**
	 * @param adjudicationDate the adjudicationDate to set
	 */
	public void setAdjudicationDate(Date adjudicationDate) {
		this.adjudicationDate = adjudicationDate;
	}
	
	public String getProbationStartDateStr()
	{
	    return probationStartDateStr;
	}

	public void setProbationStartDateStr(String probationStartDateStr)
	{
	    this.probationStartDateStr = probationStartDateStr;
	}

	public String getProbationEndDateStr()
	{
	    return probationEndDateStr;
	}

	public void setProbationEndDateStr(String probationEndDateStr)
	{
	    this.probationEndDateStr = probationEndDateStr;
	}

	public Date getProbationStartDate()
	{
	    return probationStartDate;
	}

	public void setProbationStartDate(Date probationStartDate)
	{
	    this.probationStartDate = probationStartDate;
	}

	public Date getProbationEndDate()
	{
	    return probationEndDate;
	}

	public void setProbationEndDate(Date probationEndDate)
	{
	    this.probationEndDate = probationEndDate;
	}

	public String getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	  
}