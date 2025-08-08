/*
 * Created on Aug 22, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common.calendar;

import mojo.km.persistence.PersistentObject;
import java.util.Collection;
import java.util.Date;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
/**
 * @author racooper
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CalendarEventSeries extends PersistentObject implements Cloneable {
	private Date startDatetime;
	private Date endDatetime;
	private String frequency;
	private Collection calendarEvents;
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
	 * @return Returns the frequency.
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency The frequency to set.
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
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
	public void removeFutureEvents(Date startDatetime) {
		
	}
	public Integer getCalendarEventSeriesId() {
		if (this.getOID() != null) {
			return new Integer(this.getOID() + "");
		}
		return null;
	}
	public void setCalendarEvents(Collection calendarEvents) {
		this.calendarEvents = calendarEvents;
	}
	public Collection getCalendarEvents() {
		fetch();
		initCalendarEvents();
		return calendarEvents;
	}

	private void initCalendarEvents() {
		if (calendarEvents == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			calendarEvents = new mojo.km.persistence.ArrayList(CalendarEvent.class,
					"calendarEventSeriesId", this.getCalendarEventSeriesId() + "");
		}
	}
	/**
	 * @param string
	 * @return
	 */
	public static CalendarEventSeries find(String calendarEventSeriesId) {
		IHome home = new Home();
		CalendarEventSeries caleventSeries = (CalendarEventSeries) home.find(calendarEventSeriesId, CalendarEventSeries.class);
		return caleventSeries;
	}
}
