//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceLocationServiceProvidersCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administerserviceprovider.GetServiceLocationServiceProvidersEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;

public class GetServiceLocationServiceProvidersCommand implements ICommand 
{
   
   /**
    * @roseuid 4511566700F5
    */
   public GetServiceLocationServiceProvidersCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45104B4A01DF
    */
   public void execute(IEvent event) {
		GetServiceLocationServiceProvidersEvent getServiceLocationServiceProvidersEvent = (GetServiceLocationServiceProvidersEvent) event;
		Iterator spIter = JuvenileServiceProvider.findAll(getServiceLocationServiceProvidersEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		HashMap results = new HashMap();
		
		while (spIter.hasNext()) {
			
			JuvenileServiceProvider sp = (JuvenileServiceProvider) spIter.next();			
			if (sp != null) {
				String mapKey = sp.getServiceProviderId()+ sp.getServiceId() + sp.getServiceTypeId();
				if (!results.containsKey(mapKey)){
					JuvenileServiceProviderResponseEvent serviceProviderResponseEvent = new JuvenileServiceProviderResponseEvent();
					serviceProviderResponseEvent.setServiceProviderName(sp.getServiceProviderName());
					serviceProviderResponseEvent.setProgramName(sp.getProgramName());
					serviceProviderResponseEvent.setServiceProviderId(sp.getServiceProviderId());
					serviceProviderResponseEvent.setInHouse(sp.getInHouse());
					serviceProviderResponseEvent.setServiceName(sp.getServiceName());
					
					if (sp.getStatus()!=null){
						serviceProviderResponseEvent.setStatus(sp.getStatus().getDescription());
					}
					if (sp.getEventType()!=null){
						serviceProviderResponseEvent.setServiceType(sp.getEventType().getDescription());
					}
					results.put(mapKey,serviceProviderResponseEvent);
				}				
			}			
		}
		
		Iterator iter = results.entrySet().iterator();
		
		while (iter.hasNext()){
			Map.Entry jspevt = (Map.Entry)iter.next(); 
			dispatch.postEvent((JuvenileServiceProviderResponseEvent)jspevt.getValue());
		}

	}    
   
   
   /**
    * @param event
    * @roseuid 45104B4A01EC
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45104B4A01EE
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45104B4A01F0
    */
   public void update(Object anObject) 
   {
    
   }
   
}
