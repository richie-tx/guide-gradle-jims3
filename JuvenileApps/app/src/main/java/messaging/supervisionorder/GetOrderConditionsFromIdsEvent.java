/*
 * Created on Oct 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionorder;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetOrderConditionsFromIdsEvent extends RequestEvent{
	public List orderConditionIds = new ArrayList(); 

	/**
	 * @return Returns the orderConditionIds.
	 */
	public List getOrderConditionIds() {
		return orderConditionIds;
	}
	/**
	 * @param orderConditionIds The orderConditionIds to set.
	 */
	public void setOrderConditionIds(List orderConditionIds) {
		this.orderConditionIds = orderConditionIds;
	}
}
