package messaging.viewassignment;

import mojo.km.messaging.ResponseEvent;

public class GetUserProfileInfoResponseEvent extends ResponseEvent {
	private String userId;
	private String officerName;
	private String poi;
	private String positionName;
	private String divisionName;
	private String programUnitName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getPoi() {
		return poi;
	}
	public void setPoi(String poi) {
		this.poi = poi;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getProgramUnitName() {
		return programUnitName;
	}
	public void setProgramUnitName(String programUnitName) {
		this.programUnitName = programUnitName;
	}
}
