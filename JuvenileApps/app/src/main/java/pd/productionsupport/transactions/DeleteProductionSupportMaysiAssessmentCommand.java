package pd.productionsupport.transactions;

import messaging.productionsupport.DeleteProductionSupportMaysiAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

public class DeleteProductionSupportMaysiAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public DeleteProductionSupportMaysiAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		DeleteProductionSupportMaysiAssessmentEvent assessmentEvent = (DeleteProductionSupportMaysiAssessmentEvent) event;
		JuvenileMAYSIMetadata maysiAssessment = JuvenileMAYSIMetadata.find(assessmentEvent.getAssessmentId());
		maysiAssessment.delete();
	}
	
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}
