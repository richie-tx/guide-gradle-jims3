/*
 * Created on Mar 21, 2006
 *
 */
package pd.codetable.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.codetable.GetScarMarkCodesEvent;
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
public class GetScarMarkCodesCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent anEvent) throws Exception
    {
        GetScarMarkCodesEvent event = (GetScarMarkCodesEvent) anEvent;

        if (event.getCodes() == null)
        {
            this.sendAllCodes();
        }
        else
        {
            this.sendSpecificCodes(event);
        }
    }

    /**
     * @param codes
     */
    private void sendSpecificCodes(GetScarMarkCodesEvent anEvent)
    {
        boolean isScarsMarksCategory = PDCodeTableConstants.SCAR_CATEGORY.equals(anEvent.getCategory());
        
        String[] codes = anEvent.getCodes();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        ScarsMarksTattoosCode scarMark;
        for (int i = 0; i < codes.length; i++)
        {
            if(isScarsMarksCategory)
            {
                scarMark = ScarsMarksTattoosCode.findScarMark(codes[i]);
            }
            else
            {
                scarMark = ScarsMarksTattoosCode.find(codes[i]);
            }
            
            ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
            scarMark.fill(codeResponse);
            dispatch.postEvent(codeResponse);
        }
    }

    private void sendAllCodes()
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        Collection scarsMarks = ScarsMarksTattoosCode.findAllScarsMarks();
        Iterator i = scarsMarks.iterator();
        while (i.hasNext())
        {
            ScarsMarksTattoosCode scarMark = (ScarsMarksTattoosCode) i.next();
            ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
            scarMark.fill(codeResponse);
            dispatch.postEvent(codeResponse);
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
