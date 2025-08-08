/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.productionsupport.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;


/**
 * @author rcarter
 *
 */
public class ProductionSupportRiskAnalysisResponseEvent extends ResponseEvent
{
	private String riskAnalysisId = "";
	private Integer finalScore;
	private String assessmentType;
	private String juvenileId;
	private String jpoUserId;
	private String caseFileId;
	private Date dateEntered;
	private String recommendation = "";
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	public ProductionSupportRiskAnalysisResponseEvent()
	{
	}

	/**
	 * @return the riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}

	/**
	 * @param riskAnalysisId the riskAnalysisId to set
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	/**
	 * @return the finalScore
	 */
	public Integer getFinalScore() {
		return finalScore;
	}

	/**
	 * @param finalScore the finalScore to set
	 */
	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

	/**
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		return assessmentType;
	}

	/**
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return the jpoUserId
	 */
	public String getJpoUserId() {
		return jpoUserId;
	}

	/**
	 * @param jpoUserId the jpoUserId to set
	 */
	public void setJpoUserId(String jpoUserId) {
		this.jpoUserId = jpoUserId;
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
	 * @return the dateEntered
	 */
	public Date getDateEntered() {
		return dateEntered;
	}

	/**
	 * @param dateEntered the dateEntered to set
	 */
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	/**
	 * @return the recommendation
	 */
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * @param recommendation the recommendation to set
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

}
