// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetServiceEventsByProgramReferralEvent.java

package messaging.programreferral;

import mojo.km.messaging.RequestEvent;

public class GetProgramReferralsByServiceEventEvent extends RequestEvent {
	public String serviceEventId;
	public String juvenileNum;

	/**
	 * @roseuid 463BA4D003A2
	 */
	public GetProgramReferralsByServiceEventEvent() {

	}

	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
