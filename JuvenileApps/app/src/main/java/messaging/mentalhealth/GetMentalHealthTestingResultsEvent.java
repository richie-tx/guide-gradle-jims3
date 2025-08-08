// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthTestingResultsEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthTestingResultsEvent extends RequestEvent {
	public String juvenileNum;

	/**
	 * @roseuid 45D4AF5E00B5
	 */
	public GetMentalHealthTestingResultsEvent() {

	}

	/**
	 * @param juvenileNum
	 * @roseuid 45D36FDB001D
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 45D36FDB002E
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

}
