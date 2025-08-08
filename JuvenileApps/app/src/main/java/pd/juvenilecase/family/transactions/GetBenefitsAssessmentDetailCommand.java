//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\GetRequestedBenefitsAssessmentDetailCommand.java

package pd.juvenilecase.family.transactions;

import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.BenefitsAssessmentHelper;
import messaging.family.GetBenefitsAssessmentDetailEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetBenefitsAssessmentDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 4370FD9100D7
    */
   public GetBenefitsAssessmentDetailCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 436F8B0C0000
    */
   public void execute(IEvent event) 
   {
		GetBenefitsAssessmentDetailEvent evt = (GetBenefitsAssessmentDetailEvent)event;
		
		BenefitsAssessment ben = BenefitsAssessment.find( evt.getAssessmentId() );
	
		BenefitsAssessmentDetailResponseEvent reply = BenefitsAssessmentHelper.getBenefitsAssessmentDetailResponseEvent(ben);
			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
   }
   
   /**
    * @param event
    * @roseuid 436F8B0C0002
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436F8B0C000A
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 436F8B0C000C
    */
   public void update(Object anObject) 
   {
    
   }
   
}
