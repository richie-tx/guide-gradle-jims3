package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.juvenilecase.riskanalysis.form.objects.Category;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class RiskCategoryUpdateForm extends ActionForm {
	
	private List categories;
	private List newQuestionList = new ArrayList(); //Questions
	private List questionList = new ArrayList(); //Questions
	private List questions;
	private List riskResultGroups;
	
	private Category category = new Category();
	private Question currentQuestion = new Question();
	
	//Misc
	private String[] selectedValue;
	private String actionType;
	private String secondaryAction;
	private String pageType;

	public RiskCategoryUpdateForm()
	{
		
	}
	
	public void clearForm() 
    {
    	//Lists -
    	newQuestionList = new ArrayList();
    	questions = new ArrayList();;
    	categories = new ArrayList();;
    	
    	//Category -
    	getCategory().setCategoryName(null);
    	getCategory().setEntryDate(null);
    	getCategory().setCategoryDescription(null);
    	getCategory().setRiskResultGroup(null);
    	getCategory().setRiskResultGroupId(null);
    	getCategory().setCategoryId(null);
    	getCategory().setVersion(null);
    	
    	//Strings
    	setActionType(null);
    	setSecondaryAction(null);
    	setPageType(null);
    }	
	
	/**
	 * @return the categories
	 */
	public List getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List categories) {
		this.categories = categories;
	}

	/**
	 * @return the newQuestionList
	 */
	public List getNewQuestionList() {
		return newQuestionList;
	}

	/**
	 * @param newQuestionList the newQuestionList to set
	 */
	public void setNewQuestionList(List newQuestionList) {
		this.newQuestionList = newQuestionList;
	}

	/**
	 * @return the questions
	 */
	public List getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List questions) {
		this.questions = questions;
	}

	/**
	 * @return the questionList
	 */
	public List getQuestionList() {
		return questionList;
	}

	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(List questionList) {
		this.questionList = questionList;
	}

	/**
	 * @return the riskResultGroups
	 */
	public List getRiskResultGroups() {
		return riskResultGroups;
	}

	/**
	 * @param riskResultGroups the riskResultGroups to set
	 */
	public void setRiskResultGroups(List riskResultGroups) {
		this.riskResultGroups = riskResultGroups;
	}

	/**
	 * @return the currentCategory
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param currentCategory the currentCategory to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the currentQuestion
	 */
	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	/**
	 * @param currentQuestion the currentQuestion to set
	 */
	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	/**
	 * @return the selectedValue
	 */
	public String[] getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String[] selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
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

}
