package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class RiskFormulaCreateForm extends ActionForm {

	private String activationDate;
	private String assessmentTypeId;
	private Date entryDate;
	private String formulaName;
	private String pageType;
	private String riskTypeId;
	private String riskTypeDesc;
	private String statusDesc;	
	private String statusId;
	private String version;

	private String[] selectedValues;

	private List riskResultGroups;
	private List assessmentTypes;
	private List selectableCategoriesList;
	private List selectedCategoriesList;
	
	private String emptyStr = "";
 
    public void clear()
	{
    	this.activationDate = emptyStr;
//    	this.entryDate = emptyStr;
    	this.formulaName = emptyStr;
    	this.riskTypeId = emptyStr;
    	this.riskTypeDesc = emptyStr;
    	this.statusDesc = emptyStr;
    	this.statusId = emptyStr;
    	this.version = emptyStr;
    	this.assessmentTypes = new ArrayList();
    	this.selectableCategoriesList = new ArrayList();
    	this.selectedCategoriesList = new ArrayList();    	
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	public String getAssessmentTypeId() {
		return assessmentTypeId;
	}

	public void setAssessmentTypeId(String assessmentTypeId) {
		this.assessmentTypeId = assessmentTypeId;
	}

	public List getAssessmentTypes() {
		return assessmentTypes;
	}

	public void setAssessmentTypes(List assessmentTypes) {
		this.assessmentTypes = assessmentTypes;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
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

	public List getRiskResultGroups() {
		return riskResultGroups;
	}

	public void setRiskResultGroups(List riskResultGroups) {
		this.riskResultGroups = riskResultGroups;
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