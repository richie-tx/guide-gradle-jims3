//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\UpdateContractServiceCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.administercontract.UpdateContractServiceEvent;
import messaging.administercontract.UpdateContractServiceValueEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerserviceprovider.administercontract.Contract;
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;

public class UpdateContractServiceCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F71020E
    */
   public UpdateContractServiceCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A66014E
    */
   public void execute(IEvent event) 
   {
	   UpdateContractServiceEvent contractRequestEvent = (UpdateContractServiceEvent) event;
	   HashMap map = new HashMap();
	   Iterator updateIter = MessageUtil.compositeToCollection(contractRequestEvent, UpdateContractServiceValueEvent.class).iterator();
	   ServiceContractValue serviceContractValue = null;
	   while(updateIter.hasNext()){
       	    UpdateContractServiceValueEvent vEvent = (UpdateContractServiceValueEvent) updateIter.next();
       	    if(vEvent.getContractServiceId() != null){
       	    	serviceContractValue = ServiceContractValue.find(vEvent.getContractServiceId());
		       	if(serviceContractValue != null){
					serviceContractValue.setServiceContractValue(vEvent,contractRequestEvent);
				}
       	    }else{
				serviceContractValue = new ServiceContractValue();
				serviceContractValue.setServiceContractValue(vEvent,contractRequestEvent);
			}
	       	map.put(vEvent.getContractId(),vEvent.getContractId());
       }
       
       Iterator existingIter = ServiceContractValue.findAll("serviceId", contractRequestEvent.getServiceId());
       while(existingIter.hasNext()){
       	   ServiceContractValue val = (ServiceContractValue) existingIter.next();
       	   if(val != null && !map.containsKey(val.getContractId())){
       	   	   val.delete();
       	   }
       }
    }
   

   /**
 	 * @param contract
 	 * @return
 	 */
 	private HashMap existingServiceContractValues(Contract contract) {
 		Iterator existingContractIter = contract.getServiceContractValues().iterator();
 		HashMap map = new HashMap();
 		while(existingContractIter.hasNext()){
 			ServiceContractValue serviceContractValue = (ServiceContractValue) existingContractIter.next();
 			if(serviceContractValue != null){
 				map.put(serviceContractValue.getContractServiceId(),serviceContractValue);
 			}
 		}
 		return map;
 	}

   
   /**
    * @param event
    * @roseuid 451C0A66015B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A66015D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 451C0A66015F
    */
   public void update(Object anObject) 
   {
    
   }
}
