//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\UpdateRoleGroupEvent.java

package messaging.authorization;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateRoleGroupEvent extends CompositeRequest
{
	public String roleGroupId;
	public String roleGroupName;

	/**
	@roseuid 4107BED4025C
	 */
	public UpdateRoleGroupEvent()
	{

	}

	/**
	@param roleGroupId
	@roseuid 4106B5D601F0
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	@return String
	@roseuid 4106B5D601F2
	 */
	public String getRoleGroupId()
	{
		return this.roleGroupId;
	}

	/**
	@param roleGroupName
	@roseuid 4106B5D601FA
	 */
	public void setRoleGroupName(String roleGroupName)
	{
		this.roleGroupName = roleGroupName;
	}

	/**
	@return String
	@roseuid 4106B5D601FC
	 */
	public String getRoleGroupName()
	{
		return this.roleGroupName;
	}
}
