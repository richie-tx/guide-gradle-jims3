package messaging.administersupervisee;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeHistoriesEvent extends RequestEvent{

	private String defendant_id;

	public String getDefendant_id() {
		return defendant_id;
	}

	public void setDefendant_id(String defendant_id) {
		this.defendant_id = defendant_id;
	}
}
