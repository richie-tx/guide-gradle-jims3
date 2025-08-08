package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDetRecResultEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

/**
 * @author ryoung
 */
public class GetJJSCLDetentionByDetRecResultCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJJSCLDetentionByDetRecResultEvent evt = (GetJJSCLDetentionByDetRecResultEvent) event;
	
	Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(evt);
	    while (detentionItr.hasNext())
	    {
		JJSCLDetention detention = detentionItr.next();
		DocketEventResponseEvent resp = detention.valueObject();
		if (resp != null)
		{
		    dispatch.postEvent(resp);
		}
		   
	}
    }
}
