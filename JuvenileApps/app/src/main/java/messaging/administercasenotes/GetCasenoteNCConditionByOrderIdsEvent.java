package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenoteNCConditionByOrderIdsEvent extends RequestEvent {
    private String orderIds;

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
}
