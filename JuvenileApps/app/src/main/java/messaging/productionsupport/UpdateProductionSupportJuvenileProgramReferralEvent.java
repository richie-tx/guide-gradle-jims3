package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportJuvenileProgramReferralEvent extends RequestEvent 
{
   
	// prod support merge casefile
	private String casefileId;
	private String mergeToCasefileId; 
	
	// prod support update referral
	private String referralNum;
	private String statusCd;
	private String subStatusCd;
	private String outcomeCd;
	private String outcomeSubCd;
	private String provProgramId;
	private String controllingReferral;
	private Date beginDate;
	private Date endDate;
	private Date ackDate;
	private Date sentDate;
	private Date programReferralAssignmentDate;
	private boolean isRefBeginDateChanged;
	private boolean isRefEndDateChanged;
	private boolean isRefAckDateChanged;
	private boolean isRefSentDateChanged;
	private boolean isControllingReferralChanged;
	private boolean isProgramReferralAssignmentDateChanged;
	private String programReferralAssignmentId;
	private String fundSource;
	private boolean fundSourceChanged;
	
	

/**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportJuvenileProgramReferralEvent() 
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
	 * @return the mergeToCasefileId
	 */
	public String getMergeToCasefileId() {
		return mergeToCasefileId;
	}
	
	/**
	 * @param mergeToCasefileId the mergeToCasefileId to set
	 */
	public void setMergeToCasefileId(String mergeToCasefileId) {
		this.mergeToCasefileId = mergeToCasefileId;
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
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the subStatusCd
	 */
	public String getSubStatusCd() {
		return subStatusCd;
	}

	/**
	 * @param subStatusCd the subStatusCd to set
	 */
	public void setSubStatusCd(String subStatusCd) {
		this.subStatusCd = subStatusCd;
	}

	/**
	 * @return the outcomeCd
	 */
	public String getOutcomeCd() {
		return outcomeCd;
	}

	/**
	 * @param outcomeCd the outcomeCd to set
	 */
	public void setOutcomeCd(String outcomeCd) {
		this.outcomeCd = outcomeCd;
	}

	/**
	 * @return the outcomeSubCd
	 */
	public String getOutcomeSubCd() {
		return outcomeSubCd;
	}

	/**
	 * @param outcomeSubCd the outcomeSubCd to set
	 */
	public void setOutcomeSubCd(String outcomeSubCd) {
		this.outcomeSubCd = outcomeSubCd;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	public Date getAckDate()
	{
	    return ackDate;
	}

	public void setAckDate(Date ackDate)
	{
	    this.ackDate = ackDate;
	}

	public Date getSentDate()
	{
	    return sentDate;
	}

	public void setSentDate(Date sentDate)
	{
	    this.sentDate = sentDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isRefBeginDateChanged()
	{
	    return isRefBeginDateChanged;
	}

	public void setRefBeginDateChanged(boolean isRefBeginDateChanged)
	{
	    this.isRefBeginDateChanged = isRefBeginDateChanged;
	}

	public boolean isRefEndDateChanged()
	{
	    return isRefEndDateChanged;
	}

	public void setRefEndDateChanged(boolean isRefEndDateChanged)
	{
	    this.isRefEndDateChanged = isRefEndDateChanged;
	}
	

	public boolean isRefAckDateChanged()
	{
	    return isRefAckDateChanged;
	}

	public void setRefAckDateChanged(boolean isRefAckDateChanged)
	{
	    this.isRefAckDateChanged = isRefAckDateChanged;
	}

	public boolean isRefSentDateChanged()
	{
	    return isRefSentDateChanged;
	}

	public void setRefSentDateChanged(boolean isRefSentDateChanged)
	{
	    this.isRefSentDateChanged = isRefSentDateChanged;
	}
	
	public String getControllingReferral()
	{
	    return controllingReferral;
	}

	public void setControllingReferral(String controllingReferral)
	{
	    this.controllingReferral = controllingReferral;
	}
	
	public boolean isControllingReferralChanged()
	{
	    return isControllingReferralChanged;
	}

	public void setControllingReferralChanged(boolean isControllingReferralChanged)
	{
	    this.isControllingReferralChanged = isControllingReferralChanged;
	}

	public Date getProgramReferralAssignmentDate()
	{
	    return programReferralAssignmentDate;
	}

	public void setProgramReferralAssignmentDate(Date programReferralAssignmentDate)
	{
	    this.programReferralAssignmentDate = programReferralAssignmentDate;
	}

	public boolean isProgramReferralAssignmentDateChanged()
	{
	    return isProgramReferralAssignmentDateChanged;
	}

	public void setProgramReferralAssignmentDateChanged(boolean isProgramReferralAssignmentDateChanged)
	{
	    this.isProgramReferralAssignmentDateChanged = isProgramReferralAssignmentDateChanged;
	}

	public String getProgramReferralAssignmentId()
	{
	    return programReferralAssignmentId;
	}

	public void setProgramReferralAssignmentId(String programReferralAssignmentId)
	{
	    this.programReferralAssignmentId = programReferralAssignmentId;
	}
	
	public String getFundSource() {
		return this.fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getProvProgramId()
	{
	    return provProgramId;
	}

	public void setProvProgramId(String provProgramId)
	{
	    this.provProgramId = provProgramId;
	}

	public boolean isFundSourceChanged()
	{
	    return fundSourceChanged;
	}

	public void setFundSourceChanged(boolean fundSourceChanged)
	{
	    this.fundSourceChanged = fundSourceChanged;
	}

	
   
}
