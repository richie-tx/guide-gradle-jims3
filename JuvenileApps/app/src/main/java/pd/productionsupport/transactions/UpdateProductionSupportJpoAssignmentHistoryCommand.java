package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportActivityEvent;
import messaging.productionsupport.UpdateProductionSupportAssignmentEvent;
import messaging.productionsupport.UpdateProductionSupportJpoAssignmentHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JPOAssignmentHistory;
import pd.juvenilecase.JuvenileCasefile;

public class UpdateProductionSupportJpoAssignmentHistoryCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportJpoAssignmentHistoryCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportJpoAssignmentHistory");
	   UpdateProductionSupportJpoAssignmentHistoryEvent updateEvent = (UpdateProductionSupportJpoAssignmentHistoryEvent) event;
	   Iterator<JPOAssignmentHistory> assignmentHistoryIter = JPOAssignmentHistory.findAll("casefileId",updateEvent.getCasefileId());
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   while(assignmentHistoryIter.hasNext()){
			   JPOAssignmentHistory jpoAssignmentHistory = (JPOAssignmentHistory)assignmentHistoryIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   jpoAssignmentHistory.setCasefileId(updateEvent.getMergeToCasefileId());
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
