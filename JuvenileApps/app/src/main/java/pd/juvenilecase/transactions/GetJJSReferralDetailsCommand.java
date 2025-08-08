package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJJSReferralDetailsEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefileReferral;

public class GetJJSReferralDetailsCommand implements ICommand {
	

	/**
	 * @roseuid 4328435B0083
	 */
	public GetJJSReferralDetailsCommand()
	{

	}
	

	public void execute(IEvent event)
	{
	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    GetJJSReferralDetailsEvent refEvent = (GetJJSReferralDetailsEvent) event;
	    JuvenileProfileReferralListResponseEvent resp = null;
		
		Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(refEvent);
		
		while(iter.hasNext()){
		    
			JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
			resp = caseRef.getValueObjectExt();
			
			 dispatch.postEvent(resp);
		}		
	}
}
