/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetJuvLocationUnitDetailsEvent extends RequestEvent {
	public String juvLocUnitId;

	/**
	 * @return Returns the juvLocUnitId.
	 */
	public String getJuvLocUnitId() {
		return juvLocUnitId;
	}

	/**
	 * @param juvLocUnitId
	 *            The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(String juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}
}
