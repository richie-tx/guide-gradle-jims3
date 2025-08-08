package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportCasefileNonComplianceNoticeSanctionEvent;
import messaging.productionsupport.reply.ProductionSupportNonComplianceNoticeSanctionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction;

public class GetProductionSupportCasefileNonComplianceNoticeSanctionCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportCasefileNonComplianceNoticeSanctionCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		System.out.println("GetProductionSupportCasefileNonComplianceNoticeSanctionCommand");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportCasefileNonComplianceNoticeSanctionEvent nonComplianceNoticeSanctionEvent = 
			(GetProductionSupportCasefileNonComplianceNoticeSanctionEvent) event;
		
		Iterator nonComplianceNoticeSanctionIter = CasefileNonComplianceNoticeSanction.findAll("casefileNonComplianceNoticeId", nonComplianceNoticeSanctionEvent.getNoncomnoteId());	
		while(nonComplianceNoticeSanctionIter != null && nonComplianceNoticeSanctionIter.hasNext()){
			CasefileNonComplianceNoticeSanction nonComplianceNoticeSanction = (CasefileNonComplianceNoticeSanction) nonComplianceNoticeSanctionIter.next();	
			ProductionSupportNonComplianceNoticeSanctionResponseEvent nonComplianceNoticeResponseEvent = new ProductionSupportNonComplianceNoticeSanctionResponseEvent();
			
			nonComplianceNoticeResponseEvent.setReportId(nonComplianceNoticeSanction.getOID());
			nonComplianceNoticeResponseEvent.setCasefileNonComplianceNoticeId(nonComplianceNoticeSanction.getCasefileNonComplianceNoticeId());

			
			if(nonComplianceNoticeSanction.getCreateUserID() != null){
				nonComplianceNoticeResponseEvent.setCreateUserID(nonComplianceNoticeSanction.getCreateUserID());
			}
			if(nonComplianceNoticeSanction.getCreateTimestamp() != null){
				nonComplianceNoticeResponseEvent.setCreateDate(new Date(nonComplianceNoticeSanction.getCreateTimestamp().getTime()));
			}
			if(nonComplianceNoticeSanction.getUpdateUserID() != null){
				nonComplianceNoticeResponseEvent.setUpdateUser(nonComplianceNoticeSanction.getUpdateUserID());
			}
			if(nonComplianceNoticeSanction.getUpdateTimestamp() != null){
				nonComplianceNoticeResponseEvent.setUpdateDate(new Date(nonComplianceNoticeSanction.getUpdateTimestamp().getTime()));
			}
			if(nonComplianceNoticeSanction.getCreateJIMS2UserID() != null){
				nonComplianceNoticeResponseEvent.setCreateJIMS2UserID(nonComplianceNoticeSanction.getCreateJIMS2UserID());
			}
			if(nonComplianceNoticeSanction.getUpdateJIMS2UserID() != null){
				nonComplianceNoticeResponseEvent.setUpdateJIMS2UserID(nonComplianceNoticeSanction.getUpdateJIMS2UserID());
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
