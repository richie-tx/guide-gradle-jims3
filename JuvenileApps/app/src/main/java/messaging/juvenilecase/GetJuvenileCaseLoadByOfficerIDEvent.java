package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCaseLoadByOfficerIDEvent extends RequestEvent {
	private String officerId;
	   

	/**
	 * @return the officerId
	 */
	public String getOfficerId() {
		return officerId;
	}

	/**
	 * @param officerId the officerId to set
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}	
}
