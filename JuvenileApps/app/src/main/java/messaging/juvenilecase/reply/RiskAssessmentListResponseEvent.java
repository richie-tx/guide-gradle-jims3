/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskAssessmentListResponseEvent extends ResponseEvent implements Comparable
{
	private Date assessmentDate = null;
	private String	assessmentType = "";
	private String assessmentTypeDesc;
	private String recommendation;
	private String assessmentID;
	private String casefileId;
	private List recommendations;
	private int recommendationsSizeMinusOne;
	private boolean completed;
	private String finalScores;
	private String juvenileNumber;
	private String jpoUserId;
	private String finalScore;
	private boolean effectiveDate;
	
    
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	/**
	 * @return
	 */
	public Date getAssessmentDate()
	{
		return assessmentDate;
	}

	/**
	 * @return
	 */
	public String getAssessmentID()
	{
		return assessmentID;
	}

	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}

	/**
	 * @param date
	 */
	public void setAssessmentDate(final Date date)
	{
		assessmentDate = date;
	}

	/**
	 * @param string
	 */
	public void setAssessmentID(final String string)
	{
		assessmentID = string;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(final String string)
	{
		assessmentType = string;
	}

	public int compareTo(Object riskListResponseEvent) throws ClassCastException {
		if (!(riskListResponseEvent instanceof RiskAssessmentListResponseEvent))
			 throw new ClassCastException("A RiskAssessmentListResponseEvent object expected.");
	   	Date riskAssessmentDate = ((RiskAssessmentListResponseEvent) riskListResponseEvent).getAssessmentDate();  
		if(this.assessmentDate.compareTo(riskAssessmentDate)<0)
			return -1;
		else if(this.assessmentDate.compareTo(riskAssessmentDate)>0)
			return 1;
		else
			return 0;
	 }

	/**
	 * @return Returns the recommendation.
	 */
	public String getRecommendation() {
		return recommendation;
	}
	/**
	 * @param recommendation The recommendation to set.
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	public String getCasefileId() {
		return casefileId;
	}
	
	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}

	public List getRecommendations() {
		return recommendations;
	}
	
	public int getRecommendationsSizeMinusOne() {
		
		if (recommendations != null) {
			return recommendations.size() - 1;
		} else {
			return 0;
		}
		
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setAssessmentTypeDesc(String assessmentTypeDesc) {
		this.assessmentTypeDesc = assessmentTypeDesc;
	}

	public String getAssessmentTypeDesc() {
		return assessmentTypeDesc;
	}

	/**
	 * @return the finalScores
	 */
	public String getFinalScores() {
		return finalScores;
	}

	/**
	 * @param finalScores the finalScores to set
	 */
	public void setFinalScores(String finalScores) {
		this.finalScores = finalScores;
	}

	/**
	 * @return the juvenileNumber
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	/**
	 * @param juvenileNumber the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
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
	 * @return the finalScore
	 */
	public String getFinalScore() {
		return finalScore;
	}

	/**
	 * @param finalScore the finalScore to set
	 */
	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
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

	public boolean isEffectiveDate()
	{
	    return effectiveDate;
	}

	public void setEffectiveDate(boolean effectiveDate)
	{
	    this.effectiveDate = effectiveDate;
	}
	
}
