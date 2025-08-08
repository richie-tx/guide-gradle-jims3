/*
 * Created on Mar 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.reply;

import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferralTypeSelectionResponseEvent extends ResponseEvent 
{
	private List referralTypes;
	private List orderConditions;
	
	
	/**
	 * @return Returns the orderConditions.
	 */
	public List getOrderConditions() {
		return orderConditions;
	}
	/**
	 * @param orderConditions The orderConditions to set.
	 */
	public void setOrderConditions(List orderConditions) {
		this.orderConditions = orderConditions;
	}
	/**
	 * @return Returns the referralTypes.
	 */
	public List getReferralTypes() {
		return referralTypes;
	}
	/**
	 * @param referralTypes The referralTypes to set.
	 */
	public void setReferralTypes(List referralTypes) {
		this.referralTypes = referralTypes;
	}
}
