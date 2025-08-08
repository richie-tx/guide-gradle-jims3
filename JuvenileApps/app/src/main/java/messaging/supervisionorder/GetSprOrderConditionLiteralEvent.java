/*
 * Created on Jan 25, 2008
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetSprOrderConditionLiteralEvent extends RequestEvent
{
	private int orderConditionId;
	/**
	 * @return Returns the orderConditionId.
	 */
	public int getOrderConditionId() {
		return orderConditionId;
	}
	/**
	 * @param orderConditionId The orderConditionId to set.
	 */
	public void setOrderConditionId(int orderConditionId) {
		this.orderConditionId = orderConditionId;
	}
}
