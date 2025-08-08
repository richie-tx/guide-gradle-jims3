package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import messaging.referral.GetJJSDecisionHistoryEvent;
import messaging.referral.reply.JJSDecisionHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.referral.JJSDecisionHistory;

public class GetJJSDecisionHistoryCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	// TODO Auto-generated method stub
	GetJJSDecisionHistoryEvent request = (GetJJSDecisionHistoryEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	Iterator iter = JJSDecisionHistory.findAll(request);
	
	while(iter.hasNext()) {
	    
	    JJSDecisionHistory hist = (JJSDecisionHistory) iter.next();
	    
	    JJSDecisionHistoryResponseEvent response = hist.valueObject();
	    
	    dispatch.postEvent(response);	   
	    
	}
	
    }

}
