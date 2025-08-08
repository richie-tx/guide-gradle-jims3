//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\GetJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class GetJIMS2AccountByOfficerIdEvent extends RequestEvent
{
	public String officerId;

	/**
	 * @roseuid 4399CD3A02A3
	 */
	public GetJIMS2AccountByOfficerIdEvent()
	{

	}

	/**
	 * Access method for the officerId property.
	 * 
	 * @return   the current value of the officerId property
	 */
	public String getOfficerId()
	{
		return officerId;
	}

	/**
	 * Sets the value of the officerId property.
	 * 
	 * @param aLogonId the new value of the officerId property
	 */
	public void setOfficerId(String aOfficerId)
	{
		officerId = aOfficerId;
	}

}
