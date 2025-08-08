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
public class RiskQuestionCreateForm extends ActionForm
{
	
	//Lists -
	private List questions;
	private List controlCodes;
	private List newAnswerList = new ArrayList(); //Answers
	//Question
	private Question question = new Question();
	//Answer
	private Answer currentAnswer = new Answer();
	//Misc
	private String selectedValue;

	public RiskQuestionCreateForm()
	{
		
	}

	public void setQuestions(List questions) {
		this.questions = questions;
	}

	public List getQuestions() {
		return questions;
	}

	public void setControlCodes(List controlCodes) {
		this.controlCodes = controlCodes;
	}

	public List getControlCodes() {
		return controlCodes;
	}

	public void setCurrentAnswer(Answer currentAnswer) {
		this.currentAnswer = currentAnswer;
	}

	public Answer getCurrentAnswer() {
		return currentAnswer;
	}
	
	public void clearCurrentAnswer()
    {
        setCurrentAnswer(new Answer());
    }

	public void setNewAnswerList(List newAnswerList) {
		this.newAnswerList = newAnswerList;
	}

	public List getNewAnswerList() {
		return newAnswerList;
	}
	
    public String getSelectedValue()
    {
        return selectedValue;
    }

    public void setSelectedValue(String string)
    {
        selectedValue = string;
    }
    
    public void clearForm() 
    {
    	//Lists -
    	newAnswerList.clear();
    	
    	//Question -
    	getQuestion().setQuestionName(null);
    	getQuestion().setQuestonEntryDate(null);
    	getQuestion().setQuestionText(null);
    	getQuestion().setUiControlType(null);
    	getQuestion().setUiControlTypeDesc(null);
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
    	
    	//Answer
    	clearCurrentAnswer();
    	
    	//Misc
    	selectedValue = null;
    	
    }

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}
	
}
