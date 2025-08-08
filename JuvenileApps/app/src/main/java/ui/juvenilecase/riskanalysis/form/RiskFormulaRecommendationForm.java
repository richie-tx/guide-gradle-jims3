package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class RiskFormulaRecommendationForm extends ActionForm {


	private String confirmMessage;
	private String entryDate;
	private String formulaId;
	private String formulaName;
	private String fromPage;
	private String inCustodyId;
	private String maxScore;
	private String minScore;
	private String pageType;
	private String recommendationId;
	private String recommendationName;
	private String recommendationText;
	private String riskResultGroupDesc;
	private String riskResultGroupId;
	
	private Boolean recommendationUpdatable;
	
	private List currentList;
	private List emptyList = new ArrayList();
	private List removeList;
	private List riskResultGroupList;
	
    public void clear()
	{
    	this.confirmMessage = "";
    	this.entryDate = "";
    	this.formulaName = "";
    	this.fromPage = "";
    	this.inCustodyId = "";
    	this.maxScore = "";
    	this.minScore = "";
    	this.pageType = "";
    	this.recommendationName = "";
    	this.recommendationText = "";
    	this.riskResultGroupDesc = "";
    	this.riskResultGroupId = "";
    	this.currentList = emptyList;
    	this.removeList = emptyList;
	}

	public String getConfirmMessage() {
		return confirmMessage;
	}

	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
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

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public String getInCustodyId() {
		return inCustodyId;
	}

	public void setInCustodyId(String inCustodyId) {
		this.inCustodyId = inCustodyId;
	}

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}

	public String getRecommendationName() {
		return recommendationName;
	}

	public void setRecommendationName(String recommendationName) {
		this.recommendationName = recommendationName;
	}

	public String getRecommendationText() {
		return recommendationText;
	}

	public void setRecommendationText(String recommendationText) {
		this.recommendationText = recommendationText;
	}

	public Boolean getRecommendationUpdatable() {
		return recommendationUpdatable;
	}

	public void setRecommendationUpdatable(Boolean recommendationUpdatable) {
		this.recommendationUpdatable = recommendationUpdatable;
	}

	public String getRiskResultGroupDesc() {
		return riskResultGroupDesc;
	}

	public void setRiskResultGroupDesc(String riskResultGroupDesc) {
		this.riskResultGroupDesc = riskResultGroupDesc;
	}

	public String getRiskResultGroupId() {
		return riskResultGroupId;
	}

	public void setRiskResultGroupId(String riskResultGroupId) {
		this.riskResultGroupId = riskResultGroupId;
	}

	public List getCurrentList() {
		return currentList;
	}

	public void setCurrentList(List currentList) {
		this.currentList = currentList;
	}

	public List getRemoveList() {
		return removeList;
	}

	public void setRemoveList(List removeList) {
		this.removeList = removeList;
	}

	public List getRiskResultGroupList() {
		return riskResultGroupList;
	}

	public void setRiskResultGroupList(List riskResultGroupList) {
		this.riskResultGroupList = riskResultGroupList;
	}
    
}