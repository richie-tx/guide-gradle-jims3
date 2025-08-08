package pd.codetable.transactions;

import java.util.Iterator;


import pd.juvenilecase.OverrideOptions;
import messaging.juvenilecase.reply.OverrideOptionsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetOverrideOptionCodesCommand implements ICommand
{
    
    public void execute(IEvent event) throws Exception{
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	Iterator overrideOptionCodes = OverrideOptions.findAll();
   	while (overrideOptionCodes.hasNext() ) {
   	    OverrideOptions overrideOptionCode = (OverrideOptions) overrideOptionCodes.next();
   	    OverrideOptionsResponseEvent response = OverrideOptionsResponseEvent.getResponse(overrideOptionCode);
   	    dispatch.postEvent(response);
   	}
    }

}
