/*
 * Created on Dec 22, 2007
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetSprOrderConditionCasenotesEvent extends RequestEvent
{
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
}
