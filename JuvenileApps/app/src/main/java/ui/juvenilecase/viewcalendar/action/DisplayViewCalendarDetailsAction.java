//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\viewcalendar\\action\\DisplayViewCalendarDetailsAction.java

package ui.juvenilecase.viewcalendar.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.GetProgramReferralServiceEventsEvent;
import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.interviewinfo.GetInterviewDetailEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.programreferral.GetJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.GetProgramReferralsByServiceEventEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayViewCalendarDetailsAction extends JIMSBaseAction
{
	private final String SELECTED_VALUE_PARM = "selectedValue" ;
	private final String EVENT_ID_PARM = "eventId" ;
	private final String JUV_NAME_OR_EVENT_TYPE_PARM = "jname" ;
	private final String EVENT_TYPE_PARM = "eventType" ;
	private final String REFERRAL_ID_PARM = "referralId" ;
	private final String NO_VALUE_SELECTED = "NO_VALUE_SELECTED" ;
	private final String VALUE_SELECTED = "VALUE_SELECTED" ;

	/**
	 * @roseuid 45F1B0C2028D
	 */
	public DisplayViewCalendarDetailsAction()
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
	public ActionForward displayCalendarEventDetails( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		/*
		 * this parameter (jname) will hold one of two values:
		 * 1) the value for PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED
		 * 2) the value of the Juvenile's name (full name).
		 * the intent is that if the event type is *not* of pre-scheduled,
		 * then on the subsequent (details) screen (if the user selects a 
		 * hyperlink on the list), the only data item that needs to be 
		 * displayed in the Juvenile Summary List is the Juvenile's name.
		 * So, in this scenario, we take the parameter value, which looks 
		 * like: JONES:BRIAN:ALAN and reformat it to look like 
		 * "JONES, BRIAN ALAN" and set that on the Form. 
		 */
		String calTypeOrJuvName = aRequest.getParameter( JUV_NAME_OR_EVENT_TYPE_PARM ) ;
		if( calTypeOrJuvName == null )
		{ /* 05jun2008 - mjt - in case we get a call where 
			 * the parm is not set, default to prescheduled
			 */
			calTypeOrJuvName = PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED ;
		}

		String eventId = aRequest.getParameter( EVENT_ID_PARM ) ;
		if( eventId == null )
		{
			eventId = "unknownEventID" ;
		}
		
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		Collection<CalendarServiceEventResponseEvent> dayEventList = form.getDayEvents() ;
		if( dayEventList != null )
		{
			for( CalendarServiceEventResponseEvent currentEvent : dayEventList )
			{
				if( currentEvent != null && currentEvent.getEventId().equals( eventId ) )
				{
					form.setCurrentEvent( currentEvent ) ;
	
					GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) 
							EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
					getServiceEvent.setServiceEventId(currentEvent.getEventId());
					ServiceEventAttendanceResponseEvent responseEvent = (ServiceEventAttendanceResponseEvent)
							MessageUtil.postRequest(getServiceEvent, ServiceEventAttendanceResponseEvent.class);
					
					if( responseEvent != null ) 
					{
						form.setProgressNotes(responseEvent.getProgressNotes());
				 		form.setAttendanceStatusCd(responseEvent.getAttendanceStatusCd());
				 		form.setAttendanceStatus(responseEvent.getAttendanceStatusDescription());
					}
					
				 	form.setSummaryNotes( currentEvent.getInterviewSummaryNotes() ) ;
					break ; // just get the first one in the collection
				}
			} // for
		}

		/* pre-schedule event types will have a referral */
		if( PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED.equals( calTypeOrJuvName ) )
		{
			String selectedJuvNum = aRequest.getParameter( SELECTED_VALUE_PARM ) ;
			if( selectedJuvNum == null ||  (selectedJuvNum.length() == 0) )
			{
				selectedJuvNum = NO_VALUE_SELECTED ;			
			}
			
			// get a list of referrals
			GetProgramReferralsByServiceEventEvent getPREvent = (GetProgramReferralsByServiceEventEvent)
					EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENT ) ;

			getPREvent.setServiceEventId( eventId ) ;
			getPREvent.setJuvenileNum( "" ) ;
			CompositeResponse response = MessageUtil.postRequest( getPREvent ) ;
			if( response != null )
			{ // we got a list of referrals
				List referralList = (List)MessageUtil.compositeToCollection( response, ProgramReferralResponseEvent.class ) ;

				// now, get a list of attendance records
				GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) 
								EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);					
				getServiceEvent.setServiceEventId( eventId );
				CompositeResponse responseEvent = MessageUtil.postRequest(getServiceEvent);
				if( responseEvent != null ) 
				{// got the attendance list
					List attendanceList = (List)MessageUtil.compositeToCollection( responseEvent, ServiceEventAttendanceResponseEvent.class ) ;
					
					for( Iterator<ProgramReferralResponseEvent> iter = referralList.iterator(); iter.hasNext(); /*empty*/ )
					{ /* for each record in the attendance list, look through the
						 * referral list for the matching record (based on Juv #
					   */ 
						ProgramReferralResponseEvent refEvent = iter.next() ;
						for( Iterator<ServiceEventAttendanceResponseEvent> aIter = attendanceList.iterator(); aIter.hasNext(); /*empty*/ )
						{
							ServiceEventAttendanceResponseEvent attend = aIter.next() ;
							if( attend.getJuvenileId().equals(refEvent.getJuvenileId()) )
							{
								refEvent.setExtnNum( attend.getAttendanceStatusDescription() ) ;
								break ; // got the match, get out of this loop
							}
						}

						/* if the user selected this Juv # from the Event List set 
						 * the value so the JSP can display which Juv was selected
						 */
						refEvent.setOutComeCd( selectedJuvNum.equals(refEvent.getJuvenileId()) ? VALUE_SELECTED : NO_VALUE_SELECTED ) ;
					} // outer for loop - referral list
				}
				Collections.sort(referralList,ProgramReferralResponseEvent.refNameComparator);

				form.setProgramReferralList( referralList ) ;
			}

			// let the JSP know what to display
			form.setCalendarType( PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED ) ;
		}
		else
		{ /* juvName will contain a value which looks like this: JONES:BRIAN:ALAN */
			String [ ] iNames = calTypeOrJuvName.split( ":", 3 ) ;

			if( iNames == null )
			{
				form.getSearchEvent().setJuvenileFullname( "Name not present." ) ;
			}
			else
			{
				StringBuffer name = new StringBuffer( iNames[ 0 ] ) ;

				if( iNames[ 1 ] != null && ( iNames[ 1 ].length() > 0 ) )
				{ /* first name */
					name.append( ", " ).append( iNames[ 1 ] ) ;
				}
				
				if( ( iNames.length > 2 ) && iNames[ 2 ] != null && ( iNames[ 2 ].length() > 0 ) )
				{ /* middle name */
					name.append( " " ).append( iNames[ 2 ] ) ;
				}

				// let the JSP know what to display
				form.setCalendarType( PDCalendarConstants.CALENDAR_TYPE_OTHER ) ;

				form.getSearchEvent().setJuvenileFullname( name.toString() ) ;
			 	getInterviewSummaryNotes( form ) ;
			}
		}
		
		String instructorName = form.getCurrentEvent().getInstructorName();
		String trimmedName = instructorName.contains("(") ? instructorName.substring(0, instructorName.indexOf("(")).trim() : instructorName;		
		form.getCurrentEvent().setInstructorName(trimmedName);

		ActionForward forward = aMapping.findForward( UIConstants.DETAIL_SUCCESS ) ;
		return forward ;
	}

	/*
	 * @param form
	 * @return
	 */
	private boolean getInterviewSummaryNotes( CalendarEventListForm form )
	{
		boolean rtn = false ;
		String interviewId = form.getCurrentEvent().getInterviewId() ;
		
		if( notNullNotEmptyString( interviewId ) )
		{
			GetInterviewDetailEvent event = new GetInterviewDetailEvent() ;
			event.setInterviewId( interviewId ) ;

			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( event ) ;
			Collection<InterviewDetailResponseEvent> interviews = 
					MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), InterviewDetailResponseEvent.class ) ;

			Iterator iter = interviews.iterator() ;
			if( iter.hasNext())
			{
				InterviewDetailResponseEvent detail = (InterviewDetailResponseEvent)iter.next() ;
				form.setSummaryNotes( detail.getSummaryNotes() ) ;
				rtn = true ;
			}
		}
		
		return( rtn ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward displayDocketEventDetails( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String eventId = aRequest.getParameter( EVENT_ID_PARM ) ;
		String eventType = aRequest.getParameter( EVENT_TYPE_PARM ) ;
		
		if( notNullNotEmptyString( eventId )  &&
				notNullNotEmptyString( eventType ) )
		{
			CalendarEventListForm form = (CalendarEventListForm)aForm ;
			List<DocketEventResponseEvent> docketList = form.getDocketEvents() ;
			if( docketList != null )
			{
				for( DocketEventResponseEvent currentEvent : docketList )
				{
					if( currentEvent != null && 
							currentEvent.getDocketEventId().equals( eventId ) && 
							currentEvent.getDocketType().equals( eventType ) )
					{
						form.setCurrentDocketEvent( currentEvent ) ;
						break ;
					}
				}
			} // if
		}

		ActionForward forward = aMapping.findForward( UIConstants.DOCKET_SUCCESS ) ;
		return forward ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward refreshFields( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		form.getSearchEvent().clear() ;
		form.getSearchEvent().setSearchType( "" ) ;
		
		return aMapping.findForward( UIConstants.SEARCH_SUCCESS ) ;
	}

	/*
	 * @param aMapping @param aForm @param aRequest @param aResponse @return
	 */
	public ActionForward printAttendanceList( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		
		//<KISHORE>JIMS200057084 : View Calendar - Attendance Sign In Sheet (KK)
		Collection<ProgramReferralResponseEvent> referralslist = form.getProgramReferralList();
		List<ProgramReferralResponseEvent> attendanceList = new ArrayList();
		if(referralslist != null){
			Iterator iter = referralslist.iterator();
			while(iter.hasNext()){
				ProgramReferralResponseEvent referral = (ProgramReferralResponseEvent)iter.next();
				if(!UIConstants.ATTENDANCE_CANCELLED_STATUS.equalsIgnoreCase(referral.getExtnNum()) &&
				   !UIConstants.ATTENDANCE_EXCUSED_STATUS.equalsIgnoreCase(referral.getExtnNum())){
					attendanceList.add(referral);
				}
			}
		}
		Collections.sort(attendanceList,ProgramReferralResponseEvent.refNameComparator);
		form.setProgramReferralList(attendanceList);
		
		CompositeResponse resp = sendPrintRequest( "REPORTING::EVENT_SIGN_IN_SHEET", form, null ) ;
		try
		{
			setPrintContentResp( aResponse, resp, "EventSignInSheet.pdf", UIConstants.PRINT_AS_PDF_DOC ) ;
		}
		catch( Exception e )
		{
			sendToErrorPage( aRequest, "error.generic", "Unknown error occurred while printing: " + e.toString() ) ;
			return aMapping.findForward( UIConstants.DETAIL_SUCCESS ) ;
		}

		return null ;
	}

	/*
	 * @param aMapping @param aForm @param aRequest @param aResponse @return
	 */
	public ActionForward printAttendanceListBFO( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		
		//<KISHORE>JIMS200057084 : View Calendar - Attendance Sign In Sheet (KK)
		Collection<ProgramReferralResponseEvent> referralslist = form.getProgramReferralList();
		List<ProgramReferralResponseEvent> attendanceList = new ArrayList();
		if(referralslist != null){
			Iterator iter = referralslist.iterator();
			while(iter.hasNext()){
				ProgramReferralResponseEvent referral = (ProgramReferralResponseEvent)iter.next();
				if(!UIConstants.ATTENDANCE_CANCELLED_STATUS.equalsIgnoreCase(referral.getExtnNum()) &&
				   !UIConstants.ATTENDANCE_EXCUSED_STATUS.equalsIgnoreCase(referral.getExtnNum())){
					attendanceList.add(referral);
				}
			}
		}
		Collections.sort(attendanceList,ProgramReferralResponseEvent.refNameComparator);
		form.setProgramReferralList(attendanceList);

		String instructorName = form.getCurrentEvent().getInstructorName();
		String trimmedName = instructorName.contains("(") ? instructorName.substring(0, instructorName.indexOf("(")).trim() : instructorName;		
		form.getCurrentEvent().setInstructorName(trimmedName);
		
		aRequest.getSession().setAttribute("reportInfo", form);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.EVENT_SIGN_IN_SHEET);

		return null ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayProgramReferralDetails( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String referralId = aRequest.getParameter( REFERRAL_ID_PARM ) ;

		if( notNullNotEmptyString( referralId ) )
		{
			GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent)
					EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS ) ;
			
			gpdt.setProgramReferralId( referralId ) ;
			CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest( gpdt ) ;
			
			ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent)
					MessageUtil.filterComposite( compositeResponse, ProgramReferralResponseEvent.class ) ;
			if( respDetail != null )
			{
				UIProgramReferralBean programReferral = new UIProgramReferralBean( respDetail ) ;
				
				//=========================== US 154561 ==========================
				if (programReferral != null)
				{

				    UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(UIJuvenileCaseworkHelper.getHeaderForm(aRequest, true), programReferral.getCasefileId());

				    GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);
				    gprs.setJuvenileNum(programReferral.getJuvenileId());
				    gprs.setProgramId(programReferral.getProviderProgramId());
				    gprs.setProgramReferralId(programReferral.getReferralId());

				    CompositeResponse compositeResp = (CompositeResponse) MessageUtil.postRequest(gprs);

				    List calendarEvents = (List) MessageUtil.compositeToCollection(compositeResp, CalendarServiceEventResponseEvent.class);

				    float creditHours = 0;
				    float totalCreditHours = 0;
				    if (calendarEvents != null)
				    {
					Collections.sort((List) calendarEvents);
					List activeCalendarEvents = new ArrayList();
					Iterator<CalendarServiceEventResponseEvent> iter = calendarEvents.iterator();
					while (iter.hasNext())
					{
					    CalendarServiceEventResponseEvent event = iter.next();					    
					    
					    creditHours = (float)UIProgramReferralHelper.calculateTimeDiff(event.getStartDatetime(), event.getEndDatetime());
					    totalCreditHours += creditHours;
					    programReferral.setCreditHours(creditHours);
					}
					
					programReferral.setTotalCreditHours(totalCreditHours);
					
					programReferral.setJuvenileEvents(activeCalendarEvents);
				    }				   

				}
				
				//=====================================================
				((CalendarEventListForm)aForm).setProgramReferral( programReferral ) ;
			}
		}

		return aMapping.findForward( UIConstants.VIEW_DETAIL ) ;
	}

	/*
	 * @param String str
	 * @return boolean
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  str.length() > 0 ) ;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.details", "displayCalendarEventDetails" ) ;
		keyMap.put( "button.docketEventDetails", "displayDocketEventDetails" ) ;
		keyMap.put( "button.referral", "displayProgramReferralDetails" ) ;
		keyMap.put( "button.cancel", "refreshFields" ) ;
		keyMap.put( "button.printAttendanceList", "printAttendanceListBFO" ) ;
	}
}
