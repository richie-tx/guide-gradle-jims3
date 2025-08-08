package pd.juvenilecase.riskanalysis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pd.codetable.riskanalysis.RiskAnalysisControlCode;

import naming.PDConstants;
import naming.RiskAnalysisConstants;
import messaging.riskanalysis.SaveAnswerEvent;
import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import messaging.riskanalysis.reply.SaveAnswerResponseEvent;
import messaging.riskanalysis.reply.SaveQuestionResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;

public class PDRiskQuestionHelper {

	private static final String TRUE_STRING = "true";
	private static final String FALSE_STRING = "false";
	private static final boolean BOOLEAN_TRUE = true;
	private static final boolean BOOLEAN_FALSE = false;
	private static final int ZERO = 0;
	
	public static RiskQuestions saveRiskQuestion(SaveQuestionEvent saveQuestionEvent){
		
		RiskQuestions riskQuestion = null;
		
		if (saveQuestionEvent.isACreate()) 
		{
			riskQuestion = new RiskQuestions();
		}
		else
		{
			riskQuestion = RiskQuestions.find(saveQuestionEvent.getRiskQuestionId());
		}

		if (saveQuestionEvent.getAllowFutureDates() != null 
				&& saveQuestionEvent.getAllowFutureDates().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setAllowsFutureDates(BOOLEAN_TRUE);
		} 
		else
		{
			riskQuestion.setAllowsFutureDates(BOOLEAN_FALSE);
		}
		
		//riskQuestion.setAssessmentType(saveQuestionEvent.getAssessmentType());
		riskQuestion.setInitialAction(saveQuestionEvent.getQuestionInitialAction());
		if (saveQuestionEvent.getDefaultToSystemDate() != null 
				&& saveQuestionEvent.getDefaultToSystemDate().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setDefaultToSystemDate(BOOLEAN_TRUE);
		} 
		else
		{
			riskQuestion.setDefaultToSystemDate(BOOLEAN_FALSE);
		}
		if (saveQuestionEvent.getNumericOnly() != null 
				&& saveQuestionEvent.getNumericOnly().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setNumeric(BOOLEAN_TRUE);
		}
		else
		{
			riskQuestion.setNumeric(BOOLEAN_FALSE);
		}
		riskQuestion.setQuestionName(saveQuestionEvent.getQuestionName());
		if (saveQuestionEvent.getCollapsibleHeader() != null 
				&& saveQuestionEvent.getCollapsibleHeader().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setCollapsibleHeader(BOOLEAN_TRUE);
		}
		else
		{
			riskQuestion.setCollapsibleHeader(BOOLEAN_FALSE);
		}
		riskQuestion.setControlCode(saveQuestionEvent.getControlCode());
		if (saveQuestionEvent.getHardcoded() != null 
				&& saveQuestionEvent.getHardcoded().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setQuestionTextNotModifiable(BOOLEAN_TRUE);
		}
		else
		{
			riskQuestion.setQuestionTextNotModifiable(BOOLEAN_FALSE);
		}
		riskQuestion.setQuestionText(saveQuestionEvent.getQuestionText());
		if (saveQuestionEvent.getRequired() != null && saveQuestionEvent.getRequired().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setRequired(BOOLEAN_TRUE);
		}
		else
		{
			riskQuestion.setRequired(BOOLEAN_FALSE);
		}
		
		if (saveQuestionEvent.getAllowPrint() != null && saveQuestionEvent.getAllowPrint().equalsIgnoreCase(TRUE_STRING)) 
		{
			riskQuestion.setAllowPrint(BOOLEAN_TRUE);
		}
		else
		{
			riskQuestion.setAllowPrint(BOOLEAN_FALSE);
		}
		
		riskQuestion.setUiControlType(saveQuestionEvent.getUiControlType());
		riskQuestion.setQuestionNbr(saveQuestionEvent.getUiDisplayOrder());
//		riskQuestion.setCreateUserID(saveQuestionEvent.getCreateUserID());
		riskQuestion.setCreateTimestamp(new Timestamp((new Date()).getTime()));
		riskQuestion.setRiskCategoryId(saveQuestionEvent.getRiskCategoryId());
		
		IHome home=new Home();
		home.bind(riskQuestion);
		
		home = null;
		return riskQuestion;
	}
	
	public static SaveQuestionResponseEvent getSaveQuestionResponseEvent(RiskQuestions riskQuestion){
		
		SaveQuestionResponseEvent saveQuestionResponseEvent = new SaveQuestionResponseEvent();
		saveQuestionResponseEvent.setRiskQuestionId(riskQuestion.getOID());
		saveQuestionResponseEvent.setAllowFutureDates(String.valueOf(riskQuestion.isAllowsFutureDates()));
		saveQuestionResponseEvent.setCollapsibleHeader(String.valueOf(riskQuestion.isCollapsibleHeader()));
		saveQuestionResponseEvent.setControlCode(riskQuestion.getControlCode());
		saveQuestionResponseEvent.setDefaultToSystemDate(String.valueOf(riskQuestion.isDefaultToSystemDate()));
		saveQuestionResponseEvent.setHardcoded(String.valueOf(riskQuestion.isQuestionTextNotModifiable()));
		saveQuestionResponseEvent.setNumericOnly(String.valueOf(riskQuestion.isNumeric()));
		saveQuestionResponseEvent.setQuestionInitialAction(riskQuestion.getInitialAction());
		saveQuestionResponseEvent.setQuestionName(riskQuestion.getQuestionName());
		saveQuestionResponseEvent.setQuestionText(riskQuestion.getQuestionText());
		saveQuestionResponseEvent.setQuestonEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(riskQuestion.getCreateTimestamp()));
		saveQuestionResponseEvent.setRequired(String.valueOf(riskQuestion.isRequired()));
		saveQuestionResponseEvent.setUiControlType(riskQuestion.getUiControlType());
		saveQuestionResponseEvent.setUiDisplayOrder(riskQuestion.getQuestionNbr());
		saveQuestionResponseEvent.setRiskCategoryId(riskQuestion.getRiskCategoryId());
		saveQuestionResponseEvent.setAllowPrint(String.valueOf(riskQuestion.isAllowPrint()));

		return saveQuestionResponseEvent;
	}
	public static List saveAnswers(SaveQuestionEvent saveQuestionEvent, String questionId){
		
		SaveAnswerEvent saveAnswerEvent = null;
		Object obj = null;
		RiskWeightedResponse riskWeightedResponse = null;
		IHome home=new Home();
		
		List events = CollectionUtil.enumerationToList(saveQuestionEvent.getRequests());
		List answers = new ArrayList();
		
		for (int i = ZERO; i < events.size(); i++) 
		{
			obj = events.get(i);
			if( obj instanceof SaveAnswerEvent ) {	
				saveAnswerEvent = (SaveAnswerEvent)obj;
				
				riskWeightedResponse =  new RiskWeightedResponse();
				riskWeightedResponse.setResponse(saveAnswerEvent.getAnswerText());
				riskWeightedResponse.setAction(saveAnswerEvent.getAction());
				riskWeightedResponse.setRiskQuestionId(questionId);
				if (saveAnswerEvent.getSortOrder() != null & saveAnswerEvent.getSortOrder().length() > ZERO) 
				{ 
					riskWeightedResponse.setSortOrder(Integer.parseInt(saveAnswerEvent.getSortOrder()));
				}
				riskWeightedResponse.setResponse(saveAnswerEvent.getAnswerText());
				if (saveAnswerEvent.getSubordinateQuestionId() != null & saveAnswerEvent.getSubordinateQuestionId().length() > ZERO) 
				{ 
					riskWeightedResponse.setSubordinateQuestionId(saveAnswerEvent.getSubordinateQuestionId());
				}
				if (saveAnswerEvent.getWeight() != null & saveAnswerEvent.getWeight().length() > ZERO) 
				{ 
					riskWeightedResponse.setWeight(Integer.parseInt(saveAnswerEvent.getWeight()));
				}
				riskWeightedResponse.setCreateUserID(saveAnswerEvent.getCreateUserID());
				riskWeightedResponse.setCreateTimestamp(new Timestamp((new Date()).getTime()));
				
				//riskWeightedResponse.setSuggestedCasePlainDomiainId(); will eventually add this
				
				home.bind(riskWeightedResponse);
				answers.add(riskWeightedResponse);
			}		
		}
		obj = null;
		saveAnswerEvent = null;	
		riskWeightedResponse = null;
		home = null;
		events = null;
		
		return answers;
	}
	public static List <SaveAnswerResponseEvent> getSaveAnswerResponseEvents(List answers){
		
		SaveAnswerResponseEvent saveAnswerResponseEvent = null;
		RiskWeightedResponse riskWeightedResponse = null;
		List reList = new ArrayList();
		
		for (int i = ZERO; i < answers.size(); i++) {
			riskWeightedResponse = (RiskWeightedResponse) answers.get(i);
			saveAnswerResponseEvent = PDRiskQuestionHelper.getSaveAnswerResponseEvent(riskWeightedResponse);
			reList.add(saveAnswerResponseEvent);
		}
		
		saveAnswerResponseEvent = null;
		riskWeightedResponse = null;
		
		return reList;
	}
	
	public static SaveAnswerResponseEvent getSaveAnswerResponseEvent(RiskWeightedResponse riskWeightedResponse){
		
		SaveAnswerResponseEvent saveAnswerResponseEvent = new SaveAnswerResponseEvent();
		saveAnswerResponseEvent.setRiskAnswerId(riskWeightedResponse.getOID());
		saveAnswerResponseEvent.setAction(riskWeightedResponse.getAction());
		saveAnswerResponseEvent.setAnswerEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(riskWeightedResponse.getCreateTimestamp()));
		saveAnswerResponseEvent.setAnswerText(riskWeightedResponse.getResponse());
		saveAnswerResponseEvent.setSortOrder(String.valueOf(riskWeightedResponse.getSortOrder()));
		
		saveAnswerResponseEvent.setSubordinateQuestionId(riskWeightedResponse.getSubordinateQuestionId());
		if (saveAnswerResponseEvent.getSubordinateQuestionId() != null 
			&& saveAnswerResponseEvent.getSubordinateQuestionId().length() > ZERO
			&& !saveAnswerResponseEvent.getSubordinateQuestionId().equals(PDConstants.BLANK)) 
		{
			RiskQuestions question = RiskQuestions.find(saveAnswerResponseEvent.getSubordinateQuestionId());
			if (question != null){
				saveAnswerResponseEvent.setSubordinateQuestionName(question.getQuestionName());
			}
		}
		
		saveAnswerResponseEvent.setWeight(String.valueOf(riskWeightedResponse.getWeight()));
		
		return saveAnswerResponseEvent;
	}
	
	public static GetQuestionResponseEvent getQuestionResponseEvent (RiskQuestions riskQuestion){
		
		GetQuestionResponseEvent riskQuestionsResponseEvent = new GetQuestionResponseEvent();
		riskQuestionsResponseEvent.setAllowsFutureDates(riskQuestion.isAllowsFutureDates());
		riskQuestionsResponseEvent.setAssessmentType(riskQuestion.getAssessmentType());
		riskQuestionsResponseEvent.setControlCode(riskQuestion.getControlCode());
		if (riskQuestion.getControlCode() != null && !riskQuestion.getControlCode().equals(PDConstants.BLANK)){
			RiskAnalysisControlCode code = RiskAnalysisControlCode.find(riskQuestion.getControlCode());
			if (code != null){
				riskQuestionsResponseEvent.setControlCodeDesc(code.getName());
			}
		}
		riskQuestionsResponseEvent.setDefaultToSystemDate(riskQuestion.isDefaultToSystemDate());
		riskQuestionsResponseEvent.setCollapsibleHeader(riskQuestion.isCollapsibleHeader());
		riskQuestionsResponseEvent.setHardcoded(riskQuestion.isQuestionTextNotModifiable());
		riskQuestionsResponseEvent.setHelpFileId(riskQuestion.getHelpFileId());
		riskQuestionsResponseEvent.setInitialAction(riskQuestion.getInitialAction());
		riskQuestionsResponseEvent.setNumeric(riskQuestion.isNumeric());
		riskQuestionsResponseEvent.setQuestionId(String.valueOf(riskQuestion.getOID()));
		riskQuestionsResponseEvent.setQuestionText(riskQuestion.getQuestionText());
		riskQuestionsResponseEvent.setQuestionName(riskQuestion.getQuestionName());
		riskQuestionsResponseEvent.setRequired(riskQuestion.isRequired());
		riskQuestionsResponseEvent.setAllowPrint(riskQuestion.isAllowPrint());
		if (riskQuestion.getRiskCategoryId() != null && !riskQuestion.getRiskCategoryId().equals(PDConstants.BLANK)){
			riskQuestionsResponseEvent.setRiskCategoryId(Integer.parseInt(riskQuestion.getRiskCategoryId()));
		}
		riskQuestionsResponseEvent.setUiControlType(riskQuestion.getUiControlType());
		if (riskQuestion.getQuestionNbr() != null){
			riskQuestionsResponseEvent.setUiDisplayOrder(riskQuestion.getQuestionNbr());
			riskQuestionsResponseEvent.setUiDisplayOrderAsInt(Integer.parseInt(riskQuestionsResponseEvent.getUiDisplayOrder()));
		} else {
			riskQuestionsResponseEvent.setUiDisplayOrder(PDConstants.BLANK);
			riskQuestionsResponseEvent.setUiDisplayOrderAsInt(0);
		}
		riskQuestionsResponseEvent.setQuestonEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(riskQuestion.getCreateTimestamp()));
		if (riskQuestion.getUpdateTimestamp() != null){
			riskQuestionsResponseEvent.setQuestionUpdateDate(RiskAnalysisConstants.DATE_FORMAT.format(riskQuestion.getUpdateTimestamp()));
		}
	
		return riskQuestionsResponseEvent;
	}
	
	public static GetAnswerResponseEvent getAnswerResponseEvent (RiskWeightedResponse riskWeightedResponse){

		GetAnswerResponseEvent re = new GetAnswerResponseEvent();
		re.setRiskAnswerId(riskWeightedResponse.getOID());
		re.setAction(riskWeightedResponse.getAction());
		re.setResponse(riskWeightedResponse.getResponse());
		re.setRiskQuestionId(Integer.parseInt(riskWeightedResponse.getRiskQuestionId()));
		re.setSuggestedCasePlainDomiainId(riskWeightedResponse.getSuggestedCasePlanDomainId());
		re.setWeight(riskWeightedResponse.getWeight());
		re.setSortOrder(riskWeightedResponse.getSortOrder());
		re.setSubordinateQuestionId(riskWeightedResponse.getSubordinateQuestionId());
		RiskQuestions question = null;		
		if (re.getRiskQuestionId() > 0 ) 
		{
			question = RiskQuestions.find(String.valueOf(re.getRiskQuestionId()));
			re.setRiskQuestionName(question.getQuestionName());
			
		}
		
		if (riskWeightedResponse.getSubordinateQuestionId() != null
				&& !riskWeightedResponse.getSubordinateQuestionId().equalsIgnoreCase(PDConstants.BLANK)
				&& !riskWeightedResponse.getSubordinateQuestionId().equals("0")) 
		{
			question = RiskQuestions.find(re.getSubordinateQuestionId());
			if (question != null){
				re.setSubordinateQuestionName(question.getQuestionName());
			} 
		}
		
		re.setAnswerEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(riskWeightedResponse.getCreateTimestamp()));
		question = null;

		return re;
	}
	
	public static List getAnswerResponseEvents (List <RiskWeightedResponse>  answers){
		
		RiskWeightedResponse answer = null;
		GetAnswerResponseEvent re = null;
		List reList = new ArrayList();
		for (int i = 0; i < answers.size(); i++) {
			answer = answers.get(i);
			re = getAnswerResponseEvent(answer);
			reList.add(re);
		}
		answer = null;
		re = null;

		return reList;
	}
}
