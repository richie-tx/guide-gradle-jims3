package ui.security.inquiries.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cshimek - Created on Aug 22, 2006
 * 
 * This form contains all the attributes needed for inquiring security records 
 */
public class SecurityInquiriesForm extends ActionForm
{
	private String action;
	private String agencyId;	
	private String agencyIdPrompt;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private String employeeId;
	private String featureCategoryId;
	private String featureCategoryName;	
	private String featureId;
	private String featureName;	
	private String firstName;
	private String firstNamePrompt;		
	private String formattedName;
	private String jims2LogonId;
	private String lastName;
	private String lastNamePrompt;	
	private String logonId;
	private String logonIdPrompt;	
	private String roleAgencyId;
	private String roleAgencyName;
	private String roleDescription;
	private String roleId;
	private String roleName;
	private String searchResultsCount;	
	private String searchTypeId;
	private String searchJIMSLogonId;
	private String searchJIMS2LogonId;
	private String serviceProviderName;
	private String susbsystemName;
	private String userAgencyNamePrompt;		
	private String userAgencyIdPrompt;	
	private String userDeptIdPrompt;
	private String userDeptNamePrompt;
	private String userGroupAgencyId;
	private String userGroupAgencyName;	
	private String userGroupDescription;
	private String userGroupId;	
	private String userGroupName;
	private String userId;
 

	private Collection agencies;
	private Collection departments;	
	private Collection features;
	private Collection featuresList;
	private Collection individualUsers;		
	private Collection jims2Subsystems;
	private Collection roles;
	private Collection saUsers;	
	private Collection securityInquires;
	private Collection userGroups;	
	private Collection userProfiles;
	
	private static Collection emptyColl = new ArrayList();

	/**
	 * Constructor for the RoleGroupForm 
	 */
	public SecurityInquiriesForm()
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
		this.action = null;
		this.agencyId = null;
		this.agencyIdPrompt = null;
		this.agencyName = null;
		this.departmentName = null;
		this.departmentId = null;
		this.employeeId = null;
		this.featureCategoryId = null;
		this.featureCategoryName = null;		
		this.featureId = null;
		this.featureName = null;	
		this.firstName = null;	
		this.formattedName = null;	
		this.jims2LogonId = null;
		this.lastName = null;	
		this.logonId = null;
		this.roleDescription = null;
		this.roleId = null;
		this.roleName = null;
		this.searchResultsCount = null;		
		this.searchTypeId = null;
		this.searchJIMS2LogonId = null;
		this.searchJIMSLogonId = null;
		this.serviceProviderName = null;
		this.susbsystemName = null;
		this.userGroupAgencyId = null;
		this.userGroupAgencyName = null;			
		this.userGroupDescription = null;
		this.userGroupId = null;
		this.userGroupName = null;
		this.userId = null;
		
		this.lastNamePrompt = null;
		this.firstNamePrompt= null;	
		this.userAgencyNamePrompt= null;	
		this.userAgencyIdPrompt= null;
		this.userDeptNamePrompt=null;
		this.userDeptIdPrompt=null;
		
// collections 
		this.agencies = emptyColl;
		this.departments = emptyColl;			
		this.features = emptyColl;
		this.featuresList = emptyColl;
		this.individualUsers = emptyColl;			
		this.roles = emptyColl;	
		this.saUsers = emptyColl;	
		this.userGroups = emptyColl;
		this.userProfiles = emptyColl;		
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
	 * @return Returns the agencies.
	 */
	public Collection getAgencies() {
		agencies = MessageUtil.processEmptyCollection(agencies);		
		return agencies;
	}
	/**
	 * @param agencies The agencies to set.
	 */
	public void setAgencies(Collection agencies) {
		this.agencies = agencies;
	}	
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the agencyName.
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName The agencyName to set.
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
		
	/**
	 * @return Returns the departmentId.
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId The departmentId to set.
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return Returns the departmentName.
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName The departmentName to set.
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return Returns the departments.
	 */
	public Collection getDepartments() {
		departments = MessageUtil.processEmptyCollection(departments);		
		return departments;
	}
	/**
	 * @param departments The departments to set.
	 */
	public void setDepartments(Collection departments) {
		this.departments = departments;
	}	
	/**
	 * @return Returns the employeeId.
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId The employeeId to set.
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return Returns the featureCategoryId.
	 */
	public String getFeatureCategoryId() {
		return featureCategoryId;
	}
	/**
	 * @param featureCategoryId The featureCategoryId to set.
	 */
	public void setFeatureCategoryId(String featureCategoryId) {
		this.featureCategoryId = featureCategoryId;
	}
	/**
	 * @return Returns the featureCategoryName.
	 */
	public String getFeatureCategoryName() {
		return featureCategoryName;
	}
	/**
	 * @param featureCategoryName The featureCategoryName to set.
	 */
	public void setFeatureCategoryName(String featureCategoryName) {
		this.featureCategoryName = featureCategoryName;
	}
	/**
	 * @return Returns the featureId.
	 */
	public String getFeatureId() {
		return featureId;
	}
	/**
	 * @param featureId The featureId to set.
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	/**
	 * @return Returns the featureName.
	 */
	public String getFeatureName() {
		return featureName;
	}
	/**
	 * @param featureName The featureName to set.
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	/**
	 * @return Returns the features.
	 */
	public Collection getFeatures() {
		features = MessageUtil.processEmptyCollection(features);		
		return features;
	}
	/**
	 * @param features The features to set.
	 */
	public void setFeatures(Collection features) {
		this.features = features;
	}
	/**
	 * @return Returns the featuresList.
	 */
	public Collection getFeaturesList() {
		features = MessageUtil.processEmptyCollection(featuresList);		
		return features;
	}
	/**
	 * @param features The features to set.
	 */
	public void setFeaturesList(Collection featuresList) {
		this.featuresList = featuresList;
	}	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the formattedName.
	 */
	public String getFormattedName() {
		return formattedName;
	}
	/**
	 * @param formattedName The formattedName to set.
	 */
	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}
	/**
	 * @return Returns the individualUsers.
	 */
	public Collection getIndividualUsers() {
		individualUsers = MessageUtil.processEmptyCollection(individualUsers);		
		return individualUsers;
	}
	/**
	 * @param laUsers The individualUsers to set.
	 */
	public void setIndividualUsers(Collection individualUsers) {
		this.individualUsers = individualUsers;
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
	 * @return Returns the jims2Subsystems.
	 */
	public Collection getJims2Subsystems() {
		return jims2Subsystems;
	}
	/**
	 * @param jims2Subsystems The jims2Subsystems to set.
	 */
	public void setJims2Subsystems(Collection jims2Subsystems) {
		this.jims2Subsystems = jims2Subsystems;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return Returns the roleAgencyId.
	 */
	public String getRoleAgencyId() {
		return roleAgencyId;
	}
	/**
	 * @param roleAgencyId The roleAgencyId to set.
	 */
	public void setRoleAgencyId(String roleAgencyId) {
		this.roleAgencyId = roleAgencyId;
	}
	/**
	 * @return Returns the roleAgencyName.
	 */
	public String getRoleAgencyName() {
		return roleAgencyName;
	}
	/**
	 * @param roleAgencyName The roleAgencyName to set.
	 */
	public void setRoleAgencyName(String roleAgencyName) {
		this.roleAgencyName = roleAgencyName;
	}
	/**
	 * @return Returns the roleDescription.
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	/**
	 * @param roleDescription The roleDescription to set.
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	/**
	 * @return Returns the roleId.
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId The roleId to set.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return Returns the roles.
	 */
	public Collection getRoles() {
		roles = MessageUtil.processEmptyCollection(roles);		
		return roles;
	}
	/**
	 * @param roles The roles to set.
	 */
	public void setRoles(Collection roles) {
		this.roles = roles;
	}	
	/**
	 * @return Returns the roleName.
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName The roleName to set.
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return Returns the saUsers.
	 */
	public Collection getSaUsers() {
		//saUsers = MessageUtil.processEmptyCollection(saUsers);		
		return saUsers;
	}
	/**
	 * @param saUsers The saUsers to set.
	 */
	public void setSaUsers(Collection saUsers) {
		this.saUsers = saUsers;
	}	
	/**
	 * @return Returns the searchResultCount.
	 */
	public String getSearchResultsCount() {
		return searchResultsCount;
	}
	/**
	 * @param searchResultCount The searchResultCount to set.
	 */
	public void setSearchResultsCount(String searchResultsCount) {
		this.searchResultsCount = searchResultsCount;
	}
	/**
	 * @return Returns the searchTypeId.
	 */
	public String getSearchTypeId() {
		return searchTypeId;
	}
	/**
	 * @param searchTypeId The searchTypeId to set.
	 */
	public void setSearchTypeId(String searchTypeId) {
		this.searchTypeId = searchTypeId;
	}
	/**
	 * @return Returns the searchJIMS2LogonId.
	 */
	public String getSearchJIMS2LogonId() {
		return searchJIMS2LogonId;
	}
	/**
	 * @param searchJIMS2LogonId The searchJIMS2LogonId to set.
	 */
	public void setSearchJIMS2LogonId(String searchJIMS2LogonId) {
		this.searchJIMS2LogonId = searchJIMS2LogonId;
	}
	/**
	 * @return Returns the searchJIMSLogonId.
	 */
	public String getSearchJIMSLogonId() {
		return searchJIMSLogonId;
	}
	/**
	 * @param searchJIMSLogonId The searchJIMSLogonId to set.
	 */
	public void setSearchJIMSLogonId(String searchJIMSLogonId) {
		this.searchJIMSLogonId = searchJIMSLogonId;
	}
	/**
	 * @return Returns the securityInquires.
	 */
	public Collection getSecurityInquires() {
		return securityInquires;
	}
	/**
	 * @param securityInquires The securityInquires to set.
	 */
	public void setSecurityInquires(Collection securityInquires) {
		this.securityInquires = securityInquires;
	}	
	/**
	 * @return Returns the susbsystemName.
	 */
	public String getSusbsystemName() {
		return susbsystemName;
	}
	/**
	 * @param susbsystemName The susbsystemName to set.
	 */
	public void setSusbsystemName(String susbsystemName) {
		this.susbsystemName = susbsystemName;
	}
	/**
	 * @return Returns the userGroupAgencyId.
	 */
	public String getUserGroupAgencyId() {
		return userGroupAgencyId;
	}
	/**
	 * @param userGroupAgencyId The userGroupAgencyId to set.
	 */
	public void setUserGroupAgencyId(String userGroupAgencyId) {
		this.userGroupAgencyId = userGroupAgencyId;
	}
	/**
	 * @return Returns the userGroupAgencyName.
	 */
	public String getUserGroupAgencyName() {
		return userGroupAgencyName;
	}
	/**
	 * @param userGroupAgencyName The userGroupAgencyName to set.
	 */
	public void setUserGroupAgencyName(String userGroupAgencyName) {
		this.userGroupAgencyName = userGroupAgencyName;
	}
	/**
	 * @return Returns the userGroupDescription.
	 */
	public String getUserGroupDescription() {
		return userGroupDescription;
	}
	/**
	 * @param userGroupDescription The userGroupDescription to set.
	 */
	public void setUserGroupDescription(String userGroupDescription) {
		this.userGroupDescription = userGroupDescription;
	}
	/**
	 * @return Returns the userGroupId.
	 */
	public String getUserGroupId() {
		return userGroupId;
	}
	/**
	 * @param userGroupId The userGroupId to set.
	 */
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	/**
	 * @return Returns the userGroupName.
	 */
	public String getUserGroupName() {
		return userGroupName;
	}
	/**
	 * @param userGroupName The userGroupName to set.
	 */
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	/**
	 * @return Returns the userGroups.
	 */
	public Collection getUserGroups() {
		userGroups = MessageUtil.processEmptyCollection(userGroups);		
		return userGroups;
	}
	/**
	 * @param userGroups The userGroups to set.
	 */
	public void setUserGroups(Collection userGroups) {
		this.userGroups = userGroups;
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
	 * @return Returns the userProfiles.
	 */
	public Collection getUserProfiles() {
		userProfiles = MessageUtil.processEmptyCollection(userProfiles);			
		return userProfiles;
	}
	/**
	 * @param userProfiles The userProfiles to set.
	 */
	public void setUserProfiles(Collection userProfiles) {
		this.userProfiles = userProfiles;
	}

	/**
	 * @return Returns the firstNamePrompt.
	 */
	public String getFirstNamePrompt() {
		return firstNamePrompt;
	}
	/**
	 * @param firstNamePrompt The firstNamePrompt to set.
	 */
	public void setFirstNamePrompt(String firstNamePrompt) {
		this.firstNamePrompt = firstNamePrompt;
	}
	/**
	 * @return Returns the lastNamePrompt.
	 */
	public String getLastNamePrompt() {
		return lastNamePrompt;
	}
	/**
	 * @param lastNamePrompt The lastNamePrompt to set.
	 */
	public void setLastNamePrompt(String lastNamePrompt) {
		this.lastNamePrompt = lastNamePrompt;
	}
	/**
	 * @return Returns the logonIdPrompt.
	 */
	public String getLogonIdPrompt() {
		return logonIdPrompt;
	}
	/**
	 * @param logonIdPrompt The logonIdPrompt to set.
	 */
	public void setLogonIdPrompt(String logonIdPrompt) {
		this.logonIdPrompt = logonIdPrompt;
	}
	/**
	 * @return Returns the userAgencyIdPrompt.
	 */
	public String getUserAgencyIdPrompt() {
		return userAgencyIdPrompt;
	}
	/**
	 * @param userAgencyIdPrompt The userAgencyIdPrompt to set.
	 */
	public void setUserAgencyIdPrompt(String userAgencyIdPrompt) {
		this.userAgencyIdPrompt = userAgencyIdPrompt;
	}
	/**
	 * @return Returns the userAgencyNamePrompt.
	 */
	public String getUserAgencyNamePrompt() {
		return userAgencyNamePrompt;
	}
	/**
	 * @param userAgencyNamePrompt The userAgencyNamePrompt to set.
	 */
	public void setUserAgencyNamePrompt(String userAgencyNamePrompt) {
		this.userAgencyNamePrompt = userAgencyNamePrompt;
	}
	/**
	 * @return Returns the userDeptNamePrompt.
	 */
	public String getUserDeptNamePrompt() {
		return userDeptNamePrompt;
	}
	/**
	 * @param userDeptNamePrompt The userDeptNamePrompt to set.
	 */
	public void setUserDeptNamePrompt(String userDeptNamePrompt) {
		this.userDeptNamePrompt = userDeptNamePrompt;
	}
	/**
	 * @return Returns the userDeptIdPrompt.
	 */
	public String getUserDeptIdPrompt() {
		return userDeptIdPrompt;
	}
	/**
	 * @param userDeptIdPrompt The userDeptIdPrompt to set.
	 */
	public void setUserDeptIdPrompt(String userDeptIdPrompt) {
		this.userDeptIdPrompt = userDeptIdPrompt;
	}
	/**
	 * @return Returns the agencyIdPrompt.
	 */
	public String getAgencyIdPrompt() {
		return agencyIdPrompt;
	}
	/**
	 * @param agencyIdPrompt The agencyIdPrompt to set.
	 */
	public void setAgencyIdPrompt(String agencyIdPrompt) {
		this.agencyIdPrompt = agencyIdPrompt;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
}
