//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\RefreshNCResponseComponentsCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import naming.ViolationReportConstants;
import pd.common.CommandUtil;
import pd.common.DAOHandler;

/*
 * @author mchowdhury
 */
public class RefreshNCResponseComponentsCommand  extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 47DEAA6A017D
    */
   public RefreshNCResponseComponentsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47DEA68A02A1
    */
   public void execute(IEvent event) 
   {
   	   RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) event;
   	   String requestType = refEvent.getRequestType();
   	   
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
   	   }else if(ViolationReportConstants.REQUEST_DELINQUENT_FEE.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.DELINQUENT_FEE_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_EMPLOYMENT.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.EMPLOYMENT_DAO_LOCATOR);
   	   }else if(ViolationReportConstants.REQUEST_TREATMENT.equalsIgnoreCase(requestType)){
	   	   handler = getHandler(ViolationReportConstants.TREATMENT_DAO_LOCATOR);
   	   }
   	   
  	   if(handler == null){
  	   	   IEvent re = new ReturnException("Appropriate DAO class is absent");
  	   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		   dispatch.postEvent(re);
  	   }  	   
  	   handler.refresh(refEvent);
   }
   
   /**
    * @param event
    * @roseuid 47DEA68A02A3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47DEA68A02AF
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47DEA68A02B1
    */
   public void update(Object anObject) 
   {
    
   } 
}
