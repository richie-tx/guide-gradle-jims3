package ui.juvenilecase.riskanalysis.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.juvenilecase.riskanalysis.form.objects.Question;
//import ui.juvenilecase.riskanalysis.form.objects.Answer;
/**
 * @author palcocer
 *
 */
public class RiskQuestionUpdateForm extends ActionForm
{
	//Lists -
	private List questions;
	private List controlCodes;
	private List uiControlTypes;
	
	//Question
	private Question question = new Question();
	
	//Strings
	private String pageType;

	public RiskQuestionUpdateForm()
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
    
    public void clearForm() 
    {
    	
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
    	getQuestion().setModificationDate(null);
    	getQuestion().setModificationReason(null);
    	getQuestion().setRiskCategoryId(null);
    	getQuestion().setAllowPrint(null);
    }

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	/**
	 * @return the pageType
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param pageType the pageType to set
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * @return the uiControlTypes
	 */
	public List getUiControlTypes() {
		return uiControlTypes;
	}

	/**
	 * @param uiControlTypes the uiControlTypes to set
	 */
	public void setUiControlTypes(List uiControlTypes) {
		this.uiControlTypes = uiControlTypes;
	}
	
	
}
