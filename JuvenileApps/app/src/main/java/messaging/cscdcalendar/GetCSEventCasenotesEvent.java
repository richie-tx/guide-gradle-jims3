package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;

public class GetCSEventCasenotesEvent extends RequestEvent
{
	private String csEventId;

	/**
	 * @return the programReferralId
	 */
	public String getCsEventId() {
		return csEventId;
	}

	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setCsEventId(String programReferralId) {
		this.csEventId = programReferralId;
	}
	
	
}
