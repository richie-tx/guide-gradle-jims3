/*
 * Created on Apr 7, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCSSpnViewConditionsEvent extends RequestEvent {
	private String spnId;

	public GetCSSpnViewConditionsEvent() {

	}

	/**
	 * @return Returns the spnId.
	 */
	public String getSpnId() {
		return spnId;
	}

	/**
	 * @param spnId The spnId to set.
	 */
	public void setSpnId(String spnId) {
		this.spnId = spnId;
	}
}
