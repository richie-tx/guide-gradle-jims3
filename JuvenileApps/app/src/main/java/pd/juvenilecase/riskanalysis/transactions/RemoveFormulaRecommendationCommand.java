package pd.juvenilecase.riskanalysis.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import messaging.riskanalysis.RemoveFormulaRecommendationEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class RemoveFormulaRecommendationCommand implements ICommand
{
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-REMOVE OF RECOMMENDATION";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-REMOVE OF RECOMMENDATION";

	
	public void execute(IEvent anEvent)
	{
		RemoveFormulaRecommendationEvent reqEvent = (RemoveFormulaRecommendationEvent) anEvent;
		
		RiskRecommendation recommendation = RiskRecommendation.find(reqEvent.getRecommendationId());
	
		boolean createNewVersion = this.isCreateNewVersion(recommendation.getRiskFormulaId());
		
		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();

		if (createNewVersion){
			String oldFormulaId = recommendation.getRiskFormulaId();
			RiskFormula riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(recommendation.getRiskFormulaId());
			riskFormula.setModificationReason(FORMULA_MSG);
			MessageUtil.postReply(riskFormula.getResponseEvent());
			
			PDRiskAnalysisVersioningHelper.
				cloneFormulaRecommendationsCategoriesAndQuestions(
					oldFormulaId, riskFormula.getOID(), oldCategoryToNewCategoryMap, 
					oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap, 
					oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
			
			recommendation = RiskRecommendation.find((String) oldRecommendationToNewRecommendationMap.get(reqEvent.getRecommendationId()));
			oldFormulaId = null;
			riskFormula = null;
		} 
		
		IHome home = new Home();
		recommendation.delete();
		home.bind(recommendation);

		List <RiskRecommendation> recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", recommendation.getRiskFormulaId()));
		for (int i = 0; i < recommendations.size(); i++) {
			recommendation = recommendations.get(i);
			MessageUtil.postReply(recommendation.getResponseEvent());
		}
		
		home = null;
		reqEvent = null;
		recommendation = null;
		recommendations = null;
		oldCategoryToNewCategoryMap = null;
		oldQuestionToNewQuestionMap = null;
		oldAnswerToNewAnswerMap = null;
		oldRecommendationToNewRecommendationMap = null;
	}

	private boolean isCreateNewVersion(String riskFormulaId) {
		
		boolean isCreateNewVersion = false;
		
		if (riskFormulaId != null){
			RiskFormula riskFormula = RiskFormula.find(riskFormulaId);
			
			if (riskFormula.getStatusCd() != null && 
					!riskFormula.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING)){
				isCreateNewVersion = true;
			}
			riskFormula = null;
		}
		
		return isCreateNewVersion;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
