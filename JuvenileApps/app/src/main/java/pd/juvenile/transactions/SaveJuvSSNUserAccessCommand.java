/*
 * Created on Aug 14, 2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenile.transactions;

import naming.PDConstants;

import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileSSNAccess;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvSSNUserAccessEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ugopinath
 *
 */
public class SaveJuvSSNUserAccessCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		SaveJuvSSNUserAccessEvent requestEvent = (SaveJuvSSNUserAccessEvent) event;

		if (requestEvent.getSsn() == null || requestEvent.getSsn().equals(PDConstants.BLANK)){
			return;
		}
		
		JuvenileSSNAccess juv = new JuvenileSSNAccess();
		juv.setSsn(requestEvent.getSsn());
		juv.setJuvenileNum(requestEvent.getJuvenileNum());
		juv.setAccessedBy(requestEvent.getAccessedBy());
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

