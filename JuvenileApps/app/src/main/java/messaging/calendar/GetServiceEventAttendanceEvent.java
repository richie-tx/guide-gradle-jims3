//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetJuvenileAttendanceEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetServiceEventAttendanceEvent extends RequestEvent 
{
   public String serviceEventId;
   public String calendarEventId;
   
   
   /**
    * @roseuid 456F33E603CA
    */
   public GetServiceEventAttendanceEvent() 
   {
    
   }
   
   /**
    * Access method for the serviceEventId property.
    * 
    * @return   the current value of the serviceEventId property
    */
   public String getServiceEventId()
   {
      return serviceEventId;
   }


   

   /**
    * @param serviceEventId
    * @roseuid 456F2D8502A1
    */
   public void setServiceEventId(String serviceEventId) 
   {
    this.serviceEventId = serviceEventId;
   }
   

	/**
	 * @return Returns the calendarEventId.
	 */
	public String getCalendarEventId() {
		return calendarEventId;
	}
	/**
	 * @param calendarEventId The calendarEventId to set.
	 */
	public void setCalendarEventId(String calendarEventId) {
		this.calendarEventId = calendarEventId;
	}
}
