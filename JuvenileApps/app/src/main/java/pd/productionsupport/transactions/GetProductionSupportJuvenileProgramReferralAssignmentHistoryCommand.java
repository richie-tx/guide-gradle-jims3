package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

public class GetProductionSupportJuvenileProgramReferralAssignmentHistoryCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportJuvenileProgramReferralAssignmentHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent getProgramReferralAssignmentHistoryEvent = 
				(GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent) event;
		// get by casefile
		if(getProgramReferralAssignmentHistoryEvent.getCasefileId() != null && getProgramReferralAssignmentHistoryEvent.getCasefileId().length() > 0 ){			
			Iterator getProgramReferralAssignmentHistoryIter = JuvenileProgramReferralAssignmentHistory.
				findAll("casefileId", getProgramReferralAssignmentHistoryEvent.getCasefileId());
			
			JuvenileProgramReferralAssignmentHistory programAssignmentHistory = null;
	
			while (getProgramReferralAssignmentHistoryIter.hasNext()) {
				programAssignmentHistory = (JuvenileProgramReferralAssignmentHistory) getProgramReferralAssignmentHistoryIter.next();
	
				if (programAssignmentHistory != null) {
					ProgramReferralAssignmentHistoryResponseEvent historyResponseEvent = programAssignmentHistory.getValueObject(true);
					historyResponseEvent.setProgramReferralAssignmentHistoryId(programAssignmentHistory.getOID());
					//production support 
					if(programAssignmentHistory.getCreateUserID() != null){
						historyResponseEvent.setCreateUserID(programAssignmentHistory.getCreateUserID());
					}
					if(programAssignmentHistory.getCreateTimestamp() != null){
						historyResponseEvent.setCreateDate(new Date(programAssignmentHistory.getCreateTimestamp().getTime()));
					}
					if(programAssignmentHistory.getUpdateUserID() != null){
						historyResponseEvent.setUpdateUser(programAssignmentHistory.getUpdateUserID());
					}
					if(programAssignmentHistory.getUpdateTimestamp() != null){
						historyResponseEvent.setUpdateDate(new Date(programAssignmentHistory.getUpdateTimestamp().getTime()));
					}
					if(programAssignmentHistory.getCreateJIMS2UserID() != null){
						historyResponseEvent.setCreateJIMS2UserID(programAssignmentHistory.getCreateJIMS2UserID());
					}
					if(programAssignmentHistory.getUpdateJIMS2UserID() != null){
						historyResponseEvent.setUpdateJIMS2UserID(programAssignmentHistory.getUpdateJIMS2UserID());
					}
					dispatch.postEvent(historyResponseEvent);
				}
			}
		}else if(getProgramReferralAssignmentHistoryEvent.getProgramReferralNumber() != null && getProgramReferralAssignmentHistoryEvent.getProgramReferralNumber().length() > 0 ){
			Iterator getProgramReferralAssignmentHistoryIter = JuvenileProgramReferralAssignmentHistory.
					findAll("programReferralId", getProgramReferralAssignmentHistoryEvent.getProgramReferralNumber());
				
				JuvenileProgramReferralAssignmentHistory programAssignmentHistory = null;		
				while (getProgramReferralAssignmentHistoryIter.hasNext()) {
					programAssignmentHistory = (JuvenileProgramReferralAssignmentHistory) getProgramReferralAssignmentHistoryIter.next();
		
					if (programAssignmentHistory != null) {
						ProgramReferralAssignmentHistoryResponseEvent historyResponseEvent = programAssignmentHistory.getValueObject(true);
						historyResponseEvent.setProgramReferralAssignmentHistoryId(programAssignmentHistory.getOID());
						//production support 
						if(programAssignmentHistory.getCreateUserID() != null){
							historyResponseEvent.setCreateUserID(programAssignmentHistory.getCreateUserID());
						}
						if(programAssignmentHistory.getCreateTimestamp() != null){
							historyResponseEvent.setCreateDate(new Date(programAssignmentHistory.getCreateTimestamp().getTime()));
						}
						if(programAssignmentHistory.getUpdateUserID() != null){
							historyResponseEvent.setUpdateUser(programAssignmentHistory.getUpdateUserID());
						}
						if(programAssignmentHistory.getUpdateTimestamp() != null){
							historyResponseEvent.setUpdateDate(new Date(programAssignmentHistory.getUpdateTimestamp().getTime()));
						}
						if(programAssignmentHistory.getCreateJIMS2UserID() != null){
							historyResponseEvent.setCreateJIMS2UserID(programAssignmentHistory.getCreateJIMS2UserID());
						}
						if(programAssignmentHistory.getUpdateJIMS2UserID() != null){
							historyResponseEvent.setUpdateJIMS2UserID(programAssignmentHistory.getUpdateJIMS2UserID());
						}
						dispatch.postEvent(historyResponseEvent);
					}
				}		
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
