/*
 * Created on Apr 29, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package naming;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PDCalendarConstants
{
	public static final String DAILY_RECURRENCE = "D";
	public static final String WEEKLY_RECURRENCE = "W";
	public static final String MONTHLY_RECURRENCE = "M";
	public static final String YEARLY_RECURRENCE = "Y";
	
	public static final String SERVICE_EVENT_STATUS_PENDING = "PN";
	public static final String SERVICE_EVENT_STATUS_SCHEDULED = "SC";
	public static final String SERVICE_EVENT_STATUS_COMPLETED = "CM";
	public static final String SERVICE_EVENT_STATUS_CANCELLED = "CC";
	public static final String SERVICE_EVENT_STATUS_AVAILABLE = "AV";
	
	public static final String JUV_ATTEND_STATUS_UNCONFIRMED = "UN";
	public static final String JUV_ATTEND_STATUS_CONFIRMED = "CO";
	public static final String JUV_ATTEND_STATUS_ATTENDED = "AT";
	public static final String JUV_ATTEND_STATUS_ABSENT = "AB";
	public static final String JUV_ATTEND_STATUS_CANCELLED = "CC";
	public static final String JUV_ATTEND_STATUS_EXCUSED = "EX";
	
	public static final String SCHOOL_ADJUDICATION="SAN";
	
	public static final String CALENDAR_TYPE_PROVIDER = "PROVIDERCALENDAR";
	public static final String CALENDAR_TYPE_JUVENILE = "JUVENILECALENDAR";
	public static final String CALENDAR_TYPE_JPO = "JPOCALENDAR";
	public static final String CALENDAR_TYPE_CLM = "CLMCALENDAR";	
	public static final String CALENDAR_TYPE_OTHER = "OTHERCALENDAR";
	public static final String CALENDAR_TYPE_PRESCHEDULED = "PRE";
	public static final String INVALID_CONTEXT_ID = "-9999999";
		
	public static String CALENDAR_SEARCH = "calendarSearch";
	public static String INSTRUCTOR_SEARCH = "instructorSearch";
	public static String DOCKET_SEARCH = "docketSearch";
	public static String JUVENILE_SEARCH = "juvenileSearch";
	public static String FUTURE_EVENTS_JUVENILE = "futureEventsJuvenile" ;
	public static String FUTURE_EVENTS_SVC_PROVIDER = "futureEventsSrvProvider" ;
	
	public static String FUTURE_PROVIDER_SUCCESS = "futureProviderSucess" ;
	public static String FUTURE_JUV_SUCCESS = "futureJuvSucess" ;
	
	public static String JPO_NAME_SEARCH = "jpoNameSearchType";
	public static String CLM_NAME_SEARCH = "caseloadManagerNameSearchType";
	public static String JUVENILE_NAME_SEARCH = "juvenileNameSearchType";
	
	//<KISHORE>JIMS200060153 : MJCW - Schedule Calendar Event is Timing out on SP Events
	//Request type for retrieving the calendar events
	public static final String CALENDAR_EVENTS_FOR_INACTIVATE_LOCATION="INACTIVATE_LOCATION";
}
