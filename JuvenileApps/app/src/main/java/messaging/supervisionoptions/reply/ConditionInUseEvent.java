/*
 * Created on May 17, 2006
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class ConditionInUseEvent extends ResponseEvent
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
