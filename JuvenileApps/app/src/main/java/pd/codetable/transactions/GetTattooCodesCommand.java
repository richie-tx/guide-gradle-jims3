package pd.codetable.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.codetable.GetTattooCodesEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.person.ScarsMarksTattoosCode;

/**
 * @author Jim Fisher
 *
 */
public class GetTattooCodesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
	    GetTattooCodesEvent event = (GetTattooCodesEvent) anEvent;

        if (event.getCodes() == null)
        {
            this.sendAllCodes();
        }
        else
        {
            this.sendSpecificCodes(event.getCodes());
        }
	}
	
	/**
     * @param codes
     */
    private void sendSpecificCodes(String[] codes)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        for (int i = 0; i < codes.length; i++)
        {
            ScarsMarksTattoosCode scarMark = ScarsMarksTattoosCode.find(codes[i]);
            ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
            scarMark.fill(codeResponse);
            dispatch.postEvent(codeResponse);
        }
    }

    private void sendAllCodes()
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        Collection scarsMarks = ScarsMarksTattoosCode.findAllTattoos();
        Iterator i = scarsMarks.iterator();
        while (i.hasNext())
        {
            ScarsMarksTattoosCode scarMark = (ScarsMarksTattoosCode) i.next();
            ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
            scarMark.fill(codeResponse);
            dispatch.postEvent(codeResponse);
        }
    }

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}
