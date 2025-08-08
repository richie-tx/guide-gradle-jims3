//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\VerifyContractInfoCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.util.Iterator;

import pd.supervision.administerserviceprovider.administercontract.Contract;
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;
import messaging.administercontract.GetContractsEvent;
import messaging.administercontract.VerifyContractInfoEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractVerificationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class VerifyContractInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F720088
    */
   public VerifyContractInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A6502E1
    */
   public void execute(IEvent event) 
   {
       VerifyContractInfoEvent vEvent = (VerifyContractInfoEvent) event;
	   
	   String action = vEvent.getAction();
	   Contract contract = null;
	   if(action != null && action.equals("update")){
	   	   contract = Contract.find(vEvent.getContractId());
	   	   if(contract != null){
	   	   	   if(!contract.getContractName().equalsIgnoreCase(vEvent.getContractName())){
	   	   	   		this.verifyContractName(vEvent);
	   	   	   }
	   	   	   this.verifyTotalValue(vEvent);
	   	   }
	   }else{
	   	   this.verifyContractName(vEvent);
	   }
   }
   
	/**
	 * @param event
	 * check serviceProvider value <= total value
	 */
	private void verifyTotalValue(VerifyContractInfoEvent event) {
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator valIter = ServiceContractValue.findAll("contractId",event.getContractId());
		double serviceProviderValue = 0.0;
		if(event.getServiceProviderValue() != null && !event.getServiceProviderValue().equals("")){
			serviceProviderValue = Double.parseDouble(event.getServiceProviderValue());
		}
		while(valIter.hasNext()){
		    ServiceContractValue serviceVal = (ServiceContractValue) valIter.next();
			if(serviceVal != null){
			     serviceProviderValue += serviceVal.getServiceProviderValue().doubleValue();
			}
		}
		String reqServiceProviderVal = event.getTotalValue();
		if(reqServiceProviderVal != null && !reqServiceProviderVal.equals("") && serviceProviderValue > Double.parseDouble(reqServiceProviderVal)){
			ContractVerificationResponseEvent cResp = new ContractVerificationResponseEvent();
	   	    cResp.setMessage("error.administercontract.verifyTotalValue");
	   	    cResp.setServiceProviderValue("" + serviceProviderValue);
	   	    dispatch.postEvent(cResp);
		}
	}

	/**
	 * @param event
	 */
	private void verifyContractName(VerifyContractInfoEvent event) {
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   Iterator iter = Contract.findAll("contractName",event.getContractName());
	   if(iter.hasNext()){
	   	   ContractVerificationResponseEvent cResp = new ContractVerificationResponseEvent();
	   	   cResp.setMessage("error.administercontract.verifyContractName");
	   	   cResp.setContractName(event.getContractName());
	   	   dispatch.postEvent(cResp);
	   }
	}

/**
    * @param event
    * @roseuid 451C0A6502E3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A6502E5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 451C0A6502F1
    */
   public void update(Object anObject) 
   {
    
   }
}
