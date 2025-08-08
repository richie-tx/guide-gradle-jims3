package messaging.administerserviceprovider;

import java.util.Date;

import mojo.km.messaging.PersistentEvent;

public class SendJuvenileServiceProviderDocumentAttendanceNotificationEvent extends
		PersistentEvent {
	
	private String serviceEventId;
	private String programId;
	private String serviceProviderId;
	private String serviceId;
	private Date eventDate;
	
	public String getServiceEventId() {
		return serviceEventId;
	}
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
