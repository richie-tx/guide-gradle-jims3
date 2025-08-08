package ui.juvenilecase.viewcalendar.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerserviceprovider.GetProgramByProgramIdEvent;
import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.CancelEventsListEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.NotificationMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;

/**
 * @author cc_vnarsingoju
 *
 */
public class HandleFutureCalendarEventsCancelAction extends JIMSBaseAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward selectedCancelEvents( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		
		Collection<CalendarServiceEventResponseEvent> eventList = form.getIndividualDayEvents() ;
		Collection selectedEvents = new ArrayList() ;
		
		String [ ] eventIds = form.getSelectedCancelEvents() ;
		if( eventIds != null  &&  eventList != null )
		{
			for( CalendarServiceEventResponseEvent anEvent : eventList )
			{
				for( String anID : eventIds )
				{
					if( anEvent.getEventId().trim().equals( anID ) )
					{
						selectedEvents.add( anEvent ) ;
					}
				}
			} // outer foreach
			form.setSelectedCancelEventsList( selectedEvents ) ;
		}

		form.setAction( "summary" ) ;
		
		return aMapping.findForward( UIConstants.NEXT ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancelEvents( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		Collection<CalendarServiceEventResponseEvent> cancelEventsList = 
				form.getSelectedCancelEventsList() ;
		
		if( cancelEventsList != null )
		{
			String searchType = form.getSearchEvent().getSearchType().trim() ;
			
			for( CalendarServiceEventResponseEvent evt : cancelEventsList )
			{
				ProviderProgramResponseEvent programDetails = this.getProgramDetails( evt.getProgramId() ) ;
				// JIMS200056919 : Null pointer when canceling future events for juvenile from View Calendar
				// Only SP events will have program details
				if(programDetails != null)
					programDetails.setServiceType(evt.getEventType());
	
				String adminContactId = getSPAdminContactId( new Integer( evt.getServiceProviderId() ).toString() ) ;
				IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
				CancelEventsListEvent cancelListEvent = (CancelEventsListEvent)
						EventFactory.getInstance( ServiceEventControllerServiceNames.CANCELEVENTSLIST ) ;
				cancelListEvent.setServiceEventId( evt.getEventId() ) ;
	
				if( searchType.equalsIgnoreCase( PDCalendarConstants.FUTURE_EVENTS_JUVENILE ) )
				{
					cancelListEvent.setSearchType( PDCalendarConstants.FUTURE_EVENTS_JUVENILE ) ;
					cancelListEvent.setJuvenileId( form.getSearchEvent().getJuvenileNum() ) ;
					cancelListEvent.setEventType(evt.getEventType());
					cancelListEvent.setEventTypeCategory(evt.getEventTypeCategory());
					cancelListEvent.setEventTypeCode(evt.getEventTypeCode());
				}
				else if( searchType.equalsIgnoreCase( PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER ) )
				{
					cancelListEvent.setSearchType( PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER ) ;
					cancelListEvent.setJuvenileId( "" ) ;
					cancelListEvent.setEventType(evt.getEventType());
					cancelListEvent.setEventTypeCategory(evt.getEventTypeCategory());
					cancelListEvent.setEventTypeCode(evt.getEventTypeCode());
				}
				
				dispatch.postEvent( cancelListEvent ) ;
				CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
				Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
				MessageUtil.processReturnException( dataMap ) ;
				
				Collection<ServiceEventCancellationResponseEvent> cancellationList = 
					MessageUtil.compositeToCollection( compositeResponse, ServiceEventCancellationResponseEvent.class ) ;
				if( notNullNotEmptyCollection( cancellationList ) )
				{
					// JIMS200056919 : Null pointer when canceling future events for juvenile from View Calendar
					// Notifications should only be sent to SP events cancellation
					// Non SP events will not have program details
					if(programDetails != null){
						for( ServiceEventCancellationResponseEvent noticeDetails : cancellationList )
						{
							createJPONotice( noticeDetails.getOfficerLogonId(), 
									noticeDetails.getJuvenileName(), programDetails, evt ) ;
						}
						createSPNotice( adminContactId, programDetails, evt ) ;
					}
				}
			} // foreach event selected for cancellation
		}
		
		//notifyServiceInactivation(spf, evt, "SERVICE INACTIVATION");
		form.setAction( "confirmation" ) ;
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	private boolean filterForJuv( String juvNum )
	{
		return( true ) ;
	}
	/*
	 * @param spId
	 * @param programDetails
	 */
	private void createSPNotice( String spId, 
			ProviderProgramResponseEvent programDetails,
			CalendarServiceEventResponseEvent event )
	{
		if( notNullNotEmptyString( spId ) )
		{
			IUserInfo user = SecurityUIHelper.getUser() ;
			Name userName = new Name( user.getFirstName(), "", user.getLastName() ) ;
			
			NotificationMessage nevt = new NotificationMessage() ;
			nevt.setSubject( "Session Cancelled" ) ;
			nevt.setIdentity( spId ) ;
			
			Date eventDate = event.getEventDate() ;
			String dateStr = DateUtil.dateToString( eventDate, UIConstants.DATE_FMT_1 ) ;
			String timeStr = DateUtil.dateToString( eventDate, UIConstants.TIME_FMT_1AMPM ) ;
						
			String message = "Please be advised that the " + programDetails.getServiceType() 
					+ " for " + programDetails.getProgramName() 
					+ " which has a session scheduled for " + dateStr + " at " + timeStr 
					+ " has been cancelled by [" + userName.getFormattedName() +"]" ;
			nevt.setNotificationMessage( message ) ;
			sendNotification( nevt, "MJCW.SP.SERVICEEVENT.CANCELLATION.NOTIFICATION" ) ;
		}
	}

	/*
	 * @param jpoId
	 * @param juvenileName
	 * @param programDetails
	 */
	private void createJPONotice( String jpoId, String juvenileName, 
			ProviderProgramResponseEvent programDetails,
			CalendarServiceEventResponseEvent event )
	{
		if( notNullNotEmptyString( jpoId ) )
		{
			NotificationMessage nevt = new NotificationMessage() ;
			nevt.setSubject( "Session Cancelled" ) ;
			nevt.setIdentity( jpoId ) ;
			
			Date eventDate = event.getEventDate() ;
			String dateStr = DateUtil.dateToString( eventDate, UIConstants.DATE_FMT_1 ) ;
			String timeStr = DateUtil.dateToString( eventDate, UIConstants.TIME_FMT_1AMPM ) ;
						
			String message = "Please be advised that the " + programDetails.getServiceType() + 
					" for "  + programDetails.getProgramName() 
					+ " which has a session scheduled for " + dateStr + " at " + timeStr 
					+ " has been cancelled. " + juvenileName + " was scheduled to attend this event." ;
			nevt.setNotificationMessage( message ) ;
			sendNotification( nevt, "MJCW.JPO.SERVICEEVENT.CANCELLATION.NOTIFICATION" ) ;
		}
	}

	/*
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  str.length() > 0 ) ;
	}

	/* given a Collection, return true if it's not null and not empty
	 * @param col
	 * @return boolean
	 */
	private boolean notNullNotEmptyCollection( Collection col )
	{
		return( col != null  &&  !col.isEmpty() ) ;
	}

	/*
	 * @param serviceProviderId
	 * @return
	 */
	public String getSPAdminContactId( String serviceProviderId )
	{
		GetServiceProviderEvent spEvent = (GetServiceProviderEvent)
				EventFactory.getInstance( ServiceProviderControllerServiceNames.GETSERVICEPROVIDER ) ;
		spEvent.setServiceProviderId( serviceProviderId ) ;

		CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest( spEvent ) ;

		JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent)
				MessageUtil.filterComposite( compositeResponse, JuvenileServiceProviderResponseEvent.class ) ;

		String adminContactId = null ;
		if( resp != null )
		{
			adminContactId = resp.getAdminUserProfileId() ;
		}
		
		return adminContactId ;
	}

	/*
	 * @param nevt
	 * @param topic
	 */
	public static void sendNotification( NotificationMessage nevt, String topic )
	{
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
				EventFactory.getInstance( NotificationControllerSerivceNames.CREATENOTIFICATION ) ;
		notificationEvent.setNotificationTopic( topic ) ;
		notificationEvent.setSubject( nevt.getSubject() ) ;
		notificationEvent.addIdentity( "respEvent", nevt ) ;
		notificationEvent.addContentBean( nevt ) ;
		EventManager.getSharedInstance( EventManager.REQUEST ).postEvent( notificationEvent ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward searchCalendar( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		HttpSession session = aRequest.getSession() ;
		ScheduleNewEventForm oldForm = (ScheduleNewEventForm)session.getAttribute( "scheduleNewEventForm" ) ;
		if( oldForm == null )
		{
			return null ;
		}

		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		form.setCalendarType( oldForm.getCalendarType() ) ;
		form.getSearchEvent().clear() ;
		form.getSearchEvent().setSearchType( "" ) ;

		form.getSearchEvent().setJuvUnitList( ComplexCodeTableHelper.getActiveJuvenileLocationUnits() ) ;
		form.getSearchEvent().setServiceTypePermList( ComplexCodeTableHelper.getAllServiceEventTypes() ) ;

		Calendar cal = Calendar.getInstance() ;
		cal.setTime( new Date() ) ;
		form.getSearchEvent().setStartDateStr( DateUtil.dateToString( cal.getTime(), UIConstants.DATE_FMT_1 ) ) ;
		cal.set( Calendar.MONTH, cal.get( Calendar.MONTH ) + 1 ) ;
		form.getSearchEvent().setEndDateStr( DateUtil.dateToString( cal.getTime(), UIConstants.DATE_FMT_1 ) ) ;

		if( form.getCalendarType().equals( PDCalendarConstants.CALENDAR_TYPE_PROVIDER ) )
		{
			GetServiceProviderEvent getServiceProviderEvent = (GetServiceProviderEvent)
					EventFactory.getInstance( ServiceProviderControllerServiceNames.GETSERVICEPROVIDER ) ;
			getServiceProviderEvent.setServiceProviderId( oldForm.getServiceProviderId() ) ;

			CompositeResponse response = MessageUtil.postRequest( getServiceProviderEvent ) ;
			JuvenileServiceProviderResponseEvent juvResp = (JuvenileServiceProviderResponseEvent)
					MessageUtil.filterComposite( response, JuvenileServiceProviderResponseEvent.class ) ;
			if( juvResp != null )
			{
				form.setServiceProviderName( juvResp.getServiceProviderName() ) ;
				form.getSearchEvent().setServiceProviderName( juvResp.getServiceProviderName() ) ;				
				form.getSearchEvent().setServiceProviderId( juvResp.getServiceProviderId() ) ;
			}
		}
		else
		{
			form.getSearchEvent().setServiceProviderList( ComplexCodeTableHelper.getServiceProvidersWithPrograms() ) ;
		}

		return aMapping.findForward( UIConstants.SEARCH_SUCCESS ) ;
	}

	/*
	 * @param providerProgramId
	 * @return
	 */
	private ProviderProgramResponseEvent getProgramDetails( String providerProgramId )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		GetProgramByProgramIdEvent reqEvent = (GetProgramByProgramIdEvent)
				EventFactory.getInstance( ServiceProviderControllerServiceNames.GETPROGRAMBYPROGRAMID ) ;

		reqEvent.setProviderProgramId( providerProgramId ) ;

		dispatch.postEvent( reqEvent ) ;
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		ProviderProgramResponseEvent resp = (ProviderProgramResponseEvent)
				MessageUtil.filterComposite( compositeResponse, ProviderProgramResponseEvent.class ) ;

		return resp ;

	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	@Override
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.submit", "selectedCancelEvents" ) ;
		keyMap.put( "button.returnToCalendarSearch", "searchCalendar" ) ;
		keyMap.put( "button.finish", "cancelEvents" ) ;
	}

}
