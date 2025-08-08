package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeNOfficerDetailsEvent extends RequestEvent
{
	private String defendantId;
	private String criminalCaseId;

	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

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
