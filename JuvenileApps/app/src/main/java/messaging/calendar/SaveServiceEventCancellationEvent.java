/*
 * Created on Feb 19, 2007
 *
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NRaveendran
 *
 */
public class SaveServiceEventCancellationEvent extends RequestEvent {
	
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
