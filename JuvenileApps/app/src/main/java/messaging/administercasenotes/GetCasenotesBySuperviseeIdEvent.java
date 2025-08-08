/*
 * Created on May 7, 2007
 */
package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 */
public class GetCasenotesBySuperviseeIdEvent extends RequestEvent {
    private String superviseeId;
	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
}
