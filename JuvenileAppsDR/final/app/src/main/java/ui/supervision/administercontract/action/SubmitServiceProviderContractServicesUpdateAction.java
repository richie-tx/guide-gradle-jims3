//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\SubmitContractValueAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercontract.UpdateContractEvent;
import messaging.administercontract.UpdateContractServiceEvent;
import messaging.administercontract.UpdateContractServiceValueEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractServiceResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderServiceResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ContractControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.administercontract.UIManageContractHelper;
import ui.supervision.administercontract.form.ContractForm;

public class SubmitServiceProviderContractServicesUpdateAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F10024B
    */
   public SubmitServiceProviderContractServicesUpdateAction() 
   {
    
   }

   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
   protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.finish","finish");	
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.programDashboard","pgmDashboard");
		return keyMap;
   }

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A660120
    */
   public ActionForward finish(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	   	String forward = UIConstants.FAILURE;
	   	ContractForm contractForm = (ContractForm) aForm;
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateContractEvent updateEvent = new UpdateContractEvent();
		updateEvent.setContractId(contractForm.getContractId());
		UIManageContractHelper.setUpdateRequestEvent(contractForm, updateEvent);
		updateEvent.setAction("update");
		forward = UIConstants.UPDATE_SUCCESS;
	    dispatch.postEvent(updateEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		ContractServiceResponseEvent sp = null;
		Object [] serviceProviders = contractForm.getServiceProviderServices();
		
		for(int i=0; i < serviceProviders.length;i++){
			UpdateContractServiceEvent uEvent = new UpdateContractServiceEvent();
			sp = (ContractServiceResponseEvent) contractForm.getServiceProviderServices()[i];
			uEvent.setServiceId(sp.getServiceId());

			UpdateContractServiceValueEvent vEvent = new UpdateContractServiceValueEvent();
   	        vEvent.setContractId(contractForm.getContractId());
   	        vEvent.setServiceProviderValue(sp.getSPValue());
   	        vEvent.setContractServiceId(sp.getContractServiceId());
   	        uEvent.addRequest(vEvent);
		    dispatch.postEvent(uEvent);
		    compositeResponse = (CompositeResponse) dispatch.getReply();
		    dataMap = MessageUtil.groupByTopic(compositeResponse);
		    MessageUtil.processReturnException(dataMap);
		}
		contractForm.setAction("confirm");
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A660120
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
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward pgmDashboard(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
	   contractForm.clear();
	   return aMapping.findForward(UIConstants.PROCESS_RETURN_SUCCESS);
   }
}
