/*
 * Created on Dec 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoveNonCompliantEventEvent extends RequestEvent{
    
	private String nonComplianceEventId;
	private String sprOrderConditionId;
	
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public String getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(String sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
	/**
	 * @return Returns the nonComplianceEventId.
	 */
	public String getNonComplianceEventId() {
		return nonComplianceEventId;
	}
	/**
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(String nonComplianceEventId) {
		this.nonComplianceEventId = nonComplianceEventId;
	}
}
