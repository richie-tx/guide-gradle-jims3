// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSFVEventsCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;

import messaging.cscdcalendar.CSEventTypeAttribute;
import messaging.cscdcalendar.CSFVIteneraryAttribute;
import messaging.cscdcalendar.CSPositionAttribute;
import messaging.cscdcalendar.CSSuperviseeAttribute;
import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.cscdcalendar.GetCSFVEventsEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDCodeTableConstants;
import pd.supervision.cscdcalendar.FieldVisitEvent;
import pd.supervision.cscdcalendar.FieldVisitHelper;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetCSFVEventsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB502CB
	 */
	public GetCSFVEventsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EED800A9
	 */
	public void execute(IEvent event) {
		GetCSFVEventsEvent fvEvents = (GetCSFVEventsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent();

		// Calendar Start and End Date
		retrieverEvent.setStartDatetime(fvEvents.getEventDate());
		retrieverEvent.setEndDatetime(fvEvents.getEventDate());

		// Calendar Attributes

		CSEventTypeAttribute eventTypeAttr = new CSEventTypeAttribute();
		eventTypeAttr.setEventTypeId(PDCodeTableConstants.CS_FIELD_VISIT_CATEGORY);
		fvEvents.addCalendarAttribute(eventTypeAttr);

		if (fvEvents.getCurrentContext() != null
				&& fvEvents.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			CSPositionAttribute positionAttr = new CSPositionAttribute();
			positionAttr.setPositionId(new Integer(fvEvents.getPositionId()));
			fvEvents.addCalendarAttribute(positionAttr);

			CSFVIteneraryAttribute iteneraryAttr = new CSFVIteneraryAttribute();
			iteneraryAttr.setFvIteneraryId(new Integer(Integer.parseInt(fvEvents.getFvIteneraryId())));
			fvEvents.addCalendarAttribute(iteneraryAttr);

		} else if (fvEvents.getCurrentContext() != null
				&& fvEvents.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE)) {
			CSSuperviseeAttribute superviseeAttr = new CSSuperviseeAttribute();
			superviseeAttr.setPartyId(fvEvents.getSuperviseeId());
			fvEvents.addCalendarAttribute(superviseeAttr);
		}

		ICalendarAttribute[] calAttr = new ICalendarAttribute[fvEvents.getCalendarAttributes().size()];
		fvEvents.getCalendarAttributes().toArray(calAttr);
		retrieverEvent.setCalendarAttributes(calAttr);
		IHome home = new Home();
		Iterator fvEventsIte = home.findAll(retrieverEvent, FieldVisitEvent.class);
		while (fvEventsIte.hasNext()) {
			FieldVisitEvent fieldVisit = (FieldVisitEvent) fvEventsIte.next();
			CSFieldVisitResponseEvent respEvent = FieldVisitHelper.getCSFVEventResponseEvent(fieldVisit);
			dispatch.postEvent(respEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 4798EED800AB
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EED800B8
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EED800BA
	 */
	public void update(Object anObject) {

	}

}
