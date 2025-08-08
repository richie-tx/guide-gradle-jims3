/*
 * Created on Dec 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import messaging.codetable.GetJuvenileActivityCodesEvent;

import java.util.Iterator;

import pd.codetable.criminal.JuvenileActivityTypeCode;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

// FIXME deprecate GetJuvenileActivityTypeCodesCommand

/// @author C_NAggarwal
///
/// @Deprecated use GetJuvenileActivityTypeCodesCommand
public class GetJuvenileActivityCodesCommand implements ICommand
{

    /**
     *  
     */
    public GetJuvenileActivityCodesCommand()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event)
    {
        IHome home = new Home();
        Iterator iter = home.findAll(JuvenileActivityTypeCode.class);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        while (iter.hasNext())
        {
            JuvenileActivityTypeCode activityCode = (JuvenileActivityTypeCode) iter.next();
            JuvenileActivityTypeCodeResponseEvent resp = new JuvenileActivityTypeCodeResponseEvent();
            resp.setCode(activityCode.getCode());
            resp.setDescription(activityCode.getDescription());
            dispatch.postEvent(resp);
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
