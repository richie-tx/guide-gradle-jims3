//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\SaveServiceEventAttendanceEvent.java

package messaging.calendar;

import java.util.Collection;
import java.util.Date;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.messaging.RequestEvent;

public class SaveServiceEventAttendanceEvent extends RequestEvent 
{
   
	private String serviceEventId;
	private Date eventStartDate;
	private String eventCategory;	
	public CalendarServiceEventResponseEvent currentEvent;
	
	
	
	private Collection juvenileAttendanceEvents;
	
	
	
   
	   /**
	    * @roseuid 45702FFC0393
	    */
	   public SaveServiceEventAttendanceEvent() 
	   {
	    
	   }
     
	/**
	 * @return Returns the eventCategory.
	 */
	public String getEventCategory() {
		return eventCategory;
	}
	/**
	 * @param eventCategory The eventCategory to set.
	 */
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	/**
	 * @return Returns the eventStartDate.
	 */
	public Date getEventStartDate() {
		return eventStartDate;
	}
	/**
	 * @param eventStartDate The eventStartDate to set.
	 */
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	/**
	 * @return Returns the juvenileAttendanceEvents.
	 */
	public Collection getJuvenileAttendanceEvents() {
		return juvenileAttendanceEvents;
	}
	/**
	 * @param juvenileAttendanceEvents The juvenileAttendanceEvents to set.
	 */
	public void setJuvenileAttendanceEvents(Collection juvenileAttendanceEvents) {
		this.juvenileAttendanceEvents = juvenileAttendanceEvents;
	}
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
	
	public CalendarServiceEventResponseEvent getCurrentEvent()
	{
	    return currentEvent;
	}

	public void setCurrentEvent(CalendarServiceEventResponseEvent currentEvent)
	{
	    this.currentEvent = currentEvent;
	}

}
