package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;

public class GetCSFVSuperviseeDetailsEvent extends RequestEvent {	
	
	private String superviseeId;
	

	public GetCSFVSuperviseeDetailsEvent() {

	}
	
	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	
	

}
