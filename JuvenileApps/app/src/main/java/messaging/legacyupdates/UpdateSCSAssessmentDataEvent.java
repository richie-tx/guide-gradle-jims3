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
 */
public class UpdateSCSAssessmentDataEvent extends LegacyUpdatesRequestEvent {
    public void setScsClassification(String scsClassification) {
		this.scsClassification = scsClassification;
	}
	private String action;
	private String assessmentCode;
	private Date assignmentDate;
	private String scsClassification;
	public String getAction() {
		return action;
	}
	/**
	 * @return the assessmentCode
	 */
	public String getAssessmentCode() {
		return assessmentCode;
	}
	
    /**
	 * @return the assignmentDate
	 */
	public Date getAssessmentDate() {
		return assignmentDate;
	}
   
	public String getScsClassification() {
		return scsClassification;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @param assessmentCode the assessmentCode to set
	 */
	public void setAssessmentCode(String assessmentCode) {
		this.assessmentCode = assessmentCode;
	}
	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssessmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
}
