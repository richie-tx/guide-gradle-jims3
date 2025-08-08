// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSFVItenaryDetailsCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;

import messaging.cscdcalendar.GetCSFVItineraryDetailsEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.cscdcalendar.FieldVisitHelper;
import pd.supervision.cscdcalendar.FieldVisitItinerary;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates Command used to fetch the
 * Field Visit Itinerary details based upon itineraryDate and positionId.
 */
public class GetCSFVItineraryDetailsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB50396
	 */
	public GetCSFVItineraryDetailsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EED7026E
	 */
	public void execute(IEvent event) {
		GetCSFVItineraryDetailsEvent mEvent = (GetCSFVItineraryDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CSFVItineraryResponseEvent respEvent = null;
		if (mEvent.getItineraryId() != null && (!(mEvent.getItineraryId().trim().equals("")))) {
			FieldVisitItinerary fvItinerary = FieldVisitItinerary.find(mEvent.getItineraryId());
			if (fvItinerary != null)
				respEvent = FieldVisitHelper.getCDFVItineraryResponseEvent(fvItinerary);
			dispatch.postEvent(respEvent);
		} else {
			Iterator fieldVisitItertor = FieldVisitItinerary.findAll(mEvent);
			while (fieldVisitItertor.hasNext()) {
				FieldVisitItinerary fieldVisitItinerary = (FieldVisitItinerary) fieldVisitItertor.next();
				if (fieldVisitItinerary != null)
					respEvent = FieldVisitHelper.getCDFVItineraryResponseEvent(fieldVisitItinerary);
				dispatch.postEvent(respEvent);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 4798EED70270
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EED70272
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EED7027E
	 */
	public void update(Object anObject) {

	}

}
