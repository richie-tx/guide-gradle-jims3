//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\DeleteRoleGroupEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class DeleteRoleGroupEvent extends RequestEvent
{
	public String roleGroupId;

	/**
	@roseuid 4107BED201FF
	 */
	public DeleteRoleGroupEvent()
	{

	}

	/**
	@param roleGroupId
	@roseuid 4106B5D60292
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	@return String
	@roseuid 4106B5D6029A
	 */
	public String getRoleGroupId()
	{
		return this.roleGroupId;
	}
}
