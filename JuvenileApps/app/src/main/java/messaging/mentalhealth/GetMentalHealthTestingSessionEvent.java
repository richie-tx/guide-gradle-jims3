package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthTestingSessionEvent extends RequestEvent {
	public String juvenileNum;
	public String serviceEventId;

	/**
	 * @roseuid 45D4AF5E00B5
	 */
	public GetMentalHealthTestingSessionEvent() {

	}

	public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
}
