// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetMonthlyCSCalendarCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.cscdcalendar.CSPositionAttribute;
import messaging.cscdcalendar.CSSuperviseeAttribute;
import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.cscdcalendar.GetMonthlyCSCalendarEvent;
import messaging.cscdcalendar.reply.MonthlyCSCalendarResponseEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.CSEventType;
import pd.supervision.cscdcalendar.CSEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMonthlyCSCalendarCommand implements ICommand {

	/**
	 * @roseuid 479A0EB70164
	 */
	public GetMonthlyCSCalendarCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EE7102C0
	 */
	public void execute(IEvent event) {

		GetMonthlyCSCalendarEvent monthlyEvent = (GetMonthlyCSCalendarEvent) event;
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent();

		// Calendar Start and End Date
		retrieverEvent.setStartDatetime(monthlyEvent.getStartDatetime());
		retrieverEvent.setEndDatetime(monthlyEvent.getEndDatetime());

		// Calendar Attributes

		if (monthlyEvent.getCurrentContext() != null
				&& monthlyEvent.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			CSPositionAttribute positionAttr = new CSPositionAttribute();
			positionAttr.setPositionId(new Integer(monthlyEvent.getPositionId()));
			monthlyEvent.addCalendarAttribute(positionAttr);
		} else if (monthlyEvent.getCurrentContext() != null
				&& monthlyEvent.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE)) {
			CSSuperviseeAttribute superviseeAttr = new CSSuperviseeAttribute();
			superviseeAttr.setPartyId(monthlyEvent.getSuperviseeId());
			monthlyEvent.addCalendarAttribute(superviseeAttr);
		}

		ICalendarAttribute[] as = new ICalendarAttribute[monthlyEvent.getCalendarAttributes().size()];
		monthlyEvent.getCalendarAttributes().toArray(as);
		retrieverEvent.setCalendarAttributes(as);
		IHome home = new Home();
		Iterator csEventsIte = home.findAll(retrieverEvent, CSEvent.class);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		MonthlyCSCalendarResponseEvent monthlyRespEvent = null;
		while (csEventsIte.hasNext()) {
			monthlyRespEvent = new MonthlyCSCalendarResponseEvent();
			CSEvent e = (CSEvent) csEventsIte.next();
			String outCome = e.getOutCome();
			if(StringUtils.isNotEmpty(outCome) && !outCome.equals("RE")) {
				buildResponseEvent(monthlyRespEvent, e);
				dispatch.postEvent(monthlyRespEvent);
			}
		}
	}

	/**
	 * @param monthlyRespEvent
	 * @param e
	 */
	private void buildResponseEvent(MonthlyCSCalendarResponseEvent monthlyRespEvent, CSEvent e) {
		CSEventType eventType = null;
		monthlyRespEvent.setStartDatetime(e.getEventDate());
		monthlyRespEvent.setStartTime(e.getStartTime());
		monthlyRespEvent.setCalendarEventType(e.getEventTypeId());
		monthlyRespEvent.setPositionId(e.getPositionId());
		monthlyRespEvent.setSuperviseeId(e.getPartyId());
		monthlyRespEvent.setOutcome(e.getOutCome());
		monthlyRespEvent.setStatus(e.getStatusId());
		eventType = e.getEventType();
		if(eventType != null){
		monthlyRespEvent.setCategoryCd(eventType.getCategoryId());
		monthlyRespEvent.setCategoryDesc(eventType.getCategory().getDescription());
		}
		
	}

	/**
	 * @param event
	 * @roseuid 4798EE7102CB
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EE7102CD
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EE7102CF
	 */
	public void update(Object anObject) {

	}

}
