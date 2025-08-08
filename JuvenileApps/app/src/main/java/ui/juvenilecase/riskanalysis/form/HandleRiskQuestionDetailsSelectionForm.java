package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import ui.juvenilecase.riskanalysis.form.objects.Category;
import ui.juvenilecase.riskanalysis.form.objects.Question;

import org.apache.struts.action.ActionForm;
/**
 * @author palcocer
 *
 */
public class HandleRiskQuestionDetailsSelectionForm extends ActionForm
{
	
	//Category
	private Category category = new Category();
	
	//Question -
	private Question question = new Question();
	
	//Answers
	private List answerList = new ArrayList();
	
	//Control Codes 
	private List controlCodes = new ArrayList();
	
	//Attributes
	private String selectedRiskAnswerId;
	private String[] selectedRiskAnswerIds;
	
	//general
	private String updateType;
	private String pageAsPopUp;
	
	public HandleRiskQuestionDetailsSelectionForm()
	{
		
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
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
		if (answerList != null)
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
    	
    	//Other
    	pageAsPopUp = null;
    	
    }

	public void setSelectedRiskAnswerId(String selectedRiskAnswerId) {
		this.selectedRiskAnswerId = selectedRiskAnswerId;
	}

	public String getSelectedRiskAnswerId() {
		return selectedRiskAnswerId;
	}

	/**
	 * @return the updateType
	 */
	public String getUpdateType() {
		return updateType;
	}

	/**
	 * @param updateType the updateType to set
	 */
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	/**
	 * @return the pageAsPopUp
	 */
	public String getPageAsPopUp() {
		return pageAsPopUp;
	}
	/**
	 * @param pageAsPopUp the pageAsPopUp to set
	 */
	public void setPageAsPopUp(String pageAsPopUp) {
		this.pageAsPopUp = pageAsPopUp;
	}
	public String[] getSelectedRiskAnswerIds() {
		return selectedRiskAnswerIds;
	}
	public void setSelectedRiskAnswerIds(String[] selectedRiskAnswerIds) {
		this.selectedRiskAnswerIds = selectedRiskAnswerIds;
	}
	
}
