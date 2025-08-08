package pd.productionsupport.transactions;


import messaging.productionsupport.DeleteProductionSupportIntakeHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.JuvenileTrait;
import pd.juvenilecase.referral.JJSSVIntakeHistory;


/**
 * @author rcarter
 */

public class DeleteProductionSupportIntakeHistoryCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public DeleteProductionSupportIntakeHistoryCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   
	   DeleteProductionSupportIntakeHistoryEvent deleteEvent = (DeleteProductionSupportIntakeHistoryEvent) event;
	          JJSSVIntakeHistory intakeHistory = JJSSVIntakeHistory.find(deleteEvent.getIntakeHistoryId());
		   if(intakeHistory != null ){
		       intakeHistory.delete();
		       new Home().bind(intakeHistory);

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
