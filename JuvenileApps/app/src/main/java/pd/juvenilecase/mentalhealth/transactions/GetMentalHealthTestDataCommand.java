//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthTestDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.GetMentalHealthTestDataEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileTestingResults;

public class GetMentalHealthTestDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD10000A
    */
   public GetMentalHealthTestDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C77005A
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthTestDataEvent mEvent = (GetMentalHealthTestDataEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileTestingResults juvenileTestingDetails = JuvenileTestingResults.find(mEvent.getTestSessID());
   	TestingSessionResponseEvent testRespEvent = getTestingSessionResponseEvent(juvenileTestingDetails);
   	dispatch.postEvent(testRespEvent);
   }
   
   /**
 * @param juvenileTestingDetails
 * @return
 */
private TestingSessionResponseEvent getTestingSessionResponseEvent(JuvenileTestingResults juvenileTestingDetails) {
	TestingSessionResponseEvent responseEvent = new TestingSessionResponseEvent();
	responseEvent.setServiceEventId(juvenileTestingDetails.getServiceEventId());
	responseEvent.setActualSessionLength(juvenileTestingDetails.getActualSessionLength());
	responseEvent.setTestSessID(juvenileTestingDetails.getTestSessID());
	responseEvent.setMentalIllnessDiagnosis(juvenileTestingDetails.getMentalillnessDiagnosis());
	responseEvent.setMentalRetardationDiagnosis(juvenileTestingDetails.getMentalRetardationDiagnosis());
	responseEvent.setProgramReferralNum(juvenileTestingDetails.getProgramReferralNum());
	responseEvent.setProgramStatus(juvenileTestingDetails.getProgramStatus());
	responseEvent.setPsychiatricAssessment(juvenileTestingDetails.getPsychiatricAssessment());
	responseEvent.setPsychologicalAssessment(juvenileTestingDetails.getPsychologicalAssessment());
	responseEvent.setRecommendations(juvenileTestingDetails.getRecommendations());
	responseEvent.setReferralDate(juvenileTestingDetails.getReferralDate());
	responseEvent.setSessionDate(juvenileTestingDetails.getSessionDate());
	responseEvent.setTestType(juvenileTestingDetails.getTestTypeId());
	return responseEvent;
}

/**
    * @param event
    * @roseuid 45D49C77005C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C77005E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D49C770068
    */
   public void update(Object anObject) 
   {
    
   }
   

}
