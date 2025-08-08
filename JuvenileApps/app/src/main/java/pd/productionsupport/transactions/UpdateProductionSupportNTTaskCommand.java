package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportNTTasksEvent;
import messaging.productionsupport.UpdateProductionSupportNTTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.task.Task;

public class UpdateProductionSupportNTTaskCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportNTTaskCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportNTTask");
	   UpdateProductionSupportNTTaskEvent updateEvent = (UpdateProductionSupportNTTaskEvent) event;
	   GetProductionSupportNTTasksEvent getNTTasksEvent = new GetProductionSupportNTTasksEvent();
	   getNTTasksEvent.setCasefileId(updateEvent.getCasefileId());
	   Iterator ntTasksIter = Task.findAll(getNTTasksEvent);
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals(""))) {
		   while(ntTasksIter.hasNext()){
			   Task ntTask = (Task)ntTasksIter.next();
			   ntTask.setStatusId("C"); // set status to C for each NT Task
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
