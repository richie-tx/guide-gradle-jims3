/*
 * Created on Mar 26, 2004
 */
package messaging.authorization.reply;

import java.util.Collection;
import java.util.Date;
import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 */
public class RoleGroupResponseEvent extends ResponseEvent
{
	public String action;
	public String agency;
	public String agencyId;
	public Collection associatedRoles;
	public Collection availableRoles;
	public Date createDate;
	public String createTime;
	public String roleGroupCreator;
	public String roleGroupId;
	public String roleGroupName;
	public String roleId;

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
	public String getAgency()
	{
		return agency;
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
	public String getRoleGroupCreator()
	{
		return roleGroupCreator;
	}

	/**
	 * @return
	 */
	public String getRoleGroupId()
	{
		return roleGroupId;
	}

	/**
	 * @return
	 */
	public String getRoleGroupName()
	{
		return roleGroupName;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param agency
	 */
	public void setAgency(String agency)
	{
		this.agency = agency;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
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
	 * @param roleGroupCreator
	 */
	public void setRoleGroupCreator(String roleGroupCreator)
	{
		this.roleGroupCreator = roleGroupCreator;
	}

	/**
	 * @param roleGroupId
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	 * @param roleGroupName
	 */
	public void setRoleGroupName(String roleGroupName)
	{
		this.roleGroupName = roleGroupName;
	}

	/**
	 * @param roleGroupId
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	 * @return
	 */
	public String getRoleId()
	{
		return roleId;
	}

}