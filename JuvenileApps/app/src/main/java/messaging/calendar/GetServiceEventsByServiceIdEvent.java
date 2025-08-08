/*
 * Created on Oct 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceEventsByServiceIdEvent  extends RequestEvent {

	private int serviceId;
	
	public GetServiceEventsByServiceIdEvent() {
		
	}
	
	/**
	 * @return Returns the serviceId.
	 */
	public int getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}
