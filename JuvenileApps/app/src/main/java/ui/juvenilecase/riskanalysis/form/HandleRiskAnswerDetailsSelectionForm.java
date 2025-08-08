package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;
/**
 * @author palcocer
 *
 */
public class HandleRiskAnswerDetailsSelectionForm extends ActionForm
{
	
	
	//Answers
	private List answerList = new ArrayList();
	
	//Question -
	private Question question = new Question();
	//Answer
	private Answer currentAnswer = new Answer();
	//Control Codes 
	private List controlCodes = new ArrayList();
	
	//Attributes
	private String selectedRiskAnswerId;
	
	public HandleRiskAnswerDetailsSelectionForm()
	{
		
	}

	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	public List getAnswerList() {
		return answerList;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setControlCodes(List controlCodes) {
		this.controlCodes = controlCodes;
	}

	public List getControlCodes() {
		return controlCodes;
	}
	
	public void clearAnswers() 
	{
		answerList.clear();
	}
	
	public void clearForm() 
    {
		//Attributes
		setSelectedRiskAnswerId(null);
		
    	//Lists -
		answerList.clear();
    	
    	//Question -
    	getQuestion().setQuestionName(null);
    	getQuestion().setQuestonEntryDate(null);
    	getQuestion().setQuestionText(null);
    	getQuestion().setUiControlType(null);
    	getQuestion().setCollapsibleHeader(null);
    	getQuestion().setUiDisplayOrder(null);
    	getQuestion().setAllowFutureDates(null);
    	getQuestion().setNumericOnly(null);
    	getQuestion().setQuestionInitialAction(null);
    	getQuestion().setRequired(null);
    	getQuestion().setControlCode(null);
    	getQuestion().setDefaultToSystemDate(null);
    	getQuestion().setAnswerSource(null);
    	getQuestion().setHardcoded(null);
    	getQuestion().setRiskCategoryId(null);
    	getQuestion().setAllowPrint(null);
    	
    }

	public void setSelectedRiskAnswerId(String selectedRiskAnswerId) {
		this.selectedRiskAnswerId = selectedRiskAnswerId;
	}

	public String getSelectedRiskAnswerId() {
		return selectedRiskAnswerId;
	}
	
	public void setCurrentAnswer(Answer currentAnswer) {
		this.currentAnswer = currentAnswer;
	}

	public Answer getCurrentAnswer() {
		return currentAnswer;
	}
	
}
