//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthIQTestDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.GetMentalHealthIQTestDataEvent;
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
public class GetMentalHealthIQTestDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB1918029A
    */
   public GetMentalHealthIQTestDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B3021C
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthIQTestDataEvent iqEvent = (GetMentalHealthIQTestDataEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileIQResults juvenileIQResults = JuvenileIQResults.find(iqEvent.getTestId());
   	IQTestResponseEvent iqResponseEvent = getIQTestResponseEvent(juvenileIQResults);
   	dispatch.postEvent(iqResponseEvent);
   }
   
  
/**
 * @param juvenileIQResults
 * @return
 */
private IQTestResponseEvent getIQTestResponseEvent(JuvenileIQResults juvenileIQResults) {
	IQTestResponseEvent iqResponseEvent = new IQTestResponseEvent();
	iqResponseEvent.setTestId(juvenileIQResults.getTestId());
	iqResponseEvent.setTestSessId(juvenileIQResults.getTestSessId());
	iqResponseEvent.setTestDate(juvenileIQResults.getTestDate());
	iqResponseEvent.setTestName(juvenileIQResults.getTestNameId());
	iqResponseEvent.setFullScore(juvenileIQResults.getFullScore());
	iqResponseEvent.setPerformanceScore(juvenileIQResults.getPerformanceScore());
	iqResponseEvent.setVerbalScore(juvenileIQResults.getVerbalScore());
	iqResponseEvent.setRecommendation(juvenileIQResults.getRecommendation());
	iqResponseEvent.setGeometricIQ(juvenileIQResults.getGeometricIQ());
	iqResponseEvent.setNonVerbalIQ(juvenileIQResults.getNonVerbalIQ());
	iqResponseEvent.setPerceptualReasoning(juvenileIQResults.getPerceptualReasoning());
	iqResponseEvent.setPictorialIQ(juvenileIQResults.getPictorialIQ());
	iqResponseEvent.setProcessingSpeed(juvenileIQResults.getProcessingSpeed());
	iqResponseEvent.setTestId(juvenileIQResults.getTestId());
	iqResponseEvent.setTestSessId(juvenileIQResults.getTestSessId());
	iqResponseEvent.setVerbalComprehension(juvenileIQResults.getVerbalComprehension());
	iqResponseEvent.setWorkingMemory(juvenileIQResults.getWorkingMemory());
	return iqResponseEvent;
}

/**
    * @param event
    * @roseuid 45DB18B3021E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B3022B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB18B3022D
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
