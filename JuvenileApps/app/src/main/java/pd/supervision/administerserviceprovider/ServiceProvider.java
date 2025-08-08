package pd.supervision.administerserviceprovider;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.CreateCalendarServiceEventEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.contact.user.UserProfile;

/**
* Properties for genericUsers
* @detailerDoNotGenerate false
* @referencedType pd.contact.user.UserProfile
* @referencedType pd.contact.user.UserProfile
*/
public class ServiceProvider extends PersistentObject {
	private String phone;
	private String extnNum;
	private String originatingDepartment;
	private Date startDate;
	private Date inactiveDate;
	private Date reactivateDate;
	private String inactiveReason;
	private String statusId;
	private String serviceProviderId;
	private Collection providerPrograms;
	private Date statusChangeDate;
	/**
	* Properties for status
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey SERVICE_PROVIDER_STATUS
	*/
	private Code status = null;
	private String fax;
	/**
	* Properties for spProfiles
	* @detailerDoNotGenerate false
	* @referencedType pd.supervision.administerserviceprovider.SP_Profile
	* @referencedType pd.supervision.administerserviceprovider.SP_Profile
	* @referencedType pd.supervision.administerserviceprovider.SP_Profile
	*/
	private Collection spProfiles = null;
	private String ftpSite;
	/**
	* Properties for contactUserProfile
	*/
	private UserProfile contactUserProfile = null;
	private String adminUserProfileId;
	private String webSite;
	/**
	* Properties for adminUserProfile
	*/
	private UserProfile adminUserProfile = null;
	private String ifasNumber;
	private String serviceProviderName;
	private String contactUserProfileId;
	private boolean emailCheck;
	
	// properties for serviceProviderServicelocation
	private int providerProgramId;
	private int serviceId;
	private String serviceName;
	private int locationId;
	private int serviceLocationId;
	private String locationName;
	private String serviceProviderServiceLocationId;
	
	private Date programStartDate;
	private Date programEndDate;
	private String maxEnrollment;
	
	private String instructorName;
	
	private int juvLocUnitId;
	private String locationUnitName;
	
	private String maxYouth=null; // added for 177341
	private String email;
	private boolean inHouse;
	
	/**
	* @roseuid 447357EB023D
	*/
	public ServiceProvider() {
	}
	/**
	* Access method for the fax property.
	* @return the current value of the fax property
	*/
	public String getFax() {
		fetch();
		return fax;
	}
	/**
	* Access method for the ftpSite property.
	* @return the current value of the ftpSite property
	*/
	public String getFtpSite() {
		fetch();
		return ftpSite;
	}
	/**
	* Access method for the ifasNumber property.
	* @return the current value of the ifasNumber property
	*/
	public String getIfasNumber() {
		fetch();
		return ifasNumber;
	}
	/**
	* Access method for the inactiveDate property.
	* @return the current value of the inactiveDate property
	*/
	public Date getInactiveDate() {
		fetch();
		return inactiveDate;
	}
	/**
	* Access method for the inactiveReason property.
	* @return the current value of the inactiveReason property
	*/
	public String getInactiveReason() {
		fetch();
		return inactiveReason;
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
	* Access method for the  property.
	* @return the current value of the  property
	*/
	public String getServiceProviderName() {
		fetch();
		return serviceProviderName;
	}
	/**
	* Access method for the originatingDepartment property.
	* @return the current value of the originatingDepartment property
	*/
	public String getOriginatingDepartment() {
		fetch();
		return originatingDepartment;
	}
	/**
	* Access method for the phone property.
	* @return the current value of the phone property
	*/
	public String getPhone() {
		fetch();
		return phone;
	}
	public String getExtnNum() {
		fetch();
		return extnNum;
	}	
	/**
	* Access method for the reactivateDate property.
	* @return the current value of the reactivateDate property
	*/
	public Date getReactivateDate() {
		fetch();
		return reactivateDate;
	}
	/**
	* Access method for the startDate property.
	* @return the current value of the startDate property
	*/
	public Date getStartDate() {
		fetch();
		return startDate;
	}
	public Date getStatusChangeDate() {
		fetch();
		return statusChangeDate;
	}	
	
	/**
	* Access method for the webSite property.
	* @return the current value of the webSite property
	*/
	public String getWebSite() {
		fetch();
		return webSite;
	}
	/**
	* Sets the value of the fax property.
	* @param aFax the new value of the fax property
	*/
	public void setFax(String aFax) {
		if (this.fax == null || !this.fax.equals(aFax)) {
			markModified();
		}
		fax = aFax;
	}
	/**
	* Sets the value of the ftpSite property.
	* @param aFtpSite the new value of the ftpSite property
	*/
	public void setFtpSite(String aFtpSite) {
		if (this.ftpSite == null || !this.ftpSite.equals(aFtpSite)) {
			markModified();
		}
		ftpSite = aFtpSite;
	}
	/**
	* Sets the value of the ifasNumber property.
	* @param aIfasNumber the new value of the ifasNumber property
	*/
	public void setIfasNumber(String aIfasNumber) {
		if (this.ifasNumber == null || !this.ifasNumber.equals(aIfasNumber)) {
			markModified();
		}
		ifasNumber = aIfasNumber;
	}
	/**
	* Sets the value of the inactiveDate property.
	* @param aInactiveDate the new value of the inactiveDate property
	*/
	public void setInactiveDate(Date aInactiveDate) {
		if (this.inactiveDate == null || !this.inactiveDate.equals(aInactiveDate)) {
			markModified();
		}
		inactiveDate = aInactiveDate;
	}
	/**
	* Sets the value of the inactiveReason property.
	* @param aInactiveReason the new value of the inactiveReason property
	*/
	public void setInactiveReason(String aInactiveReason) {
		if (this.inactiveReason == null || !this.inactiveReason.equals(aInactiveReason)) {
			markModified();
		}
		inactiveReason = aInactiveReason;
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
	/**
	* Sets the value of the  property.
	* @param aName the new value of the name property
	*/
	public void setServiceProviderName(String aName) {
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(aName)) {
			markModified();
		}
		serviceProviderName = aName;
	}
	/**
	* Sets the value of the originatingDepartment property.
	* @param aOriginatingDepartment the new value of the originatingDepartment property
	*/
	public void setOriginatingDepartment(String aOriginatingDepartment) {
		if (this.originatingDepartment == null || !this.originatingDepartment.equals(aOriginatingDepartment)) {
			markModified();
		}
		originatingDepartment = aOriginatingDepartment;
	}
	/**
	* Sets the value of the phone property.
	* @param aPhone the new value of the phone property
	*/
	public void setPhone(String aPhone) {
		if (this.phone == null || !this.phone.equals(aPhone)) {
			markModified();
		}
		phone = aPhone;
	}
	public void setExtnNum(String aPhone) {
		if (this.extnNum == null || !this.extnNum.equals(aPhone)) {
			markModified();
		}
		extnNum = aPhone;
	}	
	/**
	* Sets the value of the reactivateDate property.
	* @param aReactivateDate the new value of the reactivateDate property
	*/
	public void setReactivateDate(Date aReactivateDate) {
		if (this.reactivateDate == null || !this.reactivateDate.equals(aReactivateDate)) {
			markModified();
		}
		reactivateDate = aReactivateDate;
	}
	/**
	* Sets the value of the startDate property.
	* @param aStartDate the new value of the startDate property
	*/
	public void setStartDate(Date aStartDate) {
		if (this.startDate == null || !this.startDate.equals(aStartDate)) {
			markModified();
		}
		startDate = aStartDate;
	}
	public void setStatusChangeDate(Date aStatusChangeDate) {
		if (this.statusChangeDate == null || !this.statusChangeDate.equals(aStatusChangeDate)) {
			markModified();
		}
		statusChangeDate = aStatusChangeDate;
	}
	/**
	* Sets the value of the webSite property.
	* @param aWebSite the new value of the webSite property
	*/
	public void setWebSite(String aWebSite) {
		if (this.webSite == null || !this.webSite.equals(aWebSite)) {
			markModified();
		}
		webSite = aWebSite;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "SERVICE_PROVIDER_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		initStatus();
		return status;
	}
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		status.setContext("SERVICE_PROVIDER_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Initialize class relationship implementation for pd.supervision.administerserviceprovider.SP_Profile
	*/
	private void initSpProfiles() {
		if (spProfiles == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			spProfiles = new mojo.km.persistence.ArrayList(SP_Profile.class, "serviceProviderId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.supervision.administerserviceprovider.SP_Profile
	*/
	public Collection getSpProfiles() {
		initSpProfiles();
		return spProfiles;
	}
	/**
	* insert a pd.supervision.administerserviceprovider.SP_Profile into class relationship collection.
	*/
	public void insertSpProfiles(SP_Profile anObject) {
		initSpProfiles();
		spProfiles.add(anObject);
	}
	/**
	* Removes a pd.supervision.administerserviceprovider.SP_Profile from class relationship collection.
	*/
	public void removeSpProfiles(SP_Profile anObject) {
		initSpProfiles();
		spProfiles.remove(anObject);
	}
	/**
	* Clears all pd.supervision.administerserviceprovider.SP_Profile from class relationship collection.
	*/
	public void clearSpProfiles() {
		initSpProfiles();
		spProfiles.clear();
	}



	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setContactUserProfileId(String contactUserProfileId) {
		if (this.contactUserProfileId == null || !this.contactUserProfileId.equals(contactUserProfileId)) {
			markModified();
		}
		contactUserProfile = null;
		this.contactUserProfileId = contactUserProfileId;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getContactUserProfileId() {
		fetch();
		return contactUserProfileId;
	}
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initContactUserProfile() {
		if (contactUserProfile == null) {
		//87191
			contactUserProfile = UserProfile.find(contactUserProfileId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(contactUserProfileId, pd.contact.user.UserProfile.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getContactUserProfile() {
		initContactUserProfile();
		return contactUserProfile;
	}
	/**
	* set the type reference for class member contactUserProfile
	*/
	//87191
	public void setContactUserProfile(UserProfile contactUserProfile) {
		/*if (this.contactUserProfile == null || !this.contactUserProfile.equals(contactUserProfile)) {
			markModified();
		}
		if (contactUserProfile.getOID() == null) {
			new mojo.km.persistence.Home().bind(contactUserProfile);
		}*/
		setContactUserProfileId("" + contactUserProfile.getUserID());
		this.contactUserProfile = contactUserProfile;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(contactUserProfile).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setAdminUserProfileId(String adminUserProfileId) {
		if (this.adminUserProfileId == null || !this.adminUserProfileId.equals(adminUserProfileId)) {
			markModified();
		}
		adminUserProfile = null;
		this.adminUserProfileId = adminUserProfileId;
	}
	/**
	* Set the reference value to serviceProviderId
	*/
	public void setServiceProviderId(String serviceProviderId) {
		if (this.serviceProviderId == null || !this.serviceProviderId.equals(serviceProviderId)) {
			markModified();
		}		
		this.serviceProviderId = serviceProviderId;
	}	
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getAdminUserProfileId() {
		fetch();
		return adminUserProfileId;
	}
	/**
	* Get the reference value to serviceProviderId
	*/
	public String getServiceProviderId() {
		fetch();
		return serviceProviderId;
	}	
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initAdminUserProfile() {
		if (adminUserProfile == null) {
		//87191
			adminUserProfile = UserProfile.find(adminUserProfileId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(adminUserProfileId, pd.contact.user.UserProfile.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getAdminUserProfile() {
		initAdminUserProfile();
		return adminUserProfile;
	}
	/**
	* set the type reference for class member adminUserProfile
	*/
	//87191
	public void setAdminUserProfile(UserProfile adminUserProfile) {
		/*if (this.adminUserProfile == null || !this.adminUserProfile.equals(adminUserProfile)) {
			markModified();
		}
		if (adminUserProfile.getOID() == null) {
			new mojo.km.persistence.Home().bind(adminUserProfile);
		}*/
		setAdminUserProfileId("" + adminUserProfile.getUserID());
		this.adminUserProfile = adminUserProfile; //(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(adminUserProfile).getObject();
	}
	
	static public ServiceProvider find(String serviceProviderId)
	{
		ServiceProvider serviceProvider = null;
		IHome home = new Home();
		serviceProvider = (ServiceProvider) home.find(serviceProviderId, ServiceProvider.class);
		return serviceProvider;
	}
	static public Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(ServiceProvider.class);
		return iter;
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event,ServiceProvider.class);
	}
	static public Iterator findAll(String attrName, String attrValue) {
			IHome home = new Home();
			Iterator serviceProviders = null;
			serviceProviders = home.findAll(attrName, attrValue, ServiceProvider.class);
			return serviceProviders;
		}
		
	/**
	 * @param createEvent
	 */
	public void setServiceProvider(CreateCalendarServiceEventEvent createEvent)
	{

	}
	/**
	 * @return
	 */
	public int getLocationId()
	{
		fetch();
		return locationId;
	}

	/**
	 * @return
	 */
	public int getServiceLocationId()
	{
		fetch();
		return serviceLocationId;
	}
	/**
	 * @return
	 */
	public String getLocationName()
	{
		fetch();
		return locationName;
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
	 * @return
	 */
	public int getServiceId()
	{
		fetch();
		return serviceId;
	}

	/**
	 * @return
	 */
	public String getServiceProviderServiceLocationId()
	{
		fetch();
		return serviceProviderServiceLocationId;
	}

	/**
	 * @param i
	 */
	public void setLocationId(int i)
	{
		if (this.locationId != i) {
			markModified();
		}
		locationId = i;
	}
	
	/**
	 * @param i
	 */
	public void setServiceLocationId(int i)
	{
		if (this.serviceLocationId != i) {
			markModified();
		}
		serviceLocationId = i;
	}

	/**
	 * @param i
	 */
	public void setLocationName(String locationName)
	{
		if (this.locationName!=null &&  !this.locationName.equals(locationName)) {
			markModified();
		}
		this.locationName = locationName;
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
	 * @param i
	 */
	public void setServiceId(int i)
	{
		if (this.serviceId != i) {
			markModified();
		}
		serviceId = i;
	}

	/**
	 * @param string
	 */
	public void setServiceProviderServiceLocationId(String string)
	{
		if (this.serviceProviderServiceLocationId == null || !this.serviceProviderServiceLocationId.equals(string)) {
			markModified();
		}
		this.serviceProviderServiceLocationId = string;
	}
	
	
	/**
	* Clears all pd.supervision.administerserviceprovider..ProviderProgram from class relationship collection.
	* @roseuid 4107DFB603E7
	*/
	public void clearProviderPrograms()
	{
		initProviderPrograms();
		providerPrograms.clear();
	}


	/**
	* returns a collection of pd.supervision.administerserviceprovider..ProviderProgram
	* @return java.util.Collection
	* @roseuid 4107DFB603A1
	*/
	public Collection getProviderPrograms()
	{
		fetch();
		initProviderPrograms();
		return providerPrograms;
	}



	/**
	* Initialize class relationship implementation for pd.supervision.administerserviceprovider.ProviderProgram
	* @roseuid 4107DFB60397
	*/
	private void initProviderPrograms()
	{
		if (providerPrograms == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				providerPrograms =
					new mojo.km.persistence.ArrayList(ProviderProgram.class, "agencyId", "" + getOID());
			}
			catch (Throwable t)
			{
				providerPrograms = new java.util.ArrayList();
			}
		}
	}

	/**
	* insert a pd.supervision.administerserviceprovider.ProviderProgram into class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603B5
	*/
	public void insertProviderPrograms(ProviderProgram anObject)
	{
		initProviderPrograms();
		providerPrograms.add(anObject);
	}

	/**
	* Removes a pd.contact.agency.Division from class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603C9
	*/
	public void removeProviderPrograms(ProviderProgram anObject)
	{
		initProviderPrograms();
		providerPrograms.remove(anObject);
	}
	/**
	 * @return
	 */
	public String getServiceName()
	{
		fetch();
		return serviceName;
	}

	/**
	 * @param string
	 */
	public void setServiceName(String string)
	{
		if (this.serviceName == null || !this.serviceName.equals(string)) {
			markModified();
		}
		this.serviceName = string;
	}

	/**
	 * @return Returns the maxEnrollment.
	 */
	public String getMaxEnrollment() {
		fetch();
		return maxEnrollment;
	}
	/**
	 * @return Returns the programEndDate.
	 */
	public Date getProgramEndDate() {
		fetch();
		return programEndDate;
	}
	/**
	 * @return Returns the programStartDate.
	 */
	public Date getProgramStartDate() {
		fetch();
		return programStartDate;
	}
	/**
	 * @param maxEnrollment The maxEnrollment to set.
	 */
	public void setMaxEnrollment(String maxEnrollment) {
		if (this.maxEnrollment == null || !this.maxEnrollment.equals(maxEnrollment)) {
			markModified();
		}
		this.maxEnrollment = maxEnrollment;
	}
	/**
	 * @param programEndDate The programEndDate to set.
	 */
	public void setProgramEndDate(Date programEndDate) {
		if (this.programEndDate == null || !this.programEndDate.equals(programEndDate)) {
			markModified();
		}
		this.programEndDate = programEndDate;
	}
	/**
	 * @param programStartDate The programStartDate to set.
	 */
	public void setProgramStartDate(Date programStartDate) {
		if (this.programStartDate == null || !this.programStartDate.equals(programEndDate)) {
			markModified();
		}
		this.programStartDate = programStartDate;
	}
	public void createOID() {
		new Home().bind(this);
	}	
	/**
	 * @return Returns the instructorName.
	 */
	public String getInstructorName() {
		fetch();
		return instructorName;
	}
	/**
	 * @param instructorName The instructorName to set.
	 */
	public void setInstructorName(String instructorName) {
		if (this.instructorName == null || !this.instructorName.equals(instructorName)) {
			markModified();
		}		
		this.instructorName = instructorName;
	}
	/**
	 * @return Returns the juvLocUnitId.
	 */
	public int getJuvLocUnitId() {
		fetch();
		return juvLocUnitId;
	}
	/**
	 * @param juvLocUnitId The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(int i) {
		if (this.juvLocUnitId != i) {
			markModified();
		}
		this.juvLocUnitId = i;	
		
	}
	
	/**
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		fetch();
		return locationUnitName;
	}
	/**
	 * @param locationUnitName The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		if (this.locationUnitName == null || !this.locationUnitName.equals(locationUnitName)) {
			markModified();
		}		
		this.locationUnitName = locationUnitName;
	}
	
	/**
	 * @return Returns the serviceName.
	 */
	public String getMaxYouth() {
		fetch();
		return maxYouth;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setMaxYouth(String maxYouth) {
		if (this.maxYouth == null || !this.maxYouth.equals(maxYouth)) {
			markModified();
		}
		this.maxYouth = maxYouth;
	}
	
	public String getEmail()
	{
	    fetch();
	    return this.email;
	}

	public void setEmail(String email)
	{
	    if (this.email == null || !this.email.equals(email)) {
		markModified();
	    }
	    this.email = email;
	}
	
	public boolean isEmailCheck()
	{
	    fetch();
	    return emailCheck;
	}
	
	public void setEmailCheck(boolean emailCheck)
	{
	    if (this.emailCheck != emailCheck) {
		markModified();
	}
	    this.emailCheck = emailCheck;
	}
}
