package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileContact;
import pd.juvenile.JuvenileHelper;

import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileContactsCommand implements ICommand
{

    /**
     * @roseuid 42B18DC102BF
     */
    public GetJuvenileContactsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42B18306006E
     */
    public void execute(IEvent event)
    {
        GetJuvenileContactsEvent requestEvent = (GetJuvenileContactsEvent) event;
        Iterator contacts = JuvenileContact.findAll("juvenileId", requestEvent.getJuvenileNum());

        //	Get the IDispatch to post to
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        // Iterate through the warrants and post the JuvenileWarrantServiceResponseEvent
        // for each
        while (contacts.hasNext())
        {
            JuvenileContact contact = (JuvenileContact) contacts.next();
            JuvenileContactResponseEvent contactRespEvent = JuvenileHelper.getJuvenileContactResponseEvent(contact);
            if (contactRespEvent != null)
            {
                dispatch.postEvent(contactRespEvent);
            }
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
