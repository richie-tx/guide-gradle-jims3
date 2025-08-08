//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRoleSystemActivitiesEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRoleSystemActivitiesEvent extends RequestEvent
{
	public String roleId;

	/**
	@roseuid 4107BED3032D
	 */
	public GetRoleSystemActivitiesEvent()
	{

	}

	/**
	@param roleId
	@roseuid 4106B5D703D2
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 4106B5D703D4
	 */
	public String getRoleId()
	{
		return this.roleId;
	}
}
