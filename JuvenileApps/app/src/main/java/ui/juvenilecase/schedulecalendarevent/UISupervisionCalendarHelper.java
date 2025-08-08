/*
 * Created on Feb 5, 2007
 *
 */
package ui.juvenilecase.schedulecalendarevent;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.calendar.GetRecurringServiceEventsEvent;
import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceAttendanceContextResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.reply.FamilyConstellationGuardianResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileWarrantConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author C_NRaveendran
 */

public class UISupervisionCalendarHelper
{
	public static List getRecurringEvents(ScheduleNewEventForm form)
	{
		GetRecurringServiceEventsEvent getRecurringEventsEvent = (GetRecurringServiceEventsEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.GETRECURRINGSERVICEEVENTS);

		getRecurringEventsEvent.setEndDatetime(form.getCurrentService().getCurrentEvent().getEndDate());
		getRecurringEventsEvent.setLocation(form.getCurrentService().getLocation());
		getRecurringEventsEvent.setStartDatetime(form.getCurrentService().getCurrentEvent().getStartDate());
		getRecurringEventsEvent.setStatus("A");

		String recurrencePattern = form.getCurrentService().getCurrentEvent().getRecurrencePattern();
		getRecurringEventsEvent.setRecurrencePattern(recurrencePattern);
		if( recurrencePattern.equals(PDCalendarConstants.DAILY_RECURRENCE) )
		{
			setupDailyRecurrence(getRecurringEventsEvent, form);
		}
		else if( recurrencePattern.equals(PDCalendarConstants.WEEKLY_RECURRENCE) )
		{
			setupWeeklyRecurrence(getRecurringEventsEvent, form);
		}
		else if( recurrencePattern.equals(PDCalendarConstants.MONTHLY_RECURRENCE) )
		{
			setupMonthlyRecurrence(getRecurringEventsEvent, form);
		}
		else if( recurrencePattern.equals(PDCalendarConstants.YEARLY_RECURRENCE) )
		{
			setupYearlyRecurrence(getRecurringEventsEvent, form);
		}

		if( form.getCurrentService().getCurrentEvent().isFrequencyRadio() )
		{
			getRecurringEventsEvent.setNumberedRecurrenceRange(true);

			Integer totalOccurrences = null;
			String recurrenceFrequency = form.getCurrentService().getCurrentEvent().getRecurrenceFrequency().trim();
			if( notNullNotEmptyString( recurrenceFrequency ) )
			{
				totalOccurrences = new Integer( recurrenceFrequency );
			}
			getRecurringEventsEvent.setTotalOccurrences(totalOccurrences);
		}
		else
		{
			getRecurringEventsEvent.setNumberedRecurrenceRange(false);
			Date recurrenceEndDate = DateUtil.stringToDate(
					form.getCurrentService().getCurrentEvent().getRecurrenceEndDate(), "MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(recurrenceEndDate);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			getRecurringEventsEvent.setRecurrenceEndDate(cal.getTime());
		}

		List events = MessageUtil.postRequestListFilter(getRecurringEventsEvent, CalendarServiceEventResponseEvent.class);
		return events;
	}

	/*
	 * 
	 */
	public static CalendarServiceEventResponseEvent getNonRecurringEvent(ScheduleNewEventForm form)
	{
		CalendarServiceEventResponseEvent calendarResponseEvent = new CalendarServiceEventResponseEvent();

		calendarResponseEvent.setStartDatetime(form.getCurrentService().getCurrentEvent().getStartDate());
		calendarResponseEvent.setEndDatetime(form.getCurrentService().getCurrentEvent().getEndDate());
		calendarResponseEvent.setLocation(form.getCurrentService().getLocation());
		calendarResponseEvent.setStatus("A");
		
		return( calendarResponseEvent );
	}

	/*
	 * 
	 */
	public static void setupDailyRecurrence(
			GetRecurringServiceEventsEvent getRecurringEventsEvent, ScheduleNewEventForm form)
	{
		if( form.getCurrentService().getCurrentEvent().isDailyRadio() )
		{
			getRecurringEventsEvent.setDailyEveryWeekDay(false);

			Integer frequencyPattern = null;
			String interval = form.getCurrentService().getCurrentEvent().getDailyRecurrenceInterval().trim();
			if( notNullNotEmptyString( interval ) )
			{
				frequencyPattern = new Integer( interval );
			}
			getRecurringEventsEvent.setFrequencyPattern(frequencyPattern);
		}
		else
		{
			getRecurringEventsEvent.setDailyEveryWeekDay(true);
			getRecurringEventsEvent.setFrequencyPattern(new Integer(1));
		}
	}

	/*
	 * 
	 */
	public static void setupWeeklyRecurrence(
			GetRecurringServiceEventsEvent getRecurringEventsEvent, ScheduleNewEventForm form)
	{
		Integer weeklyRecurrenceInterval = null;
		String str = form.getCurrentService().getCurrentEvent().getWeeklyRecurrenceInterval().trim();
		if( notNullNotEmptyString( str ) )
		{
			weeklyRecurrenceInterval = new Integer( str );
		}
		getRecurringEventsEvent.setFrequencyPattern(weeklyRecurrenceInterval);

		String weeklyDay[] = form.getCurrentService().getCurrentEvent().getWeeklyDay();
		getRecurringEventsEvent.setWeeklyDay(weeklyDay);
	}

	/*
	 * 
	 */
	public static void setupMonthlyRecurrence(
			GetRecurringServiceEventsEvent getRecurringEventsEvent, ScheduleNewEventForm form)
	{
		if( form.getCurrentService().getCurrentEvent().isMonthlyRadio() )
		{
			getRecurringEventsEvent.setMonthlyRecurrenceType(true);

			Integer monthlyRecurrenceInterval = null;
			String str = form.getCurrentService().getCurrentEvent().getMonthlyRecurrenceInterval1().trim();
			if( notNullNotEmptyString( str ) )
			{
				monthlyRecurrenceInterval = new Integer( str );
			}
			getRecurringEventsEvent.setFrequencyPattern(monthlyRecurrenceInterval);

			Integer monthlyDay = null ;
			str = form.getCurrentService().getCurrentEvent().getMonthlyDay().trim() ;
			if( notNullNotEmptyString( str ) )
			{
				monthlyDay = new Integer( str );
			}
			getRecurringEventsEvent.setMonthlyDay(monthlyDay);
		}
		else
		{
			getRecurringEventsEvent.setMonthlyRecurrenceType(false);

			Integer monthlyRecurrenceInterval = null;
			String str = form.getCurrentService().getCurrentEvent().getMonthlyRecurrenceInterval2().trim();
			if( notNullNotEmptyString( str ) )
			{
				monthlyRecurrenceInterval = new Integer( str );
			}
			getRecurringEventsEvent.setFrequencyPattern(monthlyRecurrenceInterval);

			str = form.getCurrentService().getCurrentEvent().getMonthlyWeekNumber().trim();
			Integer weekNumber = null ;
			if( notNullNotEmptyString(str) )
			{
				weekNumber = new Integer(str);
			}
			getRecurringEventsEvent.setMonthlyWeekNumber(weekNumber);

			Integer weekDay = null ;
			str = form.getCurrentService().getCurrentEvent().getMonthlyWeekDay().trim();
			if( notNullNotEmptyString(str) )
			{
				weekDay = new Integer( str );
			}
			getRecurringEventsEvent.setMonthlyWeekDay(weekDay);
		}
	}

	/*
	 * 
	 */
	public static void setupYearlyRecurrence(
			GetRecurringServiceEventsEvent getRecurringEventsEvent, ScheduleNewEventForm form)
	{
		String str = "" ;
		
		if( form.getCurrentService().getCurrentEvent().isYearlyRadio() )
		{
			getRecurringEventsEvent.setYearlyRecurrenceType(true);
			str = form.getCurrentService().getCurrentEvent().getYearlyMonthNumber().trim();
			Integer month = null ;
			if( notNullNotEmptyString( str ))
			{
				month = new Integer(str);				
			}
			getRecurringEventsEvent.setYearlyMonthNumber(month);
			
			Integer yearlyDay = null ;
			str = form.getCurrentService().getCurrentEvent().getYearlyDay().trim();
			if( notNullNotEmptyString( str ))
			{
				yearlyDay = new Integer( str ) ;
			}
			getRecurringEventsEvent.setYearlyDay(yearlyDay);
		}
		else
		{
			getRecurringEventsEvent.setYearlyRecurrenceType(false);
			
			Integer yearlyWeekNumber = null ;
			str = form.getCurrentService().getCurrentEvent().getYearlyWeekNumber().trim();
			if( notNullNotEmptyString( str ))
			{
				yearlyWeekNumber = new Integer( str ) ;
			}
			getRecurringEventsEvent.setYearlyWeekNumber(yearlyWeekNumber);

			Integer yearlyWeekDay = null ;
			str = form.getCurrentService().getCurrentEvent().getYearlyWeekDay().trim();
			if( notNullNotEmptyString( str ))
			{
				yearlyWeekDay = new Integer( str ) ;//Bug Report#: 29695
			}
			getRecurringEventsEvent.setYearlyWeekDay(yearlyWeekDay);

			Integer month = null ;
			str = form.getCurrentService().getCurrentEvent().getYearlyMonthNumber1().trim();
			if( notNullNotEmptyString( str ))
			{
				month = new Integer( str ) ;
			}
			getRecurringEventsEvent.setYearlyMonthNumber(month);
		}
	}

	/*
	 * 
	 */
	public static void printCancellationList(ServiceEventDetailsForm form, HttpServletResponse aResponse)
	{
		GenericPrintRequestEvent cancellationListPrintEvent = new GenericPrintRequestEvent();
		form.setCurrentDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy"));
		cancellationListPrintEvent.addDataObject(form);
		cancellationListPrintEvent.setReportName("REPORTING::EVENT_CANCELLATION_CALL_LIST");

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(cancellationListPrintEvent);

		CompositeResponse compResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException(compResponse);

		ReportResponseEvent aRespEvent = (ReportResponseEvent)
				MessageUtil.filterComposite( compResponse, ReportResponseEvent.class);
		aResponse.setContentType("application/x-file-download");
		aResponse.setHeader("Content-disposition", "attachment; filename=" + 
				aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");
		aResponse.setHeader("Cache-Control", "max-age=" + 1200);
		aResponse.setContentLength(aRespEvent.getContent().length);
		aResponse.resetBuffer();
		OutputStream os;
		try
		{
			os = aResponse.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 */
	public static void printCancellationListBFO(ServiceEventDetailsForm form, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		GenericPrintRequestEvent cancellationListPrintEvent = new GenericPrintRequestEvent();
		form.setCurrentDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy"));
		cancellationListPrintEvent.addDataObject(form);
		cancellationListPrintEvent.setReportName("PDFReport.EVENT_CANCELLATION_CALL_LIST");
		
		aRequest.getSession().setAttribute("reportInfo", form);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.EVENT_CANCELLATION_CALL_LIST);
	}

	/*
	 * 
	 */
	public static void sendCancellationNotifications(ServiceEventDetailsForm form,List events)
	{
		notifyOfficers(form,events);
		notifyProvider(form);
		notifyGuardian(form,events);
	}

	/*
	 * 
	 */
	public static void notifyOfficers(ServiceEventDetailsForm form,List events)
	{
		LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
		
		nevt.setSubject("EVENT CANCELLATION");
		nevt.setServiceName(form.getServiceName());
		nevt.setServiceType(form.getEventType());
		
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");
		nevt.setSessionDate(dfmt.format(form.getEventDate()));
		dfmt.applyPattern("h:mm a");
		nevt.setSessionTime(dfmt.format(form.getEventDate()));
		

		//<KISHORE>JIMS200060339 : MJCW - Cancelling SP Events Isn't Sending Notices Correctly
		if(events != null){
			Iterator i = events.iterator();
			HashMap jpoJuvMap = new HashMap();
			HashMap juvMap = new HashMap();
			while (i.hasNext()){
				ServiceAttendanceContextResponseEvent resp = (ServiceAttendanceContextResponseEvent)i.next();
				if( jpoJuvMap.containsKey(resp.getProbationOfficerLogonId()) )
				{
					String juvenileId = (String)juvMap.get(resp.getProbationOfficerLogonId());
					// Check if the juvenile is not included in the message for that officer
					//One juvenile can be scheduled for one Service event multiple times by different officers by selecting the casefiles
					//But juvenile should be included in the message only once
					if(!StringUtils.equals(juvenileId, resp.getJuvenileId())){
						StringBuffer sb = (StringBuffer)jpoJuvMap.get(resp.getProbationOfficerLogonId());
						sb.append("<B>");
						Name name = new Name(resp.getJuvenileFirstName(), 
								resp.getJuvenileMiddleName(), resp.getJuvenileLastName());
						sb.append(name.getFormattedName());
						sb.append("</B>");
						sb.append("<br>");
					}
				}
				else
				{
					StringBuffer sb = new StringBuffer();
					sb.append("<br>");
					sb.append("<B>");
					Name name = new Name(resp.getJuvenileFirstName(), 
							resp.getJuvenileMiddleName(), resp.getJuvenileLastName());
					sb.append(name.getFormattedName());
					sb.append("</B>");
					sb.append("<br>");
					jpoJuvMap.put(resp.getProbationOfficerLogonId(), sb);
					juvMap.put(resp.getProbationOfficerLogonId(), resp.getJuvenileId());
				}
			}
			Iterator<Map.Entry> iter = jpoJuvMap.entrySet().iterator();
			while(iter.hasNext())
			{
				Map.Entry entry = iter.next();
				nevt.setIdentity( (String)entry.getKey() );
				StringBuffer sb = (StringBuffer)entry.getValue();
				nevt.setNotificationMessage(sb.toString());
				sendNotification(nevt, UIConstants.EVENT_CANCELLATION_OFFICER_NOTIFICATION);
			}
		}
	}

	/*
	 * 
	 */
	public static void notifyProvider(ServiceEventDetailsForm form)
	{
		LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
		
		nevt.setSubject("EVENT CANCELLATION");
		nevt.setServiceName(form.getServiceName());
		nevt.setServiceType(form.getEventType());
		
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");
		nevt.setSessionDate(dfmt.format(form.getEventDate()));
		dfmt.applyPattern("h:mm a");
		
		nevt.setSessionTime(dfmt.format(form.getEventDate()));
		nevt.setUserName(UIUtil.getCurrentUserName());
		nevt.setIdentity(form.getAdminUserProfileId());
		
		sendNotification(nevt, UIConstants.EVENT_CANCELLATION_PROVIDER_NOTIFICATION);
	}

	// notify Guardian : send email to Guardian if email address exists
	public static void notifyGuardian(ServiceEventDetailsForm form,List events)
	{
		LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();

		nevt.setSubject("EVENT CANCELLATION");
		nevt.setServiceName(form.getServiceName());
		nevt.setServiceType(form.getEventType());

		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");
		nevt.setSessionDate(dfmt.format(form.getEventDate()));
		dfmt.applyPattern("h:mm a");
		nevt.setSessionTime(dfmt.format(form.getEventDate()));

		//<KISHORE>JIMS200060339 : MJCW - Cancelling SP Events Isn't Sending Notices Correctly
		if(events != null){
			Iterator i = events.iterator();
			HashMap juvMap = new HashMap();
			while (i.hasNext()){
				ServiceAttendanceContextResponseEvent resp = (ServiceAttendanceContextResponseEvent)i.next();
				//One juvenile can be scheduled for one Service event multiple times by different officers by selecting the casefiles
				//But their guardians should be notified only once per juvenile
				if(juvMap.isEmpty() || !juvMap.containsKey(resp.getJuvenileId())){
					juvMap.put(resp.getJuvenileId(), true);
					List guardians = getGuardiansList(resp.getJuvenileId(),form);
					if(guardians != null && !guardians.isEmpty()){
						Name name = new Name(resp.getProbationOfficerFirstName(), resp.getProbationOfficerMiddleName(), resp.getProbationOfficerLastName());
						nevt.setProbationOfficerName(name.getFormattedName());
						Iterator<FamilyConstellationGuardianResponseEvent> guardianResponseEvents =	guardians.iterator();
						while( guardianResponseEvents.hasNext() )
						{
							FamilyConstellationGuardianResponseEvent guarResp = guardianResponseEvents.next();
							if( guarResp.getEmailAddresses() != null )
							{
								Iterator guarEmail = guarResp.getEmailAddresses().iterator();
								while(guarEmail.hasNext())
								{
									StringBuffer sb = new StringBuffer("");
									sb.append(guarEmail.next());
									nevt.setGuardianEmail(sb.toString());
									nevt.setIdentity(nevt.getGuardianEmail());
									sendNotification(nevt, UIConstants.EVENT_CANCELLATION_GUARDIAN_NOTIFICATION);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static List getGuardiansList(String juvenileId,ServiceEventDetailsForm form){
		if(form.getCancellationList() != null && !form.getCancellationList().isEmpty()){
			Iterator<ServiceEventCancellationResponseEvent> iterCancellationList = 
				form.getCancellationList().iterator();
			while( iterCancellationList.hasNext() )
			{
				ServiceEventCancellationResponseEvent emailDetails = iterCancellationList.next();
				if(StringUtils.equalsIgnoreCase(juvenileId,emailDetails.getJuvenileId())){
					return emailDetails.getGuardianResponseEvents();
				}
			}
		}
		return null;
	}
	
	public static void sendNotification(LocationNotificationResponseEvent nevt, String topic)
	{
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
				EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic(topic);
		notificationEvent.setSubject(nevt.getSubject());
		notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
		notificationEvent.addContentBean(nevt);
		
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
	}

	/**
     *  
     */
	public static void sendAttendanceNotification(ServiceEventDetailsForm detailsForm)
	{
		LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
		
		nevt.setSubject("JUVENILE ATTENDANCE");
		nevt.setServiceName(detailsForm.getServiceName());
		nevt.setServiceType(detailsForm.getEventType());
		
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");
		nevt.setSessionDate(dfmt.format(detailsForm.getEventDate()));
		dfmt.applyPattern("h:mm a");
		nevt.setSessionTime(dfmt.format(detailsForm.getEventDate()));
		nevt.setServiceProviderName(detailsForm.getServiceProviderName());

		HashMap juvMap = new HashMap();
		Iterator<JuvenileCasefileResponseEvent> associatedContexts = 
				detailsForm.getAssociatedContexts().iterator();
		while(associatedContexts.hasNext())
		{
			JuvenileCasefileResponseEvent resp = associatedContexts.next();
			if( resp != null && notNullNotEmptyString( resp.getJuvenileNum() ) )
			{
				juvMap.put(resp.getJuvenileNum().trim(), resp);
			}
		}

		Iterator<ServiceEventAttendanceResponseEvent> iter = 
				detailsForm.getNewAttendanceEvents().iterator();
		while( iter.hasNext() )
		{
			ServiceEventAttendanceResponseEvent ev = iter.next();
			if( ev.getAttendanceStatusCd() != null && 
					(ev.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED) || 
					 ev.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT)) )
			{
				if( !juvMap.isEmpty() )
				{
					JuvenileCasefileResponseEvent resp = 
							(JuvenileCasefileResponseEvent)juvMap.get(ev.getJuvenileId().trim());
					nevt.setJuvenileName(ev.getJuvenileName());
					nevt.setJuvenileNum(ev.getJuvenileId());

					GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
							EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
					
					gofe.setOfficerProfileId(resp.getProbationOfficeId());
					CompositeResponse response = MessageUtil.postRequest(gofe);
					OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent)
							MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);
					
					if( officerProfileResponse != null )
					{
						nevt.setIdentity(officerProfileResponse.getUserId());
						sendNotification(nevt, UIConstants.EVENT_ATTENDANCE_OFFICER_NOTIFICATION);
					}
				}
			}
		}
	}

	/*
	 * 
	 */
	public static void getCalendarEventDetails(HttpServletRequest aRequest)
	{
		HttpSession session = aRequest.getSession();
		CalendarEventListForm aForm = (CalendarEventListForm)session.getAttribute("calendarEventListForm");
		if( aForm == null )
		{
			aForm = new CalendarEventListForm();
			session.setAttribute("calendarEventListForm", aForm);
		}
	}

	/*
	 * 
	 */
	public static CalendarServiceEventResponseEvent populateCalendarForm(
			CalendarEventListForm form, String eventId, String currentJuvenileId)
	{
		GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);

		Calendar requestedDate = Calendar.getInstance();
		requestedDate.setTime(form.getEventDate());
		requestedDate.set(Calendar.HOUR_OF_DAY, 0);
		requestedDate.set(Calendar.MINUTE, 0);
		requestedDate.set(Calendar.SECOND, 0);
		requestedDate.set(Calendar.MILLISECOND, 0);

		gce.setStartDatetime(requestedDate.getTime());
		requestedDate.set(Calendar.HOUR_OF_DAY, 23);
		requestedDate.set(Calendar.MINUTE, 59);
		requestedDate.set(Calendar.SECOND, 59);
		requestedDate.set(Calendar.MILLISECOND, 0);

		gce.setEndDatetime(requestedDate.getTime());

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		calendarContextEvent.setJuvenileId(currentJuvenileId);
		form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JUVENILE);
		gce.setCalendarContextEvent(calendarContextEvent);
		gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(
				CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));

		List<CalendarServiceEventResponseEvent> events = MessageUtil.postRequestListFilter(
				gce, CalendarServiceEventResponseEvent.class);

		if( !  events.isEmpty() )
		{
			for(CalendarServiceEventResponseEvent event : events)
			{
				if( event.getEventId().equals(eventId) )
				{
					return event;
				}
			}
		}

		return null;
	}

	/*
	 * 
	 */
	public static void getAttendanceRecord(CalendarEventListForm form, String juvenileId, String eventId)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileAttendanceEvent getAttendanceEvent = (GetJuvenileAttendanceEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.GETJUVENILEATTENDANCE);
		getAttendanceEvent.setJuvenileId(juvenileId);
		getAttendanceEvent.setServiceEventId(eventId);
		
		dispatch.postEvent(getAttendanceEvent);
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();

		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection<ServiceEventAttendanceResponseEvent> attendanceEvents = 
				MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);

		if( !attendanceEvents.isEmpty() )
		{
			for(ServiceEventAttendanceResponseEvent attendanceResponseEvent : attendanceEvents)
			{
				String statusCode = attendanceResponseEvent.getAttendanceStatusCd().trim();
				if( statusCode.equals(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) || 
						statusCode.equals(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED) )
				{
					Date dateToday = new Date();
					ArrayList attendanceStatusList = new ArrayList();
					attendanceStatusList.add(CodeHelper.getCode(
							PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, 
							PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED));

					// (value less than 0) if getStartDatetime is before today's date
					if( form.getCurrentEvent().getStartDatetime().compareTo(dateToday) < 1 )
					{
						attendanceStatusList.add(CodeHelper.getCode( 
								PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, 
								PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED));

						attendanceStatusList.add(CodeHelper.getCode( 
								PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, 
								PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT));
					}
					form.setAttendanceStatusList(attendanceStatusList);
					form.setExistingProgressNotes(attendanceResponseEvent.getProgressNotes());
					form.setProgressNotes("");
					form.setAttendanceStatusCd("");
					form.setAction("attendanceUpdate");
					form.setAddlAttendees("");
				}
				else
				{
					form.setAttendanceStatusCd(attendanceResponseEvent.getAttendanceStatusCd());
					form.setAttendanceStatus(attendanceResponseEvent.getAttendanceStatusDescription());
					form.setExistingProgressNotes(attendanceResponseEvent.getProgressNotes());
					form.setProgressNotes(attendanceResponseEvent.getProgressNotes());
					form.setAddlAttendees(attendanceResponseEvent.getAddlAttendees());
					form.setAction("attendancePresent");
				}
			}// for
		}
	}

	/*
	 * 
	 */
	public static Collection getJuvenileContacts(String juvenileId)
	{
		List names = new ArrayList();
		GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
		
		contactsEvent.setJuvenileNum(juvenileId);
		CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
		Collection<JuvenileContactResponseEvent> contacts = (Collection)
				UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);
		if( ! contacts.isEmpty() )
		{
			for( JuvenileContactResponseEvent contact : contacts )
			{
				AttendeeNameResponseEvent contactName = new AttendeeNameResponseEvent();
				contactName.setFirstName(contact.getFirstName());
				contactName.setMiddleName(contact.getMiddleName());
				contactName.setLastName(contact.getLastName());
				names.add(contactName);
			}
		}

		getFamilyContacts(juvenileId, names);
		if( !names.isEmpty() )
		{
			Collections.sort(names);
		}

		return names;
	}

	/*
	 * 
	 */
	private static void getFamilyContacts(String juvenileId, List familyContacts)
	{
		GetActiveFamilyConstellationEvent getCurrentConstellation = new GetActiveFamilyConstellationEvent();
		
		getCurrentConstellation.setJuvenileNum(juvenileId);
		CompositeResponse replyEvent = MessageUtil.postRequest(getCurrentConstellation);
		Collection<FamilyConstellationListResponseEvent> constellationList = 
				MessageUtil.compositeToCollection(replyEvent, FamilyConstellationListResponseEvent.class);
		
		if( !constellationList.isEmpty() )
		{
			/*
			 * Only 1 active constellation at a time, 
			 * therefore it's safe to get the first in collection
			 */
			FamilyConstellationListResponseEvent activeConstellation = constellationList.iterator().next();

			GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
			
			getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
			CompositeResponse constDetailsEvent = MessageUtil.postRequest(getConstellationDetails);
			Collection<FamilyConstellationMemberListResponseEvent> familyMembers = MessageUtil.compositeToCollection(constDetailsEvent, FamilyConstellationMemberListResponseEvent.class);
			
			if( ! familyMembers.isEmpty() )
			{
				for(FamilyConstellationMemberListResponseEvent member : familyMembers)
				{
					GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent)
							EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
					
					getMemberDetails.setMemberNum(member.getMemberNum());
					replyEvent = MessageUtil.postRequest(getMemberDetails);
					
					FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent)
							MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);
					if (!memberDetail.isDeceasedInd()) {
						AttendeeNameResponseEvent contactName = new AttendeeNameResponseEvent();
						contactName.setFirstName(memberDetail.getFirstName());
						contactName.setMiddleName(memberDetail.getMiddleName());
						contactName.setLastName(memberDetail.getLastName());
						familyContacts.add(contactName);
					}
				}
			}
		}
	}

	/*
	 * 
	 */
	public static void getJuvenileNameAndBirthDate(ScheduleNewEventForm form, String juvenileId)
	{
		GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
		
		getJuvProfileEvent.setJuvenileNum(juvenileId);
		CompositeResponse response = MessageUtil.postRequest(getJuvProfileEvent);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent)
				MessageUtil.filterComposite( response, JuvenileProfileDetailResponseEvent.class);
		form.setBirthDate(DateUtil.dateToString(juvProfileRE.getDateOfBirth(), UIConstants.DATE_FMT_1));
		Name myName = new Name();
		myName.setFirstName(juvProfileRE.getFirstName());
		myName.setLastName(juvProfileRE.getLastName());
		myName.setMiddleName(juvProfileRE.getMiddleName());
		form.setJuvenileFullName(myName.getCompleteFullNameFirst().trim());
	}
	
	/*
	 * given a String, return true if it's not null and not empty
	 */
	private static boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.trim().length() > 0) );
	}
	
	/*
	 * Get the max severity level record for each Referral
	 * 
	 * @param referralList
	 * 
	 * @param juvenileNum
	 */
	public static JuvenileCasefileReferralsResponseEvent getMaxSeverityOffense(JuvenileCasefileReferralsResponseEvent resp,
			String juvenileNum) {
		String controllingReferral = resp.getReferralNumber();
		// get petition with max severity level for each referral
		if (resp.getReferralNumber() != null) {
			String referralNum = UIConstants.EMPTY_STRING;
			String referralWithPetition = UIConstants.EMPTY_STRING;
			// get petitions
			GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent) EventFactory
					.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);

			pet.setJuvenileNum(juvenileNum);
			pet.setReferralNum(controllingReferral);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(pet);

			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Map map = MessageUtil.groupByTopic(response);

			Collection<JJSChargeResponseEvent> petitions = (Collection) map.get(PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC);
			int count = 0;
			if (petitions != null) {
				HashMap petitionsMap = new HashMap();
				Iterator<JJSChargeResponseEvent> petIter = petitions.iterator();
				while (petIter.hasNext()) {
					JJSChargeResponseEvent chgResp = petIter.next();
					if (count == 0 && !petIter.hasNext()) { 
						// if this is the while's first iteration and
						// there is *not* another entry in the HashMap
						referralNum = controllingReferral;
						if (notNullNotEmptyString(chgResp.getLevelDegree())) {
							referralNum = (referralNum + " - " + chgResp.getLevelDegree());
							referralWithPetition = (controllingReferral + " - " + chgResp.getLevelDegree() + " - " + chgResp.getPetitionNum());
						}
						break;
					}

					if (petitionsMap == null || petitionsMap.isEmpty() || !petitionsMap.containsKey(chgResp.getLevelDegree())) {
						petitionsMap.put(chgResp.getLevelDegree(), chgResp);
					} else {
						JJSChargeResponseEvent tempResp = (JJSChargeResponseEvent) petitionsMap.get(chgResp.getLevelDegree());
						if (chgResp.getPetitionDate().compareTo(tempResp.getPetitionDate()) > 0) {
							petitionsMap.remove(tempResp.getLevelDegree());
							petitionsMap.put(chgResp.getLevelDegree(), chgResp);
						}
					}
					++count;
				} // while

				if (count != 0) { 
					// we're in here because level degree was never
					// appended to the referral number in the previous loop
					referralNum = getMaxSeverityWithPetition(petitionsMap, controllingReferral,false);
					referralWithPetition=getMaxSeverityWithPetition(petitionsMap, controllingReferral,true);
				}
			} else { // else petitions collection is null - get the offenses
				count = 0;
				Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses(juvenileNum, controllingReferral);
				HashMap offenseMap = new HashMap();
				if (offenses != null) {
					Iterator<JJSOffenseResponseEvent> offIter = offenses.iterator();
					while (offIter.hasNext()) {
						JJSOffenseResponseEvent offResp = offIter.next();
						if (count == 0 && !offIter.hasNext()) { 
							// if this is the while's first iteration and
							// there is *not* another entry in the offenses
							// Collection
							referralNum = controllingReferral;
							referralNum = (referralNum + " - " + offResp.getCatagory());
							referralWithPetition = (controllingReferral + " - " + offResp.getCatagory() + " - " + offResp.getPetitionNumber());
							break;
						}

						if (offenseMap == null || offenseMap.isEmpty() || !offenseMap.containsKey(offResp.getCatagory())) {
							offenseMap.put(offResp.getCatagory(), offResp);
						} else {
							JJSOffenseResponseEvent tempResp = (JJSOffenseResponseEvent) offenseMap.get(offResp.getCatagory());
							if (offResp.getOffenseDate().compareTo(tempResp.getOffenseDate()) > 0) {
								offenseMap.remove(tempResp.getCatagory());
								offenseMap.put(offResp.getCatagory(),offResp);
							}
						}
						++count;
					} // while
				}

				if (count != 0) { 
					// we're in here because level degree was never
					// appended to the referral number in the previous loop
					referralNum = getMaxSeverityWithPetition(offenseMap, controllingReferral,false);
					referralWithPetition=getMaxSeverityWithPetition(offenseMap, controllingReferral,true);
				}
			}
			checkReferralNumber(referralNum);
			resp.setReferralNumber(referralNum);
			resp.setReferralNumberWithPetition(referralWithPetition);
			return resp;
			
		} // for
		return null;
	}
	
	/*
	 * @param offenseMap
	 * 
	 * @param referralNum
	 * 
	 * @return
	 */
	public static String getMaxSeverityWithPetition(HashMap offenseMap, String referralNum, boolean petition) {
		String severity = UIConstants.EMPTY_STRING;

		if (offenseMap != null && offenseMap.size() != 0) {
			
			
			if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CF) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_CF);
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F1) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_F1);		
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F2) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_F2);		
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F3) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_F3);				
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_JF) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_JF);	
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MA) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_MA);	
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MB) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_MB);	
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MC) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_MC);	
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CO) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_CO);	
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_SO) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_SO);	
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_AC) != null) {
				severity = setRefNum(referralNum, offenseMap, petition, UIConstants.OFFENSE_LEVEL_DEGREE_AC);	
			}
		}

		return severity;
	}
	/*
	 * given a referral number, ensure it's well-formed
	 */
	private static String checkReferralNumber(String referralNum) {
		String str = UIConstants.EMPTY_STRING;
		String newNum = UIConstants.EMPTY_STRING;
		str = referralNum;
		
		int firstIndex = str.indexOf("-"), lastIndex = str.lastIndexOf("-");

		if (firstIndex != (-1)) { // found a first occurrence
			if (lastIndex != (-1) && (firstIndex != lastIndex)) { 
				// last occurrence found and both char offsets differ,
				// which means the number looks like, "1010 - JF - JF"
				newNum = str.substring(0, (lastIndex - 1));
				referralNum = newNum;				
			}
		}
		return referralNum;
	}
	
	private static String setRefNum(String referralNum, HashMap offenseMap, boolean petition, String level)
	{
		JJSChargeResponseEvent chrg = new JJSChargeResponseEvent();
		JJSOffenseResponseEvent off = new JJSOffenseResponseEvent();
		String severity="";
		if(!petition)
		{
			severity = referralNum + " - " + level;
		}
		else
		{
			chrg = (JJSChargeResponseEvent)offenseMap.get(level);
			if(chrg!=null)
				severity = referralNum + " - " + level + " - " + chrg.getPetitionNum();
			else
			{
				off = (JJSOffenseResponseEvent)offenseMap.get(level);
				severity = referralNum + " - " + level + " - " + off.getPetitionNumber();
			}
		}
		return severity;
	}
	
	
	public static Collection<ServiceEventAttendanceResponseEvent> getServiceAttendEventList(String eventId){
	    
	    Collection<ServiceEventAttendanceResponseEvent> eventAttendanceList = new ArrayList<ServiceEventAttendanceResponseEvent>();
	    
	    if(eventId == null || "".equals(eventId)){		
		return eventAttendanceList;
	    }
	    
	    IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
	    
	    GetServiceEventAttendanceEvent getServAttendanceEvent = (GetServiceEventAttendanceEvent)
			EventFactory.getInstance( ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE ) ;

        	getServAttendanceEvent.setServiceEventId(eventId) ;
        	dispatch.postEvent( getServAttendanceEvent ) ;
        
        	CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;
        	Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
        	MessageUtil.processReturnException( dataMap ) ;

		eventAttendanceList = MessageUtil.compositeToCollection( compositeResponse, ServiceEventAttendanceResponseEvent.class ) ;
		
		return eventAttendanceList;
	
	}

}
