package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetLightProgramUnitsForDivisionEvent extends RequestEvent {
	private String divisionId;

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
}
