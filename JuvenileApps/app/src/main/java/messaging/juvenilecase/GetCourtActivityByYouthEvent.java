package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCourtActivityByYouthEvent extends RequestEvent{

	
	private String juvenileNum;
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
}
