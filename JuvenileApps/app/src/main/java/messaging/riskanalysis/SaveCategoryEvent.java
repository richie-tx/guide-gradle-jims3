package messaging.riskanalysis;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveCategoryEvent extends CompositeRequest
{
	private String categoryId;
	private String riskResultGroupId;
    private String categoryName;
    private String description;
    private String modificationReason;
	public String getRiskResultGroupId() {
		return riskResultGroupId;
	}
	public void setRiskResultGroupId(String riskResultGroupId) {
		this.riskResultGroupId = riskResultGroupId;
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

}
