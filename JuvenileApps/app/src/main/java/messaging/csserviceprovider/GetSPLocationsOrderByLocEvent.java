package messaging.csserviceprovider;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetSPLocationsOrderByLocEvent extends SearchServiceProviderEvent
{
	private List serviceProviderIds;
	private List referralTypeCds;
	
	
	/**
	 * @return the serviceProviderIds
	 */
	public List getServiceProviderIds() {
		return serviceProviderIds;
	}
	/**
	 * @param serviceProviderIds the serviceProviderIds to set
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
