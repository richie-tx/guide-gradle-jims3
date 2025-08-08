/*
 * Created on Feb 14, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.cscdcalendar;

import java.util.Date;

import messaging.calendar.ICalendarAttribute;
import mojo.km.messaging.RequestEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCSEventRetrieverEvent extends RequestEvent {
	
	private Date startDatetime;
	private Date endDatetime;
	private ICalendarAttribute[] attributes = new ICalendarAttribute[0];
	
	/**
	 * Return the Start Date and Time to retrieve
	 * @return date
	 */
	public Date getStartDatetime() {
		return startDatetime;
	}
	
	/**
	 * Set the start date time to retrieve
	 * @param startDate
	 */
	public void setStartDatetime(Date startDate) {
		startDatetime = startDate;
	}
	
	/**
	 * Return the End Date and Time to retrieve
	 * @return date
	 */
	public Date getEndDatetime() {
		return endDatetime;
	}
	
	/**
	 * Set the end date time to retrieve
	 * @param endDate
	 */
	public void setEndDatetime(Date endDate) {
		endDatetime = endDate;
	}	
	
	/**
	 * Return the custom attributes that will be used for 
	 * generating the sql.  These are custom columns that
	 * can be used to constrain the sql results 
	 * @return attributes
	 */
	public ICalendarAttribute[] getCalendarAttributes() {
		return attributes;
	}
	
	/**
	 * Set the attributes that will constrain the sql that
	 * is generated for specific columns
	 * @param atts
	 */
	public void setCalendarAttributes(ICalendarAttribute[] atts) {
		attributes = atts;
	}

}
