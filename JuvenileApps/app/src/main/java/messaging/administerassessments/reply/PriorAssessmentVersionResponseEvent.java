/*
 * Created on Feb 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_bjangay
 *
 */
public class PriorAssessmentVersionResponseEvent extends ResponseEvent
{
	private Date transactionDate;
	private Date assessmentDate;
	private String assessmentId; // OID of the assessment
	private String versionNumber; 
	
	
	/**
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	/**
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	/**
	 * @return Returns the assessmentId.
	 */
	public String getAssessmentId() {
		return assessmentId;
	}
	/**
	 * @param assessmentId The assessmentId to set.
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	/**
	 * @return Returns the transactionDate.
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @param transactionDate The transactionDate to set.
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	/**
	 * @return Returns the versionNumber.
	 */
	public String getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber The versionNumber to set.
	 */
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
}
