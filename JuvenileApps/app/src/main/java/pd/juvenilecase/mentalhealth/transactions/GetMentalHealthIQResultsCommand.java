// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthIQResultsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetMentalHealthIQResultsEvent;
import messaging.mentalhealth.reply.IQTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileIQResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthIQResultsCommand implements ICommand {

	/**
	 * @roseuid 45D4AD0E02AA
	 */
	public GetMentalHealthIQResultsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 45D36FDB0167
	 */
	public void execute(IEvent event) {
		GetMentalHealthIQResultsEvent iqEvent = (GetMentalHealthIQResultsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator iqIterator = JuvenileIQResults.findAll(iqEvent);
		while (iqIterator.hasNext()) {
			JuvenileIQResults juvenileIQResults = (JuvenileIQResults) iqIterator.next();
			IQTestResponseEvent iqRespEvent = getIQTestResponseEvent(juvenileIQResults);
			dispatch.postEvent(iqRespEvent);
		}
	}

	/**
	 * @param juvenileIQResults
	 * @return
	 */
	private IQTestResponseEvent getIQTestResponseEvent(JuvenileIQResults juvenileIQResults) {
		IQTestResponseEvent iqRespEvent = new IQTestResponseEvent();
		iqRespEvent.setTestId(juvenileIQResults.getTestId());
		iqRespEvent.setTestSessId(juvenileIQResults.getTestSessId());
		iqRespEvent.setTestDate(juvenileIQResults.getTestDate());
		iqRespEvent.setFullScore(juvenileIQResults.getFullScore());
		iqRespEvent.setProgramReferralNum(juvenileIQResults.getProgramReferralNum());
		iqRespEvent.setServiceProviderName(juvenileIQResults.getServiceProviderName());
		iqRespEvent.setServiceEventId(juvenileIQResults.getServiceEventId());
		iqRespEvent.setInstructorName(juvenileIQResults.getInstrLastName()+", "+
		     juvenileIQResults.getInstrFirstName()+" "+juvenileIQResults.getInstrMiddleName());
		iqRespEvent.setPerformanceScore(juvenileIQResults.getPerformanceScore());
		iqRespEvent.setVerbalScore(juvenileIQResults.getVerbalScore());
		if (juvenileIQResults.getTestNameId() != null && !juvenileIQResults.getTestNameId().equals("")) {
			iqRespEvent.setTestName(juvenileIQResults.getTestName().getDescription());	
		}
		return iqRespEvent;
	}

	/**
	 * @param event
	 * @roseuid 45D36FDB0169
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 45D36FDB016B
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 45D36FDB0176
	 */
	public void update(Object anObject) {

	}

}
