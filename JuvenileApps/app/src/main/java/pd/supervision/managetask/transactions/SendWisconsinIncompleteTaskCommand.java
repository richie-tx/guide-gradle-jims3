// Source file:

package pd.supervision.managetask.transactions;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.managetask.PDTaskHelper;

import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.SendWisconsinIncompleteTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SendWisconsinIncompleteTaskCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B601D0
	 */
	public SendWisconsinIncompleteTaskCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0009
	 */
	public void execute(IEvent event) throws Exception{
		SendWisconsinIncompleteTaskEvent wisconsinTaskEvent = ( SendWisconsinIncompleteTaskEvent ) event;
		
		if (wisconsinTaskEvent.getAssessmentId() != null && !wisconsinTaskEvent.getAssessmentId().equals(PDConstants.BLANK)){
			Wisconsin wisconsin = Wisconsin.findByOid( wisconsinTaskEvent.getAssessmentId() );
			if( wisconsin != null ) {
				Assessment assessment = Assessment.find( wisconsin.getAssessmentId() ); 
				if(assessment != null && assessment.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENT_INCOMPLETE))
				{
					//due date
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, 1);
			
					// create cs task with 
					CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
					createTaskEvent.setTopic(" CS.INCOMPLETE.WISCONSIN ");
					createTaskEvent.setTaskSubject( wisconsinTaskEvent.getTaskSubject() );
			
					createTaskEvent.setDueDate( calendar.getTime() );
					
					StringBuffer taskText = new StringBuffer();
					taskText.append( "Wisconsin is in an incomplete status and needs to be completed for SPN " );
					taskText.append(wisconsinTaskEvent.getDefendantId());
					
					createTaskEvent.setSuperviseeName( wisconsinTaskEvent.getSuperviseeName() );
					createTaskEvent.setDefendantId( wisconsinTaskEvent.getDefendantId() );
					createTaskEvent.setStaffPositionId( wisconsinTaskEvent.getStaffPositionId() );
					createTaskEvent.setSupervisionPlanId( wisconsinTaskEvent.getAssessmentId() );
					createTaskEvent.setTastText( taskText.toString() );
					createTaskEvent.setScenario( CSCAssessmentConstants.ASSESSMENT_COMPLETE_WISCONSIN_PAGEFLOW  );
			
					PDTaskHelper.createCSTask( createTaskEvent );
				}
			}
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
