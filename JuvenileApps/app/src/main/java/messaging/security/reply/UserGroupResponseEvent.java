/*
 * Created on May 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import java.util.ArrayList;
import java.util.Collection;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserGroupResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyId;
	private String agencyName;
	private String category;
	private String creatorFirstName;
	private String creatorId;
	private String creatorLastName;
	private String creatorMiddleName;
	private String description;
	private String name;
	private String status;
	private String statusId;
	private String userGroupId;
	private String userGroupType;
	private boolean roleExist;
	private Collection users = new ArrayList();
	private Collection roles = new ArrayList();
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @return
	 */
	public String getCreatorFirstName()
	{
		return creatorFirstName;
	}
	
	/**
	 * @return
	 */
	public String getCreatorId()
	{
		return creatorId;
	}

	/**
	 * @return
	 */
	public String getCreatorLastName()
	{
		return creatorLastName;
	}

	/**
	 * @return
	 */
	public String getCreatorMiddleName()
	{
		return creatorMiddleName;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @return
	 */
	public String getUserGroupId()
	{
		return userGroupId;
	}
	/**
	 * @return
	 */
	public String getUserGroupType()
	{
		return userGroupType;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 * @param agencyName
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 * @param creatorFirstName
	 */
	public void setCreatorFirstName(String creatorFirstName)
	{
		this.creatorFirstName = creatorFirstName;
	}

	/**
	 * @param creatorId
	 */
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}

	/**
	 * @param creatorName
	 */
	public void setCreatorLastName(String creatorLastName)
	{
		this.creatorLastName = creatorLastName;
	}

	/**
	 * @param creatorMiddleName
	 */
	public void setCreatorMiddleName(String creatorMiddleName)
	{
		this.creatorMiddleName = creatorMiddleName;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @param statusId
	 */
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}

	/**
	 * @param userGroupId
	 */
	public void setUserGroupId(String userGroupId)
	{
		this.userGroupId = userGroupId;
	}

	/**
	 * @param userGroupType
	 */
	public void setUserGroupType(String userGroupType)
	{
		this.userGroupType = userGroupType;
	}

	/**
	 * @return
	 */
	public boolean isRoleExist()
	{
		return roleExist;
	}

	/**
	 * @param b
	 */
	public void setRoleExist(boolean b)
	{
		roleExist = b;
	}

	/**
	 * @return Returns the users.
	 */
	public Collection getUsers() {
		return users;
	}
	
	/**
	 * @param user 
	 */
	public void addUser(SecurityUserResponseEvent suResp) {
		this.users.add(suResp);
	}
	/**
	 * @return Returns the roles.
	 */
	public Collection getRoles() {
		return roles;
	}
	/**
	 * @param roles The roles to set.
	 */
	public void setRoles(Collection roles) {
		this.roles = roles;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		UserGroupResponseEvent c = (UserGroupResponseEvent)o;
		if (c.getName() == null){
			return -1;
		}		
		if (this.getName() == null){
			return 1;
		}
		return this.getName().compareToIgnoreCase(c.getName());
	}	
}