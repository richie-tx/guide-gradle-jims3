package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJJSDetentionBySupervisionNumEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import pd.juvenilecase.JJSFacility;

public class GetJJSDetentionBySupervisionNumCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJJSDetentionBySupervisionNumEvent request = (GetJJSDetentionBySupervisionNumEvent) event;

	//Iterator iter = JJSFacility.findAll("bookingSupervisionNum", request.getBookingSupervisionNum());//Attribute Query
	Iterator iter = JJSFacility.findAll(request);
	while (iter.hasNext())
	{
	    JuvenileDetentionFacilitiesResponseEvent response = new JuvenileDetentionFacilitiesResponseEvent();
	    JJSFacility facility = (JJSFacility) iter.next();
	    response.setBookingSupervisionNum(facility.getBookingSupervisionNum());
	    response.setCurrentSupervisionNum(facility.getCurrentSupervisionNum());
	    response.setDetentionId(facility.getOID());
	    response.setReferralNumber(facility.getReferralNumber());
	    dispatch.postEvent(response);
	}
    }

}
