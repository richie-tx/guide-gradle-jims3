package pd.productionsupport.transactions;

import java.util.Date;

import messaging.productionsupport.GetProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class GetProductionSupportRiskAnalysisCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportRiskAnalysisCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportRiskAnalysisEvent riskResponsesEvent = (GetProductionSupportRiskAnalysisEvent) event;
		RiskAnalysis riskAnalysis = RiskAnalysis.find(riskResponsesEvent.getRiskAnalysisId());
			
		ProductionSupportRiskAnalysisResponseEvent riskAnalysisResponseEvent = new ProductionSupportRiskAnalysisResponseEvent();
		
		if(riskAnalysis != null){
			riskAnalysisResponseEvent.setRiskAnalysisId(riskAnalysis.getOID());
			riskAnalysisResponseEvent.setFinalScore(riskAnalysis.getFinalScore());
			riskAnalysisResponseEvent.setAssessmentType(riskAnalysis.getAssessmentType());
			riskAnalysisResponseEvent.setJuvenileId(riskAnalysis.getJuvenileNum());
			riskAnalysisResponseEvent.setJpoUserId(riskAnalysis.getJpoUserID());
			riskAnalysisResponseEvent.setDateEntered(riskAnalysis.getEnteredDate());
			riskAnalysisResponseEvent.setRecommendation(riskAnalysis.getRecommendation());
			if(riskAnalysis.getCasefileID() != 0){
				riskAnalysisResponseEvent.setCaseFileId((new Integer(riskAnalysis.getCasefileID())).toString());
			}
			
	
			//production support 
			if(riskAnalysis.getCreateUserID() != null){
				riskAnalysisResponseEvent.setCreateUserID(riskAnalysis.getCreateUserID());
			}
			if(riskAnalysis.getCreateTimestamp() != null){
				riskAnalysisResponseEvent.setCreateDate(new Date(riskAnalysis.getCreateTimestamp().getTime()));
			}
			if(riskAnalysis.getUpdateUserID() != null){
				riskAnalysisResponseEvent.setUpdateUser(riskAnalysis.getUpdateUserID());
			}
			if(riskAnalysis.getUpdateTimestamp() != null){
				riskAnalysisResponseEvent.setUpdateDate(new Date(riskAnalysis.getUpdateTimestamp().getTime()));
			}
			if(riskAnalysis.getCreateJIMS2UserID() != null){
				riskAnalysisResponseEvent.setCreateJIMS2UserID(riskAnalysis.getCreateJIMS2UserID());
			}
			if(riskAnalysis.getUpdateJIMS2UserID() != null){
				riskAnalysisResponseEvent.setUpdateJIMS2UserID(riskAnalysis.getUpdateJIMS2UserID());
			}
			
		dispatch.postEvent(riskAnalysisResponseEvent);
			
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
