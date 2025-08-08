// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\GetCalendarServiceEventDetailsEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetCalendarServiceEventDetailsEvent extends RequestEvent {
	public String serviceEventId;

	/**
	 * @roseuid 463BA4D0026A
	 */
	public GetCalendarServiceEventDetailsEvent() {

	}

	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}

	/**
	 * @param serviceEventId
	 *            The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
}
