// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetProgramReferralsByProviderIDEvent.java

package messaging.programreferral;

import mojo.km.messaging.RequestEvent;

public class GetProgramReferralsByProviderIDEvent extends RequestEvent {
	public String serviceProviderId;

	/**
	 * @roseuid 463BA4D00354
	 */
	public GetProgramReferralsByProviderIDEvent() {

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
