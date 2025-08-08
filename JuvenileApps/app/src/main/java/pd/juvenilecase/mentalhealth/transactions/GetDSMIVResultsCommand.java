// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetDSMIVResultsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetDSMIVResultsEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileDSMIVResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetDSMIVResultsCommand implements ICommand {

	/**
	 * @roseuid 45D4A5F8000A
	 */
	public GetDSMIVResultsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 45D36FDB009C
	 */
	public void execute(IEvent event) {
		GetDSMIVResultsEvent dEvent = (GetDSMIVResultsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator dsmIterator = JuvenileDSMIVResults.findAll(dEvent);
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
		dsmRespEvent.setProgramReferralNum(juvenileDSMIVResults.getProgramReferralNum());
		dsmRespEvent.setServiceProviderName(juvenileDSMIVResults.getServiceProviderName());
		dsmRespEvent.setTestSessId(juvenileDSMIVResults.getTestSessId());
		dsmRespEvent.setTestId(juvenileDSMIVResults.getTestId());
		dsmRespEvent.setServiceEventId(juvenileDSMIVResults.getServiceEventId());
		dsmRespEvent.setInstructorName(juvenileDSMIVResults.getInstrLastName()+" "+
				juvenileDSMIVResults.getInstrMiddleName()+" "+juvenileDSMIVResults.getInstrFirstName());
		return dsmRespEvent;
	}

	/**
	 * @param event
	 * @roseuid 45D36FDB00AA
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 45D36FDB00AC
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 45D36FDB00AE
	 */
	public void update(Object anObject) {

	}

}
