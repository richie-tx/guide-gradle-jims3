package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportJuvenileCasefileSupervisionRulesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;

public class UpdateProductionSupportJuvenileCasefileSupervisionRulesCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportJuvenileCasefileSupervisionRulesCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportJuvenileCasefileSupervisionRules");
	   UpdateProductionSupportJuvenileCasefileSupervisionRulesEvent updateEvent = (UpdateProductionSupportJuvenileCasefileSupervisionRulesEvent) event;
	   Iterator JuvenileCasefileSupervisionRulesIter = JuvenileCaseSupervisionRule.findAll("casefileId",updateEvent.getCasefileId());
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   while(JuvenileCasefileSupervisionRulesIter.hasNext()){
			   JuvenileCaseSupervisionRule caseSupervisionRule = (JuvenileCaseSupervisionRule)JuvenileCasefileSupervisionRulesIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   caseSupervisionRule.setCasefileId(updateEvent.getMergeToCasefileId());
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
