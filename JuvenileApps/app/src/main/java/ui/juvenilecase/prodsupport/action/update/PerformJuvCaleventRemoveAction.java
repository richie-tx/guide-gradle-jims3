package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.productionsupport.DeleteProductionSupportCalendarEventsEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

public class PerformJuvCaleventRemoveAction  extends Action
{
    	private Logger log = Logger.getLogger("PerformJuvCaleventRemoveAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    ProdSupportForm regform = (ProdSupportForm) form;
	    
	    String 	serviceEventId 	= regform.getServeventId();
	    String	juvenileId	= regform.getJuvenileId();
	    Integer	calEventId	= Integer. parseInt( regform.getCalEventId() );
	   
	    
 	    if ( juvenileId != null
		    && serviceEventId != null
		    && calEventId != null ) {
		DeleteProductionSupportCalendarEventsEvent deleteCalendarEvent =
			(DeleteProductionSupportCalendarEventsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTCALENDAREVENTS);
		deleteCalendarEvent.setCalendarEventId(calEventId);
		deleteCalendarEvent.setServiceEventId(serviceEventId);
		deleteCalendarEvent.setJuvenileId(juvenileId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(deleteCalendarEvent);
		boolean isRemoved = isJuvenileRemoved(juvenileId, calEventId );
		if( !isRemoved ) {
		    regform.setMsg("Error - The juvenile  was not removed from calendar event. PerformJuvCaleventRemoveAction.java");
		    return mapping.findForward("error");
		}
		
		log.info("Removed juvenile " + juvenileId + " from calendar event " + calEventId  + " LogonId: " + SecurityUIHelper.getLogonId());
		regform.setMsg("");
		return mapping.findForward("success");
	    } else {				
		regform.setMsg("Error - The juvenile  was not removed from calendar event. PerformJuvCaleventRemoveAction.java");
		return mapping.findForward("error");
	    }	
	    
	    	
	   
	}
	
	private boolean isJuvenileRemoved( String juvenileId,
						Integer	calEventId ){
	    boolean isRemoved = true;
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    GetProductionSupportCalendarEventContextEvent getCalendarEventContextsEvent = (GetProductionSupportCalendarEventContextEvent)
	    EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDAREVENTCONTEXT);
	    getCalendarEventContextsEvent.setCalendarEventId(calEventId);
	    dispatch.postEvent(getCalendarEventContextsEvent);
	    CompositeResponse calendarEventContextResponse = (CompositeResponse) dispatch.getReply();
	    Map calendarEventContextResponseMap = MessageUtil.groupByTopic(calendarEventContextResponse);
	    MessageUtil.processReturnException(calendarEventContextResponseMap);
	    Collection calendarContextColl = MessageUtil.compositeToCollection(calendarEventContextResponse, CalendarEventContextResponse.class);
	    ArrayList<CalendarEventContextResponse> calendarContextList = new ArrayList<CalendarEventContextResponse>();
	    calendarContextList.addAll(calendarContextColl);
			
	    for (CalendarEventContextResponse calContext :  calendarContextList ) {
		if ( calContext != null 
			&& juvenileId.equals(calContext.getJuvenileId())) {
		    isRemoved = false;
		    break;
		}
	    }
	    
	    return isRemoved;
	    
	}
	
}
