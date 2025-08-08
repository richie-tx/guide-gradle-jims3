package messaging.calendar;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class CancelEventsListEvent extends RequestEvent {

	private String juvenileId;

	private List serviceEventIdList;
	
	private String serviceEventId;
	
	private String searchType;
	
	private String eventType;
	
	private String eventTypeCategory;
	
	private String eventTypeCode;
	

	public List getServiceEventIdList() {
		return serviceEventIdList;
	}

	public void setServiceEventIdList(List serviceEventIdList) {
		this.serviceEventIdList = serviceEventIdList;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTypeCategory() {
		return eventTypeCategory;
	}

	public void setEventTypeCategory(String eventTypeCategory) {
		this.eventTypeCategory = eventTypeCategory;
	}

	public String getEventTypeCode() {
		return eventTypeCode;
	}

	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}
	
}
