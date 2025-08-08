//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\PrepareRoleUpdateEvent.java

package messaging.authorization;

import mojo.km.messaging.Composite.CompositeRequest;

public class PrepareRoleUpdateEvent extends CompositeRequest
{
	public String roleId;
	public String roleName;
	public String description;
	public String roleTypeId;

	/**
	@roseuid 412F35D20032
	 */
	public PrepareRoleUpdateEvent()
	{

	}

	/**
	@param roleId
	@roseuid 412F354F0160
	 */
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	/**
	@return String
	@roseuid 412F354F016A
	 */
	public String getRoleId()
	{
		return this.roleId;
	}

	/**
	@param roleName
	@roseuid 412F354F016C
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	@return String
	@roseuid 412F354F0175
	 */
	public String getRoleName()
	{
		return this.roleName;
	}

	/**
	@param description
	@roseuid 412F354F017E
	 */
	public void setDescription(String description)
	{
		this.description=description;
	}

	/**
	@return String
	@roseuid 412F354F0180
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	@param roleTypeId
	@roseuid 412F354F0189
	 */
	public void setRoleTypeId(String roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	/**
	@return String
	@roseuid 412F354F0193
	 */
	public String getRoleTypeId()
	{
		return this.roleTypeId;
	}
}
