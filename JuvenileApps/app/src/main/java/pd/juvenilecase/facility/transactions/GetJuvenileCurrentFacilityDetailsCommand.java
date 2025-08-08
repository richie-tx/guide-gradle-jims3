package pd.juvenilecase.facility.transactions;

import java.util.Iterator;

import pd.juvenilecase.JJSFacility;
import messaging.facility.GetJuvenileCurrentFacilityDetailsEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCurrentFacilityDetailsCommand implements ICommand
{

    @Override
    public void execute(IEvent iEvent) throws Exception
    {
	GetJuvenileCurrentFacilityDetailsEvent requestEvent = (GetJuvenileCurrentFacilityDetailsEvent) iEvent;
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSFacility> facilityItr = JJSFacility.findAll(requestEvent);
	while (facilityItr.hasNext()) {
		JJSFacility fac = (JJSFacility)facilityItr.next();
		
		JuvenileDetentionFacilitiesResponseEvent resp = fac.valueObject();
		dispatch.postEvent(resp);
	}
	// Dispose of Object after finishing.
	facilityItr = null;
    }
}
