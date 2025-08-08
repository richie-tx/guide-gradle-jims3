//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\HandleCalendarEventDetailsAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.calendar.GetProgramReferralsByServiceEventIdEvent;
import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.CalendarEventResponse;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.calendar.reply.ProgramReferralsByServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.to.Address;
import messaging.contact.to.PhoneNumberBean;
import messaging.contact.to.SocialSecurityBean;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.family.GetFamilyMemberContactEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.interviewinfo.GetInterviewDetailEvent;
import messaging.interviewinfo.GetInterviewInventoryEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.programreferral.GetProgramReferralsByServiceEventEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import pd.supervision.programreferral.JuvenileProgramReferral;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.schedulecalendarevent.GuardianPrintBean;
import ui.juvenilecase.schedulecalendarevent.IdInfoPrintBean;
import ui.juvenilecase.schedulecalendarevent.InvolvedWeaponOffenseBean;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class HandleCalendarEventDetailsAction extends JIMSBaseAction
{
    private static final String JUV_ID_STR = "juvenileId";
    private static final String ADDL_ATTENDEES = "addlAttendees";
    private static final String ATTENDANCECD = "attendanceCd";
    private static final String CASEFILE_FORM_STR = "juvenileCasefileForm";
    private static final String SCHEDULE_EVENT_FORM_STR = "scheduleNewEventForm";
    private static final String EVENT_ID_STR = "eventId";
    private static final String EVENT_TYPE_STR = "eventType";
    private static final String CAL_EVENT_DETAILS_FORM_STR = "calendarEventDetailsForm";

    /**
     * @roseuid 4576E78601AC
     */
    public HandleCalendarEventDetailsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward displayCalendarEventDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String eventId = aRequest.getParameter(EVENT_ID_STR);
	if (eventId == null)
	{
	    eventId = UIConstants.EMPTY_STRING;
	}

	CalendarEventListForm form = (CalendarEventListForm) aForm;

	{
	    String currentJuvenileId = aRequest.getParameter("currentJuvenileId");
	    if (currentJuvenileId == null)
	    {
		currentJuvenileId = UIConstants.EMPTY_STRING;
	    }
	    form.setCurrentJuvenileId(currentJuvenileId);

	    ScheduleNewEventForm oldForm = (ScheduleNewEventForm) aRequest.getSession().getAttribute(SCHEDULE_EVENT_FORM_STR);

	    form.setJuvenileNum((oldForm != null) ? oldForm.getJuvenileNum() : UIConstants.EMPTY_STRING);
	}

	Collection<CalendarServiceEventResponseEvent> dayEventList = form.getDayEvents();
	for (CalendarServiceEventResponseEvent currentEvent : dayEventList)
	{
	    String currEventId = currentEvent.getEventId().trim();
	    if (currEventId.equals(eventId))
	    {
		form.setCurrentEvent(currentEvent);
		//<KISHORE>JIMS200057235	MJCW Sch Cal Even and View Cal - Attend Status is incorrect
		// This call retrieves all the events scheduled for a specific service program
		// We need to display the details for a particular juvenile from that list
		GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
		getServiceEvent.setServiceEventId(currEventId);
		CompositeResponse resp = MessageUtil.postRequest(getServiceEvent);
		List eventsList = MessageUtil.compositeToList(resp, ServiceEventAttendanceResponseEvent.class);

		Iterator<ServiceEventAttendanceResponseEvent> iter = eventsList.iterator();
		while (iter.hasNext())
		{
		    ServiceEventAttendanceResponseEvent attendanceEvent = iter.next();
		    if (attendanceEvent != null && notNullAndStringsMatch(currentEvent.getJuvenileNum(), attendanceEvent.getJuvenileId()))
		    {

			String updatedProgressNote = getUpdatedProgressNote(attendanceEvent, currentEvent);

			form.setProgressNotes(updatedProgressNote);
			form.setAttendanceStatusCd(attendanceEvent.getAttendanceStatusCd());
			form.setAttendanceStatus(attendanceEvent.getAttendanceStatusDescription());
			form.setIsAttendedExcusedAbsent(attendanceEvent.getAttendanceStatusCd());
			form.setSummaryNotes(currentEvent.getInterviewSummaryNotes());
			form.setAddlAttendees(attendanceEvent.getAddlAttendees());
			form.setSelectedNamesList(null);
			form.setSelectedAttendeeNamesAsString(null);
			if (!attendanceEvent.getAddlAttendeeNames().isEmpty())
			{
			    form.setSelectedNamesList(attendanceEvent.getAddlAttendeeNames());
			    StringBuffer names = new StringBuffer(UIConstants.EMPTY_STRING);
			    for (Object obj : form.getSelectedNamesList())
			    {
				AttendeeNameResponseEvent anre = (AttendeeNameResponseEvent) obj;
				if (anre != null)
				{
				    names.append(anre.getFormattedName()).append(";");
				}
			    }
			    form.setSelectedAttendeeNamesAsString(names.toString());
			}
			getInterviewSummaryNotes(form);
		    }
		} // while
		break; // found it, get out of for loop
	    }
	} // for
	form.setGuardianInHouse(validateInHouseGuardian(form.getJuvenileNum()));

	if (form.getCurrentEvent() != null && eventId != null && eventId != UIConstants.EMPTY_STRING)
	{
	    String selectedJuvNum = form.getCurrentJuvenileId();
	    if (selectedJuvNum == null || (selectedJuvNum.length() == 0))
	    {
		selectedJuvNum = "";
	    }

	    // get a list of referrals
	    GetProgramReferralsByServiceEventEvent getPREvent = (GetProgramReferralsByServiceEventEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENT);

	    getPREvent.setServiceEventId(eventId);
	    getPREvent.setJuvenileNum("");
	    CompositeResponse response = MessageUtil.postRequest(getPREvent);
	    if (response != null)
	    { // we got a list of referrals
		List referralList = (List) MessageUtil.compositeToCollection(response, ProgramReferralResponseEvent.class);

		// now, get a list of attendance records
		GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
		getServiceEvent.setServiceEventId(eventId);
		CompositeResponse responseEvent = MessageUtil.postRequest(getServiceEvent);
		if (responseEvent != null)
		{// got the attendance list
		    List attendanceList = (List) MessageUtil.compositeToCollection(responseEvent, ServiceEventAttendanceResponseEvent.class);

		    for (Iterator<ProgramReferralResponseEvent> iter = referralList.iterator(); iter.hasNext(); /*empty*/ )
		    { /* for each record in the attendance list, look through the
		       * referral list for the matching record (based on Juv #
		       */
			ProgramReferralResponseEvent refEvent = iter.next();
			for (Iterator<ServiceEventAttendanceResponseEvent> aIter = attendanceList.iterator(); aIter.hasNext(); /*empty*/ )
			{
			    ServiceEventAttendanceResponseEvent attend = aIter.next();
			    if (attend.getJuvenileId().equals(refEvent.getJuvenileId()))
			    {
				refEvent.setExtnNum(attend.getAttendanceStatusDescription());
				break; // got the match, get out of this loop
			    }
			}

			/* if the user selected this Juv # from the Event List set 
			 * the value so the JSP can display which Juv was selected
			 */
			refEvent.setOutComeCd(selectedJuvNum.equals(refEvent.getJuvenileId()) ? "VALUE_SELECTED" : "NO_VALUE_SELECTED");
		    } // outer for loop - referral list
		}
		Collections.sort(referralList, ProgramReferralResponseEvent.refNameComparator);

		form.setProgramReferralList(referralList);
	    }
	}

	return (aMapping.findForward(UIConstants.DETAIL_SUCCESS));
    }

    private String getUpdatedProgressNote(ServiceEventAttendanceResponseEvent attendanceEvent, CalendarServiceEventResponseEvent currentEvent)
    {

	int indexOfLeftBracket = 0;
	String eventUpdateDate = "";
	String progressNote = null;
	String progressNoteUpdated = null;
	String updatedUser = null;
	IName userName = null;

	if (attendanceEvent != null && attendanceEvent.getUpdateDate() != null && !attendanceEvent.getUpdateDate().equals(""))
	{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	    eventUpdateDate = dateFormat.format(attendanceEvent.getUpdateDate());
	}

	if (attendanceEvent != null && attendanceEvent.getProgressNotes() != null && !attendanceEvent.getProgressNotes().equals("") && attendanceEvent.getUpdateUser() != null)
	{
	    indexOfLeftBracket = attendanceEvent.getProgressNotes().indexOf('[');

	    if (indexOfLeftBracket != -1 && indexOfLeftBracket > 0)
	    {
		progressNote = attendanceEvent.getProgressNotes().substring(0, indexOfLeftBracket);
	    }

	    //===== get the JIMS2 login information for generic login		 
	    Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContactsFromSecurityManager(attendanceEvent.getUpdateUser());
	    Iterator iter = contacts.iterator();

	    while (iter.hasNext())
	    {
		ServiceProviderContactResponseEvent ur = (ServiceProviderContactResponseEvent) iter.next();
		if (ur.getEmail() != null && !"".equals(ur.getEmail()))
		{
		    if (ur.getEmail().equalsIgnoreCase(attendanceEvent.getUpdateJims2User()))
		    {
			updatedUser = ur.getLastName() + ", " + ur.getFirstName();
			break;
		    }
		}
	    }

	    //if non generic login - get the user name instead
	    if (updatedUser == null || "".equals(updatedUser))
	    {
		userName = (IName) SecurityUIHelper.getUserName(attendanceEvent.getUpdateUser());

		updatedUser = userName != null && userName.getLastName() != null && userName.getFirstName() != null ? userName.getLastName() + ", " + userName.getFirstName() : "";
	    }

	}

	if (progressNote != null && !progressNote.equals(""))
	{
	    progressNoteUpdated = progressNote + "[" + eventUpdateDate + " - " + updatedUser + "]";
	}

	return progressNoteUpdated;
    }

    /*
     * @param form
     * @return
     */
    private boolean getInterviewSummaryNotes(CalendarEventListForm form)
    {
	boolean rtn = false;
	String interviewId = form.getCurrentEvent().getInterviewId();

	if (notNullNotEmptyString(interviewId))
	{
	    GetInterviewDetailEvent event = new GetInterviewDetailEvent();
	    event.setInterviewId(interviewId);

	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(event);

	    Collection<InterviewDetailResponseEvent> interviews = MessageUtil.compositeToCollection((CompositeResponse) dispatch.getReply(), InterviewDetailResponseEvent.class);
	    for (InterviewDetailResponseEvent detail : interviews)
	    {
		form.setSummaryNotes(detail.getSummaryNotes());
		rtn = true;
		break; // found it, get out
	    }
	}

	return (rtn);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward displayDocketEventDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String eventId = aRequest.getParameter(EVENT_ID_STR);
	if (eventId == null)
	{
	    eventId = UIConstants.EMPTY_STRING;
	}

	String eventType = aRequest.getParameter(EVENT_TYPE_STR);
	if (eventType == null)
	{
	    eventType = UIConstants.EMPTY_STRING;
	}

	CalendarEventListForm form = (CalendarEventListForm) aForm;
	Collection<DocketEventResponseEvent> docketEventList = form.getDocketEvents();
	for (DocketEventResponseEvent currentEvent : docketEventList)
	{
	    if (currentEvent.getDocketEventId().equals(eventId) && currentEvent.getDocketType().equals(eventType))
	    {
		form.setCurrentDocketEvent(currentEvent);
		break;
	    }
	}

	return (aMapping.findForward(UIConstants.DOCKET_SUCCESS));
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward displayConflictingEventDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm oldForm = (ScheduleNewEventForm) aRequest.getSession().getAttribute(SCHEDULE_EVENT_FORM_STR);

	if (oldForm != null)
	{
	    CalendarEventListForm form = (CalendarEventListForm) aForm;

	    form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED);
	    Collection<CalendarServiceEventResponseEvent> eventList = null;

	    String eventType = aRequest.getParameter(EVENT_TYPE_STR);
	    if (eventType == null)
	    {
		eventType = UIConstants.EMPTY_STRING;
	    }

	    if (eventType.equals("JPO"))
	    {
		eventList = oldForm.getCurrentService().getCurrentEvent().getJpoConflictingEvents();
	    }
	    else
		if (eventType.equals("JUV"))
		{
		    eventList = oldForm.getCurrentService().getCurrentEvent().getJuvenileConflictingEvents();
		}

	    if (eventList != null)
	    {
		String eventId = aRequest.getParameter(EVENT_ID_STR);
		if (eventId == null)
		{
		    eventId = UIConstants.EMPTY_STRING;
		}

		for (CalendarServiceEventResponseEvent currentEvent : eventList)
		{
		    if (currentEvent.getEventId().equals(eventId))
		    {
			form.setCurrentEvent(currentEvent);
			break;
		    }
		}
	    }
	}

	return (aMapping.findForward(UIConstants.DETAIL_SUCCESS));
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward displayPreScheduledEventDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm oldForm = (ScheduleNewEventForm) aRequest.getSession().getAttribute(SCHEDULE_EVENT_FORM_STR);
	CalendarEventListForm form = (CalendarEventListForm) aForm;
	form.setCalendarType("PRE");

	Collection<CalendarServiceEventResponseEvent> dayEventList = oldForm.getAllEvents();
	if (dayEventList != null)
	{
	    String eventId = aRequest.getParameter(EVENT_ID_STR);
	    if (eventId == null)
	    {
		eventId = UIConstants.EMPTY_STRING;
	    }

	    for (CalendarServiceEventResponseEvent currentEvent : dayEventList)
	    {
		if (currentEvent.getEventId().equals(eventId))
		{
		    form.setCurrentEvent(currentEvent);
		    break;
		}
	    }
	}

	return (aMapping.findForward(UIConstants.DETAIL_SUCCESS));
    }

    /*
     * (non-Javadoc)
     * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.BACK);
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward returnToCalendar(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward generateAppointmentLetter(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;

	JuvenileInterview juvInterview = new JuvenileInterview(form.getCurrentEvent().getInterviewPersons(), new ArrayList());
	form.setCurrentInterview(juvInterview);

	GetInterviewInventoryEvent event = (GetInterviewInventoryEvent) EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETINTERVIEWINVENTORY);

	if (notNullNotEmptyString(juvInterview.getCasefileId()))
	{
	    event.setCasefileId(juvInterview.getCasefileId());
	}
	else
	{
	    HttpSession session = aRequest.getSession();
	    JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) session.getAttribute(CASEFILE_FORM_STR);

	    event.setCasefileId((casefileForm != null) ? casefileForm.getSupervisionNum() : UIConstants.EMPTY_STRING);
	}
	event.setJuvenileId(form.getJuvenileNum());

	CompositeResponse response = MessageUtil.postRequest(event);
	Collection<InterviewDetailResponseEvent> interviewDocuments = MessageUtil.compositeToCollection(response, InterviewDetailResponseEvent.class);

	Set recordIds = new HashSet();
	Collection others = new ArrayList();
	Collection recordsInventoryDisplay = new ArrayList();

	if (interviewDocuments != null)
	{
	    for (InterviewDetailResponseEvent detail : interviewDocuments)
	    {
		if (notNullNotEmptyCollection(detail.getInventoryRecordsIds()))
		{
		    recordIds.addAll(detail.getInventoryRecordsIds());
		}

		if (notNullNotEmptyString(detail.getOtherInventoryRecords()))
		{
		    others.add(detail.getOtherInventoryRecords());
		}
	    }

	    for (Iterator iter = recordIds.iterator(); iter.hasNext(); /*empty*/ )
	    {
		String recordInventoryDescription = CodeHelper.getCodeDescription(PDCodeTableConstants.INTERVIEW_RECORD, (String) iter.next());
		recordsInventoryDisplay.add(recordInventoryDescription);
	    }
	}

	Collections.sort((List) recordsInventoryDisplay);
	form.getCurrentInterview().setCurrentRecordsInventory(recordsInventoryDisplay);
	form.getCurrentInterview().setCurrentOtherInventoryRecords(others);
	form.getCurrentInterview().setCurrentRecordsInventoryIds(recordIds);

	List<CodeResponseEvent> codesList = CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_RECORD, true);
	ArrayList recordsInventoryList = new ArrayList();

	for (CodeResponseEvent code : codesList)
	{
	    String tCode = code.getCode().trim();
	    if (tCode.equals(UIConstants.OTHER_COURT_ORDER) || tCode.equals(UIConstants.OTHER_DOCUMENTS) || !recordIds.contains(code.getCode()))
	    {
		recordsInventoryList.add(code);
	    }
	}

	form.setRecordsInventoryList(recordsInventoryList);
	form.setAction("recordUpdate");

	return (aMapping.findForward(UIConstants.VIEW));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward documentAttendance(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;
	form.setTentativeRefPrgmRef(false); //reset it to original
	// # U.S.30339 changes
	String preScdEvt = aRequest.getParameter("preScheEvt");
	String from = aRequest.getParameter("from");
	if (preScdEvt != null && preScdEvt.equals("true"))
	{
	    getProposedEventDetails(aRequest, form);
	}
	// # U.S.30339 changes

	if (form.getCurrentEvent() == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Current Event is invalid; please re-select the Event from the Calendar"));
	    saveErrors(aRequest, errors);

	    return aMapping.findForward(UIConstants.CANCEL);
	}

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	GetJuvenileAttendanceEvent getAttendanceEvent = (GetJuvenileAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETJUVENILEATTENDANCE);
	getAttendanceEvent.setJuvenileId(form.getJuvenileNum());

	getAttendanceEvent.setServiceEventId(form.getCurrentEvent().getEventId());

	dispatch.postEvent(getAttendanceEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);

	Collection<ServiceEventAttendanceResponseEvent> attendanceEvents = MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);

	if (!attendanceEvents.isEmpty())
	{
	    for (ServiceEventAttendanceResponseEvent attendanceResponseEvent : attendanceEvents)
	    {
		String statusCode = attendanceResponseEvent.getAttendanceStatusCd().trim();
		if (statusCode.equals(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) || statusCode.equals(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED))
		{
		    Date dateToday = new Date();
		    ArrayList attendanceStatusList = new ArrayList();
		    attendanceStatusList.add(CodeHelper.getCode(PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED));

		    // (value less than 0) if getStartDatetime is before today's date
		    if (form.getCurrentEvent().getStartDatetime().compareTo(dateToday) < 1)
		    {
			attendanceStatusList.add(CodeHelper.getCode(PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED));

			attendanceStatusList.add(CodeHelper.getCode(PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT));
		    }
		    form.setAttendanceStatusList(attendanceStatusList);
		    form.setExistingProgressNotes(attendanceResponseEvent.getProgressNotes());
		    form.setProgressNotes(UIConstants.EMPTY_STRING);
		    form.setAttendanceStatusCd(UIConstants.EMPTY_STRING);
		    form.setAction("attendanceUpdate");
		    Collection names = UISupervisionCalendarHelper.getJuvenileContacts(form.getCurrentEvent().getJuvenileNum());
		    form.setContactNames(names);
		    form.setAddlAttendees(UIConstants.EMPTY_STRING);
		}
		else
		{
		    form.setAttendanceStatusCd(attendanceResponseEvent.getAttendanceStatusCd());
		    form.setAttendanceStatus(attendanceResponseEvent.getAttendanceStatusDescription());
		    form.setProgressNotes(attendanceResponseEvent.getProgressNotes());
		    form.setAddlAttendees(attendanceResponseEvent.getAddlAttendees());
		    form.setSelectedNamesList(attendanceResponseEvent.getAddlAttendeeNames());
		    form.setAction("attendancePresent");
		}
	    } //for
	}
	//added for #36737
	GetProgramReferralsByServiceEventIdEvent getProgramReferrals = (GetProgramReferralsByServiceEventIdEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENTID);
	getProgramReferrals.setServiceEventId(form.getCurrentEvent().getEventId());
	compositeResponse = (CompositeResponse) MessageUtil.postRequest(getProgramReferrals);
	List<ProgramReferralsByServiceEventResponseEvent> programReferrals = (List<ProgramReferralsByServiceEventResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, ProgramReferralsByServiceEventResponseEvent.class);

	if (programReferrals != null)
	{
	    Iterator<ProgramReferralsByServiceEventResponseEvent> referrals = programReferrals.iterator();
	    while (referrals.hasNext())
	    {
		ProgramReferralsByServiceEventResponseEvent programReferralRespEvt = (ProgramReferralsByServiceEventResponseEvent) referrals.next();
		if (programReferralRespEvt != null)
		{
		    //ProgramReferralResponseEvent prgmRefResp = null;
		    //Get the program referral status using prog ref id.
		    /*GetProgramReferralDetailsEvent prgmRefDetails = (GetProgramReferralDetailsEvent)EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
		    prgmRefDetails.setProgramReferralId(programReferralRespEvt.getProgramReferralId());
		    compositeResponse = (CompositeResponse) MessageUtil.postRequest(prgmRefDetails);
		    prgmRefResp = (ProgramReferralResponseEvent)MessageUtil.filterComposite(compositeResponse,ProgramReferralResponseEvent.class);*/
		    JuvenileProgramReferral progRef = JuvenileProgramReferral.find(programReferralRespEvt.getProgramReferralId());
		    if (progRef != null && progRef.getJuvenileId().equals(form.getCurrentEvent().getJuvenileNum()))
		    {
			if (progRef != null && progRef.getReferralStatusCd() != null && progRef.getReferralStatusCd().equals(ProgramReferralConstants.TENTATIVE) && progRef.getReferralSubStatusCd() != null && progRef.getReferralSubStatusCd().equals(ProgramReferralConstants.REFERRED))
			{
			    form.setTentativeRefPrgmRef(true);
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The program referral associated to this calendar event is TENTATIVE REFERRED.  The program referral status must be ACCEPTED to update attendance"));
			    saveErrors(aRequest, errors);

			    return aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE);
			}
		    }
		}
	    }
	}
	form.setTentativeRefPrgmRef(false);
	//added for #36737		
	if (from != null && from.equalsIgnoreCase("workshopCalendar"))
	{
	    form.setSecondaryAction("workshopCalendar");
	}
	return (aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE));
    }

    /**
     * # U.S.30339 changes getProposedEventDetails - replication of the
     * getEventDetails method.
     * 
     * @param aRequest
     * @param aForm
     */
    private void getProposedEventDetails(HttpServletRequest aRequest, CalendarEventListForm form)
    {

	String eventId = aRequest.getParameter(EVENT_ID_STR);
	if (eventId == null)
	{
	    eventId = UIConstants.EMPTY_STRING;
	}

	String currentJuvenileId = aRequest.getParameter("currentJuvenileId");
	if (currentJuvenileId == null)
	{
	    currentJuvenileId = UIConstants.EMPTY_STRING;
	}

	form.setCurrentJuvenileId(currentJuvenileId);

	ScheduleNewEventForm oldForm = (ScheduleNewEventForm) aRequest.getSession().getAttribute(SCHEDULE_EVENT_FORM_STR);

	form.setJuvenileNum((oldForm != null) ? oldForm.getJuvenileNum() : UIConstants.EMPTY_STRING);

	Collection<CalendarServiceEventResponseEvent> dayEventList = getProposedEventDayEvent(aRequest, oldForm, form);
	for (CalendarServiceEventResponseEvent currentEvent : dayEventList)
	{
	    String currEventId = currentEvent.getEventId().trim();
	    if (currEventId.equals(eventId))
	    {
		form.setCurrentEvent(currentEvent);
		//<KISHORE>JIMS200057235	MJCW Sch Cal Even and View Cal - Attend Status is incorrect
		// This call retrieves all the events scheduled for a specific service program
		// We need to display the details for a particular juvenile from that list
		GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
		getServiceEvent.setServiceEventId(currEventId);
		CompositeResponse resp = MessageUtil.postRequest(getServiceEvent);
		List eventsList = MessageUtil.compositeToList(resp, ServiceEventAttendanceResponseEvent.class);

		Iterator<ServiceEventAttendanceResponseEvent> iter = eventsList.iterator();
		while (iter.hasNext())
		{
		    ServiceEventAttendanceResponseEvent attendanceEvent = iter.next();
		    if (attendanceEvent != null && notNullAndStringsMatch(currentEvent.getJuvenileNum(), attendanceEvent.getJuvenileId()))
		    {
			form.setProgressNotes(attendanceEvent.getProgressNotes());
			form.setAttendanceStatusCd(attendanceEvent.getAttendanceStatusCd());
			form.setAttendanceStatus(attendanceEvent.getAttendanceStatusDescription());
			form.setSummaryNotes(currentEvent.getInterviewSummaryNotes());
			form.setAddlAttendees(attendanceEvent.getAddlAttendees());
			form.setSelectedNamesList(null);
			form.setSelectedAttendeeNamesAsString(null);
			if (!attendanceEvent.getAddlAttendeeNames().isEmpty())
			{
			    form.setSelectedNamesList(attendanceEvent.getAddlAttendeeNames());
			    StringBuffer names = new StringBuffer(UIConstants.EMPTY_STRING);
			    for (Object obj : form.getSelectedNamesList())
			    {
				AttendeeNameResponseEvent anre = (AttendeeNameResponseEvent) obj;
				if (anre != null)
				{
				    names.append(anre.getFormattedName()).append(";");
				}
			    }
			    form.setSelectedAttendeeNamesAsString(names.toString());
			}
			getInterviewSummaryNotes(form);
		    }
		} // while
		break; // found it, get out of for loop
	    }
	} // for
	form.setGuardianInHouse(validateInHouseGuardian(form.getJuvenileNum()));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward writingSample(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	GenericPrintRequestEvent writingSampleEvent = new GenericPrintRequestEvent();
	IdInfoPrintBean idInfoData = new IdInfoPrintBean();

	writingSampleEvent.addDataObject(idInfoData);
	writingSampleEvent.setReportName("REPORTING::WRITING_SAMPLE");

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(writingSampleEvent);

	CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compResponse);

	ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);
	if (aRespEvent == null)
	{
	    ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compResponse, ReturnException.class);
	    throw returnException;
	}
	else
	{
	    aResponse.setContentType("application/x-file-download");
	    aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");
	    aResponse.setHeader("Cache-Control", "must-validate");
	    aResponse.setContentLength(aRespEvent.getContent().length);
	    aResponse.resetBuffer();
	    OutputStream os;
	    os = aResponse.getOutputStream();
	    os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
	    os.flush();
	    os.close();
	}
	return null;
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward idInfoUJAC(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;
	IdInfoPrintBean idInfoData = new IdInfoPrintBean();

	setLocationInfo(idInfoData, form);
	setGuardianInfo(idInfoData, form);

	// Get casefileId from juvenileCasefileForm that's in session
	{
	    HttpSession session = aRequest.getSession();
	    JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) session.getAttribute(CASEFILE_FORM_STR);
	    setJuvenileInfo(idInfoData, casefileForm);
	}

	CompositeResponse compResp = sendPrintRequest("REPORTING::ID_INFO", idInfoData, null);
	try
	{
	    setPrintContentResp(aResponse, compResp, "ID_INFO", UIConstants.PRINT_AS_PDF_DOC);
	}
	catch (GeneralFeedbackMessageException e)
	{
	    sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
	}

	return null; //because it needs to stay on the same page.
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward idInfo(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;
	IdInfoPrintBean idInfoData = new IdInfoPrintBean();

	setLocationInfo(idInfoData, form);
	setGuardianInfo(idInfoData, form);

	// Get casefileId from juvenileCasefileForm that's in session
	{
	    HttpSession session = aRequest.getSession();
	    JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) session.getAttribute(CASEFILE_FORM_STR);
	    setJuvenileInfo(idInfoData, casefileForm);
	}
	//set data transfer object
	aRequest.getSession().setAttribute("idInfoData", idInfoData);
	// generate report
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.ID_INFO);

	return null; //because it needs to stay on the same page.
    }

    /*
     * @param idInfoData
     * @param form
     */
    private void setLocationInfo(IdInfoPrintBean idInfoData, CalendarEventListForm form)
    {
	CalendarServiceEventResponseEvent cser = form.getCurrentEvent();
	idInfoData.setCurrentDate(DateUtil.dateToString(new Date(), DateUtil.DATE_FMT_1));

	Address locationAddress = new Address();
	idInfoData.setLocationUnitAddress(locationAddress);
	String locationId = cser.getLocationId();

	if (notNullNotEmptyString(locationId))
	{
	    GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent) EventFactory.getInstance(LocationControllerServiceNames.GETJUVENILELOCATION);
	    gle.setLocationId(locationId);

	    CompositeResponse response = MessageUtil.postRequest(gle);
	    LocationResponseEvent lre = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);
	    AddressResponseEvent addressResponseEvent = lre.getLocationAddress();

	    String aLocationUnitId = cser.getLocationUnitId().trim();
	    Collection<LocationResponseEvent> juvLocUnits = lre.getLocationUnits();
	    if (notNullNotEmptyString(aLocationUnitId) && notNullNotEmptyCollection(juvLocUnits))
	    {
		for (LocationResponseEvent aLocation : juvLocUnits)
		{
		    if (aLocationUnitId.equalsIgnoreCase(aLocation.getJuvLocationUnitId()))
		    {
			idInfoData.setLocationUnitName(aLocation.getLocationUnitName());
			PhoneNumberBean phone = new PhoneNumberBean(aLocation.getJuvLocationUnitPhoneNumber());
			idInfoData.setManagerPhone(phone.getFormattedPhoneNumber());

			AddressResponseEvent address = aLocation.getLocationAddress();
			if (address != null && notNullNotEmptyString(address.getStreetName()))
			{
			    addressResponseEvent = address;
			}
			break;
		    }
		} //for
	    }

	    locationAddress.setStreetNum(addressResponseEvent.getStreetNum());
	    locationAddress.setStreetNumSuffix(addressResponseEvent.getStreetNumSuffix());
	    locationAddress.setStreetName(addressResponseEvent.getStreetName());
	    locationAddress.setStreetType(addressResponseEvent.getStreetType());
	    locationAddress.setAptNum(addressResponseEvent.getAptNum());
	    locationAddress.setCity(addressResponseEvent.getCity());
	    locationAddress.setState(addressResponseEvent.getState());
	    locationAddress.setZipCode(addressResponseEvent.getZipCode());
	    locationAddress.setAdditionalZipCode(addressResponseEvent.getAdditionalZipCode());
	}
    }

    /*
     * @param idInfoData
     * @param form
     */
    private void setGuardianInfo(IdInfoPrintBean idInfoData, CalendarEventListForm form)
    {
	String juvenileNum = form.getJuvenileNum();

	GetFamilyConstellationsEvent getFamilyEvent = (GetFamilyConstellationsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
	getFamilyEvent.setJuvenileNum(juvenileNum);

	CompositeResponse response = postRequestEvent(getFamilyEvent);
	Collection<FamilyConstellationMemberListResponseEvent> memberList = MessageUtil.compositeToCollection(response, FamilyConstellationMemberListResponseEvent.class);

	if (memberList != null)
	{
	    for (FamilyConstellationMemberListResponseEvent fme : memberList)
	    {
		if (fme.isGuardian() && fme.isInHomeStatus())
		{
		    GuardianPrintBean guardian = new GuardianPrintBean();

		    GetFamilyMemberDetailsEvent memberDetailsEvent = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
		    memberDetailsEvent.setMemberNum(fme.getMemberNum());
		    response = postRequestEvent(memberDetailsEvent);

		    FamilyMemberDetailResponseEvent guardianRE = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(response, FamilyMemberDetailResponseEvent.class);

		    guardian.setName(new Name(guardianRE.getFirstName(), guardianRE.getMiddleName(), guardianRE.getLastName()));
		    guardian.setSsn(new SocialSecurityBean(guardianRE.getSsn()));
		    if (guardianRE.getDateOfBirth() != null)
		    {
			guardian.setDateOfBirth(DateUtil.dateToString(guardianRE.getDateOfBirth(), DateUtil.DATE_FMT_1));
		    }

		    GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
		    memberAddressEvent.setMemberNum(fme.getMemberNum());
		    response = MessageUtil.postRequest(memberAddressEvent);

		    Collection<AddressResponseEvent> addressResponses = MessageUtil.compositeToCollection(response, AddressResponseEvent.class);
		    Collections.sort((List) addressResponses); //addresses will be sorted by entry date (latest will show first)

		    if (addressResponses != null)
		    {
			for (AddressResponseEvent addre : addressResponses)
			{
			    Address addr = new Address();

			    addr.setStreetNum(addre.getStreetNum());
			    addr.setStreetNumSuffix(addre.getStreetNumSuffix());
			    addr.setStreetName(addre.getStreetName());
			    addr.setStreetType(addre.getStreetType());
			    addr.setAptNum(addre.getAptNum());
			    addr.setCity(addre.getCity());
			    addr.setState(addre.getState());
			    addr.setZipCode(addre.getZipCode());
			    addr.setAdditionalZipCode(addre.getAdditionalZipCode());
			    guardian.setAddress(addr);

			    idInfoData.setMemberRelationship(fme.getRelationToJuvenile());
			    idInfoData.setGuardianFirstName(fme.getFirstName());
			    idInfoData.setGuardianLastName(fme.getLastName());
			    idInfoData.setGuardianMiddleName(fme.getMiddleName());
			    idInfoData.setGuardianStreetNo(addre.getStreetNum());
			    if (addre.getStreetNum() != null)
			    {
				if (addre.getStreetNumSuffix() != null && !addre.getStreetNumSuffix().equals(""))
				{
				    idInfoData.setGuardianStreetNo(addre.getStreetNum() + " " + addre.getStreetNumSuffix());
				}
			    }
			    idInfoData.setGuardianStreetName(addre.getStreetName());
			    idInfoData.setGuardianStreetType(addre.getStreetType());
			    idInfoData.setGuardianApt(addre.getAptNum());
			    idInfoData.setGuardianCity(addre.getCity());
			    idInfoData.setGuardianState(addre.getState());
			    idInfoData.setGuardianZip(addre.getZipCode());
			    idInfoData.setGuardianAdditionalZip(addre.getAdditionalZipCode());
			    break;
			} //for										
		    }

		    GetFamilyMemberContactEvent famMemberContact = new GetFamilyMemberContactEvent();
		    famMemberContact.setMemberId(fme.getMemberNum());
		    response = MessageUtil.postRequest(famMemberContact);

		    Collection<FamilyMemberPhoneResponseEvent> contacts = MessageUtil.compositeToCollection(response, FamilyMemberPhoneResponseEvent.class);
		    for (FamilyMemberPhoneResponseEvent phoneRE : contacts)
		    {
			guardian.setPhoneNumber(new PhoneNumberBean(phoneRE.getPhoneNum()));
			break;
		    }

		    idInfoData.getGuardians().add(guardian);
		}
	    } //for
	}
    }

    /*
     * @param idInfoData
     * @param casefileForm
     */
    public void setJuvenileInfo(IdInfoPrintBean idInfoData, JuvenileCasefileForm casefileForm)
    {
	{
	    Name aName = casefileForm.getJuvenileName();
	    if (aName != null)
	    {
		idInfoData.setJuvenileLastName(aName.getLastName());
		idInfoData.setJuvenileFirstName(aName.getFirstName());
		idInfoData.setJuvenileMiddleName(aName.getMiddleName());
		idInfoData.setJuvenilePrefix(aName.getPrefix());
		idInfoData.setJuvenileSuffix(aName.getSuffix());
	    }

	    if ((aName = casefileForm.getProbationOfficerName()) != null)
	    {
		idInfoData.setOfficerLastName(aName.getLastName());
		idInfoData.setOfficerFirstName(aName.getFirstName());
		idInfoData.setOfficerMiddleName(aName.getMiddleName());
	    }

	    if ((aName = casefileForm.getCaseloadManagerName()) != null)
	    {
		idInfoData.setManagerLastName(aName.getLastName());
		idInfoData.setManagerFirstName(aName.getFirstName());
		idInfoData.setManagerMiddleName(aName.getMiddleName());
	    }
	}

	GetOfficerProfileEvent gofe = (GetOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
	gofe.setOfficerProfileId(casefileForm.getProbationOfficerId());
	CompositeResponse response = MessageUtil.postRequest(gofe);

	OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);
	if (officerProfileResponse != null)
	{
	    PhoneNumber phoneNumber = new PhoneNumber(officerProfileResponse.getWorkPhone());
	    idInfoData.setOfficerPhone(phoneNumber.toString());
	}

	idInfoData.setRace(casefileForm.getRace());
	idInfoData.setSex(casefileForm.getSex());

	// since dateOfBirth, primaryLanguage, BirthCity, etc aren't stored in header info,
	// it is wise to query for juvenile details instead.
	GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
	getJuvProfileEvent.setJuvenileNum(casefileForm.getJuvenileNum());
	response = MessageUtil.postRequest(getJuvProfileEvent);

	JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	JuvenileMainForm juvMainForm = new JuvenileMainForm();
	//UI Juvenile Helper will automatically resolve Id -> Description
	UIJuvenileHelper.setJuvenileMainForm(juvMainForm, juvProfileRE);

	idInfoData.setDateOfBirth(juvMainForm.getDateOfBirth());
	idInfoData.setPrimaryLanguage(juvMainForm.getPrimaryLanguage());
	idInfoData.setBirthCity(juvMainForm.getBirthCity());
	idInfoData.setBirthCounty(juvMainForm.getBirthCounty());
	idInfoData.setBirthCountry(juvMainForm.getBirthCountry());
	idInfoData.setCitizenship(juvMainForm.getIsUSCitizen());
	idInfoData.setNationality(juvMainForm.getNationality());
	idInfoData.setAlienNumber(juvMainForm.getAlienNum());
    }

    /* given a string, return true if it's not null and not empty
     * @param str
     * @return 
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.trim().length() > 0));
    }

    /* given a string, return true if it's not null and not empty
     * @param str
     * @return 
     */
    private boolean notNullAndStringsMatch(String str, String compareStr)
    {
	return (str != null && compareStr != null && str.equalsIgnoreCase(compareStr));
    }

    /* given a collection, return true if it's not null and not empty
     * @param str
     * @return
     */
    private boolean notNullNotEmptyCollection(Collection coll)
    {
	return (coll != null && !coll.isEmpty());
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addAttendance(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;
	HttpSession session = aRequest.getSession();

	if (form.getCurrentJuvenileId() == null)
	{
	    form = new CalendarEventListForm();
	    session.setAttribute("calendarEventListForm", form);
	}

	String juvenileId = aRequest.getParameter(JUV_ID_STR);
	String from = aRequest.getParameter("secondaryAction");
	String attendanceCd = aRequest.getParameter(ATTENDANCECD);
	String addlAttendees = aRequest.getParameter(ADDL_ATTENDEES);
	if (form.getCurrentEvent() == null || (form.getCurrentJuvenileId() != null && !juvenileId.equals(form.getCurrentJuvenileId())))
	{
	    Object formObj = aRequest.getSession().getAttribute(CAL_EVENT_DETAILS_FORM_STR);
	    form.setJuvenileNum(juvenileId);

	    if (from == null || from.trim().length() == 0)
	    {
		from = "workshopCalendar";
	    }
	    form.setSecondaryAction(from);
	    if (formObj != null)
	    {
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm) formObj;

		form.setEventDate(detailsForm.getEventDate());
		form.setCurrentEvent(UISupervisionCalendarHelper.populateCalendarForm(form, detailsForm.getEventId(), juvenileId));
		JuvenileCasefileForm myForm = (JuvenileCasefileForm) session.getAttribute(CASEFILE_FORM_STR);
		if (myForm == null)
		{
		    myForm = new JuvenileCasefileForm();
		    myForm.populateJuvenileCasefileForm(form.getCurrentEvent().getCasefileId());
		    session.setAttribute(CASEFILE_FORM_STR, myForm);
		}
		else
		    if (!juvenileId.equals(myForm.getJuvenileNum()))
		    {
			myForm = new JuvenileCasefileForm();
			myForm.populateJuvenileCasefileForm(form.getCurrentEvent().getCasefileId());
		    }
		UISupervisionCalendarHelper.getAttendanceRecord(form, juvenileId, detailsForm.getEventId());
		if (form.getAddlAttendees().equals("") && addlAttendees != null)
		    form.setAddlAttendees(addlAttendees);
		if (form.getAttendanceStatusCd().equals("") && attendanceCd != null)
		    form.setAttendanceStatusCd(attendanceCd);
		Collection names = UISupervisionCalendarHelper.getJuvenileContacts(juvenileId);
		form.getCurrentEvent().setJuvenileFullName(myForm.getJuvenileFullName());
		form.setContactNames(names);
	    }
	}

	return (aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE));
    }

    /*
     * (non-Javadoc)
     * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;
	HttpSession session = aRequest.getSession();
	String juvenileId = aRequest.getParameter(JUV_ID_STR);

	Object formObj = aRequest.getSession().getAttribute(CAL_EVENT_DETAILS_FORM_STR);
	if (formObj != null)
	{
	    ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm) formObj;
	    form.setCurrentEvent(UISupervisionCalendarHelper.populateCalendarForm(form, detailsForm.getEventId(), juvenileId));

	    JuvenileCasefileForm myForm = (JuvenileCasefileForm) session.getAttribute(CASEFILE_FORM_STR);
	    if (myForm == null)
	    {
		myForm = new JuvenileCasefileForm();
		myForm.populateJuvenileCasefileForm(form.getCurrentEvent().getCasefileId());
		session.setAttribute(CASEFILE_FORM_STR, myForm);
	    }
	    else
		if (!juvenileId.equals(myForm.getJuvenileNum()))
		{
		    myForm = new JuvenileCasefileForm();
		    myForm.populateJuvenileCasefileForm(form.getCurrentEvent().getCasefileId());
		}
	    form.getCurrentEvent().setJuvenileFullName(myForm.getJuvenileFullName());

	    GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
	    getServiceEvent.setServiceEventId(detailsForm.getEventId());
	    CompositeResponse resp = MessageUtil.postRequest(getServiceEvent);
	    List eventsList = MessageUtil.compositeToList(resp, ServiceEventAttendanceResponseEvent.class);

	    Iterator<ServiceEventAttendanceResponseEvent> iter = eventsList.iterator();
	    while (iter.hasNext())
	    {
		ServiceEventAttendanceResponseEvent attendanceEvent = iter.next();
		if (attendanceEvent != null && notNullAndStringsMatch(form.getCurrentEvent().getJuvenileNum(), attendanceEvent.getJuvenileId()))
		{
		    form.setProgressNotes(attendanceEvent.getProgressNotes());
		    form.setAttendanceStatusCd(attendanceEvent.getAttendanceStatusCd());
		    form.setAttendanceStatus(attendanceEvent.getAttendanceStatusDescription());
		    form.setSummaryNotes(form.getCurrentEvent().getInterviewSummaryNotes());
		    form.setAddlAttendees(attendanceEvent.getAddlAttendees());
		    if (!attendanceEvent.getAddlAttendeeNames().isEmpty())
		    {
			form.setSelectedNamesList(attendanceEvent.getAddlAttendeeNames());
		    }
		    getInterviewSummaryNotes(form);
		}
	    }
	}
	form.setAction("viewAttendance");
	form.setSecondaryAction("workshopCalendar");

	return aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE);
    }

    // this method based on coding for casefile closing	
    // CHECK FOR GUARDIAN ADDRESS AND IN HOUSE	
    private String validateInHouseGuardian(String juvenileNum)
    {
	String result = "N";
	boolean guardianExceptionFound = true;
	boolean guardianInHomeExceptionFound = true;

	GetFamilyConstellationsEvent getFamilyEvent = (GetFamilyConstellationsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
	getFamilyEvent.setJuvenileNum(juvenileNum);

	CompositeResponse response = MessageUtil.postRequest(getFamilyEvent);
	Collection guardians = MessageUtil.compositeToCollection(response, FamilyConstellationMemberListResponseEvent.class);

	if (guardians != null && !guardians.isEmpty())
	{
	    Iterator<FamilyConstellationMemberListResponseEvent> iter = guardians.iterator();
	    while (iter.hasNext())
	    {
		FamilyConstellationMemberListResponseEvent fme = iter.next();
		if (fme.isGuardian())
		{
		    if (fme.isInHomeStatus())
		    {
			guardianInHomeExceptionFound = false;
		    }

		    Iterator guardianAddresses = getGuardianAddresses(fme.getMemberNum()).iterator();
		    while (guardianAddresses.hasNext())
		    {
			AddressResponseEvent address = (AddressResponseEvent) guardianAddresses.next();
			if (address != null && "RES".equalsIgnoreCase(address.getAddressTypeId()))
			{
			    guardianExceptionFound = false;
			}
		    }
		}
	    }
	}
	if (!guardianExceptionFound && !guardianInHomeExceptionFound)
	{
	    result = "Y";
	}
	return result;
    }

    private Collection getGuardianAddresses(String memberNumber)
    {
	GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
	memberAddressEvent.setMemberNum(memberNumber);

	CompositeResponse response = MessageUtil.postRequest(memberAddressEvent);
	Collection addressResponses = MessageUtil.compositeToCollection(response, AddressResponseEvent.class);
	return addressResponses;
    }

    /**
     * # U.S.30339 changes Replication of DisplayCalendarEventListAction :
     * displayCalendarEventList
     * 
     * @param aRequest
     * @param oldForm
     * @param form
     * @return
     */
    private Collection<CalendarServiceEventResponseEvent> getProposedEventDayEvent(HttpServletRequest aRequest, ScheduleNewEventForm oldForm, CalendarEventListForm form)
    {
	form.setEventDate(oldForm.getCurrentService().getCurrentEvent().getStartDate());

	GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);

	Calendar requestedDate = Calendar.getInstance();
	if (oldForm != null && oldForm.getCurrentService() != null && oldForm.getCurrentService().getCurrentEvent() != null && oldForm.getCurrentService().getCurrentEvent().getStartDate() != null)
	    requestedDate.setTime(oldForm.getCurrentService().getCurrentEvent().getStartDate());
	requestedDate.set(Calendar.HOUR_OF_DAY, 0);
	requestedDate.set(Calendar.MINUTE, 0);
	requestedDate.set(Calendar.SECOND, 0);
	requestedDate.set(Calendar.MILLISECOND, 0);

	gce.setStartDatetime(requestedDate.getTime());

	requestedDate.set(Calendar.HOUR_OF_DAY, 23);
	requestedDate.set(Calendar.MINUTE, 59);
	requestedDate.set(Calendar.SECOND, 59);
	requestedDate.set(Calendar.MILLISECOND, 0);

	gce.setEndDatetime(requestedDate.getTime());

	//	form.setOfficerId(oldForm.getOfficerId()); //TODO need to discuss with uma.

	String officerId = "";//oldForm.getOfficerId();
	String juvenileNum = form.getJuvenileNum();

	//Collection contexts = new ArrayList();
	CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
	calendarContextEvent.setJuvenileId(form.getJuvenileNum());
	if (notNullNotEmptyString(officerId))
	{
	    calendarContextEvent.setProbationOfficerId(officerId);
	    form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JPO);
	}
	else
	    if (notNullNotEmptyString(juvenileNum))
	    {
		calendarContextEvent.setJuvenileId(juvenileNum);
		form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JUVENILE);
	    }

	gce.setCalendarContextEvent(calendarContextEvent);
	gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));

	List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);

	if (events.size() > 1)
	{
	    Collections.sort(events);
	}

	List wrkList = events;
	InvolvedWeaponOffenseBean iwo = new InvolvedWeaponOffenseBean();
	String desc = "";
	for (int x = 0; x < wrkList.size(); x++)
	{
	    List wpList = new ArrayList();
	    CalendarServiceEventResponseEvent csresp = (CalendarServiceEventResponseEvent) wrkList.get(x);
	    if ("SAN".equals(csresp.getEventTypeCode()))
	    {
		// weaponDesc field is delimited by | for each element row and by ^ for each value within the element			
		//	example: "1020^Felony^OFFENSE DESCRIPTION 1^Y^FIREARM|1010^Misdemeanor^OFFENSE DESCRIPTION 2^N|";
		desc = csresp.getWeaponDescs();
		if (desc != null && desc.indexOf("|") > 1)
		{
		    String weaponDescs[] = desc.split("\\|");
		    for (int w = 0; w < weaponDescs.length; w++)
		    {
			iwo = new InvolvedWeaponOffenseBean();
			desc = weaponDescs[w];
			String weaponDesc[] = desc.split("\\^");
			if (weaponDesc.length > 3)
			{
			    iwo.setReferralNumber(weaponDesc[0]);
			    iwo.setOffenseCategoryDescription(weaponDesc[1]);
			    iwo.setOffenseDescription(weaponDesc[2]);
			    iwo.setWeaponInvolvedStr(weaponDesc[3]);
			}
			iwo.setTypeOfWeaponDescription("");
			if (weaponDesc.length == 5)
			{
			    iwo.setTypeOfWeaponDescription(weaponDesc[4]);
			}
			wpList.add(iwo);
		    }
		}
	    }
	    csresp.setOffenseInvolvedWeaponList(wpList);
	}
	events = new ArrayList(wrkList);

	wrkList = null;

	List docketEvents = this.getTodaysDocketEvents(aRequest, form);
	if (docketEvents != null)
	{
	    form.setDocketEvents(docketEvents);
	}
	return events;
    }

    /**
     * # U.S.30339 changes Replication of DisplayCalendarEventListAction :
     * getTodaysDocketEvents
     * 
     * @param aRequest
     * @param form
     * @return
     */
    private List getTodaysDocketEvents(HttpServletRequest aRequest, CalendarEventListForm form)
    {
	List docketEvents = new ArrayList();

	HttpSession session = aRequest.getSession();
	Collection<CalendarEventResponse> calendarList = (Collection) session.getAttribute(form.getCalendarType());
	if (calendarList != null)
	{
	    Calendar requestedDate = Calendar.getInstance();
	    if (form.getEventDate() != null)
		requestedDate.setTime(form.getEventDate());
	    requestedDate.set(Calendar.HOUR_OF_DAY, 0);
	    requestedDate.set(Calendar.MINUTE, 0);
	    requestedDate.set(Calendar.SECOND, 0);
	    requestedDate.set(Calendar.MILLISECOND, 0);

	    // filter events only for the requested date
	    for (CalendarEventResponse event : calendarList)
	    {
		if (event instanceof DocketEventResponseEvent)
		{
		    Calendar eventDate = Calendar.getInstance();
		    eventDate.setTime(event.getStartDatetime());
		    eventDate.set(Calendar.HOUR_OF_DAY, 0);
		    eventDate.set(Calendar.MINUTE, 0);
		    eventDate.set(Calendar.SECOND, 0);
		    eventDate.set(Calendar.MILLISECOND, 0);

		    if (eventDate.equals(requestedDate))
		    {
			docketEvents.add(event);
		    }
		}
	    }
	}

	return docketEvents;
    }

    /*
     * @param aMapping @param aForm @param aRequest @param aResponse @return
     */
    public ActionForward printAttendanceListBFO(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;

	//<KISHORE>JIMS200057084 : View Calendar - Attendance Sign In Sheet (KK)
	Collection<ProgramReferralResponseEvent> referralslist = form.getProgramReferralList();
	List<ProgramReferralResponseEvent> attendanceList = new ArrayList();
	if (referralslist != null)
	{
	    Iterator iter = referralslist.iterator();
	    while (iter.hasNext())
	    {
		ProgramReferralResponseEvent referral = (ProgramReferralResponseEvent) iter.next();
		if (!UIConstants.ATTENDANCE_CANCELLED_STATUS.equalsIgnoreCase(referral.getExtnNum()) && !UIConstants.ATTENDANCE_EXCUSED_STATUS.equalsIgnoreCase(referral.getExtnNum()))
		{
		    attendanceList.add(referral);
		}
	    }
	}
	Collections.sort(attendanceList, ProgramReferralResponseEvent.refNameComparator);
	form.setProgramReferralList(attendanceList);
	//US 190130 STARTS
	//String instructorName1 = calServRespEvent.getInstructorName().replaceAll("\\s*\\(.*?\\)", "");//this works too
	String instructorName = form.getCurrentEvent().getInstructorName();
	String trimmedName = instructorName.contains("(") ? instructorName.substring(0, instructorName.indexOf("(")).trim() : instructorName;
	form.getCurrentEvent().setInstructorName(trimmedName);
	//US 190130 ENDS
	aRequest.getSession().setAttribute("reportInfo", form);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.EVENT_SIGN_IN_SHEET);

	return null;
    }

    /* (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.details", "displayCalendarEventDetails");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "returnToCalendar");
	keyMap.put("button.returnToCalendar", "returnToCalendar");
	keyMap.put("button.conflictingEvents", "displayConflictingEventDetails");
	keyMap.put("button.preScheduledEvents", "displayPreScheduledEventDetails");
	keyMap.put("button.docketEventDetails", "displayDocketEventDetails");
	keyMap.put("button.appointmentLetter", "generateAppointmentLetter");
	keyMap.put("button.documentAttendance", "documentAttendance");
	keyMap.put("button.writingSample", "writingSample");
	keyMap.put("button.idInfo", "idInfo");
	keyMap.put("button.addAttendees", "addAttendance");
	keyMap.put("button.view", "view");
	keyMap.put("button.printAttendanceList", "printAttendanceListBFO");
    }
}
