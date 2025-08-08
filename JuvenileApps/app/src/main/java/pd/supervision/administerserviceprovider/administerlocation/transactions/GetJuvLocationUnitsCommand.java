//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\GetLocationsCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

public class GetJuvLocationUnitsCommand implements ICommand
{

	/**
	 * @roseuid 447357A50143
	 */
	public GetJuvLocationUnitsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE30388
	 */
	public void execute(IEvent event)
	{		
		Iterator i = JuvLocationUnit.findAll();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		while (i.hasNext())
		{
			JuvLocationUnit juvLocUnit = (JuvLocationUnit) i.next();
			if ( "A".equals(juvLocUnit.getUnitStatusId()) )
			{
				LocationResponseEvent juvLocResponseEvent = new LocationResponseEvent();
				 juvLocResponseEvent.setLocationUnitName(juvLocUnit.getLocationUnitName());
				 juvLocResponseEvent.setJuvUnitCd(juvLocUnit.getJuvUnitCd());
				 juvLocResponseEvent.setJuvLocationUnitId(juvLocUnit.getJuvLocUnitId());
				 juvLocResponseEvent.setLocationId(juvLocUnit.getLocationId());
				 juvLocResponseEvent.setStatusId(juvLocUnit.getUnitStatusId());
				 juvLocResponseEvent.setPhoneNumber(juvLocUnit.getPhoneNumber());
				 juvLocResponseEvent.setDrugFlag(juvLocUnit.getDrugFlag());
				 
				 dispatch.postEvent(juvLocResponseEvent);
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