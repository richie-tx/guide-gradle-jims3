/*
 * Created on Apr 1, 2008
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
public class UpdateProgramReferralsEvent extends RequestEvent 
{
	private String action;
	
	private List referralsList;

	/**
	 * @return the referralsList
	 */
	public List getReferralsList() {
		return referralsList;
	}

	/**
	 * @param referralsList the referralsList to set
	 */
	public void setReferralsList(List referralsList) {
		this.referralsList = referralsList;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
