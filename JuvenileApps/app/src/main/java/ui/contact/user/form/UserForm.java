package ui.contact.user.form;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author dnikolis
 * 
 * This form contains all the attributes needed 
 * add, update, delete, and retrieve a user.
 * 
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/16/03		Added Javadocs
 * 
 */
public class UserForm extends ActionForm
{
	private final static String DISPLAY_CREATE_USER_PATH = "displayCreateUser";
	private final static String DISPLAY_SEARCH_USER_PATH = "displayUserSearch";
	private String actionInd;
	private String activationDate;
	private String activationDateTo;
	private String activationTime;
	private String agencyId;
	private String agencyName;
	private String agencyTransferDate;
	private String agencyTransferTime;
	private String comments;
	private String createDate;
	private String createTime;
	private String creatorFirstName;
	private String creatorId;
	private String creatorLastName;
	private String creatorName;
	private String dateOfBirth;
	private String dateOfBirthToDate;
	private String deactivatorFirstName;
	private String deactivatorId;
	private String deactivatorLastName;
	private String departmentName;
	private String deptCode;
	private String divisionId;
	private String divisionName;
	private String email;
	private String expirationDate;
	private String firstName;
	private String genericLogonInd;
	private String lastLoginDate;
	private String lastName;	
	private String logonId;
	private String middleName;
	private String name;
	private String phoneNo1;
	private String phoneNo2;
	private String phoneNo3;
	private String phoneNum;
	private String requestorFirstName;
	private String requestorLastName;
	private String requestorName;
	private String roleId;
	private String selectedLogon;
	private String selection;
	private String ssn;
	private String ssn1;
	private String ssn2;
	private String ssn3;
	private String suspendDate;
	private String suspendDateTo;
	//private String suspendTime;
	private Boolean trainingInd;
	private String transactionDate;
	private String transactionTime;
	private String transactionUserFirstName;
	private String transactionUserLastName;
	private String transactionUserName;
	private String userId;
	private String userStatus;
	
	//store Search criteria for Back
	private String searchLastName;
	private String searchFirstName;
	private String searchMiddleName;
	private String searchLogonId;
	private String searchDateOfBirth;
	private String searchDateOfBirthToDate;
	private String searchAgencyId;
	private String searchDivisionId;
	private String searchSSN1;
	private String searchSSN2;
	private String searchSSN3;
	
	
	// non-user entry fields		
	private String action;
	private String searchResultSize;

	// collections for drop down lists - need to clear in Submit action
	private Collection profiles;
	private Collection agencies;
	private Collection divisions;
	private Collection availableRoles;
	private Collection associatedRoles;
	private Collection histories;
	private String[] rolesToAssociate;
	private String[] unassociatedRoles;

	/**
	* Constructor for the UserForm
	*/
	public UserForm()
	{
		super();
	}

	/**
	 * 
	 */
	public void clear()
	{
		this.actionInd = null;
		this.activationDate = null;
		this.activationDateTo = null;
		this.activationTime = null;
		this.agencies = null;
		this.agencyId = null;
		this.agencyName = null;
		this.agencyTransferDate = null;
		this.agencyTransferTime = null;
		this.associatedRoles = null;
		this.availableRoles = null;
		this.comments = null;
		this.createDate = null;
		this.createTime = null;
		this.creatorFirstName = null;
		this.creatorId = null;
		this.creatorLastName = null;
		this.dateOfBirth = null;
		this.dateOfBirthToDate = null;
		this.deactivatorFirstName = null;
		this.deactivatorId = null;
		this.deactivatorLastName = null;
		this.deptCode = null;
		this.divisionId = null;
		this.divisionName = null;
		this.divisions = null;
		this.email = null;
		this.expirationDate = null;
		this.firstName = null;
		this.genericLogonInd = null;
		this.lastLoginDate = null;
		this.lastName = null;
		this.logonId = null;
		this.middleName = null;
		this.name = null;
		this.phoneNo1 = null;
		this.phoneNo2 = null;
		this.phoneNo3 = null;
		this.phoneNum = null;
		this.requestorFirstName = null;
		this.requestorLastName = null;
		this.roleId = null;
		this.rolesToAssociate = null;
		//this.searchResultSize = null;
		this.selectedLogon = null;
		this.selection = null;
		this.ssn = null;
		this.ssn1 = null;
		this.ssn2 = null;
		this.ssn3 = null;
		this.suspendDate = null;
		this.suspendDateTo = null;
		this.trainingInd = null;
		this.transactionDate = null;
		this.transactionTime = null;
		this.transactionUserFirstName = null;
		this.transactionUserLastName = null;
		this.transactionUserName = null;
		this.unassociatedRoles = null;
		this.userId = null;
		this.userStatus = null;
	}

	/**
	 * @see ActionForm#reset(ActionMapping, HttpServletRequest)
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{

		super.reset(aMapping, aRequest);
		/*this.roleGroupId = null;
		this.availableRoles = null;
		this.associatedRoles = null;*/
		this.rolesToAssociate = null;
		this.unassociatedRoles = null;
		String path = aMapping.getPath();

		if ((path != null)
			&& (((path.indexOf(DISPLAY_CREATE_USER_PATH) > 0) || (path.indexOf(DISPLAY_SEARCH_USER_PATH) > 0))))
		{
			this.actionInd = null;
			this.activationDate = null;
			this.activationDateTo = null;
			this.activationTime = null;
			this.agencies = null;
			this.agencyId = null;
			this.agencyName = null;
			this.agencyTransferDate = null;
			this.agencyTransferTime = null;
			this.associatedRoles = null;
			this.availableRoles = null;
			this.comments = null;
			this.createDate = null;
			this.createTime = null;
			this.creatorFirstName = null;
			this.creatorId = null;
			this.creatorLastName = null;
			this.dateOfBirth = null;
			this.dateOfBirthToDate = null;
			this.deactivatorFirstName = null;
			this.deactivatorId = null;
			this.deactivatorLastName = null;
			this.deptCode = null;
			this.divisionId = null;
			this.divisionName = null;
			this.divisions = null;
			this.email = null;
			this.expirationDate = null;
			this.firstName = null;
			this.genericLogonInd = null;
			this.lastLoginDate = null;
			this.lastName = null;
			this.logonId = null;
			this.middleName = null;
			this.name = null;
			this.phoneNo1 = null;
			this.phoneNo2 = null;
			this.phoneNo3 = null;
			this.phoneNum = null;
			this.requestorFirstName = null;
			this.requestorLastName = null;
			this.roleId = null;
			this.rolesToAssociate = null;
			this.searchResultSize = null;
			this.selectedLogon = null;
			this.selection = null;
			this.ssn = null;
			this.ssn1 = null;
			this.ssn2 = null;
			this.ssn3 = null;
			this.suspendDate = null;
			this.suspendDateTo = null;
			this.transactionDate = null;
			this.transactionTime = null;
			this.transactionUserFirstName = null;
			this.transactionUserLastName = null;
			this.transactionUserName = null;
			this.unassociatedRoles = null;
			this.userId = null;
			this.userStatus = null;

		}
		/*{
			Object agenciesObj = aRequest.getAttribute("agencies");
			if (agenciesObj != null)
			{
				this.setAgencies((Collection) agenciesObj);
			}
		}*/
	}
	/**	public void reset(ActionMapping mapping, HttpServletRequest aRequest) {
					Object agenciesObj = aRequest.getAttribute("agencies");
					if(agenciesObj != null) {
						this.setAgencies((Collection) agenciesObj);
					}
				}
	**/
	/**
	 * @return String
	 */
	public String getActivationDate()
	{
		return activationDate;
	}

	/**
	 * @return String
	 */
	public String getActivationDateTo()
	{
		return activationDateTo;
	}

	/**
	 * @return String
	 */
	public Collection getAgencies()
	{
		return agencies;
	}

	/**
	 * Returns the agency.
	 * @return String
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return String
	 */
	public String getAgencyTransferDate()
	{
		return agencyTransferDate;
	}

	/**
	 * Returns the comments.
	 * @return String
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return String
	 */
	public String getCreateDate()
	{
		return createDate;
	}

	/**
	 * Returns the dob.
	 * @return String
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * Returns the divisionID.
	 * @return String
	 */
	public String getDivisionId()
	{
		return divisionId;
	}

	/**
	 * Returns the email.
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Returns the firstName.
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Returns the lastName.
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Returns the middleName.
	 * @return String
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the phoneNo1.
	 * @return String
	 */
	public String getPhoneNo1()
	{
		return phoneNo1;
	}

	/**
	 * Returns the phoneNo2.
	 * @return String
	 */
	public String getPhoneNo2()
	{
		return phoneNo2;
	}

	/**
	 * Returns the phoneNo3.
	 * @return String
	 */
	public String getPhoneNo3()
	{
		return phoneNo3;
	}

	/**
	 * @return String
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * Returns the selectedLogon.
	 * @return String
	 */
	public String getSelectedLogon()
	{
		return selectedLogon;
	}

	/**
	 * Returns the selection.
	 * @return String
	 */
	public String getSelection()
	{
		return selection;
	}

	/**
	 * Returns the ssn.
	 * @return String
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * Returns the ssn1.
	 * @return String
	 */
	public String getSsn1()
	{
		return ssn1;
	}

	/**
	 * Returns the ssn2.
	 * @return String
	 */
	public String getSsn2()
	{
		return ssn2;
	}

	/**
	 * Returns the ssn3.
	 * @return String
	 */
	public String getSsn3()
	{
		return ssn3;
	}

	/**
	 * @return String
	 */
	public String getSuspendDate()
	{
		return suspendDate;
	}

	/**
	 * @return String
	 */
	public String getSuspendDateTo()
	{
		return suspendDateTo;
	}

	/**
	 * @return String
	 */
	public Boolean getTrainingInd()
	{
		return trainingInd;
	}
	/**
	 * Returns the userId.
	 * @return String
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @return String
	 */
	public String getUserStatus()
	{
		return userStatus;
	}

	/**
	 * @param string
	 */
	public void setActivationDate(String aactivationDate)
	{
		this.activationDate = aactivationDate;
	}

	/**
	 * @param string
	 */
	public void setActivationDateTo(String aactivationDateTo)
	{
		this.activationDateTo = aactivationDateTo;
	}

	/**
	 * @param collection
	 */
	public void setAgencies(Collection collection)
	{
		agencies = collection;
	}


	/**
	 * @param aagencyId
	 */
	public void setAgencyId(String aagencyId)
	{
		this.agencyId = aagencyId;
	}

	/**
	 * @param string
	 */
	public void setAgencyTransferDate(String aagencyTransferDate)
	{
		this.agencyTransferDate = aagencyTransferDate;
	}

	/**
	 * @param comments
	 */
	public void setComments(String acomments)
	{
		this.comments = acomments;
	}

	/**
	 * @param string
	 */
	public void setCreateDate(String acreateDate)
	{
		this.createDate = acreateDate;
	}

	/**
	 * @param dob
	 */
	public void setDateOfBirth(String adateOfBirth)
	{
		this.dateOfBirth = adateOfBirth;
	}

	/**
	 * @param division
	 */
	public void setDivisionId(String adivisionId)
	{
		this.divisionId = adivisionId;
	}

	/**
	 * Sets the email.
	 * @param email
	 */
	public void setEmail(String aemail)
	{
		this.email = aemail;
	}

	/**
	 * Sets the firstName.
	 * @param firstName
	 */
	public void setFirstName(String afirstName)
	{
		this.firstName = afirstName;
	}

	/**
	 * Sets the lastName.
	 * @param lastName
	 */
	public void setLastName(String alastName)
	{
		this.lastName = alastName;
	}

	/**
	 * Sets the middleName.
	 * @param middleName 
	 */
	public void setMiddleName(String amiddleName)
	{
		this.middleName = amiddleName;
	}

	/**
	 * Sets the name.
	 * @param name
	 */
	public void setName(String aname)
	{
		this.name = aname;
	}

	/**
	 * Sets the phoneNo1.
	 * @param phoneNo1
	 */
	public void setPhoneNo1(String aphoneNo1)
	{
		this.phoneNo1 = aphoneNo1;
	}

	/**
	 * Sets the phoneNo2.
	 * @param phoneNo2
	 */
	public void setPhoneNo2(String aphoneNo2)
	{
		this.phoneNo2 = aphoneNo2;
	}

	/**
	 * Sets the phoneNo3.
	 * @param  phoneNo3
	 */
	public void setPhoneNo3(String aphoneNo3)
	{
		this.phoneNo3 = aphoneNo3;
	}

	/**
	 * @param aphoneNum
	 */
	public void setPhoneNum(String aphoneNum)
	{
		this.phoneNum = aphoneNum;
	}

	/**
	 * Sets the selectedLogon.
	 * @param selectedLogon
	 */
	public void setSelectedLogon(String aselectedLogon)
	{
		this.selectedLogon = aselectedLogon;
	}

	/**
	 * Sets the selection.
	 * @param aselection
	 */
	public void setSelection(String aselection)
	{
		this.selection = aselection;
	}

	/**
	 * Sets the ssn.
	 * @param ssn
	 */
	public void setSsn(String assn)
	{
		this.ssn = assn;
	}

	/**
	 * Sets the ssn1.
	 * @param ssn1
	 */
	public void setSsn1(String assn1)
	{
		this.ssn1 = assn1;
	}

	/**
	 * Sets the ssn2.
	 * @param ssn2
	 */
	public void setSsn2(String assn2)
	{
		this.ssn2 = assn2;
	}

	/**
	 * Sets the ssn3.
	 * @param ssn3
	 */
	public void setSsn3(String assn3)
	{
		this.ssn3 = assn3;
	}

	/**
	 * @param string
	 */
	public void setSuspendDate(String asuspendDate)
	{
		this.suspendDate = asuspendDate;
	}

	/**
	 * @param string
	 */
	public void setSuspendDateTo(String asuspendDateTo)
	{
		this.suspendDateTo = asuspendDateTo;
	}

	/**
	 * @param boolean1
	 */
	public void setTrainingInd(Boolean boolean1)
	{
		trainingInd = boolean1;
	}

	/**
	 * Sets the userId.
	 * @param userId
	 */
	public void setUserId(String auserId)
	{
		this.userId = auserId;
	}

	/**
	 * @param string
	 */
	public void setUserStatus(String auserStatus)
	{
		this.userStatus = auserStatus;
	}

	/**
	 * @return String
	 */
	public String getExpirationDate()
	{
		return expirationDate;
	}

	/**
	 * @param string
	 */
	public void setExpirationDate(String string)
	{
		expirationDate = string;
	}

	/**
	 * @return Collection
	 */
	public Collection getAssociatedRoles()
	{
		return associatedRoles;
	}

	/**
	 * @return Collection
	 */
	public Collection getAvailableRoles()
	{
		return availableRoles;
	}

	/**
	 * @return String[]
	 */
	public String[] getRolesToAssociate()
	{
		return rolesToAssociate;
	}

	/**
	 * @return String[]
	 */
	public String[] getUnassociatedRoles()
	{
		return unassociatedRoles;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedRoles(Collection collection)
	{
		associatedRoles = collection;
	}

	/**
	 * @param collection
	 */
	public void setAvailableRoles(Collection collection)
	{
		availableRoles = collection;
	}

	/**
	 * @param strings
	 */
	public void setRolesToAssociate(String[] strings)
	{
		rolesToAssociate = strings;
	}

	/**
	 * @param strings
	 */
	public void setUnassociatedRoles(String[] strings)
	{
		unassociatedRoles = strings;
	}

	/**
	 * @return String
	 */ 
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @param agencyName
	 */
	public void setAgencyName(String aagencyName)
	{
		this.agencyName = aagencyName;
	}

	/**
	 * @return String
	 */
	public String getGenericLogonInd()
	{
		return genericLogonInd;
	}

	/**
	 * @param string
	 */
	public void setGenericLogonInd(String string)
	{
		genericLogonInd = string;
	}

	/**
	 * @return String 
	 */
	public String getRequestorFirstName()
	{
		return requestorFirstName;
	}

	/**
	 * @return String
	 */
	public String getRequestorLastName()
	{
		return requestorLastName;
	}

	/**
	 * @param string
	 */
	public void setRequestorFirstName(String string)
	{
		requestorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setRequestorLastName(String string)
	{
		requestorLastName = string;
	}

	/**
	 * @return String
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
	 * @return String
	 */
	public Collection getDivisions()
	{
		return divisions;
	}

	/**
	 * @return String
	 */
	public String getSearchResultSize()
	{
		return searchResultSize;
	}

	/**
	 * @param collection
	 */
	public void setDivisions(Collection collection)
	{
		divisions = collection;
	}

	/**
	 * @param string
	 */
	public void setSearchResultSize(String string)
	{
		searchResultSize = string;
	}

	/**
	 * @return String
	 */
	public String getDateOfBirthToDate()
	{
		return dateOfBirthToDate;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirthToDate(String string)
	{
		dateOfBirthToDate = string;
	}

	/**
	 * @return String
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 * @return String
	 */
	public String getCreatorFirstName()
	{
		return creatorFirstName;
	}

	/**
	 * @return String
	 */ 
	public String getCreatorId()
	{
		return creatorId;
	}

	/**
	 * @return String
	 */
	public String getCreatorLastName()
	{
		return creatorLastName;
	}

	/**
	 * @param string
	 */
	public void setCreatorFirstName(String string)
	{
		creatorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setCreatorId(String string)
	{
		creatorId = string;
	}

	/**
	 * @param string
	 */
	public void setCreatorLastName(String string)
	{
		creatorLastName = string;
	}

	/**
	 * @return String
	 */
	public String getActivationTime()
	{
		return activationTime;
	}

	/**
	 * @return String
	 */
	public String getDeactivatorFirstName()
	{
		return deactivatorFirstName;
	}

	/**
	 * @return String
	 */
	public String getDeactivatorId()
	{
		return deactivatorId;
	}

	/**
	 * @return String
	 */
	public String getDeactivatorLastName()
	{
		return deactivatorLastName;
	}

	/**
	 * @return String
	 */
	public String getDeptCode()
	{
		return deptCode;
	}

	/**
	 * @return String
	 */
	public String getDivisionName()
	{
		return divisionName;
	}

	/**
	 * @return String
	 */
	public String getLastLoginDate()
	{
		return lastLoginDate;
	}

	/**
	 * @param string
	 */
	public void setActivationTime(String string)
	{
		activationTime = string;
	}

	/**
	 * @param string
	 */
	public void setDeactivatorFirstName(String string)
	{
		deactivatorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setDeactivatorId(String string)
	{
		deactivatorId = string;
	}

	/**
	 * @param string
	 */
	public void setDeactivatorLastName(String string)
	{
		deactivatorLastName = string;
	}

	/**
	 * @param string
	 */
	public void setDeptCode(String string)
	{
		deptCode = string;
	}

	/**
	 * @param string
	 */
	public void setDivisionName(String string)
	{
		divisionName = string;
	}

	/**
	 * @param string
	 */
	public void setLastLoginDate(String string)
	{
		lastLoginDate = string;
	}

	/**
	 * @return String
	 */
	public String getCreateTime()
	{
		return createTime;
	}

	/**
	 * @return String
	 */
	public String getCreatorName()
	{
		return creatorName;
	}

	/**
	 * @return String
	 */
	public String getRequestorName()
	{
		return requestorName;
	}

	/**
	 * @param string
	 */
	public void setCreateTime(String string)
	{
		createTime = string;
	}

	/**
	 * @param string
	 */
	public void setCreatorName(String string)
	{
		creatorName = string;
	}

	/**
	 * @param string
	 */
	public void setRequestorName(String string)
	{
		requestorName = string;
	}

	/**
	 * @return String
	 */
	public String getTransactionDate()
	{
		return transactionDate;
	}

	/**
	 * @return String
	 */
	public String getTransactionTime()
	{
		return transactionTime;
	}

	/**
	 * @param string
	 */
	public void setTransactionDate(String string)
	{
		transactionDate = string;
	}

	/**
	 * @param string
	 */
	public void setTransactionTime(String string)
	{
		transactionTime = string;
	}

	/**
	 * @return String
	 */
	public String getTransactionUserFirstName()
	{
		return transactionUserFirstName;
	}

	/**
	 * @return String
	 */
	public String getTransactionUserLastName()
	{
		return transactionUserLastName;
	}

	/**
	 * @return String
	 */
	public String getTransactionUserName()
	{
		return transactionUserName;
	}

	/**
	 * @param string
	 */
	public void setTransactionUserFirstName(String string)
	{
		transactionUserFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setTransactionUserLastName(String string)
	{
		transactionUserLastName = string;
	}

	/**
	 * @param string
	 */
	public void setTransactionUserName(String string)
	{
		transactionUserName = string;
	}

	/** 
	 * @return String
	 */
	public String getActionInd()
	{
		return actionInd;
	}

	/**
	 * @param string
	 */
	public void setActionInd(String string)
	{
		actionInd = string;
	}

	/**
	 * @return String
	 */
	public String getAgencyTransferTime()
	{
		return agencyTransferTime;
	}

	/**
	 * @param string
	 */
	public void setAgencyTransferTime(String string)
	{
		agencyTransferTime = string;
	}

	/**
	 * @return String
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @param string
	 */
	public void setRoleId(String string)
	{
		roleId = string;
	}

	/**
	 * @return Collection
	 */
	public Collection getProfiles()
	{
		return profiles;
	}

	/**
	 * @param collection
	 */
	public void setProfiles(Collection collection)
	{
		profiles = collection;
	}

	/**
	 * @return Collection
	 */
	public Collection getHistories()
	{
		return histories;
	}

	/**
	 * @param collection
	 */
	public void setHistories(Collection collection)
	{
		histories = collection;
	}

	/**
	 * @return String searchAgencyId
	 */
	public String getSearchAgencyId()
	{
		return searchAgencyId;
	}

	/**
	 * @return String searchDateOfBirth
	 */
	public String getSearchDateOfBirth()
	{
		return searchDateOfBirth;
	}

	/**
	 * @return String searchDateOfBirthToDate
	 */
	public String getSearchDateOfBirthToDate()
	{
		return searchDateOfBirthToDate;
	}

	/**
	 * @return String searchDivisionId
	 */
	public String getSearchDivisionId()
	{
		return searchDivisionId;
	}

	/**
	 * @return String searchFirstName
	 */
	public String getSearchFirstName()
	{
		return searchFirstName;
	}

	/**
	 * @return String searchLastName
	 */
	public String getSearchLastName()
	{
		return searchLastName;
	}

	/**
	 * @return String searchLogonId
	 */
	public String getSearchLogonId()
	{
		return searchLogonId;
	}

	/**
	 * @return String searchMiddleName
	 */
	public String getSearchMiddleName()
	{
		return searchMiddleName;
	}

	/**
	 * @return String searchSSN1
	 */
	public String getSearchSSN1()
	{
		return searchSSN1;
	}

	/**
	 * @return String searchSSN2
	 */
	public String getSearchSSN2()
	{
		return searchSSN2;
	}

	/** 
	 * @return String searchSSN3
	 */
	public String getSearchSSN3()
	{
		return searchSSN3;
	}

	/**
	 * @param string
	 */
	public void setSearchAgencyId(String string)
	{
		searchAgencyId = string;
	}

	/**
	 * @param string
	 */
	public void setSearchDateOfBirth(String string)
	{
		searchDateOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setSearchDateOfBirthToDate(String string)
	{
		searchDateOfBirthToDate = string;
	}

	/**
	 * @param string
	 */
	public void setSearchDivisionId(String string)
	{
		searchDivisionId = string;
	}

	/**
	 * @param string
	 */
	public void setSearchFirstName(String string)
	{
		searchFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setSearchLastName(String string)
	{
		searchLastName = string;
	}

	/**
	 * @param string
	 */
	public void setSearchLogonId(String string)
	{
		searchLogonId = string;
	}

	/**
	 * @param string
	 */
	public void setSearchMiddleName(String string)
	{
		searchMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setSearchSSN1(String string)
	{
		searchSSN1 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchSSN2(String string)
	{
		searchSSN2 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchSSN3(String string)
	{
		searchSSN3 = string;
	}

	/**
	 * @return
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * @param string
	 */
	public void setDepartmentName(String string)
	{
		departmentName = string;
	}

}
