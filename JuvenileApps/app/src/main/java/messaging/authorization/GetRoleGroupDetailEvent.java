//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRoleGroupDetailEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRoleGroupDetailEvent extends RequestEvent
{
	public String roleGroupId;
	public String type;

	/**
	@roseuid 412D10A602FD
	 */
	public GetRoleGroupDetailEvent()
	{

	}

	/**
	Access method for the type property.
	
	@return   the current value of the type property
	 */
	public String getType()
	{
		return type;
	}

	/**
	Sets the value of the type property.
	
	@param aType the new value of the type property
	 */
	public void setType(String aType)
	{
		type = aType;
	}

	/**
	@param roleGroupId
	@roseuid 412D0FF70308
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	@return String
	@roseuid 412D0FF70310
	 */
	public String getRoleGroupId()
	{
		return this.roleGroupId;
	}
}
