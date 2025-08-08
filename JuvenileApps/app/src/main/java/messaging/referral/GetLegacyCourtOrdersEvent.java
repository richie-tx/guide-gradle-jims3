package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetLegacyCourtOrdersEvent extends RequestEvent
{
	public String juvenileNum;
	public String petitionNum;
	
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getPetitionNum() {
		return petitionNum;
	}
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}

}
