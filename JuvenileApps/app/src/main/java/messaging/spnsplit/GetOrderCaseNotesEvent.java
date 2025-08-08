/**
 * 
 */
package messaging.spnsplit;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class GetOrderCaseNotesEvent extends RequestEvent 
{
	private List<String> orderIds;

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}
	
}
