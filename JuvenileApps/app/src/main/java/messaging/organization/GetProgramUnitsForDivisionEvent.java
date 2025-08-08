package messaging.organization;

import mojo.km.messaging.RequestEvent;

public class GetProgramUnitsForDivisionEvent extends RequestEvent 
{
	private String divisionId;

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	
	
}
