package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJJSMsReferralDetailsEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefileReferral;

public class GetJJSMsReferralDetailsCommand implements ICommand {
	

	/**
	 * @roseuid 4328435B0083
	 */
	public GetJJSMsReferralDetailsCommand()
	{

	}
	

	public void execute(IEvent event)
	{
	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    GetJJSMsReferralDetailsEvent refEvent = (GetJJSMsReferralDetailsEvent) event;
	    JuvenileProfileReferralListResponseEvent resp = null;
		
		Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(refEvent);
		
		while(iter.hasNext()){
		    
			JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
			resp = caseRef.getReportValueObject();
			
			 dispatch.postEvent(resp);
		}		
	}
}
