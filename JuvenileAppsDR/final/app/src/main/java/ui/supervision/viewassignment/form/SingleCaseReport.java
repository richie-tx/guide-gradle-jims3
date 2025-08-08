/*
 * Created on Feb 20, 2008
 */
package ui.supervision.viewassignment.form;

/**
 * @author cc_rbhat
 */
public class SingleCaseReport extends BaseCaseAssignmentReport {
	private String criminalCaseId;

	private String courtDivisionIndicator;


	/**
	 * @return Returns the courtDivisionIndicator.
	 */
	public String getCourtDivisionIndicator() {
		return courtDivisionIndicator;
	}
	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param courtDivisionIndicator The courtDivisionIndicator to set.
	 */
	public void setCourtDivisionIndicator(String courtDivisionIndicator) {
		this.courtDivisionIndicator = courtDivisionIndicator;
	}
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
}
