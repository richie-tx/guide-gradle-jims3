// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetMonthlyCSCalendarCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;


import messaging.cscdcalendar.GetCalendarEventsByDefendantEvent;
import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.cscdcalendar.CSEventsReport;

/**
 * @author rcapestani
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCalendarEventsByDefendantCommand implements ICommand {

	/**
	 * @roseuid 479A0EB70164
	 */
	public GetCalendarEventsByDefendantCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EE7102C0
	 */
	public void execute(IEvent event) {

		GetCalendarEventsByDefendantEvent calendarEvents = (GetCalendarEventsByDefendantEvent) event;
		Iterator csEventsIter = CSEventsReport.findAll(calendarEvents);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (csEventsIter.hasNext()) {
			CSEventsReport csEventsReport = (CSEventsReport)csEventsIter.next();
			CSEventsReportReponseEvent responseEvt = new CSEventsReportReponseEvent();
			buildResponseEvent(responseEvt, csEventsReport);
			dispatch.postEvent(responseEvt);
		}
	}

	/**
	 * @param csEventsRespEvent
	 * @param e
	 */
	private void buildResponseEvent( CSEventsReportReponseEvent csEventsRespEvent, CSEventsReport csEventsReport ) {

		csEventsRespEvent.setContactMethod(csEventsReport.getContactMethod());
		csEventsRespEvent.setCsEventDate(csEventsReport.getEventDate());
		csEventsRespEvent.setCsEventId(csEventsReport.getCsEventId());
		csEventsRespEvent.setResultUserId( csEventsReport.getPositionId() );
		csEventsRespEvent.setDefendantId( csEventsReport.getSuperviseeId() );
		csEventsRespEvent.setOutcomeCd( csEventsReport.getOutcomeCd() );
		csEventsRespEvent.setEndTime( csEventsReport.getEndTime() );
		csEventsRespEvent.setStartTime( csEventsReport.getStartTime() );
		csEventsRespEvent.setStatus( csEventsReport.getStatus());
		csEventsRespEvent.setCsEventTypeId(csEventsReport.getEventTypeId());
		
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
