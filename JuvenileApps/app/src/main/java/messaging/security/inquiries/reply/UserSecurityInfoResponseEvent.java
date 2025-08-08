/*
 * Created on Aug 28, 2006
 */
package messaging.security.inquiries.reply;

import java.util.ArrayList;
import java.util.Collection;

import messaging.security.reply.RoleResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class UserSecurityInfoResponseEvent extends ResponseEvent
{
	private String lastName;
	private String firstName;
	private String middleName;
	private String agencyId;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private String email;
	private String logonId;
	private String formattedName;
	private String jims2LogonId;
	private String serviceProviderName;
	private Collection roles = new ArrayList();
	private Collection userGroups = new ArrayList();
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
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return Returns the roles.
	 */
	public Collection getRoles() {
		return roles;
	}
	/**
	 * @param role The role to add.
	 */
	public void addRole(RoleResponseEvent role) {
		this.roles.add(role);
	}
	/**
	 * @return Returns the userGroups.
	 */
	public Collection getUserGroups() {
		return userGroups;
	}
	
	/**
	 * @param roles The roles to set.
	 */
	public void setRoles(Collection roles) {
		this.roles = roles;
	}

	/**
	 * @param userGroup The userGroup to add.
	 */
	public void addUserGroup(UserGroupResponseEvent userGroup) {
		this.userGroups.add(userGroup);
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