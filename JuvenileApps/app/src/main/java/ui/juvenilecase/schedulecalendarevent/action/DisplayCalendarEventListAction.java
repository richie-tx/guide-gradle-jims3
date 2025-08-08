package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.reply.CalendarEventResponse;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.schedulecalendarevent.InvolvedWeaponOffenseBean;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;

public class DisplayCalendarEventListAction extends LookupDispatchAction
{
	/**
	 * @roseuid 4576E78400F1
	 */
	public DisplayCalendarEventListAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward displayCalendarEventList(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm;

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

		String officerId = aRequest.getParameter("officerId");
		String juvenileNum = aRequest.getParameter("juvenileNum");

		form.setOfficerId(officerId);
		form.setJuvenileNum(juvenileNum);

		//Collection contexts = new ArrayList();
		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		
		if( notNullNotEmptyString( officerId ) )
		{
			calendarContextEvent.setProbationOfficerId(officerId);
			form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JPO);
		}
		else if( notNullNotEmptyString( juvenileNum ) )
		{
			calendarContextEvent.setJuvenileId(juvenileNum);
			form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JUVENILE);
		}
		gce.setCalendarContextEvent(calendarContextEvent);
		gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));

		List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);

		if( events.size() > 1 )
		{
			Collections.sort(events);
		}

		List wrkList = events;
		InvolvedWeaponOffenseBean iwo = new InvolvedWeaponOffenseBean();
		String desc = "";
		for (int x=0; x<wrkList.size(); x++){
			List wpList = new ArrayList();
			CalendarServiceEventResponseEvent csresp = (CalendarServiceEventResponseEvent)  wrkList.get(x);
			if ("SAN".equals(csresp.getEventTypeCode() ) ) {
// weaponDesc field is delimited by | for each element row and by ^ for each value within the element			
//	example: "1020^Felony^OFFENSE DESCRIPTION 1^Y^FIREARM|1010^Misdemeanor^OFFENSE DESCRIPTION 2^N|";
				desc =  csresp.getWeaponDescs();
				if (desc != null && desc.indexOf("|") > 1 ){
					String weaponDescs[] = desc.split("\\|");
					for (int w=0; w<weaponDescs.length; w++){
						iwo = new InvolvedWeaponOffenseBean();
						desc = weaponDescs[w];
						String weaponDesc[] = desc.split("\\^");
						if (weaponDesc.length > 3) {
							iwo.setReferralNumber(weaponDesc[0]);
							iwo.setOffenseCategoryDescription(weaponDesc[1]);
							iwo.setOffenseDescription(weaponDesc[2]);
							iwo.setWeaponInvolvedStr(weaponDesc[3]);
						}
						iwo.setTypeOfWeaponDescription("");
						if (weaponDesc.length == 5){
							iwo.setTypeOfWeaponDescription(weaponDesc[4]);
						}
						wpList.add(iwo);
					}
				}	
			}
			csresp.setOffenseInvolvedWeaponList(wpList);
		}
		events = new ArrayList(wrkList);
		form.setDayEvents(events);
		wrkList = null;
		
		List docketEvents = this.getTodaysDocketEvents(aRequest, form);
		if (docketEvents != null)
		{
			form.setDocketEvents(docketEvents);
		}

		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	/* given a string, returns true if that string
	 * is not null and contains one or more characters
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && str.trim().length() > 0 ) ;	
	}
	
	/*
	 * 
	 * @param aRequest
	 * @param form
	 * @return
	 */
	private List getTodaysDocketEvents(HttpServletRequest aRequest, CalendarEventListForm form)
	{
		List docketEvents = new ArrayList();

		HttpSession session = aRequest.getSession();
		Collection<CalendarEventResponse> calendarList = (Collection)session.getAttribute(form.getCalendarType());
		if( calendarList != null )
		{
			Calendar requestedDate = Calendar.getInstance();
			requestedDate.setTime(form.getEventDate());
			requestedDate.set(Calendar.HOUR_OF_DAY, 0);
			requestedDate.set(Calendar.MINUTE, 0);
			requestedDate.set(Calendar.SECOND, 0);
			requestedDate.set(Calendar.MILLISECOND, 0);
			
			// filter events only for the requested date
			for( CalendarEventResponse event: calendarList )
			{
				if( event instanceof DocketEventResponseEvent )
				{
					Calendar eventDate = Calendar.getInstance();
					eventDate.setTime(event.getStartDatetime());
					eventDate.set(Calendar.HOUR_OF_DAY, 0);
					eventDate.set(Calendar.MINUTE, 0);
					eventDate.set(Calendar.SECOND, 0);
					eventDate.set(Calendar.MILLISECOND, 0);

					if( eventDate.equals(requestedDate) )
					{
						docketEvents.add(event);
					}
				}
			}
		}
		
		return docketEvents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.displayCalendarEventList", "displayCalendarEventList");
		return buttonMap;
	}
}
