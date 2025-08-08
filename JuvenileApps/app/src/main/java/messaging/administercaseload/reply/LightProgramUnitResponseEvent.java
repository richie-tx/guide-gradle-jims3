package messaging.administercaseload.reply;

import mojo.km.messaging.ResponseEvent;

public class LightProgramUnitResponseEvent extends ResponseEvent {
   
	private String divisionId;
	private String programUnitId;
	private String programUnitName;
	private String stateReportingCD;
	
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getProgramUnitId() {
		return programUnitId;
	}
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	public String getProgramUnitName() {
		return programUnitName;
	}
	public void setProgramUnitName(String programUnitName) {
		this.programUnitName = programUnitName;
	}
	public String getStateReportingCD() {
		return stateReportingCD;
	}
	public void setStateReportingCD(String stateReportingCD) {
		this.stateReportingCD = stateReportingCD;
	}
}
