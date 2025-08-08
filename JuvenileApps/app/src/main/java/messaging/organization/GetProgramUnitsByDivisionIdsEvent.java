package messaging.organization;

import mojo.km.messaging.RequestEvent;

public class GetProgramUnitsByDivisionIdsEvent extends RequestEvent{
    private String divisionIds;

	public String getDivisionIds() {
		return divisionIds;
	}

	public void setDivisionIds(String divisionIds) {
		this.divisionIds = divisionIds;
	};
}
