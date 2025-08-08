/*
 * Created on Nov 6, 2006
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetSprOrderConditionRelByOrderConditionIdEvent extends RequestEvent {
    private int sprOrderConditionId;
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public int getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(int sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
}
