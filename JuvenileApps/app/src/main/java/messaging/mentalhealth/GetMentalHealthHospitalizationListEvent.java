// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthHospitalizationListEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthHospitalizationListEvent extends RequestEvent {
	public String juvenileNum;

	/**
	 * @roseuid 45B0DCAB01D4
	 */
	public GetMentalHealthHospitalizationListEvent() {

	}

	/**
	 * @param juvenileNum
	 * @roseuid 45B0CB7601F5
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 45B0CB760206
	 */

	public String getJuvenileNum() {
		return juvenileNum;
	}

}
