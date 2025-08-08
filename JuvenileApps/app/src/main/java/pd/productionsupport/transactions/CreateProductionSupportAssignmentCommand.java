package pd.productionsupport.transactions;

import messaging.productionsupport.CreateProductionSupportAssignmentEvent;
import messaging.productionsupport.SaveProductionSupportMaysiDetailEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;

public class CreateProductionSupportAssignmentCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public CreateProductionSupportAssignmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		CreateProductionSupportAssignmentEvent assignmentEvent = (CreateProductionSupportAssignmentEvent) event;
		Assignment newAassignment = new Assignment();
		newAassignment.setServiceUnitId(assignmentEvent.getServiceUnitId());
		newAassignment.setAssignmentLevelId(assignmentEvent.getAssignmentLevelId());
		newAassignment.setCaseFileId(assignmentEvent.getCasefileId());
		newAassignment.setAssignmentAddDate(assignmentEvent.getAssignmentAddDate());
		newAassignment.setReferralNumber(assignmentEvent.getReferralNumber());
		newAassignment.setAssignByUserId(assignmentEvent.getAssignByuserId());
		newAassignment.setAssignmentType(assignmentEvent.getAssignmentType());
		newAassignment.setSeqNum(assignmentEvent.getRefSeqNum());

	}
	
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}
