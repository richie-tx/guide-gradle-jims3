package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetAllJuvenileCourtDecisionCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileCourtDecisionCode;

public class GetAllJuvenileCourtDecisionCodesCommand implements ICommand
{
    /**
	 * 
	 */
    public GetAllJuvenileCourtDecisionCodesCommand()
    {

    }

    public void execute(IEvent event) throws Exception
    {
	GetAllJuvenileCourtDecisionCodesEvent getCodeEvent = (GetAllJuvenileCourtDecisionCodesEvent) event;
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JuvenileCourtDecisionCode> juvDispItr = JuvenileCourtDecisionCode.findAll();
	while (juvDispItr != null && juvDispItr.hasNext())
	{
		JuvenileCourtDecisionCode juvDispCode = juvDispItr.next();
		if (juvDispCode != null && juvDispCode.getStatus()!=null)
		{
		    JuvenileCourtDecisionCodeResponseEvent replyEvent = new JuvenileCourtDecisionCodeResponseEvent();
		    replyEvent.setCode( juvDispCode.getCode());
		    replyEvent.setDescription(juvDispCode.getDescription());
		    replyEvent.setDecision( juvDispCode.getDecision());
		    replyEvent.setAction(juvDispCode.getAction());
		    replyEvent.setResetAllowed( juvDispCode.getResetAllowed() );
		    replyEvent.setDescriptionWithCode(juvDispCode.getCode()+"-"+juvDispCode.getDescription());
		    dispatch.postEvent(replyEvent);
		}
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
