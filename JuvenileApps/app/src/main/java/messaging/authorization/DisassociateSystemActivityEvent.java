//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\DisassociateSystemActivityEvent.java

package messaging.authorization;

import mojo.km.messaging.Composite.CompositeRequest;

public class DisassociateSystemActivityEvent extends CompositeRequest
{
	public String roleId;

	/**
	@roseuid 4107BED20295
	 */
	public DisassociateSystemActivityEvent()
	{

	}

	/**
	@param roleId
	@roseuid 4106B5D701FE
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 4106B5D70206
	 */
	public String getRoleId()
	{
		return roleId;
	}
}
