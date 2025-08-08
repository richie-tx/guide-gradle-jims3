/*
 * Created on Jun 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.GetAllJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetAllJuvLocationUnitsByLocationIdCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetAllJuvLocationUnitsByLocationIdEvent locUnitEvent = (GetAllJuvLocationUnitsByLocationIdEvent) event;
		Iterator i = JuvLocationUnit.findAllLocUnitsByLocationId(locUnitEvent);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext()) {
			JuvLocationUnit juvLocUnit = (JuvLocationUnit) i.next();
			LocationResponseEvent juvLocResponseEvent = new LocationResponseEvent();
			juvLocResponseEvent.setLocationUnitName(juvLocUnit.getLocationUnitName());
			juvLocResponseEvent.setJuvUnitCd(juvLocUnit.getJuvUnitCd());
			juvLocResponseEvent.setLocationId(juvLocUnit.getLocationId());
			juvLocResponseEvent.setJuvLocationUnitId(juvLocUnit.getJuvLocUnitId());
			juvLocResponseEvent.setStatusId(juvLocUnit.getUnitStatusId());
			juvLocResponseEvent.setPhoneNumber(juvLocUnit.getPhoneNumber());
			dispatch.postEvent(juvLocResponseEvent);
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
