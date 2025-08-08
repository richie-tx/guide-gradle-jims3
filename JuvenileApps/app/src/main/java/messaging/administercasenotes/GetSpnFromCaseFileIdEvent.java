package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetSpnFromCaseFileIdEvent extends RequestEvent{
	private String caseFileId;

	public String getCaseFileId() {
		return caseFileId;
	}

	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}

}
