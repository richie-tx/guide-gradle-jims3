package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseloadByDefendentsAndProgramUnitEvent extends RequestEvent
{
    private String defendantIds; 
    private String programUnit;
	public String getDefendantIds() {
		return defendantIds;
	}
	public void setDefendantIds(String defendantIds) {
		this.defendantIds = defendantIds;
	}
	public String getProgramUnit() {
		return programUnit;
	}
	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}
}
