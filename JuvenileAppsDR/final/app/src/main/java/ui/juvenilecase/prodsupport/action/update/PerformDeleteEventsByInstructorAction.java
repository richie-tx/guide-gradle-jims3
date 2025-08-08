package ui.juvenilecase.prodsupport.action.update;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCalendarEventsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.security.RegionType;
import pd.supervision.calendar.ServiceEventContext;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformDeleteEventsByInstructorAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.next", "next");
	keyMap.put("button.delete", "delete");
	keyMap.put("button.transfer", "transfer");
	keyMap.put("button.back", "back");
	
    }
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "success";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	
	return aMapping.findForward(forward);
    }
    
    public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward 		= "deleteSuccess";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String serviceEventId	= regform.getServiceEventId();
	StringBuffer message = new StringBuffer();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST); 
	if (regform.getServices() != null 
		&& regform.getServices().size() > 0 ) {
	    message.append("<h2 style='text-align: center;'>Production Support- Event by Instructor Deleted</h2>");
	    message.append("<hr>");
	    message.append("<br>");
	   message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Juvenile Number</font></th>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Instructor Number</font></th>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Instructor Name</font></th>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Service Provider ID</font></th>");
	    message.append("</tr>");
	    for (CalendarServiceEventResponseEvent service : regform.getServices()){
		if ( serviceEventId.equals( service.getServiceEventId() ) ) {
		   
		    Integer caleventId =service.getCalendarEventId();
		    DeleteProductionSupportCalendarEventsEvent deleteCalendarEvent =
				(DeleteProductionSupportCalendarEventsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTCALENDAREVENTS);
		    deleteCalendarEvent.setCalendarEventId(caleventId);
		    dispatch.postEvent(deleteCalendarEvent);
		}
		if (service.getServiceContexts() != null &&
			service.getServiceContexts().size() > 0 ){
		    for (ServiceEventContext serviceContext : service.getServiceContexts() ){
			if ( regform.getServiceEventId().equals( serviceContext.getServiceEventId() ) ) {
        			 message.append("<tr>");
        			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + serviceContext.getJuvenileId()  + "</td>");
        			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + regform.getInstructorId()  + "</td>");
        			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + regform.getInstructor().getLastName()+ "," + regform.getInstructor().getFirstName()   + "</td>");
        			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + regform.getInstructor().getServiceProviderId()  + "</td>");
        			 message.append("</tr>");
			}
			   
		    }  
		}
		
	    }
	   message.append("</table>");
	   message.append("<br>");
	    if (regform.getPrograms() != null
		    && regform.getPrograms().size() > 0 ){
		  message.append("<table>");
		    message.append("<tr>");
		    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Service Provider ID</font></th>");
		    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Program ID</font></th>");
		    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Program Name</font></th>");
		    message.append("</tr>");
		    for (ProviderProgramResponseEvent program : regform.getPrograms() ) {
			 message.append("<tr>");
			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + program.getServiceProviderId() + "</td>");
			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + program.getProviderProgramId()  + "</td>");
			 message.append("<td style=' border-bottom: 1px solid #ddd;'>" + program.getProgramName() + "</td>");
			 message.append("</tr>");
		    }
		message.append("</table>");

	    }
	    sendNotification(message.toString());
	    regform.setMsg("Event by Instructor Successfully Deleted");
	    
	    
	}
	
	return aMapping.findForward(forward);
    }
    
    public ActionForward transfer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "transferNext";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	
	return aMapping.findForward(forward);
    }
    
    private void sendNotification( String message){
	RegionType regionType = new RegionType();
  	String fromEmail = "jims2notification@itc.hctx.net";
  	String region 	 = regionType.getRegion();
  	SendEmailEvent sendEmailEvent = new SendEmailEvent();
  	sendEmailEvent.setContentType("text/html; charset=utf-8");
  	sendEmailEvent.setSubject("Delete Event by Instructor - " + region);
  	sendEmailEvent.setFromAddress(fromEmail);
  	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
  	//sendEmailEvent.addToAddress("dustin.nguyen@us.hctx.net");
  	sendEmailEvent.setMessage(message);
  	IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
  	dispatch1.postEvent(sendEmailEvent);
  	
      }

}
