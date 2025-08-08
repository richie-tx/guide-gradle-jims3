package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.productionsupport.DeleteReferralOffenseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;



public class PeformDeleteReferralOffenseAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "next");
	keyMap.put("button.deleteReferralOffense", "deleteReferralOffense");

	
    }
    
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "success";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	ArrayList<JJSOffenseResponseEvent>referralOffenses = regform.getReferralOffenses();
	if ( referralOffenses != null
		&& referralOffenses.size() > 0 ) {
        	for ( JJSOffenseResponseEvent referralOffense :  referralOffenses ){
        	    if ( regform.getOffenseId().equals( referralOffense.getOID() ) ) {
        		regform.setReferralOffense(referralOffense);
        	    }
        	}
	}
	
	return aMapping.findForward(forward);
    }
    
    public ActionForward deleteReferralOffense(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String forward = "deleteSuccess";
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String delComments = regform.getDelComments();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	DeleteReferralOffenseEvent deleteEvent = (DeleteReferralOffenseEvent) 
							EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEREFERRALOFFENSE);
	deleteEvent.setOffenseId(regform.getReferralOffense().getOID());
	deleteEvent.setDelComments(delComments);
	
	dispatch.postEvent(deleteEvent);
	regform.setMsg("Referral Offense Record Successfully Deleted");
	
	return aMapping.findForward(forward);
    }
    

}
