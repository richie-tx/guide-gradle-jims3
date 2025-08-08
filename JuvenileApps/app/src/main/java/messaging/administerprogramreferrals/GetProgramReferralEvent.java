/*
 * Created on Apr 23, 2008
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
public class GetProgramReferralEvent extends RequestEvent 
{
	private String programReferralId;	
	private boolean isViewDetails;
	
	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the isViewDetails
	 */
	public boolean isViewDetails() {
		return isViewDetails;
	}
	/**
	 * @param isViewDetails the isViewDetails to set
	 */
	public void setViewDetails(boolean isViewDetails) {
		this.isViewDetails = isViewDetails;
	}
}
