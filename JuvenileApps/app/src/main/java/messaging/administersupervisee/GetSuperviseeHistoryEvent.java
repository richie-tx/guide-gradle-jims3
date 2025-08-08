/**
 * 
 */
package messaging.administersupervisee;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class GetSuperviseeHistoryEvent extends RequestEvent 
{
	private String superviseeHistoryId;

	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}

	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}
	
	
}
