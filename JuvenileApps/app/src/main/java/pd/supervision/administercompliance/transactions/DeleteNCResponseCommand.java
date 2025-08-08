//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\DeleteNCResponseCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.DeleteNCResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.ViolationReportConstants;
import pd.common.CommandUtil;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.ViolationReport;
/*
 * @author mchowdhury
 */

public class DeleteNCResponseCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 47DA96750198
    */
   public DeleteNCResponseCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF79013A
    */
   public void execute(IEvent event) 
   {
   	   DeleteNCResponseEvent dEvent = (DeleteNCResponseEvent) event;
   	   String ncResponseId = dEvent.getNcResponseId();
   	   
	   DAOHandler handler = getHandler(ViolationReportConstants.LAWVIOLATION_DAO_LOCATOR);
  	   handler.remove(ncResponseId);
 	   handler = getHandler(ViolationReportConstants.COMMUNITY_SERVICE_DAO_LOCATOR);
 	   handler.remove(ncResponseId);	   
   	   handler = getHandler(ViolationReportConstants.REPORTING_DAO_LOCATOR);
   	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.POSITIVEUA_DAO_LOCATOR);
	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_DAO_LOCATOR);
	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.REASON_FOR_TRANSFER_DAO_LOCATOR);
	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.DELINQUENT_FEE_DAO_LOCATOR);
	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.EMPLOYMENT_DAO_LOCATOR);
	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.RECOMMENDATION_DAO_LOCATOR);
	   handler.remove(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.TREATMENT_DAO_LOCATOR);
	   handler.remove(ncResponseId);	
	   handler = getHandler(ViolationReportConstants.MENTAL_HEALTH_DAO_LOCATOR);
	   handler.remove(ncResponseId);
	   
	   Comment.delete(ncResponseId);
	   ViolationReport.delete(ncResponseId);
   }
   
   /**
    * @param event
    * @roseuid 47D5AF790148
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF79014A
    */
   public void onUnregister(IEvent event) 
   {
    
   }

   /**
    * @param anObject
    * @roseuid 47D9A5E4026D
    */
   public void update(Object anObject) 
   {
    
   } 
}
