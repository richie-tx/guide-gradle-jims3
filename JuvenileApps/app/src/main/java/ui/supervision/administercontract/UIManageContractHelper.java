/*
 * Created on October 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.administercontract;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.administercontract.GetContractsEvent;
import messaging.administercontract.GetServiceProviderContractsEvent;
import messaging.administercontract.UpdateContractEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import ui.security.SecurityUIHelper;
import ui.supervision.administercontract.form.ContractForm;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UIManageContractHelper
{

	/*
	 * @param contractForm
	 * @param flow
	 */
	public static void selectContract(ContractForm contractForm, String flow){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		if(!"".equals(flow)){
			GetServiceProviderContractsEvent event = new GetServiceProviderContractsEvent();
			event.setContractId(contractForm.getContractId());
			event.setServiceId(contractForm.getServiceId());
			dispatch.postEvent(event);
		}else{
			GetContractsEvent event = new GetContractsEvent();
			event.setContractId(contractForm.getContractId());
			dispatch.postEvent(event);
		}

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
			
	    ContractResponseEvent resp = (ContractResponseEvent) MessageUtil.filterComposite(compositeResponse, ContractResponseEvent.class);
		if (resp != null && resp.getContractId().equals(contractForm.getContractId()))
		{
			contractForm.setContractNum(resp.getNumber());
			contractForm.setContractName(resp.getContractName());
			contractForm.setStartDate(DateUtil.dateToString(resp.getStartDate(), UIConstants.DATE_FMT_1));
			if(resp.getEndDate() != null){
				contractForm.setEndDate(DateUtil.dateToString(resp.getEndDate(), UIConstants.DATE_FMT_1));
			}
			contractForm.setProgramFundingDesc(resp.getProgramFundingDescription());
			contractForm.setTracerNumberRangeFrom(resp.getTracerNumberFrom());
			contractForm.setTracerNumberRangeTo(resp.getTracerNumberTo());
			contractForm.setTotalValue(resp.getTotalValue());
			contractForm.setRenewalNum(resp.getRenewalNum());
			contractForm.setGlAccountKey(resp.getGlAccountKeyDesc());
			contractForm.setGlAccountKeyId(resp.getGlAccountKeyId());
			contractForm.setContractTypeId(resp.getContractTypeId());
			contractForm.setContractType(resp.getContractType());
			contractForm.setServiceProviderValue(resp.getServiceProviderValue());
			contractForm.setContractServiceId(resp.getContractServiceId());
			contractForm.setAvailableContractValue(resp.getAvailableContractValue());
		}
	}
	/**
	 * @param departments
	 * @return
	 */
	public static Collection sortServices(Collection services)
	{
		SortedMap map = new TreeMap();
		Iterator iter = services.iterator();
		while (iter.hasNext())
		{
			ServiceResponseEvent service = (ServiceResponseEvent) iter.next();
			map.put(service.getServiceName(), service);
		}
		return map.values();
	}
	
	 /**
	 * @param contractForm
	 * @param updateEvent
	 */
	public static void setUpdateRequestEvent(ContractForm contractForm, UpdateContractEvent updateEvent) {
		updateEvent.setNumber(contractForm.getContractNum());
   		updateEvent.setContractName(contractForm.getContractName());
   		updateEvent.setStartDate(DateUtil.stringToDate(contractForm.getStartDate(),UIConstants.DATE_FMT_1));
   		updateEvent.setEndDate(DateUtil.stringToDate(contractForm.getEndDate(),UIConstants.DATE_FMT_1));
   		updateEvent.setContractTypeId(contractForm.getContractTypeId());
   		updateEvent.setGlAccountKey(contractForm.getGlAccountKeyId());
   		updateEvent.setProgramFundingDescription(contractForm.getProgramFundingDesc());
   		updateEvent.setTracerRangeFrom(contractForm.getTracerNumberRangeFrom());
   		updateEvent.setTracerRangeTo(contractForm.getTracerNumberRangeTo());
   		updateEvent.setTotalValue(contractForm.getTotalValue());
   		updateEvent.setRenewalNum(contractForm.getRenewalNum());
   		updateEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());   
   		updateEvent.setServiceProviderValue(contractForm.getServiceProviderValue());
   		updateEvent.setServiceId(contractForm.getServiceId());
   		updateEvent.setContractId(contractForm.getContractId());
   		updateEvent.setContractServiceId(contractForm.getContractServiceId());
   		if(contractForm.getShowServiceProviderInfo().equalsIgnoreCase("Y")){
   			updateEvent.setServiceProviderFlow(true);
   		}
	}
	
	 /**
	 * @param codeId
	 * @param codes
	 * @return codedescription String
	 */
	public static String getCodeDescription(String codeId,Collection codes){
		if (codeId != null && !codeId.equals("")) {
   			Iterator iter = codes.iterator();
   			while (iter.hasNext())
   			{
   				CodeResponseEvent cEvent = (CodeResponseEvent) iter.next();
   				if (cEvent.getCode().equalsIgnoreCase(codeId)){
   					return cEvent.getDescription();
   				}
   			}
		}
		return "";
	}
	
	/**
	 * @param contractForm
	 */
	public static void cleanSearchPrompt(ContractForm contractForm){
		contractForm.setShowExpired(false);
		contractForm.setContractName("");
		contractForm.setContractTypeId("");
		contractForm.setContractNum("");
		contractForm.setProgramFundingDesc("");
		contractForm.setStartDateFrom("");
		contractForm.setStartDateTo("");
	}	   
}
