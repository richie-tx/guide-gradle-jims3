package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.productionsupport.DeleteProductionSupportDrugTestingInfoEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformDeleteDrugTestingAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "next");
	keyMap.put("button.deleteRecord", "deleteRecord");

    }
    
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "continue";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	List<DrugTestingResponseEvent>drugTestingInfos = regform.getDrugTestingInfos();
	if ( drugTestingInfos != null
		&& drugTestingInfos.size() > 0 ) {
        	for ( DrugTestingResponseEvent drugTestingInfo :  drugTestingInfos ){
        	    if ( regform.getSelectedValue().equals( drugTestingInfo.getDrugTestingId()) ) {
        		regform.setDrugTestingInfo( drugTestingInfo  );
        	    }
        	}
	}
	
	return aMapping.findForward(forward);
    }
    
    public ActionForward deleteRecord(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "success";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String drugTestingId = regform.getSelectedValue();
	DeleteProductionSupportDrugTestingInfoEvent deleteEvent = (DeleteProductionSupportDrugTestingInfoEvent) 
									EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTDRUGTESTINGINFO);
	deleteEvent.setDrugTestingId(drugTestingId);
	CompositeResponse composite = MessageUtil.postRequest(deleteEvent);
	ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(composite,
	        ErrorResponseEvent.class);
	if(errorEvt!=null){
		if(errorEvt.getMessage()!=null) {
		    	ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Error on deleting the drug testing information."));
			saveErrors(aRequest, errors); 
			return aMapping.findForward("error");
		} 
	}
	regform.setMsg("Drug Testing ID " + drugTestingId + " was successfully deleted.");
	return aMapping.findForward(forward);
    }
}
