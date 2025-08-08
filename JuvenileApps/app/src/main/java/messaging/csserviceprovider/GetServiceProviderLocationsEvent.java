/*
 * Created on Apr 10, 2008
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
public class GetServiceProviderLocationsEvent extends SearchServiceProviderEvent 
{
	List serviceProviderIds;
	List referralTypeCds;
	
	/**
	 * @return Returns the serviceProviderIds.
	 */
	public List getServiceProviderIds() {
		return serviceProviderIds;
	}
	/**
	 * @param serviceProviderIds The serviceProviderIds to set.
	 */
	public void setServiceProviderIds(List serviceProviderIds) {
		this.serviceProviderIds = serviceProviderIds;
	}
	/**
	 * @return the referralTypeCds
	 */
	public List getReferralTypeCds() {
		return referralTypeCds;
	}
	/**
	 * @param referralTypeCds the referralTypeCds to set
	 */
	public void setReferralTypeCds(List referralTypeCds) {
		this.referralTypeCds = referralTypeCds;
	}
	
}
