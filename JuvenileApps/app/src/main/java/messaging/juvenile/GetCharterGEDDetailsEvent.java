package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetCharterGEDDetailsEvent extends RequestEvent
{
	public String juvenileCharterGEDId;

	public String getJuvenileCharterGEDId() {
		return juvenileCharterGEDId;
	}

	public void setJuvenileCharterGEDId(String juvenileCharterGEDId) {
		this.juvenileCharterGEDId = juvenileCharterGEDId;
	}
}
