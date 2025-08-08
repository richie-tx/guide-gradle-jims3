//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\GetRequestedBenefitsAssessmentDetailCommand.java

package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import pd.juvenilecase.family.BenefitsAssessment;
import messaging.family.GetBenefitsAssessmentsEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;


/**
 * Returns benefits assessments that have been completed. The request will 
 * generally be for a specific juvenile.
 *  
 */
public class GetBenefitsAssessmentsCommand implements ICommand 
{
   
   /**
    * @roseuid 4370FD9100D7
    */
   public GetBenefitsAssessmentsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436F8B0C0000
    */
   public void execute(IEvent event) 
   {
		GetBenefitsAssessmentsEvent evt = (GetBenefitsAssessmentsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator assessments = BenefitsAssessment.findAll( "juvenileId", evt.getJuvenileNum() );
		while ( assessments.hasNext() )
		{
			BenefitsAssessment ben = (BenefitsAssessment)assessments.next();
			
			if ( ben.isCompleted() )
			{
				BenefitsAssessmentResponseEvent reply = new BenefitsAssessmentResponseEvent();

				reply.setAssessmentId( ben.getOID().toString() );
   
				reply.setJuvenileNumber( ben.getJuvenileId() );
				reply.setFirstName( ben.getJuvenile().getFirstName() );
				reply.setLastName( ben.getJuvenile().getLastName() );

				reply.setGuardianId( ben.getGuardianId() );
				reply.setGuardianFirstName( ben.getGuardian().getTheFamilyMember().getFirstName() );
				reply.setGuardianLastName( ben.getGuardian().getTheFamilyMember().getLastName() );
   
				reply.setEntryDate( ben.getEntryDate() );
   
				reply.setEligibleForMedicaid( ben.isEligibleForMedicaid() );
				reply.setReceivingMedicaid( ben.isReceivingMedicaid() );
				reply.setEligibleForTitleIVe( ben.isEligibleForTitleIVe() );
				reply.setReceivingTitleIVe( ben.isReceivingTitleIVe() );
			 
				dispatch.postEvent(reply);
			}
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
