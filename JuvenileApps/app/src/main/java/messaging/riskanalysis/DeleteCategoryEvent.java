package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class DeleteCategoryEvent extends RequestEvent
{
	private String categoryId;

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}
	
}
