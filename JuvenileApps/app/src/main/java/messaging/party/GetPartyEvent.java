// Source file:
//C:\\views\\CommonSupervision\\app\\src\\messaging\\party\\GetPartyEvent.java

package messaging.party;



import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class GetPartyEvent extends RequestEvent {
	
	public Collection spns;
	
	/**
	 * @roseuid 452E795E0365
	 */
	public GetPartyEvent() {

	}

	/**
	 * @return Returns the spns.
	 */
	public Collection getSpns() {
		return spns;
	}
	/**
	 * @param spns The spns to set.
	 */
	public void setSpns(Collection spns) {
		this.spns = spns;
	}
}
