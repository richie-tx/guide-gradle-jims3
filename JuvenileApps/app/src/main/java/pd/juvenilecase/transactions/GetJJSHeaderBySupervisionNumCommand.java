package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJJSDetentionBySupervisionNumEvent;
import messaging.juvenilecase.GetJJSHeaderBySupervisionNumEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;

public class GetJJSHeaderBySupervisionNumCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetJJSHeaderBySupervisionNumEvent request = (GetJJSHeaderBySupervisionNumEvent) event;
	JJSHeader header = null;
	Iterator<JJSHeader> jjsHeaderItr = JJSHeader.findAll("bookingSupervisionNum",request.getBookingSupervisionNum());
	while (jjsHeaderItr.hasNext())
	{
	    JuvenileFacilityHeaderResponseEvent headerResp = new JuvenileFacilityHeaderResponseEvent();
	    header = jjsHeaderItr.next();
	    headerResp.setBookingSupervision(header.getBookingSupervisionNum());
	    headerResp.setHeaderId(header.getOID());
	    headerResp.setReferralNo(header.getReferralNumber());
	    dispatch.postEvent(headerResp);
	    break; //Carla: JJS_Header should have only have one. If there is more than one Header, then it�s an error.
	}
    }

}
