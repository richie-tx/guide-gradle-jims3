package messaging.cscdcalendar.reply;

import mojo.km.messaging.ResponseEvent;

public class CSFieldVisitIdResponseEvent extends ResponseEvent 
{
	private String csFieldVisitId;

	/**
	 * @return the csFieldVisitId
	 */
	public String getCsFieldVisitId() {
		return csFieldVisitId;
	}

	/**
	 * @param csFieldVisitId the csFieldVisitId to set
	 */
	public void setCsFieldVisitId(String csFieldVisitId) {
		this.csFieldVisitId = csFieldVisitId;
	}
}
