package messaging.legacyupdates;

import mojo.km.messaging.RequestEvent;

public class GetLegacyUpdateRecordsToFixEvent extends RequestEvent {
	private String recType;

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getRecType() {
		return recType;
	}

}
