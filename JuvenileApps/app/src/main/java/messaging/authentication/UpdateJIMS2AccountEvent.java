/*
 * Created on Dec 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.authentication;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateJIMS2AccountEvent extends RequestEvent {
    private String jims2AccountId;
    private String jims2LogonId;
    private String jimsLogonId;
    private String statusId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userTypeId;
    private String password;
    private String passwordQuestionId;
    private String passwordAnswer;
    
    private String action;
    
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
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
	 * @return Returns the userTypeId.
	 */
	public String getUserTypeId() {
		return userTypeId;
	}
	/**
	 * @param userTypeId The userTypeId to set.
	 */
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
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
}
