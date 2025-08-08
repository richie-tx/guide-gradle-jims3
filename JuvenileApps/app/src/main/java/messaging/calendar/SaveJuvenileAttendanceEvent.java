//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetJuvenileAttendanceEvent.java

package messaging.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.messaging.RequestEvent;

public class SaveJuvenileAttendanceEvent extends RequestEvent 
{
   public String serviceEventId;
   public String juvenileId;
   public String attendanceStatusCd;
   public String progressNotes;
   public Date eventStartDate;
   public String eventCategory;
   public CalendarServiceEventResponseEvent currentEvent;
   private boolean appendProgressNotes = true;
   
 //Activity JIMS200057658 - Document number of add'l attendees
	private int addlAttendees;
	
	private List addlAttendeeNames= new ArrayList();
   
   /**
    * @roseuid 456F33E603CA
    */
   public SaveJuvenileAttendanceEvent() 
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
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return Returns the attendanceStatusCd.
	 */
	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}
	/**
	 * @param attendanceStatusCd The attendanceStatusCd to set.
	 */
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		this.attendanceStatusCd = attendanceStatusCd;
	}
	/**
	 * @return Returns the progressNotes.
	 */
	public String getProgressNotes() {
		return progressNotes;
	}
	/**
	 * @param progressNotes The progressNotes to set.
	 */
	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
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

	public int getAddlAttendees() {
		return addlAttendees;
	}

	public void setAddlAttendees(int addlAttendees) {
		this.addlAttendees = addlAttendees;
	}

	public List getAddlAttendeeNames() {
		return addlAttendeeNames;
	}

	public void setAddlAttendeeNames(List addlAttendeeNames) {
		this.addlAttendeeNames = addlAttendeeNames;
	}

	public void setAppendProgressNotes(boolean appendProgressNotes) {
		this.appendProgressNotes = appendProgressNotes;
	}

	public boolean isAppendProgressNotes() {
		return appendProgressNotes;
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
