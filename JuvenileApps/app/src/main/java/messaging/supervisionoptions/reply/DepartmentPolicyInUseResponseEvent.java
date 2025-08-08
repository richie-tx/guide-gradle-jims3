/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DepartmentPolicyInUseResponseEvent extends ResponseEvent
{
	Collection courtIds = new ArrayList();

	/**
	 * @return Returns the courtIds.
	 */
	public Collection getCourtIds() {
		return courtIds;
	}

	/**
	 * @param courtIds The courtIds to set.
	 */
	public void setCourtIds(Collection courtIds) {
		this.courtIds = courtIds;
	}

	/**
	 * @param courtIds The courtIds to set.
	 */
	public void insertCourtId(String courtId) {
		this.courtIds.add(courtId);
	}

}
