//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetProviderServicesCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetProviderServicesEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDConstants;
import pd.supervision.administerserviceprovider.SearchServiceProvider;

public class GetProviderServicesCommand implements ICommand 
{
   
   /**
    * @roseuid 450ACDB1009C
    */
   public GetProviderServicesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA17902C3
    */
   public void execute(IEvent event) 
   {
   	GetProviderServicesEvent reqEvent = 
		  (GetProviderServicesEvent)event;

	IHome home = new Home();
	MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(reqEvent, SearchServiceProvider.class);
	 
	int totalRecords = metaData.getCount();
	 
	if (totalRecords > PDConstants.SEARCH_LIMIT){
		ErrorResponseEvent errorResp = new ErrorResponseEvent();
		errorResp.setMessage("error.max.limit.exceeded");			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorResp);
	}
	else
	{
		
		
		Iterator iter = SearchServiceProvider.findAll(reqEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			SearchServiceProvider serviceProvider = (SearchServiceProvider) iter.next();		
		    ServiceResponseEvent respEvent = new ServiceResponseEvent();
		    
		    respEvent.setServiceProviderId(serviceProvider.getJuvenileServiceProviderId());
		    respEvent.setServiceProviderName(serviceProvider.getServiceProviderName());
		    respEvent.setInHouse(serviceProvider.getInHouse());
		    respEvent.setServiceProviderStatusId(serviceProvider.getServiceProviderStatusId());
		    respEvent.setProgramName(serviceProvider.getProgramName());
		    respEvent.setProgramCode(serviceProvider.getProgramCodeId());
		    respEvent.setTargetInterventionId(serviceProvider.getTargetInterventionId());
		    respEvent.setProgramId(serviceProvider.getProgramId());
		    respEvent.setServiceName(serviceProvider.getServiceName());
		    respEvent.setServiceTypeId(serviceProvider.getServiceTypeId());
		    dispatch.postEvent(respEvent);	 
		}  
	}
   }
   
   /**
    * @param event
    * @roseuid 450AA17902D0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA17902D2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 450AA17902E1
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
