//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\UpdateNCResponseStatusCommand.java

package pd.supervision.administercompliance;

import java.sql.Timestamp;
import java.util.Calendar;

import messaging.administercompliance.UpdateNCResponseStatusEvent;
import messaging.managetask.CreateCSTaskEvent;
import mojo.km.utilities.DateUtil;
import naming.ViolationReportConstants;
import pd.security.PDSecurityHelper;
import pd.supervision.managetask.CSTask;
import pd.supervision.managetask.PDTaskHelper;

/* 
 * @ author mchowdhury 
 */

public class FinalizeViolationReportWorkFlow
{
   
   /**
    * @roseuid 47DA96A803DA
    */
   public FinalizeViolationReportWorkFlow() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D99E4E0098
    */
   public void execute(UpdateNCResponseStatusEvent reqEvent) 
   {
	    ViolationReport vr = ViolationReport.find( reqEvent.getNcResponseId() );
	    if( vr != null ){
		    vr.setStatusId(reqEvent.getStatusId());
		   
		    String currentUser = PDSecurityHelper.getLogonId();
		    Timestamp currentDate = new Timestamp(DateUtil.getCurrentDate().getTime());

		    if(ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(reqEvent.getStatusId())){
		    	vr.setSubMgrAppDate(currentDate);
		    }if(ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase(reqEvent.getStatusId())){
		    	vr.setManagerApprovalUser(currentUser);
		    	vr.setManagerApprovalDate(currentDate);
		    }else if(ViolationReportConstants.STATUS_SUBMISSION_APPROVED.equalsIgnoreCase(reqEvent.getStatusId())){
		    	vr.setSubmissionApprovedUser(currentUser);
		    	vr.setSubmissionApprovedDate(currentDate);
		    }else if(ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(reqEvent.getStatusId())){
		    	vr.setFiledUser(currentUser);
		    	vr.setFiledDate(currentDate);
		    }
		    vr.setStatusChangedDate(currentDate);
		    
	    	String taskId = createCSTask( reqEvent );
	    	vr.setTaskId( Integer.parseInt( taskId ));
		    
	    	// Close previous task
		    closePreviousTask( reqEvent.getNtTaskId() );
	    }
	   
    	String logonId = reqEvent.getLogonId();
    	boolean isChangeRequest = true;
    	if(logonId == null || logonId.equals("")){
    		logonId = PDSecurityHelper.getLogonId();
    		isChangeRequest = false;
    	}


   }
   
     
   /**
    * 
    * @param taskId
    */
   private void closePreviousTask( String taskId ) {

	   if ( taskId != null ){
		   CSTask task = new CSTask();
		   CSTask cstask = task.find( taskId );
			if(cstask!=null){
				cstask.setStatusId("C");
			}
			cstask = null;
			task = null;
	   }
	}
   
   private String createCSTask( UpdateNCResponseStatusEvent createTaskEvent ){
	   
	 //due date - should be next day unless current day is Friday then next day is Monday
	    Calendar calendar = Calendar.getInstance();
	    String dstr = calendar.getTime().toString();
	    int offsetDays = 1;
	    if (dstr.indexOf("Fri") > -1){
	    	offsetDays = 3;
	    } else if (dstr.indexOf("Sat") > -1){
	    	offsetDays = 2;
	    }
		calendar.add(Calendar.DATE, offsetDays);
		
		// on task for CLO's in VR, the court usually contains a prefix of CC or CR which needs to be removed in task
		String crtNum = createTaskEvent.getCourtNumber().trim();
		if (crtNum.indexOf(" ") > -1){
			crtNum = crtNum.substring(crtNum.length() - 3, crtNum.length());
			createTaskEvent.setCourtNumber(crtNum);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append( createTaskEvent.getCdi() ).append( createTaskEvent.getCaseNumber() );

		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		
		createCSTaskEvent.setCourtId( createTaskEvent.getCourtNumber() );
		createCSTaskEvent.setDefendantId( createTaskEvent.getSpn() );
		createCSTaskEvent.setStaffPositionId( createTaskEvent.getStaffPositionId() );
		createCSTaskEvent.setDueDate( calendar.getTime() );
		
		createCSTaskEvent.setCriminalCaseId( sb.toString() );
		createCSTaskEvent.setScenario( ViolationReportConstants.PAGEFLOW_SCENERIO );
		createCSTaskEvent.setSuperviseeName( createTaskEvent.getSuperviseeName() );
		createCSTaskEvent.setTaskSubject( createTaskEvent.getSubject() );
		createCSTaskEvent.setTopic( createTaskEvent.getTopic() );
		createCSTaskEvent.setNcResponseId( createTaskEvent.getNcResponseId() );
		
		createCSTaskEvent.setTastText( createTaskEvent.getText() );
		
		CSTask cstask = PDTaskHelper.createCSTask(createCSTaskEvent);
	

		return cstask.getOID();
	   
   }
}