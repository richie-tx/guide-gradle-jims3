package messaging.juvenile;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * 
 * @author sthyagarajan
 * SaveJuvenileGangsEvent
 */
public class SaveJuvenileGangsEvent extends CompositeRequest {
	public String juvenileNum;

	/**
	 * Default constructor.
	 */
	public SaveJuvenileGangsEvent() {

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
