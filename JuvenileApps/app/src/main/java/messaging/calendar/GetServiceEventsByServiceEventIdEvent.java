package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetServiceEventsByServiceEventIdEvent extends RequestEvent {
	private Integer serviceEventId;
	
	public GetServiceEventsByServiceEventIdEvent() {
		
	}

	public Integer getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(Integer serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

}
