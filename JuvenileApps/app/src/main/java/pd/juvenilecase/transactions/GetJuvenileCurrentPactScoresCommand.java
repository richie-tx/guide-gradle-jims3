package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.NSG_REPORTING_ASSESSED_RISK_LEVEL;
import messaging.juvenilecase.GetJuvenileCurrentPactScoresEvent;
import messaging.juvenilecase.reply.JuvenilePactScoresResponseEvent;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCurrentPactScoresCommand implements ICommand
{

    public void execute(IEvent event)
    {
	GetJuvenileCurrentPactScoresEvent request = (GetJuvenileCurrentPactScoresEvent) event;
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	Iterator iter = NSG_REPORTING_ASSESSED_RISK_LEVEL.findAll( request );
	
	while( iter.hasNext()){
	    
	    NSG_REPORTING_ASSESSED_RISK_LEVEL obj = (NSG_REPORTING_ASSESSED_RISK_LEVEL) iter.next();
	    
	    JuvenilePactScoresResponseEvent resp = obj.valueObject();
	    dispatch.postEvent(resp);
	}
	
    }

}
