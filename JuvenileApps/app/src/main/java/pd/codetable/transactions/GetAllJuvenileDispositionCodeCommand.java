package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetAllJuvenileDispositionCodeEvent;
import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileDispositionCode;

public class GetAllJuvenileDispositionCodeCommand implements ICommand
{
    /**
	 * 
	 */
    public GetAllJuvenileDispositionCodeCommand()
    {

    }

    public void execute(IEvent event) throws Exception
    {
	GetAllJuvenileDispositionCodeEvent getCodeEvent = (GetAllJuvenileDispositionCodeEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	if (getCodeEvent.getCode() != null && !getCodeEvent.getCode().isEmpty())
	{
	    JuvenileDispositionCode juvDispCode = JuvenileDispositionCode.find("codeAlpha",getCodeEvent.getCode());

	    if ( juvDispCode!= null)
	    {
		JuvenileDispositionCodeResponseEvent replyEvent = new JuvenileDispositionCodeResponseEvent();
		replyEvent.setCodeAlpha(juvDispCode.getCodeAlpha());
		replyEvent.setCategoryCode(juvDispCode.getCategoryCode());
		replyEvent.setCodeNum(juvDispCode.getCodeNum());
		replyEvent.setDispositionCode(juvDispCode.getDispositionCode());
		replyEvent.setDpsCode(juvDispCode.getDpsCode());
		replyEvent.setjPCCode(juvDispCode.getJPCCode());
		replyEvent.setLongDesc(juvDispCode.getLongDesc());
		replyEvent.setNumericCode(juvDispCode.getNumericCode());
		replyEvent.setOptionCode(juvDispCode.getOptionCode());
		replyEvent.setShortDesc(juvDispCode.getShortDesc());
		replyEvent.setSubGroupInd(juvDispCode.getSubGroupInd());
		replyEvent.setDescriptionWithCode(juvDispCode.getCodeAlpha() + "-" + juvDispCode.getShortDesc());
		dispatch.postEvent(replyEvent);
	    }
	}
	else
	{
	    Iterator<JuvenileDispositionCode> juvDispItr = JuvenileDispositionCode.findAll();
	    while (juvDispItr != null && juvDispItr.hasNext())
	    {
		JuvenileDispositionCode juvDispCode = juvDispItr.next();
		if ( juvDispCode!= null)
		{
		    JuvenileDispositionCodeResponseEvent replyEvent = new JuvenileDispositionCodeResponseEvent();
		    replyEvent.setCodeAlpha(juvDispCode.getCodeAlpha());
		    replyEvent.setCategoryCode(juvDispCode.getCategoryCode());
		    replyEvent.setCodeNum(juvDispCode.getCodeNum());
		    replyEvent.setDispositionCode(juvDispCode.getDispositionCode());
		    replyEvent.setDpsCode(juvDispCode.getDpsCode());
		    replyEvent.setjPCCode(juvDispCode.getJPCCode());
		    replyEvent.setLongDesc(juvDispCode.getLongDesc());
		    replyEvent.setNumericCode(juvDispCode.getNumericCode());
		    replyEvent.setOptionCode(juvDispCode.getOptionCode());
		    replyEvent.setShortDesc(juvDispCode.getShortDesc());
		    replyEvent.setSubGroupInd(juvDispCode.getSubGroupInd());
		    replyEvent.setDescriptionWithCode(juvDispCode.getCodeAlpha() + "-" + juvDispCode.getShortDesc());
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
