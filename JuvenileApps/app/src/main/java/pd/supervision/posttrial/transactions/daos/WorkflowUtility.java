//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.posttrial.transactions.daos;

import org.apache.commons.lang.StringUtils;

import pd.supervision.managetask.PDTaskHelper;
import messaging.managetask.CreateCSTaskEvent;
import messaging.posttrial.CreateWorkflowTaskEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;
import naming.UIConstants;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WorkflowUtility{
   
   /**
    * @roseuid 473B887E0371
    */
   public WorkflowUtility() 
   {

   }
   
  /**
    * 
    * @param cEvent
    * @return
    */
   protected String getOwnerId(CreateWorkflowTaskEvent cEvent){
	   
	   StringBuffer toBuffer = new StringBuffer();
	    if(StringUtils.isNotEmpty( cEvent.getPositionId())){
	    	
	       toBuffer.append(CaseloadConstants.POSITION_OID_PREPEND_STRING);
		   toBuffer.append(cEvent.getPositionId());
	    }else{
		   toBuffer.append(CaseloadConstants.WORKGROUP_OID_PREPEND_STRING);
		   toBuffer.append(cEvent.getWorkgroupId());
	    }
	    return toBuffer.toString();
   }
   
   protected CreateCSTaskEvent prepareTaskEvent(CreateWorkflowTaskEvent cEvent){
	   
	   CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
	   createTaskEvent.setTaskSubject(cEvent.getSubject());
	   
	   if(StringUtils.isNotEmpty( cEvent.getPositionId())){
	    	
	       createTaskEvent.setStaffPositionId( cEvent.getPositionId() );

	    }else{

		   createTaskEvent.setWorkGroupId( cEvent.getWorkgroupId() );
	    }
	   createTaskEvent.setStatusId( UIConstants.OPEN_STATUS_ID );
	   createTaskEvent.setTopic(cEvent.getNextActionId());
	   createTaskEvent.setDueDate(DateUtil.stringToDate(cEvent.getDueDate(), DateUtil.DATE_FMT_1));
					
	   createTaskEvent.setTastText( cEvent.getText() );
	   createTaskEvent.setCriminalCaseId( cEvent.getCaseNumber() );
	   createTaskEvent.setDefendantId( cEvent.getSpn() );

	   return createTaskEvent;
   }

   public void createTask(CreateTaskEvent createTaskEvent, CreateWorkflowTaskEvent event, String courtId) throws Exception {
	   try {
		
			
			CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
			
			createCSTaskEvent.setCourtId( courtId );
			createCSTaskEvent.setDefendantId( event.getSpn() );
			createCSTaskEvent.setStaffPositionId( event.getPositionId() );
			createCSTaskEvent.setWorkGroupId( event.getWorkgroupId() );
			createCSTaskEvent.setDueDate( DateUtil.stringToDate(event.getDueDate(), DateUtil.DATE_FMT_1));
			createCSTaskEvent.setCriminalCaseId( event.getCaseNumber() );
			createCSTaskEvent.setScenario( CaseloadConstants.ASSIGN_OFFICER_PAGEFLOW );
			createCSTaskEvent.setSuperviseeName( event.getSuperviseeName() );
			createCSTaskEvent.setTaskSubject( event.getSubject() );
			createCSTaskEvent.setTopic( event.getNextActionId() );
			
			createCSTaskEvent.setTastText( event.getText() );

		   PDTaskHelper.createCSTask(createCSTaskEvent);
	} catch (Exception e) {
		throw e;
	}
   }
}
