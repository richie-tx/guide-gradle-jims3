package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.SupervisionTypeTJJDMap;
import messaging.juvenilecase.GetSupervisionTypeTJJDMapEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetSupervisionTypeTJJDMapCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetSupervisionTypeTJJDMapEvent reqEvent = (GetSupervisionTypeTJJDMapEvent) event;
		
	Iterator<SupervisionTypeTJJDMap> sprvMapItr = SupervisionTypeTJJDMap.findAll("supervisionTypeId", reqEvent.getSupervisionTypeId());

	while( sprvMapItr.hasNext()){
	    
	    SupervisionTypeTJJDMap typeMap = sprvMapItr.next();
	    if( typeMap != null){
		
		SupervisionTypeMapResponseEvent response = typeMap.getResponseEvent(); 
		//post
		dispatch.postEvent(response);		
	    }
	}
	
    }

}
