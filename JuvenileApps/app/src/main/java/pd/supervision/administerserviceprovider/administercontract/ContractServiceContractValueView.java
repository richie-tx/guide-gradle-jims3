//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\ServiceContractValue.java

package pd.supervision.administerserviceprovider.administercontract;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * This entity will represent the amount of a Contract's total value that has been 
 * allotted to a particular Service for a ServiceProvider.
 */
public class ContractServiceContractValueView extends Contract
{
   private String serviceProviderValue;
   private String serviceId;
   private String contractId;
   private String contractServiceId;
   
   /**
    * @roseuid 451D32640030
    */
   public ContractServiceContractValueView() 
   {
    
   }
   
	/**
	* @return pd.supervision.administerserviceprovider.administercontract.ServiceContractValue
	* @param serviceContractValueId
	* @roseuid 4107B06D01B5
	*/
	static public ContractServiceContractValueView findContractServiceValue(String serviceContractValueId)
	{
		IHome home = new Home();
		return (ContractServiceContractValueView) home.find(serviceContractValueId, ContractServiceContractValueView.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, ContractServiceContractValueView.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName,attrValue,ContractServiceContractValueView.class);
	}
		
	/**
	* @roseuid 4107B06D01BB
	*/
	public void create(){
		IHome home = new Home();
		home.bind(this);
	}

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		fetch();
		return contractId;
	}
	
	/**
	 * @param contractId 
	 * The contractId to set.
	 */
	public void setContractId(String contractId) {
		if (this.contractId == null || !this.contractId.equals("")) {
			markModified();
		}
		this.contractId = contractId;
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
		if (this.serviceId == null || !this.serviceId.equals("")) {
			markModified();
		}
		this.serviceId = serviceId;
	}
	
	/**
	 * @return Returns the serviceProviderValue.
	 */
	public String getServiceProviderValue() {
		fetch();
		return serviceProviderValue;
	}
	
	/**
	 * @param serviceProviderValue The serviceProviderValue to set.
	 */
	public void setServiceProviderValue(String serviceProviderValue) {
		if (this.serviceProviderValue == null || !this.serviceProviderValue.equals("")) {
			markModified();
		}
		this.serviceProviderValue = serviceProviderValue;
	}
	
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
		if (this.contractServiceId == null || !this.contractServiceId.equals(contractServiceId)) {
			markModified();
		}
		this.setOID(contractServiceId);
		this.contractServiceId = contractServiceId;
	}
}
