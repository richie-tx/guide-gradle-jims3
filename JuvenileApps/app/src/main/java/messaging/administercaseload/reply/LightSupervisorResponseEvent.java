package messaging.administercaseload.reply;

import mojo.km.messaging.ResponseEvent;

public class LightSupervisorResponseEvent extends ResponseEvent {
   
	private String divisionId;
	private String supervisorPositionId;
	private String staffPositionId;
	private String positionTypeCode;
	
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getSupervisorPositionId() {
		return supervisorPositionId;
	}
	public void setSupervisorPositionId(String supervisorPositionId) {
		this.supervisorPositionId = supervisorPositionId;
	}
	public String getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	public String getPositionTypeCode() {
		return positionTypeCode;
	}
	public void setPositionTypeCode(String positionTypeCode) {
		this.positionTypeCode = positionTypeCode;
	}
}
