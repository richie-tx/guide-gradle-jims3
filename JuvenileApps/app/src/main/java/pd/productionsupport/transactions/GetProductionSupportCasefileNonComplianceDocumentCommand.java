package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.productionsupport.GetProductionSupportBenefitAssessmentsEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceDocumentEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CasefileDocument;
import pd.juvenilecase.family.BenefitsAssessment;

public class GetProductionSupportCasefileNonComplianceDocumentCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportCasefileNonComplianceDocumentCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		System.out.println("GetProductionSupportCasefileNonComplianceDocumentCommand");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportCasefileNonComplianceDocumentEvent getNonComplianceDocumentEvent = (GetProductionSupportCasefileNonComplianceDocumentEvent) event;
		Iterator nonComplianceDocumentIter = CasefileDocument.findAll("casefileNonComplianceNoticeId",getNonComplianceDocumentEvent.getNoncomnoteId());
		
		while(nonComplianceDocumentIter != null && nonComplianceDocumentIter.hasNext()){
			CasefileDocument casefileDocument = (CasefileDocument)nonComplianceDocumentIter.next();	
			CasefileDocumentsResponseEvent casefileDocumentResponseEvent = new CasefileDocumentsResponseEvent();
			
			casefileDocumentResponseEvent.setReportId(casefileDocument.getOID());	
			casefileDocumentResponseEvent.setCasefileNonComplianceNoticeId(casefileDocument.getCasefileNonComplianceNoticeId());
			
			if(casefileDocument.getCreateUserID() != null){
				casefileDocumentResponseEvent.setCreateUserID(casefileDocument.getCreateUserID());
			}
			if(casefileDocument.getCreateTimestamp() != null){
				casefileDocumentResponseEvent.setCreateDate(new Date(casefileDocument.getCreateTimestamp().getTime()));
			}
			if(casefileDocument.getUpdateUserID() != null){
				casefileDocumentResponseEvent.setUpdateUser(casefileDocument.getUpdateUserID());
			}
			if(casefileDocument.getUpdateTimestamp() != null){
				casefileDocumentResponseEvent.setUpdateDate(new Date(casefileDocument.getUpdateTimestamp().getTime()));
			}
			if(casefileDocument.getCreateJIMS2UserID() != null){
				casefileDocumentResponseEvent.setCreateJIMS2UserID(casefileDocument.getCreateJIMS2UserID());
			}
			if(casefileDocument.getUpdateJIMS2UserID() != null){
				casefileDocumentResponseEvent.setUpdateJIMS2UserID(casefileDocument.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(casefileDocumentResponseEvent);
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
