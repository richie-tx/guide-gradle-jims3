package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeByDefendantIdsEvent extends RequestEvent
{
    private String defendantIds;

    /**
     * @roseuid 46435F2003D4
     */
    public GetSuperviseeByDefendantIdsEvent()
    {

    }

	public String getDefendantIds() {
		return defendantIds;
	}

	public void setDefendantIds(String defendantIds) {
		this.defendantIds = defendantIds;
	}    
}
