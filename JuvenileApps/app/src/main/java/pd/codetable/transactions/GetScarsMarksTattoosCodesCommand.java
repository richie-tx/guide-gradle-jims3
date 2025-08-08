/*
 * Created on Jul 15, 2005
 *
 */
package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetScarsMarksTattoosCodesEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.person.ScarsMarksTattoosCode;

/**
 * @author jfisher
 *  
 */
public class GetScarsMarksTattoosCodesCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent anEvent) throws Exception
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        GetScarsMarksTattoosCodesEvent event = (GetScarsMarksTattoosCodesEvent) anEvent;

        String[] codes = event.getCodes();

        if (event.getCode() != null)
        {
            ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(event.getCode());
            ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
            code.fill(codeResponse);
            dispatch.postEvent(codeResponse);
        }
        else if (codes != null && codes.length > 0)
        {
            for (int i = 0; i < codes.length; i++)
            {
                ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(codes[i]);
                ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
                code.fill(codeResponse);
                dispatch.postEvent(codeResponse);
            }
        }
        else
        {
            Iterator i = ScarsMarksTattoosCode.findAll();
            while (i.hasNext())
            {
                ScarsMarksTattoosCode code = (ScarsMarksTattoosCode) i.next();
                ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
                code.fill(codeResponse);
                dispatch.postEvent(codeResponse);
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
