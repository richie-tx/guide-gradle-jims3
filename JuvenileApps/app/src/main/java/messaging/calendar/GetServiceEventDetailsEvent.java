package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetServiceEventDetailsEvent extends RequestEvent {
	private String serviceEventId;

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
}
