/*
 * Created on Jan 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import messaging.csserviceprovider.SearchByServiceProviderEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerserviceprovider.csserviceprovider.*;
import java.util.*;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSSearchServiceProviderByNameCommand implements ICommand {

    /* Search for service providers by name
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception 
    {
        	//retrieve all service providers matching the event attributes
        SearchByServiceProviderEvent search_service_provider_event = 
            							(SearchByServiceProviderEvent)event;
        List service_providers = CSServiceProviderHelper.searchByServiceProviderName(search_service_provider_event);
        
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

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
        // TODO Auto-generated method stub

    }

}
