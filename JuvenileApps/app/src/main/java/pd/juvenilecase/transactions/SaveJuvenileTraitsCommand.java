//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileTraitsCommand.java

package pd.juvenilecase.transactions;

import java.util.Enumeration;

import pd.juvenilecase.JuvenileTrait;
import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenileTraitsCommand implements ICommand
{

	/**
	 * @roseuid 42A75896000B
	 */
	public SaveJuvenileTraitsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0318
	 */
	public void execute(IEvent event)
	{
		SaveJuvenileTraitsEvent saveEvent = (SaveJuvenileTraitsEvent) event;

		Enumeration events = saveEvent.getRequests();

		while (events.hasMoreElements())
		{
			JuvenileTraitResponseEvent juvTraitEvent = (JuvenileTraitResponseEvent) events.nextElement();
			JuvenileTrait juvTrait = JuvenileTrait.find(juvTraitEvent.getJuvenileTraitId());
			if (juvTrait != null )
			{
				juvTrait.setOID(juvTraitEvent.getJuvenileTraitId());
				juvTrait.setStatusId(juvTraitEvent.getStatusId());
			} else {
				juvTrait = new JuvenileTrait();
				juvTrait.hydrate(juvTraitEvent);
				juvTrait.hydrate(saveEvent);
			}	
		}

	}

	/**
	 * @param event
	 * @roseuid 42A731DC031A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0325
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A731DC0327
	 */
	public void update(Object anObject)
	{

	}

}
