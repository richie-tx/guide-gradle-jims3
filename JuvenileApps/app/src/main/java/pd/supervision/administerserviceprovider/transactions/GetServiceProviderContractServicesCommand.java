//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\GetContractsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administercontract.GetServiceProviderContractsEvent;
import messaging.administerserviceprovider.GetServiceProviderContractServicesEvent;
import messaging.administerserviceprovider.GetServiceProviderServiceByServiceIdEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractServiceResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import pd.supervision.administerserviceprovider.administercontract.ServiceContractValue;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetServiceProviderContractServicesCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F6F029B
    */
   public GetServiceProviderContractServicesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A660209
    */
   public void execute(IEvent event) 
   {
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   	GetServiceProviderContractServicesEvent sEvent = (GetServiceProviderContractServicesEvent) event;
	   	
	    Iterator iter = ServiceContractValue.findAll("contractId",sEvent.getContractId());
	    ContractServiceResponseEvent sResp = null;
	    while(iter.hasNext()){
	    	ServiceContractValue value = (ServiceContractValue) iter.next();
			if(value != null){
				sResp =  new ContractServiceResponseEvent();
				GetServiceProviderServiceByServiceIdEvent sps = new GetServiceProviderServiceByServiceIdEvent();
		        sps.setServiceId(Integer.parseInt(value.getServiceId()));
				Iterator spIter = SearchServiceProvider.findAll(sps);
			    while(spIter.hasNext()){
			    	SearchServiceProvider sp = (SearchServiceProvider) spIter.next();
					if(sp != null && !sEvent.getServiceId().equals(sp.getServiceId())){
						sResp.setServiceProviderName(sp.getServiceProviderName());
						sResp.setServiceName(sp.getServiceName());
						sResp.setSPValue("" + value.getServiceProviderValue());
						sResp.setServiceId(value.getServiceId());
						sResp.setContractServiceId(value.getOID().toString());
						dispatch.postEvent(sResp);
					}
			    }
			}   
	    }
   }
 
   /**
    * @param event
    * @roseuid 451C0A660216
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C0A660218
    */
   public void onUnregister(IEvent event) 
   {
    
   }

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}
}
