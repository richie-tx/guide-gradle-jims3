/*
 * Created on Feb 28, 2008
 *
 */
package messaging.administerassessments.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_bjangay
 *
 */
public class AssessmentDetailsResponseEvent extends ResponseEvent
{
    private boolean sentToState;
    //	Used by Wisconsin
	private String riskScore; 
	private String RiskLevel; 	
	private String needsScore; 
	private String needsLevel; 	
	private String levelOfSupervision; 
	
	private String comments; //used by LSI-R, SCS
	
//	used by SCS
	private String totalSI;
	private String totalCC;
	private String totalES;
	private String totalLS;	
	private String primaryClassification;
	private String secondaryClassification;
	private String forceFieldId;
	
//	Used by all assessments
	private Collection questionAnswerList;
	
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * @return Returns the needsScore.
	 */
	public String getNeedsScore() {
		return needsScore;
	}
	/**
	 * @param needsScore The needsScore to set.
	 */
	public void setNeedsScore(String needsScore) {
		this.needsScore = needsScore;
	}
	/**
	 * @return Returns the primaryClassification.
	 */
	public String getPrimaryClassification() {
		return primaryClassification;
	}
	/**
	 * @param primaryClassification The primaryClassification to set.
	 */
	public void setPrimaryClassification(String primaryClassification) {
		this.primaryClassification = primaryClassification;
	}
	/**
	 * @return Returns the questionAnswerList.
	 */
	public Collection getQuestionAnswerList() {
		return questionAnswerList;
	}
	/**
	 * @param questionAnswerList The questionAnswerList to set.
	 */
	public void setQuestionAnswerList(Collection questionAnswerList) {
		this.questionAnswerList = questionAnswerList;
	}
	/**
	 * @return Returns the riskLevel.
	 */
	public String getRiskLevel() {
		return RiskLevel;
	}
	/**
	 * @param riskLevel The riskLevel to set.
	 */
	public void setRiskLevel(String riskLevel) {
		RiskLevel = riskLevel;
	}
	/**
	 * @return Returns the riskScore.
	 */
	public String getRiskScore() {
		return riskScore;
	}
	/**
	 * @param riskScore The riskScore to set.
	 */
	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}
	/**
	 * @return Returns the secondaryClassification.
	 */
	public String getSecondaryClassification() {
		return secondaryClassification;
	}
	/**
	 * @param secondaryClassification The secondaryClassification to set.
	 */
	public void setSecondaryClassification(String secondaryClassification) {
		this.secondaryClassification = secondaryClassification;
	}
	/**
	 * @return Returns the totalCC.
	 */
	public String getTotalCC() {
		return totalCC;
	}
	/**
	 * @param totalCC The totalCC to set.
	 */
	public void setTotalCC(String totalCC) {
		this.totalCC = totalCC;
	}
	/**
	 * @return Returns the totalLS.
	 */
	public String getTotalLS() {
		return totalLS;
	}
	/**
	 * @param totalLS The totalLS to set.
	 */
	public void setTotalLS(String totalLS) {
		this.totalLS = totalLS;
	}
/**
 * @return Returns the totalSI.
 */
public String getTotalSI() {
	return totalSI;
}
/**
 * @param totalSI The totalSI to set.
 */
public void setTotalSI(String totalSI) {
	this.totalSI = totalSI;
}
	/**
	 * @return Returns the totalES.
	 */
	public String getTotalES() {
		return totalES;
	}
	/**
	 * @param totalES The totalES to set.
	 */
	public void setTotalES(String totoalES) {
		this.totalES = totoalES;
	}
    /**
     * @return Returns the forceFieldId.
     */
    public String getForceFieldId() {
        return forceFieldId;
    }
    /**
     * @param forceFieldId The forceFieldId to set.
     */
    public void setForceFieldId(String forceFieldId) {
        this.forceFieldId = forceFieldId;
    }
    /**
     * @return Returns the sentToState.
     */
    public boolean isSentToState() {
        return sentToState;
    }
    /**
     * @param sentToState The sentToState to set.
     */
    public void setSentToState(boolean sentToState) {
        this.sentToState = sentToState;
    }
}
