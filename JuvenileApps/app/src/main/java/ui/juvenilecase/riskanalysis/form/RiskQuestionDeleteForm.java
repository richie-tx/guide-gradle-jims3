package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.objects.Question;
/**
 * @author palcocer
 *
 */
public class RiskQuestionDeleteForm extends ActionForm
{
	
	//Lists -
	private List questions;
	private List currentQuestions;
	private List removeQuestions;
	private List controlCodes;
	private List answerList = new ArrayList(); //Answers
	//Question
	private Question question = new Question();
	//Strings
	private String pageType;
	private String categoryId;
	
	public RiskQuestionDeleteForm()
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
    	//Lists -
    	getAnswerList().clear();
    	
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

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	public List getAnswerList() {
		return answerList;
	}

	/**
	 * @return the currentQuestions
	 */
	public List getCurrentQuestions() {
		return currentQuestions;
	}

	/**
	 * @param currentQuestions the currentQuestions to set
	 */
	public void setCurrentQuestions(List currentQuestions) {
		this.currentQuestions = currentQuestions;
	}

	/**
	 * @return the removeQuestions
	 */
	public List getRemoveQuestions() {
		return removeQuestions;
	}

	/**
	 * @param removeQuestions the removeQuestions to set
	 */
	public void setRemoveQuestions(List removeQuestions) {
		this.removeQuestions = removeQuestions;
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

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}
	
	
}
