package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenoteConditionByOrderIdsEvent extends RequestEvent {
    private String orderIds;

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
}
