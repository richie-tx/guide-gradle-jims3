/*
 * Created on May 1, 2006
 *
 */
package pd.contact.party.transactions;

import java.util.Iterator;

import naming.PDConstants;

import pd.contact.party.Party;
import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.party.GetPartiesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author dgibler
 *  
 */
public class GetPartiesCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
        GetPartiesEvent requestEvent = (GetPartiesEvent) event;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        IHome home = new Home();
        if(requestEvent.getLastName() == null || requestEvent.getLastName().equals("")){
        	
        	requestEvent.setLastName("*");
        }
        MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(event, Party.class);

        int totalRecords = metaData.getCount();

        if (totalRecords > PDConstants.SEARCH_LIMIT)
        {
            CountInfoMessage infoEvent = new CountInfoMessage();
            infoEvent.setMessage("error.max.limit.exceeded");
            dispatch.postEvent(infoEvent);
        }
        else
        {

            Iterator partyIter = Party.findAll(requestEvent);
            if (partyIter != null && partyIter.hasNext())
            {
                Party party = null;
                PartyListResponseEvent partyResponseEvent = null;
                while (partyIter.hasNext())
                {
                    party = (Party) partyIter.next();
                    partyResponseEvent = party.getPartyListResponseEvent();
                    dispatch.postEvent(partyResponseEvent);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {

    }

}
