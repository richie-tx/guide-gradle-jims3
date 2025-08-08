//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\workshopcalendar\\action\\DisplayServiceEventDetailsAction.java

package ui.juvenilecase.workshopcalendar.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.GetProgramReferralsByServiceEventIdEvent;
import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.GetServiceEventCancellationListEvent;
import messaging.calendar.GetServiceEventDetailsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ProgramReferralsByServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCalendarConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.collections.FastArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.programreferral.JuvenileProgramReferral;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;

public class DisplayServiceEventDetailsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 44805C380387
	 */
	public DisplayServiceEventDetailsAction( )
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49B402DD
	 */
	public ActionForward details( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String eventId = aRequest.getParameter( "eventId" ) ;
		String windowType = aRequest.getParameter( "windowType" ) ;

		if( eventId != null && eventId.length( ) > 0 )
		{
			List<CalendarServiceEventResponseEvent> eventList = new FastArrayList( ) ;

			ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
			Object formObj = aRequest.getSession( ).getAttribute( "calendarEventListForm" ) ;
			if( formObj != null )
			{
				CalendarEventListForm calForm = (CalendarEventListForm)formObj ;
				calForm.setCalendarEventId(eventId.toString());
				Collection col = calForm.getDayEvents( ) ;
				if( col != null )
				{
					eventList.addAll( col ) ;
				}
			}
			else
			{
				formObj = aRequest.getSession( ).getAttribute( "scheduleNewEventForm" ) ;
				ScheduleNewEventForm calForm = (ScheduleNewEventForm)formObj ;
				eventList.addAll( calForm.getCurrentService( ).getCurrentEvent( ).getConflictingEvents( ) ) ;
			}

			if( eventList.size( ) > 0 )
			{
				for( CalendarServiceEventResponseEvent event : eventList )
				{
					if( event.getEventId( ).equals( eventId ) )
					{
						detailsForm.setEventId( eventId ) ;
						detailsForm.setCalendarEventId( event.getCalendarEventId( ).toString( ) ) ;
						detailsForm.setServiceEventId( event.getServiceEventId() ) ;
						detailsForm.setServiceProviderName( event.getServiceProviderName( ) ) ;
						detailsForm.setServiceName( event.getServiceName( ) ) ;
						detailsForm.setEventDate( event.getEventDate( ) ) ;
						detailsForm.setServiceLocationName( event.getServiceLocationName( ) ) ;
						detailsForm.setLocationId( event.getLocationId( ) ) ;
						detailsForm.setEventSessionLength( event.getEventSessionLength( ) ) ;
						detailsForm.setEventType( event.getEventType( ) ) ;
						detailsForm.setEventTypeCode(  event.getEventTypeCode( ) ) ;
						detailsForm.setEventStatus( event.getEventStatus( ) ) ;
						detailsForm.setEventStatusCd( event.getEventStatusCode( ) ) ;
						detailsForm.setEventComments( event.getEventComments( ) ) ;
						detailsForm.setAssociatedContexts( event.getAssociatedContexts( ) ) ;
						detailsForm.setAdminUserProfileId( event.getAdminUserProfileId( ) ) ;
						detailsForm.setEventMaximum( event.getMaxAttendance( ) ) ;
						detailsForm.setEventMinimum( event.getMinAttendance( ) ) ;
						detailsForm.setTotalScheduled( event.getCurrentEnrollment( ) ) ;
						detailsForm.setInstructorName( event.getInstructorName( ) ) ;
						detailsForm.setCurrentEvent(event);
						//set current event

						/* 5mar08 - mjt - needed to add this because the Detail screen may
						 * need to be popped up from a clicked URL, which means the JSP will
						 * have to adjust what is displayed, such as Instructions and the
						 * buttons, and so forth
						 */
						detailsForm.setWindowType( "details" ) ;
						if( windowType != null  &&  windowType.equals( "popup" ) )
						{
							detailsForm.setWindowType( "popup" ) ;
						}
					}
				}
			}
			detailsForm.setSecondaryAction( "workshopCalendar" ) ;
		}

		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward returnToCalendar( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{      
		return aMapping.findForward( UIConstants.CANCEL ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	
	
	public ActionForward documentAttendance( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		detailsForm.setOutSourceVendorOver7Days(false);
		Object obj = aRequest.getSession().getAttribute("userIsInHouse");
		
		String isInHouse = null;
		
		if (obj == null){
			//Accessing via task.  Need to retrieve event details.
			this.getServiceEventDetails(detailsForm);
			aRequest.getSession().removeAttribute("userIsInHouse");
		} else {
			isInHouse = obj.toString();
		}
//		detailsForm.setOutSourceVendorOver7Days(true);  // for testing only	
		if ("false".equals(isInHouse) && detailsForm.getEventDate().before(DateUtil.getCurrentDate() ) )
		{
			int curYear = convertStringToInt(DateUtil.dateToString(DateUtil.getCurrentDate(), "yyyy") );
			int evntYear = convertStringToInt(DateUtil.dateToString(detailsForm.getEventDate(), "yyyy") );
			int curDays = convertStringToInt(DateUtil.dateToString(DateUtil.getCurrentDate(), "DDD") );
			int evntDays = convertStringToInt(DateUtil.dateToString(detailsForm.getEventDate(), "DDD") );
			// compare years and take into account of more than 1 year difference and a leap year
			if (curYear > evntYear)
			{
				int yyDiff = curYear - evntYear;
				curDays = curDays + (365 * yyDiff);
				if (curYear/4 == 0){
				  curDays += 1;	
				}
			}	
		    int diff = curDays - evntDays;
			if (diff > 7){
				detailsForm.setOutSourceVendorOver7Days(true);
			}
		}
		
		GetServiceEventAttendanceEvent getServAttendanceEvent = (GetServiceEventAttendanceEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE ) ;

		getServAttendanceEvent.setServiceEventId( detailsForm.getEventId( ) ) ;
		dispatch.postEvent( getServAttendanceEvent ) ;

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		MessageUtil.processReturnException( dataMap ) ;

		Collection<ServiceEventAttendanceResponseEvent> eventAttendanceList = 
				MessageUtil.compositeToCollection( compositeResponse, ServiceEventAttendanceResponseEvent.class ) ;
		
		ArrayList existingAttendanceEvents = new ArrayList( ) ;
		ArrayList newAttendanceEvents = new ArrayList( ) ;
		
		 //added for #36737
		GetProgramReferralsByServiceEventIdEvent getProgramReferrals = (GetProgramReferralsByServiceEventIdEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENTID);
		getProgramReferrals.setServiceEventId(detailsForm.getEventId( ));	
		compositeResponse = (CompositeResponse) MessageUtil.postRequest(getProgramReferrals);
		
		List<ProgramReferralsByServiceEventResponseEvent> programReferrals = (List<ProgramReferralsByServiceEventResponseEvent>)MessageUtil.compositeToCollection(compositeResponse,ProgramReferralsByServiceEventResponseEvent.class);
		
		
		List<ServiceEventAttendanceResponseEvent> tentativeReferredPrgmReferrals = new ArrayList<ServiceEventAttendanceResponseEvent>();
		
		if( eventAttendanceList.size( ) > 0 )
		{
			detailsForm.setAttendanceEventsPresent( true ) ;
			for( ServiceEventAttendanceResponseEvent serviceEvtAttnRespEvt : eventAttendanceList )
			{
				Map<String,String> referralStatusMap =  new HashMap<String,String>();
				if( serviceEvtAttnRespEvt.getAttendanceStatusCd( ) != null && 
						( serviceEvtAttnRespEvt.getAttendanceStatusCd( ).equals( PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED ) || 
						serviceEvtAttnRespEvt.getAttendanceStatusCd( ).equals( PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED ) ) )
				{
					newAttendanceEvents.add( serviceEvtAttnRespEvt ) ;
				}
				else
				{
					existingAttendanceEvents.add( serviceEvtAttnRespEvt ) ;
				}
			
				for( ProgramReferralsByServiceEventResponseEvent programRef : programReferrals )
				{
					//get Program Referral for the prog ref id.
					/*GetProgramReferralDetailsEvent prgmRefDetails = (GetProgramReferralDetailsEvent)EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
					prgmRefDetails.setProgramReferralId(programRef.getProgramReferralId());
					compositeResponse = (CompositeResponse) MessageUtil.postRequest(prgmRefDetails);
					ProgramReferralResponseEvent prgmRefResp = (ProgramReferralResponseEvent)MessageUtil.filterComposite(compositeResponse,ProgramReferralResponseEvent.class);*/
				    	JuvenileProgramReferral progRef = JuvenileProgramReferral.find(programRef.getProgramReferralId());
					if(progRef!=null && progRef.getJuvenileId().equals(serviceEvtAttnRespEvt.getJuvenileId())){
						if(progRef!=null && progRef.getReferralStatusCd()!=null && progRef.getReferralStatusCd().equals(ProgramReferralConstants.TENTATIVE) && progRef.getReferralSubStatusCd()!=null && progRef.getReferralSubStatusCd().equals(ProgramReferralConstants.REFERRED) ){
							detailsForm.setTentativeRefPrgmRef(true);
							tentativeReferredPrgmReferrals.add(serviceEvtAttnRespEvt);
							break;
						}
						break;
					}
				}
			}
			detailsForm.setTentativeReferredPrgmReferrals(tentativeReferredPrgmReferrals);
			//added for #36737
		}
		Collections.sort(existingAttendanceEvents, ServiceEventAttendanceResponseEvent.JuvNameComparator);
		Collections.sort(newAttendanceEvents, ServiceEventAttendanceResponseEvent.JuvNameComparator);
		detailsForm.setExistingAttendanceEvents( existingAttendanceEvents ) ;
		detailsForm.setNewAttendanceEvents( newAttendanceEvents ) ;

		if( newAttendanceEvents.size( ) > 0 )
		{
			detailsForm.setAction( "attendanceUpdate" ) ;
		}
		else
		{
			detailsForm.setAction( "attendancePresent" ) ;
		}
		detailsForm.setSecondaryAction( "workshopCalendar" ) ;

		return aMapping.findForward( UIConstants.VIEW_DOCUMENT_ATTENDANCE ) ;
	}

	private void getServiceEventDetails(ServiceEventDetailsForm detailsForm) {
		
		GetServiceEventDetailsEvent event =
			(GetServiceEventDetailsEvent) EventFactory.getInstance(
					ServiceEventControllerServiceNames.GETSERVICEEVENTDETAILS);
		
		event.setServiceEventId(detailsForm.getEventId());
		
		CompositeResponse cr = MessageUtil.postRequest(event);
		
		CalendarServiceEventResponseEvent re = (CalendarServiceEventResponseEvent) MessageUtil.filterComposite(cr, CalendarServiceEventResponseEvent.class);
		
		detailsForm.setServiceEventId( re.getServiceEventId() ) ;
		detailsForm.setServiceProviderName( re.getServiceProviderName( ) ) ;
		detailsForm.setServiceName( re.getServiceName( ) ) ;
		detailsForm.setEventDate( re.getEventDate( ) ) ;
		detailsForm.setServiceLocationName( re.getServiceLocationName( ) ) ;
		detailsForm.setLocationId( re.getLocationId( ) ) ;
		detailsForm.setEventSessionLength( re.getEventSessionLength( ) ) ;
		detailsForm.setEventType( re.getEventType( ) ) ;
		detailsForm.setEventTypeCode(  re.getEventTypeCode( ) ) ;
		detailsForm.setEventStatus( re.getEventStatus( ) ) ;
		detailsForm.setEventStatusCd( re.getEventStatusCode( ) ) ;
		detailsForm.setEventComments( re.getEventComments( ) ) ;
		detailsForm.setAssociatedContexts( re.getAssociatedContexts( ) ) ;
		detailsForm.setAdminUserProfileId( re.getAdminUserProfileId( ) ) ;
		detailsForm.setEventMaximum( re.getMaxAttendance( ) ) ;
		detailsForm.setEventMinimum( re.getMinAttendance( ) ) ;
		detailsForm.setTotalScheduled( re.getCurrentEnrollment( ) ) ;
		detailsForm.setInstructorName( re.getInstructorName( ) ) ;
		
		event = null;
		cr = null;
		re = null;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancelEvent( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original

		GetServiceEventCancellationListEvent getCancellationListEvent = (GetServiceEventCancellationListEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETSERVICEEVENTCANCELLATIONLIST ) ;

		getCancellationListEvent.setServiceEventId( detailsForm.getEventId( ) ) ;

		dispatch.postEvent( getCancellationListEvent ) ;

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		MessageUtil.processReturnException( dataMap ) ;
		Collection cancellationList = 
				MessageUtil.compositeToCollection( compositeResponse, ServiceEventCancellationResponseEvent.class ) ;

		detailsForm.setCancellationList( cancellationList ) ;
		detailsForm.setAction( "cancellationSummary" ) ;

		return aMapping.findForward( UIConstants.CANCEL_EVENT ) ;
	}

	private int convertStringToInt(String str){
		try
	    {
	      // the String to int conversion happens here
	      int i = Integer.parseInt(str.trim());
	      return i;
	    }
	    catch (NumberFormatException nfe)
	    {
	      System.out.println("NumberFormatException: " + nfe.getMessage());
	      return 0;
	    }
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map buttonMap = new HashMap( ) ;
		buttonMap.put( "button.details", "details" ) ;
		buttonMap.put( "button.back", "back" ) ;
		buttonMap.put( "button.returnToCalendar", "returnToCalendar" ) ;
		buttonMap.put( "button.cancelEvent", "cancelEvent" ) ;
		buttonMap.put( "button.documentAttendance", "documentAttendance" ) ;
		buttonMap.put( "button.viewDetails", "documentAttendance" ) ;

		return buttonMap ;
	}
}