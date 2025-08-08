package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetCurrentFacilityByJuvenileIdEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import pd.juvenilecase.JJSFacility;

public class GetCurrentFacilityByJuvenileIdCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	JuvenileDetentionFacilitiesResponseEvent response = null;
	
	GetCurrentFacilityByJuvenileIdEvent request = (GetCurrentFacilityByJuvenileIdEvent) event;
	
	Iterator iter = JJSFacility.findAll(request);
	
	if(iter.hasNext()) {
	    
	    JJSFacility facility = (JJSFacility) iter.next();
	    response = facility.valueObject();
	    
	}else {
	    
	    ReturnException exception = new ReturnException();
	    exception.setErrorReason("No Records found");
	    dispatch.postEvent(exception);
	}
	dispatch.postEvent(response);
    }

}
