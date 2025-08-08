/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import messaging.calendar.reply.CalendarEventResponse;
import pd.common.calendar.CalendarEventContext;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.CalendarEventRequestEvent;
import messaging.calendar.GetCalendarEventContext;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author glyons
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarManager {
	private CalendarManager calendarManager = null;

	public CalendarManager() {
	}

	/**
	 * Retrieves the CalendarEvents, or classes that extend Calendar Event,
	 * based off the CalendarRetrieve that is passed in. The CalendarRetriever
	 * is a custom class specific to the problem domain on what it needs to
	 * return
	 * 
	 * @param retriever
	 * @return
	 */
	public Object getCalendarEvents(CalendarRetriever retriever) {
		return retriever.retrieve();
	}

	/**
	 * Saves a calendar event only. Will save multiples based off the recurrence
	 * type and return all the events that were created based off the
	 * recurrence.
	 * 
	 * @param event
	 * @param recurrence
	 * @param currentTimeZone
	 * @return
	 */

	/**
	 * @param event
	 * @return
	 */
	private CalendarEventSeries createCalendarEventSeries(
			CalendarEventRequestEvent event) {
		CalendarEventSeries calendar = new CalendarEventSeries();

		calendar.setStartDatetime(event.getStartDatetime());
		calendar.setEndDatetime(event.getEndDatetime());
		return calendar;
	}

	/**
	 * Increments Date
	 * 
	 * @param incrementType
	 * @param increment
	 * @param start
	 * @return
	 */
	private Date incrementDate(int incrementType, int increment, Date start) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(incrementType, increment);
		return cal.getTime();
	}

	/**
	 * Takes an existing calendar event and creates a clone of it.
	 * 
	 * @param calEvent
	 * @return CalendarEvent
	 */
	private CalendarEvent cloneCalendarEvent(CalendarEvent calEvent) {
		CalendarEvent newcal = new CalendarEvent();
		newcal.setBodyText(calEvent.getBodyText());
		newcal.setCalendarEventType(calEvent.getCalendarEventType());
		newcal.setCreatedBy(calEvent.getCreatedBy());
		newcal.setLocation(calEvent.getLocation());
		newcal.setStatus(calEvent.getStatus());
		newcal.setSubject(calEvent.getSubject());
		newcal.setCalendarEventSeriesId(calEvent.getCalendarEventSeriesId());

		return newcal;
	}

	/**
	 * Creates a calendarEvent from a CalendarRequestEvent
	 * 
	 * @param event
	 * @return calendarEvent
	 */
	private CalendarEvent createCalendarEvent(CalendarEventRequestEvent event) {
		CalendarEvent calendar = new CalendarEvent();
		calendar.setBodyText(event.getBodyText());
		calendar.setCalendarEventType(event.getCalendarEventType());
		calendar.setCreatedBy(event.getCreatedBy());
		calendar.setLocation(event.getLocation());
		calendar.setStatus(event.getStatus());
		calendar.setSubject(event.getSubject());
		calendar.setStartDatetime(event.getStartDatetime());
		calendar.setEndDatetime(event.getEndDatetime());
		return calendar;
	}

	/**
	 * Saves a CalendarEvent and its context. Will save a new CalendarEvent
	 * based off the recurrence Type and also associate the context with each
	 * recurrence.
	 * 
	 * @param event
	 * @param context
	 * @param recurrence
	 * @param currentTimeZone
	 */
	public Collection saveCalendarEvent(CalendarEventRequestEvent event,
			CalendarContextEvent context, ICalendarRecurrenceType recurrence,
			TimeZone currentTimeZone) {
		Collection events = this.saveCalendarEvent(event, recurrence,
				currentTimeZone);
		if (events.size() > 0) {
			Iterator e = events.iterator();
			while (e.hasNext()) {
				
				CalendarEvent ce = (CalendarEvent) e.next();
				CalendarEventContext calContext = new CalendarEventContext();
				calContext.setProbationOfficerId(context.getProbationOfficerId());
				calContext.setCaseFileId(context.getCaseFileId());
				calContext.setJuvenileId(context.getJuvenileId());
				calContext.setCalendarEventId(ce.getCalendarEventId());
				
				CalendarEventContext newcec = (CalendarEventContext) new mojo.km.persistence.Home()
						.bind(calContext);
			}
		}
		return events;
	}

	/**
	 * Saves a Calendar Event and its associated contexts.
	 * 
	 * @param event
	 * @param contexts
	 * @param recurrence
	 * @param currentTimeZone
	 */
	public Collection saveCalendarEvent(CalendarEventRequestEvent event,
			CalendarContextEvent[] contexts, ICalendarRecurrenceType recurrence,
			TimeZone currentTimeZone) {
		Collection events = this.saveCalendarEvent(event, recurrence,
				currentTimeZone);
		if (events.size() > 0) {
			if (contexts != null && contexts.length > 0) {
				Iterator e = events.iterator();
				while (e.hasNext()) {
					CalendarEvent ce = (CalendarEvent) e.next();
					for (int x = 0; x < contexts.length; x++) {
						this.addCalendarEventContext(ce.getCalendarEventId(),
								contexts[x]);
					}
				}
			}
		}
		return events;
	}

	/**
	 * Adds one CalendarEventContext to a Calendar Event
	 * 
	 * @param calendarEventId
	 * @param context
	 */
	public void addCalendarEventContext(Integer calendarEventId,
			CalendarContextEvent context) {
		CalendarEventContext calContext = new CalendarEventContext();
		calContext.setProbationOfficerId(context.getProbationOfficerId());
		calContext.setCaseFileId(context.getCaseFileId());
		calContext.setJuvenileId(context.getJuvenileId());
		calContext.setCalendarEventId(calendarEventId);
		calContext = (CalendarEventContext) new mojo.km.persistence.Home()
				.bind(calContext);
	}

	/**
	 * Adds a group CalendarEventContexts to a Calendar Event
	 * 
	 * @param calendarEventId
	 * @param contexts
	 */
	public void addCalendarEventContexts(Integer calendarEventId,
			CalendarContextEvent[] contexts) {
		if (calendarEventId != null && contexts != null) {
			for (int x = 0; x < contexts.length; x++) {
				addCalendarEventContext(calendarEventId, contexts[x]);
			}
		}
	}

	/**
	 * Updates a Calendar Event from a CalendarEventRequestEvent
	 * 
	 * @param event
	 */
	public void updateCalendarEvent(CalendarEventRequestEvent event) {
		CalendarEvent calendar = CalendarEvent.find(""
				+ event.getCalendarEventId());
		if (calendar != null) {
			calendar.setBodyText(event.getBodyText());
			calendar.setCalendarEventType(event.getCalendarEventType());
			calendar.setCreatedBy(event.getCreatedBy());
			calendar.setEndDatetime(event.getEndDatetime());
			calendar.setLocation(event.getLocation());
			calendar.setStartDatetime(event.getStartDatetime());
			calendar.setStatus(event.getStatus());
			calendar.setSubject(event.getSubject());
			calendar = (CalendarEvent) new mojo.km.persistence.Home()
					.bind(calendar);
		}
	}

	/**
	 * Updates the Calendar Event Status
	 * 
	 * @param calendarEventId
	 * @param newStatus
	 */
	public void updateCalendarEventStatus(String calendarEventId,
			String newStatus) {
		CalendarEvent calendar = CalendarEvent.find("" + calendarEventId);
		if (calendar != null) {
			calendar.setStatus(newStatus);
			calendar = (CalendarEvent) new mojo.km.persistence.Home()
					.bind(calendar);
		}
	}

	/**
	 * Deletes a CalendarEventContext for a particlar calendar event and the
	 * appropriate context
	 * 
	 * @param calendarEventId
	 * @param context
	 */
	public void deleteCalendarContext(Integer calendarEventId,
			CalendarContextEvent context) {
		GetCalendarEventContext getevent = new GetCalendarEventContext();
		getevent.setCalendarEventId(calendarEventId);
		getevent.setProbationOfficerId(context.getProbationOfficerId());
		getevent.setCaseFileId(context.getCaseFileId());
		getevent.setJuvenileId(context.getJuvenileId());
		CalendarEventContext eventcontext = CalendarEventContext.find(getevent);
		if (eventcontext != null) {
			eventcontext.delete();
		}
	}

	/**
	 * Deletes a group of calendar contexts for a calendarevent
	 * 
	 * @param calendarEventId
	 * @param contexts
	 */
	public void deleteCalendarContexts(Integer calendarEventId,
			CalendarContextEvent[] contexts) {
		for (int x = 0; x < contexts.length; x++) {
			deleteCalendarContext(calendarEventId, contexts[x]);
		}
	}

	/**
	 * Deletes all CalendarEventContexts for a particular calendarEvent
	 * 
	 * @param calendarEventId
	 */
	public void deleteCalendarContexts(Integer calendarEventId) {
		Iterator contexts = CalendarEventContext
				.findByCalendarEventId(calendarEventId);
		while (contexts.hasNext()) {
			CalendarEventContext context = (CalendarEventContext) contexts
					.next();
			context.delete();
			new mojo.km.persistence.Home().bind(context);
		}
	}

	/**
	 * Will delete all calendar contexts for this calendar event and the
	 * calendar event itself.
	 * 
	 * @param calendarEventId
	 */
	public void deleteCalendarEvent(Integer calendarEventId) {
		CalendarEvent event = CalendarEvent.find(calendarEventId + "");
		if (event != null) {
			this.deleteCalendarContexts(calendarEventId);
			event.delete();
			new mojo.km.persistence.Home().bind(event);
		}
	}

	/**
	 * Will delete all calendar contexts for this calendar event and the
	 * calendar event itself.
	 * 
	 * @param calendarEventId
	 */
	public void deleteCalendarEventSeries(Integer calendarEventSeriesId) {
		IHome home = new Home();
		Iterator events = home.findAll("calendarEventSeriesId",
				calendarEventSeriesId, CalendarEvent.class);
		for (Iterator iter = events; events.hasNext();) {
			CalendarEvent event = (CalendarEvent) iter.next();

			if (event != null) {
				Integer calendarEventId = event.getCalendarEventId();
				this.deleteCalendarEvent(calendarEventId);

			}
		}
		CalendarEventSeries eventSeries = CalendarEventSeries
				.find(calendarEventSeriesId + "");
		if (eventSeries != null) {
			eventSeries.delete();
			new mojo.km.persistence.Home().bind(eventSeries);
		}
	}

	public Collection saveCalendarEvent(CalendarEventRequestEvent event,
			ICalendarRecurrenceType recurrence, TimeZone currentTimeZone) {
		ArrayList persistedevents = new ArrayList();

		if (recurrence != null && event != null) {

			Integer totalOccurences = recurrence.getTotalOccurences();
			CalendarEventSeries calendarEventSeries = this
					.createCalendarEventSeries(event);
			calendarEventSeries = (CalendarEventSeries) new mojo.km.persistence.Home()
					.bind(calendarEventSeries);
			String cEventOID = (String) calendarEventSeries.getOID();

			CalendarEvent calendar = this.createCalendarEvent(event);

			calendar.setTimeZone(currentTimeZone.getID());
			calendar.setCalendarEventSeriesId(new Integer(cEventOID));

			if (recurrence instanceof OneRecurrence) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);
				return persistedevents;
			}
			if (recurrence instanceof DailyRecurrence) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(calendar.getStartDatetime());
				return saveRecurringDailyCalendarEvent(
						(DailyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			} else if (recurrence instanceof WeeklyRecurrence) {
				return saveRecurringWeeklyCalendarEvent(
						(WeeklyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			} else if (recurrence instanceof MonthlyRecurrence) {
				return saveRecurringMonthlyCalendarEvent(
						(MonthlyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			} else if (recurrence instanceof YearlyRecurrence) {
				return saveRecurringYearlyCalendarEvent(
						(YearlyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			}
		}
		return persistedevents;
	}

	public Collection saveRecurringDailyCalendarEvent(
			DailyRecurrence recurrence, CalendarEvent calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.DATE;
		int calendarIncrement = 0;
		Integer frequencyPattern;
		boolean everyWeekday;

		calendarIncrementType = Calendar.DATE;
		calendarIncrement = 1;
		everyWeekday = recurrence.isEveryWeekday();
		frequencyPattern = recurrence.getFrequencyPattern();
		if (frequencyPattern.intValue() > 1) {
			calendarIncrement = frequencyPattern.intValue();
		}
		if (everyWeekday) {
			persistedevents = (ArrayList) saveWeekDayCalendarEvent(recurrence,
					calendar, persistedevents);

			runningStartDate = incrementWeekdayDate(calendarIncrementType,
					calendarIncrement, calendar.getStartDatetime());
			runningEndDate = incrementWeekdayDate(calendarIncrementType,
					calendarIncrement, calendar.getEndDatetime());
		} else {

			runningStartDate = incrementDate(calendarIncrementType,
					calendarIncrement, calendar.getStartDatetime());
			runningEndDate = incrementDate(calendarIncrementType,
					calendarIncrement, calendar.getEndDatetime());

		}

		if (recurrence.getRecurrenceEndDate() != null) {

			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {

				CalendarEvent newcal = cloneCalendarEvent(calendar,
						runningStartDate, runningEndDate, saveTimeZone);
				persistedevents.add(newcal);

				if (everyWeekday) {
					runningStartDate = incrementWeekdayDate(
							calendarIncrementType, calendarIncrement,
							runningStartDate);
					runningEndDate = incrementWeekdayDate(
							calendarIncrementType, calendarIncrement,
							runningEndDate);
				} else {
					runningStartDate = incrementDate(calendarIncrementType,
							calendarIncrement, newcal.getStartDatetime());
					runningEndDate = incrementDate(calendarIncrementType,
							calendarIncrement, newcal.getEndDatetime());
				}
			}
		}
		int loopCt = persistedevents.size();

		while (totalOccurences.intValue() > loopCt) {

			CalendarEvent newcal = cloneCalendarEvent(calendar,
					runningStartDate, runningEndDate, saveTimeZone);
			persistedevents.add(newcal);
			loopCt = persistedevents.size();

			if (everyWeekday) {
				runningStartDate = incrementWeekdayDate(calendarIncrementType,
						calendarIncrement, runningStartDate);
				runningEndDate = incrementWeekdayDate(calendarIncrementType,
						calendarIncrement, runningEndDate);
			} else {
				runningStartDate = incrementDate(calendarIncrementType,
						calendarIncrement, newcal.getStartDatetime());
				runningEndDate = incrementDate(calendarIncrementType,
						calendarIncrement, newcal.getEndDatetime());
			}
		}

		return persistedevents;
	}

	private Date incrementWeekdayDate(int incrementType, int increment,
			Date start) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(incrementType, increment);
		Date weekday = cal.getTime();
		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
			return incrementWeekdayDate(incrementType, increment, weekday);
		}

		return cal.getTime();
	}

	public Collection saveRecurringWeeklyCalendarEvent(
			WeeklyRecurrence recurrence, CalendarEvent calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.DATE;
		int calendarIncrement = 0;
		Integer frequencyPattern;

		persistedevents = (ArrayList) saveWeeklyCalendarEvent(recurrence,
				calendar, persistedevents);
		Date caDate = calendar.getStartDatetime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(caDate);
		int calday = cal.get(Calendar.DAY_OF_WEEK);
		calendarIncrementType = Calendar.DATE;
		calendarIncrement = 7;
		int numberOfDays = 0;
		int loopCt = persistedevents.size();

		if (recurrence.isScheduleFriday()) {
			numberOfDays++;
		}
		if (recurrence.isScheduleMonday()) {
			numberOfDays++;
		}
		if (recurrence.isScheduleSaturday()) {
			numberOfDays++;
		}
		if (recurrence.isScheduleSunday()) {
			numberOfDays++;
		}
		if (recurrence.isScheduleThursday()) {
			numberOfDays++;
		}
		if (recurrence.isScheduleTuesday()) {
			numberOfDays++;
		}
		if (recurrence.isScheduleWednesday()) {
			numberOfDays++;
		}

		frequencyPattern = recurrence.getFrequencyPattern();
		calendarIncrement = (frequencyPattern.intValue() * calendarIncrement) - 6;

		if (calendar.getEndDatetime() != calendar.getStartDatetime()) {
			System.err.println("Calendar end date = "
					+ calendar.getStartDatetime() + "Recurrence end date = "
					+ recurrence.getRecurrenceEndDate());
		}
		runningStartDate = incrementWeeklyDate(calendarIncrementType,
				calendarIncrement, calendar.getStartDatetime(), recurrence);
		runningEndDate = incrementWeeklyDate(calendarIncrementType,
				calendarIncrement, calendar.getEndDatetime(), recurrence);
		
		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {
				
				System.err.println("Running date = " + runningStartDate
						+ "Recurrence end date = "
						+ recurrence.getRecurrenceEndDate());
				if (runningStartDate.before(recurrence.getRecurrenceEndDate())
						|| (runningStartDate.equals(recurrence
								.getRecurrenceEndDate()))) {
					CalendarEvent newcal = cloneCalendarEvent(calendar,
							runningStartDate, runningEndDate, saveTimeZone);

					persistedevents.add(newcal);
					loopCt = persistedevents.size();
					
				}
				
				runningStartDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);

			}
		}

		loopCt = persistedevents.size();
		if (totalOccurences != null) {
			while (totalOccurences.intValue() > loopCt) {
				
				CalendarEvent newcal = cloneCalendarEvent(calendar,
						runningStartDate, runningEndDate, saveTimeZone);

				persistedevents.add(newcal);
				loopCt = persistedevents.size();
				
				runningStartDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);

			}
		}

		return persistedevents;
	}

	private Date incrementWeeklyDate(int incrementType, int increment,
			Date start, WeeklyRecurrence recurrence) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);

		if (cal.get(Calendar.DAY_OF_WEEK) < 7) {
			cal.add(incrementType, 1);
		} else {
			cal.add(incrementType, increment);
		}

		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (recurrence.isScheduleFriday()) {
			if (day == Calendar.FRIDAY) {
				return cal.getTime();
			}
		}
		if (recurrence.isScheduleMonday()) {
			if (day == Calendar.MONDAY) {
				return cal.getTime();
			}
		}
		if (recurrence.isScheduleSaturday()) {
			if (day == Calendar.SATURDAY) {
				return cal.getTime();
			}
		}
		if (recurrence.isScheduleSunday()) {
			if (day == Calendar.SUNDAY) {
				return cal.getTime();
			}
		}
		if (recurrence.isScheduleThursday()) {
			if (day == Calendar.THURSDAY) {
				return cal.getTime();
			}
		}
		if (recurrence.isScheduleTuesday()) {
			if (day == Calendar.TUESDAY) {
				return cal.getTime();
			}
		}
		if (recurrence.isScheduleWednesday()) {
			if (day == Calendar.WEDNESDAY) {
				return cal.getTime();
			}
		}

		return incrementWeeklyDate(incrementType, increment, cal.getTime(),
				recurrence);
	}

	private CalendarEvent cloneCalendarEvent(CalendarEvent calEvent,
			Date runningStartDate, Date runningEndDate, String saveTimeZone) {
		CalendarEvent newcal = new CalendarEvent();
		newcal.setBodyText(calEvent.getBodyText());
		newcal.setCalendarEventType(calEvent.getCalendarEventType());
		newcal.setCreatedBy(calEvent.getCreatedBy());
		newcal.setLocation(calEvent.getLocation());
		newcal.setStatus(calEvent.getStatus());
		newcal.setSubject(calEvent.getSubject());
		newcal.setCalendarEventSeriesId(calEvent.getCalendarEventSeriesId());
		// Set new start time
		newcal.setStartDatetime(runningStartDate);
		// Set new end time
		newcal.setEndDatetime(runningEndDate);
		// Set time zone for persistence that is determined
		// by the CalendaringProperties
		newcal.setTimeZone(saveTimeZone);

		newcal = (CalendarEvent) new mojo.km.persistence.Home().bind(newcal);

		return newcal;
	}

	public Collection saveRecurringMonthlyCalendarEvent(
			MonthlyRecurrence recurrence, CalendarEvent calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.MONTH;
		Date caDate = calendar.getStartDatetime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(caDate);
	
		int calendarIncrement = 0;
	
		calendarIncrement = 1;
		if (recurrence.getFrequencyPattern().intValue() > 1) {
			calendarIncrement = recurrence.getFrequencyPattern().intValue();
		}
		boolean firstEventSaved = false;
		if (recurrence.getDayOfMonth() != null) {
			if (cal.get(Calendar.DAY_OF_MONTH) == recurrence.getDayOfMonth()
					.intValue()) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);
				firstEventSaved = true;
			}

		}
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int week = cal.get(Calendar.WEEK_OF_MONTH);

		int weekInMonth = 0;
		if (recurrence.isFirst()) {
			weekInMonth = 1;
			if ((recurrence.getDayOfWeek() == day) && (week == 2)) {
				weekInMonth = week;
			}
		}
		if (recurrence.isSecond()) {
			weekInMonth = 2;
		}
		if (recurrence.isThird()) {
			weekInMonth = 3;
		}
		if (recurrence.isFourth()) {
			weekInMonth = 4;
		}
		if (recurrence.isLast()) {
			weekInMonth = -1;
		}
		if (weekInMonth == cal.get(Calendar.WEEK_OF_MONTH)) {
			if (recurrence.isScheduleFriday()) {
				if (day == Calendar.FRIDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleMonday()) {
				if (day == Calendar.MONDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleSaturday()) {
				if (day == Calendar.SATURDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleSunday()) {
				if (day == Calendar.SUNDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleThursday()) {
				if (day == Calendar.THURSDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleTuesday()) {
				if (day == Calendar.TUESDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleWednesday()) {
				if (day == Calendar.WEDNESDAY) {
					calendar = (CalendarEvent) new mojo.km.persistence.Home()
							.bind(calendar);
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
		}
		if (!firstEventSaved) {
			runningStartDate = incrementMonthlyDate(Calendar.DATE, 1, calendar
					.getStartDatetime(), recurrence);
			runningEndDate = incrementMonthlyDate(Calendar.DATE, 1, calendar
					.getEndDatetime(), recurrence);
			calendar.setStartDatetime(runningStartDate);
			calendar.setEndDatetime(runningEndDate);
			calendar = (CalendarEvent) new mojo.km.persistence.Home()
					.bind(calendar);
			persistedevents.add(calendar);
			firstEventSaved = true;
		}
		runningStartDate = incrementMonthlyDate(calendarIncrementType,
				calendarIncrement, calendar.getStartDatetime(), recurrence);
		runningEndDate = incrementMonthlyDate(calendarIncrementType,
				calendarIncrement, calendar.getEndDatetime(), recurrence);

		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {
				CalendarEvent newcal = cloneCalendarEvent(calendar,
						runningStartDate, runningEndDate, saveTimeZone);

				persistedevents.add(newcal);

				runningStartDate = incrementMonthlyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementMonthlyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);

			}
		}

		int loopCt = persistedevents.size();
		
		while (totalOccurences.intValue() > loopCt) {

			CalendarEvent newcal = cloneCalendarEvent(calendar,
					runningStartDate, runningEndDate, saveTimeZone);

			persistedevents.add(newcal);
			loopCt = persistedevents.size();

			runningStartDate = incrementMonthlyDate(calendarIncrementType,
					calendarIncrement, runningStartDate, recurrence);
			runningEndDate = incrementMonthlyDate(calendarIncrementType,
					calendarIncrement, runningEndDate, recurrence);

		}

		return persistedevents;
	}

	private Date incrementMonthlyDate(int incrementType, int increment,
			Date start, MonthlyRecurrence recurrence) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(start);
		cal.add(incrementType, increment);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(start);
		
		cal2.set(Calendar.DAY_OF_MONTH,1);
		int month = cal.get(Calendar.MONTH);
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (incrementType == Calendar.MONTH) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (recurrence.getDayOfMonth() != null) {
			cal.set(Calendar.DATE, recurrence.getDayOfMonth().intValue());
			if (month == cal.get(Calendar.MONTH)) {

				return cal.getTime();
			} else {

				cal.set(Calendar.DATE, recurrence.getDayOfMonth().intValue());
				if (cal.get(Calendar.DAY_OF_MONTH) == recurrence
						.getDayOfMonth().intValue()) {
					return cal.getTime();
				}
			}
		}
		day = cal.get(Calendar.DAY_OF_WEEK);
		int week = cal.get(Calendar.WEEK_OF_MONTH);

		int weekInMonth = 0;
		if (recurrence.isFirst()) {

			weekInMonth = 1;
			if ((recurrence.getDayOfWeek() == day) && (week == 2)) {
				weekInMonth = week;
			}
		}
		if (recurrence.isSecond()) {
			weekInMonth = 2;
		}
		if (recurrence.isThird()) {
			weekInMonth = 3;
		}
		if (recurrence.isFourth()) {
			weekInMonth = 4;
		}
		if (recurrence.isLast()) {
			weekInMonth = 4;
			if (week == 5) {
				weekInMonth = 5;
			}
		}
		int minDay = ((weekInMonth-1)*7)-(cal2.get((Calendar.DAY_OF_WEEK))+1);
		boolean storeSw = false;
		if(cal.get(Calendar.DAY_OF_MONTH) < weekInMonth * 7){
			if (cal.get(Calendar.DAY_OF_MONTH) >minDay ){
			storeSw = true;
		}
	}
	//RAC changed to accommodate the first scheduled day being the first instance of day in the month
	//	if ((weekInMonth == cal.get(Calendar.WEEK_OF_MONTH))  || (storeSw == true)) 
		if (storeSw){
			if (recurrence.isScheduleFriday()) {
				if (day == Calendar.FRIDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}
					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}

					return cal.getTime();
				}
			}
			if (recurrence.isScheduleMonday()) {
				if (day == Calendar.MONDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}
					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}
					return cal.getTime();
				}
			}
			if (recurrence.isScheduleSaturday()) {
				if (day == Calendar.SATURDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}
					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}
					return cal.getTime();
				}
			}
			if (recurrence.isScheduleSunday()) {
				if (day == Calendar.SUNDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}
					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}
					return cal.getTime();
				}
			}
			if (recurrence.isScheduleThursday()) {
				if (day == Calendar.THURSDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}

					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}
					return cal.getTime();
				}
			}
			if (recurrence.isScheduleTuesday()) {
				if (day == Calendar.TUESDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}
					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}
					return cal.getTime();
				}
			}
			if (recurrence.isScheduleWednesday()) {
				if (day == Calendar.WEDNESDAY) {
					if ((cal.get(Calendar.DAY_OF_MONTH) < (weekInMonth * 7) - 6)
							&& (!recurrence.isFirst())) {
						cal.add(Calendar.DATE, 7);
					}
					if (recurrence.isLast()) {
						return checkLastWeek(cal);
					}
					return cal.getTime();
				}
			}
			return incrementMonthlyDate(Calendar.DATE, 1, cal.getTime(),
					recurrence);
		} else {
			return incrementMonthlyDate(Calendar.DATE, 1, cal.getTime(),
					recurrence);
		}
	}

	/**
	 * @param cal
	 * @param recurrence
	 * @return
	 */
	private Date checkFirstWeek(Calendar cal, MonthlyRecurrence recurrence) {
		int dayOfWeek = recurrence.getDayOfWeek();
		int calMonth = cal.get(Calendar.MONTH);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(cal.getTime());
		cal2.add(Calendar.DATE, 7);
		if (cal2.get(Calendar.MONTH) != calMonth) {
			cal2.set(Calendar.DAY_OF_WEEK, 1);
			System.err.println("Day of Week = "
					+ cal2.get(Calendar.DAY_OF_WEEK));
		}

		if (cal2.get(Calendar.MONTH) == calMonth) {
			cal.add(Calendar.DATE, 1);
		}
		return cal.getTime();
	}

	public Collection saveRecurringYearlyCalendarEvent(
			YearlyRecurrence recurrence, CalendarEvent calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.MONTH;

		int calendarIncrement = 0;
		
		calendarIncrementType = Calendar.MONTH;
		calendarIncrement = 1;

		runningStartDate = incrementYearlyDate(calendarIncrementType,
				calendarIncrement, calendar.getStartDatetime(), recurrence);
		runningEndDate = incrementYearlyDate(calendarIncrementType,
				calendarIncrement, calendar.getEndDatetime(), recurrence);
		
		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {

				CalendarEvent newcal = cloneCalendarEvent(calendar,
						runningStartDate, runningEndDate, saveTimeZone);

				persistedevents.add(newcal);

				runningStartDate = incrementYearlyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementYearlyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);

			}
		}

		int loopCt = persistedevents.size();
		;

		while (totalOccurences.intValue() > loopCt) {
			int moreDays = 1;

			CalendarEvent newcal = cloneCalendarEvent(calendar,
					runningStartDate, runningEndDate, saveTimeZone);

			persistedevents.add(newcal);
			loopCt = persistedevents.size();
			
			moreDays++;
			runningStartDate = incrementYearlyDate(calendarIncrementType,
					calendarIncrement, runningStartDate, recurrence);
			runningEndDate = incrementYearlyDate(calendarIncrementType,
					calendarIncrement, runningEndDate, recurrence);

		}

		return persistedevents;
	}

	private Date incrementYearlyDate(int incrementType, int increment,
			Date start, YearlyRecurrence recurrence) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(start);

		cal.add(incrementType, increment);
		cal = progressMonth(cal, recurrence);

		if ((recurrence.getDayOfMonth() != null)
				&& (recurrence.getDayOfMonth().intValue() > 0)) {
			cal.set(Calendar.DATE, recurrence.getDayOfMonth().intValue());
			return cal.getTime();
		} else {
			cal = progressDate(cal, recurrence);
		}
		return cal.getTime();
	}

	private Date checkLastWeek(Calendar cal) {

		int calMonth = cal.get(Calendar.MONTH);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(cal.getTime());
		cal2.add(Calendar.DATE, 7);
		if (cal2.get(Calendar.MONTH) != calMonth) {

			System.err.println("Day of Week = "
					+ cal2.get(Calendar.DAY_OF_WEEK));
		}

		if (cal2.get(Calendar.MONTH) == calMonth) {
			cal.add(Calendar.DATE, 7);
		}
		return cal.getTime();
	}

	private Calendar progressMonth(Calendar cal, YearlyRecurrence recurrence) {

		int month = recurrence.getMonth();

		if (cal.get(Calendar.MONTH) == month) {
			return cal;
		}

		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);

		return progressMonth(cal, recurrence);
	}

	private Calendar progressDate(Calendar cal, YearlyRecurrence recurrence) {

		int week = 0;
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		if (recurrence.isFirst()) {
			week = 1;
			if ((recurrence.getDayOfWeek() == day) && (week == 2)) {
				weekOfMonth = week;
			}
		}
		if (recurrence.isSecond()) {
			week = 2;
		}
		if (recurrence.isThird()) {
			week = 3;
		}
		if (recurrence.isFourth()) {
			week = 4;
		}
		if (recurrence.isLast()) {
			week = 4;
			if (weekOfMonth == 5) {
				week = 5;
			}
		}

		if (week == cal.get(Calendar.WEEK_OF_MONTH)) {
			if (recurrence.isScheduleFriday()) {

				if (day == Calendar.FRIDAY) {
					if (recurrence.isLast()) {

						cal.setTime(checkLastWeek(cal));
					}

					return cal;
				}
			}
			if (recurrence.isScheduleMonday()) {
				if (day == Calendar.MONDAY) {
					if (recurrence.isLast()) {
						cal.setTime(checkLastWeek(cal));
					}
					return cal;
				}
			}
			if (recurrence.isScheduleSaturday()) {
				if (day == Calendar.SATURDAY) {
					if (recurrence.isLast()) {
						cal.setTime(checkLastWeek(cal));
					}
					return cal;
				}
			}
			if (recurrence.isScheduleSunday()) {
				if (day == Calendar.SUNDAY) {
					if (recurrence.isLast()) {
						cal.setTime(checkLastWeek(cal));
					}
					return cal;
				}
			}
			if (recurrence.isScheduleThursday()) {
				if (day == Calendar.THURSDAY) {
					if (recurrence.isLast()) {
						cal.setTime(checkLastWeek(cal));
					}
					return cal;
				}
			}
			if (recurrence.isScheduleTuesday()) {
				if (day == Calendar.TUESDAY) {
					if (recurrence.isLast()) {
						cal.setTime(checkLastWeek(cal));
					}
					return cal;
				}
			}
			if (recurrence.isScheduleWednesday()) {
				if (day == Calendar.WEDNESDAY) {
					if (recurrence.isLast()) {
						cal.setTime(checkLastWeek(cal));
					}
					return cal;
				}
			}
		}
		cal.add(Calendar.DATE, 1);

		return progressDate(cal, recurrence);
	}

	// added to preview calendar events before persisting
	public Collection getCalendarResponseEvents(
			CalendarEventRequestEvent event,
			ICalendarRecurrenceType recurrence, TimeZone currentTimeZone) {
		ArrayList persistedevents = new ArrayList();
		if (recurrence != null && event != null) {
			Integer totalOccurences = recurrence.getTotalOccurences();

			CalendarEventResponse calendar = this
					.createCalendarResponseEvent(event);

			if (recurrence instanceof OneRecurrence) {
				persistedevents.add(calendar);
				return persistedevents;
			}
			if (recurrence instanceof DailyRecurrence) {

				return saveRecurringDailyCalendarEvent(
						(DailyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			} else if (recurrence instanceof WeeklyRecurrence) {
				return saveRecurringWeeklyCalendarEvent(
						(WeeklyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			} else if (recurrence instanceof MonthlyRecurrence) {
				return saveRecurringMonthlyCalendarEvent(
						(MonthlyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			} else if (recurrence instanceof YearlyRecurrence) {
				return saveRecurringYearlyCalendarEvent(
						(YearlyRecurrence) recurrence, calendar,
						persistedevents, currentTimeZone.getID(),
						totalOccurences);
			}
		}
		return persistedevents;
	}

	private CalendarEventResponse createCalendarResponseEvent(
			CalendarEventRequestEvent event) {
		CalendarEventResponse calendar = new CalendarEventResponse();
		calendar.setBodyText(event.getBodyText());
		calendar.setCalendarEventType(event.getCalendarEventType());
		calendar.setCreatedBy(event.getCreatedBy());
		calendar.setLocation(event.getLocation());
		calendar.setStatus(event.getStatus());
		calendar.setSubject(event.getSubject());
		calendar.setStartDatetime(event.getStartDatetime());
		calendar.setEndDatetime(event.getEndDatetime());
		return calendar;
	}

	public Collection saveRecurringDailyCalendarEvent(
			DailyRecurrence recurrence, CalendarEventResponse calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.DATE;
		int calendarIncrement = 0;
		Integer frequencyPattern;
		boolean everyWeekday;

		calendarIncrementType = Calendar.DATE;
		calendarIncrement = 1;
		everyWeekday = recurrence.isEveryWeekday();
		frequencyPattern = recurrence.getFrequencyPattern();
		if (frequencyPattern.intValue() > 1) {
			calendarIncrement = frequencyPattern.intValue();
		}
		if (everyWeekday) {
			persistedevents = (ArrayList) saveWeekDayCalendarResponseEvent(
					recurrence, calendar, persistedevents);
			runningStartDate = incrementWeekdayDate(calendarIncrementType,
					calendarIncrement, calendar.getStartDatetime());
			runningEndDate = incrementWeekdayDate(calendarIncrementType,
					calendarIncrement, calendar.getEndDatetime());
		} else {
			
		       // Do not increment at the begin. Start on start date.
//		       runningStartDate = incrementDate(calendarIncrementType,
//					calendarIncrement, calendar.getStartDatetime());
		    runningStartDate =  calendar.getStartDatetime();
		    runningEndDate = calendar.getEndDatetime();
//			runningEndDate = incrementDate(calendarIncrementType,
//					calendarIncrement, calendar.getEndDatetime());

		}

		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {

				CalendarEventResponse newcal = cloneCalendarResponseEvent(
						calendar, runningStartDate, runningEndDate,
						saveTimeZone);
				persistedevents.add(newcal);

				if (everyWeekday) {
					runningStartDate = incrementWeekdayDate(
							calendarIncrementType, calendarIncrement,
							runningStartDate);
					runningEndDate = incrementWeekdayDate(
							calendarIncrementType, calendarIncrement,
							runningEndDate);
				} else {
					runningStartDate = incrementDate(calendarIncrementType,
							calendarIncrement, newcal.getStartDatetime());
					runningEndDate = incrementDate(calendarIncrementType,
							calendarIncrement, newcal.getEndDatetime());
				}
			}
		}
		int loopCt = persistedevents.size();

		while (totalOccurences.intValue() > loopCt) {

			CalendarEventResponse newcal = cloneCalendarResponseEvent(calendar,
					runningStartDate, runningEndDate, saveTimeZone);
			persistedevents.add(newcal);
			loopCt = persistedevents.size();

			if (everyWeekday) {
				runningStartDate = incrementWeekdayDate(calendarIncrementType,
						calendarIncrement, runningStartDate);
				runningEndDate = incrementWeekdayDate(calendarIncrementType,
						calendarIncrement, runningEndDate);
			} else {
				runningStartDate = incrementDate(calendarIncrementType,
						calendarIncrement, newcal.getStartDatetime());
				runningEndDate = incrementDate(calendarIncrementType,
						calendarIncrement, newcal.getEndDatetime());
			}
		}

		return persistedevents;
	}

	private CalendarEventResponse cloneCalendarResponseEvent(
			CalendarEventResponse calEvent, Date runningStartDate,
			Date runningEndDate, String saveTimeZone) {
		CalendarEventResponse newcal = new CalendarEventResponse();
		newcal.setBodyText(calEvent.getBodyText());
		newcal.setCalendarEventType(calEvent.getCalendarEventType());
		newcal.setCreatedBy(calEvent.getCreatedBy());
		newcal.setLocation(calEvent.getLocation());
		newcal.setStatus(calEvent.getStatus());
		newcal.setSubject(calEvent.getSubject());

		// Set new start time
		newcal.setStartDatetime(runningStartDate);
		// Set new end time
		newcal.setEndDatetime(runningEndDate);

		// newcal = (CalendarEvent) new mojo.km.persistence.Home().bind(newcal);

		return newcal;
	}

	public Collection saveRecurringWeeklyCalendarEvent(
			WeeklyRecurrence recurrence, CalendarEventResponse calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.DATE;
		int calendarIncrement = 0;
		Integer frequencyPattern;
		Date caDate = calendar.getStartDatetime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(caDate);
		calendarIncrementType = Calendar.DATE;
		calendarIncrement = 7;
		int loopCt = persistedevents.size();
		frequencyPattern = recurrence.getFrequencyPattern();
		calendarIncrement = (frequencyPattern.intValue() * calendarIncrement) - 6;
		persistedevents = (ArrayList) saveWeeklyCalendarResponseEvent(
				recurrence, calendar, persistedevents);
		runningStartDate = incrementWeeklyDate(calendarIncrementType,
				calendarIncrement, calendar.getStartDatetime(), recurrence);
		runningEndDate = incrementWeeklyDate(calendarIncrementType,
				calendarIncrement, calendar.getEndDatetime(), recurrence);

		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {
				System.err.println("Running date = " + runningStartDate
						+ "Recurrence end date = "
						+ recurrence.getRecurrenceEndDate());
				if (runningStartDate.before(recurrence.getRecurrenceEndDate())
						|| (runningStartDate.equals(recurrence
								.getRecurrenceEndDate()))) {
					CalendarEventResponse newcal = cloneCalendarResponseEvent(
							calendar, runningStartDate, runningEndDate,
							saveTimeZone);

					persistedevents.add(newcal);
					loopCt = persistedevents.size();

				}
				runningStartDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);

			}
		}

		loopCt = persistedevents.size();

		if (totalOccurences != null) {

			while (totalOccurences.intValue() > loopCt) {
				CalendarEventResponse newcal = cloneCalendarResponseEvent(
						calendar, runningStartDate, runningEndDate,
						saveTimeZone);

				persistedevents.add(newcal);
				loopCt = persistedevents.size();
				runningStartDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementWeeklyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);
			}
		}

		return persistedevents;
	}

	public Collection saveRecurringMonthlyCalendarEvent(
			MonthlyRecurrence recurrence, CalendarEventResponse calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.MONTH;
		Date caDate = calendar.getStartDatetime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(caDate);
		int calendarIncrement = 0;
	
		calendarIncrement = 1;
		if (recurrence.getFrequencyPattern().intValue() > 1) {
			calendarIncrement = recurrence.getFrequencyPattern().intValue();
		}
		boolean firstEventSaved = false;
		if (recurrence.getDayOfMonth() != null) {
			if (cal.get(Calendar.DAY_OF_MONTH) == recurrence.getDayOfMonth()
					.intValue()) {

				persistedevents.add(calendar);
				firstEventSaved = true;
			}

		}
		int day = cal.get(Calendar.DAY_OF_WEEK);

		int weekInMonth = 0;
		if (recurrence.isFirst()) {
			weekInMonth = 1;
		}
		if (recurrence.isSecond()) {
			weekInMonth = 2;
		}
		if (recurrence.isThird()) {
			weekInMonth = 3;
		}
		if (recurrence.isFourth()) {
			weekInMonth = 4;
		}
		if (recurrence.isLast()) {
			weekInMonth = -1;
		}
		boolean storeSw = false;
		if(cal.get(Calendar.DAY_OF_MONTH) < weekInMonth * 7){
				if(cal.get(Calendar.DAY_OF_MONTH) > ((weekInMonth-1) * 7))
			{
			
			storeSw = true;
		}
			}
		//RAC changed to accommodate the first scheduled day being the first instance of day in the month
		
	//	if ((weekInMonth == cal.get(Calendar.WEEK_OF_MONTH))  || (storeSw == true)) 
		if (storeSw)
		{
			if (recurrence.isScheduleFriday()) {
				if (day == Calendar.FRIDAY) {

					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleMonday()) {
				if (day == Calendar.MONDAY) {
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleSaturday()) {
				if (day == Calendar.SATURDAY) {

					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleSunday()) {
				if (day == Calendar.SUNDAY) {

					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleThursday()) {
				if (day == Calendar.THURSDAY) {

					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleTuesday()) {
				if (day == Calendar.TUESDAY) {

					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
			if (recurrence.isScheduleWednesday()) {
				if (day == Calendar.WEDNESDAY) {
					persistedevents.add(calendar);
					firstEventSaved = true;
				}
			}
		}
		if (!firstEventSaved) {
			runningStartDate = incrementMonthlyDate(Calendar.DATE, 1, calendar
					.getStartDatetime(), recurrence);
			runningEndDate = incrementMonthlyDate(Calendar.DATE, 1, calendar
					.getEndDatetime(), recurrence);
			calendar.setStartDatetime(runningStartDate);
			calendar.setEndDatetime(runningEndDate);
			persistedevents.add(calendar);
			firstEventSaved = true;
		}
		runningStartDate = incrementMonthlyDate(calendarIncrementType,
				calendarIncrement, calendar.getStartDatetime(), recurrence);
		runningEndDate = incrementMonthlyDate(calendarIncrementType,
				calendarIncrement, calendar.getEndDatetime(), recurrence);

		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {
				CalendarEventResponse newcal = cloneCalendarResponseEvent(
						calendar, runningStartDate, runningEndDate,
						saveTimeZone);
				persistedevents.add(newcal);
				runningStartDate = incrementMonthlyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementMonthlyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);
			}
		}
		int loopCt = persistedevents.size();
		while (totalOccurences.intValue() > loopCt) {
			CalendarEventResponse newcal = cloneCalendarResponseEvent(calendar,
					runningStartDate, runningEndDate, saveTimeZone);
			persistedevents.add(newcal);
			loopCt = persistedevents.size();

			runningStartDate = incrementMonthlyDate(calendarIncrementType,
					calendarIncrement, runningStartDate, recurrence);
			runningEndDate = incrementMonthlyDate(calendarIncrementType,
					calendarIncrement, runningEndDate, recurrence);
		}
		return persistedevents;
	}

	public Collection saveRecurringYearlyCalendarEvent(
			YearlyRecurrence recurrence, CalendarEventResponse calendar,
			ArrayList persistedevents, String saveTimeZone,
			Integer totalOccurences) {
		Date runningStartDate;
		Date runningEndDate;
		int calendarIncrementType = Calendar.MONTH;
		int calendarIncrement = 0;

		calendarIncrementType = Calendar.MONTH;
		calendarIncrement = 1;
		runningStartDate = incrementYearlyDate(calendarIncrementType,
				calendarIncrement, calendar.getStartDatetime(), recurrence);
		runningEndDate = incrementYearlyDate(calendarIncrementType,
				calendarIncrement, calendar.getEndDatetime(), recurrence);

		if (recurrence.getRecurrenceEndDate() != null) {
			while (runningStartDate.before(recurrence.getRecurrenceEndDate())
					|| (runningStartDate.equals(recurrence
							.getRecurrenceEndDate()))) {
				CalendarEventResponse newcal = cloneCalendarResponseEvent(
						calendar, runningStartDate, runningEndDate,
						saveTimeZone);
				persistedevents.add(newcal);
				runningStartDate = incrementYearlyDate(calendarIncrementType,
						calendarIncrement, runningStartDate, recurrence);
				runningEndDate = incrementYearlyDate(calendarIncrementType,
						calendarIncrement, runningEndDate, recurrence);
			}
		}
		int loopCt = persistedevents.size();
		while (totalOccurences.intValue() > loopCt) {
			CalendarEventResponse newcal = cloneCalendarResponseEvent(calendar,
					runningStartDate, runningEndDate, saveTimeZone);
			persistedevents.add(newcal);
			loopCt = persistedevents.size();
			runningStartDate = incrementYearlyDate(calendarIncrementType,
					calendarIncrement, runningStartDate, recurrence);
			runningEndDate = incrementYearlyDate(calendarIncrementType,
					calendarIncrement, runningEndDate, recurrence);
		}
		return persistedevents;
	}

	public CalendarEvent saveCalendarEventToExistingSeries(
			CalendarEventRequestEvent event, String eventSeriesId,
			TimeZone currentTimeZone) {
		CalendarEvent calendar = this.createCalendarEvent(event);

		calendar.setTimeZone(currentTimeZone.getID());
		calendar.setCalendarEventSeriesId(new Integer(eventSeriesId));
		calendar = (CalendarEvent) new mojo.km.persistence.Home()
				.bind(calendar);

		return calendar;
	}

	public Collection saveWeeklyCalendarEvent(WeeklyRecurrence recurrence,
			CalendarEvent calendar, ArrayList persistedevents) {
		Calendar cal = Calendar.getInstance();
		Date caDate = calendar.getStartDatetime();
		cal.setTime(caDate);
		int calday = cal.get(Calendar.DAY_OF_WEEK);

		if (recurrence.isScheduleFriday()) {
			if (calday == Calendar.FRIDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleMonday()) {
			if (calday == Calendar.MONDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleSaturday()) {
			if (calday == Calendar.SATURDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleSunday()) {
			if (calday == Calendar.SUNDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleThursday()) {
			if (calday == Calendar.THURSDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleTuesday()) {
			if (calday == Calendar.TUESDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleWednesday()) {
			if (calday == Calendar.WEDNESDAY) {
				calendar = (CalendarEvent) new mojo.km.persistence.Home()
						.bind(calendar);
				persistedevents.add(calendar);
			}
		}
		return persistedevents;
	}

	public static Iterator getCalendarContext(Integer calendarEventId) {
		return CalendarEventContext.findByCalendarEventId(calendarEventId);

	}

	public Collection saveWeeklyCalendarResponseEvent(
			WeeklyRecurrence recurrence, CalendarEventResponse calendar,
			ArrayList persistedevents) {
		Calendar cal = Calendar.getInstance();
		Date caDate = calendar.getStartDatetime();
		cal.setTime(caDate);
		int calday = cal.get(Calendar.DAY_OF_WEEK);
		if (recurrence.isScheduleFriday()) {
			if (calday == Calendar.FRIDAY) {
				persistedevents.add(calendar);
			}
		}
		if (recurrence.isScheduleMonday()) {
			if (calday == Calendar.MONDAY) {
				persistedevents.add(calendar);
			}
		}
		if (recurrence.isScheduleSaturday()) {
			if (calday == Calendar.SATURDAY) {
				persistedevents.add(calendar);
			}
		}
		if (recurrence.isScheduleSunday()) {
			if (calday == Calendar.SUNDAY) {
				persistedevents.add(calendar);
			}
		}
		if (recurrence.isScheduleThursday()) {
			if (calday == Calendar.THURSDAY) {
				persistedevents.add(calendar);
			}
		}
		if (recurrence.isScheduleTuesday()) {
			if (calday == Calendar.TUESDAY) {
				persistedevents.add(calendar);

			}

		}
		if (recurrence.isScheduleWednesday()) {
			if (calday == Calendar.WEDNESDAY) {
				persistedevents.add(calendar);
			}
		}
		return persistedevents;
	}

	public Collection saveWeekDayCalendarResponseEvent(
			DailyRecurrence recurrence, CalendarEventResponse calendar,
			ArrayList persistedevents) {
		Calendar cal = Calendar.getInstance();
		Date caDate = calendar.getStartDatetime();
		cal.setTime(caDate);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		if (day != Calendar.SATURDAY && day != Calendar.SUNDAY) {
			persistedevents.add(calendar);
		}
		return persistedevents;
	}

	public Collection saveWeekDayCalendarEvent(DailyRecurrence recurrence,
			CalendarEvent calendar, ArrayList persistedevents) {
		Calendar cal = Calendar.getInstance();
		Date caDate = calendar.getStartDatetime();
		cal.setTime(caDate);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		if (day != Calendar.SATURDAY && day != Calendar.SUNDAY) {
			calendar = (CalendarEvent) new mojo.km.persistence.Home()
					.bind(calendar);
			persistedevents.add(calendar);
		}
		return persistedevents;
	}

}
