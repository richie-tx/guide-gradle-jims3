//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\RequestBenefitsAssessmentCommand.java

package pd.juvenilecase.family.transactions;

import messaging.family.RequestBenefitsAssessmentEvent;
import messaging.juvenilecase.reply.RequestedBenefitsAssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.BenefitsAssessmentHelper;

public class RequestBenefitsAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4370E9C60185
    */
   public RequestBenefitsAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436A435202D3
    */
   public void execute(IEvent event) 
   {
		RequestBenefitsAssessmentEvent evt = (RequestBenefitsAssessmentEvent)event;
		
		BenefitsAssessment ben = BenefitsAssessment.requestNew( evt.getJuvenileNum(), evt.getCasefileId(), evt.getRequesterName() );
		RequestedBenefitsAssessmentResponseEvent reply = BenefitsAssessmentHelper.getRequestedBenefitsAssessmentResponseEvent(ben);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
   }
   
   /**
    * @param event
    * @roseuid 436A435202D5
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436A435202D7
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 436A435202D9
    */
   public void update(Object anObject) 
   {
    
   }
   
}
