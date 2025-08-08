// Source file:

package pd.supervision.managetask.transactions;

import java.util.Calendar;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.managetask.PDTaskHelper;

import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.SendForceFieldTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SendForceFieldTaskCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B601D0
	 */
	public SendForceFieldTaskCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0009
	 */
	public void execute(IEvent event) throws Exception{
		SendForceFieldTaskEvent forceFieldTaskEvent = ( SendForceFieldTaskEvent ) event;
		Assessment forceField = Assessment.find( forceFieldTaskEvent.getAssessmentId() ); 
		if(forceField != null && forceField.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENT_INCOMPLETE))
		{
			//due date
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 1);
	
			// create cs task with 
			CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
			createTaskEvent.setTopic(" CS.INCOMPLETE.FORCEFIELD ");
			createTaskEvent.setTaskSubject( forceFieldTaskEvent.getTaskSubject() );
	
			createTaskEvent.setDueDate( calendar.getTime() );
			
			StringBuffer taskText = new StringBuffer();
			taskText.append( "Force Field Assessment is in an incomplete status and needs to be completed for SPN " );
			taskText.append(forceFieldTaskEvent.getDefendantId());
			
			createTaskEvent.setSuperviseeName( forceFieldTaskEvent.getSuperviseeName() );
			createTaskEvent.setDefendantId( forceFieldTaskEvent.getDefendantId() );
			createTaskEvent.setStaffPositionId( forceFieldTaskEvent.getStaffPositionId() );
			createTaskEvent.setSupervisionPlanId( forceFieldTaskEvent.getAssessmentId() );
			createTaskEvent.setTastText( taskText.toString() );
			createTaskEvent.setScenario( CSCAssessmentConstants.ASSESSMENT_COMPLETE_FORCE_FIELD_PAGEFLOW  );
	
			PDTaskHelper.createCSTask( createTaskEvent );
		}			
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE000B
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0018
	 */
	public void onUnregister(IEvent event) {
	}

	/**
	 * @param anObject
	 * @roseuid 46C0C0DE001A
	 */
	public void update(Object anObject) {
	}
}
