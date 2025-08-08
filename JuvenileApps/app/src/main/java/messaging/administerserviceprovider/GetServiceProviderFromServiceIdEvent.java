//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServiceLocationsEvent.java

package messaging.administerserviceprovider;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderFromServiceIdEvent extends RequestEvent
{
	private Collection serviceIdList;

	/**
	 * @roseuid 44805C9400DF
	 */
	public GetServiceProviderFromServiceIdEvent()
	{

	}


	/**
	 * @return Returns the serviceIdList.
	 */
	public Collection getServiceIdList() {
		return serviceIdList;
	}
	/**
	 * @param serviceIdList The serviceIdList to set.
	 */
	public void setServiceIdList(Collection serviceIdList) {
		this.serviceIdList = serviceIdList;
	}
}