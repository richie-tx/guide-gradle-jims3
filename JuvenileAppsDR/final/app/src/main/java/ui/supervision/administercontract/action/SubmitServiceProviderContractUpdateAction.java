//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\SubmitContractValueAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercontract.UpdateContractServiceEvent;
import messaging.administercontract.UpdateContractServiceValueEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
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

import ui.supervision.administercontract.form.ContractForm;

public class SubmitServiceProviderContractUpdateAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F10024B
    */
   public SubmitServiceProviderContractUpdateAction() 
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
   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   ContractForm contractForm = (ContractForm) aForm;
	   String forward = "FAILURE";
	   UpdateContractServiceEvent uEvent =	(UpdateContractServiceEvent) EventFactory.getInstance(ContractControllerServiceNames.UPDATECONTRACTSERVICE);
	   uEvent.setServiceId(contractForm.getServiceId());
	   
	   Iterator currentContractsIter = contractForm.getCurrentContracts().iterator();
	   while(currentContractsIter.hasNext()){
	   	   ContractResponseEvent resp =	(ContractResponseEvent) currentContractsIter.next();
	   	   if(resp != null){
	   	       UpdateContractServiceValueEvent vEvent = new UpdateContractServiceValueEvent();
	   	       vEvent.setContractId(resp.getContractId());
	   	       vEvent.setServiceProviderValue(resp.getServiceProviderValue());
	   	       vEvent.setContractServiceId(resp.getContractServiceId());
	   	       uEvent.addRequest(vEvent);
	   	   }
	   }
	   
	   dispatch.postEvent(uEvent);
	   CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	   Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	   MessageUtil.processReturnException(dataMap);
	   contractForm.setAction("confirm");
       contractForm.setShowNoMessage("N");
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
