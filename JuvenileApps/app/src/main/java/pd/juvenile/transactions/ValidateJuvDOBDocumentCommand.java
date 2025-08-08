/*
 * Created on Nov 16, 2006
 *
 */
package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.Juvenile;
import messaging.juvenile.ValidateJuvDOBDocumentEvent;
import messaging.juvenile.reply.JuvDOBDocumentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 * 
 */
public class ValidateJuvDOBDocumentCommand implements ICommand {

	/**
	 * Constructor
	 */
	public ValidateJuvDOBDocumentCommand() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
		public void execute(IEvent event) throws Exception {
			// TODO Auto-generated method stub
		
			ValidateJuvDOBDocumentEvent requestEvent = (ValidateJuvDOBDocumentEvent) event;
			Iterator juvenileIterator = Juvenile.findAll(requestEvent);
			Juvenile juvenile = null;
			if (juvenileIterator != null && juvenileIterator.hasNext()) {
				juvenile = (Juvenile) juvenileIterator.next();
				JuvDOBDocumentResponseEvent respEvent = new JuvDOBDocumentResponseEvent();
				respEvent.setAvailable(true);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(respEvent);
			} else {
				JuvDOBDocumentResponseEvent respEvent = new JuvDOBDocumentResponseEvent();
				respEvent.setAvailable(false);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(respEvent);
			}
		
		}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
