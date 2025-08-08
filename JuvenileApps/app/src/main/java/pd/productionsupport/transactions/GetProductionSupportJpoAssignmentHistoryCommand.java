package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.productionsupport.GetProductionSupportJpoAssignmentHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JPOAssignmentHistory;

public class GetProductionSupportJpoAssignmentHistoryCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportJpoAssignmentHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportJpoAssignmentHistoryEvent jpoAssignmentHistoryEvent = (GetProductionSupportJpoAssignmentHistoryEvent) event;
		Iterator assignmentHistoryIter = JPOAssignmentHistory.findAll("casefileId",jpoAssignmentHistoryEvent.getCasefileId());
		
		while(assignmentHistoryIter != null && assignmentHistoryIter.hasNext()){
			JPOAssignmentHistory jpoAssessmentHistory = (JPOAssignmentHistory)assignmentHistoryIter.next();		
			JPOAssignmentHistoryResponseEvent responseEvent = new JPOAssignmentHistoryResponseEvent();
			
			responseEvent.setJpoAssignmentHistoryId(jpoAssessmentHistory.getOID());
			responseEvent.setJpoAssignmentDate(jpoAssessmentHistory.getJpoAssignmentDate());
			responseEvent.setOfficerProfileId(jpoAssessmentHistory.getOfficerProfileId());
			responseEvent.setCasefileId(jpoAssessmentHistory.getCasefileId());
			
			//production support 
			if(jpoAssessmentHistory.getCreateUserID() != null){
				responseEvent.setCreateUserID(jpoAssessmentHistory.getCreateUserID());
			}
			if(jpoAssessmentHistory.getCreateTimestamp() != null){
				responseEvent.setCreateDate(new Date(jpoAssessmentHistory.getCreateTimestamp().getTime()));
			}
			if(jpoAssessmentHistory.getUpdateUserID() != null){
				responseEvent.setUpdateUser(jpoAssessmentHistory.getUpdateUserID());
			}
			if(jpoAssessmentHistory.getUpdateTimestamp() != null){
				responseEvent.setUpdateDate(new Date(jpoAssessmentHistory.getUpdateTimestamp().getTime()));
			}
			if(jpoAssessmentHistory.getCreateJIMS2UserID() != null){
				responseEvent.setCreateJIMS2UserID(jpoAssessmentHistory.getCreateJIMS2UserID());
			}
			if(jpoAssessmentHistory.getUpdateJIMS2UserID() != null){
				responseEvent.setUpdateJIMS2UserID(jpoAssessmentHistory.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(responseEvent);
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
