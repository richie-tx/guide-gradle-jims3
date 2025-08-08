/*
 * Created on Jun 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider.reply;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.ResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.Name;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceProviderContactResponseEvent extends ResponseEvent implements IUserInfo, Comparable
{
	public String juvServProviderProfileId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String id;
	private String agencyId;
	private String JIMS2LogonId;
	//private String JIMS2Password;
	private String JIMSLogonId;
	//private String password;
	private String departmentId;
	private String userTypeId;
	private String employeeId;
	private String email;
	private String contactEmail;
	private String workPhone;
	private String extnNum;	
	private String message;
	private String providerName;
	private boolean isAdminContact;	
	private String prefix;
	private String suffix;
	
	private String cellPhone;
	private String faxNum;
	private String notes;
	private String pager;	
	private String logonId;
	private String jobTitle;
	private String statusId;
	private String departmentName;
	private String serviceProviderId;
	
	private boolean inactivated;
	private String statusCd;
	private String accountType;
	

	
	private int smUserOID;
	
	// IUserInfo methods  
		
	public int getSmUserOID()
	{
	    return smUserOID;
	}

	public void setSmUserOID(int smUserOID)
	{
	    this.smUserOID = smUserOID;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}
	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}
	
	/**
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
	 */
	public String getJIMS2LogonId()
	{
		return JIMS2LogonId;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
	 */
	public void setJIMS2LogonId(String JIMS2LogonId)
	{
		this.JIMS2LogonId = JIMS2LogonId;		
	}
		
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMS2Password()
	 */
	/*public String getJIMS2Password()
	{
		return JIMS2Password;
	}
	 (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
	 
	public void setJIMS2Password(String jims2Password)
	{
		this.JIMS2Password = jims2Password;
	}*/
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getJIMSLogonId()
	 */
	public String getJIMSLogonId()
	{
		return JIMSLogonId;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
	 */
	public void setJIMSLogonId(String JIMSLogonId)
	{
		this.JIMSLogonId = JIMSLogonId;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getPassword()
	 */
	/*public String getPassword()
	{
		return password;
	}
	 (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setPassword(java.lang.String)
	 
	public void setPassword(String password)
	{
		this.password = password;
	}*/
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getUserTypeId()
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#setUserTypeId(java.lang.String)
	 */
	public void setUserTypeId(String userTypeId)
	{
		this.userTypeId = userTypeId;
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.security.IUserInfo#getUserOID()
	 */
	public String getUserOID()
	{
		return JIMS2LogonId;
	}
	
	//end IUserInfo method 
	
	
	/**
	 * @return
	 */
	public String getJuvServProviderProfileId()
	{
		return juvServProviderProfileId;
	}

	/**
	 * @param string
	 */
	public void setJuvServProviderProfileId(String string)
	{
		juvServProviderProfileId = string;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param s
	 */
	public void setId(String s)
	{
		id = s;
	}
	
	public Name getContactName(){
		Name name = new Name();
		name.setFirstName(this.firstName);
		name.setLastName(this.lastName);
		name.setMiddleName(this.middleName);
		return name;
		
	}

	/**
	 * @return
	 */
	public String getEmployeeId()
	{
		return employeeId;
	}

	/**
	 * @param string
	 */
	public void setEmployeeId(String string)
	{
		employeeId = string;
	}

	/**
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string)
	{
		email = string;
	}

	/**
	 * @return
	 */
	public String getWorkPhone()
	{
		return workPhone;
	}

	/**
	 * @param string
	 */
	public void setWorkPhone(String string)
	{
		workPhone = string;
	}

	/**
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string)
	{
		message = string;
	}

	/**
	 * @return
	 */
	public String getProviderName()
	{
		return providerName;
	}

	/**
	 * @param string
	 */
	public void setProviderName(String string)
	{
		providerName = string;
	}

	/**
	 * @return Returns the extnNum.
	 */
	public String getExtnNum() {
		return extnNum;
	}
	/**
	 * @param extnNum The extnNum to set.
	 */
	public void setExtnNum(String extnNum) {
		this.extnNum = extnNum;
	}
	/**
	 * @return Returns the isAdminContact.
	 */
	public boolean isAdminContact() {
		return isAdminContact;
	}
	/**
	 * @param isAdminContact The isAdminContact to set.
	 */
	public void setAdminContact(boolean isAdminContact) {
		this.isAdminContact = isAdminContact;
	}
	/**
	 * @return Returns the prefix.
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix The prefix to set.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return Returns the suffix.
	 */
	public String getSuffix() {
		return suffix;
	}
	/**
	 * @param suffix The suffix to set.
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	/**
	 * @return Returns the cellPhone.
	 */
	public String getCellPhone() {
		return cellPhone;
	}
	/**
	 * @param cellPhone The cellPhone to set.
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	/**
	 * @return Returns the faxNum.
	 */
	public String getFaxNum() {
		return faxNum;
	}
	/**
	 * @param faxNum The faxNum to set.
	 */
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return Returns the pager.
	 */
	public String getPager() {
		return pager;
	}
	/**
	 * @param pager The pager to set.
	 */
	public void setPager(String pager) {
		this.pager = pager;
	}

	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the inactivated.
	 */
	public boolean isInactivated() {
		return inactivated;
	}
	/**
	 * @param inactivated The inactivated to set.
	 */
	public void setInactivated(boolean inactivated) {
		this.inactivated = inactivated;
	}
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		ServiceProviderContactResponseEvent evt = (ServiceProviderContactResponseEvent) obj;
		return lastName.compareToIgnoreCase(evt.getContactName().getLastName());		
	}	
	/**
	 * @return Returns the departmentName.
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName The departmentName to set.
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	
	public IPhoneNumber getWorkPhoneObj(){
		if(workPhone!=null){
			return new PhoneNumberBean(workPhone);
		}
		return null;
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

	public String getContactEmail()
	{
	    return contactEmail;
	}

	public void setContactEmail(String contactEmail)
	{
	    this.contactEmail = contactEmail;
	}
	public String getStatusCd()
	{
	    return statusCd;
	}

	public void setStatusCd(String statusCd)
	{
	    this.statusCd = statusCd;
	}
	public String getAccountType()
	{
	    return accountType;
	}

	public void setAccountType(String accountType)
	{
	    this.accountType = accountType;
	}

}
