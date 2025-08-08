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
public class JuvenileServiceProvider extends ServiceProvider {
	private String billingAddressId;
	/**
	* Properties for mailingAddress
	* @useParent true
	* @detailerDoNotGenerate true
	*/
	private Address mailingAddress = null;
	/**
	* Properties for providerPrograms
	* @referencedType pd.supervision.administerserviceprovider.ProviderProgram
	* @referencedType pd.supervision.administerserviceprovider.ProviderProgram
	* @detailerDoNotGenerate false
	*/
	private java.util.Collection providerPrograms = null;
	private String mailingAddressId;
	/**
	* Properties for billingAddress
	* @useParent true
	* @detailerDoNotGenerate true
	*/
	private Address billingAddress = null;
	private String serviceTypeId;
	private JuvenileEventTypeCode eventType = null;	
	private String programName;
	private String serviceName;
	private int providerProgramId;//added for 11099
	private String serviceStatusCode;
		
	/**
	* @roseuid 447357E803E3
	*/
	public JuvenileServiceProvider() {
	}
	/**
	* Initialize class relationship implementation for pd.supervision.administerserviceprovider.ProviderProgram
	*/
	private void initProviderPrograms() {
		if (providerPrograms == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			providerPrograms =
				new mojo.km.persistence.ArrayList(ProviderProgram.class, "juvenileServiceProviderId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.supervision.administerserviceprovider.ProviderProgram
	*/
	public java.util.Collection getProviderPrograms() {
		fetch();
		initProviderPrograms();
		return providerPrograms;
	}
	/**
	* insert a pd.supervision.administerserviceprovider.ProviderProgram into class relationship collection.
	*/
	public void insertProviderPrograms(ProviderProgram anObject) {
		initProviderPrograms();
		providerPrograms.add(anObject);
	}
	/**
	* Removes a pd.supervision.administerserviceprovider.ProviderProgram from class relationship collection.
	*/
	public void removeProviderPrograms(ProviderProgram anObject) {
		initProviderPrograms();
		providerPrograms.remove(anObject);
	}
	/**
	* Clears all pd.supervision.administerserviceprovider.ProviderProgram from class relationship collection.
	*/
	public void clearProviderPrograms() {
		initProviderPrograms();
		providerPrograms.clear();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setMailingAddressId(String mailingAddressId) {
		if (this.mailingAddressId == null || !this.mailingAddressId.equals(mailingAddressId)) {
			markModified();
		}
		mailingAddress = null;
		this.mailingAddressId = mailingAddressId;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getMailingAddressId() {
		fetch();
		return mailingAddressId;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initMailingAddress() {
		if (mailingAddress == null) {
			mailingAddress =
				(Address) new mojo
					.km
					.persistence
					.Reference(mailingAddressId, Address.class, (mojo.km.persistence.PersistentObject) this, "mailingAddress")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getMailingAddress() {
		fetch();
		initMailingAddress();
		return mailingAddress;
	}
	/**
	* set the type reference for class member mailingAddress
	*/
	public void setMailingAddress(Address mailingAddress) {
		if (this.mailingAddress == null || !this.mailingAddress.equals(mailingAddress)) {
			markModified();
		}
		mailingAddress.setContext(this, "mailingAddress");
		if (mailingAddress.getOID() == null) {
			new mojo.km.persistence.Home().bind(mailingAddress);
		}
		setMailingAddressId("" + mailingAddress.getOID());
		this.mailingAddress = (Address) new mojo.km.persistence.Reference(mailingAddress).getObject();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setBillingAddressId(String billingAddressId) {
		if (this.billingAddressId == null || !this.billingAddressId.equals(billingAddressId)) {
			markModified();
		}
		billingAddress = null;
		this.billingAddressId = billingAddressId;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getBillingAddressId() {
		fetch();
		return billingAddressId;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initBillingAddress() {
		if (billingAddress == null) {
			billingAddress =
				(Address) new mojo
					.km
					.persistence
					.Reference(billingAddressId, Address.class, (mojo.km.persistence.PersistentObject) this, "billingAddress")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getBillingAddress() {
		fetch();
		initBillingAddress();
		return billingAddress;
	}
	/**
	* set the type reference for class member billingAddress
	*/
	public void setBillingAddress(Address billingAddress) {
		if (this.billingAddress == null || !this.billingAddress.equals(billingAddress)) {
			markModified();
		}
		billingAddress.setContext(this, "billingAddress");
		if (billingAddress.getOID() == null) {
			new mojo.km.persistence.Home().bind(billingAddress);
		}
		setBillingAddressId("" + billingAddress.getOID());
		this.billingAddress = (Address) new mojo.km.persistence.Reference(billingAddress).getObject();
	}
	public void setJuvenileServiceProvider(CreateServiceProviderEvent createEvent, boolean statusChangeFlag) {
		this.setServiceProviderName(createEvent.getServiceProviderName());
		this.setStartDate(createEvent.getStartDate());
		this.setInHouse(createEvent.getIsInHouse());
		this.setAdminUserProfileId(createEvent.getAdminLogonId());
		//this.setContactUserProfileId(createEvent.getContactLogonId());#86318
		this.setIfasNumber(createEvent.getIfasNum());
		if(statusChangeFlag) {
			this.setStatusId(createEvent.getStatusId());
			this.setStatusChangeDate(DateUtil.getCurrentDate());
		}
		//this.setPhone(createEvent.getPhone().toString());
		this.setPhone(createEvent.getPhone().getAreaCode() + createEvent.getPhone().getPrefix() + createEvent.getPhone().getLast4Digit());
		this.setExtnNum(createEvent.getPhone().getExt());
		this.setFax(createEvent.getFax().getAreaCode() + createEvent.getFax().getPrefix() + createEvent.getFax().getLast4Digit());
		this.setWebSite(createEvent.getWebSite());
		this.setFtpSite(createEvent.getFtpSite());
		this.setOriginatingDepartment(createEvent.getDepartmentId());
		//to test if the trigger is executed from program
		//this.setStatusId(null);
		this.setMaxYouth(createEvent.getMaxYouth()); //US 177341
		this.setEmail(createEvent.getEmail());
		this.setEmailCheck( createEvent.isEmailCheck );
	}

	public void updateJuvServProvStatus(UpdateServiceProviderStatusRequestEvent createEvent, boolean inactivate) {
		this.setStatusId(createEvent.getStatusId());
		this.setStatusChangeDate(DateUtil.getCurrentDate());
		if (inactivate) {
			this.setInactiveDate(DateUtil.getCurrentDate());
		}
	}	
	
	/**
	* @return java.util.Iterator
	* @param departmentId
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		Iterator serviceProviders = null;
		serviceProviders = home.findAll(attrName, attrValue, JuvenileServiceProvider.class);
		return serviceProviders;
	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, JuvenileServiceProvider.class);
	}
	
		
	/**
	* @return JuvenileServiceProvider
	* @param serviceproviderId
	* @roseuid 4177C29D03A9
	*/

	static public JuvenileServiceProvider find(int oid) {
		Integer i = new Integer(oid);
		String s = i.toString();
		IHome home = new Home();
		return (JuvenileServiceProvider) home.find(s, JuvenileServiceProvider.class);
	}
	
	static public JuvenileServiceProvider find(String oid) {
		
		IHome home = new Home();
		return (JuvenileServiceProvider) home.find(oid, JuvenileServiceProvider.class);
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
	 * @return
	 */
	public String getServiceTypeId()
	{
		fetch();
		return serviceTypeId;
	}

	/**
	 * @param string
	 */
	public void setServiceTypeId(String string)
	{
		if (this.serviceTypeId == null || !this.serviceTypeId.equals(string)) {
			markModified();
		}
		this.serviceTypeId = string;
	}
    
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileEventTypeCode
	*/
	private void initEventType() {
		if (eventType == null) {
			eventType =
				(JuvenileEventTypeCode) new mojo.km.persistence.Reference(serviceTypeId, JuvenileEventTypeCode.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.criminal.JuvenileEventTypeCode
	*/
	public JuvenileEventTypeCode getEventType() {
		initEventType();
		return eventType;
	}
	
	/**
	* set the type reference for class member eventType
	*/
	public void setEventType(JuvenileEventTypeCode eventType) {
		if (this.eventType == null || !this.eventType.equals(eventType)) {
			markModified();
		}
		if (eventType.getOID() == null) {
			new mojo.km.persistence.Home().bind(eventType);
		}
		setServiceTypeId("" + eventType.getOID());
		this.eventType = (JuvenileEventTypeCode) new mojo.km.persistence.Reference(eventType).getObject();
	}
	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		fetch();
		return programName;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(String programName) {
		if (this.programName == null || !this.programName.equals(programName)) {
			markModified();
		}
		this.programName = programName;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		fetch();
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		if (this.serviceName == null || !this.serviceName.equals(serviceName)) {
			markModified();
		}
		this.serviceName = serviceName;
	}
	
	public String getServiceStatusCode() {
		fetch();
		return this.serviceStatusCode;
	}

	public void setServiceStatusCode(String serviceStatusCode) {
		if (this.serviceStatusCode == null || !this.serviceStatusCode.equals(serviceStatusCode)) {
			markModified();
		}
		this.serviceStatusCode = serviceStatusCode;
	}
}
