package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;

public class DisplayCalendarSPSelectEventsSummaryAction extends JIMSBaseAction
{
	/**
	 * @roseuid 4576E78400F1
	 */
	public DisplayCalendarSPSelectEventsSummaryAction()
	{
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.details", "details");
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		sneForm.setSelectedEventsList(new ArrayList());
		List workList = new ArrayList();
		for (int x=0; x < sneForm.getSelectedEventIds().length; x++)
		{	
			for (int y=0; y< sneForm.getEventList().size(); y++)
			{	
				CalendarServiceEventResponseEvent csere = (CalendarServiceEventResponseEvent) sneForm.getEventList().get(y); 
				if (csere.getEventId().equals(sneForm.getSelectedEventIds()[x] ))
				{
					workList.add(csere);
					sneForm.setEventType(csere.getEventType()); 
				}
			}
		}
		sneForm.setSelectedEventsList(workList);
		// find program referral details
		sneForm.setReferralFound(false);
		sneForm.setProgramReferralNew(true);
		List activeReferrals = UIProgramReferralHelper.getActiveJuvenileProgramReferrals( sneForm.getJuvenileNum()) ;
		if (activeReferrals != null)
		{
			UIProgramReferralBean aReferral = new UIProgramReferralBean();
			for (int r=0; r < activeReferrals.size(); r++)
			{
				ProgramReferralResponseEvent prre = ( ProgramReferralResponseEvent)activeReferrals.get(r);
				if (sneForm.getProgramId().equals(prre.getProvProgramId()))
				{	
					aReferral.setBeginDateStr(DateUtil.dateToString(prre.getBeginDate(), DateUtil.DATE_FMT_1));
					aReferral.setAssignedHours(prre.getAssignedHours());
					aReferral.setCourtOrdered(prre.isCourtOrdered());
					aReferral.setReferralStatus(prre.getReferralStatusDescription());
					aReferral.setReferralComments(prre.getReferralComments());
					sneForm.setProgramReferral(aReferral);
					sneForm.setReferralFound(true);
					sneForm.setProgramReferralNew(false);
					break;
				}
			}
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward details(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		String eventId = aRequest.getParameter("eventId");
//		String juvenileNum = aRequest.getParameter("currentJuvenileId");
		CalendarEventListForm celForm = (CalendarEventListForm) getSessionForm(aMapping, aRequest,"calendarEventListForm", true);
		celForm.clear();
		celForm.setCalendarType("PRE");
		for(int x=0; x<sneForm.getEventList().size(); x++)
		{	
			CalendarServiceEventResponseEvent xEvent = (CalendarServiceEventResponseEvent) sneForm.getEventList().get(x);
			if (eventId.equals(xEvent.getEventId() ) )
			{
				celForm.setCurrentEvent(xEvent);
				break;
			}
		}
// get attendance data for event
		celForm.setAttendanceStatus("");
		celForm.setAddlAttendees("");
		celForm.setSelectedAttendeeNamesAsString("");
		celForm.setProgressNotes("");
		GetServiceEventAttendanceEvent getServiceEvent =
			(GetServiceEventAttendanceEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
		getServiceEvent.setServiceEventId(eventId);
		ServiceEventAttendanceResponseEvent responseEvent =
				(ServiceEventAttendanceResponseEvent) MessageUtil.postRequest(getServiceEvent, ServiceEventAttendanceResponseEvent.class);
		if( responseEvent != null ) 
		{
			celForm.setProgressNotes(responseEvent.getProgressNotes());
			celForm.setAttendanceStatus(responseEvent.getAttendanceStatusDescription());
			if (responseEvent.getAddlAttendeeNames() != null && responseEvent.getAddlAttendeeNames().size() > 0)
			{
				List attendeeNames = (List) responseEvent.getAddlAttendeeNames();
				String addlNameStr = "";
				celForm.setAddlAttendees(Integer.toString(responseEvent.getAddlAttendeeNames().size() ) );
				
				for (int a = 0; a <attendeeNames.size(); a++)
				{
					AttendeeNameResponseEvent anre = (AttendeeNameResponseEvent) attendeeNames.get(a);
					addlNameStr += anre.getFormattedName();
					if (a < attendeeNames.size() - 1)
					{	
						addlNameStr += ";";
					}
				}
				celForm.setSelectedAttendeeNamesAsString(addlNameStr);
			}
		}
		return( aMapping.findForward(UIConstants.DETAIL_SUCCESS) );
	}
}