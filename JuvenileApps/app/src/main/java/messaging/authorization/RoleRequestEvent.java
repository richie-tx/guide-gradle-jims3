/*
 * Created on Aug 2, 2004
 */
package messaging.authorization;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 *
 */
public class RoleRequestEvent extends RequestEvent {
	private String roleId;
	private String roleName;
	/**
	 * @return
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @return
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @param string
	 */
	public void setRoleId(String string)
	{
		roleId = string;
	}

	/**
	 * @param string
	 */
	public void setRoleName(String string)
	{
		roleName = string;
	}
}