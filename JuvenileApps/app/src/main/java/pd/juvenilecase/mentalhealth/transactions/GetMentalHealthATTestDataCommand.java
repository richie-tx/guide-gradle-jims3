//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthATTestDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.GetMentalHealthATTestDataEvent;
import messaging.mentalhealth.reply.ATTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileAchievementResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthATTestDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB19160142
    */
   public GetMentalHealthATTestDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B20307
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthATTestDataEvent atEvent = (GetMentalHealthATTestDataEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileAchievementResults attResults =  JuvenileAchievementResults.find(atEvent.getTestId());
   	ATTestResponseEvent attRespEvent = getATTestResponseEvent(attResults);
	dispatch.postEvent(attRespEvent);
   }
   
   /**
 * @param attResults
 * @return
 */
private ATTestResponseEvent getATTestResponseEvent(JuvenileAchievementResults attResults) {
	ATTestResponseEvent attRespEvent = new ATTestResponseEvent();
	attRespEvent.setTestId(attResults.getTestId());
	attRespEvent.setTestSessId(attResults.getTestSessId());
	attRespEvent.setTestname(attResults.getTestNameId());
	attRespEvent.setTestDate(attResults.getTestDate());
	attRespEvent.setArithmeticGradeLevel(attResults.getArithmeticGradeLevel());
	attRespEvent.setArithmeticScore(attResults.getArithmeticScore());
	attRespEvent.setReadingGradeLevel(attResults.getReadingGradeLevel());
	attRespEvent.setReadingScore(attResults.getReadingScore());
	attRespEvent.setSpellingGradeLevel(attResults.getSpellingGradeLevel());
	attRespEvent.setSpellingScore(attResults.getSpellingScore());
	attRespEvent.setRecommendations(attResults.getRecommendation());
	attRespEvent.setSentenceCompletionLevel(attResults.getSentenceCompletionLevel());
	attRespEvent.setSentenceCompletionScore(attResults.getSentenceCompletionScore());
	attRespEvent.setReadingCompositeLevel(attResults.getReadingCompositeLevel());
	attRespEvent.setReadingCompositeScore(attResults.getReadingCompositeScore());
	return attRespEvent;
}

/**
    * @param event
    * @roseuid 45DB18B20309
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B20315
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB18B20317
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
