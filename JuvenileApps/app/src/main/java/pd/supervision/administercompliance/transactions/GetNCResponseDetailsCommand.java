//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\GetNCResponseDetailsCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.GetNCResponseDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.ViolationReportConstants;
import pd.common.CommandUtil;
import pd.common.DAOContextFactory;
import pd.common.DAOHandler;
import pd.exception.ReflectionException;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.ViolationReport;

/*
 * @author mchowdhury
 */
public class GetNCResponseDetailsCommand  extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 47DA96930263
    */
   public GetNCResponseDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF790244
    */
   public void execute(IEvent event) 
   {
	   GetNCResponseDetailsEvent dEvent = (GetNCResponseDetailsEvent) event;
   	   String ncResponseId = dEvent.getNcResponseId();
   	   if(ncResponseId == null || ncResponseId.equals("")){
   		   return;
   	   }
   	   
       ViolationReport vr = ViolationReport.find(ncResponseId);
	   if(vr == null){
		   return;
	   }
   	   
	   DAOHandler handler = getHandler(ViolationReportConstants.LAWVIOLATION_DAO_LOCATOR);
  	   handler.execute(ncResponseId);
 	   handler = getHandler(ViolationReportConstants.COMMUNITY_SERVICE_DAO_LOCATOR);
 	   handler.execute(ncResponseId);   
   	   handler = getHandler(ViolationReportConstants.REPORTING_DAO_LOCATOR);
       handler.execute(ncResponseId); 
	   handler = getHandler(ViolationReportConstants.POSITIVEUA_DAO_LOCATOR);
	   handler.execute(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_DAO_LOCATOR);
	   handler.execute(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.REASON_FOR_TRANSFER_DAO_LOCATOR);
	   handler.execute(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.DELINQUENT_FEE_DAO_LOCATOR);
	   handler.execute(ncResponseId); 
	   handler = getHandler(ViolationReportConstants.EMPLOYMENT_DAO_LOCATOR);
	   handler.execute(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.RECOMMENDATION_DAO_LOCATOR);
	   handler.execute(ncResponseId);	 
	   handler = getHandler(ViolationReportConstants.TREATMENT_DAO_LOCATOR);
	   handler.execute(ncResponseId); 
	   
	   Comment.post(ncResponseId);
	   vr.post();
   }
   
   /**
    * @param event
    * @roseuid 47D5AF790261
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF790263
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47D5AF790265
    */
   public void update(Object anObject) 
   {
    
   }   
}
