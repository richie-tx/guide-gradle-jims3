package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.casefile.reply.CasefileNonComplianceNoticeProbationViolationResponseEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceNoticeProbationViolationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation;

public class GetProductionSupportCasefileNonComplianceNoticeProbationViolationCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportCasefileNonComplianceNoticeProbationViolationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		System.out.println("GetProductionSupportCasefileNonComplianceNoticeProbationViolationCommand");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportCasefileNonComplianceNoticeProbationViolationEvent noticeProbationViolationEvent = 
			(GetProductionSupportCasefileNonComplianceNoticeProbationViolationEvent) event;
		Iterator  noticeProbationViolationIter = CasefileNonComplianceNoticeProbationViolation.findAll("casefileNonComplianceNoticeId", noticeProbationViolationEvent.getNoncomnoteId());
		
		while(noticeProbationViolationIter != null && noticeProbationViolationIter.hasNext()){
			CasefileNonComplianceNoticeProbationViolation noticeProbationViolation = (CasefileNonComplianceNoticeProbationViolation) noticeProbationViolationIter.next();	
			CasefileNonComplianceNoticeProbationViolationResponseEvent probationViolationResponseEvent = new CasefileNonComplianceNoticeProbationViolationResponseEvent();
			
			probationViolationResponseEvent.setReportId(noticeProbationViolation.getOID());
			probationViolationResponseEvent.setCasefileNonComplianceNoticeId(noticeProbationViolation.getCasefileNonComplianceNoticeId());
		
			
			if(noticeProbationViolation.getCreateUserID() != null){
				probationViolationResponseEvent.setCreateUserID(noticeProbationViolation.getCreateUserID());
			}
			if(noticeProbationViolation.getCreateTimestamp() != null){
				probationViolationResponseEvent.setCreateDate(new Date(noticeProbationViolation.getCreateTimestamp().getTime()));
			}
			if(noticeProbationViolation.getUpdateUserID() != null){
				probationViolationResponseEvent.setUpdateUser(noticeProbationViolation.getUpdateUserID());
			}
			if(noticeProbationViolation.getUpdateTimestamp() != null){
				probationViolationResponseEvent.setUpdateDate(new Date(noticeProbationViolation.getUpdateTimestamp().getTime()));
			}
			if(noticeProbationViolation.getCreateJIMS2UserID() != null){
				probationViolationResponseEvent.setCreateJIMS2UserID(noticeProbationViolation.getCreateJIMS2UserID());
			}
			if(noticeProbationViolation.getUpdateJIMS2UserID() != null){
				probationViolationResponseEvent.setUpdateJIMS2UserID(noticeProbationViolation.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(probationViolationResponseEvent);
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
