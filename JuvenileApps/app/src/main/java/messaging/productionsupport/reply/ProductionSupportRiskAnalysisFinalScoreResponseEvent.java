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
public class ProductionSupportRiskAnalysisFinalScoreResponseEvent extends ResponseEvent
{
	private String riskAnalysisFinalScoreId = "";
	private String riskAnalysisId = "";
	private Integer finalScore;
	private String riskResultGroupId = "";
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	public ProductionSupportRiskAnalysisFinalScoreResponseEvent()
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
	 * @return the riskAnalysisFinalScoreId
	 */
	public String getRiskAnalysisFinalScoreId() {
		return riskAnalysisFinalScoreId;
	}

	/**
	 * @param riskAnalysisFinalScoreId the riskAnalysisFinalScoreId to set
	 */
	public void setRiskAnalysisFinalScoreId(String riskAnalysisFinalScoreId) {
		this.riskAnalysisFinalScoreId = riskAnalysisFinalScoreId;
	}

	/**
	 * @return the riskResultGroupId
	 */
	public String getRiskResultGroupId() {
		return riskResultGroupId;
	}

	/**
	 * @param riskResultGroupId the riskResultGroupId to set
	 */
	public void setRiskResultGroupId(String riskResultGroupId) {
		this.riskResultGroupId = riskResultGroupId;
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
