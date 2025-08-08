//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetRolesEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetRoleAgencyInfoEvent extends RequestEvent 
{
	private String roleId;
 
	/**
	 * @return
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @param string
	 */
	public void setRoleId(String string)
	{
		roleId = string;
	}

}
