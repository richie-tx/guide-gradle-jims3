package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.juvenilewarrant.GetPetitionByCJISEvent;
import messaging.juvenilewarrant.GetPetitionBySequenceEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import pd.juvenilewarrant.JJSPetition;

public class GetPetitionByCJISCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetPetitionByCJISEvent evt = (GetPetitionByCJISEvent) event;

	Iterator<JJSPetition> petition = new ArrayList().iterator(); // empty iterator

	petition = JJSPetition.findAll(evt); // regular find. //juvenile,referral and seq num.

	if (petition.hasNext())
	{
	    JJSPetition pet = (JJSPetition) petition.next();
	    ResponseEvent petitionEvent = pet.valueObject();
	    dispatch.postEvent(petitionEvent);
	}
    }

}
