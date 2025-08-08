/*
 * Created on Mar 26, 2004
 */
package messaging.authorization.reply;

import java.util.Collection;
import java.util.Date;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class RoleResponseEvent extends ResponseEvent
{
	public String action;
	public String agencyId;
	public String agencyName;
	private Date createDate;
	private String createTime;
	public String creatorId;
	public String division;
	public String divisionId;
	public String roleCreatorFirstName;
	public String roleCreatorLastName;
	public String roleDescription;
	public String roleId;
	public String roleName;
	public String roleType;
	public String roleTypeId;
	public Collection associatedRoleGroups;
	public Collection availableRoleGroups;
	public Collection associatedRoles;
	public Collection availableRoles;
	public Collection roleGroups;
	public Collection divisions;
	public Collection associatedSystemActivities;
	public Collection systemActivities;
	public Collection users;

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

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
		return this.agencyName;
	}

	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}
	/**
	 * @return
	 */
	public String getCreateTime()
	{
		return createTime;
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
	public String getDivision()
	{
		return division;
	}

	/**
	 * Getter for property divisionId.
	 * @return Value of property divisionId.
	 */
	public String getDivisionId()
	{
		return divisionId;
	}

	/**
	 * @return
	 */
	public String getRoleCreatorFirstName()
	{
		return roleCreatorFirstName;
	}

	/**
	 * Getter for property roleCreatorLastName.
	 * @return Value of property roleCreatorLastName.
	 */
	public String getRoleCreatorLastName()
	{
		return roleCreatorLastName;
	}

	/**
	 * Getter for property roleDescription.
	 * @return Value of property roleDescription.
	 */
	public String getRoleDescription()
	{
		return roleDescription;
	}

	/**
	 * @return
	 */
	public String getRoleId()
	{
		return this.roleId;
	}

	/**
	 * Getter for property roleName.
	 * @return Value of property roleName.
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @return
	 */
	public String getRoleType()
	{
		return this.roleType;
	}

	/**
	 * Getter for property roleType.
	 * @return Value of property roleType.
	 */
	public String getRoleTypeId()
	{
		return this.roleTypeId;
	}

	/**
	 * @param action
	 */
	public void setAction(String action)
	{
		this.action = action;
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
	 * @param createDate
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @param creatorId
	 */
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}

	/**
	 * @param division
	 */
	public void setDivision(String division)
	{
		this.division = division;
	}

	/**
	 * Setter for property divisionId.
	 * @param divisionId New value of property divisionId.
	 */
	public void setDivisionId(String divisionId)
	{
		this.divisionId = divisionId;
	}

	/**
	 * @param roleCreatorFirstName
	 */
	public void setRoleCreatorFirstName(String roleCreatorFirstName)
	{
		this.roleCreatorFirstName = roleCreatorFirstName;
	}

	/**
	 * Setter for property roleCreatorLastName.
	 * @param roleCreator New value of property roleCreatorLastName.
	 */
	public void setRoleCreatorLastName(String roleCreatorLastName)
	{
		this.roleCreatorLastName = roleCreatorLastName;
	}

	/**
	 * Setter for property roleDescription.
	 * @param roleDescription New value of property roleDescription.
	 */
	public void setRoleDescription(String roleDescription)
	{
		this.roleDescription = roleDescription;
	}

	/**
	 * @param roleId
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	 * Setter for property roleName.
	 * @param roleName New value of property roleName.
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * Setter for property roleType.
	 * @param roleType New value of property roleType.
	 */
	public void setRoleType(String roleType)
	{
		this.roleType = roleType;
	}

	/**
	 * @param roleTypeId
	 */
	public void setRoleTypeId(String roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}
	/**
	 * @return
	 */
	public Collection getAssociatedRoleGroups()
	{
		return associatedRoleGroups;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedRoleGroups(Collection collection)
	{
		associatedRoleGroups = collection;
	}

	/**
	 * @return
	 */
	public Collection getAvailableRoleGroups()
	{
		return availableRoleGroups;
	}

	/**
	 * @param collection
	 */
	public void setAvailableRoleGroups(Collection collection)
	{
		availableRoleGroups = collection;
	}

	/**
	 * @return
	 */
	public Collection getDivisions()
	{
		return divisions;
	}

	/**
	 * @param collection
	 */
	public void setDivisions(Collection collection)
	{
		divisions = collection;
	}

	/**
	 * @return
	 */
	public Collection getAssociatedSystemActivities()
	{
		return associatedSystemActivities;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedSystemActivities(Collection collection)
	{
		associatedSystemActivities = collection;
	}

	/**
	 * @return
	 */
	public Collection getRoleGroups()
	{
		return roleGroups;
	}

	/**
	 * @param collection
	 */
	public void setRoleGroups(Collection collection)
	{
		roleGroups = collection;
	}

	/**
	 * @return
	 */
	public Collection getSystemActivities()
	{
		return systemActivities;
	}

	/**
	 * @param collection
	 */
	public void setSystemActivities(Collection collection)
	{
		systemActivities = collection;
	}

	/**
	 * @return
	 */
	public Collection getUsers()
	{
		return users;
	}

	/**
	 * @param collection
	 */
	public void setUsers(Collection collection)
	{
		users = collection;
	}

	/**
	 * @return
	 */
	public Collection getAssociatedRoles()
	{
		return associatedRoles;
	}

	/**
	 * @return
	 */
	public Collection getAvailableRoles()
	{
		return availableRoles;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedRoles(Collection collection)
	{
		associatedRoles = collection;
	}

	/**
	 * @param collection
	 */
	public void setAvailableRoles(Collection collection)
	{
		availableRoles = collection;
	}

}