//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\workshopcalendar\\action\\DisplayServiceEventDetailsAction.java

package ui.juvenilecase.workshopcalendar.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.calendar.SaveJuvenileAttendanceEvent;
import messaging.calendar.SaveServiceEventAttendanceEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;

public class SubmitWorkshopAttendanceAction extends LookupDispatchAction
{
	/**
	 * @roseuid 44805C380387
	 */
	public SubmitWorkshopAttendanceAction()
	{
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm;
		
		detailsForm.setSecondaryAction("");
		detailsForm.setAction("attendanceUpdate");

		return( aMapping.findForward(UIConstants.BACK) );
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * 
	 */
	public ActionForward viewProgressNotes(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String juvenileId = aRequest.getParameter("juvenileId");
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm;

		boolean exists = false;
		Collection<ServiceEventAttendanceResponseEvent> existingAttendanceEvent = UISupervisionCalendarHelper.getServiceAttendEventList(detailsForm.getEventId());
		Iterator<ServiceEventAttendanceResponseEvent> iter = existingAttendanceEvent.iterator();
		while(iter != null && iter.hasNext()  &&  !exists )
		{
			ServiceEventAttendanceResponseEvent ev = iter.next();
			if( ev.getJuvenileId().equals(juvenileId) )
			{
			        DisplayWorkshopAttendanceAction a=new DisplayWorkshopAttendanceAction();			        
			        String pNotes2 = a.getFormattedProgressNote(ev, "update");
				ev.setExistingProgressNotes(pNotes2.toString());
				ev.setProgressNotes(pNotes2.toString());			        
			       
				detailsForm.setCurrentAttendanceEvent(ev);
				exists = true;
			}
		}
		
		if( !exists )
		{
			iter = existingAttendanceEvent.iterator(); 	//detailsForm.getNewAttendanceEvents().iterator();
			while(iter != null && iter.hasNext() && !exists )
			{
				ServiceEventAttendanceResponseEvent ev = iter.next();
				if( ev.getJuvenileId().equals(juvenileId) )
				{
					detailsForm.setCurrentAttendanceEvent(ev);
					exists = true;
				}
			}
		}
		
		detailsForm.setSecondaryAction("viewProgressNotes");

		return aMapping.findForward(UIConstants.VIEW);
	}
	
	/*
	 * 
	 */
	public ActionForward viewMonthlySummary(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String juvenileId = aRequest.getParameter("juvenileId");
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm;

		boolean exists = false;
		Iterator<ServiceEventAttendanceResponseEvent> iter = 
				detailsForm.getExistingAttendanceEvents().iterator();
		while( iter.hasNext()  &&  !exists )
		{
			ServiceEventAttendanceResponseEvent ev = iter.next();
			if( ev.getJuvenileId().equals(juvenileId) )
			{
				detailsForm.setCurrentAttendanceEvent(ev);
				exists = true;
			}
		}
		
		if( !exists )
		{
			iter = detailsForm.getNewAttendanceEvents().iterator();
			while( iter.hasNext() && !exists )
			{
				ServiceEventAttendanceResponseEvent ev = iter.next();
				if( ev.getJuvenileId().equals(juvenileId) )
				{
					detailsForm.setCurrentAttendanceEvent(ev);
					exists = true;
				}
			}
		}
		
		detailsForm.setSecondaryAction("viewMonthlySummary");

		return aMapping.findForward(UIConstants.VIEW);
	}

	/*
	 * 
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm;
		SaveServiceEventAttendanceEvent saveAttendanceEvent = (SaveServiceEventAttendanceEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.SAVESERVICEEVENTATTENDANCE);
		
		saveAttendanceEvent.setServiceEventId(detailsForm.getEventId());
		saveAttendanceEvent.setEventCategory(UIConstants.PRESCHEDULED_SERVICE_TYPE);
		saveAttendanceEvent.setEventStartDate(detailsForm.getEventDate());		
		//pass currentevent
		saveAttendanceEvent.setCurrentEvent(detailsForm.getCurrentEvent());
		ArrayList juvenileAttendanceEvents = new ArrayList();
		String additionalAttendeeCount = "" ;
		
		Iterator<ServiceEventAttendanceResponseEvent> iter = detailsForm.getNewAttendanceEvents().iterator();
		while( iter.hasNext() )
		{
			ServiceEventAttendanceResponseEvent ev = iter.next();
			if( ev.getAttendanceStatusCd() != null && 
					!ev.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) && 
					!ev.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED) )
			{
				SaveJuvenileAttendanceEvent juvEvent = new SaveJuvenileAttendanceEvent();
				juvEvent.setJuvenileId(ev.getJuvenileId());
				juvEvent.setAttendanceStatusCd(ev.getAttendanceStatusCd());
				
				juvEvent.setProgressNotes(ev.getExistingProgressNotes());
				ev.setProgressNotes(ev.getExistingProgressNotes());
				
				if( ev.getAttendanceStatusCd().equalsIgnoreCase( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED ) ||
						ev.getAttendanceStatusCd().equalsIgnoreCase( PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT ))
				{  // Excused/Absent, so clear out these values
					juvEvent.setAddlAttendees( 0 ) ;
					juvEvent.getAddlAttendeeNames().clear() ;
				}
				else
				{
					additionalAttendeeCount = ev.getAddlAttendees().trim();
					if( additionalAttendeeCount != null && (additionalAttendeeCount.length() > 0) )
					{
						try
						{
							juvEvent.setAddlAttendees( Integer.parseInt(additionalAttendeeCount) );
						}
						catch( NumberFormatException nfe )
						{
							juvEvent.setAddlAttendees( 0 ) ;
						}
					}
				}

				juvenileAttendanceEvents.add(juvEvent);
			}
		} // while

		saveAttendanceEvent.setJuvenileAttendanceEvents(juvenileAttendanceEvents);
		CompositeResponse compositeResponse = MessageUtil.postRequest(saveAttendanceEvent);

		CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)
				MessageUtil.filterComposite(compositeResponse, CalendarServiceEventResponseEvent.class);
		if( resp != null )
		{
			detailsForm.setEventStatus(resp.getEventStatus());
			detailsForm.setEventStatusCd(resp.getEventStatusCode());
			detailsForm.setTotalScheduled(resp.getCurrentEnrollment());
		}

		UISupervisionCalendarHelper.sendAttendanceNotification(detailsForm);
		detailsForm.setAction("attendanceConfirm");
		detailsForm.setSecondaryAction("");
		
		return aMapping.findForward(UIConstants.CONFIRM);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewAttendance(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
  {
		ServiceEventDetailsForm sedForm = (ServiceEventDetailsForm) aForm;

		sedForm.setSecondaryAction("viewAttendees");
		String juvenileId = aRequest.getParameter( "juvenileId") ;
		String listInd = aRequest.getParameter( "listInd") ;
		sedForm.setJuvenileNum(juvenileId);
		sedForm.setAttendeeNamesStr("");
		Collection<ServiceEventAttendanceResponseEvent> svcEventList = new ArrayList();
		if (listInd.equalsIgnoreCase("new"))
		{	
			svcEventList = sedForm.getNewAttendanceEvents() ;
		} else {
			svcEventList = sedForm.getExistingAttendanceEvents() ;
		}
		for( ServiceEventAttendanceResponseEvent ev : svcEventList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				StringBuffer sb = new StringBuffer();
				String prefix = "";
				List names = (List) ev.getAddlAttendeeNames();
				for (int x = 0; x<names.size(); x++){
					AttendeeNameResponseEvent anre = (AttendeeNameResponseEvent) names.get(x);
					sb.append(prefix);
					sb.append(anre.getFormattedName());
					prefix = "; ";	
				}	
				sedForm.setAttendeeNamesStr(sb.toString());
				break ;
			}
		}
		
		return aMapping.findForward(UIConstants.VIEW);

  }	
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.returnToCalendar", "cancel");
		buttonMap.put("button.viewProgressNotes", "viewProgressNotes");
		buttonMap.put("button.viewMonthlySummary", "viewMonthlySummary");
		buttonMap.put("button.viewAttendees","viewAttendance");
		return buttonMap;
	}
}