package messaging.posttrial;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateCaseAssignmentDataControlEvent extends RequestEvent {
	
	public Date getProgramUnitAssignmentDate() {
		return programUnitAssignmentDate;
	}
	public void setProgramUnitAssignmentDate(Date programUnitAssignmentDate) {
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public Date getPositionAssignmentDate() {
		return positionAssignmentDate;
	}
	public void setPositionAssignmentDate(Date positionAssignmentDate) {
		this.positionAssignmentDate = positionAssignmentDate;
	}
	public String getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	public String getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getAssignmentHistId() {
		return assignmentHistId;
	}
	public void setAssignmentHistId(String assignmentHistId) {
		this.assignmentHistId = assignmentHistId;
	}	
	
	private Date programUnitAssignmentDate;
	private String organizationId;
	private Date positionAssignmentDate;
	private String staffPositionId;
	private String assignmentId;
	private String assignmentHistId;	
}
