//Source file: C:\\views\\dev\\app\\src\\messaging\\contact\\GetPartyInfoEvent.java

package messaging.party;

import mojo.km.messaging.RequestEvent;

public class GetTasksByDefendantIdsEvent extends RequestEvent
{
	public String defendantIds;
	/**
	 * @roseuid 416D2E3A016E
	 */
	public GetTasksByDefendantIdsEvent()
	{
	}
	public String getDefendantIds() {
		return defendantIds;
	}
	public void setDefendantIds(String defendantIds) {
		this.defendantIds = defendantIds;
	}
}
