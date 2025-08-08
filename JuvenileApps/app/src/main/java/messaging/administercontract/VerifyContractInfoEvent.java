//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\VerifyContractInfoEvent.java

package messaging.administercontract;

import mojo.km.messaging.RequestEvent;

public class VerifyContractInfoEvent extends RequestEvent 
{
   private String contractId;
   private String action;
   private String contractName;
   private String totalValue;
   private String serviceProviderValue;
   
   /**
    * @roseuid 451C4FB202CB
    */
   public VerifyContractInfoEvent() 
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
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the contractName.
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * @param contractName The contractName to set.
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * @return Returns the totalValue.
	 */
	public String getTotalValue() {
		return totalValue;
	}
	/**
	 * @param totalValue The totalValue to set.
	 */
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	/**
	 * @return Returns the serviceProviderValue.
	 */
	public String getServiceProviderValue() {
		return serviceProviderValue;
	}
	/**
	 * @param serviceProviderValue The serviceProviderValue to set.
	 */
	public void setServiceProviderValue(String serviceProviderValue) {
		this.serviceProviderValue = serviceProviderValue;
	}
}
