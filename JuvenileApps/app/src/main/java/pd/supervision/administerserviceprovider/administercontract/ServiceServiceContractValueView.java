//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\ServiceContractValue.java

package pd.supervision.administerserviceprovider.administercontract;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.supervision.administerserviceprovider.Service;

/*
 * 
 * @author mchowdhury
 *
 */

/**
 * This entity will represent the amount of a Contract's total value that has been 
 * alloted to a particular Service for a ServiceProvider.
 */
public class ServiceServiceContractValueView extends Service
{
   private String serviceServiceContractId;
   private String serviceId;
   /**
    * @roseuid 451D32640030
    */
   public ServiceServiceContractValueView() 
   {
    
   }
   
	/**
	* @return pd.supervision.administerserviceprovider.administercontract.ServiceContractValue
	* @param serviceContractValueId
	* @roseuid 4107B06D01B5
	*/
	static public ServiceServiceContractValueView findContractServiceValue(String serviceContractValueId)
	{
		IHome home = new Home();
		return (ServiceServiceContractValueView) home.find(serviceContractValueId, ServiceServiceContractValueView.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, ServiceServiceContractValueView.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName,new Integer(attrValue),ServiceServiceContractValueView.class);
	}
		
	/**
	 * @return Returns the serviceServiceContractId.
	 */
	public String getServiceServiceContractId() {
		fetch();
		return serviceServiceContractId;
	}
	/**
	 * @param serviceServiceContractId The serviceServiceContractId to set.
	 */
	public void setServiceServiceContractId(String serviceServiceContractId) {
		this.setOID(serviceServiceContractId);
		this.serviceServiceContractId = serviceServiceContractId;
	}
	
	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId() {
		fetch();
		return this.serviceId;
	}
	
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
}
