package messaging.casefile;

import mojo.km.messaging.RequestEvent;

public class GetCasefileNonComplianceNoticesEvent extends RequestEvent
{
	private String casefileId;

	public String getCasefileId() {
		return casefileId;
	}

	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}
