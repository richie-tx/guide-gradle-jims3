/*
 * Created on Oct 15, 2007
 *
 */
package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author awidjaja
 *
 */
public class GetCommonAppDocByOIDEvent extends RequestEvent {
	private String commonAppDocId;
	
	public String getCommonAppDocId() {
		return commonAppDocId;
	}
	public void setCommonAppDocId(String commonAppDocId) {
		this.commonAppDocId = commonAppDocId;
	}
}
