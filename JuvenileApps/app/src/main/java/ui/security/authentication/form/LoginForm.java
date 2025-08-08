/*
 * Created on Dec 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package ui.security.authentication.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import ui.common.Name;
import ui.common.PhoneNumber;


/**
 * @ugopinath 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoginForm extends org.apache.struts.action.ActionForm
{

	// ------------------------------------------------------------------------
	// --- fields                                                           ---
	// ------------------------------------------------------------------------

	private String accountType = "";
	private String accountTypeId = "";
	private String accountTypeOID = "";
	private String action ="";
	private String agencyName = "";
	private String agencyId="";
	//private String answer = "";
	private String badgeNumber = "";
	private String confirmMessage="";
	private String confirmJIMS2Password = "";
	private String confirmJIMS2UserId;
	private String confirmPassword= "";
	private String createOfficerProfileInd="";
	private String currentPassword = "";
	private String departmentId= "";
	private String departmentName = "";
	//#87188
	private String orgCode="";
	private String email="";
	//#87188
	private String displayPassword="";
//	private String employeeId = "";
	private String errorMessage="";
	private String fromPage="";
	//private String forgottenPasswdPhrase = ""; //86322
	//private String forgottenPasswdPhraseId = "";//86322
	private String jims2Password= "";
	private String jims2UserId = "";
	private String jimsUserId = "";
	private String adLogonId=""; //U.S #79250
	private String smUserId=""; //86322
	
	private String logonId = "";
	private String newPassword = "";	
	private String noticeListSize;
	private String noticeMessage;
	private String notificationId;
	private String officerId = "";
	private String otherIdNumber = "";
	private String password = "";
	private String rolesMessage="";
	private String [] selectedNotices;
	private String [] selectedTasks;
	private String serviceProviderId="";
	private String serviceProviderName="";
	private String taskCount;
	private String taskId;
	private String authenticationMethod;
//	private Address userAddress = new Address();
	//private PhoneNumber userCellPhoneNumber = new PhoneNumber("");
	private String userEmail = "";
	private Name userName = new Name();
//	private PhoneNumber userPagerNumber = new PhoneNumber("");
//	private String userStatus="";
	private String userType = "";
	private PhoneNumber userWorkPhoneNumber = new PhoneNumber("");


	//Collections
	//private Collection forgottenPasswdPhraseList= new ArrayList();
	private Collection taskList;
	private Collection noticeList;
	private Collection<String> authenticationMethods;
	
	private List<JuvenileCasefileResponseEvent> activeJuveniles;
	private List<JuvenileCasefileResponseEvent> pendingJuveniles;
	
	//
	private int activeFileCount;
	private int pendingFileCount;
	private String firstName;
	private String lastName;
	private String middleName;

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	public void clear()
	{
		accountType = "";
		accountTypeId = "";
		accountTypeOID = "";
		action="";
		agencyName = "";
		smUserId="";
		//answer="";
		badgeNumber="";
		confirmJIMS2Password="";
		confirmJIMS2UserId="";		
		confirmMessage="";	
		createOfficerProfileInd="";
		currentPassword = "";
		errorMessage="";
		//forgottenPasswdPhrase="";
		fromPage="";
		jims2Password="";
		jims2UserId="";
		newPassword = "";
		otherIdNumber="";
		password = "";
		rolesMessage="";
		serviceProviderId="";
		serviceProviderName="";
//		userAddress = new Address();	
		//userCellPhoneNumber = new PhoneNumber("");
		userEmail = "";
		userName=new Name();
		//userPagerNumber = new PhoneNumber("");
		userWorkPhoneNumber = new PhoneNumber("");

		this.noticeList = new ArrayList();
		this.taskList = new ArrayList();
		this.selectedNotices = null;
		this.selectedTasks = null;
		this.taskCount = null;
		this.activeFileCount = 0;
		this.pendingFileCount = 0;
		this.taskId = null;
		this.noticeMessage = null;
		this.notificationId = null;
		this.noticeListSize = null;
		this.authenticationMethods = new ArrayList<String>();
		
	} //end of ui.login.form.LoginForm.clear

	public void clearLoginDetails()
	{
		password="";
		logonId="";
		authenticationMethod="";
	}
	
	public void clearOfficerLogin()
	{
		this.badgeNumber="";
		this.otherIdNumber="";		
	
	}
	
	public void clearSPLogin()
	{
		serviceProviderId="";
	}
	
	public void clearOfficerDetails()
	{
		this.userName=new Name();
		this.userWorkPhoneNumber=new PhoneNumber("");
	//	this.userCellPhoneNumber=new PhoneNumber(""); #87188 changes
	//	this.userPagerNumber=new PhoneNumber(""); #87188
		this.jims2Password="";
		this.confirmJIMS2Password="";
	//	this.forgottenPasswdPhrase="";
	//	this.forgottenPasswdPhraseId="";
	//	this.answer="";
		this.userEmail="";	
	}
	public void clearJIMSMessages()
	{
		this.errorMessage="";
		this.confirmMessage="";		
		this.action="";
			
	}
	
	public void clearJIMS2LoginDetails()
	{
		this.jims2Password="";
		this.confirmJIMS2Password="";
		//this.forgottenPasswdPhrase="";
		//this.answer="";
	}
	/**
	*  
	* @return the action
	*/
	public String getAction()
	{
	   return action;
	} //end of ui.login.form.LoginForm.getAction

	/**
	*  
	* @return the accountType
	*/
	public String getAccountType()
	{
	   return accountType;
	} //end of ui.login.form.LoginForm.getAccountType
	
	/**
	*  
	* @return the accountTypeId
	*/
	public String getAccountTypeId()
	{
	   return accountTypeId;
	} //end of ui.login.form.LoginForm.getAccountTypeId
	
	
	/**
	*  
	* @return the accountTypeOID
	*/
	public String getAccountTypeOID()
	{
	   return accountTypeOID;
	} //end of ui.login.form.LoginForm.getAccountTypeOID
	
	/**
	*  
	* @return the agencyName
	*/
	public String getAgencyName()
	{
	   return agencyName;
	} //end of ui.login.form.LoginForm.getAgencyName

	
	 /**
	 *  
	 * @return the answer
	 */
	/*public String getAnswer()
	{
		return answer;
	} //end of ui.login.form.LoginForm.getAnswer

	*/

	 /**
	 *  
	 * @return the badgeNumber
	 */
	public String getBadgeNumber()
	{
		return badgeNumber;
	} //end of ui.login.form.LoginForm.getBadgeNumber

	
	
	/**
	 *  
	 * @return confirmJIMS2Password
	 */
	public String getConfirmJIMS2Password()
	{
		return confirmJIMS2Password;
	} //end of ui.login.form.LoginForm.getConfirmJIMS2Password
	
	/**
	 *  
	 * @return confirmMessage
	 */
	public String getConfirmMessage()
	{
		return confirmMessage;
	} //end of ui.login.form.LoginForm.getConfirmMessage
	
	/**
	 *  
	 * @return confirmPassword
	 */
	public String getConfirmPassword()
	{
		return confirmPassword;
	} //end of ui.login.form.LoginForm.getConfirmPassword
	
	/**
	 *  
	 * @return  createOfficerProfileInd
	 */
	public String getCreateOfficerProfileInd()
	{
		return  createOfficerProfileInd;
	} //end of ui.login.form.LoginForm.getCreateOfficerProfileInd()
	
	/**
	 *  
	 * @return currentPassword
	 */
	public String getCurrentPassword()
	{
		return currentPassword;
	} //end of ui.login.form.LoginForm.getCurrentPassword

	/**
	 *  
	 * @return displayPassword
	 */
	public String getDisplayPassword()
	{
		return displayPassword;
	} //end of ui.login.form.LoginForm.getdisplayPassword
	/**
	 *  
	 * @return departmentId
	 */
	public String getDepartmentId()
	{
		return departmentId;
	} //end of ui.login.form.LoginForm.getDepartmentId
	
	
	/**
	 *  
	 * @return departmentName
	 */
	public String getDepartmentName()
	{
		return departmentName;
	} //end of ui.login.form.LoginForm.getDepartmentName
	/**
	 *  
	 * @return the employeeId
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public String getEmployeeId()
	{
		return employeeId;
	} *///end of ui.login.form.LoginForm.getEmployeeId

	/**
	 * @return
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}


	/**
	 *  
	 * @return forgottenPasswdPhrase
	 */
	/*public String getForgottenPasswdPhrase()
	{
		return forgottenPasswdPhrase;
	} *///end of ui.login.form.LoginForm.getForgottenPasswdPhrase

	/**
	 *  
	 * @return forgottenPasswdPhraseId
	 */
	/*public String getForgottenPasswdPhraseId()
	{
		return forgottenPasswdPhraseId;
	} //end of ui.login.form.LoginForm.getForgottenPasswdPhraseId
	*//**
	 *  
	 * @return forgottenPasswdPhraseList
	 */
	/*public Collection getForgottenPasswdPhraseList()
	{
		return forgottenPasswdPhraseList;
	} *///end of ui.login.form.LoginForm.getForgottenPasswdPhrase


	/**
	 * @return Returns the fromPage.
	 */
	public String getFromPage() {
		return fromPage;
	}
	/**
	 * @param fromPage The fromPage to set.
	 */
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	/**
	 *  
	 * @return jims2UserId 
	 */
	public String getJims2UserId()
	{
		return jims2UserId;
	} //end of ui.login.form.LoginForm.getJims2UserId

	/**
	 *  
	 * @return jims2Password 
	 */
	public String getJims2Password()
	{
		return jims2Password;
	} //end of ui.login.form.LoginForm.getJims2Password
	
	/**
	 *  
	 * @return the newPassword
	 */
	public String getNewPassword()
	{
		return newPassword;
	} //end of ui.login.form.LoginForm.getNewPassword
	
	/**
	 *  
	 * @return the officerId
	 */
	public String getOfficerId()
	{
		return this.officerId;
	} //end of ui.login.form.LoginForm.getOfficerId
	
	/**
	 *  
	 * @return the password
	 */
	public String getPassword()
	{
		return this.password;
	} //end of ui.login.form.LoginForm.getPassword

	/**
	 *  
	 * @return the otherIdNumber
	 */
		public String getOtherIdNumber()
		{
			return this.otherIdNumber;
		} //end of ui.login.form.LoginForm.getOtherIdNumber
	 /**
	 *  
	 * @return the Number
	 */
	public String getNumber()
	{
		return otherIdNumber;
	} //end of ui.login.form.LoginForm.getNumber
	
	/**
	 *  
	 * @return rolesMessage
	 */
	public String getRolesMessage()
	{
		return rolesMessage;
	} //end of ui.login.form.LoginForm.getRolesMessage


	/**
	 *  
	 * @return  the serviceProviderId.
	 */
	public String getServiceProviderId()
	{
		return (serviceProviderId);
	} //end of ui.login.form.LoginForm.getServiceProviderId

	/**
	 *  
	 * @return  the serviceProviderName.
	 */
	public String getServiceProviderName()
	{
		return (serviceProviderName);
	} //end of ui.login.form.LoginForm.getServiceProviderName

	/**
	 *  
	 * @return  the userAddress.
	 */
//	public Address getUserAddress()
//	{
//		return (userAddress);
//	} //end of ui.login.form.LoginForm.getUserAddress

	/**
	 *  
	 * @return the userEmail
	 */
	public String getUserEmail()
	{
		return userEmail;
	} //end of ui.login.form.LoginForm.getUserEmail


	
	/**
	 *  
	 * @return  the User Id.
	 */
	public String getLogonId()
	{
		return this.logonId;
	} //end of ui.login.form.LoginForm.getLogonId

	/**
	 *  
	 * @return  the userName.
	 */
	public Name getUserName()
	{
		return (userName);
	} //end of ui.login.form.LoginForm.getUserName

	/**
	 *  
	 * @return  the userCellPhoneNumber.
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public PhoneNumber getUserCellPhoneNumber()
	{
		return (userCellPhoneNumber);
	} *///end of ui.login.form.LoginForm.getUserCellPhoneNumber

	/**
	 *  
	 * @return  the userPagerNumber.
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public PhoneNumber getUserPagerNumber()
	{
		return (userPagerNumber);
	} *///end of ui.login.form.LoginForm.getUserPagerNumber


	/**
	 *  
	 * @return  the userType.
	 */
	public String getUserType()
	{
		return (userType);
	} //end of ui.login.form.LoginForm.getUserType
	
	/**
	 *  
	 * @return  the userStatus.
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public String getUserStatus()
	{
		return (userStatus);
	}*/ //end of ui.login.form.LoginForm.getUserStatus
		
	/**
	 *  
	 * @return  the userWorkPhoneNumber.
	 */
	public PhoneNumber getUserWorkPhoneNumber()
	{
		return (userWorkPhoneNumber);
	} //end of ui.login.form.LoginForm.getUserWorkPhoneNumber	
	
	
	/**
	 *  
	 * @param action
	 */
	public void setAction(String act)
	{
		
		this.action = act;
	} //end of ui.login.form.LoginForm.setAction
	
	/**
	*  
	* @param acctType
	*/
	public void setAccountType(String acctType)
	{
	   accountType = acctType;
	} //end of ui.login.form.LoginForm.setAccountType
	
	/**
	*  
	* @param accountTypeId
	*/
	public void setAccountTypeId(String accountId)
	{
		this.accountTypeId = accountId;
	} //end of ui.login.form.LoginForm.setAccountTypeId
	
	/**
	* @param acctTypeOID
	*/
	public void setAccountTypeOID(String acctTypeOID)
	{
		this.accountTypeOID = acctTypeOID;
	} //end of ui.login.form.LoginForm.setAccountTypeOID
	
	
	/**
	 *  
	 * @param agencyName
	 */
	public void setAgencyName(String agency)
	{
		this.agencyName = agency;
	} //end of ui.login.form.LoginForm.setAgencyName
	
	

	 /**
	 *  
	 * @param answer
	 */
/*	public void setAnswer(String answer)
	{
		this.answer = answer;
	} //end of ui.login.form.LoginForm.setAnswer
*/
	

	 /**
	 *  
	 * @param badgeNumber
	 */
	public void setBadgeNumber(String badgeNumber)
	{
		this.badgeNumber = badgeNumber;
	} //end of ui.login.form.LoginForm.setBadgeNumber

	/**
	 *  
	 * @param confirmJIMS2Passwd
	 */
	public void setConfirmJIMS2Password(String confirmJIMS2Passwd)
	{
		this.confirmJIMS2Password = confirmJIMS2Passwd;
	} //end of ui.login.form.LoginForm.setConfirmJIMS2Password

	/**
	 *  
	 * @param confirmPasswd
	 */
	public void setConfirmPassword(String confirmPasswd)
	{
		this.confirmPassword = confirmPasswd;
	} //end of ui.login.form.LoginForm.setConfirmPassword
	
	/**
	 *  
	 * @param confirmMessage
	 */
	public void setConfirmMessage(String confirmMsg)
	{
		this.confirmMessage = confirmMsg;
	} //end of ui.login.form.LoginForm.setConfirmMessage
	
	/**
	 *  
	 * @param  createOfficerProfileInd
	 */
	public void setCreateOfficerProfileInd(String  string)
	{
		createOfficerProfileInd = string;
	} //end of ui.login.form.LoginForm.setCreateOfficerProfileInd
	
	/**
	 *  
	 * @param currentPasswd
	 */
	public void setCurrentPassword(String currentPasswd)
	{
		currentPassword = currentPasswd;
	} //end of ui.login.form.LoginForm.setCurrentPassword

	
	/**
	 *  
	 * @param departmentId
	 */
	public void setDepartmentId(String deptId)
	{
		this.departmentId = deptId;
	} //end of ui.login.form.LoginForm.setDepartmentId
	/**
	 *  
	 * @param departmentName
	 */
	public void setDepartmentName(String deptName)
	{
		this.departmentName = deptName;
	} //end of ui.login.form.LoginForm.setDepartmentName

	/**
	 *  
	 * @param displayPassword
	 */
	public void setDisplayPassword(String dispPasswd)
	{
		this.displayPassword = dispPasswd;
	} //end of ui.login.form.LoginForm.setDisplayPassword
	
	/**
	 *  
	 * @param employeeID
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public void setEmployeeId(String empId)
	{
		this.employeeId = empId;
	}*/ //end of ui.login.form.LoginForm.setEmployeeId
	
	/**
	 * @param string
	 */
	public void setErrorMessage(String string)
	{
		errorMessage = string;
	}

	/**
	 *  
	 * @param forgottenPasswdPhrase
	 */
/*	public void setForgottenPasswdPhrase(String forgottenPasswdPhrase)
	{
		this.forgottenPasswdPhrase = forgottenPasswdPhrase;
	} //end of ui.login.form.LoginForm.setForgottenPasswdPhrase
	
	*//**
	 *  
	 * @param forgottenPasswdPhraseId
	 *//*
	public void setForgottenPasswdPhraseId(String forgottenPasswdPhraseId)
	{
		this.forgottenPasswdPhraseId = forgottenPasswdPhraseId;
	} //end of ui.login.form.LoginForm.setForgottenPasswdPhraseId
*/
	/**
	 *  
	 * @param forgottenPasswdPhraseList
	 */
	/*public void setForgottenPasswdPhraseList(Collection coll)
	{
		this.forgottenPasswdPhraseList = coll;
	} //end of ui.login.form.LoginForm.setForgottenPasswdPhraseList
*/
	/**	
	 *  
	 * @param jims2UserId
	 */
	public void setJims2UserId(String JIMS2UserId)
	{
		this.jims2UserId = JIMS2UserId;
	} //end of ui.login.form.LoginForm.setJims2UserId
	
	/**	
	 *  
	 * @param jims2Password
	 */
	public void setJims2Password(String JIMS2Password)
	{
		this.jims2Password = JIMS2Password;
	} //end of ui.login.form.LoginForm.setJims2Password

	/**
	 *  
	 * @param newPasswd
	 */
	public void setNewPassword(String newPasswd)
	{
		this.newPassword = newPasswd;
	} //end of ui.login.form.LoginForm.setNewPassword
	
	/**
	 *  
	 * @param string
	 */
	public void setOfficerId(String string)
	{
		this.officerId = string;
	} //end of ui.login.form.LoginForm.setOfficerId

	
	/**
	 *
	 * @param passwd
	 */
	public void setPassword(String passwd)
	{
		this.password = passwd;
	}//end of ui.login.form.LoginForm.setPassword
	
	/**
	  *
	  * @param otherIdNum
	 */
		public void setOtherIdNumber(String otherIdNum)
		{
			this.otherIdNumber =otherIdNum;
		}//end of ui.login.form.LoginForm.setOtherIdNumber

	 /**
	 *  
	 * @param Num
	 */
	public void setNumber(String Num)
	{
		this.otherIdNumber = Num;
	} //end of ui.login.form.LoginForm.setNumber
	
	/**
	 *  
	 * @param rolesMsg
	 */
	public void setRolesMessage(String rolesMsg)
	{
		rolesMessage = rolesMsg;
	} //end of ui.login.form.LoginForm.setRolesMessage

	/**
	 *  
	 * @param serviceProviderId
	 */
	 public void setServiceProviderId(String serviceProviderId)
	 {
	   this.serviceProviderId = serviceProviderId;
	 } //end of ui.login.form.LoginForm.setServiceProviderId

	/**
	 *  
	 * @param serviceProviderName
	 */
	 public void setServiceProviderName(String serviceProviderName)
	 {
	   this.serviceProviderName = serviceProviderName;
	 } //end of ui.login.form.LoginForm.setServiceProviderName

	/**
	 *  
	 * @param userAddr.
//	 */
//	public void setUserAddress(Address userAddr)
//	{
//		this.userAddress = userAddr;
//	} //end of ui.login.form.LoginForm.setUserAddress
//

	/**
	 *  
	 * @param userCellPhoneNum
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public void setUserCellPhoneNumber(PhoneNumber userCellPhoneNum)
	{
		this.userCellPhoneNumber = userCellPhoneNum;
	} *///end of ui.login.form.LoginForm.setUserCellPhoneNumber


	/**
	 *  
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	} //end of ui.login.form.LoginForm.setUserEmail


	/**
	 *
	 * @param userId
	 */
	public void setLogonId(String userId)
	{
		this.logonId = userId;
	}//end of ui.login.form.LoginForm.setLogonId

	/**  
	 * @param userName.
	 */
	public void setUserName(Name userName)
	{
		this.userName = userName;
	} //end of ui.login.form.LoginForm.setUserName

	
	/**
	 *  
	 * @param userPagerNum
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public void setUserPagerNumber(PhoneNumber userPagerNum)
	{
		this.userPagerNumber = userPagerNum;
	}*/ //end of ui.login.form.LoginForm.setUserPagerNumber

	/**
	 *  
	 * @param type
	 */
	public void setUserType(String type)
	{
		userType = type;
	} //end of ui.login.form.LoginForm.setUserType

	/**
	 *  
	 * @param stat
	 */
	// no longer in use. Migrated to SM. Refer US #87188.
	/*public void setUserStatus(String stat)
	{
		userStatus = stat;
	}*/ //end of ui.login.form.LoginForm.setUserStatus
	/**
	 *  
	 * @param userWorkPhoneNum
	 */
	public void setUserWorkPhoneNumber(PhoneNumber userWorkPhoneNum)
	{
		this.userWorkPhoneNumber = userWorkPhoneNum;
	} //end of ui.login.form.LoginForm.getUserWorkPhoneNumber

	/**
	 * @return Returns the noticeList.
	 */
	public Collection getNoticeList() {
		return noticeList;
	}
	/**
	 * @param noticeList The noticeList to set.
	 */
	public void setNoticeList(Collection noticeList) {
		this.noticeList = noticeList;
	}
	/**
	 * @return Returns the taskList.
	 */
	public Collection getTaskList() {
		return taskList;
	}
	/**
	 * @param taskList The taskList to set.
	 */
	public void setTaskList(Collection taskList) {
		this.taskList = taskList;
	}
	/**
	 * @return Returns the selectedNotices.
	 */
	public String[] getSelectedNotices() {
		return selectedNotices;
	}
	/**
	 * @param selectedNotices The selectedNotices to set.
	 */
	public void setSelectedNotices(String[] selectedNotices) {
		this.selectedNotices = selectedNotices;
	}
	/**
	 * @return Returns the selectedTasks.
	 */
	public String[] getSelectedTasks() {
		return selectedTasks;
	}
	/**
	 * @param selectedTasks The selectedTasks to set.
	 */
	public void setSelectedTasks(String[] selectedTasks) {
		this.selectedTasks = selectedTasks;
	}
	/**
	 * @return Returns the taskCount.
	 */
	public String getTaskCount() {
		return taskCount;
	}
	/**
	 * @param taskCount The taskCount to set.
	 */
	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}
	/**
	 * @return Returns the taskId.
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return Returns the noticeMessage.
	 */
	public String getNoticeMessage() {
		return noticeMessage;
	}
	/**
	 * @param noticeMessage The noticeMessage to set.
	 */
	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}
	/**
	 * @return Returns the notificationId.
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * @param notificationId The notificationId to set.
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	/**
	 * @return Returns the noticeListSize.
	 */
	public String getNoticeListSize() {
		return noticeListSize;
	}
	/**
	 * @param noticeListSize The noticeListSize to set.
	 */
	public void setNoticeListSize(String noticeListSize) {
		this.noticeListSize = noticeListSize;
	}
	/**
	 * @return Returns the confirmJIMS2UserId.
	 */
	public String getConfirmJIMS2UserId() {
		return confirmJIMS2UserId;
	}
	/**
	 * @param confirmJIMS2UserId The confirmJIMS2UserId to set.
	 */
	public void setConfirmJIMS2UserId(String confirmJIMS2UserId) {
		this.confirmJIMS2UserId = confirmJIMS2UserId;
	}
	/**
	 * @return Returns the jimsUserId.
	 */
	public String getJimsUserId() {
		return jimsUserId;
	}
	/**
	 * @param jimsUserId The jimsUserId to set.
	 */
	public void setJimsUserId(String jimsUserId) {
		this.jimsUserId = jimsUserId;
	}

	public int getActiveFileCount() {
		return activeFileCount;
	}

	public void setActiveFileCount(int activeFileCount) {
		this.activeFileCount = activeFileCount;
	}

	public int getPendingFileCount() {
		return pendingFileCount;
	}

	public void setPendingFileCount(int pendingFileCount) {
		this.pendingFileCount = pendingFileCount;
	}

	public List<JuvenileCasefileResponseEvent> getActiveJuveniles() {
		return activeJuveniles;
	}

	public void setActiveJuveniles(List<JuvenileCasefileResponseEvent> activeJuveniles) {
		this.activeJuveniles = activeJuveniles;
	}

	public List<JuvenileCasefileResponseEvent> getPendingJuveniles() {
		return pendingJuveniles;
	}

	public void setPendingJuveniles(List<JuvenileCasefileResponseEvent> pendingJuveniles) {
		this.pendingJuveniles = pendingJuveniles;
	}

	/**
	 * @return the adLogonId
	 */
	public String getAdLogonId()
	{
	    return adLogonId;
	}

	/**
	 * @param adLogonId the adLogonId to set
	 */
	public void setAdLogonId(String adLogonId)
	{
	    this.adLogonId = adLogonId;
	}

	/**
	 * @return the authenticationMethods
	 */
	public Collection<String> getAuthenticationMethods()
	{
	    return authenticationMethods;
	}

	/**
	 * @param authenticationMethods the authenticationMethods to set
	 */
	public void setAuthenticationMethods(Collection<String> authenticationMethods)
	{
	    this.authenticationMethods = authenticationMethods;
	}

	/**
	 * @return the authenticationMethod
	 */
	public String getAuthenticationMethod()
	{
	    return authenticationMethod;
	}

	/**
	 * @param authenticationMethod the authenticationMethod to set
	 */
	public void setAuthenticationMethod(String authenticationMethod)
	{
	    this.authenticationMethod = authenticationMethod;
	}

	public String getSmUserId()
	{
	    return smUserId;
	}

	public void setSmUserId(String smUserId)
	{
	    this.smUserId = smUserId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
	    return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
	    this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
	    return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
	    this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName()
	{
	    return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName)
	{
	    this.middleName = middleName;
	}

	/**
	 * @return the agencyId
	 */
	public String getAgencyId()
	{
	    return agencyId;
	}

	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId)
	{
	    this.agencyId = agencyId;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode()
	{
	    return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode)
	{
	    this.orgCode = orgCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
	    return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
	    this.email = email;
	}


	
} // end LoginForm
