//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRolesEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRolesEvent extends RequestEvent
{
	public String description;
	public String roleName;
	public String roleTypeId;
	public String agencyId;
	public String divisionId;
	
	/**
	@roseuid 4107BED3028D
	 */
	public GetRolesEvent()
	{

	}

	/**
	@param description
	@roseuid 4106B5D70320
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	@return String
	@roseuid 4106B5D70329
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	@param roleName
	@roseuid 4106B5D70332
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	@return String
	@roseuid 4106B5D70334
	 */
	public String getRoleName()
	{
		return this.roleName;
	}

	/**
	@param roleTypeId
	@roseuid 4106B5D7033C
	 */
	public void setRoleTypeId(String roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	/**
	@return String
	@roseuid 4106B5D7033E
	 */
	public String getRoleTypeId()
	{
		return this.roleTypeId;
	}
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getDivisionId()
	{
		return divisionId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setDivisionId(String string)
	{
		divisionId = string;
	}

}
