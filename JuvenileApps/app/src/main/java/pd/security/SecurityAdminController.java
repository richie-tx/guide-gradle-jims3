//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\SecurityAdminController.java

//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\SecurityAdminController.java

package pd.security;

import java.util.Collection;


/**
 * @stereotype control
 */
public class SecurityAdminController 
{
   
   /**
	* @roseuid 4256C7F90232
	*/
   public SecurityAdminController() 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BE0041
	*/
   public void activateUserGroup(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleDescription
	* @param roleCreatorLastName
	* @param roleCreatorFirstName
	* @param roleName
	* @roseuid 425551F80234
	*/
   public void createRole(String roleDescription, String roleCreatorLastName, String roleCreatorFirstName, int roleName) 
   {
    
   }
 
   /**
   * @stereotype design
   * @param userGroupDescription
   * @param userGroupName
   * @roseuid 425551F80234
   */
  public void createUserGroup(String userGroupDescription, String userGroupName) 
  {
   
  }   
   
   /**
	* @stereotype design
	* @roseuid 425551F80302
	*/
   public void deleteRole() 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BE0219
	*/
   public void deleteUserGroup(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @roseuid 4256D631012B
	*/
   public void determineSARoleUsers(String roleId) 
   {
    
   }
   
    /**
	* @stereotype design
	* @param userID
	* @roseuid 4289F93B01A7
	*/
   public void getDepartmentConstraintsForUserAdministration(String userID) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyId
	* @roseuid 42711B0D01DB
	*/
   public void getDivisions(String agencyId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param featureDescription
	* @param featureName
	* @roseuid 425551F801D6
	*/
   public void getFeatures(String featureDescription, String featureName) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param loginId
	* @param lastName
	* @param firstName
	* @param agencyType
	* @param agencyId
	* @param agencyName
	* @roseuid 426FB60C0213
	*/
   public void getManageUsers(String loginId, String lastName, String firstName, String agencyType, String agencyId, String agencyName) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @roseuid 4256E971009F
	*/
   public void getRoleContrainsInfo(String roleId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @roseuid 425551F8019F
	*/
   public void getRoleFeatures(String roleId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyType
	* @param agencyId
	* @roseuid 425551F800EF
	*/
   public void getRoles(String agencyType, String agencyId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyName
	* @param roleName
	* @param roleCreatorLastName
	* @param roleCreatorFirstName
	* @param roleDescription
	* @param agencyType
	* @roseuid 4256E9700251
	*/
   public void getRolesByConstraints(String agencyName, int roleName, String roleCreatorLastName, String roleCreatorFirstName, String roleDescription, String agencyType) 
   {
    
   }
   
  
   
   /**
	* @stereotype design
	* @param logonId
	* @roseuid 429486240140
	*/
   public void getRolesForUser(String logonId) 
   {
    
   }
   
   /**
   * @stereotype design
   * @param roleId
   * @roseuid 429486240140
   */
	public void getRoleDetails(String roleId) 
  	{
    
	} 
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 429486240198
	*/
   public void getRolesForUserGroup(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @roseuid 425551F802C8
	*/
   public void getRoleUsers(String roleId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyName
	* @param roleName
	* @roseuid 4256D630038A
	*/
   public void getSARoles(String agencyName, int roleName) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @roseuid 4256D63100A5
	*/
   public void getSARoleSummary(String roleId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userID
	* @roseuid 426FB60C0271
	*/
   public void getUserAgency(String userID) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BC01AB
	*/
   public void getUserGroup(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BC0207
	*/
   public void getUserGroupConstraints(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyName
	* @param status
	* @param userGroupName
	* @param userGroupDescription
	* @roseuid 428B82BC010B
	*/
   public void getUserGroups(String agencyName, String status, String userGroupName, String userGroupDescription) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BC0253
	*/
   public void getUserGroupUsers(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userType
	* @param loginId
	* @param lastName
	* @param firstName
	* @param agencyType
	* @param agencyId
	* @param agencyName
	* @roseuid 42711B0D016E
	*/
   public void getUsersForUserAdministration(String userType, String loginId, String lastName, String firstName, String agencyType, String agencyId, String agencyName) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BE0257
	*/
   public void inactivateUserGroup(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyId
	* @param userType
	* @param userID
	* @roseuid 426FEA9401D4
	*/
   public void updateManageUser(String agencyId, String userType, String userID) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleDescription
	* @param roleId
	* @param roleName
	* @roseuid 425551F90003
	*/
   public void updateRole(String roleDescription, String roleId, int roleName) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param logonId
	* @roseuid 4294862402D2
	*/
   public void updateSecurityRolesForUser(String logonId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 429486240331
	*/
   public void updateSecurityRolesForUserGroup(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userType
	* @param userGroupName
	* @param userGroupDescription
	* @param status
	* @roseuid 428B82BD012B
	*/
   public void updateUserGroup(String userType, String userGroupName, String userGroupDescription, String status) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userGroupId
	* @roseuid 428B82BD034B
	*/
   public void updateUserGroupUsers(String userGroupId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @param userID
	* @roseuid 4256D6310234
	*/
   public void updateUsersProfile(String roleId, String userID) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param userType
	* @param userID
	* @roseuid 4289F93B0231
	*/
   public void updateUserTypeAndDepartmentConstraints(String userType, String userID) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyName
	* @param agencyId
	* @param roleName
	* @param roleId
	* @roseuid 425551F80157
	*/
   public void validateSecurityRoleCopy(String agencyName, String agencyId, int roleName, String roleId) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param agencyId
	* @param userGroupDescription
	* @param userGroupName
	* @roseuid 428B82BC033C
	*/
   public void validateUserGroupDetails(String agencyId, String userGroupDescription, String userGroupName) 
   {
    
   }
   
   /**
	* @stereotype design
	* @param roleId
	* @roseuid 428B82BC033C
	*/
   public void GetSARoleAgencyInfo(String roleId) 
   {
    
   } 
   
   /**
   * @stereotype design
   * @param agencyId
   * @param featureName
   * @param featureCategory
   * @roseuid 428B82BC033C
   */
  public void GetSARoleFeatures(String agencyId, String featureName, String featureCategory) 
  {
   
  } 
  
  /**
   * @stereotype design
   * @param roleId
   * @roseuid 428B82BC033C
   */
  public void GetRoleUsersAndUserGroups(String roleId) 
  {
   
  }
  
  /**
	 * @stereotype design
	 * @param roleId
	 * @roseuid 428B82BC033C
	 */
	public void GetRoleAgencyInfo(String roleId) 
	{
   
	} 
	
	/**
	 * @stereotype design
	 * @param agencyId
	 * @roseuid 428B82BC033C
	 */
		public void GetSARoleByAgencyId(String agencyId) 
		{
   
		}
		
	/**
	 * @stereotype design
	 * @param roleName
	 * @params agencyIds
	 * @roseuid 428B82BC033C
	 */
	 public void ValidateRole(String roleName, Collection agencyIds) 
	 {
   
	 }
	 
	 /**
		 * @stereotype design
		 * @param featureCategory
		 * @param featureId
		 * @roseuid 428B82BC033C
		 */
		 public void GetFeatureByIdAndCategory(String featureCategory, String featureId) 
		 {
	   
		 }
}