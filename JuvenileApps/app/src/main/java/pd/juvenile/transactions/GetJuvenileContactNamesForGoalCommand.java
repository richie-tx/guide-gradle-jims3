package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileContact;

import messaging.juvenile.GetJuvenileContactNamesForGoalEvent;
import messaging.juvenile.reply.JuvenileContactNameResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileContactNamesForGoalCommand implements ICommand
{
    /**
     * @roseuid 42B18DC102BF
     */
    public GetJuvenileContactNamesForGoalCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42B18306006E
     */
    public void execute(IEvent event)
    {
        GetJuvenileContactNamesForGoalEvent requestEvent = (GetJuvenileContactNamesForGoalEvent) event;
        Iterator contacts = JuvenileContact.findAll("juvenileId", requestEvent.getJuvenileNum());

        //	Get the IDispatch to post to
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        // Iterate through the warrants and post the JuvenileWarrantServiceResponseEvent
        // for each
        while (contacts.hasNext())
        {
            JuvenileContact contact = (JuvenileContact) contacts.next();
            JuvenileContactNameResponseEvent contactRespEvent = new JuvenileContactNameResponseEvent();
            contactRespEvent.setFirstName(contact.getFirstName());
            contactRespEvent.setLastName(contact.getLastName());
            contactRespEvent.setMiddleName(contact.getMiddleName());
            contactRespEvent.setContactNum(contact.getOID().toString());
            dispatch.postEvent(contactRespEvent);
        }
    }

    /**
     * @param event
     * @roseuid 42B183060070
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42B18306007E
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42B183060080
     */
    public void update(Object anObject)
    {

    }

}
