package messaging.productionsupport.reply;

import java.util.Date;

import messaging.calendaring.reply.CalendarResponseEvent;

/**
 * @author rcarter
 */
public class ProductionSupportJuvenileProgramReferralResponseEvent extends CalendarResponseEvent
{

	private String juvenileProgramReferralId;
	private String caseFileId;
	private Date ackDate;
	private Date beginDate;
	private Date endDate;
	private String assignedHours;
	private Date sentDate;
	private String statusCd;
	private String statusSubCd;
	private String outcomeCd;
	private String outcomeDescCd;
	private String providerProgramId;
	private Date lastActionDate;
	private String controllingReferralNum;
	private String juvenileNum;
	private String fundSource;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	private Date programReferralAssignmentDate;
	private String programReferralAssignmentId;

	

	/**
	 * @return the juvenileProgramReferralId
	 */
	public String getJuvenileProgramReferralId() {
		return juvenileProgramReferralId;
	}

	/**
	 * @param juvenileProgramReferralId the juvenileProgramReferralId to set
	 */
	public void setJuvenileProgramReferralId(String juvenileProgramReferralId) {
		this.juvenileProgramReferralId = juvenileProgramReferralId;
	}
	
	/**
	 * @return the caseFileId
	 */
	public String getCaseFileId() {
		return caseFileId;
	}

	/**
	 * @param caseFileId the caseFileId to set
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}

	/**
	 * @return the ackDate
	 */
	public Date getAckDate() {
		return ackDate;
	}

	/**
	 * @param ackDate the ackDate to set
	 */
	public void setAckDate(Date ackDate) {
		this.ackDate = ackDate;
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

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the assignedHours
	 */
	public String getAssignedHours() {
		return assignedHours;
	}

	/**
	 * @param assignedHours the assignedHours to set
	 */
	public void setAssignedHours(String assignedHours) {
		this.assignedHours = assignedHours;
	}

	/**
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * @param sentDate the sentDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
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
	 * @return the statusSubCd
	 */
	public String getStatusSubCd() {
		return statusSubCd;
	}

	/**
	 * @param statusSubCd the statusSubCd to set
	 */
	public void setStatusSubCd(String statusSubCd) {
		this.statusSubCd = statusSubCd;
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
	 * @return the outcomeDescCd
	 */
	public String getOutcomeDescCd() {
		return outcomeDescCd;
	}

	/**
	 * @param outcomeDescCd the outcomeDescCd to set
	 */
	public void setOutcomeDescCd(String outcomeDescCd) {
		this.outcomeDescCd = outcomeDescCd;
	}

	/**
	 * @return the providerProgramId
	 */
	public String getProviderProgramId() {
		return providerProgramId;
	}

	/**
	 * @param providerProgramId the providerProgramId to set
	 */
	public void setProviderProgramId(String providerProgramId) {
		this.providerProgramId = providerProgramId;
	}

	/**
	 * @return the lastActionDate
	 */
	public Date getLastActionDate() {
		return lastActionDate;
	}

	/**
	 * @param lastActionDate the lastActionDate to set
	 */
	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}

	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}

	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}

	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}

	public String getControllingReferralNum()
	{
	    return controllingReferralNum;
	}

	public void setControllingReferralNum(String controllingReferralNum)
	{
	    this.controllingReferralNum = controllingReferralNum;
	}

	public String getJuvenileNum()
	{
	    return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum)
	{
	    this.juvenileNum = juvenileNum;
	}

	public Date getProgramReferralAssignmentDate()
	{
	    return programReferralAssignmentDate;
	}

	public void setProgramReferralAssignmentDate(Date programReferralAssignmentDate)
	{
	    this.programReferralAssignmentDate = programReferralAssignmentDate;
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
	
	
}



