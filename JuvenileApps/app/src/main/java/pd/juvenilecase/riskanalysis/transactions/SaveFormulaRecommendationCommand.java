package pd.juvenilecase.riskanalysis.transactions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import naming.PDConstants;
import naming.RiskAnalysisConstants;
import messaging.riskanalysis.SaveFormulaRecommendationEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class SaveFormulaRecommendationCommand implements ICommand
{
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-UPDATE OF RECOMMENDATION";
	private static final String CATEGORY_MSG = "NEW CATEGORY CREATED-UPDATE OF RECOMMENDATION";

	
	public void execute(IEvent anEvent)
	{
		SaveFormulaRecommendationEvent reqEvent = (SaveFormulaRecommendationEvent) anEvent;
		
		RiskRecommendation recommendation = null;
		RiskFormula riskFormula = null;
		String oldFormulaId = null;
		String newFormulaId = null;
		
		if (reqEvent.getRecommendationId() != null &&!reqEvent.getRecommendationId().equals(PDConstants.BLANK)){
			recommendation = RiskRecommendation.find(reqEvent.getRecommendationId());
		} else {
			recommendation = new RiskRecommendation();
			recommendation.setCreateTimestamp(new Timestamp(new Date().getTime()));			
		}
			
		boolean createNewVersion = this.isCreateNewVersion(recommendation, reqEvent);
		
		if (createNewVersion){
			oldFormulaId = reqEvent.getFormulaId();
			riskFormula = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(reqEvent.getFormulaId());
			riskFormula.setModificationReason(FORMULA_MSG);
			newFormulaId = riskFormula.getOID();
		} else {
			riskFormula = RiskFormula.find(reqEvent.getFormulaId());
			if (recommendation.getOID() != null){
				recommendation.setUpdateTimestamp(new Timestamp(new Date().getTime()));
			}
		}

		Map oldCategoryToNewCategoryMap = new HashMap();
		Map oldQuestionToNewQuestionMap = new HashMap();
		Map oldAnswerToNewAnswerMap = new HashMap();
		Map oldRecommendationToNewRecommendationMap = new HashMap();

		if (createNewVersion){
			PDRiskAnalysisVersioningHelper.
				cloneFormulaRecommendationsCategoriesAndQuestions(
						oldFormulaId, newFormulaId, oldCategoryToNewCategoryMap, 
						oldQuestionToNewQuestionMap, oldAnswerToNewAnswerMap, 
						oldRecommendationToNewRecommendationMap, CATEGORY_MSG);
			if (recommendation.getOID() != null){
				String newRecommendationId = (String) oldRecommendationToNewRecommendationMap.get(recommendation.getOID());
				recommendation = RiskRecommendation.find(newRecommendationId);
				newRecommendationId = null;
			}
		}  

		recommendation.setAssessmentType(reqEvent.getAssessmentTypeName());
		recommendation.setMaxValue(reqEvent.getMaxScore());
		recommendation.setMinValue(reqEvent.getMinScore());
		recommendation.setRecommendation(reqEvent.getRecommendationText());
		recommendation.setRecommendationName(reqEvent.getRecommendationName());
		recommendation.setRiskFormulaId(riskFormula.getOID());
		recommendation.setRiskResultGroupId(Integer.parseInt(reqEvent.getResultGroupId()));
		
		IHome home = new Home();
		home.bind(recommendation);
		
		List <RiskRecommendation> recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", recommendation.getRiskFormulaId()));
		for (int i = 0; i < recommendations.size(); i++) {
			recommendation = recommendations.get(i);
			MessageUtil.postReply(recommendation.getResponseEvent());
		}
		
		MessageUtil.postReply(riskFormula.getResponseEvent());
		
		reqEvent = null;
		recommendation = null;
		riskFormula = null;
		oldFormulaId = null;
		newFormulaId = null;
		oldCategoryToNewCategoryMap = null;
		oldQuestionToNewQuestionMap = null;
		oldAnswerToNewAnswerMap = null;
		oldRecommendationToNewRecommendationMap = null;
		home = null;
	}

	private boolean isCreateNewVersion(RiskRecommendation recommendation,
			SaveFormulaRecommendationEvent reqEvent) {

		boolean isNewVersion = false;
		
		if (reqEvent.getFormulaId() == null || reqEvent.getFormulaId().equals(PDConstants.BLANK)){
			isNewVersion = false;
			return isNewVersion;
		} else {
			IHome home = new Home();
			RiskFormula rf = (RiskFormula) home.find(reqEvent.getFormulaId(), RiskFormula.class);
			if (rf.getStatusCd() != null && rf.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING)){
				isNewVersion = false;
				rf = null;
				home = null;
				return isNewVersion;
			}
			rf = null;
			home = null;
		}
		
		if (reqEvent.getRecommendationId() == null || reqEvent.getRecommendationId().equals(PDConstants.BLANK)){
			isNewVersion = true;
			return isNewVersion;
		}
		
		String riskResultGroupId = Integer.toString(recommendation.getRiskResultGroupId());
		if (!riskResultGroupId.equals(reqEvent.getResultGroupId())){
			isNewVersion = true;
			riskResultGroupId = null;
			return isNewVersion;
		}
		
		if (!recommendation.getRecommendation().equals(reqEvent.getRecommendationText())){
			isNewVersion = true;
			return isNewVersion;
		}
		
		if (recommendation.getMinValue() != reqEvent.getMinScore()){
			isNewVersion = true;
			return isNewVersion;
		}
		
		if (recommendation.getMaxValue() != reqEvent.getMaxScore()){
			isNewVersion = true;
			return isNewVersion;
		}
		
		if (recommendation.isCustody() != reqEvent.isCustody()){
			isNewVersion = true;
			return isNewVersion;
		}
		
		return isNewVersion;
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
