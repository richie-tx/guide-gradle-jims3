// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\SaveCSFVEventCommand.java

package pd.supervision.cscdcalendar.transactions;

import messaging.cscdcalendar.SaveCSFVEventEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.cscdcalendar.FieldVisitHelper;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/*
 * This Command handles code for Create, Update, Results, Reschedule of
 * FieldVisits.
 */
public class SaveCSFVEventCommand implements ICommand {

	/**
	 * @roseuid 479A0EB7021F
	 */
	public SaveCSFVEventCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EED90136
	 */
	public void execute(IEvent event) {

		SaveCSFVEventEvent saveFvEvent = (SaveCSFVEventEvent) event;

		//Create FV.
		if (saveFvEvent.isCreate()) {
			FieldVisitHelper.createFieldVisit(saveFvEvent);
		}

		//Update FV.
		else if ((saveFvEvent.isUpdate()) && (saveFvEvent.getFvEventid() != null)
				&& (!(saveFvEvent.getFvEventid().equals("")))) {
			FieldVisitHelper.updateFieldVisit(saveFvEvent);
		}

		//Results FV.
		else if ((saveFvEvent.isResults()) && (saveFvEvent.getFvEventid() != null)
				&& (!(saveFvEvent.getFvEventid().equals("")))) {
			FieldVisitHelper.resultsFieldVisit(saveFvEvent);
		}

		//Reschedule FV.
		else if ((saveFvEvent.isReschedule()) && (saveFvEvent.getRescheduleFVEventId() != null)
				&& (!(saveFvEvent.getRescheduleFVEventId().equals("")))) {
			FieldVisitHelper.rescheduleFieldVisit(saveFvEvent);
		}

	}

	/**
	 * @param event
	 * @roseuid 4798EED90138
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EED90145
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EED90147
	 */
	public void update(Object anObject) {

	}

}
