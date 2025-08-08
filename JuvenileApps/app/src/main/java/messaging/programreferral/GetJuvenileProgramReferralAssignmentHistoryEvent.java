package messaging.programreferral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileProgramReferralAssignmentHistoryEvent extends RequestEvent {
	public String programReferralId;
	
	/**
	 * @roseuid 463BA4D002B8
	 */
	public GetJuvenileProgramReferralAssignmentHistoryEvent() {

	}

	public String getProgramReferralId() {
		return programReferralId;
	}

	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
}
