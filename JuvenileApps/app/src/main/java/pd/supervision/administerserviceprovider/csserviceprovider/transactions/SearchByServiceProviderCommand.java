//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\SearchByServiceProviderCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;
import messaging.csserviceprovider.SearchByServiceProviderEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class SearchByServiceProviderCommand implements ICommand 
{
   
   /**
    * @roseuid 478679650002
    */
   public SearchByServiceProviderCommand() 
   {
    
   }
   
   /* Search for service providers by name
    * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
    */
   public void execute(IEvent event) throws Exception 
   {
       	//retrieve all service providers matching the event attributes
       SearchByServiceProviderEvent search_service_provider_event = 
           							(SearchByServiceProviderEvent)event;
       List service_providers = CSServiceProviderHelper.searchServiceProvider(search_service_provider_event);
       
       	//loop thru all service providers and create response events
       int num_service_providers = service_providers.size();
       List service_provider_responses = new ArrayList(num_service_providers);
       for (int i=0;i<num_service_providers;i++)
       {
           	//create response event from service provider and add to response list
           CSServiceProviderResponseEvent service_provider_response = 
               	CSServiceProviderHelper.getServiceProviderResponseEvent(
                       						(CSServiceProvider)service_providers.get(i));            
           service_provider_responses.add(service_provider_response);
       }
       
       	//post replies containing service provider responses
       MessageUtil.postReplies(service_provider_responses);
   }//end of execute()
   
   /**
    * @param event
    * @roseuid 47856C150191
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C15019E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 478679650031
    */
   public void update(Object updateObject) 
   {
    
   }
}
