//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\GetNCResponseComponentsCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.GetNCResponseComponentsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import naming.ViolationReportConstants;
import pd.common.CommandUtil;
import pd.common.DAOHandler;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetNCResponseComponentsCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 47DEAA1F0238
    */
   public GetNCResponseComponentsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47DEA68902FF
    */
   public void execute(IEvent event) 
   {
   	   GetNCResponseComponentsEvent ncrcEvent = (GetNCResponseComponentsEvent) event;
   	   String requestType = ncrcEvent.getRequestType();
   	   
 	   DAOHandler handler =  null;   	   
   	   if(ViolationReportConstants.REQUEST_LAW_VIOLATION.equalsIgnoreCase(requestType)){
           handler = getHandler(ViolationReportConstants.LAWVIOLATION_DAO_LOCATOR);
  	   }else if(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE.equalsIgnoreCase(requestType)){
  	       handler = getHandler(ViolationReportConstants.COMMUNITY_SERVICE_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_REPORTING.equalsIgnoreCase(requestType)){
   	   	   handler = getHandler(ViolationReportConstants.REPORTING_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_POSITIVE_UA.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.POSITIVEUA_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.REASON_FOR_TRANSFER_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_DELINQUENT_FEE.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.DELINQUENT_FEE_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_EMPLOYMENT.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.EMPLOYMENT_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_RECOMMENDATION.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.RECOMMENDATION_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_TREATMENT.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.TREATMENT_DAO_LOCATOR);
   	   }
   	   
  	   if(handler == null){
  	   	   IEvent re = new ReturnException("Appropriate DAO class is absent");
  	   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		   dispatch.postEvent(re);
  	   }
       handler.get(ncrcEvent);
   }
   
   /**
    * @param event
    * @roseuid 47DEA689030E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47DEA6890310
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47DEA689031D
    */
   public void update(Object anObject) 
   {
    
   }  
}
