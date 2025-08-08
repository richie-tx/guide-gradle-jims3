//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarServiceEventsEvent.java

package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportCalendarServiceEventsEvent  extends RequestEvent
{
	/**
	 * Constructor is used to set the default retriever for this event. It can be changed later if
	 * required.
	 * 
	 */
	public String serviceEventId;
	
	public GetProductionSupportCalendarServiceEventsEvent()
	{
		
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	
	

}