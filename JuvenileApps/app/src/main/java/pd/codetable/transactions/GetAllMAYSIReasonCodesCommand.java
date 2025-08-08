package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetAllAliasNameTypeCodesEvent;
import messaging.codetable.GetAllMAYSIReasonCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.person.AliasNameTypeCode;
import pd.codetable.person.ReasonNotDone;

public class GetAllMAYSIReasonCodesCommand implements ICommand
{
    /**
	 * 
	 */
    public GetAllMAYSIReasonCodesCommand()
    {

    }

    public void execute(IEvent event) throws Exception
    {
	GetAllMAYSIReasonCodesEvent reqEvent = (GetAllMAYSIReasonCodesEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator iter = ReasonNotDone.findAll();
	while (iter.hasNext())
	{
	    ReasonNotDone code = (ReasonNotDone) iter.next();

	    CodeResponseEvent reply = new CodeResponseEvent();

	    reply.setCode( code.getCode() );
	    reply.setDescription( code.getDescription() );
	    reply.setStatus(code.getStatus());
	    reply.setDescriptionWithCode( code.getCode() +"-" +code.getDescription());
	    dispatch.postEvent(reply);

	}
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
    }
}
