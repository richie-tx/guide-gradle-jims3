//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\HandleContractSelectionAction.java

package ui.supervision.administercontract.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercontract.GetContractServicesEvent;
import messaging.administercontract.GetContractsEvent;
import messaging.administercontract.GetServiceProviderContractsEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
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

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.supervision.administercontract.UIManageContractHelper;
import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class HandleServiceProviderContractSelectionAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F0F0086
    */
   public HandleServiceProviderContractSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A6601DC
    */
   public ActionForward submit(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
	   	String forward = UIConstants.SEARCH_FAILURE;
	   	ContractForm contractForm = (ContractForm) aForm; 
	   	contractForm.setAvailableContracts(new ArrayList());
	   	
		GetContractsEvent requestEvent = new GetContractsEvent();
		requestEvent.setContractName(contractForm.getContractName());
		requestEvent.setNumber(contractForm.getContractNum());
		requestEvent.setAgencyId(contractForm.getAgencyId());
		requestEvent.setContractTypeId(contractForm.getContractTypeId());
		
		if(contractForm.isShowExpired()){
			requestEvent.setToDate(DateUtil.getCurrentDate());
		}
		requestEvent.setFundingProgranDescription(contractForm.getProgramFundingDesc());
		requestEvent.setToDate(DateUtil.stringToDate(contractForm.getStartDateTo(),UIConstants.DATE_FMT_1));
		requestEvent.setFromDate(DateUtil.stringToDate(contractForm.getStartDateFrom(),UIConstants.DATE_FMT_1));
		requestEvent.setAgencyId(contractForm.getAgencyId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection availableContracts = MessageUtil.compositeToCollection(compositeResponse, ContractResponseEvent.class);
		if (availableContracts != null && !(availableContracts.isEmpty())){
			IShoppingCart sCart = new ShoppingCartImpl();
			String responseEventName = UIConstants.CONTRACT_RESPONSE_EVENT;
			String responseEventItemId = UIConstants.CONTRACT_RESPONSE_EVENT_ID;
			
			try
			{
				availableContracts =
					sCart.removeFromAvailableShoppingItemList(
						responseEventName,
						responseEventItemId,
						contractForm.getCurrentContracts(),
						availableContracts);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ReturnException returnException = new ReturnException(e.getMessage());
				throw returnException;
			}
			contractForm.setAvailableContracts(availableContracts);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.contract.found"));
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
	}
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward next(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward createContract(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
	    ContractForm contractForm = (ContractForm) aForm;
	    contractForm.clear();
	    contractForm.setAction("create");
   	    return aMapping.findForward(UIConstants.CREATE_SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward renew(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
   	   contractForm.setAction("renew");
	   UIManageContractHelper.selectContract(contractForm,"serviceproviderFlow");
	   return aMapping.findForward(UIConstants.RENEW_SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward update(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
       contractForm.setAction("update");
	   
	   UIManageContractHelper.selectContract(contractForm,"serviceproviderFlow");
	
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   GetContractServicesEvent sEvent = (GetContractServicesEvent) EventFactory.getInstance(ContractControllerServiceNames.GETSERVICECONTRACTS);
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
	   return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward addSelected(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
	   	ContractForm contractForm = (ContractForm) aForm;
		Collection availableContracts = contractForm.getAvailableContracts();
		Collection currentContracts = contractForm.getCurrentContracts();
	
		String[] selectedContractIds = contractForm.getSelectedContractIds();
		if (selectedContractIds != null && selectedContractIds.length > 0)
		{
			try
			{
				String responseEventName = UIConstants.CONTRACT_RESPONSE_EVENT;
				String responseEventItemId = UIConstants.CONTRACT_RESPONSE_EVENT_ID;
				IShoppingCart sCart = new ShoppingCartImpl();
	
				currentContracts =
					sCart.addToShoppingCart(
						responseEventName,
						responseEventItemId,
						selectedContractIds,
						currentContracts,
						availableContracts);
				contractForm.setCurrentContracts(new ArrayList(currentContracts));
				availableContracts =
					sCart.removeFromAvailableShoppingItemList(
						responseEventName,
						responseEventItemId,
						currentContracts,
						availableContracts);
				contractForm.setAvailableContracts(new ArrayList(availableContracts));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ReturnException returnException = new ReturnException(e.getMessage());
				throw returnException;
			}
		}
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward refresh(
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
   	   return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
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
   
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.submit","submit");	
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.refresh","refresh");
		keyMap.put("button.addSelected","addSelected");
		keyMap.put("button.edit","update");
		keyMap.put("button.renew","renew");
		keyMap.put("button.createContract","createContract");
		keyMap.put("button.next","next");
		return keyMap;
	}
}
