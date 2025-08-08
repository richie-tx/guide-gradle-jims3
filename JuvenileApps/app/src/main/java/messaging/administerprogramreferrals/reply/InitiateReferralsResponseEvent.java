/*
 * Created on Apr 17, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.reply;

import java.util.Hashtable;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InitiateReferralsResponseEvent extends ResponseEvent 
{
	List serviceProviderRefTypeResponses;
	Hashtable savedReferralMap;
	
	/**
	 * @return Returns the savedReferralMap.
	 */
	public Hashtable getSavedReferralMap() {
		return savedReferralMap;
	}
	/**
	 * @param savedReferralMap The savedReferralMap to set.
	 */
	public void setSavedReferralMap(Hashtable savedReferralMap) {
		this.savedReferralMap = savedReferralMap;
	}
	/**
	 * @return Returns the serviceProviderRefTypeResponses.
	 */
	public List getServiceProviderRefTypeResponses() {
		return serviceProviderRefTypeResponses;
	}
	/**
	 * @param serviceProviderRefTypeResponses The serviceProviderRefTypeResponses to set.
	 */
	public void setServiceProviderRefTypeResponses(List serviceProviderRefTypeResponses) {
		this.serviceProviderRefTypeResponses = serviceProviderRefTypeResponses;
	}
}
