// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSOtherEventDetailsCommand.java

package pd.supervision.cscdcalendar.transactions;


import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;

import messaging.cscdcalendar.GetCSOtherEventDetailsEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetCSOtherEventDetailsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB602AC
	 */
	public GetCSOtherEventDetailsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EE8A032B
	 */
	public void execute(IEvent event) {
		GetCSOtherEventDetailsEvent cee = (GetCSOtherEventDetailsEvent) event;
		if(CSEventHelper.isEventIdError(cee.getEventId())) {
			return;		
		}
		CSEvent cse = CSEvent.find(cee.getEventId()); 
		if(cse==null) {
			CSEventHelper.postCSEventError("There is no other event with the eventid specified.");
			return;
		}
		CSOtherResponseEvent resp = CSEventBuilder.buildOtherEvent(cse, true);		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(resp);
	}

	/**
	 * @param event
	 * @roseuid 4798EE8A033A
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EE8A033C
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EE8A0348
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB602DB
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
