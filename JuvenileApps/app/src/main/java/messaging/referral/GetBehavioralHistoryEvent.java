// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\referral\\GetBehavioralHistoryEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetBehavioralHistoryEvent extends RequestEvent {
	public String juvenileNum;

	/**
	 * @roseuid 453E36EE0166
	 */
	public GetBehavioralHistoryEvent() {

	}

	/**
	 * Access method for the juvenileNum property.
	 * 
	 * @return the current value of the juvenileNum property
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * Sets the value of the juvenileNum property.
	 * 
	 * @param aJuvenileNum
	 *            the new value of the juvenileNum property
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		juvenileNum = aJuvenileNum;
	}
}
