package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileCourtDecisionCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileCourtDecisionCode;

public class GetJuvenileCourtDecisionCodesCommand implements ICommand
{
    /**
	 * 
	 */
    public GetJuvenileCourtDecisionCodesCommand()
    {

    }

    public void execute(IEvent event) throws Exception
    {
	GetJuvenileCourtDecisionCodesEvent getCodeEvent = (GetJuvenileCourtDecisionCodesEvent) event;
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	if (getCodeEvent.getCode() != null && !getCodeEvent.getCode().isEmpty())
	{
	    Iterator<JuvenileCourtDecisionCode> juvDispCodeItr = JuvenileCourtDecisionCode.findAll("code",getCodeEvent.getCode());
	    JuvenileCourtDecisionCode juvDispCode = null;
	    if(juvDispCodeItr.hasNext()){
		juvDispCode = juvDispCodeItr.next();
	    }
	   if(juvDispCode!=null){
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
	else
	{
	    Iterator<JuvenileCourtDecisionCode> juvDispItr = JuvenileCourtDecisionCode.findAll();
	    while (juvDispItr != null && juvDispItr.hasNext())
	    {
		JuvenileCourtDecisionCode juvDispCode = juvDispItr.next();
		if (juvDispCode != null && juvDispCode.getStatus()!=null && juvDispCode.getStatus().equalsIgnoreCase("active"))
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
