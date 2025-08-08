// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetServiceEventsByProgramReferralEvent.java

package messaging.programreferral;

import mojo.km.messaging.RequestEvent;

public class GetServiceEventsByProgramReferralEvent extends RequestEvent {
	public String programReferralId;

	/**
	 * @roseuid 463BA4D003A2
	 */
	public GetServiceEventsByProgramReferralEvent() {

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
