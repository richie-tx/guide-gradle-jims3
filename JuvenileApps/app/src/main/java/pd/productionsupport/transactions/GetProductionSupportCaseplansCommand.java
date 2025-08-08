package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.productionsupport.GetProductionSupportCaseplansEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.caseplan.CasePlan;

public class GetProductionSupportCaseplansCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportCaseplansCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportCaseplansEvent getCaseplansEvent = (GetProductionSupportCaseplansEvent) event;
		GetCaseplanDetailsEvent specificSearchEvent = new GetCaseplanDetailsEvent();
		specificSearchEvent.setSupervisionNumber(getCaseplansEvent.getCasefileId());
		
		Iterator caseplansIter = CasePlan.findAll(specificSearchEvent);
		
		while(caseplansIter.hasNext()) {
			CaseplanDetailsResponseEvent caseplanResponseEvent = new CaseplanDetailsResponseEvent();
			CasePlan caseplan = (CasePlan)caseplansIter.next();
			caseplanResponseEvent.setCaseplanID(caseplan.getOID().toString());
			caseplanResponseEvent.setReviewDate(caseplan.getReviewDate());
			caseplanResponseEvent.setCreateDate(caseplan.getCreateTimestamp());
			caseplanResponseEvent.setStatusId(caseplan.getStatusId());  
			caseplanResponseEvent.setPlacementId(caseplan.getThePlacementId());
			
			if(caseplan.getCreateUserID() != null){
				caseplanResponseEvent.setCreateUserID(caseplan.getCreateUserID());
			}
			if(caseplan.getCreateTimestamp() != null){
				caseplanResponseEvent.setCreateDate(new Date(caseplan.getCreateTimestamp().getTime()));
			}
			if(caseplan.getUpdateUserID() != null){
				caseplanResponseEvent.setUpdateUser(caseplan.getUpdateUserID());
			}
			if(caseplan.getUpdateTimestamp() != null){
				caseplanResponseEvent.setUpdateDate(new Date(caseplan.getUpdateTimestamp().getTime()));
			}
			if(caseplan.getCreateJIMS2UserID() != null){
				caseplanResponseEvent.setCreateJIMS2UserID(caseplan.getCreateJIMS2UserID());
			}
			if(caseplan.getUpdateJIMS2UserID() != null){
				caseplanResponseEvent.setUpdateJIMS2UserID(caseplan.getUpdateJIMS2UserID());
			}
			
	   		dispatch.postEvent(caseplanResponseEvent);
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
