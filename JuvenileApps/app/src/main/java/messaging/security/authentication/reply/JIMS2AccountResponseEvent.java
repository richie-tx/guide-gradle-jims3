/*
 * Created on Dec 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.authentication.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JIMS2AccountResponseEvent extends ResponseEvent implements Comparable
{
	private String departmentId;
	private String departmentName;
	private String email;
	private String firstName;
	private String forgottenPasswdPhrase;
	private String forgottenPasswdPhraseId;
	private String JIMS2AccountId;
	private String JIMS2AccountTypeId;
	private String JIMS2AccountTypeOID;
	private String JIMS2LogonId;
	private String JIMS2Password;
	private String jimsLogonId;
	private String jimsPassword;
	private String lastName;
	private String middleName;
	private String passwordAnswer;
	private String passwordQuestion;
	private String phoneExt;
	private String phoneNum;
	private String status;
	private Date activateDate;
	private String activatedBy;
	private Date inactivateDate;
	private String inactivatedBy;
	
	// officer profile info
	private String badgeNum;
	private String otherIdNum;
	
	// service provider profile Info
	private String serviceProviderName;
	private String employeeId;
	private boolean isAccountCreated; //u.s .79250
	
	

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
	public String getEmail()
	{
		return email;
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
	public String getForgottenPasswdPhrase()
	{
		return forgottenPasswdPhrase;
	}

	/**
	 * @return
	 */
	public String getForgottenPasswdPhraseId()
	{
		return forgottenPasswdPhraseId;
	}

	/**
	 * @return
	 */
	public String getJIMS2AccountId()
	{
		return JIMS2AccountId;
	}

	/**
	 * @return
	 */
	public String getJIMS2AccountTypeId()
	{
		return JIMS2AccountTypeId;
	}

	/**
	 * @return
	 */
	public String getJIMS2AccountTypeOID()
	{
		return JIMS2AccountTypeOID;
	}
	
	/**
	 * @return
	 */
	public String getJIMS2LogonId()
	{
		return JIMS2LogonId;
	}

	/**
	 * @return
	 */
	public String getJimsLogonId()
	{
		return jimsLogonId;
	}

	/**
	 * @return
	 */
	public String getJimsPassword()
	{
		return jimsPassword;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getPasswordAnswer()
	{
		return passwordAnswer;
	}

	/**
	 * @return
	 */
	public String getPhoneExt()
	{
		return phoneExt;
	}

	/**
	 * @return
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param forgottenPasswdPhrase
	 */
	public void setForgottenPasswdPhrase(String forgottenPasswdPhrase)
	{
		this.forgottenPasswdPhrase = forgottenPasswdPhrase;
	}

	/**
	 * @param forgottenPasswdPhraseId
	 */
	public void setForgottenPasswdPhraseId(String forgottenPasswdPhraseId)
	{
		this.forgottenPasswdPhraseId = forgottenPasswdPhraseId;
	}

	/**
	 * @param JIMS2AccountId
	 */
	public void setJIMS2AccountId(String JIMS2AccountId)
	{
		this.JIMS2AccountId = JIMS2AccountId;
	}

	/**
	 * @param JIMS2AccountTypeId
	 */
	public void setJIMS2AccountTypeId(String JIMS2AccountTypeId)
	{
		this.JIMS2AccountTypeId = JIMS2AccountTypeId;
	}

	/**
	 * @param JIMS2AccountTypeOID
	 */
	public void setJIMS2AccountTypeOID(String JIMS2AccountTypeOID)
	{
		this.JIMS2AccountTypeOID = JIMS2AccountTypeOID;
	}

	/**
	 * @param JIMS2LogonId
	 */
	public void setJIMS2LogonId(String JIMS2LogonId)
	{
		this.JIMS2LogonId = JIMS2LogonId;
	}

	/**
	 * @param jimsLogonId
	 */
	public void setJimsLogonId(String jimsLogonId)
	{
		this.jimsLogonId = jimsLogonId;
	}

	/**
	 * @param jimsPassword
	 */
	public void setJimsPassword(String jimsPassword)
	{
		this.jimsPassword = jimsPassword;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param passwordAnswer
	 */
	public void setPasswordAnswer(String passwordAnswer)
	{
		this.passwordAnswer = passwordAnswer;
	}

	/**
	 * @param phoneExt
	 */
	public void setPhoneExt(String phoneExt)
	{
		this.phoneExt = phoneExt;
	}

	/**
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	/**
	 * @return
	 */
	public String getJIMS2Password()
	{
		return JIMS2Password;
	}

	/**
	 * @param JIMS2Password
	 */
	public void setJIMS2Password(String JIMS2Password)
	{
		this.JIMS2Password = JIMS2Password;
	}

	/**
	 * @return 
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return Returns the activateDate.
	 */
	public Date getActivateDate() {
		return activateDate;
	}
	/**
	 * @param activateDate The activateDate to set.
	 */
	public void setActivateDate(Date activateDate) {
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
	 * @return Returns the inactivateDate.
	 */
	public Date getInactivateDate() {
		return inactivateDate;
	}
	/**
	 * @param inactivateDate The inactivateDate to set.
	 */
	public void setInactivateDate(Date inactivateDate) {
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
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		JIMS2AccountResponseEvent c = (JIMS2AccountResponseEvent)o;
		if (c.getJIMS2LogonId() == null){
			return -1;
		}		
		if (this.getJIMS2LogonId() == null){
			return 1;
		}
		return this.getJIMS2LogonId().compareToIgnoreCase(c.getJIMS2LogonId());
	}	
	
	/**
	 * @return Returns the badgeNum.
	 */
	public String getBadgeNum() {
		return badgeNum;
	}
	/**
	 * @param badgeNum The badgeNum to set.
	 */
	public void setBadgeNum(String badgeNum) {
		this.badgeNum = badgeNum;
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
	 * @return Returns the otherIdNum.
	 */
	public String getOtherIdNum() {
		return otherIdNum;
	}
	/**
	 * @param otherIdNum The otherIdNum to set.
	 */
	public void setOtherIdNum(String otherIdNum) {
		this.otherIdNum = otherIdNum;
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
	 * @return the isAccountCreated
	 */
	public boolean isAccountCreated()
	{
	    return isAccountCreated;
	}

	/**
	 * @param isAccountCreated the isAccountCreated to set
	 */
	public void setAccountCreated(boolean isAccountCreated)
	{
	    this.isAccountCreated = isAccountCreated;
	}

}