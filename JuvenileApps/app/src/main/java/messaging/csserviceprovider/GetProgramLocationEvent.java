package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetProgramLocationEvent extends RequestEvent
{
	private String progId;
	private String locationID;
	
	
	
	/**
	 * @return the progId
	 */
	public String getProgId() {
		return progId;
	}
	/**
	 * @param progId the progId to set
	 */
	public void setProgId(String progId) {
		this.progId = progId;
	}
	/**
	 * @return the locationID
	 */
	public String getLocationID() {
		return locationID;
	}
	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	
}
