package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeCaseEvent extends RequestEvent
{
	private String criminalCaseId;

	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	
}
