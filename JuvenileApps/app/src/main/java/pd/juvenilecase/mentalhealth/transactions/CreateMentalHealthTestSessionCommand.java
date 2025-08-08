//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\CreateMentalHealthTestSessionCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.CreateMentalHealthTestSessionEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.mentalhealth.JuvenileTestingResults;

public class CreateMentalHealthTestSessionCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD090336
    */
   public CreateMentalHealthTestSessionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C9003E4
    */
   public void execute(IEvent event) 
   {
   	Home home = new Home();
   	CreateMentalHealthTestSessionEvent testEvent = (CreateMentalHealthTestSessionEvent)event ;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileTestingResults testingResults = new JuvenileTestingResults();
   	testingResults.setSessionDate(testEvent.getSessionDate());   	
   	testingResults.setJuvenileNum(testEvent.getJuvenileNum());   	
   	testingResults.setProgramStatus(testEvent.getProgramStatus());
   	testingResults.setTestTypeId(testEvent.getTestType());
   	testingResults.setActualSessionLength(testEvent.getActualSessionLength());
   	testingResults.setPsychologicalAssessment(testEvent.getPsychologicalAssessment());
   	testingResults.setPsychiatricAssessment(testEvent.getPsychiatricAssessment());
   	testingResults.setMentalRetardationDiagnosis(testEvent.getMentalRetardationDiagnosis());
   	testingResults.setMentalillnessDiagnosis(testEvent.getMentalIllnessDiagnosis());
   	testingResults.setRecommendations(testEvent.getRecommendations());
   	testingResults.setServiceEventId(testEvent.getServiceEventId());
   	home.bind(testingResults);
   	TestingSessionResponseEvent testResp = new TestingSessionResponseEvent();
   	testResp.setTestSessID((String)testingResults.getOID());
   	dispatch.postEvent(testResp);
   }
   
   /**
    * @param event
    * @roseuid 45D49C9003E6
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C9003E8
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D49C91000B
    */
   public void update(Object anObject) 
   {
    
   }
  
}
