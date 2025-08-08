//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.posttrial.transactions;

import messaging.posttrial.DeleteCaseAssignmentDataControlEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercaseload.CaseAssignmentHistory;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteCaseAssignmentDataControlCommand implements ICommand 
{   
   /**
    * @roseuid 473B887E0371
    */
   public DeleteCaseAssignmentDataControlCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
       DeleteCaseAssignmentDataControlEvent gEvent = (DeleteCaseAssignmentDataControlEvent) event;
       CaseAssignmentHistory ch = CaseAssignmentHistory.find(gEvent.getCaseAssignmentHistId());
	   if(ch != null){
		   ch.delete();
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
