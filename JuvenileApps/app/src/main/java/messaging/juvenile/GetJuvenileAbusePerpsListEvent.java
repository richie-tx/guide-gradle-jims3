package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileAbusePerpsListEvent extends RequestEvent	 {

	public GetJuvenileAbusePerpsListEvent(){
	}
	private String abuseID;
	
	public String getAbuseID() {
		return abuseID;
	}

	public void setAbuseID(String abuseID) {
		this.abuseID = abuseID;
	}

	
	
	


}
