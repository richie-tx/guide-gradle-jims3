package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;
import messaging.csserviceprovider.GetCSServiceProvidersByLocationEvent;
import messaging.csserviceprovider.GetSPLocationEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetCSServiceProvidersByLocationCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		GetCSServiceProvidersByLocationEvent get_sp_event = 
			(GetCSServiceProvidersByLocationEvent)event;
		GetSPLocationEvent loc_sp_event = new GetSPLocationEvent();
		loc_sp_event.setLocationId(get_sp_event.getLocationId());
		
			//retrieve service providers matching given location
		List<CSServiceProvider> loc_service_providers = 
			CollectionUtil.iteratorToList(
				CSServiceProvider.findAll(loc_sp_event));
		
			//loop through results and eliminate duplicates (occurs if multiple language codes selected for program)
		Hashtable<String, CSServiceProvider> sp_hashtable = new Hashtable();
		List<CSServiceProviderResponseEvent> sp_return_list = new ArrayList<CSServiceProviderResponseEvent>();
		
		int num_results = loc_service_providers.size();
		for (int i=0;i<num_results;i++)
		{
			CSServiceProvider this_provider = loc_service_providers.get(i);
			//check if program is active at this location and check if program is active 
			if(StringUtils.isNotEmpty(this_provider.getValidLocStatus()) && !this_provider.getValidLocStatus().equals("I")
					&& StringUtils.isNotEmpty(this_provider.getProgramStatusCode()) && !this_provider.getProgramStatusCode().equals("I")) {
				String hash_key = 
					this_provider.getServiceProviderId() + ":" 
						+ this_provider.getProgramId() + ":"
						+ this_provider.getProgramLocationId();
				
					//check if this [provider:program:location] tuple is already in list
				if (!sp_hashtable.containsKey(hash_key))
				{
					sp_hashtable.put(hash_key, this_provider);
					sp_return_list.add(
							CSServiceProviderHelper.
								getServiceProviderResponseEvent(this_provider));
				}
			}
		}
		
			//return matching service providers
		MessageUtil.postReplies(sp_return_list);

	}

}
