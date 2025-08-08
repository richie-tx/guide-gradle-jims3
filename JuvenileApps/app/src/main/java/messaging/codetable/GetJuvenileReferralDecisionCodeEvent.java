package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileReferralDecisionCodeEvent extends RequestEvent{
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
