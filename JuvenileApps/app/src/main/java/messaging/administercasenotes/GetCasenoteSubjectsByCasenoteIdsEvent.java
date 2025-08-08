package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenoteSubjectsByCasenoteIdsEvent extends RequestEvent{
	private String casenoteIds;

	public String getCasenoteIds() {
		return casenoteIds;
	}

	public void setCasenoteIds(String casenoteIds) {
		this.casenoteIds = casenoteIds;
	}
}
