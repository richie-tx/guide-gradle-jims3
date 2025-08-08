/*
 * Created on May 5, 2008
 *
 */
package pd.task.helper;

import java.io.IOException;
import java.util.Calendar;

import naming.PDConstants;

import messaging.managetask.CreateCSTaskEvent;
import messaging.task.CreateTaskEvent;
import pd.security.PDSecurityHelper;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.manageworkgroup.WorkGroupHelper;
import pd.task.Task;
import pd.task.TaskDef;

/**
 * @author dgibler
 *
 */
public class TaskHelper {
	
	/**
	 * Used by JUVENILE
	 * @param event
	 * @return
	 * @throws IOException
	 */
    public static String createTask(CreateTaskEvent event) throws IOException {

        TaskDef taskDef = TaskDef.find(event.getTaskTopic());
        String taskId = null;
		if (taskDef == null)
		{
			TaskDef.throwTaskDefinitionNotFound(event.getTaskTopic());
		}
		else if (event.getTaskId() == null || event.getTaskId().equals(""))
		{
               Task taskNew = taskDef.createTask(event);
                if(taskNew!= null){
                	taskId = taskNew.getOID();
                }
		}
		return taskId;
    }

	private static final String STATUS_S = "S";
	private static final String SUBJECT = "Exception to investigate";
	private static final String SPN = "SPN=";
	private static final String CASE = "CASE=";
	private static final String BLANK = " ";
	public static final String CSTASK_TOPIC_NOTIFY_EXCEPTION= "CS.NOTIFY.JIMS2.EXCEPTION";
	
	public static void createCsTaskToJIMSWorkgroup(String useCase, String spn, String caseId, Exception e){

		WorkGroup wg = WorkGroupHelper.fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), "SA", "JIMS WORKGROUP");

	    if(wg != null){
	    	String taskOwnerId = wg.getOID();
		    CreateCSTaskEvent createTask = new CreateCSTaskEvent();
		    //Set due date to 5 days from current date.
		    Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			createTask.setDueDate(cal.getTime());
			createTask.setStatusId( STATUS_S);
			createTask.setTaskSubject(SUBJECT);
			
			StringBuffer padSpn = null;
			padSpn = new StringBuffer( spn );
		    if ( padSpn.length() < 8 ){
		    	while ( padSpn.length() < 8 ){
		    		padSpn.insert( 0, "0");
		    	}
		    }
		    
			StringBuffer sb = new StringBuffer(useCase);
			if (spn != null && !spn.equals(PDConstants.BLANK)){
				sb.append(BLANK);
				sb.append(SPN);
				sb.append(spn);
			}
			if (caseId != null && !caseId.equals(PDConstants.BLANK)){
				sb.append(BLANK);
				sb.append(CASE);
				sb.append(caseId);
			}
			sb.append(BLANK);
			sb.append(e.getCause());
			createTask.setTastText( sb.toString());
			createTask.setTopic(CSTASK_TOPIC_NOTIFY_EXCEPTION);
			createTask.setWorkGroupId( taskOwnerId );
			createTask.setDefendantId( padSpn.toString() );
			createTask.setSubject2( SUBJECT );
			
			PDTaskHelper.createCSTask( createTask );
 	    }
	}
}
