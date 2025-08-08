//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\LocationController.java

package pd.supervision.administerserviceprovider.administercontract;

import java.util.Date;

import messaging.administercontract.UpdateContractEvent;
import messaging.administercontract.UpdateContractServiceEvent;


/**
 * @stereotype control
 */
public class ContractController 
{
   
   /**
    * @roseuid 448F24E9038F
    */
   public ContractController() 
   {
    
   }
   
   /**
    * @stereotype design
    * @param contractName String 
    * @param contractNum String 
    * @param contractDesc String 
    * @param agencyId String 
    * @param contractTypeId String 
    * @param toDate Date
    * @param fromDate Date
    * @param isExpired boolean
    * @roseuid 44734FE303D5
    */
   public void getContracts(String contractName, String contractNum, String contractDesc, String agencyId, String contractTypeId, Date toDate, Date fromDate, boolean isExpired) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param contractName
    * @roseuid 44734FE303D5
    */
   public void verifyContractInfo(String contractName) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param serviceId String 
    * @roseuid 44734FE303D5
    */
   public void getServiceContracts(String serviceId) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param contractId String 
    * @roseuid 44734FE303D5
    */
   public void getContractServices(String contractId) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param event UpdateContractEvent 
    * @roseuid 44734FE303D5
    */
   public void updateContract(UpdateContractEvent event) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param event UpdateContractServiceEvent
    * @roseuid 44734FE303D5
    */
   public void updateContractService(UpdateContractServiceEvent event) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param contractName String 
    * @param contractNum String 
    * @param contractDesc String 
    * @param agencyId String 
    * @param contractTypeId String 
    * @param toDate Date
    * @param fromDate Date
    * @param isExpired boolean
    * @roseuid 44734FE303D5
    */
   public void getServiceProviderContracts(String contractName, String contractNum, String contractDesc, String agencyId, String contractTypeId, Date toDate, Date fromDate, boolean isExpired) 
   {
    
   }
   /**
    * @stereotype design
    * @param contractServiceId
    * @roseuid 44734FE303D5
    */
   public void deleteContractService(String contractServiceId) 
   {
    
   }
}
