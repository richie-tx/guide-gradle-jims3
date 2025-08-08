/*
 * Created on Dec 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact.user.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;
import ui.common.Name;
import ui.common.SocialSecurity;

/**
 * @author dwilliamson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserProfileSearchForm extends ActionForm
{
// Fields	
	private String activationDate = "";
	private String agencyId = "";
	private String agencyName = "";
	private String changeDate = "";
	private String dateOfBirth = "";
	private String departmentId = "";
	private String departmentName = "";
	private String displayType = "";
	private String fromActivationDate = "";
	private String fromChangeDate = "";
	private String fromDateOfBirth = "";
	private String fromInactivationDate = "";
	private String genericUserType = "";
	private String genericUserTypeId = "";
	private String inactivationDate = "";
	private String jims2LogonId = "";	
	private String logonId = "";
	private String OPID = "";
//	private String publicInd = "";
	private Name requestorName = new Name();
	private SocialSecurity SSN = new SocialSecurity("");
	private String toActivationDate = "";
	private String toChangeDate = "";
	private String toDateOfBirth = "";
	private String toInactivationDate = "";
	private Name userName = new Name();
	private String userProfilesSize = "";
	private String userSearchResultSize;
	private String userIsLA;	
	private String userIsMA;
	private String userStatus = "";
	private String userStatusId;
	private String userType = "";
	private String userTypeId="";
	
// Collections
	private Collection agencyStatuses = new ArrayList();
    private Collection departments = new ArrayList();
    private Collection genericUserTypes = new ArrayList();
    private Collection userStatuses = new ArrayList();
    private Collection userTypes = new ArrayList(); 	
	private Collection userProfiles = new ArrayList(); 

//Methods
public void clear()
   {
   	activationDate = "";
	agencyId = "";
	agencyName = "";
	changeDate = "";
	dateOfBirth = "";
	departmentId = "";
	departmentName = "";
   	fromActivationDate = "";
	fromChangeDate = "";
	fromDateOfBirth = "";
	fromInactivationDate = "";
	genericUserType = "";
	genericUserTypeId = "";
	inactivationDate = "";
	jims2LogonId = "";	
	logonId = "";
	OPID = "";
//	publicInd = "";
	requestorName.clear();
	SSN.clear();
	toActivationDate = "";
	toChangeDate = "";
	toDateOfBirth = "";
	toInactivationDate = "";
	userName.clear();
	userProfilesSize = "";
	userStatus = "";
	userStatusId= "";
	userType = "";	
	userTypeId= "";

   }
   
   public void clearProfiles()
   {
   	 userProfiles=null;
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
public Collection getAgencyStatuses()
{
	return agencyStatuses;
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
	public String getFromActivationDate()
	{
		return fromActivationDate;
	}

	/**
	 * @return
	 */
	public String getFromChangeDate()
	{
		return fromChangeDate;
	}

	/**
	 * @return
	 */
	public String getFromDateOfBirth()
	{
		return fromDateOfBirth;
	}

	/**
	 * @return
	 */
	public String getFromInactivationDate()
	{
		return fromInactivationDate;
	}

	/**
	 * @return
	 */
	public String getChangeDate()
	{
		return changeDate;
	}

	/**
	 * @return
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
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
	public Collection getDepartments()
	{
		return departments;
	}

	/**
	 * @return
	 */
	public String getToActivationDate()
	{
		return toActivationDate;
	}

	/**
	 * @return
	 */
	public String getToChangeDate()
	{
		return toChangeDate;
	}

	/**
	 * @return
	 */
	public String getToDateOfBirth()
	{
		return toDateOfBirth;
	}

	/**
	 * @return
	 */
	public String getToInactivationDate()
	{
		return toInactivationDate;
	}

	/**
	 * @return
	 */
	public String getGenericUserType()
	{
		return genericUserType;
	}
	
	/**
	 * @return
	 */
	public String getGenericUserTypeId()
	{
		return genericUserTypeId;
	}

	/**
	 * @return
	 */
	public Collection getGenericUserTypes()
	{
		return genericUserTypes;
	}

	/**
	 * @return
	 */
	public String getInactivationDate()
	{
		return inactivationDate;
	}

	/**
	 * @return
	 */
	public String getOPID()
	{
		return OPID;
	}

	/**
	 * @return
	 */
//	public String getPublicInd()
//	{
//		return publicInd;
//	}

	/**
	 * @return
	 */
	public Name getRequestorName()
	{
		return requestorName;
	}

	/**
	 * @return
	 */
	public SocialSecurity getSSN()
	{
		return SSN;
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
	public Name getUserName()
	{
		return userName;
	}

	/**
	 * @return
	 */
	public Collection getUserProfiles()
	{
		return userProfiles;
	}
	/**
	 * @return
	 */
	public String getUserProfilesSize()
	{
		return userProfilesSize;
	}
	/**
	 * @return
	 */
	public String getUserStatus()
	{
		return userStatus;
	}

	/**
	 * @return
	 */
	public String getUserStatusId ()
	{
		return userStatusId ;
	}

	/**
	 * @return
	 */
	public Collection getUserStatuses()
	{
		return userStatuses;
	}

	/**
	 * @return
	 */
	public String getUserType()
	{
		return userType;
	}
	
	/**
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @return
	 */
	public Collection getUserTypes()
	{
		return userTypes;
	}

/**
 * @param string
 */
public void setActivationDate(String string)
{
	activationDate = string;
}

/**
 * @param collection
 */
public void setAgencyStatuses(Collection collection)
{
	agencyStatuses = collection;
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
	public void setFromActivationDate(String string)
	{
		fromActivationDate = string;
	}

	/**
	 * @param string
	 */
	public void setFromChangeDate(String string)
	{
		fromChangeDate = string;
	}

	/**
	 * @param string
	 */
	public void setFromDateOfBirth(String string)
	{
		fromDateOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setFromInactivationDate(String string)
	{
		fromInactivationDate = string;
	}

	/**
	 * @param string
	 */
	public void setChangeDate(String string)
	{
		changeDate = string;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(String string)
	{
		dateOfBirth = string;
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
		departmentName = string;
	}

	/**
	 * @param collection
	 */
	public void setDepartments(Collection collection)
	{
		departments = collection;
	}

	/**
	 * @param string
	 */
	public void setToActivationDate(String string)
	{
		toActivationDate = string;
	}

	/**
	 * @param string
	 */
	public void setToChangeDate(String string)
	{
		toChangeDate = string;
	}

	/**
	 * @param string
	 */
	public void setToDateOfBirth(String string)
	{
		toDateOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setToInactivationDate(String string)
	{
		toInactivationDate = string;
	}

	/**
	 * @param string
	 */
	public void setGenericUserType(String string)
	{
		genericUserType = string;
	}

	/**
	 * @param string
	 */
	public void setGenericUserTypeId(String string)
	{
		genericUserTypeId = string;
	}
	/**
	 * @param collection
	 */
	public void setGenericUserTypes(Collection collection)
	{
		genericUserTypes = collection;
	}

	/**
	 * @param string
	 */
	public void setInactivationDate(String string)
	{
		inactivationDate = string;
	}

	/**
	 * @param string
	 */
	public void setOPID(String string)
	{
		OPID = string;
	}

	/**
	 * @param string
	 */
//	public void setPublicInd(String string)
//	{
//		publicInd = string;
//	}

	/**
	 * @param name
	 */
	public void setRequestorName(Name name)
	{
		requestorName = name;
	}

	/**
	 * @param security
	 */
	public void setSSN(SocialSecurity security)
	{
		SSN = security;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 * @param name
	 */
	public void setUserName(Name name)
	{
		userName = name;
	}

	/**
	 * @param coll
	 */
	public void setUserProfiles(Collection coll)
	{
		userProfiles = coll;
	}
	/**
	 * @param string
	 */
	public void setUserStatus(String string)
	{
		userStatus = string;
	}
	
	/**
	 * @param string
	 */
	public void setUserProfilesSize(String string)
	{
		userProfilesSize = string;
	}
	
	/**
	 * @param string
	 */
	public void setUserStatusId(String string)
	{
		userStatusId = string;
	}

	/**
	 * @param collection
	 */
	public void setUserStatuses(Collection collection)
	{
		userStatuses = collection;
	}

	/**
	 * @param string
	 */
	public void setUserType(String string)
	{
		userType = string;
	}
	
	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
	}

	/**
	 * @param collection
	 */
	public void setUserTypes(Collection collection)
	{
		userTypes = collection;
	}

	/**
	 * @return
	 */
	public String getUserSearchResultSize()
	{
		return userSearchResultSize;
	}

	/**
	 * @param string
	 */
	public void setUserSearchResultSize(String string)
	{
		userSearchResultSize = string;
	}

	/**
	 * @return Returns the jims2LogonId.
	 */
	public String getJims2LogonId() {
		return jims2LogonId;
	}

	/**
	 * @param jims2LogonId The jims2LogonId to set.
	 */
	public void setJims2LogonId(String jims2LogonId) {
		this.jims2LogonId = jims2LogonId;
	}
	
	/**
	 * @return Returns the userIsMA.
	 */
	public String getUserIsMA() {
		return userIsMA;
	}
	/**
	 * @param userIsMA The userIsMA to set.
	 */
	public void setUserIsMA(String userIsMA) {
		this.userIsMA = userIsMA;
	}
	/**
	 * @return Returns the userIsLA.
	 */
	public String getUserIsLA() {
		return userIsLA;
	}
	/**
	 * @param userIsLA The userIsLA to set.
	 */
	public void setUserIsLA(String userIsLA) {
		this.userIsLA = userIsLA;
	}	
	/**
	 * @return Returns the displayType.
	 */
	public String getDisplayType() {
		return displayType;
	}
	/**
	 * @param displayType The displayType to set.
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
}   
