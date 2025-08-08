package messaging.organization;

import mojo.km.messaging.RequestEvent;

public class GetDivisionsProgramsForAgencyEvent extends RequestEvent{
    private String agencyId;

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
