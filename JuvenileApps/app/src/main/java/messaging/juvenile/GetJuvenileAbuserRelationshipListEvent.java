package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileAbuserRelationshipListEvent extends RequestEvent	 {

	public GetJuvenileAbuserRelationshipListEvent(){
	}
	private String abuseID;
	
	public String getAbuseID() {
		return abuseID;
	}

	public void setAbuseID(String abuseID) {
		this.abuseID = abuseID;
	}

	
	
	


}
