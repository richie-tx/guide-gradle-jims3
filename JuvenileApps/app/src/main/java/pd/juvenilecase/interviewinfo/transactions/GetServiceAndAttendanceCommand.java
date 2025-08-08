package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import messaging.interviewinfo.GetServiceAndAttendanceEvent;
import messaging.interviewinfo.reply.InterviewServiceAndAttendanceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;

public class GetServiceAndAttendanceCommand implements ICommand {
	
	/**
	 * @roseuid 448EC56A0388
	 */
	public GetServiceAndAttendanceCommand( )
	{

	}

	/**
	 * @param event
	 * @roseuid 448D7EEE029A
	 */
	public void execute( IEvent event )
	{
		GetServiceAndAttendanceEvent calEvent = (GetServiceAndAttendanceEvent) event;
//		CalendarEvent calendarEvent = CalendarEvent.find( calEvent.getCalEventId( ) ) ;
		InterviewServiceAndAttendanceResponseEvent serviceAndAttendanceResponse = new InterviewServiceAndAttendanceResponseEvent();

		Iterator calEventIter = ServiceEvent.findAll( "calendarEventId", calEvent.getCalEventId( )) ;
		
		while( calEventIter.hasNext( ) )
		{
			ServiceEvent serv = (ServiceEvent)calEventIter.next( ) ;
			serviceAndAttendanceResponse.setEventComments(serv.getEventComments());	
			serviceAndAttendanceResponse.setServiceEventId(serv.getOIDId());
			Iterator attenIter = ServiceEventAttendance.findAll( "serviceEventId", serv.getOID()) ;			
			while ( attenIter.hasNext( ) )
			{
				ServiceEventAttendance eventAttendance = (ServiceEventAttendance)attenIter.next( ) ;				
				serviceAndAttendanceResponse.setProgressNotes(eventAttendance.getProgressNotes());
				serviceAndAttendanceResponse.setAttendanceCode(eventAttendance.getAttendanceStatusCd());				
			}
			break ;
		}// while  calEventIter.hasNext
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY ) ;
		dispatch.postEvent( serviceAndAttendanceResponse ) ;
	}

	/**
	 * @param event
	 * @roseuid 448D7EEE029C
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 448D7EEE02A2
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 448D7EEE02A4
	 */
	public void update( Object anObject )
	{
	}
}
