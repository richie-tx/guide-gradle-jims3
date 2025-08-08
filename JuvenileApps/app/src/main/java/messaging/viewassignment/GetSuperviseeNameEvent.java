/*
 * Created on Mar 7, 2008
 */
package messaging.viewassignment;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_rbhat
 */
public class GetSuperviseeNameEvent extends RequestEvent {
	
	String defendantId;

	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}
