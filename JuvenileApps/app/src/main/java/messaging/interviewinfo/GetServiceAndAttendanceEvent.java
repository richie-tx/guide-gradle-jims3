package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetServiceAndAttendanceEvent extends RequestEvent {
	private String calEventId;

	public String getCalEventId() {
		return calEventId;
	}

	public void setCalEventId(String calEventId) {
		this.calEventId = calEventId;
	}	

}
