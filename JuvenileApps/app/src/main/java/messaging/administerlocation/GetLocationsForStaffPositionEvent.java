package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetLocationsForStaffPositionEvent extends RequestEvent
{
	private String agencyId;
	private boolean inHouse;
	private String statusId;
	public String getAgencyId()
	{
		return agencyId;
	}
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}
	public boolean isInHouse()
	{
		return inHouse;
	}
	public void setInHouse(boolean inHouse)
	{
		this.inHouse = inHouse;
	}
	public String getStatusId()
	{
		return statusId;
	}
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}
}
