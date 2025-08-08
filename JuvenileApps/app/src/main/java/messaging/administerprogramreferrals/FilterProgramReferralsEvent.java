/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FilterProgramReferralsEvent extends RequestEvent 
{
	String defendantId;
	List referralStatusCodes;
	String criminalCaseId;
	
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

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
	/**
	 * @return Returns the referralStatusCodes.
	 */
	public List getReferralStatusCodes() {
		return referralStatusCodes;
	}
	/**
	 * @param referralStatusCodes The referralStatusCodes to set.
	 */
	public void setReferralStatusCodes(List referralStatusCodes) {
		this.referralStatusCodes = referralStatusCodes;
	}
}
