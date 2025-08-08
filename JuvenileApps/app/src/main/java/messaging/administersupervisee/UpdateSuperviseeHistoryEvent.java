/**
 * 
 */
package messaging.administersupervisee;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class UpdateSuperviseeHistoryEvent extends RequestEvent 
{
	private String superviseeHistoryId;
	private String superviseeId;
	private String caseloadCreditStaffPositionId;
	private String assignedProgramUnitId;
	private String assignedStaffPositionId;
	private String programUnitAssignmentDate;
	private String updateAction;
	
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}
	public String getSuperviseeId() {
		return superviseeId;
	}
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	public String getCaseloadCreditStaffPositionId() {
		return caseloadCreditStaffPositionId;
	}
	public void setCaseloadCreditStaffPositionId(
			String caseloadCreditStaffPositionId) {
		this.caseloadCreditStaffPositionId = caseloadCreditStaffPositionId;
	}
	public String getAssignedProgramUnitId() {
		return assignedProgramUnitId;
	}
	public void setAssignedProgramUnitId(String assignedProgramUnitId) {
		this.assignedProgramUnitId = assignedProgramUnitId;
	}
	public String getAssignedStaffPositionId() {
		return assignedStaffPositionId;
	}
	public void setAssignedStaffPositionId(String assignedStaffPositionId) {
		this.assignedStaffPositionId = assignedStaffPositionId;
	}
	public String getProgramUnitAssignmentDate() {
		return programUnitAssignmentDate;
	}
	public void setProgramUnitAssignmentDate(String programUnitAssignmentDate) {
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}
	public String getUpdateAction() {
		return updateAction;
	}
	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}
	
}
