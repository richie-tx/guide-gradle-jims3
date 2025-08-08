/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendaring.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author racooper
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CalendarResponseEvent extends ResponseEvent {

	private Date startDatetime;

	private Date endDatetime;
	private String calendarEventType;

	private String subject;

	private String bodyText;

	private String location;

	private String status;

	private String createdBy;

	private String timeZone;

	private Integer calendarEventSeriesId;

	private Date calendarEventDate;

	/**
	 * @return Returns the endDatetime.
	 */
	public Date getEndDatetime() {
		return endDatetime;
	}
	/**
	 * @param endDatetime The endDatetime to set.
	 */
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	/**
	 * @return Returns the startDatetime.
	 */
	public Date getStartDatetime() {
		return startDatetime;
	}
	/**
	 * @param startDatetime The startDatetime to set.
	 */
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	

	 
	/**
	 * @return bodyText
	 */
	public String getBodyText() {
		return bodyText;
	}

	 
	 
	public String getCalendarEventType() {
		return calendarEventType;
	}

	/**
	 * @return createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return timezone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param string
	 */
	public void setBodyText(String text) {
		bodyText = text;
	}

	 
	/**
	 * @param string
	 */
	public void setCalendarEventType(String type) {
		calendarEventType = type;
	}

	/**
	 * @param string
	 */
	public void setCreatedBy(String created) {
		createdBy = created;
	}

	/**
	 * @param string
	 */
	public void setLocation(String loc) {
		location = loc;
	}

	/**
	 * @param string
	 */
	public void setStatus(String stat) {
		status = stat;
	}

	/**
	 * @param string
	 */
	public void setSubject(String subj) {
		subject = subj;
	}

	/**
	 * @param string
	 */
	public void setTimeZone(String zone) {
		this.timeZone = zone;
	}
}


