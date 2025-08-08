//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\GetJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class GetJIMS2AccountByJIMSLogonIdEvent extends RequestEvent
{
	public String logonId;

	/**
	 * @roseuid 4399CD3A02A3
	 */
	public GetJIMS2AccountByJIMSLogonIdEvent()
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
