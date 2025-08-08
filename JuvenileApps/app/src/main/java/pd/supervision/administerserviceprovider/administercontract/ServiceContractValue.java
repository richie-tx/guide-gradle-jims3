//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\ServiceContractValue.java

package pd.supervision.administerserviceprovider.administercontract;

import java.util.Iterator;

import messaging.administercontract.UpdateContractServiceEvent;
import messaging.administercontract.UpdateContractServiceValueEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

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
public class ServiceContractValue extends PersistentObject
{
   private Double serviceProviderValue;
   private String serviceId;
   private String contractId;
   private String contractServiceId;
   
   /**
    * @roseuid 451D32640030
    */
   public ServiceContractValue() 
   {
    
   }
   
	/**
	* @return pd.supervision.administerserviceprovider.administercontract.ServiceContractValue
	* @param serviceContractValueId
	* @roseuid 4107B06D01B5
	*/
	static public ServiceContractValue find(String serviceContractValueId)
	{
		IHome home = new Home();
		return (ServiceContractValue) home.find(serviceContractValueId, ServiceContractValue.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, ServiceContractValue.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName,attrValue,ServiceContractValue.class);
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
		if (this.contractId == null || !this.contractId.equals(contractId)) {
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
		if (this.serviceId == null || !this.serviceId.equals(serviceId)) {
			markModified();
		}
		this.serviceId = serviceId;
	}
	
	/**
	 * @return Returns the serviceProviderValue.
	 */
	public Double getServiceProviderValue() {
		fetch();
		return serviceProviderValue;
	}
	
	/**
	 * @param serviceProviderValue The serviceProviderValue to set.
	 */
	public void setServiceProviderValue(Double serviceProviderValue) {
		if (this.serviceProviderValue == null || !this.serviceProviderValue.equals(serviceProviderValue)){
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

	/**
	* @param  contractServiceEvent
	*/
	public void setServiceContractValue(UpdateContractServiceValueEvent contractServiceEvent, UpdateContractServiceEvent uEvent)
	{
		this.setContractId(contractServiceEvent.getContractId());
		this.setServiceId(uEvent.getServiceId());
		String serviceProviderValue = contractServiceEvent.getServiceProviderValue();
		if(serviceProviderValue != null && !serviceProviderValue.equals("")){
			this.setServiceProviderValue(new Double(serviceProviderValue));
		}else{
			this.setServiceProviderValue(new Double("0.00"));
		}
	}
	
	/**
	* @param  contractId
	* @param serviceId
	* @param serviceProviderValue
	*/
	public void setServiceContractValue(String contractId, String serviceId, String serviceProviderValue)
	{
		this.setContractId(contractId);
		this.setServiceId(serviceId);
		if(serviceProviderValue != null && !serviceProviderValue.equals("")){
			this.setServiceProviderValue(new Double(serviceProviderValue));
		}else{
			this.setServiceProviderValue(new Double("0.00"));
		}
	}
}
