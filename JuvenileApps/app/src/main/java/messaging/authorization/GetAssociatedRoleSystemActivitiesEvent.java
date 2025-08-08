//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetAssociatedRoleSystemActivitiesEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetAssociatedRoleSystemActivitiesEvent extends RequestEvent
{
	public String roleId;

	/**
	@roseuid 4107BED20317
	 */
	public GetAssociatedRoleSystemActivitiesEvent()
	{

	}

	/**
	@param roleId
	@roseuid 4106B5D70153
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 4106B5D7015C
	 */
	public String getRoleId()
	{
		return this.roleId;
	}
}
