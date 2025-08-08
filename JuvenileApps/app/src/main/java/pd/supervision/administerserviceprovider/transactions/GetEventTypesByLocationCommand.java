//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetEventTypesByLocationCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.administerserviceprovider.GetEventTypesByLocationEvent;
import messaging.administerserviceprovider.reply.ServiceTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.PDAdminsterServiceProviderHelper;
import pd.supervision.administerserviceprovider.Service;
import pd.supervision.administerserviceprovider.ServiceLocation;

public class GetEventTypesByLocationCommand implements ICommand
{

	/**
	 * @roseuid 44805C7B0356
	 */
	public GetEventTypesByLocationCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 447F49960247
	 */
	public void execute(IEvent event)
	{
		GetEventTypesByLocationEvent gEvent = (GetEventTypesByLocationEvent)event; 
		
		Iterator serviceLocationIterator =
					 ServiceLocation.findAll("locationId", gEvent.getLocationId());
				
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		HashMap map = new HashMap();
		while(serviceLocationIterator.hasNext()){
			ServiceLocation serviceLocation = (ServiceLocation) serviceLocationIterator.next();
			Service service = Service.find(serviceLocation.getServiceId());
			if(service != null){
				ServiceTypeResponseEvent sTREvent = PDAdminsterServiceProviderHelper.getServiceTypeResponseEvent(service);
				if(!map.containsKey(sTREvent.getServiceTypeId())){
			    	map.put(sTREvent.getServiceTypeId(), sTREvent);
					dispatch.postEvent(sTREvent);
			    }			
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 447F49960249
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 447F4996024B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 447F49960257
	 */
	public void update(Object anObject)
	{

	}
}