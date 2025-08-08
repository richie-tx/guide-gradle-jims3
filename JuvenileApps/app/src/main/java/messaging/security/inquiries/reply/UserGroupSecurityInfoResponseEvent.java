/*
 * Created on Aug 22, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.inquiries.reply;

import java.util.ArrayList;
import java.util.Collection;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserGroupSecurityInfoResponseEvent extends ResponseEvent
{
	private String agencyId;
	private String agencyName;
	private String description;
	private String name;
	private Collection users = new ArrayList();
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
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return Returns the users.
	 */
	public Collection getUsers() {
		return users;
	}
	/**
	 * @param user The user to add.
	 */
	public void addUser(SecurityUserResponseEvent user) {
		this.users.add(user);
	}
}