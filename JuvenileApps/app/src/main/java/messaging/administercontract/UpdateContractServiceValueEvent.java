//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\UpdateContractServiceEvent.java

package messaging.administercontract;

import mojo.km.messaging.RequestEvent;

public class UpdateContractServiceValueEvent extends RequestEvent
{
	private String contractId;
	private String serviceProviderValue;
	private String contractServiceId;
	   
   /**
    * @roseuid 451C4FB200A8
    */
   public UpdateContractServiceValueEvent() 
   {
    
   }
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractServiceId.
	 */
	public String getContractServiceId() {
		return contractServiceId;
	}
	/**
	 * @param contractServiceId The contractServiceId to set.
	 */
	public void setContractServiceId(String contractServiceId) {
		this.contractServiceId = contractServiceId;
	}

	/**
	 * @return Returns the serviceProvidervalue.
	 */
	public String getServiceProviderValue() {
		return serviceProviderValue;
	}
	/**
	 * @param serviceProvidervalue The serviceProvidervalue to set.
	 */
	public void setServiceProviderValue(String serviceProvidervalue) {
		this.serviceProviderValue = serviceProvidervalue;
	}
}
