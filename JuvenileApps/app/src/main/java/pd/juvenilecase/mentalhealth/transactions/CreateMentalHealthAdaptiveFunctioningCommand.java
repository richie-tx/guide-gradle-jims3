//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\CreateMentalHealthAdaptiveFunctioningCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.CreateMentalHealthAdaptiveFunctioningEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults;

/**
 * @author cc_vnarsingoju
 *
 */
public class CreateMentalHealthAdaptiveFunctioningCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD030009
    */
   public CreateMentalHealthAdaptiveFunctioningCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C8302FB
    */
   public void execute(IEvent event) 
   {
	CreateMentalHealthAdaptiveFunctioningEvent requestEvent = (CreateMentalHealthAdaptiveFunctioningEvent)event;
	JuvenileAdaptiveFunctioningResults functioningResults = new JuvenileAdaptiveFunctioningResults();
	functioningResults.setTestSessId(requestEvent.getTestSessId());
	functioningResults.setTestDate(requestEvent.getTestDate());
	functioningResults.setTestNameId(requestEvent.getTestName());
	functioningResults.setStandardScore(requestEvent.getStandardScore());	
   }
   
   /**
    * @param event
    * @roseuid 45D49C8302FD
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C830308
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D49C83030A
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
