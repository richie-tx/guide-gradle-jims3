package pd.juvenilecase.interviewinfo.transactions;

import java.util.ArrayList ;
import java.util.Date ;
import java.util.Iterator ;

import messaging.interviewinfo.SaveInterviewEvent ;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent ;
import pd.common.calendar.CalendarEvent ;
import mojo.km.context.ICommand ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.IEvent ;
import naming.PDCalendarConstants ;
import pd.juvenilecase.interviewinfo.Interview ;
import pd.supervision.calendar.ServiceEvent ;
import pd.supervision.calendar.ServiceEventAttendance ;

public class SaveInterviewCommand implements ICommand
{

	/**
	 * @roseuid 448EC56A0388
	 */
	public SaveInterviewCommand( )
	{

	}

	/**
	 * @param event
	 * @roseuid 448D7EEE029A
	 */
	public void execute( IEvent event )
	{
		SaveInterviewEvent request = (SaveInterviewEvent)event ;
		Interview interview = Interview.find( request.getInterviewId( ) ) ;
		interview.updateDetail( request ) ;

		//Update CalendarEvent Start & End time
		CalendarEvent calEvent = CalendarEvent.find( interview.getCalEventId( ) ) ;
		long sessionLength = calEvent.getEndDatetime( ).getTime( ) - calEvent.getStartDatetime( ).getTime( ) ;
		long endTime = interview.getInterviewDateTime( ).getTime( ) + sessionLength ;

		calEvent.setStartDatetime( interview.getInterviewDateTime( ) ) ;
		calEvent.setEndDatetime( new Date( endTime ) ) ;
		
		new mojo.km.persistence.Home( ).bind( calEvent ) ;

		Iterator calEventIter = ServiceEvent.findAll( "calendarEventId", calEvent.getCalendarEventId( ).toString( ) ) ;
		while( calEventIter.hasNext( ) )
		{
			ServiceEvent serv = (ServiceEvent)calEventIter.next( ) ;

			/* set Event Status to be COMPLETED since updating the interview
				will mean that the event has happened in the past, and thus
				the event has been completed.
			*/
			serv.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED ) ;

			if( request.getJuvLocUnitId( ) != null && !"".equals( request.getJuvLocUnitId( ) ) )
			{
				serv.setJuvLocUnitId( request.getJuvLocUnitId( ) ) ;
			}
			
			if(request.getUserComments() != null && !"".equals(request.getUserComments())){
				serv.setEventComments(request.getUserComments());
			}

			/* Set attendance status for that service event from CONFIRMED TO 
			   ATTENDED since updating the interview will mean that 
				the interview has happened in the past and the juvenile is assumed
				to have attended the interview. If the interview status is not CONFIRMED,
				then leave it as it is.
			*/
			Iterator attenIter = ServiceEventAttendance.findAll( "serviceEventId", serv.getOID()) ;
			ArrayList allAttItems = new ArrayList( ) ;
			while( attenIter.hasNext( ) )
			{
				ServiceEventAttendance eventAttendance = (ServiceEventAttendance)attenIter.next( ) ;
				allAttItems.add( eventAttendance ) ;

				if( eventAttendance.getJuvenileId( ).equals( request.getJuvenileNum( ) ) && 
						eventAttendance.getAttendanceStatusCd( ).equalsIgnoreCase( PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED ) )
				{
					eventAttendance.setAttendanceStatusCd( PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED ) ;
				}
				
				if(request.getProgressReport() != null && !"".equals(request.getProgressReport())){
					eventAttendance.setProgressNotes(request.getProgressReport());
				}
				
			}

			break ;
		}// while  calEventIter.hasNext

		InterviewDetailResponseEvent response = new InterviewDetailResponseEvent( ) ;
		interview.fillDetail( response ) ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY ) ;
		dispatch.postEvent( response ) ;
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
