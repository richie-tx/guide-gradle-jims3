//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\GetLocationsCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.address.PDAddressHelper;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class GetLocationCommand implements ICommand
{

	/**
	 * @roseuid 447357A50143
	 */
	public GetLocationCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE30388
	 */
	public void execute(IEvent event)
	{
		GetLocationEvent locationEvent = (GetLocationEvent) event;
		String locationId = locationEvent.getLocationId();
		if(locationId != null && !locationId.equals("")){
			Location location  = Location.find(locationEvent.getLocationId());		
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			LocationResponseEvent replyEvent = location.getValueObject();
			if (location.getAddress()!=null){
				AddressResponseEvent are = PDAddressHelper.getAddressResponseEvent(location.getAddress());
				replyEvent.setLocationAddress(are);
			}			
			dispatch.postEvent(replyEvent);		
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