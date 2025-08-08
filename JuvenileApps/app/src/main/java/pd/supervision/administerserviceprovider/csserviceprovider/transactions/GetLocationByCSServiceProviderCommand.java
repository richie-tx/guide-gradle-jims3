/**
 * 
 */
package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.csserviceprovider.GetLocationByCSServiceProviderEvent;
import messaging.csserviceprovider.GetSPProgramLocationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 */
public class GetLocationByCSServiceProviderCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		// TODO Auto-generated method stub
		GetLocationByCSServiceProviderEvent get_loc_event 
					= (GetLocationByCSServiceProviderEvent)event;
		
		List<Location> location_list = Location.findAll(get_loc_event);
		List location_responses = new ArrayList<LocationResponseEvent>();
		if (location_list != null)
		{
			int location_list_size = location_list.size(); 			
			for (int i=0; i<location_list_size;i++)
			{
				Location this_location = location_list.get(i);
				this_location.getServiceProviders();
				location_responses.add(this_location.getValueObject());
			}
		}
			//post responses
		MessageUtil.postReplies(location_responses);
	}

}
