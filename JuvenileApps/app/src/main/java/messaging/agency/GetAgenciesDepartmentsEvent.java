//Source file: C:\\views\\dev\\app\\src\\messaging\\contact\\GetAgenciesByJmcRepEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class GetAgenciesDepartmentsEvent extends RequestEvent
{
	public String agencyTypeId;
	public String agencyId;
	public String agencyName;
	public String jmcRepId;

	/**
	@roseuid 4106BB01026A
	 */
	public GetAgenciesDepartmentsEvent()
	{

	}

	/**
	@return String
	@roseuid 4106B569008A
	 */
	public String getAgencyTypeId()
	{
		return this.agencyTypeId;
	}

	/**
	@return String
	@roseuid 4106B5690050
	 */
	public String getAgencyId()
	{
		return this.agencyId;
	}

	/**
	@return String
	@roseuid 4106B5690047
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	@param agencyTypeId
	@roseuid 4106B5690082
	 */
	public void setAgencyTypeId(String agencyTypeId)
	{
		this.agencyTypeId = agencyTypeId;
	}

	/**
	@param agencyId
	@roseuid 4106B569004E
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	@param agencyName
	@roseuid 4106B5690045
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	/**
	 * @return
	 */
	public String getJmcRepId()
	{
		return jmcRepId;
	}

	/**
	 * @param string
	 */
	public void setJmcRepId(String string)
	{
		jmcRepId = string;
	}

}