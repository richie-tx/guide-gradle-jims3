/*
 * Created on Sep 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileAlertDetailsEvent;
import messaging.juvenilecase.reply.JIMS2AlertDetailsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JIMS2Alert;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
//Command used to fetch the details from M204 file JDJJSDT for Casefile closing
// Alerts Implementation
public class GetJuvenileAlertDetailsCommand implements ICommand {

	public GetJuvenileAlertDetailsCommand() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetJuvenileAlertDetailsEvent alertDetailsEvent = (GetJuvenileAlertDetailsEvent) event;
		Iterator iter = JIMS2Alert.findAll(alertDetailsEvent);
		JIMS2Alert jims2Alert = null;
		while (iter.hasNext()) {
			jims2Alert = (JIMS2Alert) iter.next();
			if (jims2Alert != null) {
				JIMS2AlertDetailsResponseEvent respEvent = jims2Alert.getValueObject();
				dispatch.postEvent(respEvent);
			}
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
