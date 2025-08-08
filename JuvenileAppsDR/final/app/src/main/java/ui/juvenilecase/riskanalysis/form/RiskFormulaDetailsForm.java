package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.juvenilecase.riskanalysis.form.objects.Category;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class RiskFormulaDetailsForm extends ActionForm {

	private String activationDateStr;
	private String categoryName;
	private String confirmMessage;
	private String emptyStr = "";
	private String entryDate;
	private String formulaId;
	private String formulaName;
	private String pageType;
	private String questionIsSubordinate;  //flow control variable 
	private String riskTypeId;
	private String riskTypeDesc;
	private String removeType;
	private String riskAssessmentType;
	private String selectedCategoryId;
	private String selectedRecommendationId;
	private String statusDesc;	
	private String statusId;
	private String version;
	
	private boolean formulaUpdatable; 
	
	private Category category = new Category();
	private Question question = new Question();
	
	private String[] selectedValues;
	
	private List emptyList = new ArrayList();
	private List answerList;
	private List currentCategoriesList;
	private List currentFormulaList;
	private List processList;
	private List questionsList;
	private List recommendationsList;
	private List removedCategoriesList;
	private List riskAnalysisDetailsList;
	private List riskResultGroups;
	private List selectableCategoriesList;
	private List selectedCategoriesList;
	
    public void clear()
	{
    	this.activationDateStr = emptyStr;
    	this.confirmMessage = emptyStr;
    	this.entryDate = emptyStr;
    	this.formulaName = emptyStr;
    	this.pageType = emptyStr;
    	this.riskTypeId = emptyStr;
    	this.riskTypeDesc = emptyStr;
    	this.removeType = emptyStr;
    	this.riskAssessmentType = emptyStr;
    	this.selectedCategoryId = emptyStr;
    	this.selectedRecommendationId = emptyStr;
    	this.statusDesc = emptyStr;
    	this.statusId = emptyStr;
    	this.version = emptyStr;
    	this.answerList = emptyList;
    	this.currentCategoriesList = emptyList;
    	this.currentFormulaList = emptyList;
    	this.processList = emptyList;
    	this.questionsList = emptyList;
    	this.removedCategoriesList = emptyList;
    	this.recommendationsList = emptyList;
    	this.selectableCategoriesList = emptyList;
    	this.selectedCategoriesList = emptyList;    	
	}

	public void clearCategory() 
    {
    	getCategory().setCategoryName(null);
    	getCategory().setEntryDate(null);
    	getCategory().setCategoryDescription(null);
//    	getCategory().setModificationReason(null);
    	getCategory().setRiskResultGroup(null);
    	getCategory().setRiskResultGroupId(null);
    	getCategory().setCategoryId(null);
    	getCategory().setVersion(null);
    	getCategory().setFormulaStatusCd(null);
    	
    }
	
	
	public List getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	public String getActivationDateStr() {
		return activationDateStr;
	}

	public void setActivationDateStr(String activationDateStr) {
		this.activationDateStr = activationDateStr;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getConfirmMessage() {
		return confirmMessage;
	}

	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}

	public List getCurrentCategoriesList() {
		return currentCategoriesList;
	}

	public void setCurrentCategoriesList(List currentCategoriesList) {
		this.currentCategoriesList = currentCategoriesList;
	}

	public List getCurrentFormulaList() {
		return currentFormulaList;
	}

	public void setCurrentFormulaList(List currentFormulaList) {
		this.currentFormulaList = currentFormulaList;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public boolean isFormulaUpdatable() {
		return formulaUpdatable;
	}

	public void setFormulaUpdatable(boolean formulaUpdatable) {
		this.formulaUpdatable = formulaUpdatable;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getQuestionIsSubordinate() {
		return questionIsSubordinate;
	}

	public void setQuestionIsSubordinate(String questionIsSubordinate) {
		this.questionIsSubordinate = questionIsSubordinate;
	}

	public List getProcessList() {
		return processList;
	}

	public void setProcessList(List processList) {
		this.processList = processList;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List questionsList) {
		this.questionsList = questionsList;
	}

	public List getRemovedCategoriesList() {
		return removedCategoriesList;
	}

	public void setRemovedCategoriesList(List removedCategoriesList) {
		this.removedCategoriesList = removedCategoriesList;
	}

	public String getRemoveType() {
		return removeType;
	}

	public void setRemoveType(String removeType) {
		this.removeType = removeType;
	}
	
	public String getRiskAssessmentType() {
		return riskAssessmentType;
	}

	public void setRiskAssessmentType(String riskAssessmentType) {
		this.riskAssessmentType = riskAssessmentType;
	}

	public List getRiskResultGroups() {
		return riskResultGroups;
	}

	public void setRiskResultGroups(List riskResultGroups) {
		this.riskResultGroups = riskResultGroups;
	}

	public String getRiskTypeId() {
		return riskTypeId;
	}

	public void setRiskTypeId(String riskTypeId) {
		this.riskTypeId = riskTypeId;
	}

	public String getRiskTypeDesc() {
		return riskTypeDesc;
	}

	public void setRiskTypeDesc(String riskTypeDesc) {
		this.riskTypeDesc = riskTypeDesc;
	}

	public String getSelectedRecommendationId() {
		return selectedRecommendationId;
	}

	public void setSelectedRecommendationId(String selectedRecommendationId) {
		this.selectedRecommendationId = selectedRecommendationId;
	}

	public String getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(String selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}

	public String[] getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(String[] selectedValues) {
		this.selectedValues = selectedValues;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List getRecommendationsList() {
		return recommendationsList;
	}

	public void setRecommendationsList(List recommendationsList) {
		this.recommendationsList = recommendationsList;
	}

	public List getRiskAnalysisDetailsList() {
		return riskAnalysisDetailsList;
	}

	public void setRiskAnalysisDetailsList(List riskAnalysisDetailsList) {
		this.riskAnalysisDetailsList = riskAnalysisDetailsList;
	}

	public List getSelectableCategoriesList() {
		return selectableCategoriesList;
	}

	public void setSelectableCategoriesList(List selectableCategoriesList) {
		this.selectableCategoriesList = selectableCategoriesList;
	}

	public List getSelectedCategoriesList() {
		return selectedCategoriesList;
	}

	public void setSelectedCategoriesList(List selectedCategoriesList) {
		this.selectedCategoriesList = selectedCategoriesList;
	}
   
}