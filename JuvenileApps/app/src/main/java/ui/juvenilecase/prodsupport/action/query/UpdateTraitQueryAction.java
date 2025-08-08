package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.productionsupport.GetProductionSupportTestingSessionInfoEvent;
import messaging.productionsupport.GetProductionSupportTraitsEvent;
import messaging.productionsupport.reply.ProductionSupportTestingSessionInfoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class UpdateTraitQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, 
					HttpServletResponse response) throws Exception {

	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	 /** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setMsg("");
	    regform.setTraitId("");
	    return mapping.findForward("error");
	}
	
	GetProductionSupportTraitsEvent getTraitsEvent = (GetProductionSupportTraitsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTTRAITS);
	JuvenileTraitResponseEvent traitResponseEvent = null;
	String traitId = regform.getTraitId();
	ArrayList<JuvenileTraitResponseEvent> getTraitsResponsesList = null;
	if (traitId != null
		&& traitId != "")
	{
	    getTraitsEvent.setTraitId(traitId);
	    CompositeResponse getTraitsResponse = MessageUtil.postRequest(getTraitsEvent);
	    getTraitsResponsesList = (ArrayList) MessageUtil.compositeToCollection(getTraitsResponse, JuvenileTraitResponseEvent.class);
	    
	    if(getTraitsResponsesList.size() > 0)
	    {
	      regform.setToJuvenileId(getTraitsResponsesList.get(0).getJuvenileNum());	
	      regform.setCurrentSupervisionNum(getTraitsResponsesList.get(0).getSupervisionNum());
	      regform.setCurrentSupervisionNum(getTraitsResponsesList.get(0).getSupervisionNum());
	      regform.setTraitTypeId(getTraitsResponsesList.get(0).getTraitTypeId());
	      regform.setStatusId(getTraitsResponsesList.get(0).getStatusId());
	      regform.setOriginalTrait(getTraitsResponsesList.get(0));	 
	      regform.setMsg("");
	    }
	    else
	    {
		regform.setMsg("No Trait Record Found.");
		return mapping.findForward("error");
	    }	    
	}
	
	
	return mapping.findForward(forward);
	
    }

}
