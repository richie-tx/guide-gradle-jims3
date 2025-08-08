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
import messaging.security.reply.FeaturesResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoleSecurityInfoResponseEvent extends ResponseEvent
{
	private String roleId;
	private String roleName;
	private String agencyName;
	private String roleDescription;
	private Collection users = new ArrayList();
	private Collection features = new ArrayList();
	private Collection userGroups = new ArrayList();
	
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
	 * @return Returns the userGroups.
	 */
	public Collection getUserGroups() {
		return userGroups;
	}
	/**
	 * @param userGroup UserGroupResponseEvent
	 */
	public void addUserGroup(UserGroupResponseEvent userGroup) {
		this.userGroups.add(userGroup);
	}
	/**
	 * @return Returns the users.
	 */
	public Collection getUsers() {
		return users;
	}
	/**
	 * @param user UserResponseEvent
	 */
	public void addUser(SecurityUserResponseEvent user) {
		this.users.add(user);
	}
	/**
	 * @return Returns the features.
	 */
	public Collection getFeatures() {
		return features;
	}
	/**
	 * @param feature FeaturesResponseEvent
	 */
	public void addFeature(FeaturesResponseEvent feature) {
		this.features.add(feature);
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
}
