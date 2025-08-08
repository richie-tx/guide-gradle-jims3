//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\UpdateContractCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import pd.supervision.administerserviceprovider.administercontract.Contract;
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;
import messaging.administercontract.UpdateContractEvent;
import messaging.administercontract.UpdateContractServiceEvent;
import messaging.administercontract.UpdateContractServiceValueEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Feature;
import mojo.km.utilities.MessageUtil;

public class UpdateContractCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F7002AA
    */
   public UpdateContractCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A65038D
    */
   public void execute(IEvent event) 
   {
        UpdateContractEvent updateEvent = (UpdateContractEvent) event;
		
		if(updateEvent.getContractId() != null && !updateEvent.getContractId().equals("")){
			Contract contract = Contract.find(updateEvent.getContractId());
			if(contract != null && updateEvent.getAction().equalsIgnoreCase("renew")){
			     contract.renewContract(updateEvent);
			 }else{
				 contract.updateContract(updateEvent);
				 this.updateServiceContractValue(updateEvent);
			 }
		}else{
		    Contract contract = new Contract();
		    contract.updateContract(updateEvent);
		    this.createServiceContractValue(contract,updateEvent);
		}	
   }
 
	/**
	 * @param contract
	 * @param updateEvent
	 */
	private void createServiceContractValue(Contract contract, UpdateContractEvent updateEvent) {
	    if(updateEvent.isServiceProviderFlow()){
	    	contract.createOID();
	    	ServiceContractValue value = new ServiceContractValue();
 			value.setServiceContractValue(contract.getOID().toString(), updateEvent.getServiceId(), updateEvent.getServiceProviderValue());
		 }
	}

	/**
	 * @param updateEvent
	 */
	private void updateServiceContractValue(UpdateContractEvent updateEvent) {
	     if(updateEvent.isServiceProviderFlow()){
	     	ServiceContractValue value = null;
	     	if(updateEvent.getContractServiceId() != null && !updateEvent.getContractServiceId().equals("")){
	 		     value = ServiceContractValue.find(updateEvent.getContractServiceId());
	 		     if(value != null){
	 			     value.setServiceContractValue(updateEvent.getContractId(), updateEvent.getServiceId(), updateEvent.getServiceProviderValue());
	 		     }else{
	 		     	 value = new ServiceContractValue();
	 			     value.setServiceContractValue(updateEvent.getContractId(), updateEvent.getServiceId(), updateEvent.getServiceProviderValue());
	 		     }
	 	     }
	     }
	}

/**
    * @param event
    * @roseuid 451C0A65038F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A650391
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 451C0A65039D
    */
   public void update(Object anObject) 
   {
    
   }
 }
