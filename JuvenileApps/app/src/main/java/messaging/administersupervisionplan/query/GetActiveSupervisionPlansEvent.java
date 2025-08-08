package messaging.administersupervisionplan.query;

import mojo.km.messaging.RequestEvent;

public class GetActiveSupervisionPlansEvent extends RequestEvent
{
	private String defendantId;
	private String statusCd;
	
	
	public String getDefendantId() {
		return defendantId;
	}
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
}
