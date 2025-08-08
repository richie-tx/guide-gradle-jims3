/*
 * Created on Mar 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar.transactions;

import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.FieldVisitEvent;
import messaging.cscdcalendar.DeleteCSFVEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteCSFVCommand implements ICommand {
	
	public DeleteCSFVCommand(){
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		DeleteCSFVEvent deleteCSFVEvent = (DeleteCSFVEvent) event;
		if (deleteCSFVEvent.getFvEventid() != null && (!deleteCSFVEvent.getFvEventid().equals(""))) {
			FieldVisitEvent fieldVisitEvent = FieldVisitEvent.find(deleteCSFVEvent.getFvEventid());
			if (fieldVisitEvent != null) {
				CSEvent csEvent = CSEvent.find(fieldVisitEvent.getCsEventId());
				fieldVisitEvent.delete(deleteCSFVEvent, csEvent);
			}
		}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}
}
