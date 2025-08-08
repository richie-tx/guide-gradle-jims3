/*
 * Created on Nov 15, 2006
 *
 */
package pd.supervision.administercasenotes.transactions;

import java.util.Iterator;

import pd.supervision.administercasenotes.Casenote;
import messaging.administercasenotes.UpdateCasenotesBySpnEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *  
 */
public class UpdateCasenotesBySpnCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event)
    {
        UpdateCasenotesBySpnEvent updateEvent = (UpdateCasenotesBySpnEvent) event;

        Iterator caseNoteIter = updateEvent.getCasenotes().iterator();

        while (caseNoteIter.hasNext())
        {
            CasenoteResponseEvent respEvent = (CasenoteResponseEvent) caseNoteIter.next();
             
            Casenote casenote = Casenote.find(respEvent.getCasenoteId());
    		if (casenote != null) {
                casenote.setSuperviseeId(updateEvent.getSuperviseeId());
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
