package messaging.calendar.reply;

import mojo.km.messaging.ResponseEvent;

public class ServiceAttendanceContextResponseEvent extends ResponseEvent{
    private Integer calendarEventId;
    private String serviceEventId;
	private String serviceEventAttendanceId;
    private int eventMaximum;
    private int eventMinimum;
    private String eventStatusId;
    private String eventTypeId;
    private int juvLocUnitId;
    private String eventComments;
    private int currentEnrollment;
    private int serviceId;
    private String probationOfficerId;
    private String juvenileId;
	private String attendanceStatusCd;
	private String probationOfficerLogonId;
	private String probationOfficerFirstName;
	private String probationOfficerMiddleName;
	private String probationOfficerLastName;
	public String juvenileFullName;
	private String juvenileFirstName;
	private String juvenileMiddleName;
	private String juvenileLastName;
	
	public Integer getCalendarEventId() {
		return calendarEventId;
	}
	public void setCalendarEventId(Integer calendarEventId) {
		this.calendarEventId = calendarEventId;
	}
	public String getServiceEventId() {
		return serviceEventId;
	}
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	public String getServiceEventAttendanceId() {
		return serviceEventAttendanceId;
	}
	public void setServiceEventAttendanceId(String serviceEventAttendanceId) {
		this.serviceEventAttendanceId = serviceEventAttendanceId;
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
	public String getEventStatusId() {
		return eventStatusId;
	}
	public void setEventStatusId(String eventStatusId) {
		this.eventStatusId = eventStatusId;
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
	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		this.attendanceStatusCd = attendanceStatusCd;
	}
	public String getProbationOfficerLogonId() {
		return probationOfficerLogonId;
	}
	public void setProbationOfficerLogonId(String probationOfficerLogonId) {
		this.probationOfficerLogonId = probationOfficerLogonId;
	}
	public String getProbationOfficerFirstName() {
		return probationOfficerFirstName;
	}
	public void setProbationOfficerFirstName(String probationOfficerFirstName) {
		this.probationOfficerFirstName = probationOfficerFirstName;
	}
	public String getProbationOfficerMiddleName() {
		return probationOfficerMiddleName;
	}
	public void setProbationOfficerMiddleName(String probationOfficerMiddleName) {
		this.probationOfficerMiddleName = probationOfficerMiddleName;
	}
	public String getProbationOfficerLastName() {
		return probationOfficerLastName;
	}
	public void setProbationOfficerLastName(String probationOfficerLastName) {
		this.probationOfficerLastName = probationOfficerLastName;
	}
	public String getJuvenileFullName() {
		return juvenileFullName;
	}
	public void setJuvenileFullName(String juvenileFullName) {
		this.juvenileFullName = juvenileFullName;
	}
	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}
	public void setJuvenileFirstName(String juvenileFirstName) {
		this.juvenileFirstName = juvenileFirstName;
	}
	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}
	public void setJuvenileMiddleName(String juvenileMiddleName) {
		this.juvenileMiddleName = juvenileMiddleName;
	}
	public String getJuvenileLastName() {
		return juvenileLastName;
	}
	public void setJuvenileLastName(String juvenileLastName) {
		this.juvenileLastName = juvenileLastName;
	}
	
}
