package pd.supervision.calendar.transactions;

import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.List ;
import naming.OfficerProfileControllerServiceNames;
import naming.PDConstants;
import messaging.administerserviceprovider.GetServicesByServiceProviderNameEvent ;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent ;
import messaging.calendar.GetViewCalendarEventsEvent ;
import messaging.calendar.ServiceAttribute ;
import messaging.calendar.ServiceEventStatusAttribute ;
import messaging.calendar.ServiceEventTypeAttribute ;
import messaging.calendar.ServiceLocationAttribute ;
import messaging.calendar.reply.CalendarServiceEventResponseEvent ;
import messaging.error.reply.ErrorResponseEvent ;
import messaging.officer.GetOfficerProfilesByManagerEvent;
import pd.common.calendar.CalendarManager ;
import pd.common.calendar.CalendarRetriever ;
import messaging.calendar.ICalendarAttribute ;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import mojo.km.context.ICommand ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent ;
import mojo.km.utilities.CollectionUtil ;
import mojo.km.utilities.MessageUtil ;
import pd.codetable.criminal.JuvenileEventTypeCode ;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider ;
import pd.supervision.calendar.CalendarEventBuilder ;

/*
 */
public class GetViewCalendarEventsCommand implements ICommand
{
	/*
	 * @param searchEvent
	 * @return
	 */
	private GetCalendarServiceEventsEvent createSearchEvent( GetViewCalendarEventsEvent searchEvent )
	{
		List attributes = new ArrayList() ;

		if( notNullNotEmptyString( searchEvent.getServiceProviderName() ) )
		{
			GetServicesByServiceProviderNameEvent spEvent = new GetServicesByServiceProviderNameEvent() ;
			spEvent.setServiceProviderName( searchEvent.getServiceProviderName() ) ;
			Iterator iter = JuvenileServiceProvider.findAll( spEvent ) ;
			while( iter.hasNext() )
			{
				JuvenileServiceProvider juv = (JuvenileServiceProvider)iter.next() ;
				ServiceAttribute sa = new ServiceAttribute() ;
				sa.setServiceId( new Integer( juv.getServiceId() ) ) ;
				attributes.add( sa ) ;
			}
		}

		if( notNullNotEmptyString( searchEvent.getJuvUnitCd() ) )
		{
			ServiceLocationAttribute sla = new ServiceLocationAttribute() ;
			sla.setServiceLocationId( Integer.parseInt( searchEvent.getJuvUnitCd() ) ) ;
			attributes.add( sla ) ;
		}

		if( notNullNotEmptyString( searchEvent.getEventTypeId() ) )
		{
			JuvenileEventTypeCode eventTypeCode = JuvenileEventTypeCode.find( searchEvent.getEventTypeId() ) ;
			ServiceEventTypeAttribute sta = new ServiceEventTypeAttribute() ;
			sta.setServiceEventTypeId( eventTypeCode.getCode() ) ;
			attributes.add( sta ) ;
		}

		if( notNullNotEmptyString( searchEvent.getEventStatusCd() ) )
		{
			ServiceEventStatusAttribute ses = new ServiceEventStatusAttribute() ;
			ses.setServiceEventStatusCd( searchEvent.getEventStatusCd() ) ;
			attributes.add( ses ) ;
		}

		CalendarContextEvent calendarContextEvent = this.getCaseFileContexts( searchEvent ) ;
		GetCalendarServiceEventsEvent gce = new GetCalendarServiceEventsEvent() ;

		gce.setCalendarAttributes( attributes ) ;
		gce.setCalendarContextEvent( calendarContextEvent ) ;
		gce.setStartDatetime( searchEvent.getEventStartDate() ) ;
		gce.setEndDatetime( searchEvent.getEventEndDate() ) ;

		return gce ;
	}

	/**
	 * @param event
	 * @roseuid 45F080FA035F
	 */
	public void execute( IEvent event )
	{
		GetViewCalendarEventsEvent searchEvent = (GetViewCalendarEventsEvent)event ;
		GetCalendarServiceEventsEvent gce = this.createSearchEvent( searchEvent ) ;
		
		String casefileId = gce.getCalendarContextEvent().getCaseFileId();
		String probationOfficerId = gce.getCalendarContextEvent().getProbationOfficerId();
		String juvenileId = gce.getCalendarContextEvent().getJuvenileId();
		
		if( (gce.getCalendarAttributes().size() > 0) ||
				(notNullNotEmptyString(casefileId)) ||
				(notNullNotEmptyString(probationOfficerId)) ||
				(notNullNotEmptyString(juvenileId)) ||
				searchEvent.getEventStartDate() != null || 
				searchEvent.getEventEndDate() != null)
		{
			this.findEventsAndSendResponses( gce ) ;
		}
	}

	/*
	 * @param calendarServicesEventsEvent
	 */
	private void findEventsAndSendResponses( GetCalendarServiceEventsEvent calendarServicesEventsEvent )
	{
		CalendarRetriever retriever = getCalendarRetriever( calendarServicesEventsEvent.getRetriever() ) ;

		// Calendar Start and End Date
		retriever.setStartDatetime( calendarServicesEventsEvent.getStartDatetime() ) ;
		retriever.setEndDatetime( calendarServicesEventsEvent.getEndDatetime() ) ;

		// Calendar Attributes
		{ ICalendarAttribute [ ] as = new ICalendarAttribute [ calendarServicesEventsEvent.getCalendarAttributes().size() ] ;
			calendarServicesEventsEvent.getCalendarAttributes().toArray( as ) ;
			retriever.setCalendarAttributes( as ) ;

			// Calendar Context
			CalendarContextEvent calendarContextEvent = calendarServicesEventsEvent.getCalendarContextEvent();
			retriever.setCalendarContext( calendarContextEvent ) ;
		}

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY ) ;

		CalendarManager cm = new CalendarManager() ;
		Iterator ret = (Iterator)cm.getCalendarEvents( retriever ) ;
		List calendarEvents = CollectionUtil.iteratorToList( ret ) ;

		ErrorResponseEvent countEvent = null ;
		if( calendarEvents.size() > PDConstants.SEARCH_LIMIT )
		{
			countEvent = new ErrorResponseEvent() ;
			String message = this.createEventCountMaxError( calendarEvents.size() ) ;
			countEvent.setMessage( message ) ;
			MessageUtil.postReply( countEvent ) ;
		}

		if( countEvent == null )
		{
			CalendarEventBuilder builder = new CalendarEventBuilder() ;
			builder.setCalDetailLevel( "N/A" ) ;

			builder.setServiceEventContexts( calendarEvents ) ;
			builder.build() ;
			List responseEvents = (List)builder.getResult() ;

			int len = responseEvents.size() ;
			for( int i = 0; i < len; i++ )
			{
				CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)responseEvents.get( i ) ;
				dispatch.postEvent( resp ) ;
			}
		}
	}

	/**
	 * @param i
	 * @return
	 */
	private String createEventCountMaxError( int count )
	{
		StringBuffer buffer = new StringBuffer() ;

		buffer.append( "Calendar event search returned " )
				.append( String.valueOf(count))
				.append( " records which exceeds the maxiumum " )
				.append( PDConstants.SEARCH_LIMIT ) ;

		return buffer.toString() ;
	}

	/**
	 * Instantiate the Calendar Retriever. The class must implement the CalendarRetriever abstract class.
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
		catch( Exception e )
		{
			String msg = "[GetCalendarEventsCommand] Exception creating the Calendar Retriever: " + retriever ;
			throw new RuntimeException( msg, e ) ;
		}
	}

	/*
	 * @param searchEvent
	 * @return
	 */
	private CalendarContextEvent getCaseFileContexts( GetViewCalendarEventsEvent searchEvent )
	{
		//List contexts = new FastArrayList() ;
		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();

		if( notNullNotEmptyString( searchEvent.getJuvenileNum() ) )
		{
			calendarContextEvent.setJuvenileId(searchEvent.getJuvenileNum());
		}
		else if( notNullNotEmptyString( searchEvent.getOfficerId() ) )
		{
			calendarContextEvent.setProbationOfficerId(searchEvent.getOfficerId());
		
			if( searchEvent.isCaseLoadManager() == true )
			{
				GetOfficerProfilesByManagerEvent offEvent = (GetOfficerProfilesByManagerEvent) 
						EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYMANAGER);
				offEvent.setManagerId(searchEvent.getOfficerId());

				List officerResponses = MessageUtil.postRequestListFilter(offEvent,
						OfficerProfileResponseEvent.class);
				
				if (officerResponses != null) 
				{
					//Iterator officerIterator = officerResponses.iterator();
					String[] officerIds = new String[officerResponses.size()];
					
					int index = 0;
					for ( Iterator<OfficerProfileResponseEvent> iter = officerResponses.iterator(); 
							iter.hasNext(); index++ )
					{
						OfficerProfileResponseEvent offResp = iter.next();
						officerIds[index] = offResp.getOfficerProfileId();
					}
					calendarContextEvent.setCLMProbationOfficerIds(officerIds);
				}
			}
		}

		return calendarContextEvent ;
	}

	/**
	 * @param event
	 * @roseuid 45F080FA036D
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 45F080FA036F
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 45F080FA037C
	 */
	public void update( Object anObject )
	{
	}

	/* returns true if incoming Sring is not null and is not empty
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null &&  str.length() > 0 ) ;
	}
}
