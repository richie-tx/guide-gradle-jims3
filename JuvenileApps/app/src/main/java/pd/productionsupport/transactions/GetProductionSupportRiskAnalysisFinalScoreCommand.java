package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportRiskAnalysisFinalScoreEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisFinalScoreResponseEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskFinalScore;

public class GetProductionSupportRiskAnalysisFinalScoreCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportRiskAnalysisFinalScoreCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportRiskAnalysisFinalScoreEvent riskResponsesEvent = (GetProductionSupportRiskAnalysisFinalScoreEvent) event;
		Iterator riskAnalysisFinalScoreList = RiskFinalScore.findAllByAttributeName("riskAnalysisId", riskResponsesEvent.getRiskAnalysisId());
		
		if(riskAnalysisFinalScoreList != null){
			while(riskAnalysisFinalScoreList.hasNext()){
				RiskFinalScore currentRiskFinalScore = (RiskFinalScore)riskAnalysisFinalScoreList.next();
				ProductionSupportRiskAnalysisFinalScoreResponseEvent riskAnalysisScoreResponseEvent = new ProductionSupportRiskAnalysisFinalScoreResponseEvent();
				riskAnalysisScoreResponseEvent.setRiskAnalysisFinalScoreId(currentRiskFinalScore.getOID());
				riskAnalysisScoreResponseEvent.setFinalScore(currentRiskFinalScore.getFinalScore());
				riskAnalysisScoreResponseEvent.setRiskResultGroupId(Integer.toString(currentRiskFinalScore.getRiskResultGroupId()));
				riskAnalysisScoreResponseEvent.setRiskAnalysisId(Integer.toString(currentRiskFinalScore.getRiskAnalysisId()));
				
		
				//production support 
				if(currentRiskFinalScore.getCreateUserID() != null){
					riskAnalysisScoreResponseEvent.setCreateUserID(currentRiskFinalScore.getCreateUserID());
				}
				if(currentRiskFinalScore.getCreateTimestamp() != null){
					riskAnalysisScoreResponseEvent.setCreateDate(new Date(currentRiskFinalScore.getCreateTimestamp().getTime()));
				}
				if(currentRiskFinalScore.getUpdateUserID() != null){
					riskAnalysisScoreResponseEvent.setUpdateUser(currentRiskFinalScore.getUpdateUserID());
				}
				if(currentRiskFinalScore.getUpdateTimestamp() != null){
					riskAnalysisScoreResponseEvent.setUpdateDate(new Date(currentRiskFinalScore.getUpdateTimestamp().getTime()));
				}
				if(currentRiskFinalScore.getCreateJIMS2UserID() != null){
					riskAnalysisScoreResponseEvent.setCreateJIMS2UserID(currentRiskFinalScore.getCreateJIMS2UserID());
				}
				if(currentRiskFinalScore.getUpdateJIMS2UserID() != null){
					riskAnalysisScoreResponseEvent.setUpdateJIMS2UserID(currentRiskFinalScore.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(riskAnalysisScoreResponseEvent);			
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
