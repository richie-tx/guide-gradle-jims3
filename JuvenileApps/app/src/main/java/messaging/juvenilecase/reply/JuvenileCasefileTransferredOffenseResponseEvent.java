/*
 * Created on June 11, 2013
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a basic populated version juvenile transferred offense info
 * 
 */
public class JuvenileCasefileTransferredOffenseResponseEvent extends ResponseEvent 
{
	private String juvenileNum;
	private String referralNum;
	private String countyId;
	private String countyDesc;
	private String offenseCode;
	private String offenseShortDesc;
	private String category;  // displays as severity level
	private String dpsCode;
	private Date offenseDate;
	private Date adjudicationDate;
	private String offenseDateStr;
	private String adjudicationDateStr;
	private String available;
	private int seqNum;   // needed for sorting
	private String personId;
	private String severitySubType; // added for user-story #32226
	private String transOffenseReferralId;

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
	 * @return the offenseCode
	 */
	public String getOffenseCode() {
		return offenseCode;
	}
	/**
	 * @param offenseCode the offenseCode to set
	 */
	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}
	/**
	 * @return the offenseShortDesc
	 */
	public String getOffenseShortDesc() {
		return offenseShortDesc;
	}
	/**
	 * @param offenseShortDesc the offenseShortDesc to set
	 */
	public void setOffenseShortDesc(String offenseShortDesc) {
		this.offenseShortDesc = offenseShortDesc;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}
	/**
	 * @return the seqNum
	 */
	public int getSeqNum() {
		return seqNum;
	}
	/**
	 * @param seqNum the seqNum to set
	 */
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
	
	/**
	 * @return
	 */
	public String getPersonId() {
		return personId;
	}
	
	/**
	 * @param pidNum
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/**
	 * @return the severitySubType
	 */
	public String getSeveritySubType() {
		return severitySubType;
	}
	/**
	 * @param severitySubType the severitySubType to set
	 */
	public void setSeveritySubType(String severitySubType) {
		this.severitySubType = severitySubType;
	}
	
	public String getTransOffenseReferralId()
	{
	    return transOffenseReferralId;
	}
	public void setTransOffenseReferralId(String transOffenseReferralId)
	{
	    this.transOffenseReferralId = transOffenseReferralId;
	}
	
}