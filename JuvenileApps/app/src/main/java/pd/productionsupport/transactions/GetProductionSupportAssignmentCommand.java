package pd.productionsupport.transactions;

import java.util.Date;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.productionsupport.GetProductionSupportActivityEvent;
import messaging.productionsupport.GetProductionSupportAssignmentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.casefile.Activity;

public class GetProductionSupportAssignmentCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportAssignmentCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportAssignmentEvent getAssignmentEvent = (GetProductionSupportAssignmentEvent) event;
		Assignment assignment = Assignment.find(getAssignmentEvent.getAssignmentId());
			
		AssignmentResponseEvent assignmentResponseEvent = new AssignmentResponseEvent();
		
		if(assignment != null){
			assignmentResponseEvent.setAssignmentId(assignment.getOID());
			assignmentResponseEvent.setReferralNum(assignment.getReferralNumber());
			assignmentResponseEvent.setAssignmentDate(assignment.getAssignmentAddDate());
			assignmentResponseEvent.setJpoUserId(assignment.getAssignByUserId());
			assignmentResponseEvent.setServiceUnitId(assignment.getServiceUnitId());
			assignmentResponseEvent.setAssessmentLevelId(assignment.getAssignmentLevelId());
			assignmentResponseEvent.setCaseFileId(assignment.getCaseFileId());
			assignmentResponseEvent.setAssignmentType(assignment.getAssignmentType());
			assignmentResponseEvent.setRefSeqNum(assignment.getSeqNum());
			//production support 
			if(assignment.getCreateUserID() != null){
				assignmentResponseEvent.setCreateUserID(assignment.getCreateUserID());
			}
			if(assignment.getCreateTimestamp() != null){
				assignmentResponseEvent.setCreateDate(new Date(assignment.getCreateTimestamp().getTime()));
			}
			if(assignment.getUpdateUserID() != null){
				assignmentResponseEvent.setUpdateUser(assignment.getUpdateUserID());
			}
			if(assignment.getUpdateTimestamp() != null){
				assignmentResponseEvent.setUpdateDate(new Date(assignment.getUpdateTimestamp().getTime()));
			}
			if(assignment.getCreateJIMS2UserID() != null){
				assignmentResponseEvent.setCreateJIMS2UserID(assignment.getCreateJIMS2UserID());
			}
			if(assignment.getUpdateJIMS2UserID() != null){
				assignmentResponseEvent.setUpdateJIMS2UserID(assignment.getUpdateJIMS2UserID());
			}
			
		dispatch.postEvent(assignmentResponseEvent);			
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
