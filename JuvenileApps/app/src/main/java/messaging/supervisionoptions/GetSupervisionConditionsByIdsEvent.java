/*
 * Created on Sep 18, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSupervisionConditionsByIdsEvent extends RequestEvent {

	private Collection conditionIds = new ArrayList();
	private boolean searchInactive;
	
	/**
	 * @return Returns the searchInactive.
	 */
	public boolean isSearchInactive() {
		return searchInactive;
	}
	/**
	 * @param searchInactive The searchInactive to set.
	 */
	public void setSearchInactive(boolean searchInactive) {
		this.searchInactive = searchInactive;
	}
	/**
	 * @return Returns the conditionIds.
	 */
	public Collection getConditionIds() {
		return conditionIds;
	}
	/**
	 * @param conditionIds The conditionIds to set.
	 */
	public void setConditionIds(Collection conditionIds) {
		this.conditionIds = conditionIds;
	}

	/**
	 * @param conditionIds The conditionIds to set.
	 */
	public void insertConditionId(String conditionId) {
		this.conditionIds.add(conditionId);
	}
}
