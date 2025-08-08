package messaging.riskanalysis;

public class UpdateCategoryQuestionAnswerEvent extends SaveAnswerEvent {
	
	private String riskCategoryId;

	public String getRiskCategoryId() {
		return riskCategoryId;
	}

	public void setRiskCategoryId(String riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

}
