/*
 * Created on Apr 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import pd.juvenilecase.mentalhealth.JuvenileDSMIVResults;
import messaging.mentalhealth.GetDSMIVRecordEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetDSMIVRecordCommand implements ICommand {

	/**
	 *  
	 */
	public GetDSMIVRecordCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void execute(IEvent event) {
		GetDSMIVRecordEvent recEvent = (GetDSMIVRecordEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator dsmIterator = JuvenileDSMIVResults.findAll(recEvent);
		while (dsmIterator.hasNext()) {
			JuvenileDSMIVResults juvenileDSMIVResults = (JuvenileDSMIVResults) dsmIterator.next();
			DSMIVTestResponseEvent dsmRespEvent = getDSMResponseEvent(juvenileDSMIVResults);
			dispatch.postEvent(dsmRespEvent);
		}
	}

	/**
	 * @param juvenileDSMIVResults
	 * @return
	 */
	private DSMIVTestResponseEvent getDSMResponseEvent(JuvenileDSMIVResults juvenileDSMIVResults) {
		DSMIVTestResponseEvent dsmRespEvent = new DSMIVTestResponseEvent();
		dsmRespEvent.setTestDate(juvenileDSMIVResults.getTestDate());
		dsmRespEvent.setTestSessId(juvenileDSMIVResults.getTestSessId());
		dsmRespEvent.setServiceEventId(juvenileDSMIVResults.getServiceEventId());
		return dsmRespEvent;
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
