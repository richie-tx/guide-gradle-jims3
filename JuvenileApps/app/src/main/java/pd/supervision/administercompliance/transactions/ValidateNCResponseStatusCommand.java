//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\UpdateNCResponseCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.Iterator;

import messaging.administercompliance.ValidateNCResponseStatusEvent;
import messaging.administercompliance.reply.ValidateNCResponseStatusResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.ViolationReportConstants;
import pd.common.CommandUtil;
import pd.supervision.administercompliance.ViolationReport;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateNCResponseStatusCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 47DA96A80002
    */
   public ValidateNCResponseStatusCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF780011
    */
   public void execute(IEvent event) 
   {
	   ValidateNCResponseStatusEvent vEvent = (ValidateNCResponseStatusEvent) event;
	   Iterator vrIter = ViolationReport.findAll(ViolationReportConstants.CASE_ID, vEvent.getCaseNumber());
	   while(vrIter.hasNext()){
		   ViolationReport vr = (ViolationReport) vrIter.next();
	       if(vEvent.getReportType().equals(vr.getReportType()) && !(ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(vr.getStatusId()))){
	    	   if(vEvent.getTopic().equalsIgnoreCase(ViolationReportConstants.CSTASK_TOPIC_NEW_VIOLATION_FOR_APPROVAL)){
	    	       if(ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(vr.getStatusId())){
	    	    	   ValidateNCResponseStatusResponseEvent resp = new ValidateNCResponseStatusResponseEvent();  
	    	    	   resp.setNcResponseId(vr.getOID());
	    	    	   break;
	    	       }
	    	   }else if(vEvent.getTopic().equalsIgnoreCase(ViolationReportConstants.CSTASK_TOPIC_VIOLATION_SUBMISSION_APPROVAL)){
	    	       if(ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase(vr.getStatusId())){
	    	    	   ValidateNCResponseStatusResponseEvent resp = new ValidateNCResponseStatusResponseEvent();  
	    	    	   resp.setNcResponseId(vr.getOID());
	    	    	   break;
	    	       }
	    	   }else if(vEvent.getTopic().equalsIgnoreCase(ViolationReportConstants.CSTASK_TOPIC_VIOLATION_SUBMISSION_REVIEW)){
	    	       if(ViolationReportConstants.STATUS_PENDING_SUBMISSION_APPROVAL.equalsIgnoreCase(vr.getStatusId())){
	    	    	   ValidateNCResponseStatusResponseEvent resp = new ValidateNCResponseStatusResponseEvent();  
	    	    	   resp.setNcResponseId(vr.getOID());
	    	    	   break;
	    	       }
	    	   }else if(vEvent.getTopic().equalsIgnoreCase(ViolationReportConstants.CSTASK_TOPIC_VIOLATION_SUBMISSION_REQUIRED)){
	    	       if(ViolationReportConstants.STATUS_SUBMISSION_APPROVED.equalsIgnoreCase(vr.getStatusId())){
	    	    	   ValidateNCResponseStatusResponseEvent resp = new ValidateNCResponseStatusResponseEvent();  
	    	    	   resp.setNcResponseId(vr.getOID());
	    	    	   break;
	    	       }
	    	   }else if(vEvent.getTopic().equalsIgnoreCase(ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_APPROVAL)){
	    	       if(ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(vr.getStatusId())){
	    	    	   ValidateNCResponseStatusResponseEvent resp = new ValidateNCResponseStatusResponseEvent();  
	    	    	   resp.setNcResponseId(vr.getOID());
	    	    	   break;
	    	       }
	    	   }else if(vEvent.getTopic().equalsIgnoreCase(ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_FILING_REQUIRED)){
	    	       if(ViolationReportConstants.STATUS_SUBMISSION_APPROVED.equalsIgnoreCase(vr.getStatusId())){
	    	    	   ValidateNCResponseStatusResponseEvent resp = new ValidateNCResponseStatusResponseEvent();  
	    	    	   resp.setNcResponseId(vr.getOID());
	    	    	   break;
	    	       }
	    	   }
	       }		       
       }
   }
   
   /**
    * @param event
    * @roseuid 47D5AF780013
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF78001F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47D5AF780021
    */
   public void update(Object anObject) 
   {
    
   }  
}
