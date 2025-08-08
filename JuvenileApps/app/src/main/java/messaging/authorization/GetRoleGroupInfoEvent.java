//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRoleGroupInfoEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRoleGroupInfoEvent extends RequestEvent
{
	public String agencyId;
	public String divisionId;
	public String roleGroupId;

	/**
	@roseuid 4107BED30174
	 */
	public GetRoleGroupInfoEvent()
	{

	}

	/**
	@param agencyId
	@roseuid 4106B5D6011D
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	@return String
	@roseuid 4106B5D6011F
	 */
	public String getAgencyId()
	{
		return this.agencyId;
	}

	/**
	@param divisionId
	@roseuid 4106B5D60128
	 */
	public void setDivisionId(String divisionId)
	{
		this.divisionId = divisionId;
	}

	/**
	@return String
	@roseuid 4106B5D6012A
	 */
	public String getDivisionId()
	{
		return this.divisionId;
	}

	/**
	@param roleGroupId
	@roseuid 4106B5D60132
	 */
	public void setRoleGroupId(String roleGroupId)
	{
		this.roleGroupId = roleGroupId;
	}

	/**
	@return String
	@roseuid 4106B5D60134
	 */
	public String getRoleGroupId()
	{
		return this.roleGroupId;
	}
}
