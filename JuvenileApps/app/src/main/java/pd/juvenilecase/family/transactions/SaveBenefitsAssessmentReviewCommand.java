//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\SaveBenefitsAssessmentCommand.java

package pd.juvenilecase.family.transactions;

import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.BenefitsAssessmentReview;
import messaging.family.SaveBenefitsAssessmentReviewEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveBenefitsAssessmentReviewCommand implements ICommand 
{
   
   /**
    * @roseuid 4370FD9200EC
    */
   public SaveBenefitsAssessmentReviewCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436A39B30052
    */
   public void execute(IEvent event) 
   {
		SaveBenefitsAssessmentReviewEvent evt = (SaveBenefitsAssessmentReviewEvent)event;
		
		BenefitsAssessment ben = BenefitsAssessment.find( evt.getAssessmentId() );
		
		BenefitsAssessmentReview review = new BenefitsAssessmentReview(); 
		
		review.setComments( evt.getComments() );
		
		ben.getReviewComments().add( review );
   }
   
   /**
    * @param event
    * @roseuid 436A39B3005A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436A39B3005C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 436A39B3005E
    */
   public void update(Object anObject) 
   {
    
   }
}
