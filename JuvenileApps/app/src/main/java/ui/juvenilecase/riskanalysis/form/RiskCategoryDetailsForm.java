package ui.juvenilecase.riskanalysis.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.juvenilecase.riskanalysis.form.objects.Category;

public class RiskCategoryDetailsForm extends ActionForm {

	//general variables
	private String[] selectedValues;
	private String actionType;
	private String secondaryAction;
	
	private String categoryName;
	private Category category = new Category();
	private List questionsList;
	private String updateType;
	public void clear() 
    {
    	getCategory().setCategoryName(null);
    	getCategory().setEntryDate(null);
    	getCategory().setCategoryDescription(null);
    	getCategory().setModificationReason(null);
    	getCategory().setRiskResultGroup(null);
    	getCategory().setRiskResultGroupId(null);
    	getCategory().setCategoryId(null);
    	getCategory().setVersion(null);
    	getCategory().setFormulaStatusCd(null);
    	getCategory().setUpdatable(false);
    }

	/**
	 * @return the selectedValues
	 */
	public String[] getSelectedValues() {
		return selectedValues;
	}

	/**
	 * @param selectedValues the selectedValues to set
	 */
	public void setSelectedValues(String[] selectedValues) {
		this.selectedValues = selectedValues;
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	 * @return the questionsList
	 */
	public List getQuestionsList() {
		return questionsList;
	}

	/**
	 * @param questionsList the questionsList to set
	 */
	public void setQuestionsList(List questionsList) {
		this.questionsList = questionsList;
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

	
}
