package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class SaveProductionSupportCalendarServiceEventsEvent extends RequestEvent 
{
   
	private String serviceEventId;
	private String maxAttendance;
	private String eventStatusCode;
	private String juvlocationUnitId;
	
	
	
	
   
   /**
    * @roseuid 45702FFC0393
    */
   public SaveProductionSupportCalendarServiceEventsEvent() 
   {
    
   }

   /**
    * 
    * @return
    */
	public String getServiceEventId() {
		return serviceEventId;
	}

	/**
	 * 
	 * @param serviceEventId
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	/**
	 * 
	 * @return
	 */
	public String getMaxAttendance() {
		return maxAttendance;
	}

	/**
	 * 
	 * @param maxAttendance
	 */
	public void setMaxAttendance(String maxAttendance) {
		this.maxAttendance = maxAttendance;
	}

	/**
	 * @return the eventStatusCode
	 */
	public String getEventStatusCode() {
		return eventStatusCode;
	}

	/**
	 * @param eventStatusCode the eventStatusCode to set
	 */
	public void setEventStatusCode(String eventStatusCode) {
		this.eventStatusCode = eventStatusCode;
	}

	/**
	 * @return the juvlocationUnitId
	 */
	public String getJuvlocationUnitId() {
		return juvlocationUnitId;
	}

	/**
	 * @param juvlocationUnitId the juvlocationUnitId to set
	 */
	public void setJuvlocationUnitId(String juvlocationUnitId) {
		this.juvlocationUnitId = juvlocationUnitId;
	}
	   
}
