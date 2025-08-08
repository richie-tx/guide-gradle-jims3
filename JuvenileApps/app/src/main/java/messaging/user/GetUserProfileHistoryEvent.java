//Source file: C:\\views\\dev\\app\\src\\messaging\\user\\GetUserProfileHistoryEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 */
public class GetUserProfileHistoryEvent extends RequestEvent
{
	public String logonId;

	/**
	 * @roseuid 42E67E51014B
	 */
	public GetUserProfileHistoryEvent()
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