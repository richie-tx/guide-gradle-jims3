//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\CreateCalendarServiceEventCommand.java

package pd.supervision.calendar.transactions;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import messaging.calendar.GetRecurringServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import pd.common.calendar.CalendarManager;
import pd.common.calendar.DailyRecurrence;
import pd.common.calendar.ICalendarRecurrenceType;
import pd.common.calendar.MonthlyRecurrence;
import pd.common.calendar.WeeklyRecurrence;
import pd.common.calendar.YearlyRecurrence;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import messaging.calendar.CalendarEventRequestEvent;
import messaging.calendar.reply.CalendarEventResponse;
import naming.PDCalendarConstants;

public class GetRecurringServiceEventsCommand implements ICommand
{

	/**
	 * @roseuid 44805C5701F8
	 */
	public GetRecurringServiceEventsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 447F49B500FA
	 */
	public void execute(IEvent event)
	{
		GetRecurringServiceEventsEvent getRecurringServiceEventsEvent = 
			  (GetRecurringServiceEventsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		String recurrencePattern = getRecurringServiceEventsEvent.getRecurrencePattern();
		
		CalendarEventRequestEvent care = new CalendarEventRequestEvent();
		
		//care.setCalendarEventType("RACTEST1");

		care.setEndDatetime(getRecurringServiceEventsEvent.getEndDatetime());
		care.setLocation(getRecurringServiceEventsEvent.getLocation());
		care.setStartDatetime(getRecurringServiceEventsEvent.getStartDatetime());
		care.setStatus("A");

		
		ICalendarRecurrenceType recurr=null;
		if (recurrencePattern.equals(PDCalendarConstants.DAILY_RECURRENCE)){
			recurr = setupDailyRecurrence(getRecurringServiceEventsEvent);		
		}else if (recurrencePattern.equals(PDCalendarConstants.WEEKLY_RECURRENCE)){
			recurr = setupWeeklyRecurrence(getRecurringServiceEventsEvent);
		}else if (recurrencePattern.equals(PDCalendarConstants.MONTHLY_RECURRENCE)){
			recurr = setupMonthlyRecurrence(getRecurringServiceEventsEvent);
		}else if (recurrencePattern.equals(PDCalendarConstants.YEARLY_RECURRENCE)){
			recurr = setupYearlyRecurrence(getRecurringServiceEventsEvent);
		}

		if (getRecurringServiceEventsEvent.isNumberedRecurrenceRange()){			
			recurr.setTotalOccurences(getRecurringServiceEventsEvent.getTotalOccurrences());			
		}else{			
			recurr.setRecurrenceEndDate(getRecurringServiceEventsEvent.getRecurrenceEndDate());			
		}
				
		CalendarManager manager = new CalendarManager();
		Collection col = manager.getCalendarResponseEvents(care,recurr,Calendar.getInstance().getTimeZone());
		
		
		Iterator iter = col.iterator();
		while(iter.hasNext()){
			CalendarEventResponse cal = (CalendarEventResponse) iter.next();
			CalendarServiceEventResponseEvent calEvent = new CalendarServiceEventResponseEvent();
			calEvent.setStartDatetime(cal.getStartDatetime());
			calEvent.setEndDatetime(cal.getEndDatetime());
			dispatch.postEvent(calEvent);
		}	

	}


	/**
	 * @param event
	 * @roseuid 447F49B50109
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 447F49B5010B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 447F49B5010D
	 */
	public void update(Object anObject)
	{

	}
	
	public ICalendarRecurrenceType setupDailyRecurrence(GetRecurringServiceEventsEvent getRecurringServiceEventsEvent){

		DailyRecurrence recurr = new DailyRecurrence();
		
		if (!getRecurringServiceEventsEvent.isDailyEveryWeekDay()){								
			recurr.setFrequencyPattern(getRecurringServiceEventsEvent.getFrequencyPattern());
		}else{
			recurr.setEveryWeekday(true);
			recurr.setFrequencyPattern(new Integer(1));
		}
			
		return recurr;
	}
	
	public ICalendarRecurrenceType setupWeeklyRecurrence(GetRecurringServiceEventsEvent getRecurringServiceEventsEvent){
 
		WeeklyRecurrence recurr = new WeeklyRecurrence();
		
		recurr.setFrequencyPattern(getRecurringServiceEventsEvent.getFrequencyPattern());
											
		String weeklyDay[] = getRecurringServiceEventsEvent.getWeeklyDay();
		
		for (int i = 0; i<weeklyDay.length ; i++){
			String day = weeklyDay[i];
			int j = Integer.parseInt(day);
			switch (j){
				case 0: recurr.setScheduleSunday(true);
						break;
				case 1: recurr.setScheduleMonday(true);
						break;
				case 2: recurr.setScheduleTuesday(true);
						break;
				case 3: recurr.setScheduleWednesday(true);
						break;
				case 4: recurr.setScheduleThursday(true);
						break;
				case 5: recurr.setScheduleFriday(true);
						break;						
				case 6: recurr.setScheduleSaturday(true);
						break;		
			}
		}
		
		return recurr;
	}
	
	public ICalendarRecurrenceType setupMonthlyRecurrence(GetRecurringServiceEventsEvent getRecurringServiceEventsEvent){
	
		MonthlyRecurrence recurr = new MonthlyRecurrence();

		recurr.setFrequencyPattern(getRecurringServiceEventsEvent.getFrequencyPattern());
		
		if (getRecurringServiceEventsEvent.isMonthlyRecurrenceType()){									
			recurr.setDayOfMonth(getRecurringServiceEventsEvent.getMonthlyDay());
		}else{
			
			int weekNumber =  getRecurringServiceEventsEvent.getMonthlyWeekNumber().intValue();
			
			switch (weekNumber){
				case 1: recurr.setFirst(true);
						break;
				case 2:recurr.setSecond(true);
						break;
			    case 3:recurr.setThird(true);
			    		break;			   	
			    case 4: recurr.setFourth(true);
			    		break;
			    case 5:recurr.setLast(true);
			    		break;
			}

			int weekDay =  getRecurringServiceEventsEvent.getMonthlyWeekDay().intValue();
			
			switch (weekDay){
			case 0: recurr.setScheduleSunday(true);
					break;
			case 1: recurr.setScheduleMonday(true);
					break;
			case 2: recurr.setScheduleTuesday(true);
					break;
			case 3: recurr.setScheduleWednesday(true);
					break;
			case 4: recurr.setScheduleThursday(true);
					break;
			case 5: recurr.setScheduleFriday(true);
					break;						
			case 6: recurr.setScheduleSaturday(true);
					break;	
			}									
		}
		return recurr;
	}
	
	
	public ICalendarRecurrenceType setupYearlyRecurrence(GetRecurringServiceEventsEvent getRecurringServiceEventsEvent){

		
		YearlyRecurrence recurr = new YearlyRecurrence();

		if (getRecurringServiceEventsEvent.isYearlyRecurrenceType()){			
			recurr.setMonth(getRecurringServiceEventsEvent.getYearlyMonthNumber().intValue());						
			recurr.setDayOfMonth(getRecurringServiceEventsEvent.getYearlyDay());
						
		}else{
			
			int yearlyWeekNumber =  getRecurringServiceEventsEvent.getYearlyWeekNumber().intValue();
			
			switch (yearlyWeekNumber){
				case 1: recurr.setFirst(true);
						break;
				case 2:recurr.setSecond(true);
						break;
			    case 3:recurr.setThird(true);
			    		break;			   	
			    case 4: recurr.setFourth(true);
			    		break;
			    case 5:recurr.setLast(true);
			    		break;
			}

			int yearlyWeekDay =  getRecurringServiceEventsEvent.getYearlyWeekDay().intValue();
			
			switch (yearlyWeekDay){
			case 0: recurr.setScheduleSunday(true);
					break;
			case 1: recurr.setScheduleMonday(true);
					break;
			case 2: recurr.setScheduleTuesday(true);
					break;
			case 3: recurr.setScheduleWednesday(true);
					break;
			case 4: recurr.setScheduleThursday(true);
					break;
			case 5: recurr.setScheduleFriday(true);
					break;						
			case 6: recurr.setScheduleSaturday(true);
					break;	
			}									
			
			int month = getRecurringServiceEventsEvent.getYearlyMonthNumber().intValue();
			
			switch (month){
			case 0: recurr.setScheduleJanuary(true);
					break;
			case 1: recurr.setScheduleFebruary(true);
					break;
			case 2: recurr.setScheduleMarch(true);
					break;
			case 3: recurr.setScheduleApril(true);
					break;
			case 4: recurr.setScheduleMay(true);
					break;
			case 5: recurr.setScheduleJune(true);
					break;
			case 6: recurr.setScheduleJuly(true);
					break;
			case 7: recurr.setScheduleAugust(true);
					break;
			case 8: recurr.setScheduleSeptember(true);
					break;
			case 9: recurr.setScheduleOctober(true);
					break;
			case 10: recurr.setScheduleNovember(true);
					break;
			case 11: recurr.setScheduleDecember(true);
					break;														
			}						
		}		
		return recurr;
	}
}
