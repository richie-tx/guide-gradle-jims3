package ui.juvenilecase.prodsupport.action.update;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.TransferProductionSupportServiceToAnotherInstructorEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.security.RegionType;
import pd.supervision.administerserviceprovider.SP_Profile;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformTransferEventToAnotherInstructorAction extends JIMSBaseAction
{

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.next", "next");
	keyMap.put("button.displayAll", "displayInstructors");
	keyMap.put("button.transfer", "transfer");
	keyMap.put("button.back", "back");
	
    }
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "success";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	
	return aMapping.findForward(forward);
    }
    
    public ActionForward displayInstructors(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	
	String forward = "successInstructorsDisplay";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	regform.getInstructors();
	
	return aMapping.findForward(forward);
    }
    
    
    public ActionForward transfer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	
	String forward = "successInstructorTransfer";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String selectedServiceEventId = regform.getServiceEventId();
	String selectedInstructorId   = regform.getSelectedTransferInstructorId();
	StringBuffer message = new StringBuffer();
	String instructorName = "";
	if ( regform.getInstructors() != null
		&& regform.getInstructors().size() > 0  ) {
	    for (SP_Profile instructor : regform.getInstructors() ){
		if ( regform.getSelectedTransferInstructorId().equals( instructor.getOID() ) ){
		    instructorName = instructor.getLastName() + ", " + instructor.getFirstName();
		}
	    }
	}
	
	TransferProductionSupportServiceToAnotherInstructorEvent transferEvent = (TransferProductionSupportServiceToAnotherInstructorEvent)
											EventFactory.getInstance(ProductionSupportControllerServiceNames.TRANSFERPRODUCTIONSUPPORTSERVICETOANOTHERINSTRUCTOR);
	transferEvent.setInstructorId(selectedInstructorId);
	transferEvent.setServiceEventId(selectedServiceEventId);
	MessageUtil.postRequest(transferEvent);
	regform.setMsg("Event  successfully transfered to Instructor Id  " + regform.getSelectedTransferInstructorId() + "." );
	message.append("Event ID " + selectedServiceEventId + " successfully transferred to instructor name " + instructorName + "( Instructor ID " +   regform.getSelectedTransferInstructorId()+" ) ." );
	sendNotification(message.toString());
	
	return aMapping.findForward(forward);
    }
    
    
    private void sendNotification( String message){
	RegionType regionType = new RegionType();
  	String fromEmail = "jims2notification@itc.hctx.net";
  	String region 	 = regionType.getRegion();
  	SendEmailEvent sendEmailEvent = new SendEmailEvent();
  	sendEmailEvent.setContentType("text/html; charset=utf-8");
  	sendEmailEvent.setSubject("Event Transfer to Instructor - " + region);
  	sendEmailEvent.setFromAddress(fromEmail);
  	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
  	//sendEmailEvent.addToAddress("dustin.nguyen@us.hctx.net");
  	sendEmailEvent.setMessage(message);
  	IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
  	dispatch1.postEvent(sendEmailEvent);
  	
      }

}
