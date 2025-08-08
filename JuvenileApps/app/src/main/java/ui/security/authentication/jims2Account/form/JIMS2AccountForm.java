package ui.security.authentication.jims2Account.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cshimek - Created on Sep 28, 2006
 * 
 * This form contains all the attributes needed for Manage JIMS2 Accounts records 
 */
public class JIMS2AccountForm extends ActionForm
{
	private String accountStatus;
	private String action;
	private String activateDate;
	private String activatedBy;
	private String badgeNumber;
	private String basicUser;
	private String currentUserLogonId;
	private String currentUserLogonIdChanged;
	private String departmentId;
	private String departmentName;
	private String employeeId;	
	private String firstName;
	private String genericType;
	private String inactivateDate;
	private String inactivatedBy;
	private String jims2AccountId;
	private String jimsLogonId;
	private String jims2LogonId;
	private String jims2Password;
	private String lastName;
	private String middleName;	
	private String newJIMS2LogonId;
	private String newPassword;
	private String otherIdNumber;
	private String pageType;	
	private String password;
	private String passwordAnswer;
	private String reenterJIMS2LogonId;
	private String reenterNewJIMS2LogonId;
	private String passwordQuestion;
	private String passwordQuestionId;	
	private String reenterNewPassword;
	private String reenterPassword;	
	private String searchResultCount;	
	private String serviceProviderName;
	private String showInactive;
	private String userAccountId;
	private String UserName;
	private String userType;		
	
// input search fields	
	private String searchBadgeNumber;
	private String searchEmployeeId;
	private String searchFirstName;
	private String searchJIMSLogonId;
	private String searchJIMS2LogonId;
	private String searchLogonId;
	private String searchLastName;
	private String searchOtherIdNumber;
	private String searchTypeId;

	
// collections
	private Collection passwordQuestions;
	private Collection users;	
	
	private static Collection emptyColl = new ArrayList();

	/**
	 * Constructor for the RoleGroupForm 
	 */
	public JIMS2AccountForm()
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
		this.accountStatus = null;		
		this.action = null;
		this.activateDate =null;
		this.activatedBy = null;
		this.badgeNumber = null;
		this.currentUserLogonIdChanged = null;
		this.departmentId = null;
		this.departmentName = null;
		this.employeeId = null;	
		this.firstName = null;
		this.genericType = null;
		this.inactivateDate = null;
		this.inactivatedBy = null;
		this.jims2AccountId = null;
		this.jimsLogonId = null;
		this.jims2LogonId = null;
		this.jims2Password = null;
		this.lastName = null;
		this.middleName = null;	
		this.newJIMS2LogonId = null;
		this.newPassword = null;
		this.otherIdNumber = null;
		this.pageType = null;
		this.password = null;
		this.passwordAnswer = null;
		this.passwordQuestion = null;
		this.passwordQuestionId = null;
		this.reenterJIMS2LogonId = null;
		this.reenterNewJIMS2LogonId = null;
		this.reenterNewPassword = null;
		this.reenterPassword = null;		
		this.searchResultCount = null;		
		this.serviceProviderName = null;
		this.userAccountId = null;
		this.UserName = null;
		
//		 search fields	
		this.searchBadgeNumber = null;
		this.searchEmployeeId = null;
		this.searchFirstName = null;
		this.searchJIMSLogonId = null;
		this.searchJIMS2LogonId = null;
		this.searchLogonId = null;
		this.searchLastName = null;
		this.searchOtherIdNumber = null;
		this.searchTypeId = null;
		
// collections 
		this.users = emptyColl;
	}

	/**
	 * @return Returns the accountStatus.
	 */
	public String getAccountStatus() {
		return accountStatus;
	}
	/**
	 * @param accountStatus The accountStatus to set.
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
	 * @return Returns the activateDate.
	 */
	public String getActivateDate() {
		return activateDate;
	}
	/**
	 * @param activateDate The activateDate to set.
	 */
	public void setActivateDate(String activateDate) {
		this.activateDate = activateDate;
	}
	/**
	 * @return Returns the activatedBy.
	 */
	public String getActivatedBy() {
		return activatedBy;
	}
	/**
	 * @param activatedBy The activatedBy to set.
	 */
	public void setActivatedBy(String activatedBy) {
		this.activatedBy = activatedBy;
	}
	/**
	 * @return Returns the badgeNumber.
	 */
	public String getBadgeNumber() {
		return badgeNumber;
	}
	/**
	 * @param badgeNumber The badgeNumber to set.
	 */
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	/**
	 * @return Returns the basicUser.
	 */
	public String getBasicUser() {
		return basicUser;
	}
	/**
	 * @param basicUser The basicUser to set.
	 */
	public void setBasicUser(String basicUser) {
		this.basicUser = basicUser;
	}	
	/**
	 * @return Returns the currentUserLogonId.
	 */
	public String getCurrentUserLogonId() {
		return currentUserLogonId;
	}
	/**
	 * @param currentUserLogonId The currentUserLogonId to set.
	 */
	public void setCurrentUserLogonId(String currentUserLogonId) {
		this.currentUserLogonId = currentUserLogonId;
	}
	/**
	 * @return Returns the currentUserLogonIdChanged.
	 */
	public String getCurrentUserLogonIdChanged() {
		return currentUserLogonIdChanged;
	}
	/**
	 * @param currentUserLogonIdChanged The currentUserLogonIdChanged to set.
	 */
	public void setCurrentUserLogonIdChanged(String currentUserLogonIdChanged) {
		this.currentUserLogonIdChanged = currentUserLogonIdChanged;
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
	 * @return Returns the genericType.
	 */
	public String getGenericType() {
		return genericType;
	}
	/**
	 * @param genericType The genericType to set.
	 */
	public void setGenericType(String genericType) {
		this.genericType = genericType;
	}	
	/**
	 * @return Returns the inactivateDate.
	 */
	public String getInactivateDate() {
		return inactivateDate;
	}
	/**
	 * @param inactivateDate The inactivateDate to set.
	 */
	public void setInactivateDate(String inactivateDate) {
		this.inactivateDate = inactivateDate;
	}
	/**
	 * @return Returns the inactivatedBy.
	 */
	public String getInactivatedBy() {
		return inactivatedBy;
	}
	/**
	 * @param inactivatedBy The inactivatedBy to set.
	 */
	public void setInactivatedBy(String inactivatedBy) {
		this.inactivatedBy = inactivatedBy;
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
	 * @return Returns the jimsLogonId.
	 */
	public String getJimsLogonId() {
		return jimsLogonId;
	}
	/**
	 * @param jimsLogonId The jimsLogonId to set.
	 */
	public void setJimsLogonId(String jimsLogonId) {
		this.jimsLogonId = jimsLogonId;
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
	 * @return Returns the newJIMS2LogonId.
	 */
	public String getNewJIMS2LogonId() {
		return newJIMS2LogonId;
	}
	/**
	 * @param newJIMS2LogonId The newJIMS2LogonId to set.
	 */
	public void setNewJIMS2LogonId(String newJIMS2LogonId) {
		this.newJIMS2LogonId = newJIMS2LogonId;
	}
	/**
	 * @return Returns the newPassword.
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword The newPassword to set.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}	
	/**
	 * @return Returns the otherIdNumber.
	 */
	public String getOtherIdNumber() {
		return otherIdNumber;
	}
	/**
	 * @param otherIdNumber The otherIdNumber to set.
	 */
	public void setOtherIdNumber(String otherIdNumber) {
		this.otherIdNumber = otherIdNumber;
	}
	/**
	 * @return Returns the pageType.
	 */
	public String getPageType() {
		return pageType;
	}
	/**
	 * @param  pageType The  pageType to set.
	 */
	public void setPageType(String  pageType) {
		this. pageType =  pageType;
	}	
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the passwordAnswer.
	 */
	public String getPasswordAnswer() {
		return passwordAnswer;
	}
	/**
	 * @param passwordAnswer The passwordAnswer to set.
	 */
	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}
	/**
	 * @return Returns the passwordQuestion.
	 */
	public String getPasswordQuestion() {
		return passwordQuestion;
	}
	/**
	 * @param passwordQuestion The passwordQuestion to set.
	 */
	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}
	
	/**
	 * @return Returns the passwordQuestionId.
	 */
	public String getPasswordQuestionId() {
		return passwordQuestionId;
	}
	/**
	 * @param passwordQuestionId The passwordQuestionId to set.
	 */
	public void setPasswordQuestionId(String passwordQuestionId) {
		this.passwordQuestionId = passwordQuestionId;
	}
	/**
	* @return Returns the passwordQuestions.
	*/
		public Collection getPasswordQuestions() {
		passwordQuestions = MessageUtil.processEmptyCollection(passwordQuestions);
		return passwordQuestions;
	}
	/**
	* @param passwordQuestions The passwordQuestions to set.
	*/
		public void setPasswordQuestions(Collection passwordQuestions) {
		this.passwordQuestions = passwordQuestions;
	}

	/**
	 * @return Returns the reenterJIMS2LogonId.
	 */
	public String getReenterJIMS2LogonId() {
		return reenterJIMS2LogonId;
	}
	/**
	 * @param reenterJIMS2LogonId The reenterJIMS2LogonId to set.
	 */
	public void setReenterJIMS2LogonId(String reenterJIMS2LogonId) {
		this.reenterJIMS2LogonId = reenterJIMS2LogonId;
	}
	/**
	 * @return Returns the reenterNewJIMS2LogonId.
	 */
	public String getReenterNewJIMS2LogonId() {
		return reenterNewJIMS2LogonId;
	}
	/**
	 * @param reenterNewJIMS2LogonId The reenterNewJIMS2LogonId to set.
	 */
	public void setReenterNewJIMS2LogonId(String reenterNewJIMS2LogonId) {
		this.reenterNewJIMS2LogonId = reenterNewJIMS2LogonId;
	}
	/**
	 * @return Returns the reenterNewPassword.
	 */
	public String getReenterNewPassword() {
		return reenterNewPassword;
	}
	/**
	 * @param reenterNewPassword The reenterNewPassword to set.
	 */
	public void setReenterNewPassword(String reenterNewPassword) {
		this.reenterNewPassword = reenterNewPassword;
	}
	
	/**
	 * @return Returns the reenterPassword.
	 */
	public String getReenterPassword() {
		return reenterPassword;
	}
	/**
	 * @param reenterPassword The reenterPassword to set.
	 */
	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}
	/**
	 * @return Returns the searchLogonId.
	 */
	public String getSearchLogonId() {
		return searchLogonId;
	}
	/**
	 * @param searchLogonId The searchLogonId to set.
	 */
	public void setSearchLogonId(String searchLogonId) {
		this.searchLogonId = searchLogonId;
	}
	/**
	 * @return Returns the searchResultCount.
	 */
	public String getSearchResultCount() {
		return searchResultCount;
	}
	/**
	 * @param searchResultCount The searchResultCount to set.
	 */
	public void setSearchResultCount(String searchResultCount) {
		this.searchResultCount = searchResultCount;
	}
	/**
	* @return Returns the searchBadgeNumber.
	*/
		public String getSearchBadgeNumber() {
		return searchBadgeNumber;
	}
	/**
	* @param searchBadgeNumber The searchBadgeNumber to set.
	*/
		public void setSearchBadgeNumber(String searchBadgeNumber) {
		this.searchBadgeNumber = searchBadgeNumber;
	}
	/**
	 * @return Returns the searchEmployeeId.
	 */
	public String getSearchEmployeeId() {
		return searchEmployeeId;
	}
	/**
	 * @param searchEmployeeId The searchEmployeeId to set.
	 */
	public void setSearchEmployeeId(String searchEmployeeId) {
		this.searchEmployeeId = searchEmployeeId;
	}
	/**
	 * @return Returns the searchFirstName.
	 */
	public String getSearchFirstName() {
		return searchFirstName;
	}
	/**
	 * @param searchFirstName The searchFirstName to set.
	 */
	public void setSearchFirstName(String searchFirstName) {
		this.searchFirstName = searchFirstName;
	}
	/**
	 * @return Returns the searchJIMS2UserId.
	 */
	public String getSearchJIMS2LogonId() {
		return searchJIMS2LogonId;
	}
	/**
	 * @param searchJIMS2UserId The searchJIMS2UserId to set.
	 */
	public void setSearchJIMS2LogonId(String searchJIMS2LogonId) {
		this.searchJIMS2LogonId = searchJIMS2LogonId;
	}
	/**
	 * @return Returns the searchJIMSUserId.
	 */
	public String getSearchJIMSLogonId() {
		return searchJIMSLogonId;
	}
	/**
	 * @param searchJIMSUserId The searchJIMSUserId to set.
	 */
	public void setSearchJIMSLogonId(String searchJIMSLogonId) {
		this.searchJIMSLogonId = searchJIMSLogonId;
	}
	/**
	 * @return Returns the searchLastName.
	 */
	public String getSearchLastName() {
		return searchLastName;
	}
	/**
	 * @param searchLastName The searchLastName to set.
	 */
	public void setSearchLastName(String searchLastName) {
		this.searchLastName = searchLastName;
	}
	/**
	 * @return Returns the searchOtherIdNumber.
	 */
	public String getSearchOtherIdNumber() {
		return searchOtherIdNumber;
	}
	/**
	 * @param searchOtherIdNumber The searchOtherIdNumber to set.
	 */
	public void setSearchOtherIdNumber(String searchOtherIdNumber) {
		this.searchOtherIdNumber = searchOtherIdNumber;
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
	/**
	 * @return Returns the showInactive.
	 */
	public String getShowInactive() {
		return showInactive;
	}
	/**
	 * @param showInactive The showInactive to set.
	 */
	public void setShowInactive(String showInactive) {
		this.showInactive = showInactive;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * @return Returns the users.
	 */
	public Collection getUsers() {
		users = MessageUtil.processEmptyCollection(users);		
		return users;
	}
	/**
	 * @param users The users to set.
	 */
	public void setUsers(Collection users) {
		this.users = users;
	}
	/**
	 * @return Returns the userType.
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType The userType to set.
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}		
	/**
	 * @return Returns the jims2AccountId.
	 */
	public String getJims2AccountId() {
		return jims2AccountId;
	}
	/**
	 * @param jims2AccountId The jims2AccountId to set.
	 */
	public void setJims2AccountId(String jims2AccountId) {
		this.jims2AccountId = jims2AccountId;
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
	 * @return Returns the userAccountId.
	 */
	public String getUserAccountId() {
		return userAccountId;
	}
	/**
	 * @param userAccountId The userAccountId to set.
	 */
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
	/**
	 * @return Returns the jims2Password.
	 */
	public String getJims2Password() {
		return jims2Password;
	}
	/**
	 * @param jims2Password The jims2Password to set.
	 */
	public void setJims2Password(String jims2Password) {
		this.jims2Password = jims2Password;
	}
}
