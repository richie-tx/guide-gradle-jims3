package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileDispositionCodeByValueEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileDispositionCode;

public class GetJuvenileDispositionCodeByValueCommand implements ICommand
{

    /**
     * @roseuid 43039DCD01A5
     */
    public GetJuvenileDispositionCodeByValueCommand()
    {

    }

    /**
     * @param event
     * @roseuid 43039A40005E
     */
    public void execute(IEvent event)
    {
	GetJuvenileDispositionCodeByValueEvent request = (GetJuvenileDispositionCodeByValueEvent) event;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator<JuvenileDispositionCode> iter = JuvenileDispositionCode.findAll( request.getAttributeName(), request.getAttributeValue() );
	while (iter.hasNext())
	{

	    JuvenileDispositionCode code = (JuvenileDispositionCode) iter.next();
	    if (!"Y".equalsIgnoreCase(code.getInactiveInd()))
	    {

		JuvenileDispositionCodeResponseEvent replyEvent = new JuvenileDispositionCodeResponseEvent();
		replyEvent.setCodeAlpha(code.getCodeAlpha());
		replyEvent.setCategoryCode(code.getCategoryCode());
		replyEvent.setCodeNum(code.getCodeNum());
		replyEvent.setDispositionCode(code.getDispositionCode());
		replyEvent.setDpsCode(code.getDpsCode());
		replyEvent.setjPCCode(code.getJPCCode());
		replyEvent.setLongDesc(code.getLongDesc());
		replyEvent.setNumericCode(code.getNumericCode());
		replyEvent.setOptionCode(code.getOptionCode());
		replyEvent.setShortDesc(code.getShortDesc());
		replyEvent.setSubGroupInd(code.getSubGroupInd());
		replyEvent.setDescriptionWithCode(code.getCodeAlpha() + "-" + code.getShortDesc());
		dispatch.postEvent(replyEvent);
	    }

	}

    }

    /**
     * @param event
     * @roseuid 43039A400060
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 43039A400062
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 43039A40006E
     */
    public void update(Object anObject)
    {

    }

}
