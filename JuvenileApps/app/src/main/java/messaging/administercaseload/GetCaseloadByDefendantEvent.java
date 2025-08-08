package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseloadByDefendantEvent extends RequestEvent {
	
	private String defendantId;

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}	
}
