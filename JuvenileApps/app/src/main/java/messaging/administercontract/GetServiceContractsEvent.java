//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\GetServiceContractsEvent.java

package messaging.administercontract;

import mojo.km.messaging.RequestEvent;

public class GetServiceContractsEvent extends RequestEvent 
{
    /**
    * @roseuid 451C4FB1000B
    */
   public GetServiceContractsEvent() 
   {
    
   }
   
    private String serviceId;
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
