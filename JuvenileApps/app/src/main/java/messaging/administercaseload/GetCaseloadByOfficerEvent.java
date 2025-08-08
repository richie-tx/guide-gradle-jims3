package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseloadByOfficerEvent extends RequestEvent {
	
	private String officerPositionId;

	public String getOfficerPositionId() {
		return officerPositionId;
	}

	public void setOfficerPositionId(String officerPositionId) {
		this.officerPositionId = officerPositionId;
	}
}
