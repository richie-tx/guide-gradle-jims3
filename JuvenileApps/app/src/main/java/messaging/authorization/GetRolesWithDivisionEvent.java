//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRolesWithDivisionEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRolesWithDivisionEvent extends RequestEvent
{
	public String roleName;
	public String description;
	public String roleGroupId;
	public String roleTypeId;
	public String divisionId;

	/**
	 * @roseuid 41800055022A
	 */
	public GetRolesWithDivisionEvent()
	{

	}

	/**
	 * @param roleName
	 * @roseuid 418000120327
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * @return String
	 * @roseuid 418000120329
	 */
	public String getRoleName()
	{
		return this.roleName;
	}

	/**
	 * @param description
	 * @roseuid 41800012032B
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return String
	 * @roseuid 418000120335
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * @param roleGroupId
	 * @roseuid 418000120337
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	 * @return Numeric
	 * @roseuid 418000120339
	 */
	public String getRoleGroupId()
	{
		return this.roleGroupId;
	}

	/**
	 * @param roleTypeId
	 * @roseuid 41800012033B
	 */
	public void setRoleTypeId(String roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	/**
	 * @return String
	 * @roseuid 418000120344
	 */
	public String getRoleTypeId()
	{
		return this.roleTypeId;
	}

	/**
	 * @param divisionId
	 * @roseuid 418000120346
	 */
	public void setDivisionId(String divisionId)
	{
		this.divisionId = divisionId;
	}

	/**
	 * @return Numeric
	 * @roseuid 418000120348
	 */
	public String getDivisionId()
	{
		return this.divisionId;
	}
}