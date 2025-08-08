//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\CreateServiceProviderContactEvent.java

package messaging.administerserviceprovider;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.messaging.Composite.CompositeRequest;

public class CreateServiceProviderContactEvent extends CompositeRequest
{
	public IPhoneNumber cellPhone;
	public String email;
	public IPhoneNumber fax;
	public IName fullName;
	public boolean isAdminContact;
	public String logonId;
	public String employeeId;
	public String serviceProviderId;
	public String notes;
	public IPhoneNumber pager;
	public IPhoneNumber workPhone;
	public boolean isInHouse;
	public String jobTitle;
	public boolean isCreate;
	public String juvServProvProfId;
	
	private boolean inactivate;
	private String statusId;	
	
	/**
	 * @roseuid 4473534B0376
	 */
	public CreateServiceProviderContactEvent()
	{

	}

	/**
	 * Access method for the cellPhone property.
	 * 
	 * @return   the current value of the cellPhone property
	 */
	public IPhoneNumber getCellPhone()
	{
		return cellPhone;
	}

	/**
	 * Access method for the email property.
	 * 
	 * @return   the current value of the email property
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Access method for the fax property.
	 * 
	 * @return   the current value of the fax property
	 */
	public IPhoneNumber getFax()
	{
		return fax;
	}

	/**
	 * Access method for the fullName property.
	 * 
	 * @return   the current value of the fullName property
	 */
	public IName getFullName()
	{
		return fullName;
	}

	/**
	 * Determines if the isAdminContact property is true.
	 * 
	 * @return   <code>true<code> if the isAdminContact property is true
	 */
	public boolean getIsAdminContact()
	{
		return isAdminContact;
	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}
	
	/**
	 * Access method for the serviceProviderId property.
	 * 
	 * @return   the current value of the serviceProviderId property
	 */
	public String getServiceProviderId()
	{
		return serviceProviderId;
	}
	/**
	 * Access method for the notes property.
	 * 
	 * @return   the current value of the notes property
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * Access method for the pager property.
	 * 
	 * @return   the current value of the pager property
	 */
	public IPhoneNumber getPager()
	{
		return pager;
	}

	/**
	 * Access method for the workPhone property.
	 * 
	 * @return   the current value of the workPhone property
	 */
	public IPhoneNumber getWorkPhone()
	{
		return workPhone;
	}

	/**
	 * Sets the value of the cellPhone property.
	 * 
	 * @param aCellPhone the new value of the cellPhone property
	 */
	public void setCellPhone(IPhoneNumber aCellPhone)
	{
		cellPhone = aCellPhone;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param aEmail the new value of the email property
	 */
	public void setEmail(String aEmail)
	{
		email = aEmail;
	}

	/**
	 * Sets the value of the fax property.
	 * 
	 * @param aFax the new value of the fax property
	 */
	public void setFax(IPhoneNumber aFax)
	{
		fax = aFax;
	}

	/**
	 * Sets the value of the fullName property.
	 * 
	 * @param aFullName the new value of the fullName property
	 */
	public void setFullName(IName aFullName)
	{
		fullName = aFullName;
	}

	/**
	 * Sets the value of the isAdminContact property.
	 * 
	 * @param aIsAdminContact the new value of the isAdminContact property
	 */
	public void setIsAdminContact(boolean aIsAdminContact)
	{
		isAdminContact = aIsAdminContact;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}
	/**
	 * Sets the value of the serviceProviderId property.
	 * 
	 * @param aLogonId the new value of the serviceProviderId property
	 */
	public void setServiceProviderId(String aServiceProviderId)
	{
		serviceProviderId = aServiceProviderId;
	}	
	/**
	 * Sets the value of the notes property.
	 * 
	 * @param aNotes the new value of the notes property
	 */
	public void setNotes(String aNotes)
	{
		notes = aNotes;
	}

	/**
	 * Sets the value of the pager property.
	 * 
	 * @param aPager the new value of the pager property
	 */
	public void setPager(IPhoneNumber aPager)
	{
		pager = aPager;
	}

	/**
	 * Sets the value of the workPhone property.
	 * 
	 * @param aWorkPhone the new value of the workPhone property
	 */
	public void setWorkPhone(IPhoneNumber aWorkPhone)
	{
		workPhone = aWorkPhone;
	}

	/**
	 * @return
	 */
	public String getEmployeeId()
	{
		return employeeId;
	}

	/**
	 * @return
	 */
	public boolean isInHouse()
	{
		return isInHouse;
	}

	/**
	 * @param string
	 */
	public void setEmployeeId(String string)
	{
		employeeId = string;
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
		return jobTitle;
	}

	/**
	 * @param string
	 */
	public void setJobTitle(String string)
	{
		jobTitle = string;
	}
	/**
	 * @return
	 */
	public boolean isCreate()
	{
		return isCreate;
	}

	/**
	 * @param b
	 */
	public void setCreate(boolean b)
	{
		isCreate = b;
	}

	/**
	 * @return
	 */
	public String getJuvServProvProfId()
	{
		return juvServProvProfId;
	}

	/**
	 * @param string
	 */
	public void setJuvServProvProfId(String string)
	{
		juvServProvProfId = string;
	}

	/**
	 * @return Returns the inactivate.
	 */
	public boolean isInactivate() {
		return inactivate;
	}
	/**
	 * @param inactivate The inactivate to set.
	 */
	public void setInactivate(boolean inactivate) {
		this.inactivate = inactivate;
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
}