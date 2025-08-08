package messaging.facility;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileFacilityMaxTJPCSeqNumEvent extends RequestEvent{
	
	private String tjpcSeqNum;
	private String lcDate;
	private String lcUser;
	public GetJuvenileFacilityMaxTJPCSeqNumEvent(){
		
	}
	
	
	/**
	 * @return the tjpcSeqNum
	 */
	public String getTjpcSeqNum() {
		return tjpcSeqNum;
	}


	/**
	 * @param tjpcSeqNum the tjpcSeqNum to set
	 */
	public void setTjpcSeqNum(String tjpcSeqNum) {
		this.tjpcSeqNum = tjpcSeqNum;
	}


	public String getLcDate() {
		return lcDate;
	}


	public void setLcDate(String lcDate) {
		this.lcDate = lcDate;
	}


	public String getLcUser() {
		return lcUser;
	}


	public void setLcUser(String lcUser) {
		this.lcUser = lcUser;
	}


	
	
}
