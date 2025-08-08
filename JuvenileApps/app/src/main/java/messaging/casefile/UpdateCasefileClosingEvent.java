//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\UpdateCasefileClosingEvent.java

package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateCasefileClosingEvent extends RequestEvent 
{
	 private String closingEvaluation;
	 private String supervisionOutcomeId;
	 private String supervisionOutcomeDescriptionId;
	 private String supervisionNumber;
	 private Date supervisionEndDate = null;
	 private String casefileClosingStatus;
	 private String controllingReferralId;
	 private String closingComments;
	 private String casefileClosingInfoId;
	 private String rejectionReason;
	 private String clmLogonID;
	 private String juvenileNum;
	 private String casefileID;
	 private boolean isSendForApproval;
	 private boolean isClosingPktGenerated;
	 private boolean isClosingLetterGenerated;
	 private boolean isCreate;
	 private String youthDeathReason;
	 private String youthDeathVerification;
	 private Date deathDate;
	 private int deathAge;
	 private boolean isApprovalForRequest;
	 private boolean isApprovalRejected; 
	 private String juvUnitId;
	 private String recordJuvUnit;
	 private String recordCLM;

   
   /**
    * @roseuid 439601E001BB
    */
   public UpdateCasefileClosingEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getCasefileClosingStatus()
	{
		return casefileClosingStatus;
	}

	/**
	 * @return
	 */
	public String getClosingComments()
	{
		return closingComments;
	}

	/**
	 * @return
	 */
	public String getClosingEvaluation()
	{
		return closingEvaluation;
	}

	/**
	 * @return
	 */
	public String getControllingReferralId()
	{
		return controllingReferralId;
	}

	/**
	 * @return
	 */
	public Date getSupervisionEndDate()
	{
		return supervisionEndDate;
	}

	/**
	 * @return
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	}

	/**
	 * @return
	 */
	public String getSupervisionOutcomeId()
	{
		return supervisionOutcomeId;
	}

	
	/**
	 * @return the supervisionOutcomeDescriptionId
	 */
	public String getSupervisionOutcomeDescriptionId() {
		return supervisionOutcomeDescriptionId;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingStatus(String string)
	{
		casefileClosingStatus = string;
	}

	/**
	 * @param string
	 */
	public void setClosingComments(String string)
	{
		closingComments = string;
	}

	/**
	 * @param string
	 */
	public void setClosingEvaluation(String string)
	{
		closingEvaluation = string;
	}

	/**
	 * @param string
	 */
	public void setControllingReferralId(String string)
	{
		controllingReferralId = string;
	}

	/**
	 * @param date
	 */
	public void setSupervisionEndDate(Date date)
	{
		supervisionEndDate = date;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNumber(String string)
	{
		supervisionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcomeId(String string)
	{
		supervisionOutcomeId = string;
	}

	/**
	 * @param supervisionOutcomeDescriptionId the supervisionOutcomeDescriptionId to set
	 */
	public void setSupervisionOutcomeDescriptionId(String supervisionOutcomeDescriptionId) {
		this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
	}

	/**
	 * @return
	 */
	public String getCasefileClosingInfoId()
	{
		return casefileClosingInfoId;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingInfoId(String string)
	{
		casefileClosingInfoId = string;
	}

	/**
	 * @return
	 */
	public String getRejectionReason()
	{
		return rejectionReason;
	}

	/**
	 * @param string
	 */
	public void setRejectionReason(String string)
	{
		rejectionReason = string;
	}

	/**
	 * @return Returns the clmLogonID.
	 */
	public String getClmLogonID() {
		return clmLogonID;
	}
	/**
	 * @param clmLogonID The clmLogonID to set.
	 */
	public void setClmLogonID(String clmLogonID) {
		this.clmLogonID = clmLogonID;
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
	 * @return Returns the casefileID.
	 */
	public String getCasefileID() {
		return casefileID;
	}
	/**
	 * @param casefileID The casefileID to set.
	 */
	public void setCasefileID(String casefileID) {
		this.casefileID = casefileID;
	}
	/**
	 * @return Returns the isSendForApproval.
	 */
	public boolean isSendForApproval() {
		return isSendForApproval;
	}
	/**
	 * @param isSendForApproval The isSendForApproval to set.
	 */
	public void setSendForApproval(boolean isSendForApproval) {
		this.isSendForApproval = isSendForApproval;
	}
	/**
	 * @return Returns the isClosingLetterGenerated.
	 */
	public boolean isClosingLetterGenerated() {
		return isClosingLetterGenerated;
	}
	/**
	 * @param isClosingLetterGenerated The isClosingLetterGenerated to set.
	 */
	public void setClosingLetterGenerated(boolean isClosingLetterGenerated) {
		this.isClosingLetterGenerated = isClosingLetterGenerated;
	}
	
	/**
	 * @return Returns the isClosingPktGenerated.
	 */
	public boolean isClosingPktGenerated() {
		return isClosingPktGenerated;
	}
	/**
	 * @param isClosingPktGenerated The isClosingPktGenerated to set.
	 */
	public void setClosingPktGenerated(boolean isClosingPktGenerated) {
		this.isClosingPktGenerated = isClosingPktGenerated;
	}
	/**
	 * @return Returns the isCreate.
	 */
	public boolean isCreate() {
		return isCreate;
	}
	/**
	 * @param isCreate The isCreate to set.
	 */
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public String getYouthDeathReason()
	{
	    return youthDeathReason;
	}

	public void setYouthDeathReason(String youthDeathReason)
	{
	    this.youthDeathReason = youthDeathReason;
	}

	public String getYouthDeathVerification()
	{
	    return youthDeathVerification;
	}

	public void setYouthDeathVerification(String youthDeathVerification)
	{
	    this.youthDeathVerification = youthDeathVerification;
	}

	public Date getDeathDate()
	{
	    return deathDate;
	}

	public void setDeathDate(Date deathDate)
	{
	    this.deathDate = deathDate;
	}

	public int getDeathAge()
	{
	    return deathAge;
	}

	public void setDeathAge(int deathAge)
	{
	    this.deathAge = deathAge;
	}
	
	 public boolean isApprovalForRequest()
	{
	     return isApprovalForRequest;
	}

	 public void setApprovalForRequest(boolean isApprovalForRequest)
	 {
	     this.isApprovalForRequest = isApprovalForRequest;
	 }

	public boolean isApprovalRejected()
	{
	    return isApprovalRejected;
	}

	public void setApprovalRejected(boolean isApprovalRejected)
	{
	    this.isApprovalRejected = isApprovalRejected;
	}

	public String getJuvUnitId()
	{
	    return juvUnitId;
	}

	public void setJuvUnitId(String juvUnitId)
	{
	    this.juvUnitId = juvUnitId;
	}

	public String getRecordJuvUnit()
	{
	    return recordJuvUnit;
	}

	public void setRecordJuvUnit(String recordJuvUnit)
	{
	    this.recordJuvUnit = recordJuvUnit;
	}

	public String getRecordCLM()
	{
	    return recordCLM;
	}

	public void setRecordCLM(String recordCLM)
	{
	    this.recordCLM = recordCLM;
	}

}
