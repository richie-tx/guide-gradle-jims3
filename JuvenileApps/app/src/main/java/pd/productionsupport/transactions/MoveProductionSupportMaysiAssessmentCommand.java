package pd.productionsupport.transactions;

import messaging.productionsupport.MoveProductionSupportMaysiAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

public class MoveProductionSupportMaysiAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public MoveProductionSupportMaysiAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		MoveProductionSupportMaysiAssessmentEvent assessmentEvent = (MoveProductionSupportMaysiAssessmentEvent) event;
		JuvenileMAYSIMetadata maysiAssessment = JuvenileMAYSIMetadata.find(assessmentEvent.getAssessmentId());
		maysiAssessment.setJuvenileNumber(assessmentEvent.getJuvenileNumber());
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
