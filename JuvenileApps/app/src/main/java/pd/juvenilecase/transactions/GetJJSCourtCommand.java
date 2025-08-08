package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSCourt;

/**
 * 
 * GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
 *
 */
public class GetJJSCourtCommand implements ICommand{

    @Override
    public void execute(IEvent event) throws Exception {
	GetJJSCourtEvent evt =(GetJJSCourtEvent)event;
		
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCourt> courtItr = JJSCourt.findAll(evt);
	while (courtItr.hasNext())
	{
	    JJSCourt court =  courtItr.next();
	    DocketEventResponseEvent resp = court.valueObject();
	    if (resp != null)
	    {
		dispatch.postEvent(resp);
	    }
	}
    }
}
