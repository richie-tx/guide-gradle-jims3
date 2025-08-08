/*
 * Created on Jan 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.calendar.transactions;

import java.util.Iterator;

import pd.supervision.programreferral.JuvenileEventReferral;
import messaging.calendar.GetProgramReferralsByServiceEventIdEvent;
import messaging.calendar.reply.ProgramReferralsByServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramReferralsByServiceEventIdCommand implements ICommand{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		
		GetProgramReferralsByServiceEventIdEvent reqEvent = (GetProgramReferralsByServiceEventIdEvent)event; 
		Iterator iter = JuvenileEventReferral.findAll("serviceEventId", reqEvent.getServiceEventId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iter.hasNext()) {
			JuvenileEventReferral servEvent = (JuvenileEventReferral) iter.next();
			ProgramReferralsByServiceEventResponseEvent respEvent = new ProgramReferralsByServiceEventResponseEvent();
			respEvent.setProgramReferralId(servEvent.getProgramReferralId());
			dispatch.postEvent(respEvent);
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
