/*
 * Created on Nov 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.form;

import java.util.ArrayList;
import java.util.Collection;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;


/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderContactForm extends ActionForm {
//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	private String contactId;
	private String jobTitle;
	private String contactStatusId;
	private String contactStatusDesc;
	private String userId;
	private IName contactName;
	private boolean adminContact;
	private String adminContactAsStr;
	private IPhoneNumber officePhone;
	private IPhoneNumber fax;
	private IPhoneNumber cellPhone;
	private IPhoneNumber pager;
	private String email;
	private String notes;
	private boolean lastAdminContact;
	private String serviceProviderId="";
	
	public void clear(){
		jobTitle="";
		contactId="";
		contactStatusId="";
		contactStatusDesc="";
		userId="";
		contactName=new Name("","","");
		adminContactAsStr="";
		officePhone=new PhoneNumber("");
		fax=new PhoneNumber("");
		cellPhone=new PhoneNumber("");
		pager=new PhoneNumber("");
		email="";
		notes="";
		lastAdminContact=false;
		serviceProviderId="";
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
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
			adminContactAsStr=(UIConstants.YES_FULL_TEXT);
		}
		else{
			adminContactAsStr=(UIConstants.NO_FULL_TEXT);
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
	 * @return Returns the cellPhone.
	 */
	public IPhoneNumber getCellPhone() {
		return cellPhone;
	}
	/**
	 * @param cellPhone The cellPhone to set.
	 */
	public void setCellPhone(IPhoneNumber cellPhone) {
		this.cellPhone = cellPhone;
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
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the fax.
	 */
	public IPhoneNumber getFax() {
		return fax;
	}
	/**
	 * @param fax The fax to set.
	 */
	public void setFax(IPhoneNumber fax) {
		this.fax = fax;
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
	 * @return Returns the officePhone.
	 */
	public IPhoneNumber getOfficePhone() {
		return officePhone;
	}
	/**
	 * @param officePhone The officePhone to set.
	 */
	public void setOfficePhone(IPhoneNumber officePhone) {
		this.officePhone = officePhone;
	}
	/**
	 * @return Returns the pager.
	 */
	public IPhoneNumber getPager() {
		return pager;
	}
	/**
	 * @param pager The pager to set.
	 */
	public void setPager(IPhoneNumber pager) {
		this.pager = pager;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
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
	 * @return Returns the lastAdminContact.
	 */
	public boolean isLastAdminContact() {
		return lastAdminContact;
	}
	/**
	 * @param lastAdminContact The lastAdminContact to set.
	 */
	public void setLastAdminContact(boolean lastAdminContact) {
		this.lastAdminContact = lastAdminContact;
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
}// END CLASS
