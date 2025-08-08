package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.reply.RiskResponseEvent;
import messaging.productionsupport.GetProductionSupportRiskResponsesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.RiskResponse;

public class GetProductionSupportRiskResponsesCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportRiskResponsesCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportRiskResponsesEvent riskResponsesEvent = (GetProductionSupportRiskResponsesEvent) event;
		Iterator riskResponsesIter = RiskResponse.findAll("riskAnalysisId",riskResponsesEvent.getRiskAssessmentId());
		
		while(riskResponsesIter != null && riskResponsesIter.hasNext()){
			RiskResponse riskResponse = (RiskResponse)riskResponsesIter.next();		
			RiskResponseEvent riskResponseEvent = new RiskResponseEvent();
			
			riskResponseEvent.setQuestionText(riskResponse.getText());
			riskResponseEvent.setResponseID(riskResponse.getRiskResponseId());
			Integer weight = 0;
			if(riskResponse.getWeightedResponseID() != null){
				weight = new Integer(riskResponse.getWeightedResponseID());
				riskResponseEvent.setWeight(weight);
			}
			if(riskResponse.getRiskAnalysisId() != null){
				riskResponseEvent.setRiskAnalysisID(riskResponse.getRiskAnalysisId().toString());
			}
			riskResponseEvent.setResponseText(riskResponse.getText());
			
			//production support 
			if(riskResponse.getCreateUserID() != null){
				riskResponseEvent.setCreateUserID(riskResponse.getCreateUserID());
			}
			if(riskResponse.getCreateTimestamp() != null){
				riskResponseEvent.setCreateDate(new Date(riskResponse.getCreateTimestamp().getTime()));
			}
			if(riskResponse.getUpdateUserID() != null){
				riskResponseEvent.setUpdateUser(riskResponse.getUpdateUserID());
			}
			if(riskResponse.getUpdateTimestamp() != null){
				riskResponseEvent.setUpdateDate(new Date(riskResponse.getUpdateTimestamp().getTime()));
			}
			if(riskResponse.getCreateJIMS2UserID() != null){
				riskResponseEvent.setCreateJIMS2UserID(riskResponse.getCreateJIMS2UserID());
			}
			if(riskResponse.getUpdateJIMS2UserID() != null){
				riskResponseEvent.setUpdateJIMS2UserID(riskResponse.getUpdateJIMS2UserID());
			}
			
			
			dispatch.postEvent(riskResponseEvent);
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
