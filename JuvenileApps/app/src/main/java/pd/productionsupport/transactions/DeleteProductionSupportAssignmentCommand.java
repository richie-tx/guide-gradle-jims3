package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.DeleteProductionSupportAssignmentEvent;
import messaging.productionsupport.UpdateProductionSupportAssignmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.Assignment;

public class DeleteProductionSupportAssignmentCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public DeleteProductionSupportAssignmentCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("DeleteProductionSupportAssignmentCommand");
	   DeleteProductionSupportAssignmentEvent deleteEvent = (DeleteProductionSupportAssignmentEvent) event;
		 Assignment assignment = Assignment.find(deleteEvent.getAssignmentId());
		   if(assignment != null ){
			   assignment.delete();
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
