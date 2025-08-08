package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseAssignmentsByDefendantIdEvent extends RequestEvent {

	public GetCaseAssignmentsByDefendantIdEvent(){
		
	}
	
	private String defendantId;

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	
	
	
}
