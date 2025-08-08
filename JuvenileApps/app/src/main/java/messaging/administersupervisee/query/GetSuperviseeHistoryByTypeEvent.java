package messaging.administersupervisee.query;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeHistoryByTypeEvent extends RequestEvent {
	private String superviseeId;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
}
