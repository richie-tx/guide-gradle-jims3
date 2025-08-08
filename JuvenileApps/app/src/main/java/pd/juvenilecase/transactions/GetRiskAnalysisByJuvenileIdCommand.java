package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetRiskAnalysisByJuvenileIdEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class GetRiskAnalysisByJuvenileIdCommand implements ICommand{
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetRiskAnalysisByJuvenileIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event)
	{
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    
	    GetRiskAnalysisByJuvenileIdEvent officerEvent = (GetRiskAnalysisByJuvenileIdEvent) event;
	    Iterator risks = RiskAnalysis.findAllByJuvenileNum(officerEvent.getJuvenileNum());
	    
	    while( risks.hasNext() ){
		
		RiskAnalysis risk = (RiskAnalysis) risks.next();
		
		RiskAssessmentListResponseEvent riskAssessList = new RiskAssessmentListResponseEvent();
		riskAssessList.setAssessmentDate(risk.getEnteredDate());
		riskAssessList.setAssessmentID(risk.getOID());
		riskAssessList.setAssessmentType(risk.getAssessmentType());
		//riskAssessList.setAssessmentTypeDesc( (String) assessmentTypeMap.get(risk.getAssessmentType()));
		riskAssessList.setCasefileId(String.valueOf(risk.getCasefileID()));
		riskAssessList.setCompleted(risk.isCompleted());
		riskAssessList.setFinalScore(new Integer(risk.getFinalScore()).toString());
		if(risk.getFinalScores() != null){
			riskAssessList.setFinalScores(risk.getFinalScores().toString());
		}
		
		riskAssessList.setJuvenileNumber(risk.getJuvenileNum());
		riskAssessList.setJpoUserId(risk.getJpoUserID());
		
		/*JuvenileCasefileResponseEvent casefileResponse =
			JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);*/
		
		//post response
		dispatch.postEvent(riskAssessList);
	    }
	    
	}
	

	public void onRegister(IEvent event)
	{

	}

	public void onUnregister(IEvent event)
	{

	}

	public void update(Object anObject)
	{

	}
}
