// Source file:

package pd.supervision.managetask.transactions;

import java.util.Calendar;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.managetask.PDTaskHelper;

import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.SendSCSInventoryTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SendSCSInventoryTaskCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B601D0
	 */
	public SendSCSInventoryTaskCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0009
	 */
	public void execute(IEvent event) throws Exception{
		SendSCSInventoryTaskEvent SCSInventoryTaskEvent = ( SendSCSInventoryTaskEvent ) event;
	 Assessment SCSInventory = Assessment.find( SCSInventoryTaskEvent.getAssessmentId() ); 
	 if(SCSInventory != null && SCSInventory.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENT_INCOMPLETE))
	  {
		
		//due date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);

		// create cs task with 
		CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();
		createTaskEvent.setTopic(" CS.INCOMPLETE.SCSInventory ");
		createTaskEvent.setTaskSubject( SCSInventoryTaskEvent.getTaskSubject() );

		createTaskEvent.setDueDate( calendar.getTime() );
		
		StringBuffer taskText = new StringBuffer();
		taskText.append( "SCS Inventory is in an incomplete status and needs to be completed for SPN " );
		taskText.append(SCSInventoryTaskEvent.getDefendantId());
		
		createTaskEvent.setSuperviseeName( SCSInventoryTaskEvent.getSuperviseeName() );
		createTaskEvent.setDefendantId( SCSInventoryTaskEvent.getDefendantId() );
		createTaskEvent.setStaffPositionId( SCSInventoryTaskEvent.getStaffPositionId() );
		createTaskEvent.setSupervisionPlanId( SCSInventoryTaskEvent.getAssessmentId() );
		createTaskEvent.setTastText( taskText.toString() );
		createTaskEvent.setScenario( CSCAssessmentConstants.ASSESSMENT_COMPLETE_SCS_INVENTORY_PAGEFLOW  );

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
