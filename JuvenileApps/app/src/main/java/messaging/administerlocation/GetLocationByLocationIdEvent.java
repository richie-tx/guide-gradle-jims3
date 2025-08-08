package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetLocationByLocationIdEvent extends RequestEvent
{
	private String agencyId;
	private String statusId;
	private String locationId;
	
	public String getAgencyId()
	{
		return agencyId;
	}
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}
	public String getStatusId()
	{
		return statusId;
	}
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
}
