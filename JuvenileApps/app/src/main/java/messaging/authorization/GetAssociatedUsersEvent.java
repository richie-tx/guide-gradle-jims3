//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetAssociatedUsersEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetAssociatedUsersEvent extends RequestEvent
{
	public String roleId;

	/**
	@roseuid 4107BED203AD
	 */
	public GetAssociatedUsersEvent()
	{

	}

	/**
	@param roleId
	@roseuid 4106B5D800BC
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 4106B5D800BE
	 */
	public String getRoleId()
	{
		return this.roleId;
	}
}
