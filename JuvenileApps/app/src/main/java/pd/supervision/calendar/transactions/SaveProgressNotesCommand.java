package pd.supervision.calendar.transactions;

import java.util.Iterator;
import pd.supervision.calendar.ServiceEventAttendance;
import messaging.calendar.SaveProgressNotesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveProgressNotesCommand implements ICommand {

	/**
	    * 
	    */
	public SaveProgressNotesCommand() {

	}

	/**
	 * @param event
	 * 
	 */
	public void execute(IEvent event) {
		SaveProgressNotesEvent evt = (SaveProgressNotesEvent) event;
		Iterator<ServiceEventAttendance> attenIter = ServiceEventAttendance.findAll(evt);
		while (attenIter.hasNext()) {
			ServiceEventAttendance eventAttendance = attenIter.next();
			if (eventAttendance.getProgressNotes() != null) {
				StringBuffer notes = new StringBuffer(eventAttendance.getProgressNotes()) ;
				notes = notes.append( " " ) ;
				notes = notes.append(evt.getProgressNotes());
				eventAttendance.setProgressNotes(notes.toString());
			} else {
				eventAttendance.setProgressNotes(evt.getProgressNotes());
			}
		}
	}

	/**
	 * @param event
	 * 
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * 
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * 
	 */
	public void update(Object anObject) {

	}
}
