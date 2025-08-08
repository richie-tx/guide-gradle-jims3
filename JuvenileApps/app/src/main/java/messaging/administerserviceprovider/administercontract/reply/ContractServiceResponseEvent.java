/*
 * Create on September 29, 2006
 */

package messaging.administerserviceprovider.administercontract.reply;

import messaging.administerserviceprovider.reply.ServiceResponseEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractServiceResponseEvent extends ServiceResponseEvent implements Comparable
{	
   private double serviceProviderValue;
   private String contractServiceId;
   private String serviceId;
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
	/**
	 * @return Returns the serviceProviderValue.
	 */
	public double getServiceProviderValue() {
		return serviceProviderValue;
	}
	/**
	 * @param serviceProviderValue The serviceProviderValue to set.
	 */
	public void setServiceProviderValue(double serviceProviderValue) {
		this.serviceProviderValue = serviceProviderValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		/*if (o == null){
			return -1;
		}
		ContractServiceResponseEvent c = (ContractServiceResponseEvent)o;
		if (c.getServiceName() == null){
			return -1;
		}		
		if (this.getServiceName() == null){
			return 1;
		}
		return this.getServiceName().compareToIgnoreCase(c.getServiceName()); */
		return 0;
	}	
}