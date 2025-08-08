package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileCasefileControllingReferralsEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefileReferral;

/**
 * 
 * 
 * 
 */
public class GetJuvenileCasefileControllingReferralsCommand implements ICommand
{

    /**
     * @roseuid 42791F9003A9
     */
    public GetJuvenileCasefileControllingReferralsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42791D5702FF
     */
    public void execute(IEvent event)
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileCasefileControllingReferralsEvent searchEvent = (GetJuvenileCasefileControllingReferralsEvent) event;
	JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
		
	Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
	while (iter.hasNext())
	{
	    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
	    resp = caseRef.getValueObjectExt();		    
	    dispatch.postEvent(resp);
	}
    }

    /**
     * @param event
     * @roseuid 42791D570301
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42791D570303
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42791D57030E
     */
    public void update(Object anObject)
    {

    }

}
