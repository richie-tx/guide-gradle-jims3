//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\UpdateNCResponseStatusCommand.java

package pd.supervision.posttrial.transactions;

import messaging.posttrial.CreateWorkflowTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import naming.ViolationReportConstants;
import pd.common.CommandUtil;
import pd.common.DAOHandler;

/* 
 * @ author mchowdhury 
 */

public class CreateWorkflowTaskCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 47DA96A803DA
    */
   public CreateWorkflowTaskCommand () 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D99E4E0098
    */
   public void execute(IEvent event) 
   {
	   CreateWorkflowTaskEvent cEvent = (CreateWorkflowTaskEvent) event;
	   String nextActionParent = cEvent.getNextActionParent();  	   
 	   
	   DAOHandler handler =  null;   	   
   	   if(ViolationReportConstants.VIOLATION_REPORT.equalsIgnoreCase(nextActionParent) || ViolationReportConstants.CASE_SUMMARY.equalsIgnoreCase(nextActionParent)){
           handler = getHandler(ViolationReportConstants.WORKFLOW_VIOLATIONREPORT_CASESUMMARY_DAO_LOCATOR);
  	   }else{
	   	   handler = getHandler(ViolationReportConstants.WORKFLOW_ASSIGNMENT_REASSIGNMENT_DAO_LOCATOR);
   	   }
   	   
  	   if(handler == null){
  		 IEvent re = new ReturnException("Appropriate DAO class is absent");
         IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
         dispatch.postEvent(re);
  	   }  	   
  	   handler.execute(cEvent);
	}
 
   /**
    * @param event
    * @roseuid 47D99E4E00A7
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D99E4E00B7
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47D99E4E00C6
    */
   public void update(Object anObject) 
   {
    
   }
}
