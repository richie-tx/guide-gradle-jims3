// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetProgramReferralListEvent.java

package messaging.programreferral;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetProgramReferralListEvent extends RequestEvent {

	private List referralAttributes;
	private boolean detailsNeeded = false;
	/**
	 * @return Returns the referralAttributes.
	 */
	public List getReferralAttributes() {
		return referralAttributes;
	}
	/**
	 * @param referralAttributes The referralAttributes to set.
	 */
	public void setReferralAttributes(List referralAttributes) {
		this.referralAttributes = referralAttributes;
	}
	/**
	 * @return Returns the detailsNeeded.
	 */
	public boolean isDetailsNeeded() {
		return detailsNeeded;
	}
	/**
	 * @param detailsNeeded The detailsNeeded to set.
	 */
	public void setDetailsNeeded(boolean detailsNeeded) {
		this.detailsNeeded = detailsNeeded;
	}
}
