// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetProgramReferralDetailsEvent.java

package messaging.programreferral;

import mojo.km.messaging.RequestEvent;

public class GetProgramReferralDetailsEvent extends RequestEvent {
	public String programReferralId;

	/**
	 * @roseuid 463BA4D002B8
	 */
	public GetProgramReferralDetailsEvent() {

	}

	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}

	/**
	 * @param programReferralId
	 *            The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
}
