/*
 * Created on Feb 20, 2008
 */
package ui.supervision.viewassignment.form;

import java.util.List;

/**
 * @author cc_rbhat
 */
public class BaseCaseAssignmentReport {
	private String assignmentStartDate;

	private String assignmentEndDate;

	private List searchResults;
	
	private String noOfCases;	
		
	/**
	 * @return Returns the assignmentEndDate.
	 */
	public String getAssignmentEndDate() {
		return assignmentEndDate;
	}
	/**
	 * @return Returns the assignmentStartDate.
	 */
	public String getAssignmentStartDate() {
		return assignmentStartDate;
	}
	/**
	 * @return Returns the noOfCases.
	 */
	public String getNoOfCases() {
		return noOfCases;
	}
	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}
	/**
	 * @param assignmentEndDate The assignmentEndDate to set.
	 */
	public void setAssignmentEndDate(String assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}
	/**
	 * @param assignmentStartDate The assignmentStartDate to set.
	 */
	public void setAssignmentStartDate(String assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
	}
	/**
	 * @param noOfCases The noOfCases to set.
	 */
	public void setNoOfCases(String noOfCases) {
		this.noOfCases = noOfCases;
	}
	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}
}
