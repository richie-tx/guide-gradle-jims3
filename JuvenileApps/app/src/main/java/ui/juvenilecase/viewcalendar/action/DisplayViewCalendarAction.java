//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\viewcalendar\\action\\DisplayViewCalendarAction.java

package ui.juvenilecase.viewcalendar.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.calendar.GetCalendarUserTypeEvent;
import messaging.calendar.reply.CalendarUserTypeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
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

import ui.common.Name;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;


public class DisplayViewCalendarAction extends LookupDispatchAction
{
   /**
    * @roseuid 45F1B0BC02DB
    */
   public DisplayViewCalendarAction() 
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
	public ActionForward displayViewCalendar(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;
		form.clear();
		String serviceProviderId = SecurityUIHelper.getServiceProviderId();
		
		String forward = UIConstants.SUCCESS;
		if(serviceProviderId == null){
						
			String loginId = SecurityUIHelper.getJIMSLogonId();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);			
			GetCalendarUserTypeEvent getUTEvent = 
				(GetCalendarUserTypeEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARUSERTYPE);
			getUTEvent.setLogonId(loginId);
			
			dispatch.postEvent(getUTEvent);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
		
			CalendarUserTypeResponseEvent resp =
					(CalendarUserTypeResponseEvent) MessageUtil.filterComposite(compositeResponse, CalendarUserTypeResponseEvent.class);
			
			if(resp == null){
				form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_OTHER);			
				forward = UIConstants.SEARCH_SUCCESS;
			}else if (resp.getCalendarType().equals(PDCalendarConstants.CALENDAR_TYPE_JPO)){
				form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JPO);
				form.setOfficerId(resp.getOfficerId());
				Name name = new Name(resp.getOfficerFirstName(),resp.getOfficerMiddleName(),resp.getOfficerLastName());
				form.setOfficerName(name.getFormattedName());
			}else if (resp.getCalendarType().equals(PDCalendarConstants.CALENDAR_TYPE_CLM)){
				form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_CLM);
				form.setOfficerId(resp.getOfficerId());
				Name name = new Name(resp.getOfficerFirstName(),resp.getOfficerMiddleName(),resp.getOfficerLastName());
				form.setOfficerName(name.getFormattedName());				
			}
		}else{			
			form.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_PROVIDER);
			form.setServiceProviderId(serviceProviderId);
			JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(serviceProviderId);
			String name = "";
			if(resp!=null)
				 form.setProviderName(resp.getServiceProviderName());
		}				
		return aMapping.findForward(forward);
	}
   
  
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();
		buttonMap.put("title.viewCalendar", "displayViewCalendar");
		return buttonMap;
	}
}
