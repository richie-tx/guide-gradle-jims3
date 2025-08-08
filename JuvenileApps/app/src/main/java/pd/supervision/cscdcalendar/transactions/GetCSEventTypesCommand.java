// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSEventTypesCommand.java

package pd.supervision.cscdcalendar.transactions;

import messaging.cscdcalendar.GetCSEventTypesEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import java.util.Iterator;
import java.util.List;


import pd.supervision.cscdcalendar.CSEventHelper;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;

public class GetCSEventTypesCommand implements ICommand {

	/**
	 * @roseuid 479A0EB50154
	 */
	public GetCSEventTypesCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EE72006B
	 */
	public void execute(IEvent event)   {
		GetCSEventTypesEvent etEvent = (GetCSEventTypesEvent)event;   
		if(etEvent.getContext()==null) {			
			CSEventHelper.postCSEventError("Please specify the CS event context.");
			return;
		}
		if(etEvent.getContext()!=null) {
			if(etEvent.getContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE) || 
					etEvent.getContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			} else {
				CSEventHelper.postCSEventError("Please specify a valid CS event context.");
				return;
			}
		}
	   	List eventTypes = CSEventHelper.getInstance().getCSEventTypes(etEvent.getContext());
	   	Iterator iter = eventTypes.iterator();
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   	if (iter!=null ){	   			
	   		while(iter.hasNext()) {
	   			CSEventTypeResponseEvent resp = (CSEventTypeResponseEvent) iter.next();
	   			dispatch.postEvent(resp);
	   		} 			
	   	}	
	}

	/**
	 * @param event
	 * @roseuid 4798EE72006D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EE720079
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EE72007B
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB50173
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
