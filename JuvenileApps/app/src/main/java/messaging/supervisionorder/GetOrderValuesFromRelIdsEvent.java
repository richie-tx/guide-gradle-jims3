/*
 * Created on Oct 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetOrderValuesFromRelIdsEvent extends RequestEvent{

	private Collection relIds = new ArrayList();
	/**
	 * @return Returns the relIds.
	 */
	public Collection getRelIds() {
		return relIds;
	}
	/**
	 * @param relIds The relIds to set.
	 */
	public void setRelIds(Collection relIds) {
		this.relIds = relIds;
	}
}
