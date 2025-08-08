package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenoteAssociatesByCasenoteIdsEvent extends RequestEvent{
	private String casenoteIds;

	public String getCasenoteIds() {
		return casenoteIds;
	}

	public void setCasenoteIds(String casenoteIds) {
		this.casenoteIds = casenoteIds;
	}
}
