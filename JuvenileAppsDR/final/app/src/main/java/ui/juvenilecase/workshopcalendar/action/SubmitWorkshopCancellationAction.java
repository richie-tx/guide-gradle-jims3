//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\workshopcalendar\\action\\DisplayServiceEventDetailsAction.java

package ui.juvenilecase.workshopcalendar.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetProgramReferralServiceEventsByProgRefIdEvent;
import messaging.calendar.GetProgramReferralsByServiceEventIdEvent;
import messaging.calendar.GetServiceEventsByServiceEventIdEvent;
import messaging.calendar.SaveServiceEventCancellationEvent;
import messaging.calendar.SendProgramReferralOrphanedNotificationEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ProgramReferralsByServiceEventResponseEvent;
import messaging.calendar.reply.ServiceAttendanceContextResponseEvent;
import messaging.calendar.reply.ServiceEventCancellationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;

public class SubmitWorkshopCancellationAction extends LookupDispatchAction
{

	/**
	 * @roseuid 44805C380387
	 */
	public SubmitWorkshopCancellationAction()
	{
		
	}
	
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
		
	public ActionForward finish(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm) aForm;
			//<KISHORE>JIMS200060339 : MJCW - Cancelling SP Events Isn't Sending Notices Correctly
			List events = getEventsToSendNotifications(detailsForm.getServiceEventId());
	   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    	SaveServiceEventCancellationEvent saveCancellationEvent = (SaveServiceEventCancellationEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.SAVESERVICEEVENTCANCELLATION);
	     	saveCancellationEvent.setServiceEventId(detailsForm.getEventId());
	    	dispatch.postEvent(saveCancellationEvent);						
	    	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	  		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	  		MessageUtil.processReturnException(dataMap);
			UISupervisionCalendarHelper.sendCancellationNotifications(detailsForm,events);
			sendProgramReferralOrphanedNotification(detailsForm.getEventId());
			detailsForm.setEventStatusCd(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED);
			detailsForm.setEventStatus(SimpleCodeTableHelper.getDescrByCode("SERVICE_EVENT_STATUS",PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED));
			detailsForm.setAction("cancellationConfirm");
			return aMapping.findForward(UIConstants.CONFIRM);			
		}
	
	/*
	 * ServiceEventId
	 * @return ServiceEvents List
	 */
	private static List getEventsToSendNotifications(String serviceEventId) {
		if(StringUtils.isNotEmpty(serviceEventId)){
			GetServiceEventsByServiceEventIdEvent gse = (GetServiceEventsByServiceEventIdEvent) EventFactory
			.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTSBYSERVICEEVENTID);
			gse.setServiceEventId(Integer.valueOf(serviceEventId));
			List events = MessageUtil.postRequestListFilter(gse, ServiceAttendanceContextResponseEvent.class);
			List activeEvents = new ArrayList();
			if(events != null & !events.isEmpty()){
				Iterator iter = events.iterator();
				while(iter.hasNext()){
					ServiceAttendanceContextResponseEvent resp = (ServiceAttendanceContextResponseEvent)iter.next();
					//Only JPOs of the juveniles who haven't cancelled their events should get the notices
					if(!PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED.equalsIgnoreCase(resp.getAttendanceStatusCd())){
						activeEvents.add(resp);
					}
				}
			}
			return activeEvents;
		}
		return null;
	}

	public void sendProgramReferralOrphanedNotification(String serviceEventId) {
		boolean hasFutureEvent = false;
		GetProgramReferralsByServiceEventIdEvent getProgramReferrals = (GetProgramReferralsByServiceEventIdEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENTID);
		getProgramReferrals.setServiceEventId(serviceEventId);	
		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(getProgramReferrals);
		List programReferrals = 
			(List)MessageUtil.compositeToCollection(compositeResponse,ProgramReferralsByServiceEventResponseEvent.class);
		if (programReferrals!=null){
			Iterator referrals = programReferrals.iterator(); 
			while (referrals.hasNext()) {
				ProgramReferralsByServiceEventResponseEvent ref = (ProgramReferralsByServiceEventResponseEvent) referrals.next();
				GetProgramReferralServiceEventsByProgRefIdEvent gprs = (GetProgramReferralServiceEventsByProgRefIdEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTSBYPROGREFID);
				gprs.setProgramReferralId(ref.getProgramReferralId());
				compositeResponse = (CompositeResponse) MessageUtil.postRequest(gprs);
				List calendarEvents = 
					(List)MessageUtil.compositeToCollection(compositeResponse,CalendarServiceEventResponseEvent.class);
				if (calendarEvents!=null){
					Iterator events = calendarEvents.iterator(); 
					while (events.hasNext()) {
						CalendarServiceEventResponseEvent serv = (CalendarServiceEventResponseEvent) events.next();
						String eventStatus = serv.getEventStatusCode();
						if (eventStatus.equals("AV") || eventStatus.equals("PN") || eventStatus.equals("SC")) {
							Date eventDate = serv.getEventDate(); 
							if (eventDate.after(DateUtil.getCurrentDate())) {
								hasFutureEvent = true;
							}
						}					
					}
					if (!hasFutureEvent) {
						SendProgramReferralOrphanedNotificationEvent sendNotice = (SendProgramReferralOrphanedNotificationEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.SENDPROGRAMREFERRALORPHANEDNOTIFICATION);
						sendNotice.setProgramReferralId(ref.getProgramReferralId());
						compositeResponse = (CompositeResponse) MessageUtil.postRequest(sendNotice);
					}
				}
			}
		}
	}
	
	public ActionForward viewGuardianInfo(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm) aForm;
			String juvenileId = aRequest.getParameter("juvenileId");
			if (juvenileId!=null){
				Iterator iter = detailsForm.getCancellationList().iterator();
				while (iter.hasNext()){
					ServiceEventCancellationResponseEvent sre = (ServiceEventCancellationResponseEvent)iter.next();
					if (sre.getJuvenileId().equals(juvenileId)){
						detailsForm.setCurrentCancellationEvent(sre);
					}
				}			
			}	
			return aMapping.findForward(UIConstants.SUCCESS);			
		}
	
	public ActionForward generateCancellationList(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm) aForm;
			UISupervisionCalendarHelper.printCancellationListBFO(detailsForm, aRequest, aResponse);
			//UISupervisionCalendarHelper.printCancellationList(detailsForm,aResponse);
			return null;			
		}
	

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.returnToEventList", "cancel");	
		buttonMap.put("button.generateCancellationList", "generateCancellationList");
		buttonMap.put("button.viewGuardianInfo", "viewGuardianInfo");
		
		return buttonMap;
	}
}