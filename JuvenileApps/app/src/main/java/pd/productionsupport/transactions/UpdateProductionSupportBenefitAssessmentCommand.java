package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.RetrieveJuvenileProgramReferralsEvent;
import messaging.productionsupport.UpdateProductionSupportBenefitAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.family.BenefitsAssessment;
import pd.supervision.programreferral.JuvenileProgramReferral;

public class UpdateProductionSupportBenefitAssessmentCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportBenefitAssessmentCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportBenefitAssessment");
	   UpdateProductionSupportBenefitAssessmentEvent updateEvent = (UpdateProductionSupportBenefitAssessmentEvent) event;
	   Iterator benefitAssessmentsIter = BenefitsAssessment.findAll("casefileId",updateEvent.getCasefileId());
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   while(benefitAssessmentsIter.hasNext()){
			   BenefitsAssessment benefitAssessment = (BenefitsAssessment)benefitAssessmentsIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   benefitAssessment.setCasefileId(updateEvent.getMergeToCasefileId());
			   }
		   }
	   }
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02F0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44C8E0DB02FF
    */
   public void update(Object anObject) 
   {
    
   } 
}
