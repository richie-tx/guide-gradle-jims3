package ui.juvenilecase.riskanalysis.form;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class RiskCategorySearchForm extends ActionForm {
	
	public RiskCategorySearchForm()
	{
		
	}
	private String formulaId;
	private String group1Id;
	private String group2Id;
	private String riskCategoryId;
	private String selectedValue;
	
	public int riskResultGroupId;
	
	private Collection groups;
	private Collection group2;
	
	private List controlCodes;
	private List formulas;
	private List questions;
	private List riskCategories;
	private List riskResultGroups;
	
	/**
	 * @return the riskCategories
	 */
	public List getRiskCategories() {
		return riskCategories;
	}

	/**
	 * @param riskCategories the riskCategories to set
	 */
	public void setRiskCategories(List riskCategories) {
		this.riskCategories = riskCategories;
	}

	/**
	 * @return the riskResultGroupId
	 */
	public int getRiskResultGroupId() {
		return riskResultGroupId;
	}

	/**
	 * @param riskResultGroupId the riskResultGroupId to set
	 */
	public void setRiskResultGroupId(int riskResultGroupId) {
		this.riskResultGroupId = riskResultGroupId;
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
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}


	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
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
	 * @return the controlCodes
	 */
	public List getControlCodes() {
		return controlCodes;
	}

	/**
	 * @param controlCodes the controlCodes to set
	 */
	public void setControlCodes(List controlCodes) {
		this.controlCodes = controlCodes;
	}

	/**
	 * @return the formulas
	 */
	public List getFormulas() {
		return formulas;
	}

	/**
	 * @param formulas the formulas to set
	 */
	public void setFormulas(List formulas) {
		this.formulas = formulas;
	}

	/**
	 * @return the formulaId
	 */
	public String getFormulaId() {
		return formulaId;
	}

	/**
	 * @param formulaId the formulaId to set
	 */
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	/**
	 * @return the group1Id
	 */
	public String getGroup1Id() {
		return group1Id;
	}

	/**
	 * @param group1Id the group1Id to set
	 */
	public void setGroup1Id(String group1Id) {
		this.group1Id = group1Id;
	}

	/**
	 * @return the group2Id
	 */
	public String getGroup2Id() {
		return group2Id;
	}

	/**
	 * @param group2Id the group2Id to set
	 */
	public void setGroup2Id(String group2Id) {
		this.group2Id = group2Id;
	}

	/**
	 * @return the groups
	 */
	public Collection getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Collection groups) {
		this.groups = groups;
	}

	/**
	 * @return the group2
	 */
	public Collection getGroup2() {
		return group2;
	}

	/**
	 * @param group2 the group2 to set
	 */
	public void setGroup2(Collection group2) {
		this.group2 = group2;
	}

	/**
	 * @return the riskCategoryId
	 */
	public String getRiskCategoryId() {
		return riskCategoryId;
	}

	/**
	 * @param riskCategoryId the riskCategoryId to set
	 */
	public void setRiskCategoryId(String riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}
	
}