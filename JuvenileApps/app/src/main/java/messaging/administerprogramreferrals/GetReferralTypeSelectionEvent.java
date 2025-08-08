/*
 * Created on Mar 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetReferralTypeSelectionEvent extends RequestEvent 
{
	private String criminalCaseId;
	
	
	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
}
