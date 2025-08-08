// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSOfficeVisitDetailsCommand.java

package pd.supervision.cscdcalendar.transactions;

import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;
import messaging.cscdcalendar.GetCSOfficeVisitDetailsEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetCSOfficeVisitDetailsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB60125
	 */
	public GetCSOfficeVisitDetailsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA403D5
	 */
	public void execute(IEvent event) {
		GetCSOfficeVisitDetailsEvent cov = (GetCSOfficeVisitDetailsEvent) event;		
		if(CSEventHelper.isEventIdError(cov.getEventId())) {
			return;		
		}
		CSEvent cse = CSEvent.find(cov.getEventId()); 
		if(cse==null) {
			CSEventHelper.postCSEventError("There is no Office Visit with the eventid specified.");
			return;
		}
		CSOfficeVisitResponseEvent resp = CSEventBuilder.buildOfficeVisit(cse, true);		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(resp);	
	}
	
	/**
	 * @param event
	 * @roseuid 4798EEA403D7
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA403D9
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EEA403E5
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB60154
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
