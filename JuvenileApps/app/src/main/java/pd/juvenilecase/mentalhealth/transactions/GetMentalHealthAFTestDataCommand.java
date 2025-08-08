//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthAFTestDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.GetMentalHealthAFTestDataEvent;
import messaging.mentalhealth.reply.AFTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthAFTestDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB19120113
    */
   public GetMentalHealthAFTestDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B30345
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthAFTestDataEvent afEvent = (GetMentalHealthAFTestDataEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileAdaptiveFunctioningResults afResults =  JuvenileAdaptiveFunctioningResults.find(afEvent.getTestId());
   	AFTestResponseEvent afRespEvent = getAFTestResponseEvent(afResults);
	dispatch.postEvent(afRespEvent);
    
   }
   
   /**
 * @param afResults
 * @return
 */
private AFTestResponseEvent getAFTestResponseEvent(JuvenileAdaptiveFunctioningResults afResults) {
	AFTestResponseEvent afRespEvent = new AFTestResponseEvent();
	afRespEvent.setTestDate(afResults.getTestDate());
	afRespEvent.setTestId(afResults.getTestId());
	afRespEvent.setTestSessId(afResults.getTestSessId());
	afRespEvent.setTestName(afResults.getTestNameId());
	afRespEvent.setStandardScore(afResults.getStandardScore());
	return afRespEvent;
	
}

/**
    * @param event
    * @roseuid 45DB18B30347
    */
   public void onRegister(IEvent event) 
   {
   	
   }
   
   /**
    * @param event
    * @roseuid 45DB18B30353
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB18B30355
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
