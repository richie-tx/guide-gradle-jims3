package ui.juvenilecase.workshopcalendar.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.GetServiceProviderActiveInstructorsEvent;
import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.GetServiceProviderServiceLocationsEvent;
import messaging.administerserviceprovider.GetServiceProviderServicesEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.administerserviceprovider.reply.ServiceTypeResponseEvent;
import messaging.calendar.GetCalendarServiceEventConflictsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class HandleJuvenileServiceEventAction extends LookupDispatchAction
{
    private static final String STATUS_A_STR = "A";

    /**
     * @author awidjaja
     * @roseuid 44805C3B004A
     */
    public HandleJuvenileServiceEventAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward displayWorkshopCalendar(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;
	form.clear();

	form.getCurrentService().getCurrentEvent().setWorkDays(CodeHelper.getWorkDayCodes());

	String serviceProviderId = SecurityUIHelper.getServiceProviderId();
	if (serviceProviderId == null)
	{
	    this.saveErrors(aRequest, "error.serviceProvider.invalidUser");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	form.getCurrentService().setServiceProviderId(serviceProviderId);

	GetServiceProviderEvent spEvent = (GetServiceProviderEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);
	spEvent.setServiceProviderId(serviceProviderId);
	spEvent.setStatusId(STATUS_A_STR);

	JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent) MessageUtil.postRequest(spEvent, JuvenileServiceProviderResponseEvent.class);

	if (resp == null)
	{
	    this.saveErrors(aRequest, "error.serviceProvider.inActiveServiceProvider");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	boolean isInHouse = resp.isInHouse();
	form.getCurrentService().setInHouse(isInHouse);

	//search for all service locations for the service provider
	GetServiceProviderServiceLocationsEvent serviceProviderServiceLocationsEvent = (GetServiceProviderServiceLocationsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERSERVICELOCATIONS);
	/*
			{ int providerID = 0 ;
				try
				{
					providerID = Integer.parseInt( serviceProviderId ) ;
				}
				catch( NumberFormatException nfe )
				{
					this.saveErrors( aRequest, "error.serviceProvider.invalidUser" ) ;
					return aMapping.findForward( UIConstants.FAILURE ) ;
				}
				serviceProviderServiceLocationsEvent.setServiceProviderId( providerID ) ;
			}*/
	serviceProviderServiceLocationsEvent.setServiceProviderId(serviceProviderId);
	serviceProviderServiceLocationsEvent.setInHouse(isInHouse);
	serviceProviderServiceLocationsEvent.setServiceStatusCd(STATUS_A_STR);
	serviceProviderServiceLocationsEvent.setProgramStatusCd(STATUS_A_STR);

	List serviceLocations = MessageUtil.postRequestListFilter(serviceProviderServiceLocationsEvent, LocationResponseEvent.class);
	if (serviceLocations.size() > 0)
	{
	    Collections.sort(serviceLocations, LocationResponseEvent.JuvUnitNameComparator);
	    form.getCurrentService().setLocationsList(serviceLocations);
	}

	aRequest.setAttribute("pageType", "selectLocation");
	aRequest.getSession().setAttribute("userIsInHouse", isInHouse);
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param errorkey
     */
    private void saveErrors(HttpServletRequest aRequest, String errorKey)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, SecurityUIHelper.getLogonId()));
	saveErrors(aRequest, errors);
    }

    /*
     * @param aRequest
     * @param errorKey
     * @param errorMsg
     */
    private void saveErrors(HttpServletRequest aRequest, String errorKey, String errorMsg)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, errorMsg, SecurityUIHelper.getLogonId()));
	saveErrors(aRequest, errors);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward serviceLocationChange(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

	//clear serviceType so that everything is reset
	form.getCurrentService().setServiceTypeId(UIConstants.EMPTY_STRING);

	//Get the Service Location display name to be used in Conflicts & Confirmation page
	{
	    String juvLocUnitId = form.getCurrentService().getLocationId();
	    Collection<LocationResponseEvent> locationList = form.getCurrentService().getLocationsList();
	    for (LocationResponseEvent aLocation : locationList)
	    {
		//ArrayList finalTypeList = new ArrayList() ;
		if (aLocation.getJuvLocationUnitId().equals(juvLocUnitId))
		{
		    form.getCurrentService().setLocation(aLocation.getLocationUnitName());
		    form.getCurrentService().setServiceTypeList(aLocation.getServiceTypeResponseEvents());// change to ServiceTypeCdResponseEvent
		    break;
		}
	    }
	}

	/*ADDED TO LOAD  ServiceTypeCdResponseEvent INSTEAD OF ServiceTypeResponseEvent
	 * { 
	    ArrayList finalTypeList = new ArrayList() ;
	    //finalTypeList.ensureCapacity( locationList.size() ) ;
	    String juvLocUnitId = form.getCurrentService().getLocationId() ;
	  Collection<LocationResponseEvent> locationList = form.getCurrentService().getLocationsList() ;
		for( LocationResponseEvent aLocation : locationList )
		{
		    	
		    	//ServiceTypeCdResponseEvent replyEvent = new ServiceTypeCdResponseEvent();
		    	if( aLocation.getJuvLocationUnitId().equals( juvLocUnitId ) )
			{
				form.getCurrentService().setLocation( aLocation.getLocationUnitName() ) ;
				//form.getCurrentService().setServiceTypeList( aLocation.getServiceTypeResponseEvents() ) ;// change to ServiceTypeCdResponseEvent
				
				Service service = Service.find(aLocation.getServiceId());
				if(service != null){//for loop need to find all for agencycode like 217 and 221
					ServiceTypeCdResponseEvent sTREvent = PDAdminsterServiceProviderHelper.getServiceTypeCdResponseEvent(service);
					if(!map.containsKey(sTREvent.getServiceTypeId())){
				    	map.put(sTREvent.getServiceTypeId(), sTREvent);
					finalTypeList.add(sTREvent);				   			
				}
				
			}
		}
		form.getCurrentService().setServiceTypeList( finalTypeList ) ;
		//break ;
	}*/

	aRequest.setAttribute("pageType", "selectServiceType");

	Calendar cal = Calendar.getInstance();
	StringBuffer monthYear = new StringBuffer();
	int month = (cal.get(Calendar.MONTH) + 1),
		year = cal.get(Calendar.YEAR);
	if (month < 10)
	{
	    monthYear.append("0");
	}
	monthYear.append(month);
	monthYear.append(year);

	aRequest.setAttribute("startMonthYear", monthYear.toString());

	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	return forward;
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward serviceTypeChange(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

	aRequest.setAttribute("calendarNeedsRefresh", new Boolean(true));
	//clear existing instructor names
	form.getCurrentService().setInstructorList(new ArrayList());
	form.getCurrentService().setServiceId(UIConstants.EMPTY_STRING);
	form.getCurrentService().getCurrentEvent().clear();
	form.getCurrentService().getCurrentEvent().setWorkDays(CodeHelper.getWorkDayCodes());

	form.getCurrentService().setSessionLengthIntList(SimpleCodeTableHelper.getCodesSortedByCodeId(PDCodeTableConstants.SESSION_LENGTH_INTERVAL));

	//get list of service provider with services
	GetServiceProviderServicesEvent sps = new GetServiceProviderServicesEvent();
	sps.setInHouse(form.getCurrentService().isInHouse() ? 1 : 0);

	sps.setServiceProviderId(convertToInt(form.getCurrentService().getServiceProviderId(), 0));
	sps.setJuvLocUnitId(convertToInt(form.getCurrentService().getLocationId(), 0));

	sps.setServiceTypeId(form.getCurrentService().getServiceTypeId());
	sps.setServiceStatusCd(STATUS_A_STR);
	sps.setProgramStatusCd(STATUS_A_STR);

	// get serviceProviders and their services
	List<ServiceProviderResponseEvent> serviceProviders = MessageUtil.postRequestListFilter(sps, ServiceProviderResponseEvent.class);
	if (serviceProviders.size() > 0)
	{
	    filterServicesForEndDate(serviceProviders);
	}

	//filter for the service providers associated with the selected service location unit which are also designated as inHouse
	List<ServiceProviderResponseEvent> currentServiceProviders = new ArrayList<ServiceProviderResponseEvent>();

	if (serviceProviders != null && serviceProviders.size() > 0)
	{
	    for (int i = 0; i < serviceProviders.size(); i++)
	    {
		String currentSPId = form.getCurrentService().getServiceProviderId();
		boolean inHouse = form.getCurrentService().isInHouse();
		ServiceProviderResponseEvent provider = serviceProviders.get(i);

		if (provider != null && provider.getJuvServProviderId() != null && currentSPId != null && !"".equals(currentSPId))
		{

		    if (provider.getJuvServProviderId().equals(currentSPId) && inHouse)
		    {

			currentServiceProviders.add(provider);
		    }
		}
	    }
	}

	if (currentServiceProviders.size() > 0)
	{
	    form.getCurrentService().setServiceProviders(currentServiceProviders);
	}
	else
	{
	    form.getCurrentService().setServiceProviders(serviceProviders);
	}

	//Get the Service Type display name to be used in Conflicts & Confirmation page
	String serviceTypeId = form.getCurrentService().getServiceTypeId();
	Collection<ServiceTypeResponseEvent> serviceTypeList = form.getCurrentService().getServiceTypeList();
	for (ServiceTypeResponseEvent stre : serviceTypeList)
	{
	    if (stre.getServiceTypeId().equals(serviceTypeId))
	    {
		form.getCurrentService().setServiceType(stre.getServiceTypeName());
		break;
	    }
	}

	form.setExpandProviders(false);

	aRequest.setAttribute("pageType", "addToWorkshopCalendar");

	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	return forward;
    }

    /* given a collection of serviceProviders, loop through each
     * S.P., checking its collection of services, checking its
     * endDate against today's date - if its endDate is older,
     * dont include it in the list
     * 
     * @param serviceProviders
     * @return
     */
    private boolean filterServicesForEndDate(List<ServiceProviderResponseEvent> serviceProviders)
    {
	boolean rtn = true;
	Date today = new Date(), pgmEndDate = null;

	for (ServiceProviderResponseEvent aProvider : serviceProviders)
	{
	    Collection<ServiceResponseEvent> newSvcList = new ArrayList();
	    Collection<ServiceResponseEvent> serviceList = aProvider.getServiceResponseEvents();

	    for (ServiceResponseEvent aService : serviceList)
	    {
		pgmEndDate = aService.getProgramEndDate();
		if (pgmEndDate != null && today.before(pgmEndDate))
		{
		    newSvcList.add(aService);
		}
	    }

	    if (newSvcList.isEmpty())
	    {
		Collections.sort((List) serviceList);
		newSvcList = null;
	    }
	    else
	    {
		Collections.sort((List) newSvcList);
		aProvider.setServiceResponseEvents(newSvcList);
	    }
	}

	return (rtn);
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addToWorkshopCalendar(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

	aRequest.setAttribute("calendarNeedsRefresh", new Boolean(false));

	/* Combine EventDate, EventTime and SessionLength to 
		 produce StartDate and EndDate from user input
	*/
	Calendar startDate = Calendar.getInstance();
	Calendar endDate = Calendar.getInstance();

	ScheduleNewEventForm.Event currentEvent = form.getCurrentService().getCurrentEvent();
	Date sdate = DateUtil.stringToDate(currentEvent.getEventDateStr(), "MM/dd/yyyy");
	if (sdate == null)
	{
	    aRequest.setAttribute("pageType", "addToWorkshopCalendar");

	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.date", "Event Date"));
	    saveErrors(aRequest, errors);

	    return aMapping.findForward(UIConstants.FAILURE);
	}

	startDate.setTime(sdate);
	String amPm = UIConstants.EMPTY_STRING,
		eventTime = currentEvent.getEventTime();
	int eventHr = 0, eventMin = 0;

	if (eventTime.equalsIgnoreCase("12:00 PM"))
	{
	    eventHr = 12;
	    eventMin = 00;
	}
	else
	    if (eventTime.equalsIgnoreCase("12:30 PM"))
	    {
		eventHr = 12;
		eventMin = 30;
	    }
	    else
	    {
		if (eventTime.length() == 8)
		{
		    eventHr = convertToInt(eventTime.substring(0, 2), 0);
		    amPm = eventTime.substring(6, 8);
		    if (amPm.trim().equalsIgnoreCase("PM"))
		    {
			eventHr += 12;
		    }

		    eventMin = convertToInt(eventTime.substring(3, 5), 0);
		}
	    }

	startDate.set(Calendar.HOUR_OF_DAY, eventHr);
	startDate.set(Calendar.MINUTE, eventMin);

	currentEvent.setStartDate(startDate.getTime());

	//EndDate = StartDate + SessionLength
	Date pgmStartDate = form.getCurrentService().getProgramStartDate(),
		pgmEndDate = form.getCurrentService().getProgramEndDate();
	endDate.setTime(startDate.getTime());

	String sessionLength = currentEvent.getSessionLength();
	int sessionHr = 0, sessionMin = 0;
	/* The sessionLength code table values are 5,10,15,20,25,30,35,40,45,50...80,
		which stands for session length 0.5
		hr, 1 hr, 1.5 hrs....8hrs.
		Parse out the hour by dividing the value by 10. Parse out minutes 
		by doing a mod on 10..minutes value is always 0 or 30.
	*/
	if (notNullNotEmptyString(sessionLength))
	{
	    int sessionLengthInt = convertToInt(sessionLength, 0);

	    sessionHr = sessionLengthInt / 10;
	    if (sessionLengthInt % 10 != 0)
	    {
		sessionMin = 30;
	    }
	}

	endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY) + sessionHr);
	endDate.set(Calendar.MINUTE, endDate.get(Calendar.MINUTE) + sessionMin);

	sdate = startDate.getTime();
	Date edate = endDate.getTime();

	if (pgmStartDate != null && (sdate.before(pgmStartDate) || edate.before(pgmStartDate)))
	{
	    this.saveErrors(aRequest, "error.programStartDate");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	if (pgmEndDate != null && (sdate.after(pgmEndDate) || edate.after(pgmEndDate)))
	{
	    this.saveErrors(aRequest, "error.programEndDate");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	currentEvent.setEndDate(edate);

	int maxAttendance = 0;
	try
	{
	    maxAttendance = Integer.parseInt(currentEvent.getMaxAttendance());
	}
	catch (NumberFormatException nfe)
	{
	    this.saveErrors(aRequest, "error.generic", "Error obtaining Maximum Attendance value.");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	int maxEnrollment = 0;
	try
	{
	    maxEnrollment = Integer.parseInt(form.getCurrentService().getMaxEnrollment());
	}
	catch (NumberFormatException nfe)
	{
	    this.saveErrors(aRequest, "error.generic", "Error obtaining Maximum Enrollment value.");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	if (maxAttendance > maxEnrollment)
	{
	    this.saveErrors(aRequest, "error.maxAttendance");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	//Get the Instructor display name to be used in Conflicts & Confirmation page
	String instructorId = currentEvent.getInstructorId();
	Collection<ServiceProviderContactResponseEvent> instructorList = form.getCurrentService().getInstructorList();
	for (ServiceProviderContactResponseEvent spcre : instructorList)
	{
	    if (spcre.getId().equals(instructorId))
	    {
		currentEvent.setInstructorName(spcre.getContactName().getFormattedName());
		break;
	    }
	}

	//Clear list of conflicting events that may exist
	currentEvent.setConflictingEvents(new ArrayList());

	List allEvents = null;

	if (currentEvent.isRecurringEvent())
	{
	    allEvents = UISupervisionCalendarHelper.getRecurringEvents(form);
	    if (allEvents != null)
	    {
		if (allEvents.size() > 60)
		{
		    this.saveErrors(aRequest, "error.generic", "A total of " + allEvents.size() + " events would have been generated which exceeds the max limit of 60. Please edit your recurring event options");
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		    if (allEvents.size() > 0)
		    {
			Date myStartDate = DateUtil.stringToDate(currentEvent.getEventDateStr(), "MM/dd/yyyy");
			int maxVal = allEvents.size();
			boolean errorFlag = false;
			if (maxVal > 0)
			{
			    GregorianCalendar myOneYearOutCal = new GregorianCalendar();
			    myOneYearOutCal.setTime(myStartDate);
			    myOneYearOutCal.add(Calendar.YEAR, 1);

			    GregorianCalendar testCal = new GregorianCalendar();
			    CalendarServiceEventResponseEvent myLastEvt = (CalendarServiceEventResponseEvent) allEvents.get(maxVal - 1);
			    testCal.setTime(myLastEvt.getStartDatetime());

			    if (testCal.after(myOneYearOutCal))
			    {
				errorFlag = true;
			    }
			    if (!errorFlag)
			    {
				myLastEvt = (CalendarServiceEventResponseEvent) allEvents.get(0);
				testCal.setTime(myLastEvt.getStartDatetime());
				if (testCal.after(myOneYearOutCal))
				{
				    errorFlag = true;
				}
			    }
			}
			if (errorFlag)
			{
			    this.saveErrors(aRequest, "error.generic", "Events spanning more than one year out would have been generated which is not allowed");
			    return aMapping.findForward(UIConstants.FAILURE);
			}
		    }
	    }
	}
	else
	{
	    allEvents = new ArrayList();
	    allEvents.add(UISupervisionCalendarHelper.getNonRecurringEvent(form));
	}

	if (allEvents == null || allEvents.size() < 1)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.events.match"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	form.setAllEvents(allEvents);

	// Remove duplicates from the list
	List conflictingEvents = (List) this.getConflictingEvents(allEvents, form);
	TreeMap<String, CalendarServiceEventResponseEvent> sorted_map = new TreeMap<String, CalendarServiceEventResponseEvent>();

	for (int ctr = 0; ctr < conflictingEvents.size(); ctr++)
	{

	    CalendarServiceEventResponseEvent respE = (CalendarServiceEventResponseEvent) conflictingEvents.get(ctr);

	    if (respE != null && respE.getEventId() != null)
	    {

		sorted_map.put(respE.getEventId(), respE);
	    }
	}

	currentEvent.setConflictingEvents(sorted_map.values());
	aRequest.setAttribute("pageType", "summary");

	ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
	return forward;
    }

    /*
     * @param allEvents
     * @param form
     * @return
     */
    public Collection getConflictingEvents(Collection<CalendarServiceEventResponseEvent> allEvents, ScheduleNewEventForm form)
    {
	//Get list of conflicting events with the event to be scheduled
	Collection conflictingEvents = new ArrayList();
	for (CalendarServiceEventResponseEvent event : allEvents)
	{
	    GetCalendarServiceEventConflictsEvent gce = (GetCalendarServiceEventConflictsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTCONFLICTS);

	    gce.setStartDatetime(event.getStartDatetime());
	    gce.setEndDatetime(event.getEndDatetime());

	    gce.setServiceLocationId(convertToInt(form.getCurrentService().getLocationId(), 0));
	    gce.setInHouse(true);

	    if (notNullNotEmptyString(form.getCurrentService().getServiceProviderId()))
	    {
		gce.setServiceProviderId(convertToInt(form.getCurrentService().getServiceProviderId(), 0));
	    }

	    if (notNullNotEmptyString(form.getCurrentService().getCurrentEvent().getInstructorId()))
	    {
		gce.setInstructorId(convertToInt(form.getCurrentService().getCurrentEvent().getInstructorId(), 0));
	    }

	    List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);
	    if (events.size() > 0)
	    {
		event.setAddConflictingEvent(false);
		event.setConflictingEvent(true);
		conflictingEvents.addAll(events);
	    }
	}

	return conflictingEvents;
    }

    /*
     * @param str
     * @return
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.trim().length() > 0));
    }

    /*
     * given a String and an initial value, convert String to int
     * created this to reduce code clutter, when no error processing
     * is required in the catch.
     */
    private int convertToInt(String tStr, int initialValue)
    {
	int value = initialValue;

	if (notNullNotEmptyString(tStr))
	{
	    String str = tStr.trim();

	    try
	    {
		value = Integer.parseInt(str);
	    }
	    catch (NumberFormatException nfe)
	    { /*empty*/
	    }
	}

	return (value);
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward serviceChange(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

	aRequest.setAttribute("calendarNeedsRefresh", new Boolean(false));

	//Get the Service display name to be used in Conflicts & Confirmation page
	Collection<ServiceProviderResponseEvent> svcProviderList = form.getCurrentService().getServiceProviders();
	for (ServiceProviderResponseEvent spre : svcProviderList)
	{
	    //if(spre.getJuvServProviderId().equals(form.getSelectedServiceProviderId())){			

	    Collection<ServiceResponseEvent> serviceList = spre.getServiceResponseEvents();
	    for (ServiceResponseEvent sre : serviceList)
	    {
		if (form.getCurrentService().getServiceId().equals(sre.getServiceId()))
		{
		    form.getCurrentService().setService(sre.getServiceName());
		    form.getCurrentService().setServiceProvider(spre.getJuvServProviderName());
		    form.getCurrentService().setServiceProviderId(spre.getJuvServProviderId());
		    form.getCurrentService().setProgramStartDate(sre.getProgramStartDate());
		    form.getCurrentService().setProgramEndDate(sre.getProgramEndDate());
		    form.getCurrentService().setMaxEnrollment(sre.getMaxEnrollment());
		    form.getCurrentService().setProgramId(sre.getProgramId());
		}
	    }

	    //}

	}

	//refresh the contacts with Security Manager- task 152592
	ServiceProviderForm sp = new ServiceProviderForm();
	sp.setProviderId(form.getCurrentService().getServiceProviderId());
	JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(form.getCurrentService().getServiceProviderId());
	if (resp != null)
	{
	    UIServiceProviderHelper.fillServiceProviderDetails(sp, resp);
	    Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContactsFromSecurityManager(resp.getAdminUserProfileId()); //86318
	    UIServiceProviderHelper.updateContactInJims2FromSM(contacts, sp);
	}
	// add a new event and command to retrieve only active instructors so other places remains untouched
	/*GetServiceProviderInstructorsEvent event = new GetServiceProviderInstructorsEvent() ;
	event.setInHouse( true ) ;
	event.setServiceProviderId( form.getCurrentService().getServiceProviderId() ) ;*/
	GetServiceProviderActiveInstructorsEvent spactiveinstructorsEvent = (GetServiceProviderActiveInstructorsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERACTIVEINSTRUCTORS);
	spactiveinstructorsEvent.setServiceProviderId(form.getCurrentService().getServiceProviderId());

	List instructors = MessageUtil.postRequestListFilter(spactiveinstructorsEvent, ServiceProviderContactResponseEvent.class);
	form.getCurrentService().setInstructorList(instructors);
	if (instructors.isEmpty())
	{
	    aRequest.setAttribute("showNoInstructor", new String("visibleTR"));
	}

	form.setSelectedServiceProviderId(form.getCurrentService().getServiceProviderId());

	return (aMapping.findForward(UIConstants.SUCCESS));
    }

    public static Map<String, String> getQueryMap(String[] params)
    {
	Map<String, String> map = new HashMap<String, String>();

	if (params != null && params.length > 0)
	{

	    for (String param : params)
	    {
		String name = param.split("=")[0];
		String value = param.split("=")[1];
		map.put(name, value);
	    }
	}

	return map;
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.BACK));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.CANCEL));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
	Map buttonMap = new HashMap();
	buttonMap.put("button.cancel", "cancel");
	buttonMap.put("button.back", "back");
	buttonMap.put("button.addToWorkshopCalendar", "addToWorkshopCalendar");
	buttonMap.put("button.serviceLocationChange", "serviceLocationChange");
	buttonMap.put("button.serviceTypeChange", "serviceTypeChange");
	buttonMap.put("title.workshopCalendar", "displayWorkshopCalendar");
	buttonMap.put("button.serviceChange", "serviceChange");

	return buttonMap;
    }

}
