// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetSPProgramReferralListEvent.java

package messaging.programreferral;

import mojo.km.messaging.RequestEvent;

public class GetSPProgramReferralListEvent extends RequestEvent {
	public String serviceProviderId;

	/**
	 * @roseuid 463BA4D10009
	 */
	public GetSPProgramReferralListEvent() {

	}

	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId
	 *            The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
}
