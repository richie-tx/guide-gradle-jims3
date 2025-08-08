package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;

public class GetCSFieldVisitIdEvent extends RequestEvent
{
	private String csEventId;

	/**
	 * @return the csEventId
	 */
	public String getCsEventId() {
		return csEventId;
	}

	/**
	 * @param csEventId the csEventId to set
	 */
	public void setCsEventId(String csEventId) {
		this.csEventId = csEventId;
	}
}
