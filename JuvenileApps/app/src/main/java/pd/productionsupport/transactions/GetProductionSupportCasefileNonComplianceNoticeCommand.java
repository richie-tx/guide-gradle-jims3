package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.productionsupport.GetProductionSupportBenefitAssessmentsEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceNoticeEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CasefileNonComplianceNotice;
import pd.juvenilecase.family.BenefitsAssessment;

public class GetProductionSupportCasefileNonComplianceNoticeCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportCasefileNonComplianceNoticeCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		
		System.out.println("GetProductionSupportCasefileNonComplianceNoticeCommand");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportCasefileNonComplianceNoticeEvent getNoticeEvent = (GetProductionSupportCasefileNonComplianceNoticeEvent) event;
		Iterator nonComplianceNoticeIter = CasefileNonComplianceNotice.findAll("casefileId",getNoticeEvent.getCasefileId());
		
		while(nonComplianceNoticeIter != null && nonComplianceNoticeIter.hasNext()){
			CasefileNonComplianceNotice  casefileNonComplianceNotice= (CasefileNonComplianceNotice)nonComplianceNoticeIter.next();	
			CasefileNonComplianceNoticeResponseEvent nonComplianceNoticeResponseEvent = new CasefileNonComplianceNoticeResponseEvent();
			
			nonComplianceNoticeResponseEvent.setDocumentId(casefileNonComplianceNotice.getOID());
			nonComplianceNoticeResponseEvent.setCasefileNonComplianceNoticeId(casefileNonComplianceNotice.getOID());

			
			if(casefileNonComplianceNotice.getCreateUserID() != null){
				nonComplianceNoticeResponseEvent.setCreateUserID(casefileNonComplianceNotice.getCreateUserID());
			}
			if(casefileNonComplianceNotice.getCreateTimestamp() != null){
				nonComplianceNoticeResponseEvent.setCreateDate(new Date(casefileNonComplianceNotice.getCreateTimestamp().getTime()));
			}
			if(casefileNonComplianceNotice.getUpdateUserID() != null){
				nonComplianceNoticeResponseEvent.setUpdateUser(casefileNonComplianceNotice.getUpdateUserID());
			}
			if(casefileNonComplianceNotice.getUpdateTimestamp() != null){
				nonComplianceNoticeResponseEvent.setUpdateDate(new Date(casefileNonComplianceNotice.getUpdateTimestamp().getTime()));
			}
			if(casefileNonComplianceNotice.getCreateJIMS2UserID() != null){
				nonComplianceNoticeResponseEvent.setCreateJIMS2UserID(casefileNonComplianceNotice.getCreateJIMS2UserID());
			}
			if(casefileNonComplianceNotice.getUpdateJIMS2UserID() != null){
				nonComplianceNoticeResponseEvent.setUpdateJIMS2UserID(casefileNonComplianceNotice.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(nonComplianceNoticeResponseEvent);
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
