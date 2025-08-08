//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\UpdateNCResponseStatusCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.UpdateNCResponseStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercompliance.FinalizeCaseSummaryWorkFlow;
import pd.supervision.administercompliance.FinalizeViolationReportWorkFlow;

/* 
 * @ author mchowdhury 
 */

public class UpdateNCResponseStatusCommand implements ICommand 
{
   
   /**
    * @roseuid 47DA96A803DA
    */
   public UpdateNCResponseStatusCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D99E4E0098
    */
   public void execute(IEvent event) 
   {
	    UpdateNCResponseStatusEvent reqEvent = (UpdateNCResponseStatusEvent) event;
	    String reportType = reqEvent.getReportType();
	    if(reportType != null && naming.ViolationReportConstants.REPORTTYPE_CASESUMMARY.equals(reportType)){
	    	FinalizeCaseSummaryWorkFlow cswf = new FinalizeCaseSummaryWorkFlow();
	    	cswf.execute(reqEvent);
	    }else{
	    	FinalizeViolationReportWorkFlow vrwf = new FinalizeViolationReportWorkFlow();
	    	vrwf.execute(reqEvent);	    	
	    }
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
