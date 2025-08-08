/*
 * Created on Jan 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.criminalcase;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCasesByCriminalCaseIdsEvent extends RequestEvent
{
	private String criminalCaseIds;

	public String getCriminalCaseIds() {
		return criminalCaseIds;
	}

	public void setCriminalCaseIds(String criminalCaseIds) {
		this.criminalCaseIds = criminalCaseIds;
	}	
}
