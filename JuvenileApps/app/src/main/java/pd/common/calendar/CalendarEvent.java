/*
 * Created on Oct 26, 2005
 *
 */
package pd.common.calendar;

import java.util.Date;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.common.calendar.CalendarEventContext;

/**
 * @author glyons
 * 
 */
public class CalendarEvent extends PersistentObject implements Cloneable
{
    private Date startDatetime;

    private Date endDatetime;

    /**
     * @return Returns the endDatetime.
     */
    public Date getEndDatetime()
    {
    	fetch();
        return endDatetime;
    }

    /**
     * @param endDatetime
     *            The endDatetime to set.
     */
    public void setEndDatetime(Date endDatetime)
    {
    	if(endDatetime != null){
	    	markModified();
	        this.endDatetime = endDatetime;
    	}
    }

    /**
     * @return Returns the startDatetime.
     */
    public Date getStartDatetime()
    {
    	fetch();
        return startDatetime;
    }

    /**
     * @param startDatetime
     *            The startDatetime to set.
     */
    public void setStartDatetime(Date startDatetime)
    {
    	if(startDatetime != null){
	    	markModified();
	    	this.startDatetime = startDatetime;
    	}
    }

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
     * Properties for calendarEventContexts
     * 
     * @referencedType pd.common.calendar.CalendarEventContext
     * @detailerDoNotGenerate true
     */
    private java.util.Collection calendarEventContexts = null;

    /**
     * @return bodyText
     */
    public String getBodyText()
    {
        return bodyText;
    }

    /**
     * @return calendarEventId
     */
    public Integer getCalendarEventId()
    {
        if (this.getOID() != null)
        {
            return new Integer(this.getOID() + "");
        }
        return null;
    }

    /**
     * @return calendarEventType
     */
    public String getCalendarEventType()
    {
        return calendarEventType;
    }

    /**
     * @return createdBy
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @return location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * @return status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @return subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @return timezone
     */
    public String getTimeZone()
    {
        return timeZone;
    }

    /**
     * @param string
     */
    public void setBodyText(String text)
    {
        bodyText = text;
    }

    /**
     * @param string
     */
    public void setCalendarEventId(Integer id)
    {
        this.setOID(String.valueOf(id));//calendarEventId = id;
    }

    /**
     * @param string
     */
    public void setCalendarEventType(String type)
    {
        calendarEventType = type;
    }

    /**
     * @param string
     */
    public void setCreatedBy(String created)
    {
        createdBy = created;
    }

    /**
     * @param string
     */
    public void setLocation(String loc)
    {
        location = loc;
    }

    /**
     * @param string
     */
    public void setStatus(String stat)
    {
        status = stat;
    }

    /**
     * @param string
     */
    public void setSubject(String subj)
    {
        subject = subj;
    }

    /**
     * @param string
     */
    public void setTimeZone(String zone)
    {
        this.timeZone = zone;
    }

    /**
     * returns a collection of pd.common.calendar.CalendarEventContext
     * 
     * @return Collection contexts
     */
    public java.util.Collection getCalendarEventContexts()
    {
        fetch();
        initCalendarEventContexts();
        return calendarEventContexts;
    }

    private void initCalendarEventContexts()
    {
        if (calendarEventContexts == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }
            calendarEventContexts = new mojo.km.persistence.ArrayList(CalendarEventContext.class,
                    "calendarEventId", this.getCalendarEventId() + "");
        }
    }

    //	/**
    //	* Clears all pd.common.calendar.CalendarEventContext from class
    // relationship collection.
    //	*/
    //	public void clearCalendarEventContexts()
    //	{
    //		initCalendarEventContexts();
    //		calendarEventContexts.clear();
    //	}
    //	
    //	/**
    //	* insert a pd.common.calendar.CalendarEventContext into class
    // relationship collection.
    //	* @param anObject
    //	*/
    //	public void
    // insertCalendarEventContexts(pd.common.calendar.CalendarEventContext
    // anObject)
    //	{
    //		initCalendarEventContexts();
    //		calendarEventContexts.add(anObject);
    //	}
    //	
    //	/**
    //	* Removes a pd.common.calendar.CalendarEventContext from class
    // relationship collection.
    //	* @param anObject
    //	*/
    //	public void
    // removeCalendarEventContexts(pd.common.calendar.CalendarEventContext
    // anObject)
    //	{
    //		initCalendarEventContexts();
    //		calendarEventContexts.remove(anObject);
    //	}

    public static CalendarEvent find(String calendarEventId)
    {
        IHome home = new Home();
        CalendarEvent calevent = (CalendarEvent) home.find(calendarEventId, CalendarEvent.class);
        return calevent;
    }

    /**
     * @return Returns the calendarEventDate.
     */
    public Date getCalendarEventDate()
    {
        return calendarEventDate;
    }

    /**
     * @param calendarEventDate
     *            The calendarEventDate to set.
     */
    public void setCalendarEventDate(Date calendarEventDate)
    {
        this.calendarEventDate = calendarEventDate;
    }

    /**
     * @return Returns the calendarEventSeriesId.
     */
    public Integer getCalendarEventSeriesId()
    {
        return calendarEventSeriesId;
    }

    /**
     * @param calendarEventSeriesId
     *            The calendarEventSeriesId to set.
     */
    public void setCalendarEventSeriesId(Integer calendarEventSeriesId)
    {
        this.calendarEventSeriesId = calendarEventSeriesId;
    }
}
