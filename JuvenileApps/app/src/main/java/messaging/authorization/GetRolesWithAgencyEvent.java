//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRolesWithAgencyEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRolesWithAgencyEvent extends RequestEvent
{
	public String roleName;
	public String description;
	public String roleTypeId;
	public String roleGroupId;

	/**
	 * @roseuid 417FFE6A01BD
	 */
	public GetRolesWithAgencyEvent()
	{

	}

	/**
	 * @param roleName
	 * @roseuid 417FFE1603DF
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * @return String
	 * @roseuid 417FFE1603E1
	 */
	public String getRoleName()
	{
		return this.roleName;
	}

	/**
	 * @param description
	 * @roseuid 417FFE170007
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return String
	 * @roseuid 417FFE170009
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * @param roleTypeId
	 * @roseuid 417FFE17000B
	 */
	public void setRoleTypeId(String roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	/**
	 * @return String
	 * @roseuid 417FFE17000D
	 */
	public String getRoleTypeId()
	{
		return this.roleTypeId;
	}

	/**
	 * @param roleGroupId
	 * @roseuid 417FFE170018
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	 * @return Numeric
	 * @roseuid 417FFE17001A
	 */
	public String getRoleGroupId()
	{
		return this.roleGroupId;
	}

}
