package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportTraitEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileTrait;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

public class UpdateProductionSupportTraitCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportTraitCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportTrait");
	   UpdateProductionSupportTraitEvent updateEvent = (UpdateProductionSupportTraitEvent) event;

	  

	   if (updateEvent.getJuvenileTraitId() != null && !updateEvent.getJuvenileTraitId().isEmpty()) {
	       JuvenileTrait trait = JuvenileTrait.find(updateEvent.getJuvenileTraitId());
	       if (trait != null) {
	           trait.setJuvenileNum(updateEvent.getJuvenileNum());
	           trait.setSupervisionNum(updateEvent.getSupervisionNum());
	           trait.setTraitTypeId(updateEvent.getTraitTypeId());
	           trait.setStatusId(updateEvent.getTraitStatus());
	       }
	   } else if (updateEvent.getCasefileId() != null && !updateEvent.getCasefileId().isEmpty() &&
	              updateEvent.getMergeToCasefileId() != null && !updateEvent.getMergeToCasefileId().isEmpty()) {
	       
	       Iterator traitsIter = JuvenileTrait.findAllByAttributeName("supervisionNum", updateEvent.getCasefileId());
	       while (traitsIter.hasNext()) {
	           JuvenileTrait trait = (JuvenileTrait) traitsIter.next();
	           if (updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0) {
	               trait.setSupervisionNum(updateEvent.getMergeToCasefileId());
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
