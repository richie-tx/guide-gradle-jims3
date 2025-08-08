/*
 * Created on Aug 2, 2005
 *
 */
package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.juvenilewarrant.GetJJSPetitionsByJuvNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import pd.juvenilewarrant.JJSPetition;

/**
 * @author jfisher
 *  
 */
public class GetJJSPetitionsByJuvNumCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        
        GetJJSPetitionsByJuvNumEvent evt =(GetJJSPetitionsByJuvNumEvent)event;
        
        Iterator<JJSPetition> petition = new ArrayList().iterator(); //empty iterator
        petition = JJSPetition.findAll("juvenileNum", evt.getJuvenileNum()); //regular find. //juvenile and referral.
      	
        while (petition.hasNext())
        {
            JJSPetition pet = (JJSPetition) petition.next();
            ResponseEvent petitionEvent = pet.valueObject();
            dispatch.postEvent(petitionEvent);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
        // TODO Auto-generated method stub

    }

}
