//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\SearchByProgramCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;
import messaging.csserviceprovider.SearchByProgramEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class SearchByProgramCommand implements ICommand 
{
   
   /**
    * @roseuid 4786795902D1
    */
   public SearchByProgramCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C140121
    */
   public void execute(IEvent event) 
   {
      	//retrieve all service providers matching the event attributes
       SearchByProgramEvent search_service_provider_event = 
           							(SearchByProgramEvent)event;
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
    * @roseuid 47856C140123
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C140131
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47856C140133
    */
   public void update(Object anObject) 
   {
    
   }
   
}
