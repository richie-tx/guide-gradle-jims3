package messaging.interviewinfo.to;

import java.util.Date;

/**
 *
 */
public class JusticeOfPeaceReferralTO extends ExcludedTO
{
	private String referralNumber = "";
	private String assignmentLevel = "";
	private Date assignmentDate = null;
	private String unit = "";
	
	
	/**
	 * @return Returns the assignmentDate.
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	/**
	 * @param assignmentDate The assignmentDate to set.
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	/**
	 * @return Returns the assignmentLevel.
	 */
	public String getAssignmentLevel() {
		return assignmentLevel;
	}
	/**
	 * @param assignmentLevel The assignmentLevel to set.
	 */
	public void setAssignmentLevel(String assignmentLevel) {
		this.assignmentLevel = assignmentLevel;
	}
	
	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	/**
	 * @return Returns the unit.
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit The unit to set.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
