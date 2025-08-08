//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\GetLocationsCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.GetLocationsByAgencyEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class GetLocationsByAgencyCommand implements ICommand
{

	/**
	 * @roseuid 447357A50143
	 */
	public GetLocationsByAgencyCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE30388
	 */
	public void execute(IEvent event)
	{
		GetLocationsByAgencyEvent locationEvent = (GetLocationsByAgencyEvent) event;
		Iterator i = Location.findLocationByAgency(locationEvent);		

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			Location location = (Location) i.next();
			LocationResponseEvent replyEvent = location.getValueObject();
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