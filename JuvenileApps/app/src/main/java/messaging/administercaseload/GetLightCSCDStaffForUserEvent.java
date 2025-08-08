package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetLightCSCDStaffForUserEvent extends RequestEvent {
	private String logonId;
	private String staffPositionId;
	private boolean officerNameNeeded;
	private boolean supervisorNameNeeded;
	private boolean supervisorSupervisorNeeded;	
	private boolean courtsNeeded;
	
	public boolean isCourtsNeeded() {
		return courtsNeeded;
	}

	public void setCourtsNeeded(boolean courtsNeeded) {
		this.courtsNeeded = courtsNeeded;
	}

	public String getStaffPositionId() {
		return staffPositionId;
	}

	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	
	public boolean isSupervisorSupervisorNeeded() {
		return supervisorSupervisorNeeded;
	}

	public void setSupervisorSupervisorNeeded(boolean supervisorSupervisorNeeded) {
		this.supervisorSupervisorNeeded = supervisorSupervisorNeeded;
	}

	public boolean isSupervisorNameNeeded() {
		return supervisorNameNeeded;
	}

	public void setSupervisorNameNeeded(boolean supervisorNameNeeded) {
		this.supervisorNameNeeded = supervisorNameNeeded;
	}

	public boolean isOfficerNameNeeded() {
		return officerNameNeeded;
	}

	public void setOfficerNameNeeded(boolean officerNameNeeded) {
		this.officerNameNeeded = officerNameNeeded;
	}

	public String getLogonId() {
		return logonId;
	}

	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
}
