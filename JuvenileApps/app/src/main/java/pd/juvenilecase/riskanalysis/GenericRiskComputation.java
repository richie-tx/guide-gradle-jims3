package pd.juvenilecase.riskanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.riskanalysis.SaveRiskAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;

import pd.exception.ComputationValidationException;
import pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputationUtil;

public class GenericRiskComputation {
	private HashMap answerMap;
	private SaveRiskAssessmentEvent saveRiskEvent;
	private RiskComputationReponseEvent riskCompRespEvent;  

	public GenericRiskComputation (SaveRiskAssessmentEvent saveRiskEvent) {
		this.saveRiskEvent = saveRiskEvent; 
		this.setWeightOfQuestions();
	}
	
	public void setWeightOfQuestions() {
		HashMap hmAnswerWeights = RiskComputationUtil.getQuestionWeights(saveRiskEvent);
		this.setAnswerMap(hmAnswerWeights);
	}
	
	/**
	 * @return
	 * @throws ComputationValidationException
	 */
	public RiskComputationReponseEvent compute() {
		
		riskCompRespEvent = new RiskComputationReponseEvent();
		
		//Retrieve categories associated to formula
		List <RiskCategory> categoryList = RiskCategory.findAll("formulaId", saveRiskEvent.getRiskFormulaId());
		
		Map resultGroupMap = new HashMap();
		RiskCategory riskCategory = null;
		
		//Build map of unique result groups.
		Integer ZERO = new Integer(0);
		for (int i = 0; i < categoryList.size(); i++) {
			riskCategory = categoryList.get(i);
			if (resultGroupMap.get(riskCategory.getRiskResultGroupId()) == null){
				resultGroupMap.put(riskCategory.getRiskResultGroupId(), ZERO);
			}
			
		}

		//Build hashmap of questions associated to each category using categoryId as key.
		HashMap questionsByCategoryMap = RiskComputationUtil.getQuestionsByCategories(categoryList);
	    
		//Create a list of categoryIds.
		Set aSet = questionsByCategoryMap.keySet();
	    Iterator iter = aSet.iterator();
		List <String> categoryIds = CollectionUtil.iteratorToList(iter);
		
		
		String categoryId = null;
		List <RiskQuestions> questionList = null;
		HashMap thisCategoryQuestionWeightsMap = null;
		RiskQuestions rq = null;
		int totalScore = 0;
		riskCompRespEvent.setTotalScores(new ArrayList());
		
		//Traverse through list of categories.
		for (int i = 0; i < categoryIds.size(); i++) {
			categoryId = categoryIds.get(i);
			riskCategory = categoryList.get(i);
			
			//Retrieve list of questions associated to category
			questionList = (List) questionsByCategoryMap.get(categoryId);
			thisCategoryQuestionWeightsMap = new HashMap();
			
			//Retrieve answers associated to question and store weights in a map.
			for (int j = 0; j < questionList.size(); j++) {
				rq = questionList.get(j);
				Integer weight = (Integer) this.getAnswerMap().get(rq.getOID());
				thisCategoryQuestionWeightsMap.put(rq.getOID(), weight);
			}
			
			//Calculate total score for category.
			int score = RiskComputationUtil.calculateScore(thisCategoryQuestionWeightsMap);
			//Accumulate scores by result group.
			Integer accumulatedScore = (Integer) resultGroupMap.get(riskCategory.getRiskResultGroupId());
			accumulatedScore = accumulatedScore + score;
			resultGroupMap.put(riskCategory.getRiskResultGroupId(), accumulatedScore);
		}
		
		aSet = resultGroupMap.keySet();
	    iter = aSet.iterator();
		List <Integer> resultGroupIds = CollectionUtil.iteratorToList(iter);
		Integer resultGroupId = null;
		RiskFinalScore rfs = null;
		
		for (int i = 0; i < resultGroupIds.size(); i++) {
			resultGroupId = resultGroupIds.get(i);
			totalScore = (Integer) resultGroupMap.get(resultGroupId);
			rfs = this.createRiskScore(resultGroupId, totalScore);
			riskCompRespEvent.getTotalScores().add(rfs);
		}
		
	
		return riskCompRespEvent;
	}
	
	protected RiskFinalScore createRiskScore(Integer resultGroupId, int score) {
		RiskFinalScore riskFinalScore = new RiskFinalScore();
		riskFinalScore.setRiskAnalysisId(Integer.parseInt(saveRiskEvent.getRiskAnalysisId()));
		riskFinalScore.setRiskResultGroupId(resultGroupId.intValue());
		riskFinalScore.setFinalScore(score);
		//Explicitly bind to database so that a Risk Final Score OID is generated
		IHome home=new Home();
		home.bind(riskFinalScore);

		return riskFinalScore;
	}	

	protected void setSaveRiskEvent(SaveRiskAssessmentEvent saveRiskEvent) {
		this.saveRiskEvent = saveRiskEvent;
	}
	protected SaveRiskAssessmentEvent getSaveRiskEvent() {
		return saveRiskEvent;
	}
	protected void setAnswerMap(HashMap answerMap) {
		this.answerMap = answerMap;
	}
	protected HashMap getAnswerMap() {
		return answerMap;
	}
}
