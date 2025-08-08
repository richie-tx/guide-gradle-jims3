package ui.juvenilecase.riskanalysis;

import java.util.ArrayList;
import java.util.List;

import ui.juvenilecase.riskanalysis.form.objects.Answer;
import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;

public class RiskAnalysisHelper 
{

	public RiskAnalysisHelper() {}
	
	public static void setSubordinateQuestionName(
			List<GetQuestionResponseEvent> questions, Answer answer) {
		
		if (answer.getSubordinateQuestionId() != null 
				&& answer.getSubordinateQuestionId().length() > 0) 
		{
			for (GetQuestionResponseEvent subordinateQuestion : questions) 
			{
				if (answer.getSubordinateQuestionId().equalsIgnoreCase(subordinateQuestion.getQuestionId())) {
					answer.setSubordinateQuestionName(subordinateQuestion.getQuestionName());
				}
			}
		}
		
	}
	
	public static void removeAnswerFromList(List answerList,
			String currentAddressPosition) {
		
		answerList.remove( Integer.parseInt(currentAddressPosition) );
	}
	
	public static List removeSubordinateQuestions(List<GetQuestionResponseEvent> questions
			, List<GetAnswerResponseEvent> answers) {
		
		List questionsFiltered = new ArrayList(questions); 
		for (GetQuestionResponseEvent questionResponseEvent : questions) 
		{
			for (GetAnswerResponseEvent answerResponseEvent : answers) 
			{
				if (questionResponseEvent.getQuestionId()
						.equalsIgnoreCase(answerResponseEvent.getSubordinateQuestionId())) 
				{
					questionsFiltered.remove(questionResponseEvent);
				}
			}
		}
		
		return questionsFiltered;
	}
	
	public static List removeSubordinateQuestions(
			List<GetQuestionResponseEvent> questions, 
			List<GetAnswerResponseEvent> answers, 
			String selectedAnswerId) {
		
		List questionsFiltered = new ArrayList(questions); 
		for (GetQuestionResponseEvent questionResponseEvent : questions) 
		{
			for (GetAnswerResponseEvent answerResponseEvent : answers) 
			{
				if (!answerResponseEvent.getRiskAnswerId().equals(selectedAnswerId) && questionResponseEvent.getQuestionId()
						.equalsIgnoreCase(answerResponseEvent.getSubordinateQuestionId())) 
				{
					questionsFiltered.remove(questionResponseEvent);
				}
			}
		}
		
		return questionsFiltered;
	}

	public static String getControlCodeName(
			List controlCodes,
			String controlCode) 
	{		
		
		if (controlCode != null && controlCode.length() > 0) 
		{
			List<RiskAnalysisControlCodeResponseEvent> controlCodesList = controlCodes;
			for (RiskAnalysisControlCodeResponseEvent controlCodeEvent : controlCodesList) 
			{
				RiskAnalysisControlCodeResponseEvent riskAnalysisControlCodeResponseEvent = controlCodeEvent; 
				if (controlCode.equalsIgnoreCase(controlCodeEvent.getCode())) 
	        	{
					return riskAnalysisControlCodeResponseEvent.getName();
	        	}
			}
		}
		
		return null;
	};
	
}
