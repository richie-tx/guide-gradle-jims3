// Source file:

package pd.supervision.managetask.transactions;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.SCSInterview;
import pd.supervision.managetask.PDTaskHelper;

import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.SendSCSInterviewTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SendSCSInterviewTaskCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B601D0
	 */
	public SendSCSInterviewTaskCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0009
	 */
	public void execute(IEvent event) throws Exception{
		SendSCSInterviewTaskEvent scsInterviewTaskEvent = ( SendSCSInterviewTaskEvent ) event;
		SCSInterview scsInterview = SCSInterview.findByOid( scsInterviewTaskEvent.getAssessmentId() ); 
		if( StringUtils.isNotEmpty( scsInterview.getAssessmentId() ) ) {
			Assessment assessment = Assessment.find( scsInterview.getAssessmentId() ); 
			if(assessment != null && assessment.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENT_INCOMPLETE)){
				//due date
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, 1);
		
				// create cs task with 
				CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
				createTaskEvent.setTopic(" CS.INCOMPLETE.SCSINTERVIEW ");
				createTaskEvent.setTaskSubject( scsInterviewTaskEvent.getTaskSubject() );
		
				createTaskEvent.setDueDate( calendar.getTime() );
				
				StringBuffer taskText = new StringBuffer();
				taskText.append( "SCS Interview is in an incomplete status and needs to be completed for SPN " );
				taskText.append(scsInterviewTaskEvent.getDefendantId());
				
				createTaskEvent.setSuperviseeName( scsInterviewTaskEvent.getSuperviseeName() );
				createTaskEvent.setDefendantId( scsInterviewTaskEvent.getDefendantId() );
				createTaskEvent.setStaffPositionId( scsInterviewTaskEvent.getStaffPositionId() );
				createTaskEvent.setSupervisionPlanId( scsInterviewTaskEvent.getAssessmentId() );
				createTaskEvent.setTastText( taskText.toString() );
				createTaskEvent.setScenario( CSCAssessmentConstants.ASSESSMENT_COMPLETE_SCS_INTERVIEW_PAGEFLOW );
				PDTaskHelper.createCSTask( createTaskEvent );
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
