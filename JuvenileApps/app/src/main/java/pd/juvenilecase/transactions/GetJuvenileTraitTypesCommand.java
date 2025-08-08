//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileTraitTypesCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.TraitType;
import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileTraitTypesCommand implements ICommand
{

	/**
	 * @roseuid 42B0634F0085
	 */
	public GetJuvenileTraitTypesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B03B3503A4
	 */
	public void execute(IEvent event)
	{
		GetJuvenileTraitTypesEvent traitEvent = (GetJuvenileTraitTypesEvent) event;

		Iterator i = null;
		
		if(traitEvent.getTraitType() == null)
		{
			i = TraitType.findAll();
		}
		else
		{		
			i = TraitType.findByType(traitEvent);
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			TraitType traitType = (TraitType) i.next();
			if (traitType.getStatus() != null && (traitType.getStatus().equalsIgnoreCase("I") || traitType.getStatus().equalsIgnoreCase("INACTIVE"))) {
               continue;
			} else {
				TraitTypeResponseEvent replyEvent = traitType.getValueObject();
				replyEvent.setTopic(traitType.getParentTypeId());
				dispatch.postEvent(replyEvent);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 42B03B3503B2
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42B03B3503B4
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42B0634F00A4
	 */
	public void update(Object updateObject)
	{

	}
}
