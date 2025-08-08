/*
 * Created on Oct 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.calendar.transactions;

import java.util.Iterator;

import pd.supervision.calendar.ServiceEventContext;
import messaging.calendar.GetServiceEventsByServiceIdEvent;
import messaging.calendar.reply.ServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceEventsByServiceIdCommand implements ICommand {
	
	   public GetServiceEventsByServiceIdCommand() 
	   {
	    
	   }

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetServiceEventsByServiceIdEvent reqEvent = (GetServiceEventsByServiceIdEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator iter = ServiceEventContext.findAll(reqEvent);

		while(iter.hasNext()){
			ServiceEventContext serviceEvent = (ServiceEventContext) iter.next();		
			ServiceEventResponseEvent respEvent = new ServiceEventResponseEvent();  
			respEvent.setStartDateTime(serviceEvent.getStartDatetime());
			dispatch.postEvent(respEvent);	 
		}		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}
	   
}
