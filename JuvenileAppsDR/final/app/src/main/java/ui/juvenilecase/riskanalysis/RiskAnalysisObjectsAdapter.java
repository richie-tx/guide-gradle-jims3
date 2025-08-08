package ui.juvenilecase.riskanalysis;

import java.util.ArrayList;
import java.util.List;

import messaging.riskanalysis.SaveAnswerEvent;
import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import messaging.riskanalysis.reply.SaveQuestionResponseEvent;
import messaging.riskanalysis.reply.SaveAnswerResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.ResponseEvent;
import ui.common.UIUtil;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public final class RiskAnalysisObjectsAdapter {
	
	public RiskAnalysisObjectsAdapter() {};
	
	/** 
	 * @param requestEvent
	 * @param question
	 * @param answerList
	 * @return RequestEvent
	 */
	public static RequestEvent createAnswerEventObject(RequestEvent requestEvent, List<Answer> answerList) {
		
		if (requestEvent instanceof SaveAnswerEvent) 
		{
			return setSaveAnswerRequestEventFromFormObject((SaveAnswerEvent)requestEvent, answerList);
		} 
		
		return null;
		
	}
	
	/** 
	 * @param List
	 * @return Question
	 */
	public static List<Answer> createAnswerObjects(List answerList) {
		//Only first object is examined, lets us know which private method to call
		for (Object object : answerList) 
		{
			if (object instanceof GetAnswerResponseEvent) 
				return setAnswerFromGetAnswerResponseEvent(answerList);
		    else  if (object instanceof SaveAnswerResponseEvent)
		    	return setAnswerFromSaveAnswerResponseEvent(answerList);
		}
		return null;
	}

	/** 
	 * @param ResponseEvent
	 * @return Question
	 */
	public static Question createQuestionObject(ResponseEvent responseEvent) {
		
		if (responseEvent instanceof SaveQuestionResponseEvent) 
			return setQuestionFromSaveQuestionResponseEvent((SaveQuestionResponseEvent)responseEvent);
		else if (responseEvent instanceof GetQuestionResponseEvent) 
			return setQuestionFromGetQuestionResponseEvent((GetQuestionResponseEvent)responseEvent);
		
		return null;
		
	}
	
	/** 
	 * @param requestEvent
	 * @param question
	 * @param answerList
	 * @return RequestEvent
	 */
	public static RequestEvent createQuestionEventObject(RequestEvent requestEvent, Question question, List<Answer> answerList) {
		
		if (requestEvent instanceof SaveQuestionEvent) 
			return setSaveQuestionRequestEventFromFormObject((SaveQuestionEvent)requestEvent, question, answerList);
		
		return null;
		
	}
	
	private static SaveQuestionEvent setSaveAnswerRequestEventFromFormObject(SaveAnswerEvent saveAnswerEvent, List<Answer> answerList) 
	{
		
		if (saveAnswerEvent.isACreate())
		{
			for (Answer answer: answerList) 
	    	{
	    		SaveAnswerEvent individualSaveAnswerEvent = new SaveAnswerEvent();
	    		setSaveAnswerEventAttributes(saveAnswerEvent.getRiskQuestionId(), answer,
						individualSaveAnswerEvent);
	    		individualSaveAnswerEvent.setACreate(true);
	    		saveAnswerEvent.addRequest(individualSaveAnswerEvent);
	    	}
		}
		else
		{
			Answer answer = new Answer();
			for (Answer a: answerList) 
				answer = a;
			
			setSaveAnswerEventAttributes(saveAnswerEvent.getRiskQuestionId(), 
					answer, saveAnswerEvent);
		}
		
		return null;
	}

	private static void setSaveAnswerEventAttributes(
			String riskQuestionId, Answer answer,
			SaveAnswerEvent saveAnswerEvent) {
		
		saveAnswerEvent.setAnswerEntryDate(answer.getAnswerEntryDate());
		saveAnswerEvent.setRiskQuestionId(riskQuestionId);
		saveAnswerEvent.setRiskAnswerId(answer.getRiskAnswerId());
		saveAnswerEvent.setAction(answer.getAction());
		saveAnswerEvent.setAnswerText(answer.getAnswerText());
		saveAnswerEvent.setSortOrder(answer.getSortOrder());
		saveAnswerEvent.setSubordinateQuestionId(answer.getSubordinateQuestionId());
		saveAnswerEvent.setWeight(answer.getWeight());
		saveAnswerEvent.setCreateUserID(UIUtil.getCurrentUserID());
		
	}
	
	private static SaveQuestionEvent setSaveQuestionRequestEventFromFormObject(SaveQuestionEvent saveQuestionEvent, Question question, List<Answer> answerList) 
	{
		saveQuestionEvent.setRiskQuestionId(question.getRiskQuestionId());
		saveQuestionEvent.setAllowFutureDates(question.getAllowFutureDates());
    	//saveQuestionsEvent.setAnswerSource(riskQuestionUpdateForm.getAnswerSource());
		saveQuestionEvent.setCollapsibleHeader(question.getCollapsibleHeader());
		saveQuestionEvent.setDefaultToSystemDate(question.getDefaultToSystemDate());
		saveQuestionEvent.setControlCode(question.getControlCode());
   		saveQuestionEvent.setHardcoded(question.getHardcoded());
   		saveQuestionEvent.setNumericOnly(question.getNumericOnly());
    	saveQuestionEvent.setQuestionInitialAction(question.getQuestionInitialAction());
    	saveQuestionEvent.setQuestionName(question.getQuestionName());
    	saveQuestionEvent.setQuestionText(question.getQuestionText());
    	saveQuestionEvent.setQuestonEntryDate(question.getQuestonEntryDate());
    	saveQuestionEvent.setRequired(question.getRequired());
    	saveQuestionEvent.setUiControlType(question.getUiControlType());
    	saveQuestionEvent.setUiDisplayOrder(question.getUiDisplayOrder());
    	saveQuestionEvent.setCreateUserID(UIUtil.getCurrentUserID());
    	saveQuestionEvent.setRiskCategoryId(question.getRiskCategoryId());
    	saveQuestionEvent.setUiDisplayOrder(question.getUiDisplayOrder());
    	saveQuestionEvent.setAllowPrint(question.getAllowPrint());
    	
    	//Place Answers within Question Event
    	
    	if (saveQuestionEvent.isACreate())
    	{
	    	for (Answer answer: answerList) 
	    	{
	    		SaveAnswerEvent saveAnswerEvent = new SaveAnswerEvent();
	        	saveAnswerEvent.setAnswerEntryDate(answer.getAnswerEntryDate());
	        	saveAnswerEvent.setAction(answer.getAction());
	        	saveAnswerEvent.setAnswerText(answer.getAnswerText());
	        	saveAnswerEvent.setSortOrder(answer.getSortOrder());
	        	saveAnswerEvent.setSubordinateQuestionId(answer.getSubordinateQuestionId());
	        	saveAnswerEvent.setWeight(answer.getWeight());
	        	saveAnswerEvent.setCreateUserID(UIUtil.getCurrentUserID());
	        	
	        	saveQuestionEvent.addRequest(saveAnswerEvent);
	    	}
    	}
		
		return saveQuestionEvent;
	}

	private static Question setQuestionFromSaveQuestionResponseEvent (SaveQuestionResponseEvent saveQuestionResponseEvent)
	{
		Question question = new Question();
		
		question.setRiskQuestionId(saveQuestionResponseEvent.getRiskQuestionId());
		
		question.setDefaultToSystemDate(saveQuestionResponseEvent.getDefaultToSystemDate());
		question.setAllowFutureDates(saveQuestionResponseEvent.getAllowFutureDates());
    	//riskQuestionUpdateForm.setAnswerSource(saveQuestionResponseEvent.getAnswerSource());
   		question.setCollapsibleHeader(saveQuestionResponseEvent.getCollapsibleHeader());
    	question.setControlCode(saveQuestionResponseEvent.getControlCode());
   		question.setHardcoded(saveQuestionResponseEvent.getHardcoded());
   		question.setNumericOnly(saveQuestionResponseEvent.getNumericOnly());
    	question.setQuestionInitialAction(saveQuestionResponseEvent.getQuestionInitialAction());
    	question.setQuestionName(saveQuestionResponseEvent.getQuestionName());
    	question.setQuestionText(saveQuestionResponseEvent.getQuestionText());
    	question.setQuestonEntryDate(saveQuestionResponseEvent.getQuestonEntryDate());
   		question.setRequired(saveQuestionResponseEvent.getRequired());
    	question.setUiControlType(saveQuestionResponseEvent.getUiControlType());
    	question.setUiDisplayOrder(saveQuestionResponseEvent.getUiDisplayOrder());
    	question.setRiskCategoryId(saveQuestionResponseEvent.getRiskCategoryId());
    	question.setAllowPrint(saveQuestionResponseEvent.getAllowPrint());
    	
    	return question;
	}
	private static final String TRUE_STRING_NEW = "true";
	private static final String FALSE_STRING_NEW = "false";
	private static final String ZERO_STRING = "0";
	
	private static Question setQuestionFromGetQuestionResponseEvent (GetQuestionResponseEvent getQuestionResponseEvent)
	{
		Question question = new Question();
		
		question.setRiskQuestionId(getQuestionResponseEvent.getQuestionId());
		if (getQuestionResponseEvent.isNumeric()) {
			question.setNumericOnly(TRUE_STRING_NEW);
    	} else {
    		question.setNumericOnly(FALSE_STRING_NEW);
    	}
		if (getQuestionResponseEvent.isDefaultToSystemDate()) {
			question.setDefaultToSystemDate(TRUE_STRING_NEW);
    	} else {
    		question.setDefaultToSystemDate(FALSE_STRING_NEW);
    	}
		if (getQuestionResponseEvent.isAllowsFutureDates()) {
			question.setAllowFutureDates(TRUE_STRING_NEW);
    	} else {
    		question.setAllowFutureDates(FALSE_STRING_NEW);
    	}
    	//riskQuestionCreateForm.setAnswerSource(saveQuestionResponseEvent.getAnswerSource());
    	if (getQuestionResponseEvent.isCollapsibleHeader()) {
    		question.setCollapsibleHeader(TRUE_STRING_NEW);
    	} else {
    		question.setCollapsibleHeader(FALSE_STRING_NEW);
    	}
    	
    	question.setControlCode(getQuestionResponseEvent.getControlCode());
    	
    	if (getQuestionResponseEvent.isHardcoded()) {
    		question.setHardcoded(TRUE_STRING_NEW);
    	} else {
    		question.setHardcoded(FALSE_STRING_NEW);
    	}
    	question.setQuestionInitialAction(getQuestionResponseEvent.getInitialAction());
    	question.setQuestionName(getQuestionResponseEvent.getQuestionName());
    	question.setQuestionText(getQuestionResponseEvent.getQuestionText());
    	question.setQuestonEntryDate(getQuestionResponseEvent.getQuestonEntryDate());
    	
    	if (getQuestionResponseEvent.isRequired()) {
    		question.setRequired(TRUE_STRING_NEW);
    	} else {
    		question.setRequired(FALSE_STRING_NEW);
    	}
    	
    	if (getQuestionResponseEvent.isAllowPrint()) {
		question.setAllowPrint(TRUE_STRING_NEW);
	} else {
		question.setAllowPrint(FALSE_STRING_NEW);
	}
    	question.setUiControlType(getQuestionResponseEvent.getUiControlType());
    	question.setUiDisplayOrder(getQuestionResponseEvent.getUiDisplayOrder());
    	String aString = Integer.toString(getQuestionResponseEvent.getRiskCategoryId());
    	if (!aString.equals(ZERO_STRING)){
    		question.setRiskCategoryId(String.valueOf(getQuestionResponseEvent.getRiskCategoryId()));
    	}
    	aString = null;
    	return question;
    	
	}
	
	private static List<Answer> setAnswerFromGetAnswerResponseEvent(List<GetAnswerResponseEvent> answerList) 
	{
		List<Answer> answers = new ArrayList();
    	for (GetAnswerResponseEvent getAnswerResponseEvent: answerList) 
    	{
    		Answer answer = new Answer();
    		answer.setRiskAnswerId(getAnswerResponseEvent.getRiskAnswerId());
        	answer.setAnswerEntryDate(getAnswerResponseEvent.getAnswerEntryDate());
        	answer.setAction(getAnswerResponseEvent.getAction());
        	answer.setAnswerText(getAnswerResponseEvent.getResponse());
        	answer.setSortOrder(String.valueOf(getAnswerResponseEvent.getSortOrder()));
        	answer.setSubordinateQuestionId(getAnswerResponseEvent.getSubordinateQuestionId());
        	answer.setSubordinateQuestionName(getAnswerResponseEvent.getSubordinateQuestionName());
        	answer.setWeight(String.valueOf(getAnswerResponseEvent.getWeight()));
        	answers.add(answer);
    	}
    	
		return answers;
	}
	
	private static List<Answer> setAnswerFromSaveAnswerResponseEvent(List<SaveAnswerResponseEvent> answerList) 
	{
		List<Answer> answers = new ArrayList();
    	for (SaveAnswerResponseEvent saveAnswerResponseEvent: answerList) 
    	{
    		Answer answer = new Answer();
    		answer.setAnswerEntryDate(saveAnswerResponseEvent.getAnswerEntryDate());
        	answer.setAction(saveAnswerResponseEvent.getAction());
        	answer.setAnswerText(saveAnswerResponseEvent.getAnswerText());
        	answer.setSortOrder(saveAnswerResponseEvent.getSortOrder());
        	answer.setSubordinateQuestionId(saveAnswerResponseEvent.getSubordinateQuestionId());
        	answer.setSubordinateQuestionName(saveAnswerResponseEvent.getSubordinateQuestionName());
        	answer.setWeight(saveAnswerResponseEvent.getWeight());
        	answers.add(answer);
    	}
    	
		return answers;
	}
	
	
}
