package messaging.administercasenotes.reply;

import mojo.km.messaging.ResponseEvent;

public class SpnFromCaseFileIdResponseEvent extends ResponseEvent{
	
	private String spn;

	public String getSpn() {
		return spn;
	}

	public void setSpn(String spn) {
		this.spn = spn;
	}

}
