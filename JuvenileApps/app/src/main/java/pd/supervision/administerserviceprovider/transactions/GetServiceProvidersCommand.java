//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProvidersCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceProvidersEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;

public class GetServiceProvidersCommand implements ICommand 
{
   
   /**
    * @roseuid 450EF788031A
    */
   public GetServiceProvidersCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450EF3D6016E
    */
   public void execute(IEvent event) 
   {
	GetServiceProvidersEvent serviceProviderEvent = (GetServiceProvidersEvent)event;

	IHome home = new Home();
	MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(serviceProviderEvent, JuvenileServiceProvider.class);
	 
	int totalRecords = metaData.getCount();
	 
	if (totalRecords>2000){
		ErrorResponseEvent errorResp = new ErrorResponseEvent();
		errorResp.setMessage("error.max.limit.exceeded");			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorResp);
	}
	else { 
		Iterator iter = JuvenileServiceProvider.findAll(serviceProviderEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()) {
			JuvenileServiceProvider serviceProvider = (JuvenileServiceProvider) iter.next();		
		    ServiceProviderResponseEvent serviceProviderResponseEvent = new ServiceProviderResponseEvent();
		    serviceProviderResponseEvent.setJuvServProviderName(serviceProvider.getServiceProviderName());
		    serviceProviderResponseEvent.setJuvServProviderId(serviceProvider.getOID().toString());
		    serviceProviderResponseEvent.setInHouse(serviceProvider.getInHouse());
		    serviceProviderResponseEvent.setStatusId(serviceProvider.getStatusId());
		    serviceProviderResponseEvent.setStatusChangeDate(serviceProvider.getStatusChangeDate());
		    
			dispatch.postEvent(serviceProviderResponseEvent);	 
		}  
	}
   }
   
   /**
    * @param event
    * @roseuid 450EF3D60170
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450EF3D6017B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 450EF3D6017D
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
