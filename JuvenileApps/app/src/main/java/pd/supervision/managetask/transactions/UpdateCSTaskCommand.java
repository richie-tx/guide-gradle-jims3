//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managetask\\transactions\\UpdateCSTaskCommand.java

package pd.supervision.managetask.transactions;

import pd.supervision.managetask.CSTask;
import messaging.managetask.UpdateCSTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class UpdateCSTaskCommand implements ICommand 
{
   
   /**
    * @roseuid 463F300003B7
    */
   public UpdateCSTaskCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 463F171C03CB
    */
   public void execute(IEvent event) 
   {
    
	   UpdateCSTaskEvent updateEvent = (UpdateCSTaskEvent) event;
	   CSTask cstask = new CSTask();
	   
	  	cstask = cstask.find(updateEvent.getCsTaskId());
		if (cstask != null) {
			
			 if ( updateEvent.isAcceptTask() ){
				
				   cstask.setStatusId( updateEvent.getStatusId());
				   cstask.setStaffPositionId( Integer.parseInt( updateEvent.getStaffPositionId() ));
				   cstask.setWorkGroupId( 0 );
				   
			   } else{
				   
				   if ( updateEvent.isCloseTask()){
					   
					   cstask.setStatusId( updateEvent.getStatusId());
				   } else { // transfer
					   
					   cstask.setLastTransferDate(updateEvent.getLastTransferDate());
					   cstask.setLastTransferUser(updateEvent.getLastTransferUser());
				   }
				   
			   }

		}
		
		updateEvent = null;
		cstask = null;
	   
   }
   
   /**
    * @param event
    * @roseuid 463F171C03D8
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 463F171C03DA
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
      
   /**
    * @param updateObject
    * @roseuid 463F300003D7
    */
   public void update(Object updateObject) 
   {
    
   }
}
