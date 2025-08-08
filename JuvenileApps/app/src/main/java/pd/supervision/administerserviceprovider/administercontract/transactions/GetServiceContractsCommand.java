//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\GetServiceContractsCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.text.DecimalFormat;
import java.util.Iterator;

import messaging.administercontract.GetServiceContractsEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.supervision.administerserviceprovider.administercontract.Contract;
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetServiceContractsCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F720327
    */
   public GetServiceContractsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C44A30323
    */
   public void execute(IEvent event) 
   {
       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   GetServiceContractsEvent serviceContractEvent = (GetServiceContractsEvent) event;
		
	   ResponseContextFactory respFac = new ResponseContextFactory();
	   ResponseCreator aCreator =  null;
   	   try {
		   aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.CONTRACT_RESPONSE_LOCATOR);
	   } catch (ReflectionException e) {
		   e.printStackTrace();
	   }
		
	   Iterator iter = ServiceContractValue.findAll("serviceId",serviceContractEvent.getServiceId());
	   while(iter.hasNext()){
			ServiceContractValue serviceContractValue = (ServiceContractValue) iter.next();
			if(serviceContractValue != null){
				Contract contract = Contract.find(serviceContractValue.getContractId());
				if(contract != null){
					ContractResponseEvent resp = (ContractResponseEvent) aCreator.create(contract);
					resp.setServiceProviderValue(formatDecimal(serviceContractValue.getServiceProviderValue().doubleValue()));
					resp.setAvailableContractValue(formatDecimal(contract.getTotalValue().doubleValue() - serviceContractValue.getServiceProviderValue().doubleValue()));
					resp.setContractServiceId(serviceContractValue.getOID().toString());
					dispatch.postEvent(resp);
				}
			}
		}    
   }
   
   /**
	 * @param d
	 * @return String
	 */
	private String formatDecimal(double d) {
		DecimalFormat myFormatter = new DecimalFormat("######0.00");
		return myFormatter.format(d);
	}
   
   /**
    * @param event
    * @roseuid 451C44A30325
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C44A30330
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 451C44A30332
    */
   public void update(Object anObject) 
   {
    
   }
}
