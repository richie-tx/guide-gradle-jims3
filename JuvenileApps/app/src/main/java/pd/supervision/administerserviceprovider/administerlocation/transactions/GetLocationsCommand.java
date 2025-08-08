//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\GetLocationsCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetLocationsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDConstants;
import pd.address.PDAddressHelper;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class GetLocationsCommand implements ICommand
{

	/**
	 * @roseuid 447357A50143
	 */
	public GetLocationsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE30388
	 */
	public void execute(IEvent event)
	{
		GetLocationsEvent locationEvent = (GetLocationsEvent) event;
		IHome home = new Home();
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(event, Location.class);
		 
		int totalRecords = metaData.getCount();
		 
		if (totalRecords > PDConstants.SEARCH_LIMIT){
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("error.max.limit.exceeded");			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorResp);
		}
		else {
			Iterator i = Location.findLocation(locationEvent);		
	
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			//String location_id = "";
			HashMap locationMap = new HashMap();		
			while (i.hasNext())
			{
				Location location = (Location) i.next();			
					String locationId = location.getLocationId();				
					if (locationMap.containsKey(locationId)){				
						ArrayList locList = (ArrayList)locationMap.get(locationId);
						locList.add(location);								
					}else{
						ArrayList locList = new ArrayList();
						locList.add(location);
						locationMap.put(locationId,locList);
					}			
			}	
			
			Iterator iter = locationMap.values().iterator();
			
			while (iter.hasNext()){			
				ArrayList locList = (ArrayList)iter.next();
				Iterator locIter = locList.iterator();
				LocationResponseEvent replyEvent = null;
				while (locIter.hasNext()){
					Location location = (Location)locIter.next();
					replyEvent = location.getValueObject();
					if (location.getAddress()!=null){
						AddressResponseEvent are = PDAddressHelper.getAddressResponseEvent(location.getAddress());
						replyEvent.setLocationAddress(are);
					}	
					if(location.getServiceProviderName()!=null && !"".equals(location.getServiceProviderName()))
					{
						break;
					}				
				}
				dispatch.postEvent(replyEvent);
			}		
		}
	}

	/**
	 * @param event
	 * @roseuid 44734FE30393
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE30395
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FE30397
	 */
	public void update(Object anObject)
	{

	}
}