package ui.security.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * @author awidjaja - Created on May 27th, 2005
 * 
 * This form contains all the attributes needed for assign roles use-case.
 */
public class AssignRolesForm extends GenericSecurityForm
{
	private String userAgencyName;          //search criteria
	private String userAgencyNamePrompt;    //search criteria
	private String userGroupAgencyName;		//search criteria
	private String roleAgencyName;			//search criteria
	private String userAgencyCode;
	private String userGroupAgencyCode;
	private String userDepartmentCode;
	
	private String agencyId;   
	private String agencyName;           //agencyId of current logged on user
	private String departmentName;       //search criteria
	private String departmentId;         //department Id of current logged on user
	
	private String errorMessage; 
	private String refreshButton;		 //search criteria (hidden field)
	private String roleId;               //search criteria
	private String roleDescription;      //search criteria 
	private String roleName;             //search criteria
	private String searchType;
	private String userGroupId;	         //search criteria
	private String userGroupName;        //search criteria 
	private String userGroupDescription; //search criteria
	private String userFirstName;  		 //search criteria
	private String userLastName;   		 //search criteria
	private String userMiddleName; 		 //search criteria
	private String userId;   //the userId of user to assign role to
	private String userTypeId;
	private String userType; //the current logon user, MA or SA
	private String userGroupStatus;
	private String userGroupCreatorName;
	
	private String[] selectedRoles;
	
	private Collection availableRoles;
	private Collection currentRoles;
	private Collection userGroupUsers;

	private Collection departments; //departments that belong to the logon user's agency id
	
	private static Collection emptyColl = new ArrayList();

	/**
	 * Constructor for the RoleGroupForm 
	 */
	public AssignRolesForm()
	{
		super();
	}
	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
	}
	/**
	 * @param aRequest
	 */
	public void clear()
	{
		super.clear();
		Collection emptyColl = new ArrayList();
		agencyId = null;
		availableRoles = emptyColl;
		currentRoles = emptyColl;
		departmentId = null;
		departmentName = null;
		departments = emptyColl;
		errorMessage = null;
		refreshButton = null;
		roleAgencyName = null;
		roleDescription = null;
		roleId = null;
		roleName = null;
		searchType = null;
		selectedRoles = null;
		userAgencyCode = null;		
		userAgencyName = null;
		userAgencyNamePrompt = null;
		userDepartmentCode = null;
		userFirstName = null;
		userGroupAgencyCode = null;
		userGroupAgencyName = null;
		userGroupDescription = null;
		userGroupId = null;	
		userGroupName = null;
		userId = null;
		userLastName = null;
		userMiddleName = null;
		userType = null;
		userTypeId = null;
	}

	/**
	 * @return String userGroupName
	 */
	public String getUserGroupName()
	{
		return userGroupName;
	}

	/**
	 * @param string
	 */
	public void setUserGroupName(String string)
	{
		if(string != null && !string.equals(""))
		{
			userGroupName = string.trim();
		}
		else
		{	
			userGroupName = string;
		}
	}

	/**
	 * @return String userGroupDescription
	 */
	public String getUserGroupDescription()
	{
		return userGroupDescription;
	}

	/**
	 * @param string
	 */
	public void setUserGroupDescription(String string)
	{
		if(string != null && !string.equals(""))	
		{
			userGroupDescription = string.trim();
		}
		else	
		{
			userGroupDescription = string;	
		}
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}





	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @return
	 */
	public String getUserGroupId()
	{
		return userGroupId;
	}

	/**
	 * @param string
	 */
	public void setUserGroupId(String string)
	{
		userGroupId = string;
	}

	/**
	 * @return
	 */
	public String getRoleDescription()
	{
		return roleDescription;
	}

	/**
	 * @return
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @return
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @param string
	 */
	public void setRoleDescription(String string)
	{
		if(string != null && !string.equals(""))	
		{
			roleDescription = string.trim();
		}
		else	
		{
			roleDescription = string;	
		}
	}

	/**
	 * @param string
	 */
	public void setRoleId(String string)
	{
		roleId = string;
	}

	/**
	 * @param string
	 */
	public void setRoleName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			roleName = string.trim();
		}
		else	
		{
			roleName = string;
		}	
	}

	/**
	 * @return
	 */
	public String[] getSelectedRoles()
	{
		return selectedRoles;
	}

	/**
	 * @param strings
	 */
	public void setSelectedRoles(String[] strings)
	{
		selectedRoles = strings;
	}

	/**
	 * @return
	 */
	public String getUserFirstName()
	{
		return userFirstName;
	}

	/**
	 * @return
	 */
	public String getUserLastName()
	{
		return userLastName;
	}

	/**
	 * @return
	 */
	public String getUserMiddleName()
	{
		return userMiddleName;
	}

	/**
	 * @param string
	 */
	public void setUserFirstName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			userFirstName = string.trim();
		}
		else	
		{
			userFirstName = string;
		}
	}

	/**
	 * @param string
	 */
	public void setUserLastName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			userLastName = string.trim();
		}
		else	
		{
			userLastName = string;	
		}
	}

	/**
	 * @param string
	 */
	public void setUserMiddleName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			userMiddleName = string.trim();
		}
		else	
		{
			userMiddleName = string;
		}	
	}

	/**
	 * @return
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @return
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param string
	 */
	public void setErrorMessage(String string)
	{
		errorMessage = string;
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
		if(string != null && !string.equals(""))	
		{
			departmentName = string.trim();
		}
		else	
		{
			departmentName = string;	
		}
	}

	/**
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
	}

	/**
	 * @return
	 */
	public Collection getAvailableRoles()
	{
		return availableRoles;
	}

	/**
	 * @return
	 */
	public Collection getCurrentRoles()
	{
		return currentRoles;
	}

	/**
	 * @param collection
	 */
	public void setAvailableRoles(Collection collection)
	{
		availableRoles = collection;
	}

	/**
	 * @param collection
	 */
	public void setCurrentRoles(Collection collection)
	{
		currentRoles = collection;
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
	public Collection getDepartments()
	{
		return departments;
	}

	/**
	 * @param collection
	 */
	public void setDepartments(Collection collection)
	{
		departments = collection;
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
	public String getRoleAgencyName()
	{
		return roleAgencyName;
	}

	/**
	 * @return
	 */
	public String getUserAgencyName()
	{
		return userAgencyName;
	}

	/**
	 * @return
	 */
	public String getUserGroupAgencyName()
	{
		return userGroupAgencyName;
	}

	/**
	 * @param string
	 */
	public void setRoleAgencyName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			roleAgencyName = string.trim();
		}
		else	
		{
			roleAgencyName = string;
		}			
	}

	/**
	 * @param string
	 */
	public void setUserAgencyName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			userAgencyName = string.trim();
		}
		else	
		{
			userAgencyName = string;
		}			
	}

	/**
	 * @param string
	 */
	public void setUserGroupAgencyName(String string)
	{
		if(string != null && !string.equals(""))	
		{
			userGroupAgencyName = string.trim();
		}
		else	
		{
			userGroupAgencyName = string;
		}
	}



	/**
	 * @return
	 */
	public String getUserAgencyNamePrompt()
	{
		return userAgencyNamePrompt;
	}

	/**
	 * @param string
	 */
	public void setUserAgencyNamePrompt(String string)
	{
		userAgencyNamePrompt = string;
	}

	/**
	 * @return
	 */
	public String getUserGroupStatus()
	{
		return userGroupStatus;
	}

	/**
	 * @param string
	 */
	public void setUserGroupStatus(String string)
	{
		userGroupStatus = string;
	}

	/**
	 * @return
	 */
	public String getUserGroupCreatorName()
	{
		return userGroupCreatorName;
	}

	/**
	 * @param string
	 */
	public void setUserGroupCreatorName(String string)
	{
		userGroupCreatorName = string;
	}

	/**
	 * @return
	 */
	public Collection getUserGroupUsers()
	{
		return userGroupUsers;
	}

	/**
	 * @param collection
	 */
	public void setUserGroupUsers(Collection collection)
	{
		userGroupUsers = collection;
	}

	/**
	 * @return
	 */
	public String getUserAgencyCode()
	{
		return userAgencyCode;
	}

	/**
	 * @param string
	 */
	public void setUserAgencyCode(String string)
	{
		userAgencyCode = string;
	}

	/**
	 * @return
	 */
	public String getUserDepartmentCode()
	{
		return userDepartmentCode;
	}

	/**
	 * @return
	 */
	public String getUserGroupAgencyCode()
	{
		return userGroupAgencyCode;
	}

	/**
	 * @param string
	 */
	public void setUserDepartmentCode(String string)
	{
		userDepartmentCode = string;
	}

	/**
	 * @param string
	 */
	public void setUserGroupAgencyCode(String string)
	{
		userGroupAgencyCode = string;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @return
	 */
	public String getRefreshButton()
	{
		return refreshButton;
	}

	/**
	 * @param string
	 */
	public void setRefreshButton(String string)
	{
		refreshButton = string;
	}

	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType The searchType to set.
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
