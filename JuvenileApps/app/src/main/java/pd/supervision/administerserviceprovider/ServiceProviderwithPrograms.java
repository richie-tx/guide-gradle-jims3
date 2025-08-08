package pd.supervision.administerserviceprovider;

import java.util.Iterator;
import messaging.administerserviceprovider.CreateServiceProviderEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.address.Address;
import pd.codetable.criminal.JuvenileEventTypeCode;

/**
* Properties for providerPrograms
* @referencedType pd.supervision.administerserviceprovider.ProviderProgram
* @referencedType pd.supervision.administerserviceprovider.ProviderProgram
* @detailerDoNotGenerate false
*/
public class ServiceProviderwithPrograms extends ServiceProvider {
    
    	private int providerProgramId;
	private String serviceProviderName;
	private boolean inHouse;
	
	public ServiceProviderwithPrograms() {
	}
	
	/**
	* @return java.util.Iterator
	* @param departmentId
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		Iterator serviceProviders = null;
		serviceProviders = home.findAll(attrName, attrValue, ServiceProviderwithPrograms.class);
		return serviceProviders;
	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, ServiceProviderwithPrograms.class);
	}
	
	
	/**
	 * @return
	 */
	public int getProviderProgramId()
	{
		fetch();
		return providerProgramId;
	}

	
	/**
	 * @param i
	 */
	public void setProviderProgramId(int i)
	{
		if (this.providerProgramId != i) {
			markModified();
		}
		providerProgramId = i;
	}
	
		
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceProviderName() {
		fetch();
		return serviceProviderName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(serviceProviderName)) {
			markModified();
		}
		this.serviceProviderName = serviceProviderName;
	}
	/**
	* Determines if the inHouse property is true.
	* @return <code>true<code> if the inHouse property is true
	*/
	public boolean getInHouse() {
		fetch();
		return inHouse;
	}
	
	/**
	* Sets the value of the inHouse property.
	* @param aInHouse the new value of the inHouse property
	*/
	public void setInHouse(boolean aInHouse) {
		if (this.inHouse != aInHouse) {
			markModified();
		}
		inHouse = aInHouse;
	}
}
