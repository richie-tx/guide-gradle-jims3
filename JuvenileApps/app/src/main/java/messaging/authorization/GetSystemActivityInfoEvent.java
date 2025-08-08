//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetSystemActivityInfoEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetSystemActivityInfoEvent extends RequestEvent
{
	public String roleId;

	/**
	@roseuid 411142F402DF
	 */
	public GetSystemActivityInfoEvent()
	{

	}

	/**
	@param roleId
	@roseuid 4111424B005B
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 4111424B005D
	 */
	public String getRoleId()
	{
		return this.roleId;
	}
}
