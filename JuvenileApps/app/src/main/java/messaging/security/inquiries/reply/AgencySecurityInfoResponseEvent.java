/*
 * Created on Aug 24, 2006
 */
package messaging.security.inquiries.reply;

import java.util.ArrayList;
import java.util.Collection;

import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class AgencySecurityInfoResponseEvent extends ResponseEvent {
	private String agencyId;
	private String agencyName;
	private Collection sAUsers = new ArrayList();
	private Collection departments = new ArrayList();
	private Collection roles = new ArrayList();

	
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
	 * @return Returns the departments.
	 */
	public Collection getDepartments() {
		return departments;
	}
	/**
	 * @param department The department to add.
	 */
	public void addDepartment(DepartmentResponseEvent department) {
		this.departments.add(department);
	}
	/**
	 * @return Returns the roles.
	 */
	public Collection getRoles() {
		return roles;
	}
	/**
	 * @param role The role to addt.
	 */
	public void addRole(RoleResponseEvent role) {
		this.roles.add(role);
	}
	/**
	 * @return Returns the sAUsers.
	 */
	public Collection getSAUsers() {
		return sAUsers;
	}
	/**
	 * @param users The sAUsers to set.
	 */
	public void setSAUsers(Collection users) {
		sAUsers = users;
	}
}