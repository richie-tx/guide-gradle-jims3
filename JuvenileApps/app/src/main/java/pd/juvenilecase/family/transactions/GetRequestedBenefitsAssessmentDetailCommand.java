//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\GetRequestedBenefitsAssessmentDetailCommand.java

package pd.juvenilecase.family.transactions;

import messaging.family.GetRequestedBenefitsAssessmentDetailEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.BenefitsAssessmentHelper;
import pd.juvenilecase.family.FamilyConstellationMember;

public class GetRequestedBenefitsAssessmentDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 4370FD9100D7
    */
   public GetRequestedBenefitsAssessmentDetailCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436F8B0C0000
    */
   public void execute(IEvent event) 
   {
		GetRequestedBenefitsAssessmentDetailEvent evt = (GetRequestedBenefitsAssessmentDetailEvent)event;
		
		BenefitsAssessment ben = BenefitsAssessment.find( evt.getAssessmentId() );

		FamilyConstellationMember guardian = FamilyConstellationMember.find( evt.getGuardianId() ); 
		ben.startEntryForGuardian( guardian );
		
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
