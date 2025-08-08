//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\DisplayContractSearchResultsAction.java

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
import messaging.administercontract.GetServiceContractsEvent;
import messaging.administercontract.GetServiceProviderContractsEvent;
import messaging.administercontract.DeleteContractServiceEvent;
import messaging.administercontract.UpdateContractServiceValueEvent;
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
import ui.security.SecurityUIHelper;
import ui.supervision.administercontract.UIManageContractHelper;
import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayContractSearchResultsAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F0D0394
    */
   public DisplayContractSearchResultsAction() 
   {
    
   }
   
   /**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.submit", "submit");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.next", "next");	
		buttonMap.put("button.addSelected", "addSelected");			
		buttonMap.put("button.createContract", "create");
		return buttonMap;
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
	   	int size = 0;
	   	ContractForm contractForm = (ContractForm) aForm; 
	   	contractForm.setAvailableContracts(new ArrayList());
		contractForm.setAction("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		if("Y".equalsIgnoreCase(contractForm.getShowServiceProviderInfo())){
			GetServiceProviderContractsEvent requestEvent = this.prepareGetServiceproviderEvent(contractForm);
			dispatch.postEvent(requestEvent);

		}else{
			GetContractsEvent requestEvent = this.prepareGetContractsEvent(contractForm);
			dispatch.postEvent(requestEvent);
		}
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		// clean up the search prompt
		contractForm.setShowExpired(false);
	
		Collection availableContracts = MessageUtil.compositeToCollection(compositeResponse, ContractResponseEvent.class);
//		Collections.sort((ArrayList)availableContracts);
		if(contractForm.getShowServiceProviderInfo().equalsIgnoreCase("Y") && availableContracts != null && !(availableContracts.isEmpty())){
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
			contractForm.setAvailableContracts(new ArrayList(availableContracts));
			forward = UIConstants.LIST_SUCCESS;
		}
		else if(!contractForm.getShowServiceProviderInfo().equalsIgnoreCase("Y") && availableContracts != null && !(availableContracts.isEmpty())){
			size = availableContracts.size();
			contractForm.setAvailableContracts(availableContracts);
			if (size == 1){
				ContractResponseEvent contract = (ContractResponseEvent) availableContracts.toArray()[0];		
				contractForm.setContractId(contract.getContractId());
				contractForm.setContractNum(contract.getNumber());
				contractForm.setContractName(contract.getContractName());
				if(contract.getStartDate() != null){
					contractForm.setStartDate(DateUtil.dateToString(contract.getStartDate(), UIConstants.DATE_FMT_1));
				}
				if(contract.getEndDate() != null){
					contractForm.setEndDate(DateUtil.dateToString(contract.getEndDate(), UIConstants.DATE_FMT_1)); 
				}
				contractForm.setContractTypeId(contract.getContractTypeId());
				contractForm.setContractType(contract.getContractType());
				contractForm.setGlAccountKey(contract.getGlAccountKeyDesc());
				contractForm.setGlAccountKeyId(contract.getGlAccountKeyId());
				contractForm.setProgramFundingDesc(contract.getProgramFundingDescription());
				contractForm.setTracerNumberRangeFrom(contract.getTracerNumberFrom());
				contractForm.setTracerNumberRangeTo(contract.getTracerNumberTo());
				contractForm.setRenewalNum(contract.getRenewalNum());
				contractForm.setContractExpired(contract.isExpired());
				contractForm.setTotalValue(contract.getTotalValue());
				contractForm.setContractExpired(contract.isExpired());
				contractForm.setAction("searchResult");

				GetContractServicesEvent sEvent = (GetContractServicesEvent) EventFactory.getInstance(ContractControllerServiceNames.GETCONTRACTSERVICES);
			    sEvent.setContractId(contractForm.getContractId());
			    dispatch.postEvent(sEvent);
				CompositeResponse compositeResponse2 = (CompositeResponse) dispatch.getReply();
				Map dataMap2 = MessageUtil.groupByTopic(compositeResponse2);
				MessageUtil.processReturnException(dataMap2);
					
				Collection services = MessageUtil.compositeToCollection(compositeResponse, ServiceResponseEvent.class);
				if (services != null && !services.isEmpty())
				{
					contractForm.setServices(UIManageContractHelper.sortServices(services));
				}
				forward = UIConstants.SEARCH_SUCCESS;
			}else{
			   forward = UIConstants.LIST_SUCCESS;
			}
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.search.results.found"));
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
	}

	/**
	 * @param contractForm
	 * @return
	 */
	private GetContractsEvent prepareGetContractsEvent(ContractForm contractForm) {
		GetContractsEvent requestEvent = new GetContractsEvent();
		requestEvent.setContractName(contractForm.getSearchContractName());
		requestEvent.setNumber(contractForm.getSearchContractNum());
		requestEvent.setContractTypeId(contractForm.getContractTypeId());
		
		if(contractForm.isShowExpired()){
			requestEvent.setExpired(contractForm.isShowExpired());
		}
		requestEvent.setFundingProgranDescription(contractForm.getSearchProgramFundingDesc());
		requestEvent.setToDate(DateUtil.stringToDate(contractForm.getStartDateTo(),UIConstants.DATE_FMT_1));
		requestEvent.setFromDate(DateUtil.stringToDate(contractForm.getStartDateFrom(),UIConstants.DATE_FMT_1));
		requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		return requestEvent;
	}

	/**
	 * @param contractForm
	 * @return
	 */
	private GetServiceProviderContractsEvent prepareGetServiceproviderEvent(ContractForm contractForm) {
		GetServiceProviderContractsEvent requestEvent = new GetServiceProviderContractsEvent();
		requestEvent.setContractName(contractForm.getSearchContractName());
		requestEvent.setNumber(contractForm.getSearchContractNum());
		requestEvent.setContractTypeId(contractForm.getContractTypeId());
		requestEvent.setServiceId(contractForm.getServiceId());
		
		if(contractForm.isShowExpired()){
			requestEvent.setExpired(true);
		}
		requestEvent.setFundingProgranDescription(contractForm.getSearchProgramFundingDesc());
		requestEvent.setToDate(DateUtil.stringToDate(contractForm.getStartDateTo(),UIConstants.DATE_FMT_1));
		requestEvent.setFromDate(DateUtil.stringToDate(contractForm.getStartDateFrom(),UIConstants.DATE_FMT_1));
		requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		return requestEvent;
	}

/**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward next(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	    ContractForm contractForm = (ContractForm) aForm; 
	   	String forward = UIConstants.FAILURE;
	   	Iterator currentContractIter = contractForm.getCurrentContracts().iterator();
	   	boolean isNewContractAdded = false;
	   	HashMap currentMap = new HashMap();
	   	while(currentContractIter.hasNext()){
	   		ContractResponseEvent cResp = (ContractResponseEvent) currentContractIter.next();
	   		if(cResp.getContractServiceId() == null || cResp.getServiceProviderValue() == null || cResp.getServiceProviderValue().equals("0.00")){
	   			isNewContractAdded = true;
	   		}
	   		currentMap.put(cResp.getContractId(), cResp.getContractId());
	   	}
	    if(isNewContractAdded){
	    	forward = UIConstants.SUCCESS;
	    	contractForm.setShowNoMessage("N");
	    	contractForm.setShowDropAssignmentMessage("");
	    }else{
	    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			contractForm.setAction("confirm");
	 	    GetServiceContractsEvent sEvent = new GetServiceContractsEvent();
	 	    sEvent.setServiceId(contractForm.getServiceId());
	 	    dispatch.postEvent(sEvent);
	 	    
	 	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
				
			Iterator contractIter = MessageUtil.compositeToCollection(compositeResponse, ContractResponseEvent.class).iterator();
			boolean hasExistingContractBeenDropped = false;
			while(contractIter.hasNext()){
				ContractResponseEvent cResp = (ContractResponseEvent) contractIter.next();
				if(!currentMap.containsKey(cResp.getContractId())){
					hasExistingContractBeenDropped = true;
			   	    if(cResp != null){
			   		    DeleteContractServiceEvent dEvent =	(DeleteContractServiceEvent) EventFactory.getInstance(ContractControllerServiceNames.DELETECONTRACTSERVICE);
			   		    dEvent.setContractServiceId(cResp.getContractServiceId());
			   		    dispatch.postEvent(dEvent);
					    
					    compositeResponse = (CompositeResponse) dispatch.getReply();
					    dataMap = MessageUtil.groupByTopic(compositeResponse);
					    MessageUtil.processReturnException(dataMap);
			   	    }
				}
			}
	 	    if(hasExistingContractBeenDropped){
	 	    	contractForm.setShowDropAssignmentMessage("Y");
	 	    	contractForm.setShowNoMessage("");
	 	    	forward = UIConstants.DROP_ASSIGNMENT_SUCCESS;
	 	    }else{
	 	    	contractForm.setShowNoMessage("");
	 	    	contractForm.setShowDropAssignmentMessage("");
	 	    	forward = UIConstants.NONEW_ASSIGNMENT_SUCCESS;
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
    */
   public ActionForward create(
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
   public ActionForward addSelected(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	    /*
   	     * Added for Defect #JIMS200046116
   	     */
   		ContractForm contractForm = (ContractForm) aForm;
		Collection selectedContracts = new ArrayList();
		String idArray[] = contractForm.getSelectedContractIds();
		Collection availableContracts = contractForm.getAvailableContracts();
		
		if(!availableContracts.isEmpty() && idArray.length > 0){
		Iterator iterator = availableContracts.iterator();
        while (iterator.hasNext()) {
			ContractResponseEvent contractResp = (ContractResponseEvent) iterator.next();
			for (int k = 0; k < idArray.length; k++) {
				if (idArray[k].equalsIgnoreCase(contractResp.getContractId())) {
					selectedContracts.add(contractResp);
				}
			}
		}
		}
		Collection currentContracts = contractForm.getCurrentContracts();
		currentContracts.addAll(selectedContracts);
		contractForm.setCurrentContracts(currentContracts);
		//contractForm.setCurrentContracts(selectedContracts);
		return aMapping.findForward(UIConstants.ADD_SELECTED_SUCCESS);
   }    
}
