package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportSubstanceAbuseEvent;
import messaging.productionsupport.UpdateProductionSupportTraitEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenile.SubstanceAbuse;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileTrait;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

public class UpdateProductionSupportSubstanceAbuseCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportSubstanceAbuseCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportSubstanceAbuse");
	   UpdateProductionSupportSubstanceAbuseEvent updateEvent = (UpdateProductionSupportSubstanceAbuseEvent) event;	  
	   Iterator substanceAubseInfoIter = SubstanceAbuse.findAll("casefileId", updateEvent.getCasefileId() );
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
			updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
	   while(substanceAubseInfoIter.hasNext()){
	       SubstanceAbuse substanceAbuse = (SubstanceAbuse)substanceAubseInfoIter.next();
		   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
		       substanceAbuse.setCasefileId(updateEvent.getMergeToCasefileId());
		       IHome home = new Home();
		       home.bind(substanceAbuse);
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
