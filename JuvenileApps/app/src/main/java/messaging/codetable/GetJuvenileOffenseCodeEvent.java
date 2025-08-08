package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileOffenseCodeEvent extends RequestEvent{
	private String alphaCode;

	public String getAlphaCode() {
		return alphaCode;
	}

	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}
}
