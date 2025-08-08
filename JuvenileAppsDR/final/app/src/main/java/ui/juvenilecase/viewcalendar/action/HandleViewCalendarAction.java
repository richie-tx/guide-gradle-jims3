package ui.juvenilecase.viewcalendar.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.ServiceEventAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.juvenilecase.CalendarHelper;
import ui.juvenilecase.CalendarSchedulePrintBean;
import ui.juvenilecase.CalendarSchedulePrintBean.CalendarPrintObj;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class HandleViewCalendarAction extends JIMSBaseAction
{
	/**
	 * @roseuid 45F1B0BC02DB
	 */
	public HandleViewCalendarAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayViewCalendarEventList(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm;

		refreshFields(aMapping, aForm, aRequest, aResponse);

		String serviceProviderId = SecurityUIHelper.getServiceProviderId();
		if( serviceProviderId != null )
		{
			form.setServiceProviderId(serviceProviderId);
		}

		ArrayList indivEvents = new ArrayList();
		form.setIndividualDayEvents(indivEvents);
		HttpSession session = aRequest.getSession();

		ScheduleNewEventForm oldForm = (ScheduleNewEventForm)session.getAttribute("scheduleNewEventForm");
		if( oldForm == null )
		{
			return null;
		}

		GetCalendarServiceEventsEvent gce = new GetCalendarServiceEventsEvent();

		// Start - Added to pull officerId or juvenileId
		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		
		String juvenileNum = aRequest.getParameter("juvenileNum");
		String officerId = aRequest.getParameter("officerId");

		if( notNullNotEmptyString(officerId) )
		{
			calendarContextEvent.setProbationOfficerId(officerId);
		}
		else if( notNullNotEmptyString(juvenileNum) )
		{
			calendarContextEvent.setJuvenileId(juvenileNum);
		}

		gce.setCalendarContextEvent(calendarContextEvent);
		// End

		String calendarType = oldForm.getCalendarType();
		List docketEventsList = new ArrayList();
		if( notNullNotEmptyString( calendarType ) )
		{
			Collection calendar = (Collection)session.getAttribute(calendarType);
			if( calendar != null )
			{
				Calendar requestedDate = Calendar.getInstance();
				requestedDate.setTime(form.getEventDate());
				requestedDate.set(Calendar.HOUR_OF_DAY, 0);
				requestedDate.set(Calendar.MINUTE, 0);
				requestedDate.set(Calendar.SECOND, 0);
				requestedDate.set(Calendar.MILLISECOND, 0);

				gce.setStartDatetime(requestedDate.getTime());
				List attributes = new ArrayList();
				// filter events only for the requested date
				for( Iterator iter = calendar.iterator(); iter.hasNext(); /* empty */)
				{
					//<KISHORE> JIMS200057010 : Display docket events on JPO view calendar-UI (KK)
					//events here contain both calendar and docket type events
					Object obj = iter.next();
					if( obj instanceof CalendarServiceEventResponseEvent) 
					{
						CalendarServiceEventResponseEvent event = (CalendarServiceEventResponseEvent)obj;
						Calendar eventDate = Calendar.getInstance();
						eventDate.setTime(event.getStartDatetime());
						eventDate.set(Calendar.HOUR_OF_DAY, 0);
						eventDate.set(Calendar.MINUTE, 0);
						eventDate.set(Calendar.SECOND, 0);
						eventDate.set(Calendar.MILLISECOND, 0);

						if( eventDate.equals(requestedDate) )
						{
							ServiceEventAttribute sa = new ServiceEventAttribute();
							sa.setServiceEventId(new Integer(event.getEventId()));
							attributes.add(sa);
						}
					}
					else if( obj instanceof DocketEventResponseEvent) 
					{
						DocketEventResponseEvent event = (DocketEventResponseEvent)obj;
						Calendar eventDate = Calendar.getInstance();
						eventDate.setTime(event.getStartDatetime());
						eventDate.set(Calendar.HOUR_OF_DAY, 0);
						eventDate.set(Calendar.MINUTE, 0);
						eventDate.set(Calendar.SECOND, 0);
						eventDate.set(Calendar.MILLISECOND, 0);
						if( eventDate.equals(requestedDate) )
						{
							docketEventsList.add(event);
						}
					}
				}

				requestedDate.set(Calendar.HOUR_OF_DAY, 23);
				requestedDate.set(Calendar.MINUTE, 59);
				requestedDate.set(Calendar.SECOND, 59);
				requestedDate.set(Calendar.MILLISECOND, 0);
				gce.setEndDatetime(requestedDate.getTime());

				if( attributes.size() > 0 )
				{
					gce.setCalendarAttributes(attributes);
					List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);

					if( events.size() > 1 )
					{
						if( notNullAndNotEmptyList( events ) && (serviceProviderId != null) )
						{
							List serviceProviderEvents = new ArrayList();
							Collection<CalendarServiceEventResponseEvent> listEvents = events;
							for( CalendarServiceEventResponseEvent tEvent : listEvents )
							{
								boolean eventExistInArrayList = false;
								for( Iterator<CalendarServiceEventResponseEvent> iter = serviceProviderEvents.iterator();
										iter.hasNext(); /*empty*/ )
								{
									CalendarServiceEventResponseEvent calServRespEvent = iter.next();
									if( calServRespEvent.getEventId().equals(tEvent.getEventId()) )
									{
										eventExistInArrayList = true;
										break;
									}
								}

								if( !eventExistInArrayList )
								{
									serviceProviderEvents.add(tEvent);
								}
							}
							events = new ArrayList();
							events = serviceProviderEvents;
						}

						Collections.sort(events);
					}

					DisplayViewCalendarSearchResultsAction.getIndividualDayEvents(events, indivEvents);
					form.setDayEvents(events);
				}
				form.setDocketEvents(docketEventsList);
			}
		}

		return aMapping.findForward(UIConstants.LIST_SUCCESS);
	}


	/*
	 * @param aMapping
	 * 
	 * @param aForm
	 * 
	 * @param aRequest
	 * 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward generateSchedule(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.LIST_SUCCESS);
		CalendarEventListForm form = (CalendarEventListForm)aForm;
		String juvenileNum = form.getSelectedValue();

		List myDayEvents = CalendarHelper.getFutureJuvCalendarEvents(form.getSelectedValue(), null, null);
		List myDocketEvents = CalendarHelper.getFutureJuvenileDocketEvents(form.getSelectedValue(), null, null);
		
		CalendarSchedulePrintBean myPrintBean = new CalendarSchedulePrintBean();
		myPrintBean.setTodaysDate(DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1));
		myPrintBean.setTodaysTime(DateUtil.dateToString(new Date(), 
					UIConstants.DISPLAY_PRESENTATION_TYPE_TIME_12HOUR_AM_PM));
		
		if( nullOrEmptyList(myDayEvents) && nullOrEmptyList(myDocketEvents) )
		{
			sendToErrorPage(aRequest, "error.generic", "No Future Active Juvenile Events Found");
			return forward;
		}
		else
		{
			myPrintBean.setJuvenileName(juvenileNum);

			SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent)
					EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);

			searchEvent.setJuvenileNum(juvenileNum);
			// empty string: dont include alias records  
			searchEvent.setSearchType( UIConstants.EMPTY_STRING ) ; 

			JuvenileProfileDetailResponseEvent resp = (JuvenileProfileDetailResponseEvent)
					MessageUtil.postRequest(searchEvent, JuvenileProfileDetailResponseEvent.class);

			if( resp != null )
			{
				Name myName = new Name();
				myName.setFirstName(resp.getFirstName());
				myName.setMiddleName(resp.getMiddleName());
				myName.setLastName(resp.getLastName());
				myPrintBean.setJuvenileName(myName.getFormattedName());
			}

			myPrintBean.setServiceProvider(false);
			ServiceProviderContactResponseEvent spUser = null;

			String serviceProviderId = SecurityUIHelper.getServiceProviderId();
			if( serviceProviderId != null )
			{
				ISecurityManager manager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");
				if( manager != null )
				{
					try
					{
						spUser = (ServiceProviderContactResponseEvent)manager.getIUserInfo();
						myPrintBean.setServiceProvider(true);
					}
					catch( ClassCastException e )
					{
						spUser = null;
					}
				}
			}

			if( notNullAndNotEmptyList(myDayEvents) )
			{
				Iterator<CalendarServiceEventResponseEvent> iterDay = myDayEvents.iterator();
				while( iterDay.hasNext() )
				{
					CalendarServiceEventResponseEvent myEv = iterDay.next();

					if( !myPrintBean.isServiceProvider() )
					{
						myPrintBean.addEvent(myEv);
					}
					else if( spUser != null )
					{
						String profileID = spUser.getJuvServProviderProfileId() ;
						if( notNullNotEmptyString(profileID)  )
						{
							int idValue = 0;
							try
							{
								idValue = Integer.parseInt(spUser.getJuvServProviderProfileId() ) ;
								if( idValue == myEv.getServiceProviderId() )
								{
									if( nullOrEmptyString(myPrintBean.getServiceProviderName()) )
									{
										myPrintBean.setServiceProviderName(myEv.getServiceProviderName());
									}
									myPrintBean.addEvent(myEv);
								}
							}
							catch( NumberFormatException nfe )
							{
							}
						}
					}
				}  // while 
			}

			if( !myPrintBean.isServiceProvider() && notNullAndNotEmptyList(myDocketEvents) )
			{
				Iterator<DocketEventResponseEvent> iterDoc = myDocketEvents.iterator();
				while( iterDoc.hasNext() )
				{
					DocketEventResponseEvent myEv = iterDoc.next();
					myPrintBean.addEvent(myEv);
				}
			}

			/*
			 * Commented to enable the print function if there are only Docket events without calendar events
			if( myPrintBean.isServiceProvider() && nullOrEmptyList(myPrintBean.getCalendarEvents()) )
			{
				sendToErrorPage(aRequest, "error.generic", 
						"No Future Active Juvenile Events For This Service Provider Found");
				return forward;
			}*/
			
			//<Kishore> JIMS200058252 : New print template for Juvenile Future Events (KK)
			// Group all the events by the date to print the events as per the new template
			// Here the list is an arraylist containing myPrintBean objects which 
			// inturn have an eventdate and events list for that date
			long javaStartTime = System.currentTimeMillis();
			if(notNullAndNotEmptyList(myPrintBean.getDocketEvents()))
			{
				groupSimilarDocketEvents(myPrintBean);
			}
			
			if(notNullAndNotEmptyList(myPrintBean.getCalendarEvents()) && 
					notNullAndNotEmptyList(myPrintBean.getDocketEvents()))
			{
				groupAllEventsByDate(myPrintBean);
			}
			else if(notNullAndNotEmptyList(myPrintBean.getCalendarEvents()))
			{
				groupCalendarEventsByDate(myPrintBean);
			}
			else if(notNullAndNotEmptyList(myPrintBean.getDocketEvents()))
			{
				groupDocketEventsByDate(myPrintBean);
			}
			
			long javaEndTime = System.currentTimeMillis();
			long elapsed = javaEndTime - javaStartTime;
			log.info("TOTAL TIME TOOK TO PRINT THE EVENTS SCHEDULE : " +elapsed);
			
			aRequest.getSession().setAttribute("reportInfo", myPrintBean);
			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SCHEDULE_EVENTS_LIST);
			
			/*CompositeResponse myResp = sendPrintRequest("REPORTING::SCHEDULE_EVENTS_LIST", myPrintBean, null);
			try
			{
				setPrintContentResp(aResponse, myResp, "ScheduledEvents", UIConstants.PRINT_AS_PDF_DOC);
			}
			catch( Exception e )
			{
				sendToErrorPage(aRequest, "error.generic", "Error while trying to print Scheduled Events List");
			}*/
		}

		return null;
	}

	/*
	 * 
	 */
	private void groupSimilarDocketEvents(CalendarSchedulePrintBean myPrintBean) 
	{
		// If a juvenile is scheduled for multiple docket events on the same date,time & court, then each associated
		// Petition number & allegation should be displayed as a single field delimited by semi colons
		if(notNullAndNotEmptyList(myPrintBean.getDocketEvents()))
		{
			List events = new ArrayList();
			CalendarPrintObj prevDocObj = new CalendarPrintObj();
			
			int iter = 0;
			List<CalendarPrintObj> docketList = myPrintBean.getDocketEvents() ;
			for( CalendarPrintObj currDocObj : docketList )
			{

				if(StringUtils.equalsIgnoreCase(currDocObj.getCourtDate(), prevDocObj.getCourtDate()) &&
						StringUtils.equalsIgnoreCase(currDocObj.getCourtTime(), prevDocObj.getCourtTime()) &&
						StringUtils.equalsIgnoreCase(currDocObj.getCourtNum(), prevDocObj.getCourtNum()) )
				{
					if(StringUtils.isEmpty(prevDocObj.getPetitionNum()))
					{
						currDocObj.setPetitionNum(currDocObj.getPetitionNum());
					}
					else if(StringUtils.isEmpty(currDocObj.getPetitionNum()))
					{
						currDocObj.setPetitionNum(prevDocObj.getPetitionNum());
					}
					else
					{
						currDocObj.setPetitionNum(prevDocObj.getPetitionNum() +";" +currDocObj.getPetitionNum());
					}
					
					if(StringUtils.isEmpty(prevDocObj.getPetitionAllegation()))
					{
						currDocObj.setPetitionAllegation(currDocObj.getPetitionAllegation());
					}
					else if(StringUtils.isEmpty(currDocObj.getPetitionAllegation()))
					{
						currDocObj.setPetitionAllegation(prevDocObj.getPetitionAllegation());
					}
					else
					{
						currDocObj.setPetitionAllegation(prevDocObj.getPetitionAllegation() +";"+currDocObj.getPetitionAllegation());
					}

					if(iter == 1)
					{
						events = new ArrayList();
					}
					else
					{
						events.remove(prevDocObj);
					}
					
					events.add(currDocObj);
				}
				else
				{
					events.add(currDocObj);
				}
				
				prevDocObj = currDocObj;
				iter ++;
			}
			myPrintBean.setDocketEvents(events);
		}
	}

	/*
	 * 
	 */
	private void groupAllEventsByDate(CalendarSchedulePrintBean myPrintBean) 
	{
		String calDate = null;
		List events = new ArrayList();
		
		if(myPrintBean.getAllEvents() == null || 
				myPrintBean.getAllEvents().isEmpty())
		{
			myPrintBean.setAllEvents(new ArrayList());
		}

		List<CalendarPrintObj> calEventList = myPrintBean.getCalendarEvents() ;
		for(CalendarPrintObj calObj : calEventList)
		{
			if(calDate == null)
			{
				events.add(calObj);
			}
			else if(calDate.equalsIgnoreCase(calObj.getCalEventDate()))
			{
				events.add(calObj);
			}
			else
			{
				// Add all Docket events for a date
				List<CalendarPrintObj> docketList = myPrintBean.getDocketEvents() ;
				for(CalendarPrintObj docObj : docketList )
				{
					if(calDate != null && calDate.equalsIgnoreCase(docObj.getCourtDate()))
					{
						events.add(docObj);
					}
				}
				
				CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
				printBean.setAllEvents(events);
				printBean.setEventsDate(calDate);
				myPrintBean.getAllEvents().add(printBean);
				events = new ArrayList();
				events.add(calObj);
			}
			calDate = calObj.getCalEventDate();
		} // foreach

		// Add the last object in the above iterations if not added
		if(!events.isEmpty())
		{
			List<CalendarPrintObj> docketList = myPrintBean.getDocketEvents() ;
			for(CalendarPrintObj docObj : docketList)
			{
				if(calDate != null && calDate.equalsIgnoreCase(docObj.getCourtDate()))
				{
					events.add(docObj);
				}
			}
			
			CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
			printBean.setAllEvents(events);
			printBean.setEventsDate(calDate);
			myPrintBean.getAllEvents().add(printBean);
		}

		// Add the docket events that were not matched with calendar event
		// Here we prepare new list of myPrintBean objects with all the events
		/*List finalList = new ArrayList();
		if( notNullAndNotEmptyList(myPrintBean.getAllEvents()) && 
				notNullAndNotEmptyList(myPrintBean.getDocketEvents()))
		{
			List<CalendarPrintObj> docketEvents = myPrintBean.getDocketEvents() ;
			for(CalendarPrintObj docObj : docketEvents )
			{
				if( ! find(docObj.getCourtDate(), myPrintBean.getAllEvents()) )
				{
					finalList = addToList(docObj,myPrintBean.getAllEvents());
					myPrintBean.setAllEvents(finalList);
				}
			}
		}
		myPrintBean.setAllEvents(finalList);*/
	}
	
	/*
	 * 
	 */
	private List addToList(CalendarPrintObj value, List<CalendarSchedulePrintBean> valueList)
	{
		List finalList = new ArrayList();
		Date valueAsDate = DateUtil.stringToDate(value.getCourtDate(), "MM/dd/yyyy");
		boolean added = false;
		
		for( CalendarSchedulePrintBean obj : valueList)
		{
			Date objDate = DateUtil.stringToDate(obj.getEventsDate(), "MM/dd/yyyy");
			
			if(valueAsDate.after(objDate) || 
					value.getCourtDate().equalsIgnoreCase(obj.getEventsDate()))
			{
				finalList.add(obj);
			}
			else if(valueAsDate.before(objDate))
			{
				if(!added)
				{
					CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
					List events = new ArrayList();
					events.add(value);
					printBean.setAllEvents(events);
					printBean.setEventsDate(value.getCourtDate());
					finalList.add(printBean);
					added = true;
				}
				finalList.add(obj);
			}
		}
		
		if(!added)
		{
			CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
			List events = new ArrayList();
			events.add(value);
			printBean.setAllEvents(events);
			printBean.setEventsDate(value.getCourtDate());
			finalList.add(printBean);
		}
		
		return finalList;
	}
	
	/*
	 * 
	 */
	private boolean find(String value, List valueList){
		if(notNullAndNotEmptyList(valueList) && value != null){
			for(Object valObj:valueList){
				CalendarSchedulePrintBean obj = (CalendarSchedulePrintBean)valObj;
				if(value.equalsIgnoreCase(obj.getEventsDate())){
					return true;				
				}
			}
		}
		return false;
	}

	/*
	 * 
	 */
	private void groupCalendarEventsByDate(CalendarSchedulePrintBean myPrintBean) 
	{
		String calDate = null;
		List events = new ArrayList();
		
		if(myPrintBean.getAllEvents() == null || 
				myPrintBean.getAllEvents().isEmpty())
		{
			myPrintBean.setAllEvents(new ArrayList());
		}

		List<CalendarPrintObj> calEventList = myPrintBean.getCalendarEvents() ;
		for(CalendarPrintObj calObj : calEventList)
		{
			if(calDate == null)
			{
				events.add(calObj);
			}
			else if(calDate.equalsIgnoreCase(calObj.getCalEventDate()))
			{
				events.add(calObj);
			}
			else
			{
				CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
				printBean.setAllEvents(events);
				printBean.setEventsDate(calDate);
				myPrintBean.getAllEvents().add(printBean);
				// collect the events list for the next date
				events = new ArrayList();
				events.add(calObj);
			}
			calDate = calObj.getCalEventDate();
		}
		
		if(!events.isEmpty())
		{
			CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
			printBean.setAllEvents(events);
			printBean.setEventsDate(calDate);
			myPrintBean.getAllEvents().add(printBean);
		}
	}

	/*
	 * 
	 */
	private void groupDocketEventsByDate(CalendarSchedulePrintBean myPrintBean) 
	{
		String courtDate = null;
		List events = new ArrayList();

		if(myPrintBean.getAllEvents() == null || myPrintBean.getAllEvents().isEmpty())
		{
			myPrintBean.setAllEvents(new ArrayList());
		}

		List<CalendarPrintObj> docketList = myPrintBean.getDocketEvents() ;
		for( CalendarPrintObj docObj : docketList)
		{
			if(courtDate == null)
			{
				events.add(docObj);
			}
			else if(courtDate.equalsIgnoreCase(docObj.getCourtDate()))
			{
				events.add(docObj);
			}
			else
			{
				CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
				printBean.setAllEvents(events);
				printBean.setEventsDate(courtDate);
				myPrintBean.getAllEvents().add(printBean);
				events = new ArrayList();
				events.add(docObj);
			}
			courtDate = docObj.getCourtDate();
		}
		
		if(!events.isEmpty())
		{
			CalendarSchedulePrintBean printBean = new CalendarSchedulePrintBean();
			printBean.setAllEvents(events);
			printBean.setEventsDate(courtDate);
			myPrintBean.getAllEvents().add(printBean);
		}
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward refreshFields(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm;
		form.getSearchEvent().clear();
		form.getSearchEvent().setSearchType( UIConstants.EMPTY_STRING );
		
		if( form.getDocketEvents() != null )
		{
			form.getDocketEvents().clear();
		}
		
		if( form.getIndividualDayEvents() != null )
		{
			form.getIndividualDayEvents().clear();
		}

		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward searchCalendar(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		HttpSession session = aRequest.getSession();

		ScheduleNewEventForm oldForm = (ScheduleNewEventForm)session.getAttribute("scheduleNewEventForm");
		if( oldForm == null )
		{
			return null;
		}

		CalendarEventListForm form = (CalendarEventListForm)aForm;

		String serviceProviderId = SecurityUIHelper.getServiceProviderId();
		if( serviceProviderId != null )
		{
			form.setServiceProviderId(serviceProviderId);
		}

		form.setCalendarType(oldForm.getCalendarType());
		form.getSearchEvent().clear();
		form.getSearchEvent().setSearchType( UIConstants.EMPTY_STRING );

		form.getSearchEvent().setJuvUnitList(ComplexCodeTableHelper.getActiveJuvenileLocationUnits());
		form.getSearchEvent().setServiceTypePermList(ComplexCodeTableHelper.getAllServiceEventTypes());

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		form.getSearchEvent().setStartDateStr(DateUtil.dateToString(cal.getTime(), UIConstants.DATE_FMT_1));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		form.getSearchEvent().setEndDateStr(DateUtil.dateToString(cal.getTime(), UIConstants.DATE_FMT_1));

		if( form.getCalendarType().equals(PDCalendarConstants.CALENDAR_TYPE_PROVIDER) )
		{
			GetServiceProviderEvent getServiceProviderEvent = (GetServiceProviderEvent)
					EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);

			getServiceProviderEvent.setServiceProviderId(oldForm.getServiceProviderId());
			CompositeResponse response = MessageUtil.postRequest(getServiceProviderEvent);

			JuvenileServiceProviderResponseEvent juvResp = (JuvenileServiceProviderResponseEvent)
					MessageUtil.filterComposite(response, JuvenileServiceProviderResponseEvent.class);
			if( juvResp != null )
			{
				form.setServiceProviderName(juvResp.getServiceProviderName());
				form.getSearchEvent().setServiceProviderName(juvResp.getServiceProviderName());
				form.getSearchEvent().setServiceProviderId(juvResp.getServiceProviderId());
			}
		}
		else
		{
			List temp1 = new ArrayList();
			List temp2 = ComplexCodeTableHelper.getServiceProvidersWithPrograms();
			for (int x=0; x<temp2.size(); x++)
			{
				ServiceProviderResponseEvent spre = (ServiceProviderResponseEvent)temp2.get(x);
				if (spre.getJuvServProviderName().indexOf("MIGRATED") == -1 )
				{
					temp1.add(spre);
				}
			}
			form.getSearchEvent().setServiceProviderList(temp1);
			temp1 = null;
			temp2 = null;
//			form.getSearchEvent().setServiceProviderList(ComplexCodeTableHelper.getServiceProvidersWithPrograms());
		}

		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}

	/*
	 * given a string, returns true if that string is not 
	 * null and contains one or more characters
	 * 
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString(String str)
	{
		return(str != null && str.trim().length() > 0);
	}

	/*
	 * @param tList
	 * @return
	 */
	private boolean notNullAndNotEmptyList(List tList)
	{
		return(tList != null && (tList.size() > 0));
	}

	/*
	 * @param tList
	 * @return
	 */
	private boolean nullOrEmptyList(List tList)
	{
		return(tList == null || (tList.size() < 1));
	}

	/*
	 * @param str
	 * @return
	 */
	private boolean nullOrEmptyString(String str)
	{
		return(str == null || str.equals(""));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.displayViewCalendarEventList", "displayViewCalendarEventList");
		keyMap.put("button.searchCalendarOptions", "searchCalendar");
		keyMap.put("button.generateUpdatedSchedule", "generateSchedule");
		keyMap.put("button.cancel", "refreshFields");
	}
}
