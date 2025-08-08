/*
 * Created on Jul 24, 2006
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;
import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CourtPolicyInUseResponseEvent extends ResponseEvent
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
