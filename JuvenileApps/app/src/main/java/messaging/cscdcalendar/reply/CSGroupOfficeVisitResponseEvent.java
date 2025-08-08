//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\ResponseEvents\\CSGroupOfficeVisitResponseEvent.java

package messaging.cscdcalendar.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;


public class CSGroupOfficeVisitResponseEvent  extends ResponseEvent {
   
	private Date eventDate;
	private String eventType;
	private String eventName;
	private String startTime;
	private String endTime;
	private Collection officeVisits;
	private boolean atleaseOneEventOpen;
	private boolean atleaseOneEventClosed;
	
   
	/**
	 * @roseuid 47A2367000C8
	 */
	public CSGroupOfficeVisitResponseEvent() {
    
	}
	
	
	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return Returns the eventName.
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName The eventName to set.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return Returns the officeVisits.
	 */
	public Collection getOfficeVisits() {
		return officeVisits;
	}
	/**
	 * @param officeVisits The officeVisits to set.
	 */
	public void setOfficeVisits(Collection officeVisits) {
		this.officeVisits = officeVisits;
	}
	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * @return Returns the atleaseOneEventClosed.
	 */
	public boolean isAtleaseOneEventClosed() {
		return atleaseOneEventClosed;
	}
	/**
	 * @param atleaseOneEventClosed The atleaseOneEventClosed to set.
	 */
	public void setAtleaseOneEventClosed(boolean atleaseOneEventClosed) {
		this.atleaseOneEventClosed = atleaseOneEventClosed;
	}
	/**
	 * @return Returns the atleaseOneEventOpen.
	 */
	public boolean isAtleaseOneEventOpen() {
		return atleaseOneEventOpen;
	}
	/**
	 * @param atleaseOneEventOpen The atleaseOneEventOpen to set.
	 */
	public void setAtleaseOneEventOpen(boolean atleaseOneEventOpen) {
		this.atleaseOneEventOpen = atleaseOneEventOpen;
	}
	
	public Collection getSortedOfficeVisits(){

        List tempList = (ArrayList)officeVisits;

        Collections.sort(tempList);

        return tempList;  

        

  }




}
