package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetLegacyCourtOrderDetailsEvent extends RequestEvent
{
	public String courtOrderID;

	public String getCourtOrderID() {
		return courtOrderID;
	}

	public void setCourtOrderNumber(String courtOrderID) {
		this.courtOrderID = courtOrderID;
	}
	
}
