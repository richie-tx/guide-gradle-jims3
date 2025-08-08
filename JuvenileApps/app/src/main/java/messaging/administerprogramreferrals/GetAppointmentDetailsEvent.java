package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetAppointmentDetailsEvent extends RequestEvent
{
	private String programReferralId;

	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}

	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	
	
}
