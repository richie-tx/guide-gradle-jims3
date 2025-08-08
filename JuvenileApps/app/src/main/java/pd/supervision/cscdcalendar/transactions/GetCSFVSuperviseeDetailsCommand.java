package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;

import messaging.cscdcalendar.GetCSFVSuperviseeDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import messaging.cscdcalendar.reply.CSFVSuperviseeDetailsResponseEvent;

import pd.supervision.cscdcalendar.FieldVisitHelper;
import pd.supervision.cscdcalendar.FieldVisitSuperviseeDetails;

public class GetCSFVSuperviseeDetailsCommand implements ICommand {

	public GetCSFVSuperviseeDetailsCommand() {

	}

	/**
	 * 
	 */
	public void execute(IEvent event) {
		GetCSFVSuperviseeDetailsEvent sEvent = (GetCSFVSuperviseeDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CSFVSuperviseeDetailsResponseEvent respEvent = null;
		
		Iterator superviseeDetailsIter = FieldVisitSuperviseeDetails.findAll(sEvent);
		
		while (superviseeDetailsIter != null && superviseeDetailsIter.hasNext()) {
			FieldVisitSuperviseeDetails fvSuperviseeDetails = (FieldVisitSuperviseeDetails) superviseeDetailsIter.next();
			if (fvSuperviseeDetails != null)
				respEvent = FieldVisitHelper.getCSFVSuperviseeDeatilsResponseEvent(fvSuperviseeDetails);
			dispatch.postEvent(respEvent);
		}

	}//end of execute()

	public void onRegister(IEvent event) {

	}

	public void onUnregister(IEvent event) {

	}

	public void update(Object anObject) {

	}

}
