//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\HandleContractDetailsSelectionAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.HashMap;
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.administercontract.UIManageContractHelper;
import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleContractDetailsSelectionAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F0F03B3
    */
   public HandleContractDetailsSelectionAction() 
   {
    
   }

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.update","update");	
		keyMap.put("button.renew","renew");
		keyMap.put("button.createContract","create");		
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		return keyMap;
	}   
   
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward update(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
   	   String forward = UIConstants.FAILURE;
 	    
	   UIManageContractHelper.selectContract(contractForm,"");
	
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   GetContractServicesEvent sEvent = (GetContractServicesEvent) EventFactory.getInstance(ContractControllerServiceNames.GETCONTRACTSERVICES);
	   sEvent.setContractId(contractForm.getContractId());
	   dispatch.postEvent(sEvent);
		
	   CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	   Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	   MessageUtil.processReturnException(dataMap);
		
	   Collection services = MessageUtil.compositeToCollection(compositeResponse, ServiceResponseEvent.class);
	   if (services != null && !services.isEmpty())
	   {
		   contractForm.setServices(services);
	   }
	   contractForm.setAction("update");
	   forward = UIConstants.UPDATE_SUCCESS;
	   return aMapping.findForward(forward);
   }

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward renew(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
   	   String forward = UIConstants.FAILURE;
 	    
	   UIManageContractHelper.selectContract(contractForm,"");
	
	   contractForm.setAction("renew");
	   forward = UIConstants.RENEW_SUCCESS;
	   return aMapping.findForward(forward);
   }
     
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward create(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	    ContractForm contractForm = (ContractForm) aForm;
   	    contractForm.setContractNum("");
   	    contractForm.setContractName("");
   	    contractForm.setStartDate("");
   	    contractForm.setEndDate("");
   	    contractForm.setContractTypeId("");
   	    contractForm.setGlAccountKeyId("");
   	    contractForm.setProgramFundingDesc("");
   	    contractForm.setTracerNumberRangeFrom("");
   	    contractForm.setTracerNumberRangeTo("");
   	    contractForm.setTotalValue("");
   	    contractForm.setAction("create");
   	    return aMapping.findForward(UIConstants.CREATE_SUCCESS);
   }

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward back(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	    return aMapping.findForward(UIConstants.BACK);
   }  

	/**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
   public ActionForward cancel(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	    ContractForm contractForm = (ContractForm) aForm;
   	    Collection currentContracts = contractForm.getCurrentContracts();
   	    contractForm.clear();
	 	if(contractForm.getShowServiceProviderInfo().equals("Y")){
	 		contractForm.setCurrentContracts(currentContracts);
	 	}
	    return aMapping.findForward(UIConstants.CANCEL);
   }
   
}
