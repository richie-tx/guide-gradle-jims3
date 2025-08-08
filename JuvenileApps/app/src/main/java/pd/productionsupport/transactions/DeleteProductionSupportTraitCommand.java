package pd.productionsupport.transactions;


import messaging.productionsupport.DeleteProductionSupportTraitEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileTrait;


/**
 * @author rcarter
 */

public class DeleteProductionSupportTraitCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public DeleteProductionSupportTraitCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("DeleteProductionSupportTraitCommand");
	   DeleteProductionSupportTraitEvent deleteEvent = (DeleteProductionSupportTraitEvent) event;
		 JuvenileTrait trait = JuvenileTrait.find(deleteEvent.getTraitId());
		   if(trait != null ){
			   trait.delete();
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
