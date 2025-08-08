package pd.supervision.managetask;

import java.io.IOException;
import java.util.Iterator;

import naming.PDConstants;

import pd.task.Task;
import pd.task.TaskDef;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.GetCSTasksEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class PDTaskHelper {
	
	public static String SUBMITTED = "S";
	
	public static CSTask createCSTask(CreateCSTaskEvent event){
		CSTask cstask = new CSTask();
		
		cstask.setCourtId(event.getCourtId());
		cstask.setCourtId2(event.getCourtId2());
		cstask.setDefendantId(event.getDefendantId());
		cstask.setSubject2(event.getSubject2());
		cstask.setCaseAssignIds( event.getCaseAssignId() );
		cstask.setCriminalCaseId( event.getCriminalCaseId() );
		cstask.setSupervisionOrderIds( event.getSupervisionOrderId() );

		if ( !"".equals( event.getStaffPositionId()) && event.getStaffPositionId() != null ){
			
			cstask.setStaffPositionId( Integer.parseInt( event.getStaffPositionId()));
		}
		
		if ( !"".equals( event.getWorkGroupId()) && event.getWorkGroupId() != null ){
			
			cstask.setWorkGroupId( Integer.parseInt( event.getWorkGroupId() ));
		}
		
		if ( !"".equals( event.getNcResponseId()) && event.getNcResponseId() != null ){
			
			cstask.setNcResponseId( Integer.parseInt( event.getNcResponseId() ));
		}
		cstask.setDueDate( event.getDueDate() );
		cstask.setScenario( event.getScenario() );
		cstask.setStatusId( SUBMITTED );
		cstask.setSuperviseeName( event.getSuperviseeName() );
		cstask.setTaskSubject( event.getTaskSubject() );
		cstask.setTaskText( event.getTastText() );
		cstask.setTopic( event.getTopic() );
		cstask.setSupervisionPlanId( event.getSupervisionPlanId() );
		
		 IHome home = new Home();
	     home.bind( cstask );
		
	     return cstask;
		
	}
	
	public static Task createTask(CreateTaskEvent event) throws IOException {
		TaskDef taskDef = TaskDef.find(event.getTaskTopic());
		Task taskNew = null;
		if (taskDef == null)
		{
			TaskDef.throwTaskDefinitionNotFound(event.getTaskTopic());
		}
		else if (event.getTaskId() == null || event.getTaskId().equals(PDConstants.BLANK))
		{
			taskNew = taskDef.createTask(event);
		}
		return taskNew;
	}
	
	/**
	 * 
	 * @param spn
	 * @param crimCase
	 */
	public void closeAllCSTasks ( String spn, String crimCase ){
		
		CSTask cstask = null;
		GetCSTasksEvent request = new GetCSTasksEvent();
		
		request.setDefendantId( spn );
		request.setCriminalCaseId( crimCase );
		
		Iterator i = new Home().findAll( request, CSTask.class );
		while (i.hasNext()) {
			cstask = (CSTask) i.next();			
			// Close tasks here...
			if( cstask != null ){
				
				cstask.setStatusId( "C" );
			}
		}
		request = null;
		cstask = null;
	}
}
