//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\DisplayContractCreateUpdateSummaryAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercontract.GetContractServicesEvent;
import messaging.administercontract.VerifyContractInfoEvent;
import messaging.administerserviceprovider.GetServiceProviderContractServicesEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractVerificationResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ContractControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.supervision.administercontract.UIManageContractHelper;
import ui.supervision.administercontract.form.ContractForm;

public class DisplayContractSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F0C00E4
    */
   public DisplayContractSummaryAction() 
   {
    
   }
 
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.next","next");	
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
    * @roseuid 451C0A6502A7
    */
   public ActionForward next(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   ContractForm contractForm = (ContractForm) aForm;
	   String forward = UIConstants.FAILURE;;
   	   
   	   String action = contractForm.getAction();
   	   VerifyContractInfoEvent vEvent =	(VerifyContractInfoEvent) EventFactory.getInstance(ContractControllerServiceNames.VERIFYCONTRACTINFO);
 
   	   if (action != null){
	   		if (action.equalsIgnoreCase("cancel")){
	   			contractForm.clear();
	   			forward = UIConstants.CANCEL;
	   		}
	   		if(action.equalsIgnoreCase("create")){
	   			contractForm.setContractType(UIManageContractHelper.getCodeDescription(contractForm.getContractTypeId(),contractForm.getContractTypes()));
	   			contractForm.setGlAccountKey(UIManageContractHelper.getCodeDescription(contractForm.getGlAccountKeyId(),contractForm.getGlAccountKeys()));
	   			vEvent.setContractName(contractForm.getContractName().toUpperCase());
	   			forward = UIConstants.CREATE_SUCCESS;	
	   		}else if(action.equalsIgnoreCase("update") || action.equalsIgnoreCase("updateContinue")){
	   			contractForm.setContractType(UIManageContractHelper.getCodeDescription(contractForm.getContractTypeId(),contractForm.getContractTypes()));
	   			contractForm.setGlAccountKey(UIManageContractHelper.getCodeDescription(contractForm.getGlAccountKeyId(),contractForm.getGlAccountKeys()));
	   			vEvent.setContractId(contractForm.getContractId());
	   			vEvent.setContractName(contractForm.getContractName().toUpperCase());
	   			vEvent.setTotalValue(contractForm.getTotalValue());
	   			vEvent.setServiceProviderValue(contractForm.getServiceProviderValue());
	   			vEvent.setAction(UIConstants.UPDATE);
	   		    forward = UIConstants.UPDATE_SUCCESS;
	   		    if (contractForm.getShowServiceProviderInfo().equalsIgnoreCase("Y")){
	   		    	if (!contractForm.getTotalValue().equals(contractForm.getOriginalTotalVale())){
	   		    		GetServiceProviderContractServicesEvent cEvent = new GetServiceProviderContractServicesEvent();
	   		    		cEvent.setContractId(contractForm.getContractId());
	   		    		cEvent.setServiceId(contractForm.getServiceId());
	   		    		dispatch.postEvent(cEvent);
	   		 		
	   		 	   		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	   		 	   		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	   		 	   		MessageUtil.processReturnException(dataMap);
	   		 	   		
	   			   		Collection responses = MessageUtil.compositeToCollection(compositeResponse, ServiceResponseEvent.class);
	   			   		if(responses != null && !responses.isEmpty()){
	   			   			contractForm.setServiceProviderServices(responses);
		                    forward = UIConstants.UPDATE_CONITNUE;
		                    contractForm.setAction(forward);
	   			   		}
	   		    	}
	   		    }
	   		}
	   		else if(action.equalsIgnoreCase("renew")){
	   			forward = UIConstants.RENEW_SUCCESS;
	   		}
	   		else if( action.equalsIgnoreCase("view")){
	   			vEvent.setContractId(contractForm.getContractId());
	   			forward = UIConstants.VIEW_SUCCESS;
	   		}	
       }
	   if (action != null && (action.equalsIgnoreCase("create") || action.equalsIgnoreCase("update"))){
	   		dispatch.postEvent(vEvent);
		
	   		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	   		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	   		MessageUtil.processReturnException(dataMap);
		
	   		ContractVerificationResponseEvent vResp = (ContractVerificationResponseEvent) MessageUtil.filterComposite(compositeResponse, ContractVerificationResponseEvent.class);
	   		if (vResp != null && vResp.getMessage() != null && vResp.getContractName() != null)
	   		{
	   			ActionErrors errors = new ActionErrors();
	   			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(vResp.getMessage(),vResp.getContractName()));
	   			saveErrors(aRequest, errors);
	   			forward = UIConstants.VERIFICATION_FAILURE;
	   		}
	   		
	   		if (vResp != null && vResp.getMessage() != null && vResp.getServiceProviderValue() != null)
	   		{
	   			ActionErrors errors = new ActionErrors();
	   			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(vResp.getMessage(),vResp.getServiceProviderValue()));
	   			saveErrors(aRequest, errors);
	   			forward = UIConstants.VERIFICATION_FAILURE;
	   		}
	   }	
	   return aMapping.findForward(forward);
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
