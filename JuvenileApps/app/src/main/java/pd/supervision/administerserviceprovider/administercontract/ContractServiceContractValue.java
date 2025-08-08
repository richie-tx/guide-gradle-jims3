//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\Contract.java

package pd.supervision.administerserviceprovider.administercontract;

import pd.supervision.administerserviceprovider.Service;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * A Provider Program may be funded through a contract. Some service providers
 * have contracts to be paid in CSCD. The contracts are traced to the funding
 * source to make payments accordingly.
 */
public class ContractServiceContractValue extends Service {
	/**
	 * Properties for contract
	 */

	private String contractServiceId;
	private String serviceId;


	/**
	 * @return Returns the contractServiceId.
	 */
	public String getContractServiceId() {
		fetch();
		return contractServiceId;
	}
	/**
	 * @param contractServiceId The contractServiceId to set.
	 */
	public void setContractServiceId(String contractServiceId) {
		if(this.contractServiceId == null || !this.contractServiceId.equals("")){
			markModified();
		}
		this.setOID(contractServiceId);
		this.contractServiceId = contractServiceId;
	}
	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId() {
		fetch();
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(String serviceId) {
		if(this.serviceId == null || !this.serviceId.equals("")){
			markModified();
		}
		this.serviceId = serviceId;
	}
}
