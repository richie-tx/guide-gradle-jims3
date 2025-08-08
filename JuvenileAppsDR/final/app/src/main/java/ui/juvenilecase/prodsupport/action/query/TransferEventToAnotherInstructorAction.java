package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetProgramsByProviderIdEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceByInstructorEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class TransferEventToAnotherInstructorAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	 /** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setServeventId("");
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	
	if ( regform.getInstructor() != null ) {
	    String instructorId	= regform.getInstructorId();
	    GetProductionSupportCalendarServiceByInstructorEvent getServiceEvent = (GetProductionSupportCalendarServiceByInstructorEvent)
	    									EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDARSERVICEBYINSTRUCTOR);
	    									
	   /* GetServiceEventsByProviderProfileIdEvent reqEvent = (GetServiceEventsByProviderProfileIdEvent)
		    EventFactory.getInstance( ServiceEventControllerServiceNames.GETSERVICEEVENTSBYPROVIDERPROFILEID);*/
	    getServiceEvent.setInstructorId(instructorId);
	    getServiceEvent.setFromStartDate(regform.getFromStartDate());
	    getServiceEvent.setToStartDate(regform.getToStartDate());
	    CompositeResponse compResponse = MessageUtil.postRequest(getServiceEvent);
	    List<CalendarServiceEventResponseEvent> services = MessageUtil.compositeToList(compResponse, CalendarServiceEventResponseEvent.class);
	    
	    if ( services != null 
		   && services.size() > 0 ) {
	       Collections.sort(services,  new Comparator<CalendarServiceEventResponseEvent>(){
		   @Override
		    public int compare(CalendarServiceEventResponseEvent s1, CalendarServiceEventResponseEvent s2 ){
			return s1.getStartDatetime().compareTo(s2.getStartDatetime()) ;
		    }
	       });
	   }
	       regform.setServices(services);
	       
	       GetProgramsByProviderIdEvent getProgramNamesEvent = (GetProgramsByProviderIdEvent) 
			EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROGRAMSBYPROVIDERID);
	       
	       getProgramNamesEvent.setJuvServiceProviderId(regform.getInstructor().getServiceProviderId());
	       CompositeResponse compositeRsp = MessageUtil.postRequest(getProgramNamesEvent);
	       List<ProviderProgramResponseEvent> programs = MessageUtil.compositeToList(compositeRsp, ProviderProgramResponseEvent.class);
	       List<ProviderProgramResponseEvent>activePrograms = new ArrayList<ProviderProgramResponseEvent>();
	       if (programs != null
		       && programs.size() > 0 ) {
		   for ( ProviderProgramResponseEvent program : programs){
		       if ( "A".equals( program.getProgramStatusId() ) ){
			   activePrograms.add( program );
		       }
		   }
		   
		   regform.setPrograms(activePrograms);
	       }
	       
	    
	} else {
	    regform.setMsg("Instructor not found. You must enter a valid Instructor ID.");
	    return mapping.findForward("error");
	}
	
	  return mapping.findForward(forward);
	
    }
}
