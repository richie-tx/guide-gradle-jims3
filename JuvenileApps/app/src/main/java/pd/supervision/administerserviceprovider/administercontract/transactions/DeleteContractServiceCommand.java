//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\UpdateContractServiceCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.administercontract.DeleteContractServiceEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administercontract.Contract;
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;

public class DeleteContractServiceCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F71020E
    */
   public DeleteContractServiceCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A66014E
    */
   public void execute(IEvent event) 
   {
	   DeleteContractServiceEvent requestEvent = (DeleteContractServiceEvent) event;
	   ServiceContractValue serviceContractValue = ServiceContractValue.find(requestEvent.getContractServiceId());
	   if(serviceContractValue != null){
			serviceContractValue.delete();
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
