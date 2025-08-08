//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\GetLocationsCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.GetJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

public class GetJuvLocationUnitsByLocationIdCommand implements ICommand
{

	/**
	 * @roseuid 447357A50143
	 */
	public GetJuvLocationUnitsByLocationIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE30388
	 */
	public void execute(IEvent event)
	{
		GetJuvLocationUnitsByLocationIdEvent locUnitEvent = (GetJuvLocationUnitsByLocationIdEvent) event;
		Iterator i = JuvLocationUnit.findLocUnitByLocationId(locUnitEvent);	

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			JuvLocationUnit juvLocUnit = (JuvLocationUnit) i.next();
			//LocationResponseEvent replyEvent = location.getValueObject();
			if("A".equals(juvLocUnit.getLocationStatus()))
			{
				if ( "A".equals(juvLocUnit.getUnitStatusId())
					&& juvLocUnit.getOfficerProfileFlag() == 1 )
				{
					LocationResponseEvent juvLocResponseEvent = new LocationResponseEvent();
					 juvLocResponseEvent.setLocationUnitName(juvLocUnit.getLocationUnitName());
					 juvLocResponseEvent.setJuvUnitCd(juvLocUnit.getJuvUnitCd());
					 juvLocResponseEvent.setLocationId(juvLocUnit.getLocationId());
					 juvLocResponseEvent.setJuvLocationUnitId(juvLocUnit.getJuvLocUnitId());
					 juvLocResponseEvent.setOfficerProfileFlag(juvLocUnit.getOfficerProfileFlag());
					 dispatch.postEvent(juvLocResponseEvent);
				}
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