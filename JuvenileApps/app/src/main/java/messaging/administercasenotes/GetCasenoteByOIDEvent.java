/*
 * Created on May 7, 2007
 */
package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

/**
 * @author rcapestani
 */
public class GetCasenoteByOIDEvent extends RequestEvent {
   
	private String casenoteId = "";

	public String getCasenoteId() {
		return casenoteId;
	}

	public void setCasenoteId(String casenoteId) {
		this.casenoteId = casenoteId;
	}
	
	
}
