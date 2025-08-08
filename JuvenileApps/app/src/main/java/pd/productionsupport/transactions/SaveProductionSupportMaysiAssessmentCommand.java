package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportMaysiAssessmentEvent;
import messaging.productionsupport.SaveProductionSupportMaysiAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

public class SaveProductionSupportMaysiAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public SaveProductionSupportMaysiAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		SaveProductionSupportMaysiAssessmentEvent maysiAssessmentEvent = (SaveProductionSupportMaysiAssessmentEvent) event;
		JuvenileMAYSIMetadata maysiMetadata  = JuvenileMAYSIMetadata.find(maysiAssessmentEvent.getMaysiAssessmentId());
		if(maysiAssessmentEvent.getAssessmentOption() != null && maysiMetadata.getAssessmentOptionId() != null
				&& !maysiAssessmentEvent.getAssessmentOption().equals(maysiMetadata.getAssessmentOptionId())){
			maysiMetadata.setAssessmentOptionId(maysiAssessmentEvent.getAssessmentOption());		
		}
		
		//maysiMetadata.s
		
		if(maysiAssessmentEvent.getAssessOfficerId() != null 
			&& !maysiAssessmentEvent.getAssessOfficerId().equals(maysiMetadata.getRequestingOfficerId())){
		maysiMetadata.setRequestingOfficerId(maysiAssessmentEvent.getAssessOfficerId());		
	}
		
		if(maysiAssessmentEvent.getReferralNumber() != null && maysiMetadata.getReferralNumber() != null
				&& !maysiAssessmentEvent.getReferralNumber().equals(maysiMetadata.getReferralNumber())){
			maysiMetadata.setReferralNumber(maysiAssessmentEvent.getReferralNumber());		
		}
		if(maysiAssessmentEvent.getAssessmentDate() != null){
			maysiMetadata.setRequestDate(maysiAssessmentEvent.getAssessmentDate());		
		}
		if(maysiAssessmentEvent.getHasPreviousMaysi() != maysiMetadata.isHasPreviousMAYSI()){
			maysiMetadata.setHasPreviousMAYSI(maysiAssessmentEvent.getHasPreviousMaysi());		
		}
		if(maysiAssessmentEvent.getIsAdministered() != maysiMetadata.isAdministered()){
			maysiMetadata.setAdministered(maysiAssessmentEvent.getIsAdministered());		
		}
		
		if(maysiAssessmentEvent.getReasonNotDone() != null &&  !maysiAssessmentEvent.getReasonNotDone().equals(maysiMetadata.getReasonNotDoneId())){
		maysiMetadata.setReasonNotDoneId(maysiAssessmentEvent.getReasonNotDone());		
	}
		
		if(maysiAssessmentEvent.getReasonNotDone() == null && maysiMetadata.getReasonNotDoneId() != null)
		{
		    maysiMetadata.setReasonNotDoneId(null);
		}
			
		
		if(maysiAssessmentEvent.getOtherReasonNotDone() != null &&  !maysiAssessmentEvent.getOtherReasonNotDone().equals(maysiMetadata.getOtherReasonNotDone())){
		maysiMetadata.setOtherReasonNotDone(maysiAssessmentEvent.getOtherReasonNotDone());		
	}
		
		if(maysiAssessmentEvent.getOtherReasonNotDone() == null && maysiMetadata.getOtherReasonNotDone() != null)
		{
		    maysiMetadata.setOtherReasonNotDone(null);
		}
		
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
