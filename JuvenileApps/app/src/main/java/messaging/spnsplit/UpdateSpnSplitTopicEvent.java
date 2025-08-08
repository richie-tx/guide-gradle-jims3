//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnsplit\\ProcessSpnSplitEvent.java

package messaging.spnsplit;

import mojo.km.messaging.RequestEvent;

public class UpdateSpnSplitTopicEvent extends RequestEvent 
{
    private String errDefendantId;
    private String validDefendantId;
    private String[] topicIds;
	
    
	public String getErrDefendantId() {
		return errDefendantId;
	}
	public void setErrDefendantId(String errDefendantId) {
		this.errDefendantId = errDefendantId;
	}
	public String getValidDefendantId() {
		return validDefendantId;
	}
	public void setValidDefendantId(String validDefendantId) {
		this.validDefendantId = validDefendantId;
	}
	public String[] getTopicIds() {
		return topicIds;
	}
	public void setTopicIds(String[] topicIds) {
		this.topicIds = topicIds;
	}	
}
