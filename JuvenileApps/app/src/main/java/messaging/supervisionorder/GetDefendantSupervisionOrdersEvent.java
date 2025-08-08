package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetDefendantSupervisionOrdersEvent extends RequestEvent 
{
	String defendantId;

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}
