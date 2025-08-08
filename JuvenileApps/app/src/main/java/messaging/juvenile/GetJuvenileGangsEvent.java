package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan 
 * GetJuvenileGangsEvent
 */
public class GetJuvenileGangsEvent extends RequestEvent {

	private String juvenileNum;

	/**
	 * Default constructor.
	 */
	public GetJuvenileGangsEvent() {

	}

	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
