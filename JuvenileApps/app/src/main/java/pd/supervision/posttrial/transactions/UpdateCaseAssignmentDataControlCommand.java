//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.posttrial.transactions;

import messaging.posttrial.UpdateCaseAssignmentDataControlEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentHistory;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateCaseAssignmentDataControlCommand implements ICommand 
{   
   /**
    * @roseuid 473B887E0371
    */
   public UpdateCaseAssignmentDataControlCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
	   UpdateCaseAssignmentDataControlEvent gEvent = (UpdateCaseAssignmentDataControlEvent) event;
	   if(gEvent.getAssignmentHistId() != null && !gEvent.getAssignmentHistId().equals("")){
		   CaseAssignmentHistory ch = CaseAssignmentHistory.find(gEvent.getAssignmentHistId());
		   if(ch != null){
			   if(gEvent.getPositionAssignmentDate() != null && !gEvent.getPositionAssignmentDate().equals("")){
				   ch.setOfficerAssignDate(gEvent.getPositionAssignmentDate());
			   }
			   
			   if(gEvent.getStaffPositionId() != null && !gEvent.getStaffPositionId().equals("")){
				   ch.setAssignedStaffPositionId(gEvent.getStaffPositionId());
			   }
			   
			   if(gEvent.getOrganizationId() != null && !gEvent.getOrganizationId().equals("")){
				   ch.setProgramUnitId(gEvent.getOrganizationId());
			   }
			   
			   if(gEvent.getProgramUnitAssignmentDate() != null && !gEvent.getProgramUnitAssignmentDate().equals("")){
				   ch.setProgramUnitAssignDate(gEvent.getProgramUnitAssignmentDate());
			   }
		   }
	   }		   
       else{
		   CaseAssignment ca = CaseAssignment.find(gEvent.getAssignmentId());
		   if(ca != null){
			   if(gEvent.getPositionAssignmentDate() != null && !gEvent.getPositionAssignmentDate().equals("")){
				   ca.setOfficerAssignDate(gEvent.getPositionAssignmentDate());
			   }
			   
			   if(gEvent.getStaffPositionId() != null && !gEvent.getStaffPositionId().equals("")){
				   ca.setAssignedStaffPositionId(gEvent.getStaffPositionId());
			   }
			   
			   if(gEvent.getOrganizationId() != null && !gEvent.getOrganizationId().equals("")){
				   ca.setProgramUnitId(gEvent.getOrganizationId());
			   }
			   
			   if(gEvent.getProgramUnitAssignmentDate() != null && !gEvent.getProgramUnitAssignmentDate().equals("")){
				   ca.setProgramUnitAssignDate(gEvent.getProgramUnitAssignmentDate());
			   }
			   ca.bind();
			   
			   CaseAssignmentHistory ch = new CaseAssignmentHistory();	
			   ch.update(ca);
		   }		   
       }
   }
  
   /**
    * @param event
    * @roseuid 473B75560240
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560242
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560244
    */
   public void update(Object anObject) 
   {
    
   }
}
