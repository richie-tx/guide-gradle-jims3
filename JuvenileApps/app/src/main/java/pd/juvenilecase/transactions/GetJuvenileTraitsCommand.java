//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileTraitsCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileTrait;

public class GetJuvenileTraitsCommand implements ICommand
{

	/**
	 * @roseuid 42A7588D01A1
	 */
	public GetJuvenileTraitsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2D02DF
	 */
	public void execute(IEvent event)
	{
		GetJuvenileTraitsEvent traitsEvt = (GetJuvenileTraitsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator<JuvenileTrait> i = JuvenileTrait.findAll(event);
		while (i.hasNext())
		{
			JuvenileTrait trait = (JuvenileTrait) i.next();
			//Added for U.S. #42660
			if(traitsEvt.getIsUIFacility()){
				if(trait!=null && trait.getFacilityAdmitOID()!=null && traitsEvt.getFacilityAdmitOID()!=null&&trait.getFacilityAdmitOID().equals(traitsEvt.getFacilityAdmitOID())){
					JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
					replyEvent.setTopic(replyEvent.getTraitTypeId());
					dispatch.postEvent(replyEvent);
				}
			}else{
					JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
					replyEvent.setTopic(replyEvent.getTraitTypeId());
					dispatch.postEvent(replyEvent);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 42A70E2D02EC
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2D02EE
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A70E2D02F0
	 */
	public void update(Object anObject)
	{

	}

}
