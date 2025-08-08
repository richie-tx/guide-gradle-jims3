//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetSystemActivitiesEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetSystemActivitiesEvent extends RequestEvent
{
	public String systemActivityDescription;
	public String systemActivityName;

	/**
	@roseuid 4107BED4005D
	 */
	public GetSystemActivitiesEvent()
	{

	}

	/**
	@param systemActivityDescription
	@roseuid 4106B5D503A8
	 */
	public void setSystemActivityDescription(String systemActivityDescription)
	{
		this.systemActivityDescription = systemActivityDescription;
	}

	/**
	@return String
	@roseuid 4106B5D503AA
	 */
	public String getSystemActivityDescription()
	{
		return this.systemActivityDescription;
	}

	/**
	@param systemActivityName
	@roseuid 4106B5D503B2
	 */
	public void setSystemActivityName(String systemActivityName)
	{
		this.systemActivityName = systemActivityName;
	}

	/**
	@return String
	@roseuid 4106B5D503BB
	 */
	public String getSystemActivityName()
	{
		return this.systemActivityName;
	}
}
