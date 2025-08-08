package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportAssignmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.Assignment;

public class UpdateProductionSupportAssignmentCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportAssignmentCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportAssignment");
	   UpdateProductionSupportAssignmentEvent updateEvent = (UpdateProductionSupportAssignmentEvent) event;
	   // updating based on merge casefiles
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   Iterator<Assignment> assignmentIter = Assignment.findAll("caseFileId", updateEvent.getCasefileId());
		   while(assignmentIter.hasNext()){
			   Assignment assignment = (Assignment)assignmentIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   assignment.setCaseFileId(updateEvent.getMergeToCasefileId());
			   }
		   }
		   // updating unique assignment record based on updating assignment date
	   }else if(updateEvent.getAssignmentId() != null && !updateEvent.getAssignmentId().equalsIgnoreCase("")){
		   Assignment assignment = Assignment.find(updateEvent.getAssignmentId());
		   if(updateEvent.getAssignmentDate() != null ){
			   assignment.setAssignmentAddDate(updateEvent.getAssignmentDate());
			   assignment.setAssignmentType(updateEvent.getReferralAssmentType());
			   assignment.setSeqNum(updateEvent.getReferralSeqNum());
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
