package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;
import pd.juvenilecase.mentalhealth.JuvenileTestingResults;
import messaging.mentalhealth.GetMentalHealthTestingSessionEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetMentalHealthTestingSessionCommand implements ICommand {

	/**
	 * @roseuid 45D4AD1100E5
	 */
	public GetMentalHealthTestingSessionCommand() {

	}

	/**
	 * @param event
	 * @roseuid 45D36FDA03DA
	 */
	public void execute(IEvent event) {
		GetMentalHealthTestingSessionEvent tEvent = (GetMentalHealthTestingSessionEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator testIterator = JuvenileTestingResults.findAll(tEvent);
		String testSessionId = null;
		TestingSessionResponseEvent testRespEvent = null;
		while (testIterator.hasNext()) {
			JuvenileTestingResults juvenileTestingResults = (JuvenileTestingResults) testIterator.next();
			testRespEvent = getTestingSessionResponseEvent(juvenileTestingResults);
			testSessionId = juvenileTestingResults.getTestSessID();
		}
		if (testSessionId != null) {
			JuvenileTestingResults testSession = JuvenileTestingResults.find(testSessionId);
			TestingSessionResponseEvent testingRespEvent = getMoreTestingSessionResponseEvent(testSession, testRespEvent);
			dispatch.postEvent(testingRespEvent);			
		}
	}

	/**
	 * @param juvenileTestingResults
	 * @return
	 */
	private TestingSessionResponseEvent getTestingSessionResponseEvent(
			JuvenileTestingResults juvenileTestingResults) {
		TestingSessionResponseEvent responseEvent = new TestingSessionResponseEvent();
		responseEvent.setSessionDate(juvenileTestingResults.getSessionDate());
		responseEvent.setTestSessID(juvenileTestingResults.getTestSessID());
		responseEvent.setServiceEventId(juvenileTestingResults.getServiceEventId());
		responseEvent.setServiceProviderName(juvenileTestingResults.getServiceProviderName());
		responseEvent.setEventType(juvenileTestingResults.getEventType());
		responseEvent.setProgramReferralNum(juvenileTestingResults.getProgramReferralNum());
		responseEvent.setReferralStatusCd(juvenileTestingResults.getReferralStatusCd());
		responseEvent.setReferralSubStatusCd(juvenileTestingResults.getReferralSubStatusCd());
		responseEvent.setReferralDate(juvenileTestingResults.getSentDate());
		responseEvent.setPsychiatricAssessment(juvenileTestingResults.getPsychiatricAssessment());
		responseEvent.setPsychologicalAssessment(juvenileTestingResults.getPsychologicalAssessment());
		responseEvent.setInstructorName(juvenileTestingResults.getInstrLastName() + ", "
				+ juvenileTestingResults.getInstrFirstName()+ " "
				+ juvenileTestingResults.getInstrMiddleName());
		return responseEvent;
	}

	/**
	 * @param juvenileTestingResults
	 * @return
	 */
	private TestingSessionResponseEvent getMoreTestingSessionResponseEvent(
			JuvenileTestingResults juvenileTestingResults, TestingSessionResponseEvent responseEvent) {
		if (juvenileTestingResults.getTestTypeId() != null) {
			responseEvent.setTestType(juvenileTestingResults.getTestTypeId());
		}
		responseEvent.setActualSessionLength(juvenileTestingResults.getActualSessionLength());
		responseEvent.setMentalIllnessDiagnosis(juvenileTestingResults.getMentalillnessDiagnosis());
		responseEvent.setMentalRetardationDiagnosis(juvenileTestingResults.getMentalRetardationDiagnosis());
		responseEvent.setRecommendations(juvenileTestingResults.getRecommendations());
		return responseEvent;
	}
		
	/**
	 * @param event
	 * @roseuid 45D36FDA03E7
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 45D36FDA03E9
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 45D36FDA03EB
	 */
	public void update(Object anObject) {

	}
}
