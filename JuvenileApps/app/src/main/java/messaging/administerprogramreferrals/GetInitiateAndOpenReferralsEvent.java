package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetInitiateAndOpenReferralsEvent extends RequestEvent
{
	private String spn;

	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}

	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	
	
}
