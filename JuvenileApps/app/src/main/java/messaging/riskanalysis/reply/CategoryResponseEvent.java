package messaging.riskanalysis.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class CategoryResponseEvent extends ResponseEvent{
	private String categoryId;
	private String formulaId;
	private String riskResultGroupId;
	private String riskResultGroupDesc;
	private String version;
	private String categoryName;
	private String description;
	private String modificationReason;
	private Date entryDate;
	private Date modifyDate;
	private String formulaStatusCd;
	private boolean updatable;
	private boolean totalCategoriesTiedToFormulaGreaterThan1;
	
	public String getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	public String getRiskResultGroupId() {
		return riskResultGroupId;
	}
	public void setRiskResultGroupId(String riskResultGroupId) {
		this.riskResultGroupId = riskResultGroupId;
	}
	public String getRiskResultGroupDesc() {
		return riskResultGroupDesc;
	}
	public void setRiskResultGroupDesc(String riskResultGroupDesc) {
		this.riskResultGroupDesc = riskResultGroupDesc;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModificationReason() {
		return modificationReason;
	}
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public void setFormulaStatusCd(String formulaStatusCd) {
		this.formulaStatusCd = formulaStatusCd;
	}
	public String getFormulaStatusCd() {
		return formulaStatusCd;
	}
	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}
	public boolean isUpdatable() {
		return updatable;
	}
	public void setTotalCategoriesTiedToFormulaGreaterThan1(
			boolean totalCategoriesTiedToFormulaGreaterThan1) {
		this.totalCategoriesTiedToFormulaGreaterThan1 = totalCategoriesTiedToFormulaGreaterThan1;
	}
	public boolean isTotalCategoriesTiedToFormulaGreaterThan1() {
		return totalCategoriesTiedToFormulaGreaterThan1;
	}
	
}
