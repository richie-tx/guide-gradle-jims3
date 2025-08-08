//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthTestingResultsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetMentalHealthTestingResultsEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileTestingResults;

public class GetMentalHealthTestingResultsCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD1100E5
    */
   public GetMentalHealthTestingResultsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D36FDA03DA
    */
   public void execute(IEvent event) 
   {
    GetMentalHealthTestingResultsEvent tEvent = (GetMentalHealthTestingResultsEvent)event;
    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
    Iterator testIterator = JuvenileTestingResults.findAll(tEvent);
    while (testIterator.hasNext()) {
    	JuvenileTestingResults juvenileTestingResults = (JuvenileTestingResults) testIterator.next();
    	TestingSessionResponseEvent testRespEvent = getTestingSessionResponseEvent(juvenileTestingResults);
    	dispatch.postEvent(testRespEvent);
	}
    	 
   }
   
   /**
 * @param juvenileTestingResults
 * @return
 */
private TestingSessionResponseEvent getTestingSessionResponseEvent(JuvenileTestingResults juvenileTestingResults) {
	
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
	
	responseEvent.setInstructorName(juvenileTestingResults.getInstrLastName()+", "+juvenileTestingResults.getInstrFirstName()+
			" "+juvenileTestingResults.getInstrMiddleName());
	return responseEvent;
	
}
   

   /**
    * @param event
    * @roseuid 45D36FDA03E7
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D36FDA03E9
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D36FDA03EB
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
