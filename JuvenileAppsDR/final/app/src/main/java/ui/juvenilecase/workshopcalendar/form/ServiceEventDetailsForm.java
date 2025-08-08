/*
 * Created on Jun 8, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.workshopcalendar.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.SimpleCodeTableHelper;

/**
 * @author dwilliamson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceEventDetailsForm extends ActionForm
{
	private String eventId;
	private Date eventDate;
	private String eventSessionLength;
	private String eventSessionLengthDesc;
	private String eventStatus;
	private String eventStatusCd;
	private String eventType;
	private String eventTypeCode ;
	private String eventComments;
	private String locationId="";
	private String serviceLocationId;
	private String serviceLocationName;
	private String calendarEventId;
	private String serviceProviderName;
	private String serviceName;
	private String eventMaximum;
	private String eventMinimum;
	private String totalScheduled;
	private String instructorName;
	
	private Collection existingAttendanceEvents;
	private Collection newAttendanceEvents;
	private boolean attendanceEventsPresent;
	private ServiceEventAttendanceResponseEvent currentAttendanceEvent;
	public CalendarServiceEventResponseEvent currentEvent;
	

	private Collection cancellationList;
	private Collection signInList;
	private ServiceEventCancellationResponseEvent currentCancellationEvent;
	
	private String action;
	private String secondaryAction;
	private String windowType;
	
	private String adminUserProfileId;
	
	private Collection associatedContexts;
	
	// for printing current date in cancellation list notice
	private String currentDate;
	//<KISHORE>JIMS200060339 : MJCW - Cancelling SP Events Isn't Sending Notices Correctly	
	private String serviceEventId;
	
	private String serviceEventAttendanceId;  //added for #36737
	private boolean tentativeRefPrgmRef=false;  //added for #36737
	
	private Collection contactNames = null;
	private String[] selectedAttendeeNames = null;
	private String juvenileNum;
	private String flowInd;
	private String attendeeNamesStr;
	private boolean outSourceVendorOver7Days;
	private Collection<ServiceEventAttendanceResponseEvent> tentativeReferredPrgmReferrals = new ArrayList<ServiceEventAttendanceResponseEvent>();  //added for #36737
	
	public ServiceEventDetailsForm()
	{
		eventDate = new Date();
		eventSessionLength = "";
		eventStatus = "";
		eventStatusCd="";
		eventType = "";
		eventComments = "";
		serviceLocationId = "";
		serviceLocationName = "";
		serviceProviderName = "";
		serviceName="";
		eventMaximum="";
		eventMinimum="";
		totalScheduled="";
		instructorName="";
		signInList=new ArrayList();

		eventId="";
		calendarEventId="";
		existingAttendanceEvents = new ArrayList();
		newAttendanceEvents = new ArrayList();
		cancellationList= new ArrayList();
		associatedContexts=new ArrayList();
		adminUserProfileId="";
		attendanceEventsPresent = false;
		currentAttendanceEvent = null;
		currentCancellationEvent=null;
		action="";
		secondaryAction="";
		attendeeNamesStr = "";
		tentativeReferredPrgmReferrals = new ArrayList<ServiceEventAttendanceResponseEvent>();
		serviceEventAttendanceId="";
	}
	
	public void clear()
    {
		eventSessionLength = "";
		eventStatus = "";
		eventStatusCd="";
		eventType = "";
		eventComments = "";
		serviceLocationId = "";
		serviceLocationName = "";
		serviceProviderName = "";
		serviceName="";
		eventMaximum="";
		eventMinimum="";
		totalScheduled="";
		instructorName="";
		eventId="";
		calendarEventId="";
		existingAttendanceEvents = new ArrayList();
		newAttendanceEvents = new ArrayList();
		cancellationList= new ArrayList();
		signInList=new ArrayList();
		associatedContexts=new ArrayList();
		adminUserProfileId="";
		attendanceEventsPresent = false;
		currentAttendanceEvent = null;
		currentCancellationEvent=null;
		action="";
		secondaryAction="";
		flowInd = "";
		outSourceVendorOver7Days = false;
	}
	/**
	 * @return
	 */
	public Date getEventDate()
	{
		return eventDate;
	}

	/**
	 * @return
	 */
	public String getEventSessionLength()
	{
		return eventSessionLength;
	}

	/**
	 * @return
	 */
	public String getEventStatus()
	{
		return eventStatus;
	}

	/**
	 * @return
	 */
	public String getEventType()
	{
		return eventType;
	}

	/**
	 * @return
	 */
	public String getServiceLocationId()
	{
		return serviceLocationId;
	}

	/**
	 * @return
	 */
	public String getServiceLocationName()
	{
		return serviceLocationName;
	}

	/**
	 * @return
	 */
	public String getServiceProviderName()
	{
		return serviceProviderName;
	}

	/**
	 * @param date
	 */
	public void setEventDate(Date date)
	{
		eventDate = date;
	}

	/**
	 * @param string
	 */
	public void setEventSessionLength(String string)
	{
		eventSessionLength = string;
		eventSessionLengthDesc="";
		eventSessionLengthDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SESSION_LENGTH_INTERVAL,eventSessionLength);

	}

	/**
	 * @param string
	 */
	public void setEventStatus(String string)
	{
		eventStatus = string;
	}

	/**
	 * @param string
	 */
	public void setEventType(String string)
	{
		eventType = string;
	}

	/**
	 * @param string
	 */
	public void setServiceLocationId(String string)
	{
		serviceLocationId = string;
	}

	/**
	 * @param string
	 */
	public void setServiceLocationName(String string)
	{
		serviceLocationName = string;
	}

	/**
	 * @param string
	 */
	public void setServiceProviderName(String string)
	{
		serviceProviderName = string;
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
	public void setEventComments(String string)
	{
		eventComments = string;
	}

	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	/**
	 * @return Returns the currentAttendanceEvent.
	 */
	public ServiceEventAttendanceResponseEvent getCurrentAttendanceEvent() {
		return currentAttendanceEvent;
	}
	/**
	 * @param currentAttendanceEvent The currentAttendanceEvent to set.
	 */
	public void setCurrentAttendanceEvent(ServiceEventAttendanceResponseEvent currentAttendanceEvent) {
		this.currentAttendanceEvent = currentAttendanceEvent;
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the existingAttendanceEvents.
	 */
	public Collection getExistingAttendanceEvents() {
		return existingAttendanceEvents;
	}
	/**
	 * @param existingAttendanceEvents The existingAttendanceEvents to set.
	 */
	public void setExistingAttendanceEvents(Collection existingAttendanceEvents) {
		this.existingAttendanceEvents = existingAttendanceEvents;
	}
	/**
	 * @return Returns the newAttendanceEvents.
	 */
	public Collection getNewAttendanceEvents() {
		return newAttendanceEvents;
	}
	/**
	 * @param newAttendanceEvents The newAttendanceEvents to set.
	 */
	public void setNewAttendanceEvents(Collection newAttendanceEvents) {
		this.newAttendanceEvents = newAttendanceEvents;
	}
	/**
	 * @return Returns the attendanceEventsPresent.
	 */
	public boolean isAttendanceEventsPresent() {
		return attendanceEventsPresent;
	}
	/**
	 * @param attendanceEventsPresent The attendanceEventsPresent to set.
	 */
	public void setAttendanceEventsPresent(boolean attendanceEventsPresent) {
		this.attendanceEventsPresent = attendanceEventsPresent;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the cancellationList.
	 */
	public Collection getCancellationList() {
		return cancellationList;
	}
	/**
	 * @param cancellationList The cancellationList to set.
	 */
	public void setCancellationList(Collection cancellationList) {
		this.cancellationList = cancellationList;
	}
	/**
	 * @return Returns the eventStatusCd.
	 */
	public String getEventStatusCd() {
		return eventStatusCd;
	}
	/**
	 * @param eventStatusCd The eventStatusCd to set.
	 */
	public void setEventStatusCd(String eventStatusCd) {
		this.eventStatusCd = eventStatusCd;
	}
	/**
	 * @return Returns the currentCancellationEvent.
	 */
	public ServiceEventCancellationResponseEvent getCurrentCancellationEvent() {
		return currentCancellationEvent;
	}
	/**
	 * @param currentCancellationEvent The currentCancellationEvent to set.
	 */
	public void setCurrentCancellationEvent(ServiceEventCancellationResponseEvent currentCancellationEvent) {
		this.currentCancellationEvent = currentCancellationEvent;
	}

	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return Returns the adminUserProfileId.
	 */
	public String getAdminUserProfileId() {
		return adminUserProfileId;
	}
	/**
	 * @param adminUserProfileId The adminUserProfileId to set.
	 */
	public void setAdminUserProfileId(String adminUserProfileId) {
		this.adminUserProfileId = adminUserProfileId;
	}
	/**
	 * @return Returns the associatedContexts.
	 */
	public Collection getAssociatedContexts() {
		return associatedContexts;
	}
	/**
	 * @param associatedContexts The associatedContexts to set.
	 */
	public void setAssociatedContexts(Collection associatedContexts) {
		this.associatedContexts = associatedContexts;
	}
	/**
	 * @return Returns the instructorName.
	 */
	public String getInstructorName() {
		return instructorName;
	}
	/**
	 * @param instructorName The instructorName to set.
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * @return Returns the totalScheduled.
	 */
	public String getTotalScheduled() {
		return totalScheduled;
	}
	/**
	 * @param totalScheduled The totalScheduled to set.
	 */
	public void setTotalScheduled(String totalScheduled) {
		this.totalScheduled = totalScheduled;
	}
	/**
	 * @return Returns the eventMaximum.
	 */
	public String getEventMaximum() {
		return eventMaximum;
	}
	/**
	 * @param eventMaximum The eventMaximum to set.
	 */
	public void setEventMaximum(String eventMaximum) {
		this.eventMaximum = eventMaximum;
	}
	/**
	 * @return Returns the eventMinimum.
	 */
	public String getEventMinimum() {
		return eventMinimum;
	}
	/**
	 * @param eventMinimum The eventMinimum to set.
	 */
	public void setEventMinimum(String eventMinimum) {
		this.eventMinimum = eventMinimum;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return Returns the currentDate.
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate The currentDate to set.
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	/**
	 * @return Returns the signInList.
	 */
	public Collection getSignInList() {
		return signInList;
	}
	/**
	 * @param signInList The signInList to set.
	 */
	public void setSignInList(Collection signInList) {
		this.signInList = signInList;
	}
	/**
	 * @return Returns the eventSessionLengthDesc.
	 */
	public String getEventSessionLengthDesc() {
		return eventSessionLengthDesc;
	}
	/**
	 * @param eventSessionLengthDesc The eventSessionLengthDesc to set.
	 */
	public void setEventSessionLengthDesc(String eventSessionLengthDesc) {
		this.eventSessionLengthDesc = eventSessionLengthDesc;
	}
	/**
	 * @return Returns the windowType.
	 */
	public String getWindowType() {
		return windowType;
	}
	/**
	 * @param windowType The windowType to set.
	 */
	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}

	public String getEventTypeCode( )
	{
		return eventTypeCode ;
	}

	public void setEventTypeCode( String eventTypeCode )
	{
		this.eventTypeCode = eventTypeCode ;
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	public Collection getContactNames() {
		return contactNames;
	}

	public void setContactNames(Collection contactNames) {
		this.contactNames = contactNames;
	}

	public String[] getSelectedAttendeeNames() {
		return selectedAttendeeNames;
	}

	public void setSelectedAttendeeNames(String[] selectedAttendeeNames) {
		this.selectedAttendeeNames = selectedAttendeeNames;
	}

	public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	public String getFlowInd() {
		return flowInd;
	}

	public void setFlowInd(String flowInd) {
		this.flowInd = flowInd;
	}

	public String getAttendeeNamesStr() {
		return attendeeNamesStr;
	}

	public void setAttendeeNamesStr(String attendeeNamesStr) {
		this.attendeeNamesStr = attendeeNamesStr;
	}

	/**
	 * @return the outSourceVendorOver7Days
	 */
	public boolean isOutSourceVendorOver7Days() {
		return outSourceVendorOver7Days;
	}

	/**
	 * @param outSourceVendorOver7Days the outSourceVendorOver7Days to set
	 */
	public void setOutSourceVendorOver7Days(boolean outSourceVendorOver7Days) {
		this.outSourceVendorOver7Days = outSourceVendorOver7Days;
	}

	/**
	 * @return the tentativeReferredJuveniles
	 */
	public Collection<ServiceEventAttendanceResponseEvent> getTentativeReferredPrgmReferrals() {
		return tentativeReferredPrgmReferrals;
	}

	/**
	 * @param tentativeReferredJuveniles the tentativeReferredJuveniles to set
	 */
	public void setTentativeReferredPrgmReferrals(Collection<ServiceEventAttendanceResponseEvent> tentativeReferredPrgmReferrals) {
		this.tentativeReferredPrgmReferrals = tentativeReferredPrgmReferrals;
	}

	/**
	 * @return the serviceEventAttendanceId
	 */
	public String getServiceEventAttendanceId() {
		return serviceEventAttendanceId;
	}

	/**
	 * @param serviceEventAttendanceId the serviceEventAttendanceId to set
	 */
	public void setServiceEventAttendanceId(String serviceEventAttendanceId) {
		this.serviceEventAttendanceId = serviceEventAttendanceId;
	}

	/**
	 * @return the tentativeRefPrgmRef
	 */
	public boolean isTentativeRefPrgmRef() {
		return tentativeRefPrgmRef;
	}

	/**
	 * @param tentativeRefPrgmRef the tentativeRefPrgmRef to set
	 */
	public void setTentativeRefPrgmRef(boolean tentativeRefPrgmRef) {
		this.tentativeRefPrgmRef = tentativeRefPrgmRef;
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
