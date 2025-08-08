package pd.supervision.calendar.transactions;

import java.util.ArrayList ;
import java.util.Calendar ;
import java.util.Date ;
import java.util.Iterator ;
import java.util.List ;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetScheduleCalendarEventConflictsEvent ;
import messaging.calendar.reply.CalendarServiceEventResponseEvent ;
import pd.common.calendar.CalendarManager ;
import pd.common.calendar.CalendarRetriever ;
import mojo.km.context.ICommand ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.IEvent ;
import naming.PDCalendarConstants ;
import naming.UIConstants ;
import pd.supervision.calendar.CalendarEventBuilder ;
import pd.supervision.calendar.ServiceEventContext ;

public class GetScheduleCalendarEventConflictsCommand implements ICommand
{
	/**
	 * @roseuid 44805C58019A
	 */
	public GetScheduleCalendarEventConflictsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49B4034B
	 */
	public void execute( IEvent event )
	{
		GetScheduleCalendarEventConflictsEvent calendarServicesEventConflictsEvent = (GetScheduleCalendarEventConflictsEvent)event ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY ) ;
		boolean jpoConflicts = false ;
		CalendarRetriever retriever = getCalendarRetriever( calendarServicesEventConflictsEvent.getRetriever() ) ;

		// Calendar Contexts
		
		CalendarContextEvent calContext = calendarServicesEventConflictsEvent.getCalendarContextEvent() ;
		
		if( calContext != null ) {
			
			retriever.setCalendarContext( calContext ) ;
			if( calContext.getProbationOfficerId() != null && calContext.getProbationOfficerId().length() > 0 )
			{
				jpoConflicts = true;
			}
		}

		List eventCollection = new ArrayList() ;
		List eventList = new ArrayList() ;
		List<CalendarServiceEventResponseEvent> ckEventList = calendarServicesEventConflictsEvent.getCheckEvents() ;
		for( CalendarServiceEventResponseEvent checkEvent : ckEventList )
		{
			{ Date tempDate = checkEvent.getStartDatetime() ;
				Calendar cal = Calendar.getInstance() ;
				cal.setTime( tempDate ) ;
				cal.set( Calendar.HOUR_OF_DAY, 0 ) ;
				cal.set( Calendar.MINUTE, 0 ) ;
				tempDate = cal.getTime() ;
				retriever.setStartDatetime( tempDate ) ;
	
				tempDate = checkEvent.getEndDatetime() ;
				cal.setTime( tempDate ) ;
				cal.set( Calendar.HOUR_OF_DAY, 23 ) ;
				cal.set( Calendar.MINUTE, 59 ) ;
				tempDate = cal.getTime() ;
				retriever.setEndDatetime( tempDate ) ;
			}

			CalendarManager cm = new CalendarManager() ;
			Object ret = cm.getCalendarEvents( retriever ) ;
			ServiceEventContext aContext = null ;
			
			for( Iterator i = (Iterator)ret; i.hasNext(); /*empty*/ )
			{
				aContext = (ServiceEventContext)i.next() ;
				if( !aContext.getEventStatusId().equals( PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED )  
						&&  (!jpoConflicts ||
						!aContext.getEventType().getGroup().equals(UIConstants.PRESCHEDULED_SERVICE_TYPE)) )
				{
					checkEvent.setAddConflictingEvent( false ) ;
					checkEvent.setConflictingEvent( true ) ;
					if( !eventList.contains( aContext.getServiceEventId() ) )
					{
						eventList.add( aContext.getServiceEventId() ) ;
						eventCollection.add( aContext ) ;
					}
				}
			} // inner for
		} // foreach

		if( eventCollection.size() > 0 )
		{
			CalendarEventBuilder builder = new CalendarEventBuilder() ;
			builder.setCalDetailLevel( "N/A" ) ;

			builder.setServiceEventContexts( eventCollection ) ;
			builder.build() ;
			List responseEvents = (List)builder.getResult() ;

			
			for( int len = responseEvents.size(), i = 0; i < len; i++ )
			{
				CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)responseEvents.get( i ) ;
				/**
				if( calContexts != null && calContexts.size() > 0 )
				{
					CasefileCalendarContext cfContext = new CasefileCalendarContext() ;
					JuvenileCalendarContext juvContext = new JuvenileCalendarContext() ;
					List contexts = resp.getAssociatedContexts() ;

					for( int contextLen = contexts.size(), j = 0; j < contextLen; j++ )
					{
						Object obj = contexts.get( j ) ;
						if( obj instanceof CalendarServiceEventResponseEvent )
						{
							cfContext.setCasefileId( ((CalendarServiceEventResponseEvent)obj).getCasefileId() ) ;
							if( !calContexts.contains( cfContext ) )
							{
								((CalendarServiceEventResponseEvent)obj).setCalendarAssociatedCasefile( false ) ;
							}
						}
						else if( obj instanceof JuvenileCasefileResponseEvent )
						{
							juvContext.setjuvenileNum( ((JuvenileCasefileResponseEvent)obj).getJuvenileNum() ) ;
						}
					}
				}
				**/
				
				dispatch.postEvent( resp ) ;
			} // for
		}
	}

	/**
	 * @param event
	 * @roseuid 447F49B4034D
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49B4035A
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 447F49B4035C
	 */
	public void update( Object anObject )
	{
	}

	/**
	 * Instantiate the Calendar Retriever. The class must implement the
	 * CalendarRetriever abstract class.
	 * 
	 * @param retriever
	 * @return ICalendarRetriever
	 */
	private CalendarRetriever getCalendarRetriever( String retriever )
	{
		try
		{
			Class c = Class.forName( retriever ) ;
			CalendarRetriever retrieverClass = (CalendarRetriever)c.newInstance() ;

			return retrieverClass ;
		}
		catch( ClassNotFoundException cnfe )
		{
			throw new RuntimeException( "[GetCalendarEventsCommand] Retrieve Class Not Found: " + retriever ) ;
		}
		catch( InstantiationException ie )
		{
			throw new RuntimeException( "[GetCalendarEventsCommand] Instantiation Exception: " + retriever ) ;
		}
		catch( IllegalAccessException iae )
		{
			throw new RuntimeException( "[GetCalendarEventsCommand] Illegal Access Exception: " + retriever ) ;
		}
		catch( Exception e )
		{
			throw new RuntimeException( "[GetCalendarEventsCommand] Exception creating the Calendar Retriever: " + retriever ) ;
		}
	}
}