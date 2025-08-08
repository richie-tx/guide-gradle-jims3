/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import java.util.List;


/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchByReferralTypesEvent extends SearchServiceProviderEvent 
{
	List referralTypes;
	
	
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
