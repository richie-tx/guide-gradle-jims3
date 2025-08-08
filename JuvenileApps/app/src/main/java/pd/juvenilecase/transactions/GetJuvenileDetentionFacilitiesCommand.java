//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefilePetitionCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSFacility;

public class GetJuvenileDetentionFacilitiesCommand implements ICommand
{

	/**
	 * @roseuid 42A9A3040091
	 */
	public GetJuvenileDetentionFacilitiesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990128
	 */
	public void execute(IEvent event)
	{
		GetJuvenileDetentionFacilitiesEvent pet = (GetJuvenileDetentionFacilitiesEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator<JJSFacility> facItr = JJSFacility.findAll(pet);
		while (facItr.hasNext()) {
			JJSFacility fac =  facItr.next();
			//RRY added value object
			JuvenileDetentionFacilitiesResponseEvent resp = fac.valueObject();
			dispatch.postEvent(resp);
		}
	}
	

	/**
	 * @param event
	 * @roseuid 42A99B990131
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990133
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B99013A
	 */
	public void update(Object anObject)
	{

	}
}
