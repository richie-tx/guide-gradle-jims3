//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\HandleContractSelectionAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercontract.GetContractServicesEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ContractControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.administercontract.UIManageContractHelper;
import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class HandleContractSelectionAction extends Action
{
   
   /**
    * @roseuid 451C4F0F0086
    */
   public HandleContractSelectionAction() 
   {
    
   }
	
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward execute(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
   	   String forward = UIConstants.FAILURE;
   	   String action = contractForm.getAction();
	    
	   if("Y".equalsIgnoreCase(contractForm.getShowServiceProviderInfo())){
   	       UIManageContractHelper.selectContract(contractForm,"serviveProviderFlow");
	   }else{
	       UIManageContractHelper.selectContract(contractForm,"");
	   }
	
	   if (action.equalsIgnoreCase("view")){
	   		this.setServices(contractForm);
			forward = UIConstants.VIEW_SUCCESS;
       }else if (action.equalsIgnoreCase("update")){
       	    this.setServices(contractForm);
       	    contractForm.setOriginalServiceProviderValue(contractForm.getServiceProviderValue());
       	    contractForm.setOriginalTotalVale(contractForm.getTotalValue());
	   		forward = UIConstants.UPDATE_SUCCESS;
	   } 
	   else if (action.equalsIgnoreCase("renew")){
		   	this.setServices(contractForm);
		   	forward = UIConstants.RENEW_SUCCESS;
	   } 
	   return aMapping.findForward(forward);
   }
   
	/**
	 * @param contractForm
	 */
	private void setServices(ContractForm contractForm) {
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    GetContractServicesEvent sEvent =	(GetContractServicesEvent) EventFactory.getInstance(ContractControllerServiceNames.GETCONTRACTSERVICES);
	    sEvent.setContractId(contractForm.getContractId());
	    dispatch.postEvent(sEvent);
		
	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
		
	    Collection services = MessageUtil.compositeToCollection(compositeResponse, ServiceResponseEvent.class);
	    if (services != null && !services.isEmpty())
	    {
			contractForm.setServices(UIManageContractHelper.sortServices(services));
//	    	contractForm.setServices(services);
	    }
	}
}
