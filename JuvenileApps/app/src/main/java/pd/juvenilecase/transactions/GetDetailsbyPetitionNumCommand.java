package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.GetDetailsbyPetitionNumEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import pd.juvenilecase.JJSCourt;
import pd.juvenilewarrant.JJSPetition;

/**
 * 
 * GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
 *
 */
public class GetDetailsbyPetitionNumCommand implements ICommand{

    @Override
    public void execute(IEvent event) throws Exception {
	GetDetailsbyPetitionNumEvent evt =(GetDetailsbyPetitionNumEvent)event;
		
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSPetition> Itr = JJSPetition.findAll(evt);
	while (Itr.hasNext())
	{
	    JJSPetition pet =  Itr.next();
	    PetitionResponseEvent resp = pet.valueObject();
	    if (resp != null)
	    {
		dispatch.postEvent(resp);
	    }
	}
    }
}
