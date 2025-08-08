/*
 * Created on Aug 14, 2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenile.transactions;

import pd.juvenile.Juvenile;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvJPOOfRecEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ugopinath
 *
 */
public class SaveJuvJPOOfRecCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		SaveJuvJPOOfRecEvent requestEvent = (SaveJuvJPOOfRecEvent) event;

		Juvenile juv = Juvenile.findJCJuvenile(requestEvent.getJuvenileNum());
		if (juv != null)
			juv.setJpoOfRecId(requestEvent.getJpoId());
		else{
			ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
			errRespEvt.setMessage("Error: Unable to find Juvenile record");
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errRespEvt);
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

