package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

public class ValidateNCResponseStatusResponseEvent extends ResponseEvent {
    private String ncResponseId;

	/**
	 * @return the ncResponseId
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}

	/**
	 * @param ncResponseId the ncResponseId to set
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
}
