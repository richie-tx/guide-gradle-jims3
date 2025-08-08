//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRoleEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRoleEvent extends RequestEvent
{
	public String roleId;

	/**
	@roseuid 4107BED30052
	 */
	public GetRoleEvent()
	{

	}

	/**
	@param roleId
	@roseuid 4106B5D80184
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 4106B5D80186
	 */
	public String getRoleId()
	{
		return this.roleId;
	}
}
