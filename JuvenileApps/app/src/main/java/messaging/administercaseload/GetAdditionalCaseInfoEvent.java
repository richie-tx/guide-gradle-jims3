package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetAdditionalCaseInfoEvent extends RequestEvent {
	private String officerPositionId;
	
	public String getOfficerPositionId() {
		return officerPositionId;
	}

	public void setOfficerPositionId(String staffPositionId) {
		this.officerPositionId = staffPositionId;
	}
}
