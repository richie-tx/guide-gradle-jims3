package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class RetrieveJuvenileProgramReferralsEvent extends RequestEvent {
	public String casefileId;

	/**
	 * @roseuid 463BA4D003A2
	 */
	public RetrieveJuvenileProgramReferralsEvent() {

	}

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}

	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}


}
