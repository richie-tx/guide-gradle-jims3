/*
 * Created on May 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoleUserGroupsResponseEvent extends ResponseEvent
{
	private String description;
	private String name;
	private Collection users;
	private String userGroupId;	
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
	public Collection getUsers()
	{
		return users;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param collection
	 */
	public void setUsers(Collection collection)
	{
		users = collection;
	}

	/**
	 * @return Returns the userGroupId.
	 */
	public String getUserGroupId() {
		return userGroupId;
	}
	/**
	 * @param userGroupId The userGroupId to set.
	 */
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
}
