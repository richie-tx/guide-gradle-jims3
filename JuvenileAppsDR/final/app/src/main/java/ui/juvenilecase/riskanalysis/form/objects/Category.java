/**
 * 
 */
package ui.juvenilecase.riskanalysis.form.objects;

import java.util.Date;

/**
 * @author dwilliamson
 *
 */
public class Category {
	
	private String categoryDescription;
	private String categoryId;
	private String categoryName;
	private Date entryDate;
	private Date modificatoinDate;
	private String modificationReason;
	private String riskResultGroup;
	private String riskResultGroupId;
	private String version;
	private String formulaStatusCd;
	private boolean updatable;
	private boolean totalCategoriesTiedToFormulaGreaterThan1;
	
	public Category() {
	}

	/**
	 * @return the categoryDescription
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}

	/**
	 * @param categoryDescription the categoryDescription to set
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the modificatoinDate
	 */
	public Date getModificatoinDate() {
		return modificatoinDate;
	}

	/**
	 * @param modificatoinDate the modificatoinDate to set
	 */
	public void setModificatoinDate(Date modificatoinDate) {
		this.modificatoinDate = modificatoinDate;
	}

	/**
	 * @return the modificationReason
	 */
	public String getModificationReason() {
		return modificationReason;
	}

	/**
	 * @param modificationReason the modificationReason to set
	 */
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}

	/**
	 * @return the riskResultGroup
	 */
	public String getRiskResultGroup() {
		return riskResultGroup;
	}

	/**
	 * @param riskResultGroup the riskResultGroup to set
	 */
	public void setRiskResultGroup(String riskResultGroup) {
		this.riskResultGroup = riskResultGroup;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the riskResultGroupId
	 */
	public String getRiskResultGroupId() {
		return riskResultGroupId;
	}

	/**
	 * @param riskResultGroupId the riskResultGroupId to set
	 */
	public void setRiskResultGroupId(String riskResultGroupId) {
		this.riskResultGroupId = riskResultGroupId;
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
