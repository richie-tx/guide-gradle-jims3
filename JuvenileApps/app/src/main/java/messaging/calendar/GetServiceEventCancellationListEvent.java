/*
 * Created on Feb 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NRaveendran
 *
 */
public class GetServiceEventCancellationListEvent extends RequestEvent {
	private String serviceEventId;
	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
}
