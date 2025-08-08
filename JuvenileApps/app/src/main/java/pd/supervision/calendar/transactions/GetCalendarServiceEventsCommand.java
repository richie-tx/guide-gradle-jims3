package pd.supervision.calendar.transactions;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.FastArrayList;

import naming.PDCalendarConstants;
import naming.PDConstants;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import pd.common.calendar.CalendarManager;
import pd.common.calendar.CalendarRetriever;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.IUserInfo;
import mojo.km.security.helper.SecurityUtil;
import mojo.km.utilities.CollectionUtil;
import pd.supervision.calendar.CalendarEventBuilder;
import pd.supervision.calendar.ServiceEventContext;

public class GetCalendarServiceEventsCommand implements ICommand
{
	/**
	 * @roseuid 44805C590081
	 */
	public GetCalendarServiceEventsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49A50093
	 */
	public void execute(IEvent event)
	{
		GetCalendarServiceEventsEvent calendarRequest = (GetCalendarServiceEventsEvent) event;
		CalendarRetriever retriever = null ;
		
		
		{ String retrieverStr = calendarRequest.getRetriever() ;
			if( retrieverStr != null )
			{
				retriever = getCalendarRetriever(retrieverStr);
			}
		}

		// Calendar Start and End Date
		retriever.setStartDatetime(calendarRequest.getStartDatetime());
		retriever.setEndDatetime(calendarRequest.getEndDatetime());
		
		//If service events are only true, let the retriever know.
        if (calendarRequest.isSpEventsOnly()) {
        	retriever.setSpEventsOnly(true);
        }

		// Calendar Attributes
		ICalendarAttribute[] as = new ICalendarAttribute[calendarRequest.getCalendarAttributes().size()];
		calendarRequest.getCalendarAttributes().toArray(as);
		retriever.setCalendarAttributes(as);

		// Calendar Contexts
		CalendarContextEvent cx = calendarRequest.getCalendarContextEvent();
		retriever.setCalendarContext(cx);

		CalendarManager cm = new CalendarManager();
		Iterator ret = (Iterator) cm.getCalendarEvents(retriever);

		// refactor CalendarManager to return List instead of Iterator
		List events = CollectionUtil.iteratorToList(ret);

		List<CalendarServiceEventResponseEvent> responseEvents = null;
		String responseType = calendarRequest.getResponseType();

		if( responseType != null && responseType.equals(PDConstants.CAL_DETAIL_SHORT))
		{
			responseEvents = this.getEventBasics(events, true);
		}
		else
		{
			CalendarEventBuilder builder = new CalendarEventBuilder();
			builder.setCalDetailLevel("N/A");

			// do not filter out invalid contexts if current user is a service provider
			{ IUserInfo userInfo = SecurityUtil.getCurrentUser();
				boolean svcProvider = (userInfo instanceof ServiceProviderContactResponseEvent) ;
				boolean filter = (calendarRequest.isFilterInvalidContexts() && !svcProvider) ;
			  builder.setFilterInvalidContexts(filter);
			}
			
			builder.setServiceEventContexts(events);
			//<KISHORE>JIMS200060153 : MJCW - Schedule Calendar Event is Timing out on SP Events
			builder.setRequestType(calendarRequest.getRequestType());
			
			if (builder.getRequestType() != null 
					&& builder.getRequestType().equals(PDCalendarConstants.CALENDAR_EVENTS_FOR_INACTIVATE_LOCATION)) {
				builder.buildLite();
			} else {
				builder.build();
			}
			responseEvents = (List)builder.getResult();
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if( responseEvents != null  &&  responseEvents.size() > 0 )
		{
			for( CalendarServiceEventResponseEvent resp : responseEvents )
			{
				
				dispatch.postEvent(resp);
				
			}
		}
	}

	/*
	 * @param serviceEventContexts
	 * @param filter
	 * @return
	 */
	private List getEventBasics(List<ServiceEventContext> serviceEventContexts, boolean filter)
	{
		List responseList = new FastArrayList();
		ServiceEventContext e = null ;
		CalendarServiceEventResponseEvent resp = null ;

		for(int len = serviceEventContexts.size(), i = 0; i < len; i++)
		{
			if( ((e = (ServiceEventContext)serviceEventContexts.get(i)) != null) &&
					((resp = e.getBasicCalendarServiceResponseEvent()) != null) )
			{
				//if (filter) {
					//if (!e.getJuvenileId().equalsIgnoreCase(PDCalendarConstants.INVALID_CONTEXT_ID)) {
						//responseList.add(resp);
					//} 
				//} else {
					responseList.add(resp);
				//}
			}
		}
		
		return responseList;
	}

	/**
	 * Instantiate the Calendar Retriever. The class must implement the CalendarRetriever abstract
	 * class.
	 * 
	 * @param retriever
	 * @return ICalendarRetriever
	 */
	private CalendarRetriever getCalendarRetriever(String retriever)
	{
		CalendarRetriever retrieverClass = null ;
		try
		{
			Class c = Class.forName(retriever);
			retrieverClass = (CalendarRetriever)c.newInstance();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Exception creating the Calendar Retriever: " + retriever);
		}
		
		return retrieverClass;
	}

	/**
	 * @param event
	 * @roseuid 447F49A500A0
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49A500A2
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 447F49A500AF
	 */
	public void update(Object anObject)
	{
	}
}