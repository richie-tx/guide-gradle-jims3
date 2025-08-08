/*
 * Created on Jul 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;

/**
 * @author jfisher
 *  
 */
public class GetJuvenileCourtsCommand implements ICommand
{
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent anEvent) throws Exception
    {
        GetJuvenileCourtsEvent event = (GetJuvenileCourtsEvent) anEvent;

        if (event.getCourt() == null)
        {
            this.sendMultiple();
        }
        else
        {
            this.sendSingle(event);
        }
    }

    private void sendSingle(GetJuvenileCourtsEvent anEvent)
    {
        IHome home = new Home();
        JuvenileCourt court = (JuvenileCourt) home.find(anEvent.getCourt(), JuvenileCourt.class);
        if (court != null)
        {
            JuvenileCourtResponseEvent event = PDCriminalCodeTableHelper.getJuvenileCourtResponseEvent(court);
            MessageUtil.postReply(event);
        }
    }

    private void sendMultiple()
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        IHome home = new Home();
        Iterator i = home.findAll(JuvenileCourt.class);

        while (i.hasNext())
        {
            JuvenileCourt court = (JuvenileCourt) i.next();
            if( court.getInactiveInd() == null ){
        	//pull only active codes
        	 JuvenileCourtResponseEvent event = PDCriminalCodeTableHelper.getJuvenileCourtResponseEvent(court);
                 dispatch.postEvent(event);
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
