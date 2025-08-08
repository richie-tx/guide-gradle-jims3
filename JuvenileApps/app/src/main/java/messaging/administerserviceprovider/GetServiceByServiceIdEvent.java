//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetVendorContactEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceByServiceIdEvent extends RequestEvent 
{
   private String serviceId;
   
   /**
    * @roseuid 447458710020
    */
   public GetServiceByServiceIdEvent() 
   {
    
   }
	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
}
