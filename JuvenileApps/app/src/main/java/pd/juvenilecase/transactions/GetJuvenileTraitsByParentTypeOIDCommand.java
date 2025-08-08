package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeOIDEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileTrait;
/**
 * @author sthyagarajan
 * ER Changes JIMS200074578
 */
public class GetJuvenileTraitsByParentTypeOIDCommand implements ICommand {

	@Override
	public void execute(IEvent event) throws Exception {
		GetJuvenileTraitsByParentTypeOIDEvent traitEvent = (GetJuvenileTraitsByParentTypeOIDEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		Iterator<JuvenileTrait> i = null;
	
		if(traitEvent.getJuvenileNum() != null && traitEvent.getTraitParent()!=null )//&& traitEvent.getFacilityAdmitOID()!=null
		{
			i = JuvenileTrait.findAll(traitEvent);
			if(i!=null){
				while (i.hasNext())
				{
					JuvenileTrait trait = i.next();
					JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
					replyEvent.setTopic(trait.getTraitParent());
					dispatch.postEvent(replyEvent);
				}
				return;
			}
		}
		return;

	}

}
