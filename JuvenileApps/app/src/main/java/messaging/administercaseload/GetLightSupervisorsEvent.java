package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetLightSupervisorsEvent extends RequestEvent {
	private String logonId;
	private String agencyId;
	private String staffPositionId;
	private String divisionId;
	private String supervisorId;
	private boolean supportStaffNeeded;
	
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	private boolean officerInfoNeeded;
	
	public boolean isOfficerInfoNeeded() {
		return officerInfoNeeded;
	}
	public void setOfficerInfoNeeded(boolean officerInfoNeeded) {
		this.officerInfoNeeded = officerInfoNeeded;
	}
	public String getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	public String getLogonId() {
		return logonId;
	}
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public boolean isSupportStaffNeeded() {
		return supportStaffNeeded;
	}
	public void setSupportStaffNeeded(boolean supportStaffNeeded) {
		this.supportStaffNeeded = supportStaffNeeded;
	}
}
