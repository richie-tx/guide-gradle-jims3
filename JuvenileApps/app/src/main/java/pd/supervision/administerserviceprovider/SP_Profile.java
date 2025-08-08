package pd.supervision.administerserviceprovider;

import java.util.Iterator;

import pd.codetable.Code;
import pd.contact.IContact;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.IUserInfo;

public class SP_Profile extends PersistentObject implements IContact, IUserInfo
{
	private String agencyId;
	private String cellPhone;
	private String departmentId;
	private String serviceProviderId;
	// this serviceProviderName attribute is for the view between CSJVSRVPRVPROF and CSJUVSERVPROV.
	private String serviceProviderName;
	private String email;
	private String faxNum;
	private String firstName;
	private boolean isAdminContact;
	private boolean isInHouse;
	private String JIMS2LogonId;
	//private String JIMS2Password;
	private String JIMSLogonId;
	private String lastName;
	private String middleName;
	private String notes;
	private String pager;
	//private String password;
	private String phoneNum;
	private String extnNum;
	private String userTypeId;
	private String prefix;
	private String suffix;
	private String jobTitle;
	private String employeeId;
	
	private String statusId;
	private Code status = null;	
	private String inHouseLogonId;
	private String accountType;

	
	/**
	* @roseuid 447357ED01C0
	*/
	public SP_Profile()
	{
	}
	/**
	* Access method for the employeeId property.
	* @return the current value of the employeeId property
	*/
	public String getEmployeeId() {
		fetch();
		return employeeId;
	}
	/**
	* Sets the value of the employeeId property.
	* @param aEmployeeId the new value of the employeeId property
	*/
	public void setEmployeeId(String aEmployeeId) {
		if (this.employeeId == null || !this.employeeId.equals(aEmployeeId)) {
			markModified();
		}
		employeeId = aEmployeeId;
	}
	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#getAgencyId()
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	* Access method for the cellPhone property.
	* @return the current value of the cellPhone property
	*/
	public String getCellPhone()
	{
		fetch();
		return cellPhone;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#getContactId()
	 */
	public String getContactId()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getDepartmentId()
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	public String getServiceProviderId()
	{
		return serviceProviderId;
	}
	/**
	* Access method for the email property.
	* @return the current value of the email property
	*/
	public String getEmail()
	{
		fetch();
		return email;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#getFaxLocation()
	 */
	public String getFaxLocation()
	{
		return null;
	}
	/**
	* Access method for the faxNum property.
	* @return the current value of the faxNum property
	*/
	public String getFaxNum()
	{
		fetch();
		return faxNum;
	}

	/**
	* Access method for the firstName property.
	* @return the current value of the firstName property
	*/
	public String getFirstName()
	{
		fetch();
		return firstName;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#getHomePhoneNum()
	 */
	public String getHomePhoneNum()
	{
		return null;
	}
	public String getHomeExtnNum()
	{
		return null;
	}	
	
	/**
	* Determines if the isAdminContact property is true.
	* @return <code>true<code> if the isAdminContact property is true
	*/
	public boolean getIsAdminContact()
	{
		fetch();
		return isAdminContact;
	}
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
	 */
	public String getJIMS2LogonId()
	{
		return JIMS2LogonId;
	}
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMS2Password()
	 */
	/*public String getJIMS2Password()
	{
		return JIMS2Password;
	}*/
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMSLogonId()
	 */
	public String getJIMSLogonId()
	{
		return JIMSLogonId;
	}
	/**
	* Access method for the lastName property.
	* @return the current value of the lastName property
	*/
	public String getLastName()
	{
		fetch();
		return lastName;
	}
	/**
	* Access method for the middleName property.
	* @return the current value of the middleName property
	*/
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}

	/**
	* Access method for the notes property.
	* @return the current value of the notes property
	*/
	public String getNotes()
	{
		fetch();
		return notes;
	}
	/**
	* Access method for the pager property.
	* @return the current value of the pager property
	*/
	public String getPager()
	{
		fetch();
		return pager;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#getPassword()
	 */
	/*public String getPassword()
	{
		return password;
	}*/
	/* (non-Javadoc)
	 * @see pd.contact.IContact#getPhoneExt()
	 */
	public String getPhoneExt()
	{
		return null;
	}
	/**
	* Access method for the phoneNum property.
	* @return the current value of the phoneNum property
	*/
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}
	public String getExtnNum()
	{
		fetch();
		return extnNum;
	}	
	
	
	/* (non-Javadoc)
	 * @see pd.contact.IContact#getTitle()
	 */
	public String getTitle()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#getUserLoginName()
	 */
	public String getUserLoginName()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#getUserOID()
	 */
	public String getUserOID()
	{
		return JIMS2LogonId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#getUserTypeId()
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#setAgencyId(java.lang.String)
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}
	/**
	* Sets the value of the cellPhone property.
	* @param aCellPhone the new value of the cellPhone property
	*/
	public void setCellPhone(String aCellPhone)
	{
		if (this.cellPhone == null || !this.cellPhone.equals(aCellPhone))
		{
			markModified();
		}
		cellPhone = aCellPhone;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setContactId(java.lang.String)
	 */
	public void setContactId(String aContactId)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setDepartmentId(java.lang.String)
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;

	}
	public void setServiceProviderId(String serviceProviderId)
	{
		this.serviceProviderId = serviceProviderId;

	}

	/**
	* Sets the value of the email property.
	* @param aEmail the new value of the email property
	*/
	public void setEmail(String aEmail)
	{
		if (this.email == null || !this.email.equals(aEmail))
		{
			markModified();
		}
		email = aEmail;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setFaxLocation(java.lang.String)
	 */
	public void setFaxLocation(String aFaxLocation)
	{
	}
	/**
	* Sets the value of the faxNum property.
	* @param aFax the new value of the faxNum property
	*/
	public void setFaxNum(String aFax)
	{
		if (this.faxNum == null || !this.faxNum.equals(aFax))
		{
			markModified();
		}
		faxNum = aFax;
	}

	/* (non-Javadoc)
	 * @see pd.contact.IContact#setFirstName(java.lang.String)
	 */
	public void setFirstName(String aFirstName)
	{
		if (this.firstName == null || !this.firstName.equals(aFirstName))
		{
			markModified();
		}
		firstName = aFirstName;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setHomePhoneNum(java.lang.String)
	 */
	public void setHomePhoneNum(String aHomePhoneNum)
	{
	}
	/**
	* Sets the value of the isAdminContact property.
	* @param aIsAdminContact the new value of the isAdminContact property
	*/
	public void setIsAdminContact(boolean aIsAdminContact)
	{
		if (this.isAdminContact != aIsAdminContact)
		{
			markModified();
		}
		isAdminContact = aIsAdminContact;
	}
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
	 */
	public void setJIMS2LogonId(String JIMS2LogonId)
	{
		this.JIMS2LogonId = JIMS2LogonId;
	}
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
	 */
	/*public void setJIMS2Password(String jims2Password)
	{
		this.JIMS2Password = jims2Password;

	}*/
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
	 */
	public void setJIMSLogonId(String JIMSLogonId)
	{
		this.JIMSLogonId = JIMSLogonId;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setLastName(java.lang.String)
	 */
	public void setLastName(String aLastName)
	{
		if (this.lastName == null || !this.lastName.equals(aLastName))
		{
			markModified();
		}
		lastName = aLastName;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setMiddleName(java.lang.String)
	 */
	public void setMiddleName(String aMiddleName)
	{
		if (this.middleName == null || !this.middleName.equals(aMiddleName))
		{
			markModified();
		}
		middleName = aMiddleName;
	}
	/**
	* Sets the value of the notes property.
	* @param aNotes the new value of the notes property
	*/
	public void setNotes(String aNotes)
	{
		if (this.notes == null || !this.notes.equals(aNotes))
		{
			markModified();
		}
		notes = aNotes;
	}
	/**
	* Sets the value of the pager property.
	* @param aPager the new value of the pager property
	*/
	public void setPager(String aPager)
	{
		if (this.pager == null || !this.pager.equals(aPager))
		{
			markModified();
		}
		pager = aPager;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#setPassword(java.lang.String)
	 */
/*	public void setPassword(String password)
	{
		this.password = password;
	}*/
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setPhoneExt(java.lang.String)
	 */
	public void setPhoneExt(String aPhoneExt)
	{

	}
	/**
	* Sets the value of the phoneNum property.
	* @param aPhone the new value of the phoneNum property
	*/
	public void setPhoneNum(String aPhone)
	{
		if (this.phoneNum == null || !this.phoneNum.equals(aPhone))
		{
			markModified();
		}
		phoneNum = aPhone;
	}
	public void setExtnNum(String aPhone)
	{
		if (this.extnNum == null || !this.extnNum.equals(aPhone))
		{
			markModified();
		}
		extnNum = aPhone;
	}	
	
	
	/* (non-Javadoc)
	 * @see pd.contact.IContact#setTitle(java.lang.String)
	 */
	public void setTitle(String aTitle)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#setUserLoginName(java.lang.String)
	 */
	public void setUserLoginName(String userLoginName)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.IUser#setUserTypeId(java.lang.String)
	 */
	public void setUserTypeId(String userTypeId)
	{
		this.userTypeId = userTypeId;
	}
	/* (non-Javadoc)
	 * @see pd.contact.IContact#getWorkPhoneNum()
	 */
	public String getWorkPhoneNum()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.contact.IContact#setWorkPhoneNum(java.lang.String)
	 */
	public void setWorkPhoneNum(String aWorkPhoneNum)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @return
	 */
	public boolean isInHouse()
	{
		return isInHouse;
	}

	/**
	 * @param b
	 */
	public void setAdminContact(boolean b)
	{
		isAdminContact = b;
	}

	/**
	 * @param b
	 */
	public void setInHouse(boolean b)
	{
		isInHouse = b;
	}

	/**
	 * @return
	 */
	public String getJobTitle()
	{
		fetch();
		return jobTitle;
	}

	/**
	 * @return
	 */
	public String getPrefix()
	{
		fetch();
		return prefix;
	}

	/**
	 * @return
	 */
	public String getSuffix()
	{
		fetch();
		return suffix;
	}

	/**
	 * @param string
	 */
	public void setJobTitle(String aJobTitle)
	{
		if (this.jobTitle == null || !this.jobTitle.equals(aJobTitle))
		{
			markModified();
		}
		jobTitle = aJobTitle;
	}

	/**
	 * @param string
	 */
	public void setPrefix(String aPrefix)
	{
		if (this.prefix == null || !this.prefix.equals(aPrefix))
		{
			markModified();
		}
		prefix = aPrefix;
	}

	/**
	 * @param string
	 */
	public void setSuffix(String aSuffix)
	{
		if (this.suffix == null || !this.suffix.equals(aSuffix))
		{
			markModified();
		}
		suffix = aSuffix;
	}
	
	static public SP_Profile find(String serviceProviderProfileId) {
		IHome home = new Home();
		return (SP_Profile) home.find(serviceProviderProfileId, SP_Profile.class);
	}
	
	static public SP_Profile findInstructor(String instructorId) {
		IHome home = new Home();
		return (SP_Profile) home.find(instructorId, SP_Profile.class);
	}
	
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, attrValue, SP_Profile.class);
	}
	
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, SP_Profile.class);
	}
	
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	public String getStatusId() {
		fetch();
		return statusId;
	}
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "SP_PROFILE_STATUS").getObject();
		}
	}
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		status.setContext("SP_PROFILE_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}	
	public void setInHouseLogonId(String inHouseLogonId)
	{
		if (this.inHouseLogonId == null || !this.inHouseLogonId.equals(inHouseLogonId))
		{
			markModified();
		}
		this.inHouseLogonId = inHouseLogonId;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getInHouseLogonId()
	{
		fetch();
		return inHouseLogonId;
	}	
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		fetch();
		return serviceProviderName;
	}
	
	public String getAccountType()
	{
	    fetch();
	    return accountType;
	}
	public void setAccountType(String accountType)
	{
	    if (this.accountType == null || !this.accountType.equals(accountType))
		{
			markModified();
		}
	    this.accountType = accountType;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(serviceProviderName))
		{
			markModified();
		}
		this.serviceProviderName = serviceProviderName;
	}
	@Override
	public String getDepartmentName()
	{
	    // TODO Auto-generated method stub
	    return null;
	}
	@Override
	public void setDepartmentName(String departmentName)
	{
	    // TODO Auto-generated method stub
	    
	}
	@Override
	public String getAgencyName()
	{
	    // TODO Auto-generated method stub
	    return null;
	}
	@Override
	public void setAgencyName(String agencyName)
	{
	    // TODO Auto-generated method stub
	    
	}
	@Override
	public String getOrgCode()
	{
	    // TODO Auto-generated method stub
	    return null;
	}
	@Override
	public void setOrgCode(String orgCode)
	{
	    // TODO Auto-generated method stub
	    
	}
	@Override
	public void setUserOID(String smUserId)
	{
	    // TODO Auto-generated method stub
	    
	}
}