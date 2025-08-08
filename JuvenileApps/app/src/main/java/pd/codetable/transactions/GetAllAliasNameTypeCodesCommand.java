package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetAllAliasNameTypeCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.person.AliasNameTypeCode;

public class GetAllAliasNameTypeCodesCommand implements ICommand
{
    /**
	 * 
	 */
    public GetAllAliasNameTypeCodesCommand()
    {

    }

    public void execute(IEvent event) throws Exception
    {
	GetAllAliasNameTypeCodesEvent reqEvent = (GetAllAliasNameTypeCodesEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator iter = AliasNameTypeCode.findAll();
	while (iter.hasNext())
	{
	    AliasNameTypeCode aliasCode = (AliasNameTypeCode) iter.next();

	    CodeResponseEvent reply = new CodeResponseEvent();

	    reply.setCode( aliasCode.getCode() );
	    reply.setDescription( aliasCode.getDescription() );
	    reply.setDescriptionWithCode( aliasCode.getCode() +"-" +aliasCode.getDescription());
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
