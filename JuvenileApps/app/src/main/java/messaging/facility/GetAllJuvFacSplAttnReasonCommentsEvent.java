package messaging.facility;

import mojo.km.messaging.RequestEvent;

public class GetAllJuvFacSplAttnReasonCommentsEvent extends RequestEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		
	private String juvenileNum;


	/**
	 * @return the juvenileNumber
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNumber the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}