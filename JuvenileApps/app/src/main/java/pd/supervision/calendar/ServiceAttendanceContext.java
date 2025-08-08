package pd.supervision.calendar;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.common.calendar.CalendarEvent;

public class ServiceAttendanceContext extends CalendarEvent{
    private Integer calendarEventId;
    private Integer serviceEventId;
	private Integer serviceEventAttendanceId;
    private int eventMaximum;
    private int eventMinimum;
    private Code eventStatus = null;
    private String eventStatusId;
    private JuvenileEventTypeCode eventType = null;
    private String eventTypeId;
    private int juvLocUnitId;
    private String eventComments;
    private int currentEnrollment;
    private int serviceId;
    private String probationOfficerId;
    private String juvenileId;
	private Code attendanceStatus;
	private String attendanceStatusCd;
	
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return (Iterator) home.findAll(event, ServiceAttendanceContext.class);
    }

    static public Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, ServiceAttendanceContext.class);
    }

    public ServiceAttendanceContext()
    {
    }

	public Integer getCalendarEventId() {
		return calendarEventId;
	}

	public void setCalendarEventId(Integer calendarEventId) {
		this.calendarEventId = calendarEventId;
	}

	public Integer getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(Integer serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	public Integer getServiceEventAttendanceId() {
		return serviceEventAttendanceId;
	}

	public void setServiceEventAttendanceId(Integer serviceEventAttendanceId) {
		this.serviceEventAttendanceId = serviceEventAttendanceId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getProbationOfficerId() {
		return probationOfficerId;
	}

	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}

	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	public String getEventComments() {
		return eventComments;
	}

	public void setEventComments(String eventComments) {
		this.eventComments = eventComments;
	}

	public int getCurrentEnrollment() {
		return currentEnrollment;
	}

	public void setCurrentEnrollment(int currentEnrollment) {
		this.currentEnrollment = currentEnrollment;
	}

	public int getEventMaximum() {
		return eventMaximum;
	}

	public void setEventMaximum(int eventMaximum) {
		this.eventMaximum = eventMaximum;
	}

	public int getEventMinimum() {
		return eventMinimum;
	}

	public void setEventMinimum(int eventMinimum) {
		this.eventMinimum = eventMinimum;
	}

	public Code getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(Code eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventStatusId() {
		return eventStatusId;
	}

	public void setEventStatusId(String eventStatusId) {
		this.eventStatusId = eventStatusId;
	}

	public JuvenileEventTypeCode getEventType() {
		return eventType;
	}

	public void setEventType(JuvenileEventTypeCode eventType) {
		this.eventType = eventType;
	}

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getJuvLocUnitId() {
		return juvLocUnitId;
	}

	public void setJuvLocUnitId(int juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}

	public Code getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(Code attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}

	public void setAttendanceStatusCd(String attendanceStatusCd) {
		this.attendanceStatusCd = attendanceStatusCd;
	}

}
