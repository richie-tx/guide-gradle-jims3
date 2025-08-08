package pd.juvenilecase.riskanalysis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import naming.PDConstants;
import naming.RiskAnalysisConstants;

public class PDRiskAnalysisVersioningHelper {
	private static final String ZERO = "0";
	
	public static RiskFormula getNewRiskFormulaVersion(String formulaId){
		
		IHome home = new Home();
		if (formulaId == null || formulaId.equals(PDConstants.BLANK)){
			return null;
		}
		
		RiskFormula riskFormula = (RiskFormula) home.find(formulaId, RiskFormula.class);
		
		if (riskFormula != null && 
				riskFormula.getStatusCd() != null 
				&& !riskFormula.getStatusCd().equals(PDConstants.BLANK)
				&& riskFormula.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE)){
			riskFormula = new RiskFormula(riskFormula);
			riskFormula.setCreateTimestamp(new Timestamp(new Date().getTime()));
			home.bind(riskFormula);
		}

		home = null;
		
		return riskFormula;
	}
	
	public static RiskQuestions cloneQuestion(RiskQuestions origQuestion, Map oldAnswerToNewAnswerMap) {
		
		List <RiskWeightedResponse> answers = 
			CollectionUtil.iteratorToList(RiskWeightedResponse.findAll(RiskAnalysisConstants.RISK_QUESTION_ID, origQuestion.getOID()));
		
		RiskQuestions newQuestion = new RiskQuestions(origQuestion);
		IHome home = new Home();
		home.bind(newQuestion);
		
		RiskWeightedResponse origAnswer = null;
		RiskWeightedResponse newAnswer = null;
		
		for (int i = 0; i < answers.size(); i++) {
			origAnswer = answers.get(i);
			newAnswer = new RiskWeightedResponse(origAnswer);
			newAnswer.setRiskQuestionId(newQuestion.getOID());
			home.bind(newAnswer);
			oldAnswerToNewAnswerMap.put(origAnswer.getOID(), newAnswer.getOID());
		}

		origQuestion = null;
		origAnswer = null;
		newAnswer = null;
		home = null;
		answers = null;
		
		return newQuestion;
	}
	
	public static RiskRecommendation cloneRecommendation(RiskRecommendation origRecommendation, Map oldRecommendationToNewRecommendationMap){
			
		RiskRecommendation newRecommendation = new RiskRecommendation(origRecommendation);
		
		IHome home = new Home();
		home.bind(newRecommendation);
		oldRecommendationToNewRecommendationMap.put(origRecommendation.getOID(), newRecommendation.getOID());

		newRecommendation = null;
		origRecommendation = null;
		home = null;
	
		return newRecommendation;
	}

	public static Map buildQuestionMap(List <RiskQuestions> questionList) {
		
		Map aMap = new HashMap();
		RiskQuestions question = null;
		
		if (questionList != null){
			for (int i = 0; i < questionList.size(); i++) {
				question = questionList.get(i);
				aMap.put(question.getOID(), question);
			}
		}
		
		question = null;
		
		return aMap;
	}
	
	/**
	 * Convert subordinateQuestionId on cloned responses.
	 * @param clonedQuestionList
	 * @param newQuestionToOldQuestionMap
	 */
	public static void fixAnswersToSubordinateQuestions(List <RiskQuestions> clonedQuestionList, Map oldQuestionToNewQuestionMap){
		RiskWeightedResponse answer = null;
		RiskQuestions question = null;
		
		for (int i = 0; i < clonedQuestionList.size(); i++) {
			question = clonedQuestionList.get(i);
			//These following answers were cloned along with the questions.  
			//Have to correct the subordinate question IDs on answers as needed.
			List <RiskWeightedResponse> answers = 
				CollectionUtil.iteratorToList(RiskWeightedResponse.findAll(RiskAnalysisConstants.RISK_QUESTION_ID, question.getOID()));
			for (int j = 0; j < answers.size(); j++) {
				answer = answers.get(j);
				if (answer.getSubordinateQuestionId()!= null 
						&& !answer.getSubordinateQuestionId().equals(PDConstants.BLANK)
						&& !answer.getSubordinateQuestionId().equals("0")){
					String newQuestionId = (String) oldQuestionToNewQuestionMap.get(answer.getSubordinateQuestionId());
					if (newQuestionId != null){
						answer.setSubordinateQuestionId(newQuestionId);
					}
					newQuestionId = null;
				}
			}
			answers = null;
			answer = null;
		}

		question = null;

	}
	
/*	public static void cloneFormulaCategoriesAndQuestions(
				String oldFormulaId, 
				String newFormulaId, 
				Map oldCategoryToNewCategoryMap,
				Map oldQuestionToNewQuestionMap,
				Map oldAnswerToNewAnswerMap,
				String modificationReason) {
		
		String oldCategoryId = null;
		String newCategoryId = null;
		IHome home = new Home();
		String oldQuestionId = null;
		String newQuestionId = null;
		
		List <RiskCategory> categories = RiskCategory.findAll("formulaId", oldFormulaId);
		List clonedQuestionList = new ArrayList();
		RiskCategory category = null;
		RiskQuestions question = null;
		
		if (categories != null){
			for (int i = 0; i < categories.size(); i++) {
				category = categories.get(i);
				oldCategoryId = category.getOID();
				category = new RiskCategory(category);
				category.setFormulaId(newFormulaId);
				category.setModificationReason(modificationReason);
				home.bind(category);
				newCategoryId = category.getOID();
				oldCategoryToNewCategoryMap.put(oldCategoryId, newCategoryId);
				
				List <RiskQuestions> questions = CollectionUtil.iteratorToList(RiskQuestions.findAll("riskCategoryId", oldCategoryId));
				
				for (int j = 0; j < questions.size(); j++) {
					question = questions.get(j);
					oldQuestionId = question.getOID();
					question = PDRiskAnalysisVersioningHelper.cloneQuestion(question, oldAnswerToNewAnswerMap);
					question.setRiskCategoryId(newCategoryId);
					home.bind(question);
					clonedQuestionList.add(question);
					newQuestionId = question.getOID();
					oldQuestionToNewQuestionMap.put(oldQuestionId, newQuestionId);
				}
				questions = null;
			}
			PDRiskAnalysisVersioningHelper.fixAnswersToSubordinateQuestions(clonedQuestionList, oldQuestionToNewQuestionMap);
		}
		
		oldCategoryId = null;
		newCategoryId = null;
		oldQuestionId = null;
		newQuestionId = null;
		categories = null;
		category = null;
		question = null;
		home = null;
	}*/
	
	public static void cloneFormulaRecommendationsCategoriesAndQuestions(
			String oldFormulaId, 
			String newFormulaId, 
			Map oldCategoryToNewCategoryMap,
			Map oldQuestionToNewQuestionMap,
			Map oldAnswerToNewAnswerMap,
			Map oldRecommendationToNewRecommendationMap,
			String modificationReason) {
	
		String oldCategoryId = null;
		String newCategoryId = null;
		IHome home = new Home();
		String oldQuestionId = null;
		String newQuestionId = null;
		
		List <RiskCategory> categories = RiskCategory.findAll("formulaId", oldFormulaId);
		List clonedQuestionList = new ArrayList();
		RiskCategory category = null;
		RiskQuestions question = null;
		
		if (categories != null){
			for (int i = 0; i < categories.size(); i++) {
				category = categories.get(i);
				oldCategoryId = category.getOID();
				category = new RiskCategory(category);
				category.setFormulaId(newFormulaId);
				category.setModificationReason(modificationReason);
				home.bind(category);
				newCategoryId = category.getOID();
				oldCategoryToNewCategoryMap.put(oldCategoryId, newCategoryId);
				
				List <RiskQuestions> questions = CollectionUtil.iteratorToList(RiskQuestions.findAll("riskCategoryId", oldCategoryId));
				
				for (int j = 0; j < questions.size(); j++) {
					question = questions.get(j);
					oldQuestionId = question.getOID();
					question = PDRiskAnalysisVersioningHelper.cloneQuestion(question, oldAnswerToNewAnswerMap);
					question.setRiskCategoryId(newCategoryId);
					home.bind(question);
					clonedQuestionList.add(question);
					newQuestionId = question.getOID();
					oldQuestionToNewQuestionMap.put(oldQuestionId, newQuestionId);
				}
				questions = null;
			}
			PDRiskAnalysisVersioningHelper.fixAnswersToSubordinateQuestions(clonedQuestionList, oldQuestionToNewQuestionMap);
		}
		
		List <RiskRecommendation> recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", oldFormulaId));
		RiskRecommendation recommendation = null;
		String oldRecommendationId = null;
		
		for (int i = 0; i < recommendations.size(); i++) {
			recommendation = recommendations.get(i);
			oldRecommendationId = recommendation.getOID();
			recommendation = new RiskRecommendation(recommendation);
			recommendation.setRiskFormulaId(newFormulaId);
			home.bind(recommendation);
			oldRecommendationToNewRecommendationMap.put(oldRecommendationId, recommendation.getOID());
		}
		
		oldCategoryId = null;
		newCategoryId = null;
		oldQuestionId = null;
		newQuestionId = null;
		oldRecommendationId = null;
		categories = null;
		recommendations = null;
		category = null;
		question = null;
		recommendation = null;
		home = null;
}	
	public static void addRiskCategoryIdToQuestion(RiskQuestions question, String categoryId){

		question.setRiskCategoryId(categoryId);
		List <RiskWeightedResponse> answers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll("riskQuestionId", question.getOID()));
		RiskWeightedResponse answer = null;
		
		for (int i = 0; i < answers.size(); i++) {
			answer = answers.get(i);
			if (answer.getSubordinateQuestionId() != null 
					&& !answer.getSubordinateQuestionId().equals(PDConstants.BLANK)
					&& !answer.getSubordinateQuestionId().equals(ZERO)){
				RiskQuestions subQuestion = RiskQuestions.find(answer.getSubordinateQuestionId());
				PDRiskAnalysisVersioningHelper.addRiskCategoryIdToQuestion(subQuestion, categoryId);
				
				subQuestion = null;
			}
		}
		
		answers = null;
		answer = null;
	}
	
	public static void removeRiskCategoryIdFromQuestions(RiskQuestions question){

		question.setRiskCategoryId(null);
		List <RiskWeightedResponse> answers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll("riskQuestionId", question.getOID()));
		RiskWeightedResponse answer = null;
		
		for (int i = 0; i < answers.size(); i++) {
			answer = answers.get(i);
			if (answer.getSubordinateQuestionId() != null 
					&& !answer.getSubordinateQuestionId().equals(PDConstants.BLANK)
					&& !answer.getSubordinateQuestionId().equals(ZERO)){
				RiskQuestions subQuestion = RiskQuestions.find(answer.getSubordinateQuestionId());
				PDRiskAnalysisVersioningHelper.removeRiskCategoryIdFromQuestions(subQuestion);
				
				subQuestion = null;
			}
		}
		
		answers = null;
		answer = null;
	}

	public static void removeRiskCategoryIdFromSubordinateQuestions(RiskWeightedResponse answer){

		if (answer.getSubordinateQuestionId() != null 
				&& !answer.getSubordinateQuestionId().equals(PDConstants.BLANK)
				&& !answer.getSubordinateQuestionId().equals(ZERO)){
			RiskQuestions subQuestion = RiskQuestions.find(answer.getSubordinateQuestionId());
			if (subQuestion != null){
				PDRiskAnalysisVersioningHelper.removeRiskCategoryIdFromQuestions(subQuestion);
			}
			subQuestion = null;
		}
	}
	
	public static void deleteRecommendation(RiskRecommendation recommendation){
		
		IHome home = new Home();
		
		recommendation.delete();
		home.bind(recommendation);
		
		home = null;
		recommendation = null;
	}
	
	public static void deleteCategoryQuestionsAndAnswers(RiskCategory category){
		
		IHome home = new Home();
		List <RiskQuestions> questions = CollectionUtil.iteratorToList(RiskQuestions.findAll("riskCategoryId", category.getOID()));
		RiskQuestions question = null;
		
		for (int i = 0; i < questions.size(); i++) {
			question = questions.get(i);
			PDRiskAnalysisVersioningHelper.deleteQuestionAndAnswers(question);
		}
		category.delete();
		home.bind(category);
		
		home = null;
		questions = null;
		question = null;
		category = null;
	}
	
	public static void deleteQuestionAndAnswers(RiskQuestions question){

		IHome home = new Home();
		List <RiskWeightedResponse> answers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll("riskQuestionId", question.getOID()));
		RiskWeightedResponse answer = null;
		for (int i = 0; i < answers.size(); i++) {
			answer = answers.get(i);
			if (answer.getSubordinateQuestionId() != null 
					&& !answer.getSubordinateQuestionId().equals(PDConstants.BLANK)
					&& !answer.getSubordinateQuestionId().equals(ZERO)){
				RiskQuestions subQuestion = RiskQuestions.find(answer.getSubordinateQuestionId());
				if (subQuestion != null){
					PDRiskAnalysisVersioningHelper.deleteQuestionAndAnswers(subQuestion);
					subQuestion.delete();
					home.bind(subQuestion);
				}
				answer.delete();
				home.bind(answer);
				subQuestion = null;
			}
			answer.delete();
			home.bind(answer);
		}
		question.delete();
		
		home = null;
		answers = null;
		answer = null;
		question = null;
	}
	
	public static void deleteAnswerAndSubordinateQuestion(RiskWeightedResponse answer){

		IHome home = new Home();
		
		if (answer.getSubordinateQuestionId() != null 
				&& !answer.getSubordinateQuestionId().equals(PDConstants.BLANK)
				&& !answer.getSubordinateQuestionId().equals(ZERO)){
			RiskQuestions subQuestion = RiskQuestions.find(answer.getSubordinateQuestionId());
			PDRiskAnalysisVersioningHelper.deleteQuestionAndAnswers(subQuestion);
			answer.delete();
			home.bind(answer);
			subQuestion.delete();
			home.bind(subQuestion);
			
			subQuestion = null;
		}
		answer.delete();
		home.bind(answer);
		
		home = null;
		answer = null;
		
	}
}
