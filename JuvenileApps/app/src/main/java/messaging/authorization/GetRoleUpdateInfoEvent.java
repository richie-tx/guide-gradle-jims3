//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\GetRoleUpdateInfoEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class GetRoleUpdateInfoEvent extends RequestEvent
{
	public String logonId;

	/**
	@roseuid 4107BED303B9
	 */
	public GetRoleUpdateInfoEvent()
	{

	}

	/**
	@param logonId
	@roseuid 4106B5D500B9
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	/**
	@return String
	@roseuid 4106B5D500BB
	 */
	public String getLogonId()
	{
		return this.logonId;
	}
}
