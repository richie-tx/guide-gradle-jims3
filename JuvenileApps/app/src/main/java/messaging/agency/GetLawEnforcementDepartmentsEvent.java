//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\GetLawEnforcementAgenciesEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class GetLawEnforcementDepartmentsEvent extends RequestEvent
{
	public String lawEnforcementInd;

	/**
	 * @roseuid 41E8107A001F
	 */
	public GetLawEnforcementDepartmentsEvent()
	{

	}

	/**
	 * Access method for the lawEnforcementAgencyInd property.
	 * 
	 * @return   the current value of the lawEnforcementAgencyInd property
	 */
	public String getLawEnforcementInd()
	{
		return lawEnforcementInd;
	}

	/**
	 * Sets the value of the lawEnforcementAgencyInd property.
	 * 
	 * @param aLawEnforcementAgencyInd the new value of the lawEnforcementAgencyInd property
	 */
	public void setLawEnforcementInd(String lawEnforcementInd)
	{
		this.lawEnforcementInd = lawEnforcementInd;
	}

}
