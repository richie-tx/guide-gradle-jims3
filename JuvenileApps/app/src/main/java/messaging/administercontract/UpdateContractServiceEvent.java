//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\UpdateContractServiceEvent.java

package messaging.administercontract;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateContractServiceEvent extends CompositeRequest 
{
	private String serviceId;
	   
   /**
    * @roseuid 451C4FB200A8
    */
   public UpdateContractServiceEvent() 
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
