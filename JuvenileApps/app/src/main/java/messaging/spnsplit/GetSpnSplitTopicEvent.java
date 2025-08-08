//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnsplit\\ProcessSpnSplitEvent.java

package messaging.spnsplit;

import mojo.km.messaging.RequestEvent;

public class GetSpnSplitTopicEvent extends RequestEvent 
{
    private String defendantId;

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}
