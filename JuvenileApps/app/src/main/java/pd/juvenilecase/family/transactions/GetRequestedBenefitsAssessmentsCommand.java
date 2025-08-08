//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\GetRequestedBenefitsAssessmentDetailCommand.java

package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import pd.juvenile.Juvenile;
import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.BenefitsAssessmentHelper;
import messaging.family.GetRequestedBenefitsAssessmentsEvent;
import messaging.juvenilecase.reply.RequestedBenefitsAssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetRequestedBenefitsAssessmentsCommand implements ICommand 
{
   
   /**
    * @roseuid 4370FD9100D7
    */
   public GetRequestedBenefitsAssessmentsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436F8B0C0000
    */
   public void execute(IEvent event) 
   {
		GetRequestedBenefitsAssessmentsEvent evt = (GetRequestedBenefitsAssessmentsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator iter = BenefitsAssessment.findAll( evt );
		while ( iter.hasNext() )
		{
			BenefitsAssessment ben = (BenefitsAssessment)iter.next();
			
			RequestedBenefitsAssessmentResponseEvent reply = BenefitsAssessmentHelper.getRequestedBenefitsAssessmentResponseEvent(ben);
			
			dispatch.postEvent(reply);
		}
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
