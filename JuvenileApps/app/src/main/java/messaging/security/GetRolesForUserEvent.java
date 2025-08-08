//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\GetRolesForUserEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetRolesForUserEvent extends RequestEvent
{
	public String logonId;

	/**
	 * @roseuid 429720DD00EE
	 */
	public GetRolesForUserEvent()
	{

	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}
}
