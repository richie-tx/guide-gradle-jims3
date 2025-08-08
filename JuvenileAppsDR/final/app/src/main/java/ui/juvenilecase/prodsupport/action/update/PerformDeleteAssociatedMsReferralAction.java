package ui.juvenilecase.prodsupport.action.update;

import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.DeleteAssociatedMsReferalEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformDeleteAssociatedMsReferralAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.deleteReferral", "submitReferralRecordDelete");
	keyMap.put("button.details", "submitTableDetails");
	keyMap.put("button.back", "back");
	
    }

    private Logger log = Logger.getLogger("PerformCaseDeleteClosingAction");
    public ActionForward submitReferralRecordDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	ProdSupportForm regform = (ProdSupportForm) form;
	System.out.println("Perform delete referral");
	DeleteAssociatedMsReferalEvent deleteReferralEvent = (DeleteAssociatedMsReferalEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEASSOCIATEDMSREFERAL);
	deleteReferralEvent.setProdSupportForm(regform);
	MessageUtil.postRequest(  deleteReferralEvent );
	return mapping.findForward("success");
    }
    
    public ActionForward submitTableDetails(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	ProdSupportForm regform = (ProdSupportForm) form;
	String tableId		= request.getParameter("tableId");
	regform.setTableId(tableId);
	System.out.println("Retrieve the table details" + tableId);
	
	return mapping.findForward("tableDetailsDisplay");
    }
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("associatedSuccess");
    }
}
