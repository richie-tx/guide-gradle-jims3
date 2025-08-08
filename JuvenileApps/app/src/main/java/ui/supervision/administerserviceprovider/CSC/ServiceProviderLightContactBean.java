/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC;

import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderLightContactBean implements Comparable{
	
	private String contactId="";
	private IName contactName=new Name();
	private String contactStatusId="";
	private String contactStatusDesc="";
	private boolean adminContact;
	private String adminContactAsStr;
	private String contactEmail="";
	private IPhoneNumber contactOfficePhoneNumber=new PhoneNumber("");
	private String serviceProviderId="";
	private String jobTitle;
	
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		try{
			ServiceProviderLightContactBean myIncomingObj=(ServiceProviderLightContactBean)o;
			int rtrVal= this.contactName.getLastName().compareTo(myIncomingObj.getContactName().getLastName());
			if(rtrVal==0){
				return this.contactName.getFirstName().compareTo(myIncomingObj.getContactName().getFirstName());
			}
			else
				return rtrVal;
		}
		catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * @return Returns the contactEmail.
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail The contactEmail to set.
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return Returns the contactId.
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId The contactId to set.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return Returns the contactName.
	 */
	public IName getContactName() {
		return contactName;
	}
	/**
	 * @param contactName The contactName to set.
	 */
	public void setContactName(IName contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return Returns the contactOfficePhoneNumber.
	 */
	public IPhoneNumber getContactOfficePhoneNumber() {
		return contactOfficePhoneNumber;
	}
	/**
	 * @param contactOfficePhoneNumber The contactOfficePhoneNumber to set.
	 */
	public void setContactOfficePhoneNumber(IPhoneNumber contactOfficePhoneNumber) {
		this.contactOfficePhoneNumber = contactOfficePhoneNumber;
	}
	/**
	 * @return Returns the contactStatusDesc.
	 */
	public String getContactStatusDesc() {
		return contactStatusDesc;
	}
	/**
	 * @param contactStatusDesc The contactStatusDesc to set.
	 */
	public void setContactStatusDesc(String contactStatusDesc) {
		this.contactStatusDesc = contactStatusDesc;
	}
	/**
	 * @return Returns the contactStatusId.
	 */
	public String getContactStatusId() {
		return contactStatusId;
	}
	/**
	 * @param contactStatusId The contactStatusId to set.
	 */
	public void setContactStatusId(String contactStatusId) {
		this.contactStatusId = contactStatusId;
		this.contactStatusDesc="";
		contactStatusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_CONTACT_STATUS,contactStatusId);
	}
	/**
	 * @return Returns the adminContact.
	 */
	public boolean isAdminContact() {
		return adminContact;
	}
	/**
	 * @param adminContact The adminContact to set.
	 */
	public void setAdminContact(boolean aAdminContact) {
		this.adminContact = aAdminContact;
		if(this.adminContact){
			adminContactAsStr=UIConstants.YES_FULL_TEXT;
		}
		else{
			adminContactAsStr=UIConstants.NO_FULL_TEXT;
		}
	}
	/**
	 * @return Returns the adminContactAsStr.
	 */
	public String getAdminContactAsStr() {
		return adminContactAsStr;
	}
	/**
	 * @param adminContactAsStr The adminContactAsStr to set.
	 */
	public void setAdminContactAsStr(String aAdminContactAsStr) {
		this.adminContactAsStr = aAdminContactAsStr;
		if(this.adminContactAsStr!=null && (PDCodeTableConstants.ASP_CS_CONTACT_ADMINCONTACT_YES.equals(this.adminContactAsStr) || adminContactAsStr.equalsIgnoreCase("true"))){
			adminContact=true;
			adminContactAsStr=UIConstants.YES_FULL_TEXT;
		}
		else if(this.adminContactAsStr!=null && (PDCodeTableConstants.ASP_CS_CONTACT_ADMINCONTACT_NO.equals(this.adminContactAsStr) || adminContactAsStr.equalsIgnoreCase("false"))){
			adminContact=false;
			adminContactAsStr=UIConstants.NO_FULL_TEXT;
		}
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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
}
