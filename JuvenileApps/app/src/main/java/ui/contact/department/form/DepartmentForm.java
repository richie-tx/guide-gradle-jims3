/*
 * Created on Aug 25, 2005
 *
 * Leslie Deen	8/21/2007	Defect #44542 Changed Grits Indicator to YES and NO (all caps)
 * 
 */
package ui.contact.department.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import messaging.agency.UpdateContactEvent;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.contact.department.LoadManageDepartmentCodeTables;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DepartmentForm extends ActionForm
{

		private String action;    
		private String accessType;  // Display name of selected id value
		private String accessTypeId; // Drop down code and selected id value
		private String activationDate;
		private String activationTime;
		private String county;     // Display name of selected id value
		private String countyId;      // Drop down code and selected id value
		private String departmentId;
		private String departmentName;
		private String agencyStatus;   // Display name of selected id value
		private String agencyStatusId; //Drop down code and selected value
		private String comments;
		private String orgCode;
		private PhoneNumber departmentFaxNumber=new PhoneNumber("");
		private String gritsInd="";
		private String grits="";
		private boolean inUse=false;
		private Address mailingAddress;
		
		private String originatingAgencyId;    // Drop down code and selected id value
		private String originatingAgency;       // Display name of selected id value
		private String createOfficerProfileInd; // Indicator used to Allow officer profile creation.
		private PhoneNumber departmentPhoneNumber=new PhoneNumber("");
		private String selectedDept;
		private String setcicAccess;     // Display name of selected id value
		private String setcicAccessId; // Drop down code and selected id value
		private String setcicAccessInd; 
		private Name setcicContactName=new Name();
		private String setcicContactId="";
		private String setcicDate;
		private String setcicInactiveDate;
		private String setcicInactiveTime;
		private PhoneNumber setcicPhoneNumber=new PhoneNumber("");
		private String setcicRenewDate;
		private String setcicRenewTime;
		private String setcicTime;
		private String status;         // Display name of selected id value
		private String statusId;           // Drop down code and selected id value
		private String subscriberCivilActivationDate;
		private String subscriberCivilActivationTime;
		private String subscriberCivilTerminationDate;
		private String subscriberCivilTerminationTime;
		private String subscriberCriminalActivationDate;
		private String subscriberCriminalActivationTime;
		private String subscriberCriminalTerminationDate;
		private String subscriberCriminalTerminationTime;
		private String terminationDate;
		private String terminationTime;
		private PhoneNumber warrantConfPhoneNumber=new PhoneNumber("");

		// Physical ADDRESS RELATED
		private Address physicalAddress;
		
		//	SecticBilling ADDRESS RELATED
		private Address setcicBillingAddress;
		private String setcicBillingLabelInd="N";
		private String setcicBillingLabel="";

		
		// AGENCY RELATED
		private String agencyType;         // Display name of selected id value
		private String agencyTypeId; 
		private String agencyId;
		private String agencyName;
		private String selectedAgencyId;
		
		
		//	CONTACT RELATED
		 private String contactId;
		 private String liaisonTrainingInd="N";  // Actual Value
		 private String liaisonTraining=""; // For Display
		 private String primaryContact="";
		 private String contactJobTitle;
		 boolean isUpdatable;
		 boolean isDeletable=false;
		 boolean isNameOrgDirty=false; // Used on UI to determine if name or Org is Dirty
		 
		// USER RELATED // added by DPA
		// need the name for the user as 
		// well as the contact on the same page.
		public Name userName = new Name();
		// need the userlogon id separately because both user
		// and contact info is being displayed on the same page.
		public String userLogonId;
		 
		// CONTACT RELATED
		private String logonId;  //aka userId
		private Name contactName= new Name();
		private PhoneNumber contactPhoneNumber=new PhoneNumber("");
		private String contactEmail;
		
		
		private boolean isClearCheckBoxes=false;
		

		//	Collection
		private Collection departmentList;   // List of Departments
		private Collection agencyList;       // List of Agencies  
		private Collection statusTypes;      // List of Status Types
		private Collection setcicAccessTypes;  // Sectic Access List Types
		private Collection accessTypes;        // List of Access Types
		private Collection streetTypes;        // List of Street Types
		private Collection stateList;          // List of States
		private Collection contactList;        // List of contacts
		private Collection countyList;        // List of counties
		private Collection userList;			// List of users	
		private Collection agencies;

	private void loadCodeTables(){
			LoadManageDepartmentCodeTables instance = LoadManageDepartmentCodeTables.getInstance();
			instance.setDepartmentForm(this);
	}
			
	public DepartmentForm(){
			clear();
			LoadManageDepartmentCodeTables instance = LoadManageDepartmentCodeTables.getInstance();
			instance.setDepartmentForm(this);
	}		

	public void clear(){
		departmentFaxNumber=new PhoneNumber("");
		departmentPhoneNumber=new PhoneNumber("");
		setcicContactName=new Name();
		setcicPhoneNumber=new PhoneNumber("");
		warrantConfPhoneNumber=new PhoneNumber("");
		userName = new Name();
		contactName= new Name();
		contactPhoneNumber=new PhoneNumber("");
		contactEmail="";
		logonId="";
		action="";
		  accessType="";
		  accessTypeId=""; // code
		  activationDate="";
		  activationTime="";
		  departmentId="";
		  departmentName="";
		  agencyStatus=""; // code
		  agencyStatusId=""; // code
		  comments="";
		  orgCode="";
		  county="";
		gritsInd="N";
		grits="";
		
		  mailingAddress=new Address();
		  originatingAgencyId="";
		 
				  setcicAccessId=""; // code
		 setcicDate="";
		 setcicInactiveDate="";
		  setcicInactiveTime="";
		 setcicRenewDate="";
		  setcicRenewTime="";
		  setcicTime="";
		  statusId="";
		 subscriberCivilActivationDate="";
		  subscriberCivilActivationTime="";
		 subscriberCivilTerminationDate="";
		  subscriberCivilTerminationTime="";
		 subscriberCriminalActivationDate="";
		  subscriberCriminalActivationTime="";
		 subscriberCriminalTerminationDate="";
		  subscriberCriminalTerminationTime="";
		 terminationDate="";
		  terminationTime="";
		 contactJobTitle="";
		
		// Physical ADDRESS RELATED
				physicalAddress=new Address();
		  
		//				SecticBilling ADDRESS RELATED
		setcicBillingAddress=new Address();
		
		setcicBillingLabelInd="N";
		setcicBillingLabel="";
		
		// AGENCY RELATED
		  agencyType=""; 
		  agencyTypeId=""; 
		  agencyId="";
		  agencyName="";
		  selectedAgencyId = "";
		  createOfficerProfileInd = "N";
		
		// USER RELATED
		  logonId="";
		 
		
				// CONTACT RELATED
		contactId="";
		setcicContactId = "";
		
		liaisonTrainingInd="N";
		liaisonTraining="";
		primaryContact="";
		
		isUpdatable=false;
		isDeletable=false; 
		isNameOrgDirty=false;
		
		if(departmentList != null) {
			departmentList.clear();
		}
		if(agencyList != null) {
			agencyList.clear();
		}
		if(contactList != null) {
			contactList.clear();
		}
		if(userList != null) {
			userList.clear();
		}
		if(agencies != null){
			agencies.clear();
		}

	}
	
	public void clearContactFields() {
		contactName.clear();
		contactPhoneNumber.clear();
		contactPhoneNumber.setExt("");
		contactJobTitle = "";
		primaryContact="";
		logonId = "";
		contactEmail = "";
		liaisonTrainingInd = "";
		userLogonId="";
	}
	
		
		/**
		 * @return
		 */
		public String getAccessTypeId()
		{
			return accessTypeId;
		}

		/**
		 * @return
		 */
		public String getActivationDate()
		{
			return activationDate;
		}

		/**
		 * @return
		 */
		public String getActivationTime()
		{
			return activationTime;
		}

		


		/**
		 * @return
		 */
		public Collection getAgencyList()
		{
			return agencyList;
		}

		/**
		 * @return
		 */
		public String getAgencyId()
		{
			return agencyId;
		}

		/**
		 * @return
		 */
		public String getAgencyName()
		{
			return agencyName;
		}

		/**
		 * @return
		 */
		public String getAgencyStatus()
		{
			return agencyStatus;
		}

		/**
		 * @return
		 */
		public String getAgencyStatusId()
		{
			return agencyStatusId;
		}

		/**
		 * @return
		 */
		public String getAgencyType()
		{
			return agencyType;
		}

		/**
		 * @return
		 */
		public String getAgencyTypeId()
		{
			return agencyTypeId;
		}

		
		/**
		 * @return
		 */
		public String getComments()
		{
			return comments;
		}

		/**
		 * @return
		 */
		public String getContactId()
		{
			return contactId;
		}

		
		/**
		 * @return
		 */
		public String getCounty()
		{
			return county;
		}

		/**
		 * @return
		 */
		public String getCountyId()
		{
			return countyId;
		}

	
		/**
		 * @return
		 */
		public String getDepartmentId()
		{
			return departmentId;
		}

		/**
		 * @return
		 */
		public String getDepartmentName()
		{
			return departmentName;
		}

		/**
		 * @return
		 */
		public Collection getDepartmentList()
		{
			return departmentList;
		}

		

		/**
		 * @return
		 */
		public boolean isDeletable()
		{
			return isDeletable;
		}

		/**
		 * @return
		 */
		public boolean isUpdatable()
		{
			return isUpdatable;
		}

		
		

	
		/**
		 * @return
		 */
		public String getLiaisonTrainingInd()
		{
			
			return liaisonTrainingInd;
		}
	/**
		 * 
		 * @return the current value of the liaisonTrainingInd property as yes or no
		 */
		public String getLiaisonTrainingIndAsYesNo()
						{
							if(liaisonTrainingInd==null)
								return "";
							if(liaisonTrainingInd.equalsIgnoreCase("Y"))
								return "YES";
							else
								return "NO";
						}

		/**
		 * @return
		 */
		public String getLogonId()
		{
			return logonId;
		}


		/**
		 * @return
		 */
		public String getOrgCode()
		{
			return orgCode;
		}

		/**
		 * @return
		 */
		public String getOriginatingAgencyId()
		{
			return originatingAgencyId;
		}

		



	

		/**
		 * @return
		 */
		public String getSetcicAccessId()
		{
			return setcicAccessId;
		}

		

		/**
		 * @return
		 */
		public String getSetcicDate()
		{
			return setcicDate;
		}

		/**
		 * @return
		 */
		public String getSetcicInactiveDate()
		{
			return setcicInactiveDate;
		}

		/**
		 * @return
		 */
		public String getSetcicInactiveTime()
		{
			return setcicInactiveTime;
		}

		
		/**
		 * @return
		 */
		public String getSetcicRenewDate()
		{
			return setcicRenewDate;
		}

		/**
		 * @return
		 */
		public String getSetcicRenewTime()
		{
			return setcicRenewTime;
		}

		/**
		 * @return
		 */
		public String getSetcicTime()
		{
			return setcicTime;
		}

		

		
		/**
		 * @return
		 */
		public String getSubscriberCivilActivationDate()
		{
			return subscriberCivilActivationDate;
		}

		/**
		 * @return
		 */
		public String getSubscriberCivilActivationTime()
		{
			return subscriberCivilActivationTime;
		}

		/**
		 * @return
		 */
		public String getSubscriberCivilTerminationDate()
		{
			return subscriberCivilTerminationDate;
		}

		/**
		 * @return
		 */
		public String getSubscriberCivilTerminationTime()
		{
			return subscriberCivilTerminationTime;
		}

		/**
		 * @return
		 */
		public String getSubscriberCriminalActivationDate()
		{
			return subscriberCriminalActivationDate;
		}

		/**
		 * @return
		 */
		public String getSubscriberCriminalActivationTime()
		{
			return subscriberCriminalActivationTime;
		}

		/**
		 * @return
		 */
		public String getSubscriberCriminalTerminationDate()
		{
			return subscriberCriminalTerminationDate;
		}

		/**
		 * @return
		 */
		public String getSubscriberCriminalTerminationTime()
		{
			return subscriberCriminalTerminationTime;
		}

		/**
		 * @return
		 */
		public String getTerminationDate()
		{
			return terminationDate;
		}

		/**
		 * @return
		 */
		public String getTerminationTime()
		{
			return terminationTime;
		}

		/**
		 * @return
		 */
		public String getContactJobTitle()
		{
			return contactJobTitle;
		}

		

	
		

		/**
		 * @param string
		 */
		public void setAccessTypeId(String string)
		{
			accessTypeId = string;
		}

		/**
		 * @param string
		 */
		public void setActivationDate(String string)
		{
			activationDate = string;
		}

		/**
		 * @param string
		 */
		public void setActivationTime(String string)
		{
			activationTime = string;
		}

	


	

		/**
		 * @param collection
		 */
		public void setAgencyList(Collection collection)
		{
			agencyList = collection;
		}

		/**
		 * @param string
		 */
		public void setAgencyId(String string)
		{
			agencyId = string;
		}

		/**
		 * @param string
		 */
		public void setAgencyName(String string)
		{
			agencyName = string;
		}

		/**
		 * @param string
		 */
		public void setAgencyStatus(String string)
		{
			agencyStatus = string;
		}

		/**
		 * @param string
		 */
		public void setAgencyStatusId(String string)
		{
			agencyStatusId = string;
		}

		/**
		 * @param string
		 */
		public void setAgencyType(String string)
		{
			agencyType = string;
		}

		/**
		 * @param string
		 */
		public void setAgencyTypeId(String string)
		{
			agencyTypeId = string;
		}





		/**
		 * @param string
		 */
		public void setComments(String string)
		{
			comments = string;
		}

		/**
		 * @param string
		 */
		public void setContactId(String string)
		{
			contactId = string;
		}



		/**
		 * @param string
		 */
		public void setCounty(String string)
		{
			county = string;
		}

		/**
		 * @param string
		 */
		public void setCountyId(String string)
		{
			countyId = string;
		}

	

		/**
		 * @param string
		 */
		public void setDepartmentId(String string)
		{
			departmentId = string;
		}

		/**
		 * @param string
		 */
		public void setDepartmentName(String string)
		{
			if(!(departmentName.equals(string)))
					setNameOrgDirty(true);
			departmentName = string;
		}

		/**
		 * @param collection
		 */
		public void setDepartmentList(Collection collection)
		{
			departmentList = collection;
		}

		

		/**
		 * @param b
		 */
		public void setDeletable(boolean b)
		{
			isDeletable = b;
		}

		/**
		 * @param b
		 */
		public void setUpdatable(boolean b)
		{
			isUpdatable = b;
		}



	

		/**
		 * @param string
		 */
		public void setLiaisonTrainingInd(String string)
		{
			liaisonTrainingInd = string;
		}

		/**
		 * @param string
		 */
		public void setLogonId(String string)
		{
			logonId = string;
		}







	

		/**
		 * @param string
		 */
		public void setOrgCode(String string)
		{
			if(!(orgCode.equals(string)))
				setNameOrgDirty(true);
			orgCode = string;
		}

		/**
		 * @param string
		 */
		public void setOriginatingAgencyId(String string)
		{
			originatingAgencyId = string;
		}

	

	
		/**
		 * @param string
		 */
		public void setSetcicAccessId(String string)
		{
			setcicAccessId = string;
		}

	

		/**
		 * @param string
		 */
		public void setSetcicDate(String string)
		{
			setcicDate = string;
		}

		/**
		 * @param string
		 */
		public void setSetcicInactiveDate(String string)
		{
			setcicInactiveDate = string;
		}

		/**
		 * @param string
		 */
		public void setSetcicInactiveTime(String string)
		{
			setcicInactiveTime = string;
		}



		/**
		 * @param string
		 */
		public void setSetcicRenewDate(String string)
		{
			setcicRenewDate = string;
		}

		/**
		 * @param string
		 */
		public void setSetcicRenewTime(String string)
		{
			setcicRenewTime = string;
		}

		/**
		 * @param string
		 */
		public void setSetcicTime(String string)
		{
			setcicTime = string;
		}

	

	

		/**
		 * @param string
		 */
		public void setSubscriberCivilActivationDate(String string)
		{
			subscriberCivilActivationDate = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCivilActivationTime(String string)
		{
			subscriberCivilActivationTime = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCivilTerminationDate(String string)
		{
			subscriberCivilTerminationDate = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCivilTerminationTime(String string)
		{
			subscriberCivilTerminationTime = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCriminalActivationDate(String string)
		{
			subscriberCriminalActivationDate = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCriminalActivationTime(String string)
		{
			subscriberCriminalActivationTime = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCriminalTerminationDate(String string)
		{
			subscriberCriminalTerminationDate = string;
		}

		/**
		 * @param string
		 */
		public void setSubscriberCriminalTerminationTime(String string)
		{
			subscriberCriminalTerminationTime = string;
		}

		/**
		 * @param string
		 */
		public void setTerminationDate(String string)
		{
			terminationDate = string;
		}

		/**
		 * @param string
		 */
		public void setTerminationTime(String string)
		{
			terminationTime = string;
		}

		/**
		 * @param string
		 */
		public void setContactJobTitle(String string)
		{
			contactJobTitle = string;
		}



	

		/**
		 * @return
		 */
		public String getStatusId()
		{
			return statusId;
		}

		/**
		 * @param string
		 */
		public void setStatusId(String string)
		{
			statusId = string;
		}

		/**
		 * @return
		 */
		public Collection getStatusTypes()
		{
			return statusTypes;
		}

		/**
		 * @param collection
		 */
		public void setStatusTypes(Collection collection)
		{
			statusTypes = collection;
		}

		/**
		 * @param collection
		 */
		public void setSetcicAccessTypes(Collection collection)
		{
			setcicAccessTypes = collection;
		}

		/**
		 * @return
		 */
		public Collection getSetcicAccessTypes()
		{
			return setcicAccessTypes;
		}

		/**
		 * @return
		 */
		public String getAction()
		{
			return action;
		}

		/**
		 * @param string
		 */
		public void setAction(String string)
		{
			action = string;
		}

		/**
		 * @return
		 */
		public String getAccessType()
		{
			return accessType;
		}

		
		/**
		 * @return
		 */
		public Collection getStateList()
		{
			return stateList;
		}

		/**
		 * @return
		 */
		public Collection getStreetTypes()
		{
			return streetTypes;
		}

		/**
		 * @param collection
		 */
		public void setStateList(Collection collection)
		{
			stateList = collection;
		}

		/**
		 * @param collection
		 */
		public void setStreetTypes(Collection collection)
		{
			streetTypes = collection;
		}

		/**
		 * @return
		 */
		public Collection getContactList()
		{
			if(contactList == null) {
				contactList = new ArrayList();
			}
			return contactList;
		}

		/**
		 * @param collection
		 */
		public void setContactList(Collection collection)
		{
			contactList = collection;
		}
		
		public void addContactToContactList(UpdateContactEvent obj) {
			contactList.add(obj);
		}


		/**
		 * @return
		 */
		public Collection getCountyList()
		{
			return countyList;
		}

		/**
		 * @param collection
		 */
		public void setCountyList(Collection collection)
		{
			countyList = collection;
		}

		/**
		 * @return
		 */
		public Collection getAccessTypes()
		{
			if(accessTypes==null){
				loadCodeTables();
			}			
			return accessTypes;
		}

		/**
		 * @param string
		 */
		public void setAccessType(String string)
		{
			accessType = string;
		}

		/**
		 * @param collection
		 */
		public void setAccessTypes(Collection collection)
		{
			accessTypes = collection;
		}


		/**
		 * @return
		 */
		public String getGritsInd()
		{
			return gritsInd;
		}

	

		/**
		 * @return
		 */
		public String getOriginatingAgency()
		{
			return originatingAgency;
		}

		/**
		 * @return
		 */
		public String getSetcicAccess()
		{
			return setcicAccess;
		}

		/**
		 * @return
		 */
		public String getStatus()
		{
			return status;
		}

		/**
		 * @param string
		 */
		public void setGritsInd(String string)
		{
			gritsInd = string;
		}

	

		/**
		 * @param string
		 */
		public void setOriginatingAgency(String string)
		{
			originatingAgency = string;
		}

		/**
		 * @param string
		 */
		public void setSetcicAccess(String string)
		{
			setcicAccess = string;
		}

		/**
		 * @param string
		 */
		public void setStatus(String string)
		{
			status = string;
		}

		/**
		 * @return
		 */
		public String getSetcicAccessInd()
		{
			return setcicAccessInd;
		}

		/**
		 * @param string
		 */
		public void setSetcicAccessInd(String string)
		{
			setcicAccessInd = string;
		}

			/**
		 * @return
		 */
		public String getContactEmail()
		{
			return contactEmail;
		}

		/**
		 * @param string
		 */
		public void setContactEmail(String string)
		{
			contactEmail = string;
		}


		/**
		 * @return
		 */
		public String getSetcicBillingLabelInd()
		{
			return setcicBillingLabelInd;
		}

		/**
		 * @param string
		 */
		public void setSetcicBillingLabelInd(String string)
		{
			setcicBillingLabelInd = string;
		}


		/**
		 * @return
		 */
		public Name getUserName()
		{
			return userName;
		}

		/**
		 * @param name
		 */
		public void setUserName(Name name)
		{
			userName = name;
		}

		/**
		 * @return
		 */
		public Collection getUserList()
		{
			return userList;
		}

		/**
		 * @param collection
		 */
		public void setUserList(Collection collection)
		{
			userList = collection;
		}

		/**
		 * @return
		 */
		public Address getMailingAddress()
		{
			mailingAddress.setAddressTypeId("M");
			return mailingAddress;
		}

		/**
		 * @return
		 */
		public Address getPhysicalAddress()
		{
			physicalAddress.setAddressTypeId("P");
			return physicalAddress;
		}

		/**
		 * @return
		 */
		public Address getSetcicBillingAddress()
		{
			setcicBillingAddress.setAddressTypeId("S");
			return setcicBillingAddress;
		}

		/**
		 * @param event
		 */
		public void setMailingAddress(Address event)
		{
			
			mailingAddress = event;
		}

		/**
		 * @param event
		 */
		public void setPhysicalAddress(Address event)
		{
			physicalAddress = event;
		}

		/**
		 * @param event
		 */
		public void setSetcicBillingAddress(Address event)
		{
			setcicBillingAddress = event;
		}

		/**
		 * @return
		 */
		public Name getContactName()
		{
			return contactName;
		}

		/**
		 * @return
		 */
		public PhoneNumber getContactPhoneNumber()
		{
			return contactPhoneNumber;
		}

		/**
		 * @return
		 */
		public PhoneNumber getDepartmentPhoneNumber()
		{
			return departmentPhoneNumber;
		}

		/**
		 * @return
		 */
		public PhoneNumber getDepartmentFaxNumber()
		{
			return departmentFaxNumber;
		}

		/**
		 * @return
		 */
		public Name getSetcicContactName()
		{
			return setcicContactName;
		}

		/**
		 * @return
		 */
		public PhoneNumber getSetcicPhoneNumber()
		{
			return setcicPhoneNumber;
		}

		/**
		 * @return
		 */
		public PhoneNumber getWarrantConfPhoneNumber()
		{
			return warrantConfPhoneNumber;
		}

		/**
		 * @param name
		 */
		public void setContactName(Name name)
		{
			contactName = name;
		}

		/**
		 * @param number
		 */
		public void setContactPhoneNumber(PhoneNumber number)
		{
			contactPhoneNumber = number;
		}

		/**
		 * @param number
		 */
		public void setDepartmentPhoneNumber(PhoneNumber number)
		{
			departmentPhoneNumber = number;
		}

		/**
		 * @param number
		 */
		public void setDepartmentFaxNumber(PhoneNumber number)
		{
			departmentFaxNumber = number;
		}

		/**
		 * @param name
		 */
		public void setSetcicContactName(Name name)
		{
			setcicContactName = name;
		}

		/**
		 * @param number
		 */
		public void setSetcicPhoneNumber(PhoneNumber number)
		{
			setcicPhoneNumber = number;
		}

		/**
		 * @param number
		 */
		public void setWarrantConfPhoneNumber(PhoneNumber number)
		{
			warrantConfPhoneNumber = number;
		}

		/**
		 * @return
		 */
		public String getUserLogonId()
		{
			return userLogonId;
		}

		/**
		 * @param string
		 */
		public void setUserLogonId(String string)
		{
			userLogonId = string;
		}

		/**
		 * @return
		 */
		public String getSelectedDept()
		{
			return selectedDept;
		}

		/**
		 * @param string
		 */
		public void setSelectedDept(String string)
		{
			selectedDept = string;
		}

		/**
		 * @return
		 */
		public String getLiaisonTraining()
		{
			if(liaisonTrainingInd==null)
							return "";
			if(liaisonTrainingInd.equalsIgnoreCase("N") || liaisonTrainingInd.equalsIgnoreCase("NO") || liaisonTrainingInd.equalsIgnoreCase("false"))
				setLiaisonTraining("NO");
			else if (liaisonTrainingInd.equalsIgnoreCase("Y") || liaisonTrainingInd.equalsIgnoreCase("YES") || liaisonTrainingInd.equalsIgnoreCase("true"))
				setLiaisonTraining("YES");
			else
				return "";
			return liaisonTraining;
		}

		/**
		 * @param string
		 */
		public void setLiaisonTraining(String string)
		{
			liaisonTraining = string;
		}
	
		private void checkBoxFix(){
			if(contactList!=null && contactList.size()>0){
				Iterator iter=contactList.iterator();
				UpdateContactEvent contact;
				while(iter.hasNext()){
					contact=(UpdateContactEvent)iter.next();
					contact.setDeletable(false);
				}
			}
		}

		/* (non-Javadoc)
		 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
		 */
		public void reset(ActionMapping arg0, HttpServletRequest request)
		{
			  Object obj=request.getAttribute("clearCheckBoxes");
			  if(obj!=null){
				  checkBoxFix();	
			  }
			  obj=null;
			  obj=request.getParameter("clearCheckBoxes");
			  if(obj!=null){
				  checkBoxFix();	
			  }	
			super.reset(arg0, request);
		}

		/**
		 * @return
		 */
		public boolean isNameOrgDirty()
		{
			return isNameOrgDirty;
		}

		/**
		 * @param b
		 */
		public void setNameOrgDirty(boolean b)
		{
			isNameOrgDirty = b;
		}

		/**
		 * @return
		 */
		public String getGrits()
		{
			if(gritsInd==null)
				return "";
			if(gritsInd.equalsIgnoreCase("N") || gritsInd.equalsIgnoreCase("NO"))
				setGrits("NO");
			else if (gritsInd.equalsIgnoreCase("Y") || gritsInd.equalsIgnoreCase("YES"))
				setGrits("YES");
			else
				return "";
			return grits;
		}

		/**
		 * @param string
		 */
		public void setGrits(String string)
		{
			grits = string;
		}

		

		

		/**
		 * @return
		 */
		public boolean isClearCheckBoxes()
		{
			return isClearCheckBoxes;
		}

		/**
		 * @return
		 */
		public String getSetcicBillingLabel()
		{
			if(setcicBillingLabelInd==null)
				return "";
			if(setcicBillingLabelInd.equalsIgnoreCase("N") || setcicBillingLabelInd.equalsIgnoreCase("NO"))
				setSetcicBillingLabel("NO");
			else if (setcicBillingLabelInd.equalsIgnoreCase("Y") || setcicBillingLabelInd.equalsIgnoreCase("YES"))
				setSetcicBillingLabel("YES");
			else
				return "";
			return setcicBillingLabel;
		}

		/**
		 * @param b
		 */
		public void setClearCheckBoxes(boolean b)
		{
			isClearCheckBoxes = b;
		}

		/**
		 * @param string
		 */
		public void setSetcicBillingLabel(String string)
		{
			setcicBillingLabel = string;
		}

		/**
		 * @return
		 */
		public boolean isInUse()
		{
			return inUse;
		}

		/**
		 * @param b
		 */
		public void setInUse(boolean b)
		{
			inUse = b;
		}
		/**
		 * @return
		 */
		public String getSetcicContactId()
		{
			return setcicContactId;
		}

		/**
		 * @param string
		 */
		public void setSetcicContactId(String string)
		{
			setcicContactId = string;
		}

		/**
		 * @return
		 */
		public String getSelectedAgencyId()
		{
			return selectedAgencyId;
		}

		/**
		 * @param string
		 */
		public void setSelectedAgencyId(String string)
		{
			selectedAgencyId = string;
		}

		/**
		 * @return
		 */
		public Collection getAgencies()
		{
			return agencies;
		}

		/**
		 * @param collection
		 */
		public void setAgencies(Collection collection)
		{
			agencies = collection;
		}

		/**
		 * @return
		 */
		public String getPrimaryContact()
		{
			return primaryContact;
		}

		/**
		 * @param string
		 */
		public void setPrimaryContact(String string)
		{
			primaryContact = string;
		}
		
	public String getPrimaryContactAsYesNo()
			{
				if(primaryContact.equalsIgnoreCase("Y"))
					return "YES";
				else
					return "NO";
			}


		/**
		 * @return
		 */
		public String getCreateOfficerProfileInd()
		{
			return createOfficerProfileInd;
		}

		/**
		 * @param string
		 */
		public void setCreateOfficerProfileInd(String string)
		{
			createOfficerProfileInd = string;
		}

}// END OF CLASS

