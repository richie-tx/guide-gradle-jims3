/*
 * Created on Nov 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import messaging.contact.domintf.IAddress;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionMapping;
import ui.common.Address;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.form.AddressValidationForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderCSCDForm extends AddressValidationForm {
//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	private String serviceProviderAgencyId;
	private String serviceProviderId;
	private String statusId;
	private String statusDesc;
	private String name;
	private String startDateAsStr;
	private Date startDate;
	private String inHouseAsStr;
	private boolean inHouse;
	private String ifasNumber;
	private IPhoneNumber phoneNumber;
	private IPhoneNumber faxNumber;
	private String website;
	private String email;
	private String ftp;
	private String comments;
	private String confirmMessage;
	private IAddress mailingAddress;
	private IAddress billingAddress;
	private boolean sameAsMailingAddress=false;
	private boolean isFaithBased;
	private String isFaithBasedStr;
	private String cancelPath="";
	private String billingAddressStatus;
	private String mailingAddressStatus;
	

	private String selectedProgram;
	private String selectedContact;
	
	private Collection programs; // ServiceProviderLightProgramBean
	private Collection contacts; // ServiceProviderLightContactBean
	private static Collection statusTypes = new ArrayList();
	
	
	public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request){
		setSameAsMailingAddress(false);
	}
	
	public void clear(){
		serviceProviderAgencyId="";
		serviceProviderId="";
		statusDesc="";
		statusId="";
		name="";
		startDateAsStr="";
		startDate=null;
		inHouseAsStr="";
		inHouse=false;
		ifasNumber="";
		phoneNumber=new PhoneNumber("");
		faxNumber=new PhoneNumber("");
		website="";
		email="";
		ftp="";
		comments="";
		confirmMessage="";
		mailingAddress=new Address();
		billingAddress=new Address();
		programs=new ArrayList();
		contacts=new ArrayList();
		selectedProgram="";
		selectedContact="";
		sameAsMailingAddress=false;
		isFaithBasedStr="";
		isFaithBased=false;		
		cancelPath="";
		mailingAddressStatus = "";
		billingAddressStatus = "";
	}
	
	public void partialClear(){
		serviceProviderAgencyId="";
		serviceProviderId="";
		statusDesc="";
		statusId="";
		confirmMessage="";
		mailingAddress=new Address();
		billingAddress=new Address();
		programs=new ArrayList();
		contacts=new ArrayList();
		selectedProgram="";
		selectedContact="";
		sameAsMailingAddress=false;
		cancelPath="";
		mailingAddressStatus = "";
		billingAddressStatus = "";
	}
	

	public String getCancelPath() {
		return cancelPath;
	}

	public void setCancelPath(String cancelPath) {
		this.cancelPath = cancelPath;
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
	
	public void setAddressStatus( String status )
	{
		super.setAddressStatus( status ) ;
		if( super.getCurrentAddressInd() != null )
		{
			if( super.getCurrentAddressInd().equals( "mailingAddress" ) )
			{
				this.setMailingAddressStatus( status ) ;
			}
			else if( super.getCurrentAddressInd().equals( "billingAddress" ) )
			{
				this.setBillingAddressStatus( status ) ;
			}
			else
			{
				this.setMailingAddressStatus( "U" ) ;
				this.setBillingAddressStatus( "U" ) ;
			}
		}
	}
	
	/**
	 * @return Returns the billingAddress.
	 */
	public IAddress getBillingAddress() {
		return billingAddress;
	}
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the contacts.
	 */
	public Collection getContacts() {
		return contacts;
	}
	/**
	 * @param contacts The contacts to set.
	 */
	public void setContacts(Collection contacts) {
		this.contacts = contacts;
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
	 * @return Returns the faxNumber.
	 */
	public IPhoneNumber getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param faxNumber The faxNumber to set.
	 */
	public void setFaxNumber(IPhoneNumber faxNumber) {
		this.faxNumber = faxNumber;
	}
	/**
	 * @return Returns the ftp.
	 */
	public String getFtp() {
		return ftp;
	}
	/**
	 * @param ftp The ftp to set.
	 */
	public void setFtp(String ftp) {
		this.ftp = ftp;
	}
	/**
	 * @return Returns the ifasNumber.
	 */
	public String getIfasNumber() {
		return ifasNumber;
	}
	/**
	 * @param ifasNumber The ifasNumber to set.
	 */
	public void setIfasNumber(String ifasNumber) {
		this.ifasNumber = ifasNumber;
	}
	/**
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
		if(this.inHouse){
			inHouseAsStr=(UIConstants.YES_FULL_TEXT);
		}
		else{
			inHouseAsStr=(UIConstants.NO_FULL_TEXT);
		}
	}
	/**
	 * @return Returns the inHouseAsStr.
	 */
	public String getInHouseAsStr() {
		return inHouseAsStr;
	}
	/**
	 * @param inHouseAsStr The inHouseAsStr to set.
	 */
	public void setInHouseAsStr(String aInHouseAsStr) {
		this.inHouseAsStr = aInHouseAsStr;
		if(this.inHouseAsStr!=null && (PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_YES.equals(this.inHouseAsStr) || inHouseAsStr.equalsIgnoreCase("true"))){
			inHouse=true;
			inHouseAsStr=UIConstants.YES_FULL_TEXT;
		}
		else if(this.inHouseAsStr!=null && ( PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_NO.equals(this.inHouseAsStr) || inHouseAsStr.equalsIgnoreCase("false"))){
			inHouse=false;
			inHouseAsStr=UIConstants.NO_FULL_TEXT;
		}
	}
	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}
	/**
	 * @param listsSet The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}
	/**
	 * @return Returns the mailingAddress.
	 */
	public IAddress getMailingAddress() {
		return mailingAddress;
	}
	/**
	 * @param mailingAddress The mailingAddress to set.
	 */
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public IPhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(IPhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Returns the programs.
	 */
	public Collection getPrograms() {
		return programs;
	}
	/**
	 * @param programs The programs to set.
	 */
	public void setPrograms(Collection programs) {
		this.programs = programs;
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
	 * @return Returns the selectedContact.
	 */
	public String getSelectedContact() {
		return selectedContact;
	}
	/**
	 * @param selectedContact The selectedContact to set.
	 */
	public void setSelectedContact(String selectedContact) {
		this.selectedContact = selectedContact;
	}
	/**
	 * @return Returns the selectedProgram.
	 */
	public String getSelectedProgram() {
		return selectedProgram;
	}
	/**
	 * @param selectedProgram The selectedProgram to set.
	 */
	public void setSelectedProgram(String selectedProgram) {
		this.selectedProgram = selectedProgram;
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
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		if(startDate==null){
			startDateAsStr= "";
		}
		try{
			startDateAsStr=DateUtil.dateToString(startDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			startDateAsStr="";
		}
	}
	/**
	 * @return Returns the startDateAsStr.
	 */
	public String getStartDateAsStr() {
		return startDateAsStr;
	}
	/**
	 * @param startDateAsStr The startDateAsStr to set.
	 */
	public void setStartDateAsStr(String aStartDateAsStr) {
		this.startDateAsStr="";
		if(aStartDateAsStr==null || aStartDateAsStr.equals(""))
			startDate=null;
		try{
			this.startDateAsStr = aStartDateAsStr;
			startDate=DateUtil.stringToDate(startDateAsStr, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			startDate=null;
		}
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
	 * @return Returns the website.
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website The website to set.
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return Returns the statusTypes.
	 */
	public static Collection getStatusTypes() {
		return statusTypes;
	}
	/**
	 * @param statusTypes The statusTypes to set.
	 */
	public static void setStatusTypes(Collection statusTypes) {
		ServiceProviderCSCDForm.statusTypes = statusTypes;
	}
	/**
	 * @return Returns the confirmMessage.
	 */
	public String getConfirmMessage() {
		return confirmMessage;
	}
	/**
	 * @param confirmMessage The confirmMessage to set.
	 */
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}
	/**
	 * @return Returns the statusDesc.
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc The statusDesc to set.
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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
		this.statusDesc="";
		statusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_SERVICE_PROVIDER_STATUS,statusId);

		
	}
	/**
	 * @param billingAddress The billingAddress to set.
	 */
	public void setBillingAddress(IAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	/**
	 * @param mailingAddress The mailingAddress to set.
	 */
	public void setMailingAddress(IAddress mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	/**
	 * @return Returns the sameAsMailingAddress.
	 */
	public boolean isSameAsMailingAddress() {
		return sameAsMailingAddress;
	}
	/**
	 * @param sameAsMailingAddress The sameAsMailingAddress to set.
	 */
	public void setSameAsMailingAddress(boolean sameAsMailingAddress) {
		this.sameAsMailingAddress = sameAsMailingAddress;
	}
	/**
	 * @return Returns the serviceProviderAgencyId.
	 */
	public String getServiceProviderAgencyId() {
		return serviceProviderAgencyId;
	}
	/**
	 * @param serviceProviderAgencyId The serviceProviderAgencyId to set.
	 */
	public void setServiceProviderAgencyId(String serviceProviderAgencyId) {
		this.serviceProviderAgencyId = serviceProviderAgencyId;
	}

	public boolean getIsFaithBased() {
		return isFaithBased;
	}

	public void setIsFaithBased(boolean isFaithBased) {
		this.isFaithBased = isFaithBased;
	}

	public String getIsFaithBasedStr() {
		return isFaithBasedStr;
	}

	public void setIsFaithBasedStr(String isFaithBasedStr) {
		this.isFaithBasedStr = isFaithBasedStr;
	}

	public String getBillingAddressStatus() {
		return billingAddressStatus;
	}

	public void setBillingAddressStatus(String billingAddressStatus) {
		this.billingAddressStatus = billingAddressStatus;
	}

	public String getMailingAddressStatus() {
		return mailingAddressStatus;
	}

	public void setMailingAddressStatus(String mailingAddressStatus) {
		this.mailingAddressStatus = mailingAddressStatus;
	}
}// END CLASS
