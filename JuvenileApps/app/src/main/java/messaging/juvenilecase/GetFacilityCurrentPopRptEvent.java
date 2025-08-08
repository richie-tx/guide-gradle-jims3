package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetFacilityCurrentPopRptEvent extends RequestEvent {
	
	public GetFacilityCurrentPopRptEvent()
	{
	}
	
	public String facilityCode;
	/**
	 * @return Returns the facilityCode.
	 */
	public String getFacilityCode() {
		return facilityCode;
	}
	/**
	 * @param facilityCode The facilityCode to set.
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

}
