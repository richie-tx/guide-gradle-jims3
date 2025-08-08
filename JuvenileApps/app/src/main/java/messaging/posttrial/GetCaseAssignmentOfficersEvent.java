package messaging.posttrial;

import mojo.km.messaging.RequestEvent;

public class GetCaseAssignmentOfficersEvent extends RequestEvent {

	private String organizationId;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
}
