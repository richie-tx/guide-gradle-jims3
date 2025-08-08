/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.legacyupdates;

import java.util.Date;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateRiskNeedScoreAssessmentDataEvent extends LegacyUpdatesRequestEvent {
    private String action;
	private Date assessmentDate;
	private String assessmentType;
	private String needsScore;
	private String riskScore;
    public String getAction() {
		return action;
	}
    /**
	 * @return the assessmentDate
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}
    
    /**
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		return assessmentType;
	}
   
	/**
	 * @return the needsScore
	 */
	public String getNeedsScore() {
		return needsScore;
	}
	
	/**
	 * @return the riskScore
	 */
	public String getRiskScore() {
		return riskScore;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @param assessmentDate the assessmentDate to set
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	
	/**
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	/**
	 * @param needsScore the needsScore to set
	 */
	public void setNeedsScore(String needsScore) {
		this.needsScore = needsScore;
	}
	/**
	 * @param riskScore the riskScore to set
	 */
	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}
}
