package pd.juvenilecase.districtCourtHearings.transactions;/*package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;

import messaging.districtCourtHearings.GetJJSCLCourtActionDocketsEvent;
import messaging.districtCourtHearings.reply.JJSCLCourtActionDocketResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillaryDetentionCourt;

*//**
 * 
 *Not in USe
 *
 *//*
public class GetJJSCLCourtActionDocketsCommand implements ICommand{
	
	@Override
	public void execute(IEvent event) throws Exception {
	    GetJJSCLCourtActionDocketsEvent evt =(GetJJSCLCourtActionDocketsEvent)event;
	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator<JJSCLAncillaryDetentionCourt> detentionItr = JJSCLAncillaryDetentionCourt.findAll(evt);
		while ( detentionItr.hasNext() )
		{
		    JJSCLAncillaryDetentionCourt detention = (JJSCLAncillaryDetentionCourt) detentionItr.next();
		       JJSCLCourtActionDocketResponseEvent resp = detention.valueObject();
			if (resp != null)
			{
			    dispatch.postEvent(resp);
			}
		}
	}
}
*/