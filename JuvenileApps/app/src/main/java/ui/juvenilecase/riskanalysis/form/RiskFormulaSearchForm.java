package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class RiskFormulaSearchForm extends ActionForm {


	private String assessmentId;
	private String formulaId;
	private String selectedValue;
	private String resultsMessage;
	
	
	private List assessmentTypesList;
	private List formulasList;
	private List availableAssessmentTypesList; //used for formula create
	private List searchResultsList;
	
	private String emptyStr = "";
	private List emptyList = new ArrayList();
 
    public void clear()
	{
    	this.assessmentId = emptyStr;
    	this.formulaId = emptyStr;
    	this.selectedValue = emptyStr;
    	this.resultsMessage = emptyStr;
    	this.assessmentTypesList = emptyList;
    	this.availableAssessmentTypesList = emptyList;
    	this.searchResultsList = emptyList;
	}
	
	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	public List getAssessmentTypesList() {
		return assessmentTypesList;
	}
	public void setAssessmentTypesList(List assessmentTypesList) {
		this.assessmentTypesList = assessmentTypesList;
	}
	public List getAvailableAssessmentTypesList() {
		return availableAssessmentTypesList;
	}
	public void setAvailableAssessmentTypesList(List availableAssessmentTypesList) {
		this.availableAssessmentTypesList = availableAssessmentTypesList;
	}
	public String getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	public List getFormulasList() {
		return formulasList;
	}
	public void setFormulasList(List formulasList) {
		this.formulasList = formulasList;
	}

	public String getSelectedValue() {
		return selectedValue;
	}
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	public String getResultsMessage() {
		return resultsMessage;
	}
	public void setResultsMessage(String resultsMessage) {
		this.resultsMessage = resultsMessage;
	}
	public List getSearchResultsList() {
		return searchResultsList;
	}
	public void setSearchResultsList(List searchResultsList) {
		this.searchResultsList = searchResultsList;
	}
}