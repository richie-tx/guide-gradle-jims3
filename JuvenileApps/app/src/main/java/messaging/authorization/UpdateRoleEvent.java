//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\UpdateRoleEvent.java

package messaging.authorization;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateRoleEvent extends CompositeRequest
{
	public String roleId;
	public String roleTypeId;
	public String roleName;
	public String creator;
	public String description;

	/**
	@roseuid 410FD86D0294
	 */
	public UpdateRoleEvent()
	{

	}

	/**
	@param roleId
	@roseuid 410FD811026C
	 */
	public void setRoleId(String newRoleId)
	{
		roleId = newRoleId;
	}

	/**
	@return String
	@roseuid 410FD811026E
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	@param roleTypeId
	@roseuid 410FD8110274
	 */
	public void setRoleTypeId(String newRoleTypeId)
	{
		roleTypeId = newRoleTypeId;
	}

	/**
	@return String
	@roseuid 410FD8110276
	 */
	public String getRoleTypeId()
	{
		return roleTypeId;
	}

	/**
	@param roleName
	@roseuid 410FD8110278
	 */
	public void setRoleName(String newRoleName)
	{
		roleName = newRoleName;
	}

	/**
	@return String
	@roseuid 410FD811027F
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	@param creator
	@roseuid 410FD8110281
	 */
	public void setCreator(String newCreator)
	{
		creator = newCreator;
	}

	/**
	@return String
	@roseuid 410FD8110283
	 */
	public String getCreator()
	{
		return creator;
	}

	/**
	@param description
	@roseuid 410FD8110288
	 */
	public void setDescription(String newDescription)
	{
		description = newDescription;
	}

	/**
	@return String
	@roseuid 410FD811028A
	 */
	public String getDescription()
	{
		return description;
	}
}
