/*
 * Created on Sep 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetDocketEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import naming.ServiceEventControllerServiceNames;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;

public class CalendarHelper 
{
	// Future date set to 2099 to put system way into the future.
	public static final int CALENDAR_FUTURE_YEAR = 2099; 

	/**
	 * REturns CalendarServiceEventResponseEvent (s)
	 * 
	 * @param aJuvenileNumber
	 * @param aStartingDate
	 * @param aEndingDate
	 * @return
	 */
	public static List getFutureJuvCalendarEvents(
			String aJuvenileNumber, Date aStartingDate, Date aEndingDate) 
	{
		List myList = null ;

		if( aJuvenileNumber != null  &&  aJuvenileNumber.length() > 0 ) 
		{
			if( aStartingDate == null ) 
			{
				aStartingDate = getTodaysDate();
			}
			if( aEndingDate == null ) 
			{
				aEndingDate = getFutureDate();
			}

			GetCalendarServiceEventsEvent myCalEvent = (GetCalendarServiceEventsEvent)
					EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);

			myCalEvent.setStartDatetime(aStartingDate);
			myCalEvent.setEndDatetime(aEndingDate);
			myCalEvent.setResponseType(PDConstants.CAL_DETAIL_MEDIUM);

			//Collection contexts = new ArrayList();
			//JuvenileCalendarContext juvenileContext = new JuvenileCalendarContext();
			//juvenileContext.setjuvenileNum(aJuvenileNumber);
			//contexts.add(juvenileContext);
			
			//myCalEvent.setCalendarContexts(contexts);
			
			CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
			calendarContextEvent.setJuvenileId(aJuvenileNumber);
			
			myCalEvent.setCalendarContextEvent(calendarContextEvent);
			
			myCalEvent.setRetriever(
					CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));

			myList = MessageUtil.postRequestListFilter(myCalEvent, CalendarServiceEventResponseEvent.class);
			if( myList.size() > 1 ) 
			{
				Collections.sort( myList );
			}
		}

		return myList;
	}

	/**
	 * Returns DocketEventResponseEvent (s)
	 * 
	 * @param aJuvenileNumber
	 * @param aStartingDate
	 * @param aEndingDate
	 * @return
	 */
	public static List getFutureJuvenileDocketEvents(
			String aJuvenileNumber, Date aStartingDate, Date aEndingDate) 
	{
		List myList = new ArrayList();
		
		if( aJuvenileNumber != null  &&  aJuvenileNumber.length() > 0 ) 
		{
			if( aStartingDate == null ) 
			{
				aStartingDate = getTodaysDate();
			}
			if( aEndingDate == null ) 
			{
				aEndingDate = getFutureDate();
			}

			GetDocketEventsEvent getDocketEventsEvent = (GetDocketEventsEvent)
					EventFactory.getInstance(ServiceEventControllerServiceNames.GETDOCKETEVENTS);
			getDocketEventsEvent.setJuvenileId(aJuvenileNumber);
			getDocketEventsEvent.setStartDate(aStartingDate);
			getDocketEventsEvent.setEndDate(aEndingDate);

			myList = MessageUtil.postRequestListFilter(getDocketEventsEvent, DocketEventResponseEvent.class);
			if( myList.size() > 1 ) 
			{
				Collections.sort( myList );
			}
		}

		return myList;
	}

	public static Date getTodaysDate() 
	{
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * Future date is in 2099 that way system is way into the future.
	 * 
	 * @return
	 */
	public static Date getFutureDate() 
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, CalendarHelper.CALENDAR_FUTURE_YEAR);

		return cal.getTime();
	}

	/**
	 * Future date is in 2099 that way system is way into the future.
	 * 
	 * @return
	 */
	public static Date getMonthsIntoFutureDate(int aNumOfMonths) 
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, aNumOfMonths);

		return cal.getTime();
	}

	/**
	 * Future date is in 2099 that way system is way into the future.
	 * 
	 * @return
	 */
	public static Date getDaysIntoFutureDate(int aNumOfDays) 
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, aNumOfDays);

		return cal.getTime();
	}
}
