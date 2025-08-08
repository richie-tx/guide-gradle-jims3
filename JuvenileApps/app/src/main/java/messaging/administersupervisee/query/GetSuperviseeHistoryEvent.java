package messaging.administersupervisee.query;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeHistoryEvent extends RequestEvent {
	private String superviseeId;

	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
}
