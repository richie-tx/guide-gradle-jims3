package pd.supervision.calendar.transactions;

import java.util.Iterator;

import pd.supervision.calendar.ServiceEventAttendance;
import messaging.calendar.SaveMonthlySummaryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveMonthlySummaryCommand implements ICommand {

	public void execute(IEvent event) throws Exception {
		SaveMonthlySummaryEvent evt = (SaveMonthlySummaryEvent) event;
		Iterator<ServiceEventAttendance> attenIter = ServiceEventAttendance.findAll(evt);
		while (attenIter.hasNext()) {
			ServiceEventAttendance eventAttendance = attenIter.next();
			eventAttendance.setMonthlySummary(evt.getMonthlySummary());
		}
	}

}
