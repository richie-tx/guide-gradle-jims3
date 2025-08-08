package ui.security.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.ServletRequest;

import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionMapping;

import ui.common.Name;

/**
 * @author cShimek - Created on 06/02/2005  
 * 
 * This form contains all the attributes needed for creteing, 
 * updating, deleting and retrieving user group information.
 */
public class UserGroupForm extends GenericSecurityForm
{
	private String action;
	private String agencyCode;
	private String agencyId;
	private String agencyName;
	private String agencySearchErrorMessage;	
	private String agencySearchResultSize;
	private String agencyType;
	private String agencyTypeId;
	private Name creatorName = new Name();
	private String department;
	private String departmentId;
	private String errorMessage;
	private String firstName; 
	private String groupStatus;
	private String groupStatusId;
	private String groupId;
	private String lastName; 
	private String middleName;
	private Name memberName = new Name();
	private String originalUserGroupName;
	private String pageType;
	private String searchAgencyName;	
	private String[] selectedAgencies;
	private String[] selectedUsers;
	private String statusId;
	private String userAgencyName;
	private String userGroupDescription;
	private String userGroupName;
	private String userGroupType;	
	private String userId;
	private String userSearchErrorMessage;	
	private String userSearchResultSize;
	private String userType;

	private boolean updateFeatureFlag;
	private boolean updateAgencyFlag;

//	collections for display and shopping cart 	
	private Collection availableUsers;
	private Collection characterDropDownList;
	private Collection currentUsers;
	private Collection usersSelected;

// collections for drop downs
	private Collection agencyTypes;
	private Collection departments;
	private Collection statuses;
	
	
	/**
	* Constructor for the RoleSAForm 
	*/
	public UserGroupForm()
	{
		super();
	}

	/**
	 * @param array
	 */
	public void clearStringArray(String[] array)
	{
		if (array == null)
		{
			return;
		}
		for (int i = 0; i < array.length; i++)
		{
			array[i] = "";
		}
	}
	/** 
	 * @see org.apache.struts.action.ActionForm#reset(ActionMapping, ServletRequest)
	 */
	public void reset(ActionMapping aMapping, ServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		clear();
	}	

	/**
	 * @param event
	 */
	public void clear()
	{
		super.clear();
		this.agencyCode = null;
		this.action = null;
		this.agencyId = null;
		this.agencyName = null;
		this.agencySearchErrorMessage = null;
		this.agencySearchResultSize = null;
		this.agencyType = null;
		this.agencyTypeId = null;
		this.creatorName = null;
		this.department = null;
		this.departmentId = null;
		this.errorMessage = null;
		this.firstName = null;
		this.groupStatus = null;
		this.groupStatusId = null;
		this.groupId = null;
		this.lastName = null;
		this.middleName = null;
		this.memberName = null;
		this.originalUserGroupName = null;
		this.pageType = null;
		clearStringArray(selectedAgencies);
		clearStringArray(selectedUsers);
		this.statusId = null;
		this.userAgencyName = null;
		this.userGroupDescription = null;
		this.userGroupName = null;
		this.userGroupType = null;
		this.userId = null;
		this.userSearchErrorMessage = null;
		this.userSearchResultSize = null;
		this.userType = null;

/** clear collections used in FIND */
		this.availableUsers = null;
		this.currentUsers = null;
		this.usersSelected = null;
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
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
	public String getAgencySearchResultSize()
	{
		return agencySearchResultSize;
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
	public Collection getAgencyTypes()
	{
		agencyTypes = MessageUtil.processEmptyCollection(agencyTypes);
		Collections.sort((ArrayList) agencyTypes);
		return agencyTypes;
	}

	/**
	 * @return
	 */
	public Collection getAvailableUsers()
	{
		availableUsers = MessageUtil.processEmptyCollection(availableUsers);
//		Collections.sort((ArrayList) availableUsers);
		return availableUsers;
	}

	/**
	 * @return
	 */
	public Name getCreatorName()
	{
		return creatorName;
	}

	/**
	 * @return
	 */
	public Collection getCurrentUsers()
	{
		currentUsers = MessageUtil.processEmptyCollection(currentUsers);
//		Collections.sort((ArrayList) currentUsers);
		return currentUsers;
	}

	/**
	 * @return
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * @return
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @return
	 */
	public String getGroupStatus()
	{
		return groupStatus;
	}

	/**
	 * @return
	 */
	public String[] getSelectedAgencies()
	{
		return selectedAgencies;
	}

	/**
	 * @return
	 */
	public String[] getSelectedUsers()
	{
		return selectedUsers;
	}

	/**
	 * @return
	 */
	public Collection getStatuses()
	{
		statuses = MessageUtil.processEmptyCollection(statuses);
		Collections.sort((ArrayList) statuses);
		return statuses;
	}

	/**
	 * @return
	 */
	public boolean isUpdateAgencyFlag()
	{
		return updateAgencyFlag;
	}

	/**
	 * @return
	 */
	public boolean isUpdateFeatureFlag()
	{
		return updateFeatureFlag;
	}

	/**
	 * @return
	 */
	public String getUserGroupDescription()
	{
		return userGroupDescription;
	}

	/**
	 * @return
	 */
	public String getUserGroupName()
	{
		return userGroupName;
	}

	/**
	 * @return
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @return
	 */
	public String getUserSearchResultSize()
	{
		return userSearchResultSize;
	}

	/**
	 * @return
	 */
	public Collection getUsersSelected()
	{
		usersSelected = MessageUtil.processEmptyCollection(usersSelected);
//		Collections.sort((ArrayList) usersSelected);
		return usersSelected;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
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
	public void setAgencySearchResultSize(String string)
	{
		agencySearchResultSize = string;
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
 	* @param collection
 	*/
	public void setAgencyTypes(Collection collection)
	{
		agencyTypes = collection;
	}


	/**
	 * @param collection
	 */
	public void setAvailableUsers(Collection collection)
	{
		availableUsers = this.sortUsers(collection);
	}

	/**
	 * @param string
	 */
	public void setCreatorName(Name aCreatorName)
	{
		creatorName = aCreatorName;
	}


	/**
	 * @param collection
	 */
	public void setCurrentUsers(Collection collection)
	{
		currentUsers = this.sortUsers(collection);
	}

	/**
	 * @param string
	 */
	public void setDepartment(String string)
	{
		department = string;
	}

	/**
	 * @param string
	 */
	public void setErrorMessage(String string)
	{
		errorMessage = string;
	}

	/**
	 * @param string
	 */
	public void setGroupStatus(String string)
	{
		groupStatus = string;
	}

	/**
	 * @param strings
	 */
	public void setSelectedAgencies(String[] strings)
	{
		selectedAgencies = strings;
	}

	/**
	 * @param strings
	 */
	public void setSelectedUsers(String[] strings)
	{
		selectedUsers = strings;
	}

	/**
	 * @param collection
	 */
	public void setStatuses(Collection collection)
	{
		statuses = collection;
	}

	/**
	 * @param b
	 */
	public void setUpdateAgencyFlag(boolean b)
	{
		updateAgencyFlag = b;
	}

	/**
	 * @param b
	 */
	public void setUpdateFeatureFlag(boolean b)
	{
		updateFeatureFlag = b;
	}

	/**
	 * @param string
	 */
	public void setUserGroupDescription(String string)
	{
		userGroupDescription = string;
	}

	/**
	 * @param string
	 */
	public void setUserGroupName(String string)
	{
		userGroupName = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @param string
	 */
	public void setUserSearchResultSize(String string)
	{
		userSearchResultSize = string;
	}

	/**
	 * @param collection
	 */
	public void setUsersSelected(Collection collection)
	{
		usersSelected = collection;
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
	public String getUserType()
	{
		return userType;
	}

	/**
	 * @param string
	 */
	public void setUserType(String string)
	{
		userType = string;
	}

	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * @param string
	 */
	public void setGroupId(String string)
	{
		groupId = string;
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
	 * @return
	 */
	public String getUserAgencyName()
	{
		return userAgencyName;
	}

	/**
	 * @param string
	 */
	public void setUserAgencyName(String string)
	{
		userAgencyName = string;
	}

	/**
	 * @return
	 */
	public String getPageType()
	{
		return pageType;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string)
	{
		pageType = string;
	}

	/**
	 * @return
	 */
	public String getUserGroupType()
	{
		return userGroupType;
	}

	/**
	 * @param userGroupType
	 */
	public void setUserGroupType(String userGroupType)
	{
		this.userGroupType = userGroupType;
	}

	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}


	/**
	 * @return
	 */
	public Collection getDepartments()
	{
		return departments;
	}

	/**
	 * @param collection
	 */
	public void setDepartments(Collection collection)
	{
		departments = this.sortDepartments(collection);
	}

	/**
	 * @return
	 */
	public String getGroupStatusId()
	{
		return groupStatusId;
	}

	/**
	 * @param string
	 */
	public void setGroupStatusId(String string)
	{
		groupStatusId = string;
	}

	/**
	 * @return
	 */
	public Name getMemberName()
	{
		return memberName;
	}

	/**
	 * @param name
	 */
	public void setMemberName(Name name)
	{
		memberName = name;
	}

	/**
	 * @return
	 */
	public String getSearchAgencyName()
	{
		return searchAgencyName;
	}

	/**
	 * @param string
	 */
	public void setSearchAgencyName(String string)
	{
		searchAgencyName = string;
	}

	/**
	 * @return
	 */
	public Collection getCharacterDropDownList()
	{
		return characterDropDownList;
	}

	/**
	 * @param collection
	 */
	public void setCharacterDropDownList(Collection collection)
	{
		characterDropDownList = collection;
	}


	/**
	 * @return
	 */
	public String getAgencySearchErrorMessage()
	{
		return agencySearchErrorMessage;
	}

	/**
	 * @return
	 */
	public String getUserSearchErrorMessage()
	{
		return userSearchErrorMessage;
	}

	/**
	 * @param string
	 */
	public void setAgencySearchErrorMessage(String string)
	{
		agencySearchErrorMessage = string;
	}

	/**
	 * @param string
	 */
	public void setUserSearchErrorMessage(String string)
	{
		userSearchErrorMessage = string;
	}

	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * @return Returns the agencyCode.
	 */
	public String getAgencyCode() {
		return agencyCode;
	}
	/**
	 * @param agencyCode The agencyCode to set.
	 */
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	/**
	 * @return Returns the originalUserGroupName.
	 */
	public String getOriginalUserGroupName() {
		return originalUserGroupName;
	}
	/**
	 * @param originalUserGroupName The originalUserGroupName to set.
	 */
	public void setOriginalUserGroupName(String originalUserGroupName) {
		this.originalUserGroupName = originalUserGroupName;
	}
}
