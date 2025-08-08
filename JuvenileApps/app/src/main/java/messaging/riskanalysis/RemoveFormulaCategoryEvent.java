package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class RemoveFormulaCategoryEvent extends RequestEvent
{
	private String categoryId;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
}
