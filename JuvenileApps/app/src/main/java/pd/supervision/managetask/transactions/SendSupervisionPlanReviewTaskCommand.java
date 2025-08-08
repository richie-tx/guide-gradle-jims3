// Source file:

package pd.supervision.managetask.transactions;

import java.util.Calendar;
import pd.supervision.administersupervisionplan.SupervisionPlan;
import pd.supervision.managetask.PDTaskHelper;

import naming.PDCodeTableConstants;
import naming.SupervisionPlanConstants;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.SendSupervisionPlanReviewTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SendSupervisionPlanReviewTaskCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B601D0
	 */
	public SendSupervisionPlanReviewTaskCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0009
	 */
	public void execute(IEvent event) throws Exception{
	 SendSupervisionPlanReviewTaskEvent reviewTaskEvent = (SendSupervisionPlanReviewTaskEvent) event;
	
	 SupervisionPlan supervisionPlan = SupervisionPlan.find(reviewTaskEvent.getSupervisionPlanId());	 
	 if(supervisionPlan != null && supervisionPlan.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_DRAFT))
	  {
		
		//due date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);

		// create cs task with 
		CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
		createTaskEvent.setTopic(" CS.DRAFT.SUPERVISIONPLAN ");
		createTaskEvent.setTaskSubject(reviewTaskEvent.getTaskSubject());

		createTaskEvent.setDueDate( calendar.getTime() );
		
		StringBuffer taskText = new StringBuffer();
		taskText.append("Supervision Plan in Draft Status older than 7 days requires your attention ");		
        taskText.append(", ");
		taskText.append(reviewTaskEvent.getDefendantId());
		
		createTaskEvent.setSuperviseeName( reviewTaskEvent.getSuperviseeName() );
		createTaskEvent.setDefendantId( reviewTaskEvent.getDefendantId() );
		createTaskEvent.setStaffPositionId( reviewTaskEvent.getStaffPositionId() );
		createTaskEvent.setSupervisionPlanId( reviewTaskEvent.getSupervisionPlanId() );
		createTaskEvent.setTastText( taskText.toString() );
		createTaskEvent.setScenario( SupervisionPlanConstants.UPDATE_SUPERVISION_PLAN_PAGEFLOW );

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
