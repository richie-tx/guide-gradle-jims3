/*
 * Created on Jan 17, 2008
 *
 */
package messaging.viewassignment;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_rbhat
 *  
 */
public class CaseAssignmentReportEvent extends RequestEvent {
	private String searchType;

	/**
	 * Program unit assignment start date
	 */
	private Date assignmentStartDate;

	/**
	 * Program unit assignment end date
	 */
	private Date assignmentEndDate;

	private List programUnitIds;

	private String workgroupId;

	private String defendantId;

	private String criminalCaseId;

	private String courtDivisionIndicator;

	/**
	 * User id of person who made the assignment/reassignment.
	 */
	private String acknowledgeUserId;

	/**
	 * @return Returns the acknowledgeUserId.
	 */
	public String getAcknowledgeUserId() {
		return acknowledgeUserId;
	}
	/**
	 * @return Returns the assignmentEndDate.
	 */
	public Date getAssignmentEndDate() {
		return assignmentEndDate;
	}
	/**
	 * @return Returns the assignmentStartDate.
	 */
	public Date getAssignmentStartDate() {
		return assignmentStartDate;
	}
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
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @return Returns the programUnitIds.
	 */
	public List getProgramUnitIds() {
		return programUnitIds;
	}
	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @return Returns the workgroupId.
	 */
	public String getWorkgroupId() {
		return workgroupId;
	}
	/**
	 * @param acknowledgeUserId The acknowledgeUserId to set.
	 */
	public void setAcknowledgeUserId(String acknowledgeUserId) {
		this.acknowledgeUserId = acknowledgeUserId;
	}
	/**
	 * @param assignmentEndDate The assignmentEndDate to set.
	 */
	public void setAssignmentEndDate(Date assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}
	/**
	 * @param assignmentStartDate The assignmentStartDate to set.
	 */
	public void setAssignmentStartDate(Date assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
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
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @param programUnitIds The programUnitIds to set.
	 */
	public void setProgramUnitIds(List programUnitIds) {
		this.programUnitIds = programUnitIds;
	}
	/**
	 * @param searchType The searchType to set.
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @param workgroupId The workgroupId to set.
	 */
	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}
}
