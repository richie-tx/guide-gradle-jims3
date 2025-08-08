/*
 * Created on Feb 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.form;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WisconsinAssessmentForm extends AssessmentTypeForm
{
//	set to "INITIAL_ASSESSMENT" or "REASSESSMENT"
	private String wisconsinAssessmentType = "";

	private Collection riskAssessmentQuestionsList = new ArrayList();	
	private Collection needsAssessmentQuestionsList = new ArrayList();
		
	private String totalRiskScore = "";	
	private String riskLevel = "";
	
	private String totalNeedsScore = "";
	private String needsLevel = "";
	
	private String levelOfSupervisionCd = "";
	private String levelOfSupervision = "";

//	set to "INITIAL_ASSESSMENT" or "REASSESSMENT"
	private String selectedAssessmentType = "";
	
	private String taskId = "";

	private String wisconsinScreenType = "";
	
	public void clear()
	{
		action = "";
		secondaryAction = "";
		
		defendantId = "";
		supervisionBeginDate = null;
		supervisionEndDate = null;
		
		supervisionPeriod = "";
		
		assessmentTypeId = "";
		masterAssessmentId = "";
		
		assessmentIncomplete = false;
		
		versionId = "";
		assessmentId = "";
		
		assessmentDate = null;
		assessmentDateStr = "";
		
		islatestVersionShown = false;
		
		versionDetailsList = new ArrayList(); // map of prior version with versionId - key, and a collection of transaction date, assessment date and assessmentId(OID) - maintain an Inner class for the collection
		questionAnswerList = new ArrayList();
		
		wisconsinAssessmentType = "";
		
		riskAssessmentQuestionsList = new ArrayList();	
		needsAssessmentQuestionsList = new ArrayList();
		
		totalRiskScore = "";	
		riskLevel = "";
		
		migrated ="";
		totalNeedsScore = "";
		needsLevel = "";
		
		levelOfSupervisionCd = "";
		levelOfSupervision = "";	
		
		wisconsinScreenType = "";
		
		sentToState = false;
	}
	
	
	public void clearSelected()
	{
		selectedAssessmentBeanId = "";
		selectedAssessSupervisionPrd = "";
		selectedAssessmentType = "";
		selectedVersionNumber = "";
		selectedAssessmentBeanObj = null;
		taskId = "";
	}
	
	public void clearAll()
	{
		clear();
		clearSelected();
	}
	
	
	
	/**
	 * @return Returns the levelOfSupervisionCd.
	 */
	public String getLevelOfSupervisionCd() {
		return levelOfSupervisionCd;
	}
	/**
	 * @param levelOfSupervisionCd The levelOfSupervisionCd to set.
	 */
	public void setLevelOfSupervisionCd(String levelOfSupervisionCd) {
		this.levelOfSupervisionCd = levelOfSupervisionCd;
	}
	/**
	 * @return Returns the wisconsinScreenType.
	 */
	public String getWisconsinScreenType() {
		return wisconsinScreenType;
	}
	/**
	 * @param wisconsinScreenType The wisconsinScreenType to set.
	 */
	public void setWisconsinScreenType(String wisconsinScreenType) {
		this.wisconsinScreenType = wisconsinScreenType;
	}
	/**
	 * @return Returns the levelOfSupervision.
	 */
	public String getLevelOfSupervision() {
		return levelOfSupervision;
	}
	/**
	 * @param levelOfSupervision The levelOfSupervision to set.
	 */
	public void setLevelOfSupervision(String levelOfSupervision) {
		this.levelOfSupervision = levelOfSupervision;
	}
	/**
	 * @return Returns the needsAssessmentQuestionsList.
	 */
	public Collection getNeedsAssessmentQuestionsList() {
		return needsAssessmentQuestionsList;
	}
	/**
	 * @param needsAssessmentQuestionsList The needsAssessmentQuestionsList to set.
	 */
	public void setNeedsAssessmentQuestionsList(Collection needsAssessmentQuestionsList) {
		this.needsAssessmentQuestionsList = needsAssessmentQuestionsList;
	}
	/**
	 * @return Returns the needsLevel.
	 */
	public String getNeedsLevel() {
		return needsLevel;
	}
	/**
	 * @param needsLevel The needsLevel to set.
	 */
	public void setNeedsLevel(String needsLevel) {
		this.needsLevel = needsLevel;
	}
	/**
	 * @return Returns the riskAssessmentQuestionsList.
	 */
	public Collection getRiskAssessmentQuestionsList() {
		return riskAssessmentQuestionsList;
	}
	/**
	 * @param riskAssessmentQuestionsList The riskAssessmentQuestionsList to set.
	 */
	public void setRiskAssessmentQuestionsList(Collection riskAssessmentQuestionsList) {
		this.riskAssessmentQuestionsList = riskAssessmentQuestionsList;
	}
	/**
	 * @return Returns the riskLevel.
	 */
	public String getRiskLevel() {
		return riskLevel;
	}
	/**
	 * @param riskLevel The riskLevel to set.
	 */
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	/**
	 * @return Returns the selectedAssessmentType.
	 */
	public String getSelectedAssessmentType() {
		return selectedAssessmentType;
	}
	/**
	 * @param selectedAssessmentType The selectedAssessmentType to set.
	 */
	public void setSelectedAssessmentType(String selectedAssessmentType) {
		this.selectedAssessmentType = selectedAssessmentType;
	}
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return Returns the totalNeedsScore.
	 */
	public String getTotalNeedsScore() {
		return totalNeedsScore;
	}
	/**
	 * @param totalNeedsScore The totalNeedsScore to set.
	 */
	public void setTotalNeedsScore(String totalNeedsScore) {
		this.totalNeedsScore = totalNeedsScore;
	}
	/**
	 * @return Returns the totalRiskScore.
	 */
	public String getTotalRiskScore() {
		return totalRiskScore;
	}
	/**
	 * @param totalRiskScore The totalRiskScore to set.
	 */
	public void setTotalRiskScore(String totalRiskScore) {
		this.totalRiskScore = totalRiskScore;
	}
	/**
	 * @return Returns the wisconsinAssessmentType.
	 */
	public String getWisconsinAssessmentType() {
		return wisconsinAssessmentType;
	}
	/**
	 * @param wisconsinAssessmentType The wisconsinAssessmentType to set.
	 */
	public void setWisconsinAssessmentType(String wisconsinAssessmentType) {
		this.wisconsinAssessmentType = wisconsinAssessmentType;
	}
}
