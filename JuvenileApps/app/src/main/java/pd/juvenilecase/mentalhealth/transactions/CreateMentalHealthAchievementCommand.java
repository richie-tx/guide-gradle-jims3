//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\CreateMentalHealthAchievementCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import pd.juvenilecase.mentalhealth.JuvenileAchievementResults;
import messaging.mentalhealth.CreateMentalHealthAchievementEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 *
 */
public class CreateMentalHealthAchievementCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AC080051
    */
   public CreateMentalHealthAchievementCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C7D0174
    */
   public void execute(IEvent event) 
   {
    CreateMentalHealthAchievementEvent requestEvent = (CreateMentalHealthAchievementEvent)event;
    JuvenileAchievementResults juvAchievementResults = new JuvenileAchievementResults();
    juvAchievementResults.setTestSessId(requestEvent.getTestSessId());
    juvAchievementResults.setTestNameId(requestEvent.getTestName());
    juvAchievementResults.setTestDate(requestEvent.getTestDate());
    juvAchievementResults.setArithmeticGradeLevel(requestEvent.getArithmeticGradeLevel());
    juvAchievementResults.setArithmeticScore(requestEvent.getArithmeticScore());
    juvAchievementResults.setReadingGradeLevel(requestEvent.getReadingGradeLevel());
    juvAchievementResults.setReadingScore(requestEvent.getReadingScore());
    juvAchievementResults.setSpellingGradeLevel(requestEvent.getSpellingGradeLevel());
    juvAchievementResults.setSpellingScore(requestEvent.getSpellingScore());
    juvAchievementResults.setRecommendation(requestEvent.getRecommendations());    
    juvAchievementResults.setSentenceCompletionLevel(requestEvent.getSentenceCompletionLevel());
    juvAchievementResults.setSentenceCompletionScore(requestEvent.getSentenceCompletionScore());
    juvAchievementResults.setReadingCompositeLevel(requestEvent.getReadingCompositeLevel());
    juvAchievementResults.setReadingCompositeScore(requestEvent.getReadingCompositeScore());
   }
   
   /**
    * @param event
    * @roseuid 45D49C7D0176
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C7D0182
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
