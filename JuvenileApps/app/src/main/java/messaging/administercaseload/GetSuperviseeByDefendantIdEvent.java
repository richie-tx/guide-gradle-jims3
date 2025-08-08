package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeByDefendantIdEvent extends RequestEvent {

	public GetSuperviseeByDefendantIdEvent(){
		
	}
	
	private String defendantId;

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	
	
	
}
