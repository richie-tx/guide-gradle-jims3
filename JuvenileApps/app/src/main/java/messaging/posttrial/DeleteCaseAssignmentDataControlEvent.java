package messaging.posttrial;

import mojo.km.messaging.RequestEvent;

public class DeleteCaseAssignmentDataControlEvent extends RequestEvent {
    private String caseAssignmentHistId;

	public String getCaseAssignmentHistId() {
		return caseAssignmentHistId;
	}

	public void setCaseAssignmentHistId(String caseAssignmentHistId) {
		this.caseAssignmentHistId = caseAssignmentHistId;
	}
}
