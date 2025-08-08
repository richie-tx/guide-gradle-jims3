/*
 * Created on Feb 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments;

import java.util.Date;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentVersion implements Comparable
{
	private Date transactionDate = null;
	private Date assessmentDate = null;
	
	private String transactionDateStr = "";
	private String assessmentDateStr = "";
	
	private String assessmentId = ""; // OID of the assessment
	private String versionNumber = "";
	
	
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
	 * @return Returns the assessmentDateStr.
	 */
	public String getAssessmentDateStr() {
		return assessmentDateStr;
	}
	/**
	 * @param assessmentDateStr The assessmentDateStr to set.
	 */
	public void setAssessmentDateStr(String assessmentDateStr) {
		this.assessmentDateStr = assessmentDateStr;
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
	 * @return Returns the transactionDateStr.
	 */
	public String getTransactionDateStr() {
		return transactionDateStr;
	}
	/**
	 * @param transactionDateStr The transactionDateStr to set.
	 */
	public void setTransactionDateStr(String transactionDateStr) {
		this.transactionDateStr = transactionDateStr;
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
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		if((o == null) && (!(o instanceof AssessmentVersion)))
		{
			return 1;
		}
		AssessmentVersion versionObj = (AssessmentVersion)o;
		int versionInt1 = -1;
		int versionInt2 = -2;
		
		try
		{
		versionInt1 = Integer.parseInt(this.versionNumber);
		versionInt2 = Integer.parseInt(versionObj.getVersionNumber());
		}
		catch(Exception ex)
		{
			
		}
		
		if(versionInt1 == versionInt2)
		{
			return 0;
		}
		else
			if(versionInt1 > versionInt2)
			{
				return -1;
			}
		else
		{
			return 1;
		}
	}
}
