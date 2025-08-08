/*
 * Created on Apr 11, 2007
 *
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class CourtRequestEvent extends RequestEvent {
	private String courtId;
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
}
