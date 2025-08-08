package messaging.riskanalysis;

import mojo.km.messaging.Composite.CompositeRequest;

public class RemoveCategoryQuestionAnswerEvent extends CompositeRequest {
	private String riskCategoryId;
	private String riskQuestionId;

	public String getRiskCategoryId() {
		return riskCategoryId;
	}

	public void setRiskCategoryId(String riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

	public String getRiskQuestionId() {
		return riskQuestionId;
	}

	public void setRiskQuestionId(String riskQuestionId) {
		this.riskQuestionId = riskQuestionId;
	}
}
