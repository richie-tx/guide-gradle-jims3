//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\GetOfficerProfileEvent.java

package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class GetOfficerProfileEvent extends RequestEvent
{
	private String officerProfileId;
	
	/**
	 * @roseuid 42E679FD028D
	 */
	public GetOfficerProfileEvent()
	{

	}

	/**
	 * Access method for the officerProfileId property.
	 * 
	 * @return   the current value of the officerProfileId property
	 */
	public String getOfficerProfileId()
	{
		return officerProfileId;
	}

	/**
	 * Sets the value of the officerProfileId property.
	 * 
	 * @param aOfficerProfileId the new value of the officerProfileId property
	 */
	public void setOfficerProfileId(String aOfficerProfileId)
	{
		officerProfileId = aOfficerProfileId;
	}
}
