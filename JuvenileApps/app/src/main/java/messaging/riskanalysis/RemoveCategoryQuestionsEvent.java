package messaging.riskanalysis;

import mojo.km.messaging.Composite.CompositeRequest;

public class RemoveCategoryQuestionsEvent extends CompositeRequest {
	private String categoryId;

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}
}
