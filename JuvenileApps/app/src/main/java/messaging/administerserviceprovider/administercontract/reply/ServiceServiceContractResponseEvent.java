/*
 * Create on September 29, 2006
 */
/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerserviceprovider.administercontract.reply;

public class ServiceServiceContractResponseEvent extends ContractResponseEvent implements Comparable
{	
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
    
	private String contractServiceId;
    private String serviceId;
}