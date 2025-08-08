//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\CreateCalendarServiceEventEvent.java

package messaging.calendar;

import java.util.Collection;

public class CreateCalendarServiceEventEvent extends CalendarEventRequestEvent
{
	private int eventMaximum;

	private int eventMinimum;

	private String eventTypeId;

	private String eventStatusId;

	private String instructorId;

	private String locationId;

	private String facilityId;

	private String serviceId;

	private String serviceName; 

	private String eventComments;
	
	private String progressNotes;
	
	private Collection events;

	private String eventTypeCategory;

	private String interviewId;

	private String schoolCd;

	private String schoolDistrictId;

	private String memberAddressId;

	private CalendarContextEvent calendarContextEvent;
	
	private int addlAttendees;

	private Collection additionalAttendeeNames = null;
	
	//<KISHORE>JIMS200059078 : Calendar: Add new event type Job Visit (PD) - KK
	private String memberEmploymentId;
	
	private String contactLastName;
	
	private String contactFirstName;
	
	private Object document;
	
	private String docTypeCd;
	
	private String sexOffender;
	
	private String restrictOther;
	
	private String weaponDescs;

	/**
	 * @return
	 */
	public int getEventMaximum()
	{
		return eventMaximum;
	}

	/**
	 * @return
	 */
	public int getEventMinimum()
	{
		return eventMinimum;
	}

	/**
	 * @return
	 */
	public String getEventStatusId()
	{
		return eventStatusId;
	}

	/**
	 * @return
	 */
	public String getEventTypeId()
	{
		return eventTypeId;
	}

	/**
	 * @param i
	 */
	public void setEventMaximum(int i)
	{
		eventMaximum = i;
	}

	/**
	 * @param i
	 */
	public void setEventMinimum(int i)
	{
		eventMinimum = i;
	}

	/**
	 * @param string
	 */
	public void setEventStatusId(String string)
	{
		eventStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setEventTypeId(String string)
	{
		eventTypeId = string;
	}

	/**
	 * @return Returns the instructorId.
	 */
	public String getInstructorId()
	{
		return instructorId;
	}

	/**
	 * @param instructorId
	 *            The instructorId to set.
	 */
	public void setInstructorId(String instructorId)
	{
		this.instructorId = instructorId;
	}

	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * @param locationId
	 *            The locationId to set.
	 */
	public void setLocationId(String locationId)
	{
		this.locationId = locationId;
	}

	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId()
	{
		return serviceId;
	}

	/**
	 * @param serviceId
	 *            The serviceId to set.
	 */
	public void setServiceId(String serviceId)
	{
		this.serviceId = serviceId;
	}

	/**
	 * @return
	 */
	public String getServiceName()
	{
		return serviceName;
	}

	/**
	 * @param string
	 */
	public void setServiceName(String string)
	{
		serviceName = string;
	}

	/**
	 * @return
	 */
	public String getEventComments()
	{
		return eventComments;
	}

	/**
	 * @param string
	 */
	public void setEventComments(String eventComments)
	{
		this.eventComments = eventComments;
	}

	/**
	 * @return Returns the events.
	 */
	public Collection getEvents()
	{
		return events;
	}

	/**
	 * @param events
	 *            The events to set.
	 */
	public void setEvents(Collection events)
	{
		this.events = events;
	}

	/**
	 * @return Returns the contexts.
	 */
	public CalendarContextEvent getCalendarContextEvent()
	{
		return calendarContextEvent;
	}

	/**
	 * @param contexts
	 *            The contexts to set.
	 */
	public void setCalendarContextEvent(CalendarContextEvent calendarContextEvent)
	{
		this.calendarContextEvent = calendarContextEvent;
	}

	/**
	 * @return Returns the eventTypeCategory.
	 */
	public String getEventTypeCategory()
	{
		return eventTypeCategory;
	}

	/**
	 * @param eventTypeCategory
	 *            The eventTypeCategory to set.
	 */
	public void setEventTypeCategory(String eventTypeCategory)
	{
		this.eventTypeCategory = eventTypeCategory;
	}

	/**
	 * @return Returns the interviewId.
	 */
	public String getInterviewId()
	{
		return interviewId;
	}

	/**
	 * @param interviewId
	 *            The interviewId to set.
	 */
	public void setInterviewId(String interviewId)
	{
		this.interviewId = interviewId;
	}

	/**
	 * @return Returns the schoolCd.
	 */
	public String getSchoolCd()
	{
		return schoolCd;
	}

	/**
	 * @param schoolCd
	 *            The schoolCd to set.
	 */
	public void setSchoolCd(String schoolCd)
	{
		this.schoolCd = schoolCd;
	}

	/**
	 * @return Returns the memberAddressId.
	 */
	public String getMemberAddressId()
	{
		return memberAddressId;
	}

	/**
	 * @param memberAddressId
	 *            The memberAddressId to set.
	 */
	public void setMemberAddressId(String memberAddressId)
	{
		this.memberAddressId = memberAddressId;
	}

	/**
	 * @return Returns the schoolDistrictId.
	 */
	public String getSchoolDistrictId()
	{
		return schoolDistrictId;
	}

	/**
	 * @param schoolDistrictId
	 *            The schoolDistrictId to set.
	 */
	public void setSchoolDistrictId(String schoolDistrictId)
	{
		this.schoolDistrictId = schoolDistrictId;
	}

	public String getFacilityId()
	{
		return facilityId;
	}

	public void setFacilityId(String facilityId)
	{
		this.facilityId = facilityId;
	}

	public String getProgressNotes() {
		return progressNotes;
	}

	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}

	public int getAddlAttendees() {
		return addlAttendees;
	}

	public void setAddlAttendees(int addlAttendees) {
		this.addlAttendees = addlAttendees;
	}

	public Collection getAdditionalAttendeeNames() {
		return additionalAttendeeNames;
	}

	public void setAdditionalAttendeeNames(Collection additionalAttendeeNames) {
		this.additionalAttendeeNames = additionalAttendeeNames;
	}

	/**
	 * @return the memberEmploymentId
	 */
	public String getMemberEmploymentId() {
		return memberEmploymentId;
	}

	/**
	 * @param memberEmploymentId the memberEmploymentId to set
	 */
	public void setMemberEmploymentId(String memberEmploymentId) {
		this.memberEmploymentId = memberEmploymentId;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	/**
	 * @return the document
	 */
	public Object getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Object document) {
		this.document = document;
	}

	/**
	 * @return the docTypeCd
	 */
	public String getDocTypeCd() {
		return docTypeCd;
	}

	/**
	 * @param docTypeCd the docTypeCd to set
	 */
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}

	/**
	 * @return the sexOffender
	 */
	public String getSexOffender() {
		return sexOffender;
	}

	/**
	 * @param sexOffender the sexOffender to set
	 */
	public void setSexOffender(String sexOffender) {
		this.sexOffender = sexOffender;
	}

	/**
	 * @return the restrictOther
	 */
	public String getRestrictOther() {
		return restrictOther;
	}

	/**
	 * @param restrictOther the restrictOther to set
	 */
	public void setRestrictOther(String restrictOther) {
		this.restrictOther = restrictOther;
	}

	/**
	 * @return the weaponDescs
	 */
	public String getWeaponDescs() {
		return weaponDescs;
	}

	/**
	 * @param weaponDescs the weaponDescs to set
	 */
	public void setWeaponDescs(String weaponDescs) {
		this.weaponDescs = weaponDescs;
	}
	
	
}
